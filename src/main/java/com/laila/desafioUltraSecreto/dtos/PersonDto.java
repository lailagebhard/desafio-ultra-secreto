package com.laila.desafioUltraSecreto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PersonDto {

    @NotBlank
    private String name;
    @NotNull
    private int age;
    @NotBlank
    private String species;
    @NotBlank
    private String power;
    @NotBlank
    private String level;
    @NotBlank
    private String email;

}
