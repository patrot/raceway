package com.galvanize.sombrero.speedway.controllers;

import com.galvanize.sombrero.speedway.model.RaceCar;
import com.galvanize.sombrero.speedway.services.RaceCarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/speedway/racecars")
@Api(tags = {"racecars"})
public class RacecarController {

    @Autowired
    RaceCarService raceCarService;

    @GetMapping
    public List<RaceCar> getAllCars() {
      return raceCarService.getAllCars();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RaceCar addRaceCare(@RequestBody RaceCar raceCar) {
        return raceCarService.addRaceCar(raceCar);
    }
}
