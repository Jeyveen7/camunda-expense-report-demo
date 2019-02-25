package com.camunda.bpm.servicesdemo.resource;

import com.camunda.bpm.servicesdemo.adapter.EmailAdapter;
import com.camunda.bpm.servicesdemo.entity.notification.Email;
import com.camunda.bpm.servicesdemo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailResource {

    @Autowired
    private EmailRepository repository;
    @Autowired
    private EmailAdapter adapter;

    @PostMapping("notification/emails")
    public Email merge(@RequestBody  Email email) {
        email.setSent(adapter.sendEmail(email));
        return repository.save(email);
    }

}
