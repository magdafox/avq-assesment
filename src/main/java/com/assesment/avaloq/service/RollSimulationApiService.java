package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.RollConfiguration;
import com.assesment.avaloq.model.RollSimulationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class RollSimulationApiService {

    @Autowired
    private RollSimulationService rollSimulationService;

    public ResponseEntity<RollSimulationResponse> executeSimulation(Integer diceNumber, Integer diceSide,
                                                                    Integer numberOfRolls) {
        log.info("Roll {} pieces of {}-sided dice {} times", diceNumber, diceSide, numberOfRolls);
        RollConfiguration rollConfiguration = rollSimulationService.getRollConfiguration(diceNumber, diceSide);

        List<Roll> rollList = rollSimulationService.executeSimulation(numberOfRolls, rollConfiguration);

        RollSimulationResponse response = new RollSimulationResponse();
        response.setDiceDistribution(RollResponseMapper.mapDistributionList(rollList));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Void> getSimulatedDistribution() {

        return null;
    }
}
