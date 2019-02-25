package com.camunda.bpm.sbexpensereportdemo.util;

import com.camunda.bpm.model.expenses.ExpenseReport;
import com.camunda.bpm.sbexpensereportdemo.exception.RequesterNotFound;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.camunda.spin.Spin.JSON;

@Component
public class ProcessVariables {

    public ExpenseReport getExpenseReport(DelegateExecution execution){
        Object obj = execution.getVariable(ProcessConstants.VARIABLE_EXPENSEREPORT_NAME);

        if(obj instanceof ExpenseReport)
            return (ExpenseReport) obj;
        else
            return JSON(obj).mapTo(ExpenseReport.class);

    }

    public void setExpenseReport(DelegateExecution execution, ExpenseReport expenseReport){
        ObjectValue expenseReportJson = Variables.objectValue(expenseReport)
                .serializationDataFormat(ProcessConstants.SERIALIZATION_FORMAT)
                .create();

        execution.setVariable(ProcessConstants.VARIABLE_EXPENSEREPORT_NAME, expenseReportJson);
    }

    public Long getApprovalLevel(DelegateExecution execution){
        return (Long) execution.getVariable(ProcessConstants.VARIABLE_APPROVALLEVEL_NAME);
    }

    public void setApprovalLevel(DelegateExecution execution, Long approvalLevel){
        execution.setVariable(ProcessConstants.VARIABLE_APPROVALLEVEL_NAME, approvalLevel);
    }
}
