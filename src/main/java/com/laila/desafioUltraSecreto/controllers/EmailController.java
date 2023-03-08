package com.laila.desafioUltraSecreto.controllers;


import com.laila.desafioUltraSecreto.enums.StatusEmail;
import com.laila.desafioUltraSecreto.models.EmailModel;
import com.laila.desafioUltraSecreto.models.PersonModel;
import com.laila.desafioUltraSecreto.services.CheckPointService;
import com.laila.desafioUltraSecreto.services.EmailService;
import com.laila.desafioUltraSecreto.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/emails")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmailController {


    @Autowired
    EmailService emailService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private PersonService personService;

    //Envia a lista de entradas para o Prof. Xavier até a data solicitada
    @PostMapping
    public ResponseEntity<List<PersonModel>> emailAllEntries(){

        EmailModel emailModel = new EmailModel();

        try{
            //Envia o email de registro de todas as entradas para o professor Xavier
            emailModel.setEmailFrom("seguranca@mansaoe.com.br");
            emailModel.setEmailTo("profxavier@mansoae.com.br");
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setSubject("Registro de entradas na Mansão E até " + LocalDateTime.now());
            emailModel.setText("Listagem de entradas até o dia " + LocalDateTime.now() + ": " + personService.findAll());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailService.save(emailModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(personService.findAll());
        }

    }


}
