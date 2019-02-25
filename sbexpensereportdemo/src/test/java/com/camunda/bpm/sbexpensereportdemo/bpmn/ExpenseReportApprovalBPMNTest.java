package com.camunda.bpm.sbexpensereportdemo.bpmn;

import com.camunda.bpm.model.common.ApprovalStatus;
import com.camunda.bpm.model.expenses.ExpenseReport;
import com.camunda.bpm.sbexpensereportdemo.util.ProcessConstants;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

import java.util.HashMap;

import static com.camunda.bpm.sbexpensereportdemo.util.TestUtils.createTestExpenseReport;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ExpenseReportApprovalBPMNTest {

  private static final String PROCESS_DEFINITION_KEY = "expense-report-approval-process";

  @Autowired
  private ProcessEngine processEngine;

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Rule @ClassRule
  public static ProcessEngineRule rule;

  @PostConstruct
  void initRule() {
    rule = TestCoverageProcessEngineRuleBuilder.create(processEngine).build();
  }

  @Before
  public void setup() {
    init(processEngine);
  }

  @Test
  @Deployment(resources = {"define-approval-level-er.dmn", "expense-report-process.bpmn", "payment-process.bpmn"})
  public void testHappyPath() {
    ExpenseReport expenseReport = createTestExpenseReport();
    ObjectValue objectValue = Variables.objectValue(expenseReport)
            .serializationDataFormat(Variables.SerializationDataFormats.JSON).create();

    ProcessInstance processInstance = processEngine.getRuntimeService()
            .startProcessInstanceByKey(PROCESS_DEFINITION_KEY, expenseReport.getNumber(),
                    withVariables(ProcessConstants.VARIABLE_EXPENSEREPORT_NAME, objectValue));

    assertThat(processInstance.getBusinessKey().equals(expenseReport.getNumber()));
    assertThat(processInstance).hasPassed("DefineApprovalLevelTask");
    assertThat(processInstance).hasPassed("DefineApproversTask");
    assertThat(processInstance).isWaitingAt("ApproveExpenseReportTask");

    expenseReport.setLastApproval(ApprovalStatus.APPROVED);

    HashMap<String, Object> approvalVariables = new HashMap<String, Object>();
    approvalVariables.put("expenseReport", expenseReport);

    taskQuery().active().list().forEach(task -> complete(task, approvalVariables));

    assertThat(processInstance).hasPassed("NotifyApprovalTask");
    assertThat(processInstance).isWaitingAt("PaymentProcessCallActivity");
    ProcessInstance paymentProcess = calledProcessInstance();
    assertThat(paymentProcess).hasPassed("SendPaymentRequestTask");
    assertThat(paymentProcess).isWaitingAt("WaitForPaymentConfirmationTask");

    processEngine.getRuntimeService().createMessageCorrelation("MessagePaymentConfirmation")
            .processInstanceBusinessKey(expenseReport.getNumber())
            .setVariable("paymentCorrect", true).correlate();

    assertThat(paymentProcess).isEnded();
    assertThat(processInstance).isEnded();
  }

}
