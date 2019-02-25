package com.camunda.bpm.sbexpensereportdemo.delegate;

import com.camunda.bpm.model.expenses.ExpenseReport;
import com.camunda.bpm.sbexpensereportdemo.adapter.CrudServiceAdapter;
import com.camunda.bpm.sbexpensereportdemo.util.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateERDelegate implements JavaDelegate {

    @Autowired
    private ProcessVariables processVariables;

    @Autowired
    private CrudServiceAdapter<ExpenseReport> crudServiceAdapter;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ExpenseReport expenseReport = processVariables.getExpenseReport(delegateExecution);
        crudServiceAdapter.merge(expenseReport);
    }
}
