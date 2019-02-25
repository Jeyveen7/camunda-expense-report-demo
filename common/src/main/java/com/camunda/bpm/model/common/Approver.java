package com.camunda.bpm.model.common;

public class Approver {

    private Participant participant;

    private Long approvalLevel;

    public Long getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(Long approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
