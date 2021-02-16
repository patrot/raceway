package com.galvanize.sombrero.speedway.repositories;

import com.galvanize.sombrero.speedway.model.RaceCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceCarRepository extends JpaRepository<RaceCar,Long> {
}
