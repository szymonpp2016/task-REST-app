package com.crud.tasks.scheduler;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT_TASK = "Tasks: Once a day email";
    private static final String SUBJECT_WELCOME = "Welcome email";

    @Autowired
    private SimpleMailService simpleMailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 100000)
    public void sendInformationEmail() {

        simpleMailService.sendWelcomeMail(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT_WELCOME,
                "Nice too See You Again")
        );
    }

    // @Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 100000)
    public void sendInformationShedule() {
        long numberOfTaskInRepository = taskRepository.count();
        simpleMailService.sendShedulerMail(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT_TASK,
                String.format("Currently in database you got: %d %s", numberOfTaskInRepository, numberOfTaskInRepository == 1 ? "task" : "tasks")));
    }
}


