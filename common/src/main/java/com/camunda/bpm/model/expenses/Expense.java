package com.camunda.bpm.model.expenses;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {

    private Long id;
    private Double amount;
    private Date expenseDate;
    private String description;
    private String receiptFileEncoded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceiptFileEncoded() {
        return receiptFileEncoded;
    }

    public void setReceiptFileEncoded(String receiptFileEncoded) {
        this.receiptFileEncoded = receiptFileEncoded;
    }
}
