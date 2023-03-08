package com.laila.desafioUltraSecreto.controllers;

import com.laila.desafioUltraSecreto.dtos.PersonDto;
import com.laila.desafioUltraSecreto.models.PersonModel;
import com.laila.desafioUltraSecreto.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PersonController {

    @Autowired
    private PersonService personService;

    //Envia o cadastro e horário de entrada da pessoa
    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody @Valid PersonDto personDto){

        PersonModel personModel = new PersonModel();
        BeanUtils.copyProperties(personDto, personModel);
        personModel.setEntryTime(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personModel));
    }


    //Mostra a lista com todas as pessoas cadastradas
    @GetMapping
    public ResponseEntity<List<PersonModel>> getAllPersons(){
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    //Filtra as pessoas por nível de poder
    @GetMapping("/level")
    public ResponseEntity<List<PersonModel>> getPersonByLevel (@RequestParam String level){

        return new ResponseEntity<List<PersonModel>>(personService.findByLevel(level), HttpStatus.OK);

    }

    //Atualiza data de saída da mansão
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable(value = "id")UUID id){
        Optional<PersonModel> personModelOptional = personService.findById(id);

        if(personModelOptional.isPresent()){
            PersonModel personModel =  personModelOptional.get();
            personModel.setExitTime(LocalDateTime.now(ZoneId.of("UTC")));

            return ResponseEntity.status(HttpStatus.OK).body(personService.save(personModel));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found.");
        }
    }

    //Exclui o resgistro de uma pessoa pelo id
    @DeleteMapping("/{id}")
        public ResponseEntity<Object> deletePersonById (@PathVariable(value = "id") UUID id){
            Optional<PersonModel> personModelOptional = personService.findById(id);

            if (!personModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não encontrado.");
            } else {
                personService.delete(personModelOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso.");
            }

        }


    }




