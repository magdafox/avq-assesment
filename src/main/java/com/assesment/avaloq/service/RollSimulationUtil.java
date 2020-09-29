package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.Simulation;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Helper class for {@link Roll} simulations.
 */
@UtilityClass
public class RollSimulationUtil {

    private static final int HUNDRED_PERCENT = 100;

    /**
     * Count relative distribution.
     * <p>
     * E.g. in case <b>300</b> all rolls, if the sum <b>3</b> was rolled <b>4</b> times, that would be <b>1.33%.</b>
     * </p>
     *
     * @param counter       count of rolls on given total sum
     * @param numberOfRolls total number of rolls for a combination
     * @return relative distribution for given parameters
     */
    public BigDecimal countRelativeDistribution(long counter, long numberOfRolls) {
        double result = (HUNDRED_PERCENT * counter) / (double) numberOfRolls;
        return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_DOWN);
    }

    /**
     * Count rolls grouped by the same total amounts.
     * <p>
     * Return:
     * <i>totalAmount</i> as a key
     * <i>count of rolls</i> as a value
     * </p>
     *
     * @param rolls list of rolls
     * @return key - totalAmount
     */
    public Map<Integer, Long> countByTotalSum(List<Roll> rolls) {
        return rolls.stream().collect(Collectors.groupingBy(Roll::getTotalSum, Collectors.counting()));
    }

    /**
     * Extract list of rolls from list of simulations.
     *
     * @param simulations list of simulations
     * @return list of all the rolls
     */
    public static List<Roll> extractRolls(List<Simulation> simulations) {
        return simulations.stream().map(Simulation::getRolls).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
