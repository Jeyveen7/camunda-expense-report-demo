package com.camunda.bpm.sbexpensereportdemo.adapter.impl;

import com.camunda.bpm.model.expenses.ExpenseReport;
import com.camunda.bpm.sbexpensereportdemo.adapter.CrudServiceAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseReportServiceAdapter implements CrudServiceAdapter<ExpenseReport> {

    @Override
    public void merge(ExpenseReport object) {

    }

    @Override
    public List<ExpenseReport> findAll() {
        return null;
    }

    @Override
    public ExpenseReport findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
