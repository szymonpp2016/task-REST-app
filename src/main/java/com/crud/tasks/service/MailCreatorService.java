package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.scheduler.EmailScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;

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

    private Context context = new Context();

    public String buildTrelloCardEmail(String message) {
        ArrayList<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Provides connection with Trello account");
        functionality.add("Application allows sending task to Trello");

        setContext();
        context.setVariable("message", message);
        context.setVariable("goodbye", "Thank you for choosing us!");
        context.setVariable("application_functionality", functionality);

        return  templateEngine.process("mail/created-trello-card-mail", context);
    }


    public String buildSchedulerCardEmail(String message) {
        ArrayList<String> schedulerFunctionality = new ArrayList<>();
        schedulerFunctionality.add("Sending update every 10 hours");
        schedulerFunctionality.add("Provides number of active tasks");

        setContext();
        context.setVariable("message", message);
        context.setVariable("goodbye", "See you in 10 hours");
        context.setVariable("application_functionality", schedulerFunctionality);

        return templateEngine.process("mail/scheduled-one-day-email", setContext());
    }


    private Context setContext() {
        context.setVariable("task_url", "http://localhost:8888/trello_frontend");
        context.setVariable("admin_name", adminConfig.getAdminMail()); // adminConfig.getAdminName());
        context.setVariable("company_name", companyName);
        context.setVariable("company_phone", companyPhoneNumber);
        context.setVariable("company_email" , companyEmail);
        context.setVariable("show_button",false );
        context.setVariable("is_friend",false );
        context.setVariable("admin_config",adminConfig );
        context.setVariable("button", "Check Crud App");
        context.setVariable("admin_name", adminConfig.getAdminMail());
        return context;
    }
}