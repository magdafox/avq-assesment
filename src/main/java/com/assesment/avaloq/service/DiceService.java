package com.assesment.avaloq.service;

import com.assesment.avaloq.model.DiceSimulationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class DiceService {

    public ResponseEntity<DiceSimulationResponse> executeSimulation(Integer numberOfDice, Integer sidesOfDice,
                                                                    Integer numberOfRolls) {
        DiceSimulationResponse response = new DiceSimulationResponse();
        return ResponseEntity.ok(response);
    }

    private int roll(int min) {
        Random random = new Random();
        return random.nextInt((Integer.MAX_VALUE - min) + 1) + min;
    }
}
