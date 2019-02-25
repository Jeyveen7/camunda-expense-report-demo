package com.camunda.bpm.servicesdemo.repository;

import com.camunda.bpm.servicesdemo.entity.common.Approver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproverRepository extends CrudRepository<Approver, Long> {

}