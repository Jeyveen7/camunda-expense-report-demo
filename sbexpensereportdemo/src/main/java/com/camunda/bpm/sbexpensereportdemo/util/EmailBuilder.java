package com.camunda.bpm.sbexpensereportdemo.util;

import com.camunda.bpm.model.common.Participant;
import com.camunda.bpm.model.notification.Email;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.h2.mvstore.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class EmailBuilder<T> {

    private static final Logger LOGGER = Logger.getLogger(EmailBuilder.class.getName());
    @Autowired
    private Configuration freemarkerConfig;
    @Value(PropertyNames.TEMPLATES_EMAIL)
    private String TEMPLATES_EMAIL;
    @Value(PropertyNames.TEMPLATES_SUBJECT)
    private String TEMPLATES_SUBJECT;
    @Value(PropertyNames.TEMPLATES_TYPEVARIABLE)
    private String TEMPLATES_TYPEVARIABLE;

    public Email buildEmail(String key, T obj, EmailType emailType, Participant participant) throws MessagingException, IOException, TemplateException {
        String templateName = TEMPLATES_EMAIL.replace(TEMPLATES_TYPEVARIABLE, emailType.getTypeName());
        String subjectTempName =  TEMPLATES_SUBJECT.replace(TEMPLATES_TYPEVARIABLE, emailType.getTypeName());

        Template emailTemplate = freemarkerConfig.getTemplate(templateName);
        Template subjectTemplate = freemarkerConfig.getTemplate(subjectTempName);

        Map model = new HashMap<String,String>();
        model.put(key, obj);

        Email email = new Email();
        email.setTo(participant.getEmail());
        email.setSubject(FreeMarkerTemplateUtils.processTemplateIntoString(subjectTemplate, model));
        email.setContent(FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, model));

        return email;
    }
}
