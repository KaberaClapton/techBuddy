package com.amalitech.techBuddy.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender mailSender;

    public void sendEmail(String destEmail,
                          String subject,
                          String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ckabera6@gmail.com");
        message.setTo(destEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        System.out.println("Email sent successfully!");
    }
}
