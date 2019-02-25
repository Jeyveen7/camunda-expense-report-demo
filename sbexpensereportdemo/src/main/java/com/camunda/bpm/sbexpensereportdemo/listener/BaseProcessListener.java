package com.camunda.bpm.sbexpensereportdemo.listener;

import com.camunda.bpm.model.expenses.ExpenseReport;
import com.camunda.bpm.sbexpensereportdemo.util.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class BaseProcessListener implements ExecutionListener {

    private static final Logger LOGGER = Logger.getLogger(BaseProcessListener.class.getName());
    @Autowired
    private ProcessVariables processVariables;

    public void notify(DelegateExecution execution) throws Exception {
        ExpenseReport expenseReport = processVariables.getExpenseReport(execution);
        processVariables.setExpenseReport(execution, expenseReport);
    }
}