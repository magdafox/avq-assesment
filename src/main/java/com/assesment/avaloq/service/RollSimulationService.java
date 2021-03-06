package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.RollConfiguration;
import com.assesment.avaloq.domain.Simulation;
import com.assesment.avaloq.repository.RollConfigurationRepository;
import com.assesment.avaloq.repository.SimulationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Service for executing actions on {@link Roll} object.
 */
@Slf4j
@Service
public class RollSimulationService {

    private static final Random RANDOM = new Random();

    @Autowired
    private RollConfigurationRepository rollConfigurationRepository;

    @Autowired
    private SimulationRepository simulationRepository;

    public Simulation executeSimulation(Integer numberOfRolls, RollConfiguration rollConfiguration) {
        List<Roll> rolls = new ArrayList<>();
        for (int i = 0; i < numberOfRolls; i++) {
            Roll roll = executeRoll(rollConfiguration);
            rolls.add(roll);
        }

        return saveSimulation(rollConfiguration, rolls);
    }

    private Roll executeRoll(RollConfiguration rollConfiguration) {
        int totalSum = 0;
        for (int i = 0; i < rollConfiguration.getDiceNumber(); i++) {
            int rolledNumber = roll(rollConfiguration.getDiceSide());
            log.debug("Rolled number: {}", rolledNumber);

            totalSum += rolledNumber;
        }
        return createRoll(totalSum);
    }

    private int roll(int max) {
        return RANDOM.nextInt((max - 1) + 1) + 1;
    }

    private Roll createRoll(int totalSum) {
        Roll roll = new Roll();
        roll.setTotalSum(totalSum);
        return roll;
    }

    private Simulation saveSimulation(RollConfiguration rollConfiguration, List<Roll> rollList) {
        Simulation simulation = new Simulation();
        simulation.setRolls(rollList);
        simulation.setConfiguration(rollConfiguration);
        rollList.forEach(roll -> roll.setSimulation(simulation));

        return simulationRepository.save(simulation);
    }

    public RollConfiguration getRollConfiguration(Integer diceNumber, Integer diceSide) {
        Optional<RollConfiguration> rollConfigurationOpt = rollConfigurationRepository
                .findByDiceNumberAndDiceSide(diceNumber, diceSide);
        if (rollConfigurationOpt.isEmpty()) {
            return createRollConfiguration(diceNumber, diceSide);
        }
        return rollConfigurationOpt.get();
    }

    private RollConfiguration createRollConfiguration(Integer diceNumber, Integer diceSide) {
        RollConfiguration rollConfiguration = new RollConfiguration();
        rollConfiguration.setDiceNumber(diceNumber);
        rollConfiguration.setDiceSide(diceSide);
        return rollConfigurationRepository.save(rollConfiguration);
    }

    public List<RollConfiguration> getAllRollConfigurations() {
        return rollConfigurationRepository.findAll();
    }
}
