package com.camunda.bpm.servicesdemo.loader;

import com.camunda.bpm.servicesdemo.entity.common.Approver;
import com.camunda.bpm.servicesdemo.repository.ApproverRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {

    private ApproverRepository repository;

    @Autowired
    public DataLoader(ApproverRepository repository) {
        this.repository = repository;
        loadData();
    }

    private void loadData() {
        InputStream is = DataLoader.class.getResourceAsStream("/data.json");

        if (is != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Approver> approverList = null;
            try {
                approverList = Arrays.asList(objectMapper.readValue(is, Approver[].class));
            } catch (IOException e) {
                e.printStackTrace();
            }
            repository.saveAll(approverList);
        }
    }

}