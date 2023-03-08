package com.laila.desafioUltraSecreto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Data
public class CheckPointDto implements Serializable {

    @NotBlank
    private String name;

    @NotNull
    private UUID personId;

}
