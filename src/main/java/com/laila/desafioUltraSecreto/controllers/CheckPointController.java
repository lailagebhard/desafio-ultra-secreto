package com.laila.desafioUltraSecreto.controllers;

import com.laila.desafioUltraSecreto.dtos.CheckPointDto;
import com.laila.desafioUltraSecreto.enums.StatusEmail;
import com.laila.desafioUltraSecreto.models.CheckPointModel;
import com.laila.desafioUltraSecreto.models.EmailModel;
import com.laila.desafioUltraSecreto.models.PersonModel;
import com.laila.desafioUltraSecreto.services.CheckPointService;
import com.laila.desafioUltraSecreto.services.EmailService;
import com.laila.desafioUltraSecreto.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/check-point")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CheckPointController {

    @Autowired
    private CheckPointService checkPointService;

    @Autowired
    PersonService personService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping
    public ResponseEntity<Object> sendCheckPointEmail(@RequestBody @Valid CheckPointDto checkPointDto){

        CheckPointModel checkPointModel = new CheckPointModel();
        EmailModel emailModel1 = new EmailModel();
        EmailModel emailModel2 = new EmailModel();
        EmailModel emailModel3 = new EmailModel();

        Optional<PersonModel> personModelOptional = personService.findById(checkPointDto.getPersonId());

        //Email para visitantes "Homo sapiens" e "Homo peregrino"
        if (personModelOptional.get().getSpecies() == "Homo sapiens" || personModelOptional.get().getSpecies() == "Homo peregrino"){
            try{
                //Envia email de registro de entrada para o visitante
                BeanUtils.copyProperties(checkPointDto, checkPointModel);
                checkPointModel.setPersonModel(personModelOptional.get());

                emailModel1.setEmailFrom("seguranca@mansaoe.com.br");
                emailModel1.setEmailTo(personModelOptional.get().getEmail());
                emailModel1.setSendDateEmail(personModelOptional.get().getEntryTime());
                emailModel1.setSubject("Novo registro de entrada na Mansão E");
                emailModel1.setText("Prezado(a), " + personModelOptional.get().getName() + "Informamos que sua entrada na “Mansão E Para Jovens Superdotados”" +
                        " foi registrada na data de " + personModelOptional.get().getEntryTime());

                SimpleMailMessage message1 = new SimpleMailMessage();
                message1.setFrom(emailModel1.getEmailFrom());
                message1.setTo(emailModel1.getEmailTo());
                message1.setSubject(emailModel1.getSubject());
                message1.setText(emailModel1.getText());
                emailSender.send(message1);

                emailModel1.setStatusEmail(StatusEmail.SENT);

                //Envia o email de registro de entrada para o professor Xavier
                emailModel2.setEmailFrom("seguranca@mansaoe.com.br");
                emailModel2.setEmailTo("profxavier@mansoae.com.br");
                emailModel2.setSendDateEmail(personModelOptional.get().getEntryTime());
                emailModel2.setSubject("Novo registro de entrada na Mansão E");
                emailModel2.setText("Prezado(a), " + personModelOptional.get().getName() + "Informamos que sua entrada na “Mansão E Para Jovens Superdotados”" +
                        " foi registrada na data de " + personModelOptional.get().getEntryTime());

                SimpleMailMessage message2 = new SimpleMailMessage();
                message2.setFrom(emailModel2.getEmailFrom());
                message2.setTo(emailModel2.getEmailTo());
                message2.setSubject(emailModel2.getSubject());
                message2.setText(emailModel2.getText());
                emailSender.send(message2);

                emailModel2.setStatusEmail(StatusEmail.SENT);

            } catch (MailException e) {
                emailModel1.setStatusEmail(StatusEmail.ERROR);
            } finally {
                emailService.save(emailModel1);
                return ResponseEntity.status(HttpStatus.CREATED).body(checkPointService.save(checkPointModel));
            }

            //Email para "Homo superior"
        } else if (personModelOptional.get().getSpecies() == "Homo superior"){
            try{
                //Envia email de registro de entrada para o mutante
                BeanUtils.copyProperties(checkPointDto, checkPointModel);
                checkPointModel.setPersonModel(personModelOptional.get());

                emailModel3.setEmailFrom("seguranca@mansaoe.com.br");
                emailModel3.setEmailTo(personModelOptional.get().getEmail());
                emailModel3.setSendDateEmail(personModelOptional.get().getEntryTime());
                emailModel3.setSubject("Seja bem-vindo(a)!");
                emailModel3.setText("Prezado(a), " + personModelOptional.get().getName() + "Seja bem-vindo(a) à “Mansão E Para Jovens Superdotados”!" +
                        " Sua última entrada foi registrada na data de " + personModelOptional.get().getEntryTime());

                SimpleMailMessage message3 = new SimpleMailMessage();
                message3.setFrom(emailModel3.getEmailFrom());
                message3.setTo(emailModel3.getEmailTo());
                message3.setSubject(emailModel3.getSubject());
                message3.setText(emailModel3.getText());
                emailSender.send(message3);

                emailModel3.setStatusEmail(StatusEmail.SENT);

            } catch (MailException e) {
                emailModel3.setStatusEmail(StatusEmail.ERROR);
            } finally {
                emailService.save(emailModel1);
                return ResponseEntity.status(HttpStatus.CREATED).body(checkPointService.save(checkPointModel));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não encontrada nos registros de entrada.");
        }

    }


}
