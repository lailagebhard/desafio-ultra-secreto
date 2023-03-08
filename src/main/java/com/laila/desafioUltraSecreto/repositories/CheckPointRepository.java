package com.laila.desafioUltraSecreto.repositories;

import com.laila.desafioUltraSecreto.models.CheckPointModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CheckPointRepository extends JpaRepository<CheckPointModel, UUID> {

}
