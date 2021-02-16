package com.galvanize.sombrero.speedway.services;

import com.galvanize.sombrero.speedway.model.RaceCar;
import com.galvanize.sombrero.speedway.repositories.RaceCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceCarService {

    private RaceCarRepository raceCarRepository;

    public RaceCarService(RaceCarRepository raceCarRepository) {
        this.raceCarRepository = raceCarRepository;
    }

    public List<RaceCar> getAllCars() {
        return raceCarRepository.findAll();
    }

    public RaceCar addRaceCar(RaceCar raceCar) {
        return raceCarRepository.save(raceCar);
    }
}
