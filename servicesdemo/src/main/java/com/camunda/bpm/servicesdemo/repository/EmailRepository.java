package com.camunda.bpm.servicesdemo.repository;

import com.camunda.bpm.servicesdemo.entity.notification.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends CrudRepository<Email, Long> {

    List<Email> findBySent(Boolean sent);
}
