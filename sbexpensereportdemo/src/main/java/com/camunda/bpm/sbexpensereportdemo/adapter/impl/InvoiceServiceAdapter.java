package com.camunda.bpm.sbexpensereportdemo.adapter.impl;

import com.camunda.bpm.model.accountspayable.Invoice;
import com.camunda.bpm.sbexpensereportdemo.adapter.CrudServiceAdapter;
import com.camunda.bpm.sbexpensereportdemo.util.CustomRestTemplateBuilder;
import com.camunda.bpm.sbexpensereportdemo.util.PropertyNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.camunda.bpm.sbexpensereportdemo.util.ApplicationHelper.buildURI;

@Component
public class InvoiceServiceAdapter implements CrudServiceAdapter<Invoice> {

    @Value(PropertyNames.ERP_REST_INVOICE_BASEPATH)
    private String ERP_REST_INVOICE_BASEPATH;
    @Value(PropertyNames.ERP_REST_URL)
    private String ERP_REST_URL;
    private RestTemplate restTemplate;

    @Autowired
    public InvoiceServiceAdapter(@Autowired CustomRestTemplateBuilder restTemplateBuilder,
                               @Value(PropertyNames.ERP_REST_TIMEOUT) Integer timeout) {
        this.restTemplate = restTemplateBuilder.build(timeout);
    }

    @Override
    public void merge(Invoice invoice){
        URI uri = buildURI(ERP_REST_URL, ERP_REST_INVOICE_BASEPATH);
        restTemplate.postForEntity(uri, invoice, Invoice.class);
    }

    @Override
    public List<Invoice> findAll() {
        URI uri = buildURI(ERP_REST_URL, ERP_REST_INVOICE_BASEPATH);
        return restTemplate.getForEntity(uri, List.class).getBody();
    }

    @Override
    public Invoice findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
