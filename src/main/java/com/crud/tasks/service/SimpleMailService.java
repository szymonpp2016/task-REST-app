package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleMailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void sendWelcomeMail(final Mail mail) {
        LOGGER.info("Starting Welcome email preparation...");
        try {

            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Welcome Email Thymeleaf has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process Welcome email sending: ", e.getMessage(), e);
        }
    }

    public void sendShedulerMail(final Mail mail) {
        LOGGER.info("Starting Scheduler email preparation...");
        try {
            javaMailSender.send(createSheduleMimeMessage(mail));
            LOGGER.info("Scheduler Email Thymeleaf has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process Scheduler email sending: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        //    if (mail.getToCc()!=null) {
        //       mailMessage.setCc(mail.getToCc());
        //    }
        return mailMessage;
    }



    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }


    private MimeMessagePreparator createSheduleMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mailCreatorService.buildSchedulerCardEmail(mail.getMessage()), true);
        };
    }

   /* public void sendWelcomeMail(final Mail mail) {  //klasa sen przed Thymself
        LOGGER.info("Starting email preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.sendWelcomeMail(mailMessage);
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    } */
}