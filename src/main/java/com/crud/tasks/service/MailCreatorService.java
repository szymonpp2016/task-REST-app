package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.scheduler.EmailScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private EmailScheduler emailScheduler;

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.phone}")
    private String companyPhoneNumber;

    @Value("${info.company.email}")
    private String companyEmail;


    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/trello_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminMail()); // adminConfig.getAdminName());
        context.setVariable("goodbye", "Thank you for choosing us!");
        context.setVariable("company_name", companyName);
        context.setVariable("company_phone", companyPhoneNumber);
        context.setVariable("company_email" , companyEmail);

        return  templateEngine.process("mail/created-trello-card-mail", context);

    }
}
