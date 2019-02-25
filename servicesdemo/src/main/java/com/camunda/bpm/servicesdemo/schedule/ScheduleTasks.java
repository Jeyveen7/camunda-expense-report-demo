package com.camunda.bpm.servicesdemo.schedule;

import com.camunda.bpm.servicesdemo.adapter.EmailAdapter;
import com.camunda.bpm.servicesdemo.entity.notification.Email;
import com.camunda.bpm.servicesdemo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleTasks {

    @Autowired
    private EmailRepository repository;
    @Autowired
    private EmailAdapter emailAdapter;

    @Scheduled(fixedDelay = 300000)
    public void sendEmails(){
        List<Email> emailsToSend = repository.findBySent(false);
        List<Email> emailsSent = emailAdapter.sendEmailBatch(emailsToSend);
        repository.saveAll(emailsSent);
    }
}
