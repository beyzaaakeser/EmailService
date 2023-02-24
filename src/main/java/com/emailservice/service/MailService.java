package com.emailservice.service;

import com.emailservice.domain.EmailDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface MailService {

    // To send a simple email
    ResponseEntity<String> sendSimpleMail(EmailDetails details);


    // To send an email with attachment
    ResponseEntity<String> sendMailWithAttachment(EmailDetails details);
}


