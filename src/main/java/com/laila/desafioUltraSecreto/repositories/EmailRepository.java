package com.laila.desafioUltraSecreto.repositories;

import com.laila.desafioUltraSecreto.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {


}
