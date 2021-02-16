package com.galvanize.sombrero.speedway.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.sombrero.speedway.model.RaceCar;
import com.galvanize.sombrero.speedway.repositories.RaceCarRepository;
import io.swagger.annotations.Api;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class RacecarController_IntTests {

    private RaceCar maserati;
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RaceCarRepository raceCarRepository;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();

        maserati = RaceCar.builder()
                .nickName("The Grey Ghost")
                .model("Maserati")
                .year(2019L)
                .owner(29L)
                .status("AVAILABLE")
                .topSpeed(191L)
                .build();
    }

    @Test
    public void getRaceCarsTest() throws Exception {
        mockMvc.perform(get("/api/v1/speedway/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].nickName").value("The Condor"))
                .andExpect(jsonPath("$[0].model").value("Corvette"))
                .andExpect(jsonPath("$[0].year").value(2019))
                .andExpect(jsonPath("$[0].owner").value(27))
                .andExpect(jsonPath("$[0].status").value("AVAILABLE"))
                .andExpect(jsonPath("$[0].topSpeed").value(189));
    }

    @Test
    public void addRaceCarTest() throws Exception {
        String raceCar = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(maserati);

        mockMvc.perform(post("/api/v1/speedway/racecars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(raceCar))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nickName").value("The Grey Ghost"))
                .andExpect(jsonPath("$.model").value("Maserati"))
                .andExpect(jsonPath("$.year").value(2019))
                .andExpect(jsonPath("$.owner").value(29))
                .andExpect(jsonPath("$.status").value("AVAILABLE"))
                .andExpect(jsonPath("$.topSpeed").value(191));

    }
}
