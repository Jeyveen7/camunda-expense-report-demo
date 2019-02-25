package com.camunda.bpm.sbexpensereportdemo.adapter.impl;

import com.camunda.bpm.model.notification.Email;
import com.camunda.bpm.sbexpensereportdemo.adapter.NotificationServiceAdapter;
import com.camunda.bpm.sbexpensereportdemo.util.CustomRestTemplateBuilder;
import com.camunda.bpm.sbexpensereportdemo.util.PropertyNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.camunda.bpm.sbexpensereportdemo.util.ApplicationHelper.buildURI;

@Component
public class EmailServiceAdapter implements NotificationServiceAdapter<Email> {

    @Value(PropertyNames.ERP_REST_EMAIL_BASEPATH)
    private String ERP_REST_EMAIL_BASEPATH;
    @Value(PropertyNames.ERP_REST_URL)
    private String ERP_REST_URL;
    private RestTemplate restTemplate;

    @Autowired
    public EmailServiceAdapter(@Autowired CustomRestTemplateBuilder restTemplateBuilder,
                                  @Value(PropertyNames.ERP_REST_TIMEOUT) Integer timeout) {
        this.restTemplate = restTemplateBuilder.build(timeout);
    }

    @Override
    public void send(Email notification) {
        URI uri = buildURI(ERP_REST_URL, ERP_REST_EMAIL_BASEPATH);
        restTemplate.postForEntity(uri, notification, Email.class);
    }
}
