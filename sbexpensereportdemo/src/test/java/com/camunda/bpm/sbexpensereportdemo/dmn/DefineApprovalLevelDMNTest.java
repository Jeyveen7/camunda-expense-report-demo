package com.camunda.bpm.sbexpensereportdemo.dmn;

import com.camunda.bpm.model.expenses.ExpenseReport;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static com.camunda.bpm.sbexpensereportdemo.util.TestUtils.createTestExpenseReport;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DefineApprovalLevelDMNTest {

    private static final String DECISION_DEFINITION_KEY = "define-approval-level-er";

    @Autowired
    public ProcessEngine rule;

    @Test
    @Deployment(resources = {"define-approval-level-er.dmn"})
    public void testHappyPath(){
        ExpenseReport expenseReport = createTestExpenseReport();
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("amount", expenseReport.getTotalAmount());

        DmnDecisionTableResult decisionResult = rule.getDecisionService()
                .evaluateDecisionTableByKey(DECISION_DEFINITION_KEY)
                .variables(variables).evaluate();
        assertThat(decisionResult.getFirstResult()).containsEntry("approvalLevel", new Long(3));
    }
}
