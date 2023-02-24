package com.emailservice.service;

import com.emailservice.domain.EmailDetails;
import com.emailservice.message.Messages;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailServiceImpl implements MailService{

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public ResponseEntity<String> sendSimpleMail(EmailDetails details) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setSubject(details.getSubject());
            mailMessage.setText(details.getMsgBody());

            javaMailSender.send(mailMessage);

            return ResponseEntity.ok(Messages.EMAIL_SEND_SUCCESSFUL);
        } catch (MailException e) {
            return ResponseEntity.ok(Messages.EMAIL_FAILED_TO_SEND);
        }

    }

    @Override
    public ResponseEntity<String> sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setSubject(details.getSubject());
            mimeMessageHelper.setText(details.getMsgBody());
            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(),file);
            javaMailSender.send(mimeMessage);
            return ResponseEntity.ok(Messages.EMAIL_SEND_SUCCESSFUL);
        } catch (MessagingException e) {
            return ResponseEntity.ok(Messages.EMAIL_FAILED_TO_SEND);
        }
    }


}
