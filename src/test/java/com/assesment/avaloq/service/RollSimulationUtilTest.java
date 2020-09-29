package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.Simulation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RollSimulationUtilTest {

    @ParameterizedTest
    @MethodSource("relativeDistributionParams")
    void countRelativeDistributionTest(long counter, long numberOfRolls, double expectedResult) {
        BigDecimal result = RollSimulationUtil.countRelativeDistribution(counter, numberOfRolls);

        assertNotNull(result);
        assertEquals(expectedResult, result.doubleValue());
    }

    private static Stream<Arguments> relativeDistributionParams() {
        return Stream.of(
                Arguments.of(4, 300, 1.33),
                Arguments.of(10, 10, 100),
                Arguments.of(0, 10, 0));
    }

    @Test
    void countByTotalSumTest() {
        Roll roll1 = createRoll(1);
        Roll roll2 = createRoll(1);
        Roll roll3 = createRoll(2);

        Map<Integer, Long> totalCounts = RollSimulationUtil.countByTotalSum(List.of(roll1, roll2, roll3));

        assertNotNull(totalCounts);
        assertEquals(2, totalCounts.get(1));
        assertEquals(1, totalCounts.get(2));
    }

    @Test
    void extractRollsTest_noRolls() {
        Simulation simulation = createSimulation(0);
        assertEquals(0, RollSimulationUtil.extractRolls(List.of(simulation)).size());
    }

    @Test
    void extractRollsTest_multipleRolls() {
        Simulation simulation1 = createSimulation(2);
        Simulation simulation2 = createSimulation(3);
        Simulation simulation3 = createSimulation(4);
        assertEquals(9, RollSimulationUtil.extractRolls(List.of(simulation1, simulation2, simulation3)).size());
    }

    @NotNull
    private Simulation createSimulation(int numberOfRolls) {
        Simulation simulation = new Simulation();
        List<Roll> rolls = new ArrayList<>();
        for (int i = 0; i < numberOfRolls; i++) {
            rolls.add(createRoll(1));
        }
        simulation.setRolls(rolls);
        return simulation;
    }

    @NotNull
    private Roll createRoll(int totalSum) {
        Roll roll = new Roll();
        roll.setTotalSum(totalSum);
        return roll;
    }
}
