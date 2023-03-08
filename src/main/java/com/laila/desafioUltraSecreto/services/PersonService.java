package com.laila.desafioUltraSecreto.services;

import com.laila.desafioUltraSecreto.models.PersonModel;
import com.laila.desafioUltraSecreto.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public PersonModel save(PersonModel personModel) {
        return personRepository.save(personModel);
    }

    public List<PersonModel> findAll() {
        return personRepository.findAll();
    }

    public List<PersonModel> findByLevel(String level) {
        return personRepository.findByLevel(level);
    }

    public Optional<PersonModel> findById(UUID id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void delete(PersonModel personModel) {
        personRepository.delete(personModel);
    }

}
