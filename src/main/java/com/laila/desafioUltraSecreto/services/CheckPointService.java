package com.laila.desafioUltraSecreto.services;

import com.laila.desafioUltraSecreto.models.CheckPointModel;
import com.laila.desafioUltraSecreto.repositories.CheckPointRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckPointService {

    @Autowired
    private CheckPointRepository checkPointRepository;

    @Transactional
    public CheckPointModel save(CheckPointModel checkPointModel){
        return checkPointRepository.save(checkPointModel);
    }

    public List<CheckPointModel> findAll(){
        return checkPointRepository.findAll();
    }



}
