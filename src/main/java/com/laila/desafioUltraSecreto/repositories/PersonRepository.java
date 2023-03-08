package com.laila.desafioUltraSecreto.repositories;

import com.laila.desafioUltraSecreto.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository <PersonModel, UUID> {

    List<PersonModel> findByLevel(String level);

    List<PersonModel> findByName(String name);
}
