package com.camunda.bpm.servicesdemo.repository;

import com.camunda.bpm.servicesdemo.entity.expense.ExpenseReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseReportRepository extends JpaRepository<ExpenseReport, Long> {
}
