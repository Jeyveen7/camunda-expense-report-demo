package com.camunda.bpm.sbexpensereportdemo.delegate;

import com.camunda.bpm.model.common.Participant;
import com.camunda.bpm.model.expenses.ExpenseReport;
import com.camunda.bpm.sbexpensereportdemo.adapter.NotificationServiceAdapter;
import com.camunda.bpm.model.notification.Email;
import com.camunda.bpm.sbexpensereportdemo.util.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationDelegate implements JavaDelegate {

    @Autowired
    private ProcessVariables processVariables;
    @Autowired
    private EmailBuilder<ExpenseReport> emailBuilder;
    @Autowired
    private NotificationServiceAdapter<Email> notificationServiceAdapter;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Participant participant = (Participant) delegateExecution.getVariableLocal("participant");
        EmailType emailType = EmailType.valueOf((String) delegateExecution.getVariableLocal("emailType"));

        Email email = emailBuilder.buildEmail(ProcessConstants.VARIABLE_EXPENSEREPORT_NAME, processVariables.getExpenseReport(delegateExecution), emailType, participant);
        notificationServiceAdapter.send(email);
    }
}
