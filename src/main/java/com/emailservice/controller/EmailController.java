package com.emailservice.controller;

import com.emailservice.domain.EmailDetails;
import com.emailservice.message.Messages;
import com.emailservice.service.MailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final MailService mailService;


    public EmailController(MailService mailService) {
        this.mailService = mailService;
    }


    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@Valid  @RequestBody EmailDetails emailDetails){

        mailService.sendSimpleMail(emailDetails);

        return new ResponseEntity<>(Messages.EMAIL_SEND_SUCCESSFUL, HttpStatus.OK);
    }


    @PostMapping("/attachment")
    public ResponseEntity<String> sendMailWithAttachment(@Valid @RequestBody EmailDetails emailDetails){
        mailService.sendMailWithAttachment(emailDetails);

        return new ResponseEntity<>(Messages.EMAIL_SEND_SUCCESSFUL,HttpStatus.OK);
    }


}
