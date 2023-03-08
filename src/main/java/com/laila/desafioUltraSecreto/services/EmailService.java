package com.laila.desafioUltraSecreto.services;

import com.laila.desafioUltraSecreto.models.EmailModel;
import com.laila.desafioUltraSecreto.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public EmailModel save(EmailModel emailModel) {
        return emailRepository.save(emailModel);
    }
}
