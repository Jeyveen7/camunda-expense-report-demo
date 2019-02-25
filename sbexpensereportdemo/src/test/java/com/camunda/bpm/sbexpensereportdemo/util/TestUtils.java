package com.camunda.bpm.sbexpensereportdemo.util;

import com.camunda.bpm.model.common.Participant;
import com.camunda.bpm.model.expenses.Expense;
import com.camunda.bpm.model.expenses.ExpenseReport;
import com.camunda.bpm.model.expenses.ExpenseReportReason;

import java.util.Date;
import java.util.Random;

public class TestUtils {

    public static ExpenseReport createTestExpenseReport(){
        Expense expense = new Expense();
        expense.setAmount(10000.00);
        expense.setDescription("Some description...");
        expense.setExpenseDate(new Date());

        Participant requester = new Participant();
        requester.setName("Lucas da Silva");
        requester.setUser("lucas.silva");
        requester.setEmail("lucas.silva@camunda.com");

        Participant expenseManager = new Participant();
        requester.setName("Lucas da Silva");
        requester.setUser("demo");
        requester.setEmail("lucas.silva@camunda.com");

        ExpenseReport expenseReport = new ExpenseReport();
        expenseReport.setNumber(String.valueOf(getRandomNumberInRange(10000, 20000)));
        expenseReport.setRequester(requester);
        expenseReport.setExpenseManager(expenseManager);
        expenseReport.setReason(ExpenseReportReason.CONSULTING);
        expenseReport.setJustification("Some justification...");
        expenseReport.setCreationDate(new Date());
        expenseReport.getExpenses().add(expense);

        expenseReport.setTotalAmount(expenseReport.getExpenses().
                stream().mapToDouble(o -> o.getAmount()).sum());

        return expenseReport;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
