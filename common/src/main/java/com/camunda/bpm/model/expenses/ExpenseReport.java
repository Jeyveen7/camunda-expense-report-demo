package com.camunda.bpm.model.expenses;

import com.camunda.bpm.model.common.Approval;
import com.camunda.bpm.model.common.ApprovalStatus;
import com.camunda.bpm.model.common.Participant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseReport{

    private Long id;
    private String number;
    private Participant requester;
    private Participant expenseManager;
    private ExpenseReportReason reason;
    private String justification;
    private Date creationDate;
    private Double totalAmount;
    private List<Approval> approvals = new ArrayList<Approval>();
    private List<Expense> expenses = new ArrayList<Expense>();
    private ApprovalStatus lastApproval;
    private String changesDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Participant getRequester() {
        return requester;
    }

    public void setRequester(Participant requester) {
        this.requester = requester;
    }

    public Participant getExpenseManager() {
        return expenseManager;
    }

    public void setExpenseManager(Participant expenseManager) {
        this.expenseManager = expenseManager;
    }

    public ExpenseReportReason getReason() {
        return reason;
    }

    public void setReason(ExpenseReportReason reason) {
        this.reason = reason;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Approval> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public ApprovalStatus getLastApproval() {
        return lastApproval;
    }

    public void setLastApproval(ApprovalStatus lastApproval) {
        this.lastApproval = lastApproval;
    }

    public String getChangesDescription() {
        return changesDescription;
    }

    public void setChangesDescription(String changesDescription) {
        this.changesDescription = changesDescription;
    }
}
