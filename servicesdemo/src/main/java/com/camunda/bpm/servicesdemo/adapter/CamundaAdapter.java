package com.camunda.bpm.servicesdemo.adapter;

import com.camunda.bpm.servicesdemo.entity.expense.ExpenseReport;
import com.camunda.bpm.servicesdemo.util.CustomRestTemplateBuilder;
import com.camunda.bpm.servicesdemo.util.PropertyNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.camunda.bpm.servicesdemo.util.ApplicationHelper.buildURI;

@Component
public class CamundaAdapter {

    @Value(PropertyNames.CAMUNDA_REST_URL)
    private String CAMUNDA_REST_URL;

    @Value(PropertyNames.CAMUNDA_REST_EXPENSE_REPORT_PROCESS_START_PATH)
    private String CAMUNDA_REST_EXPENSE_REPORT_PROCESS_START_PATH;

    @Value(PropertyNames.CAMUNDA_REST_EXPENSE_REPORT_PROCESS_CANCEL_PATH)
    private String CAMUNDA_REST_EXPENSE_REPORT_PROCESS_CANCEL_PATH;

    private RestTemplate restTemplate;

    @Autowired
    public CamundaAdapter(@Autowired CustomRestTemplateBuilder restTemplateBuilder,
                          @Value(PropertyNames.CAMUNDA_REST_TIMEOUT) Integer timeout) {
        this.restTemplate = restTemplateBuilder.build(timeout);
    }

    public void startExpenseReportProcess(ExpenseReport expenseReport){
        URI url = buildURI(CAMUNDA_REST_URL, CAMUNDA_REST_EXPENSE_REPORT_PROCESS_START_PATH);
        restTemplate.postForEntity(url, expenseReport, ExpenseReport.class);
    }

    public void cancelExpenseReportProcess(ExpenseReport expenseReport){
        URI url = buildURI(CAMUNDA_REST_URL, CAMUNDA_REST_EXPENSE_REPORT_PROCESS_CANCEL_PATH);
        restTemplate.postForEntity(url, expenseReport, ExpenseReport.class);
    }
}
