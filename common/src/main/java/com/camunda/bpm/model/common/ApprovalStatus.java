package com.camunda.bpm.model.common;

public enum ApprovalStatus {
    APPROVED("Approved"), DECLINED("Declined"), REQUEST_CHANGES("Request Changes"),
    CANCEL("Cancel"), PAYMENT_ISSUES("Payment Issues");

    private String description;

    ApprovalStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
