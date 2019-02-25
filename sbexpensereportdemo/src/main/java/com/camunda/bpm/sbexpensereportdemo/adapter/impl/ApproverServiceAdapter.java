package com.camunda.bpm.sbexpensereportdemo.adapter.impl;

import com.camunda.bpm.sbexpensereportdemo.adapter.CrudServiceAdapter;
import com.camunda.bpm.model.common.Approver;
import com.camunda.bpm.sbexpensereportdemo.util.CustomRestTemplateBuilder;
import com.camunda.bpm.sbexpensereportdemo.util.PropertyNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.camunda.bpm.sbexpensereportdemo.util.ApplicationHelper.buildURI;

@Component
public class ApproverServiceAdapter implements CrudServiceAdapter<Approver> {

    @Value(PropertyNames.ERP_REST_APPROVER_BASEPATH)
    private String ERP_REST_APPROVER_BASEPATH;
    @Value(PropertyNames.ERP_REST_URL)
    private String ERP_REST_URL;
    private RestTemplate restTemplate;

    @Autowired
    public ApproverServiceAdapter(@Autowired CustomRestTemplateBuilder restTemplateBuilder,
                                  @Value(PropertyNames.ERP_REST_TIMEOUT) Integer timeout) {
        this.restTemplate = restTemplateBuilder.build(timeout);
    }

    @Override
    public void merge(Approver object) {

    }

    @Override
    public List<Approver> findAll() {
        URI uri = buildURI(ERP_REST_URL, ERP_REST_APPROVER_BASEPATH);
        return Arrays.asList(restTemplate.getForEntity(uri, Approver[].class).getBody());
    }

    @Override
    public Approver findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
