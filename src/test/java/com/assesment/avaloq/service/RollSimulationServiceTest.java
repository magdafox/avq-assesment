package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.RollConfiguration;
import com.assesment.avaloq.repository.RollConfigurationRepository;
import com.assesment.avaloq.repository.SimulationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RollSimulationServiceTest {

    @Mock
    private RollConfigurationRepository rollConfigurationRepository;

    @Mock
    private SimulationRepository simulationRepository;

    @InjectMocks
    private RollSimulationService rollSimulationService;

    @ParameterizedTest
    @MethodSource("numberOfRolls")
    void executeSimulationSuccess(int numberOfRolls) {
        RollConfiguration configuration = new RollConfiguration();
        configuration.setDiceNumber(3);
        configuration.setDiceSide(6);

        List<Roll> rolls = rollSimulationService.executeSimulation(numberOfRolls, configuration);

        assertNotNull(rolls);
        assertEquals(numberOfRolls, rolls.size());
    }

    private static Stream<Arguments> numberOfRolls() {
        return Stream.of(Arguments.of(0), Arguments.of(10));
    }

    @Test
    void getRollConfigurationList_createNewConfiguration() {
        int diceNumber = 6;
        int diceSide = 6;

        when(rollConfigurationRepository.save(any(RollConfiguration.class))).then(returnsFirstArg());
        when(rollConfigurationRepository.findByDiceNumberAndDiceSide(eq(diceNumber), eq(diceSide)))
                .thenReturn(Optional.empty());

        RollConfiguration rollConfiguration = rollSimulationService.getRollConfiguration(diceNumber, diceSide);

        assertNotNull(rollConfiguration);
        assertEquals(diceNumber, rollConfiguration.getDiceNumber());
        assertEquals(diceSide, rollConfiguration.getDiceSide());

        verify(rollConfigurationRepository, times(1)).save(any());
    }

    @Test
    void getRollConfigurationList_getOldConfiguration() {
        int diceNumber = 6;
        int diceSide = 6;

        when(rollConfigurationRepository.findByDiceNumberAndDiceSide(eq(diceNumber), eq(diceSide)))
                .thenReturn(Optional.of(new RollConfiguration()));

        RollConfiguration rollConfiguration = rollSimulationService.getRollConfiguration(diceNumber, diceSide);

        assertNotNull(rollConfiguration);

        verify(rollConfigurationRepository, times(0)).save(any());
    }
}
