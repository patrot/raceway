package com.galvanize.sombrero.speedway.services;


import com.galvanize.sombrero.speedway.model.RaceCar;
import com.galvanize.sombrero.speedway.repositories.RaceCarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RaceCarService_UnitTests {

    private RaceCarRepository mockRaceCarRepository;

    private RaceCar condor;

    @BeforeEach
    public void setup() {

        mockRaceCarRepository = mock(RaceCarRepository.class);

        condor = RaceCar.builder()
                .nickName("The Condor")
                .model("Corvette")
                .year(2019L)
                .owner(27L)
                .status("AVAILABLE")
                .topSpeed(189L)
                .build();
    }

    @Test
    public void test_getAllCars() {
        List<RaceCar> expectedRaceCarList = new ArrayList<>();
        RaceCar raceCar1 = RaceCar.builder()
                .nickName("The Condor")
                .build();
        RaceCar raceCar2 = RaceCar.builder()
                .nickName("Blue Fire")
                .build();
        expectedRaceCarList.add(raceCar1);
        expectedRaceCarList.add(raceCar2);
        when(mockRaceCarRepository.findAll()).thenReturn(expectedRaceCarList);
        RaceCarService raceCarService = new RaceCarService(mockRaceCarRepository);
        List<RaceCar> actualRaceCarList = raceCarService.getAllCars();
        assertEquals("The Condor", actualRaceCarList.get(0).getNickName());
        assertEquals("Blue Fire", actualRaceCarList.get(1).getNickName());
        verify(mockRaceCarRepository).findAll();
    }

    @Test
    public void test_addRaceCar() {
        when(mockRaceCarRepository.save(any())).thenReturn(condor);

        RaceCarService raceCarService = new RaceCarService(mockRaceCarRepository);

        RaceCar actual = raceCarService.addRaceCar(condor);

        assertEquals(condor, actual);

        verify(mockRaceCarRepository).save(condor);
    }


}
