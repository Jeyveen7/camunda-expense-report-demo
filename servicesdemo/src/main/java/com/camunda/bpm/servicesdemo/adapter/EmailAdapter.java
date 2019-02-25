package com.camunda.bpm.servicesdemo.adapter;

import com.camunda.bpm.servicesdemo.entity.notification.Email;
import com.camunda.bpm.servicesdemo.util.PropertyNames;
import com.fasterxml.jackson.databind.PropertyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EmailAdapter {

    @Autowired
    private JavaMailSender emailSender;
    @Value(PropertyNames.SPRING_MAIL_USERNAME)
    private String SPRING_MAIL_USERNAME;

    public List<Email> sendEmailBatch(List<Email> emails) {
        List<Email> sentEmails = new ArrayList<>();
        emails.forEach(email -> {
            if(sendEmail(email))
                email.setSent(true);
                sentEmails.add(email);
        });
        return sentEmails;
    }

    public boolean sendEmail(Email email){
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setTo(email.getTo());
            helper.setText(email.getContent(), true);
            helper.setSubject(email.getSubject());
            helper.setFrom(SPRING_MAIL_USERNAME);
            emailSender.send(message);
            return true;
        }
        catch(MessagingException e){
            return false;
        }
    }
}
