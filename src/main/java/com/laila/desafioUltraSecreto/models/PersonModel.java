package com.laila.desafioUltraSecreto.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_PERSON")
public class PersonModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 130)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, length = 130)
    private String species;
    @Column(nullable = false, length = 130)
    private String power;
    @Column(nullable = false, length = 130)
    private String level;
    @Column(nullable = false, unique = true, length = 130)
    private String email;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime entryTime;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime exitTime;

    @JsonIgnore
    @OneToMany(mappedBy = "personModel")
    @JsonManagedReference
    private List<CheckPointModel> checkPointModelList;

}
