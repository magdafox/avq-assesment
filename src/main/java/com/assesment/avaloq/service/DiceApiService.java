package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.RollConfiguration;
import com.assesment.avaloq.model.DiceSimulationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class DiceApiService {

    @Autowired
    private DiceService diceService;

    public ResponseEntity<DiceSimulationResponse> executeSimulation(Integer diceNumber, Integer diceSide,
                                                                    Integer numberOfRolls) {
        log.info("Roll {} pieces of {}-sided dice {} times", diceNumber, diceSide, numberOfRolls);
        RollConfiguration rollConfiguration = diceService.getRollConfiguration(diceNumber, diceSide);

        List<Roll> rollList = diceService.executeSimulation(numberOfRolls, rollConfiguration);

        DiceSimulationResponse response = new DiceSimulationResponse();
        response.setDiceDistribution(DiceResponseMapper.mapDistributionList(rollList));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Void> getDistributions() {
        
        return null;
    }
}
