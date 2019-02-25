package com.camunda.bpm.servicesdemo.resource;

import com.camunda.bpm.servicesdemo.adapter.CamundaAdapter;
import com.camunda.bpm.servicesdemo.entity.expense.Expense;
import com.camunda.bpm.servicesdemo.entity.expense.ExpenseReport;
import com.camunda.bpm.servicesdemo.repository.ExpenseReportRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseReportResource {

    @Autowired
    private ExpenseReportRepository repository;

    @Autowired
    private CamundaAdapter camundaAdapter;

    @PostMapping("expenses/expense-reports")
    public ExpenseReport merge(@RequestBody ExpenseReport expenseReport) {
        ExpenseReport newExpenseReport = repository.save(expenseReport);
        try {
            camundaAdapter.startExpenseReportProcess(newExpenseReport);
        }
        catch(Exception e){
            repository.delete(newExpenseReport);
        }
        return newExpenseReport;
    }

    @GetMapping("expenses/expense-reports")
    public List<ExpenseReport> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

}
