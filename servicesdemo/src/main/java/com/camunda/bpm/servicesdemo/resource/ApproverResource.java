package com.camunda.bpm.servicesdemo.resource;

import com.camunda.bpm.servicesdemo.entity.common.Approver;
import com.camunda.bpm.servicesdemo.repository.ApproverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;

@RestController
public class ApproverResource {

    @Autowired
    private ApproverRepository repository;

    @GetMapping("common/approvers")
    public List<Approver> findAll() {
        return IterableUtils.toList(repository.findAll());
    }
}
