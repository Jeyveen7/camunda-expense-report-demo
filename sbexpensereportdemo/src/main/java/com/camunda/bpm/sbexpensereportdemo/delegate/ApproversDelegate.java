package com.camunda.bpm.sbexpensereportdemo.delegate;

import com.camunda.bpm.sbexpensereportdemo.adapter.CrudServiceAdapter;
import com.camunda.bpm.model.common.Approver;
import com.camunda.bpm.model.expenses.ExpenseReport;
import com.camunda.bpm.sbexpensereportdemo.mapper.ApprovalMapper;
import com.camunda.bpm.sbexpensereportdemo.util.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApproversDelegate implements JavaDelegate {

    @Autowired
    private CrudServiceAdapter<Approver> crudServiceAdapter;
    @Autowired
    private ProcessVariables processVariables;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ExpenseReport expenseReport = processVariables.getExpenseReport(delegateExecution);
        Long approvalLevel = processVariables.getApprovalLevel(delegateExecution);

        List<Approver> approvers = crudServiceAdapter.findAll()
                .stream()
                .filter(approver -> approver.getApprovalLevel() <= approvalLevel)
                .collect(Collectors.toList());

        expenseReport.setApprovals(ApprovalMapper.INSTANCE.approversToApprovals(approvers));
        processVariables.setExpenseReport(delegateExecution, expenseReport);
    }
}
