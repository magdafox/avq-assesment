package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.RollConfiguration;
import com.assesment.avaloq.domain.Simulation;
import com.assesment.avaloq.model.DistributionDetails;
import com.assesment.avaloq.model.TotalSumResult;
import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class to map dice distribution API responses.
 */
@UtilityClass
public class RollResponseMapper {

    public List<TotalSumResult> mapTotalSumDistribution(List<Roll> rolls) {
        Map<Integer, Long> totalCounts = countByTotalSum(rolls);
        return totalCounts.entrySet().stream().map(t -> mapTotalSum(t.getKey(), t.getValue()))
                .sorted(Comparator.comparing(TotalSumResult::getTotalSum))
                .collect(Collectors.toList());
    }

    private Map<Integer, Long> countByTotalSum(List<Roll> rollList) {
        return rollList.stream().collect(Collectors.groupingBy(Roll::getTotalSum, Collectors.counting()));
    }

    private TotalSumResult mapTotalSum(Integer total, Long amount) {
        TotalSumResult response = new TotalSumResult();
        response.setTotalSum(total);
        response.setCount(amount);
        return response;
    }

    public List<DistributionDetails> mapDistributionList(List<RollConfiguration> rollConfigurations) {
        return rollConfigurations.stream().map(RollResponseMapper::mapDistribution)
                .sorted(Comparator.comparing(DistributionDetails::getDiceNumber)
                        .thenComparing(DistributionDetails::getDiceSide))
                .collect(Collectors.toList());
    }

    private DistributionDetails mapDistribution(RollConfiguration rollConfiguration) {
        DistributionDetails details = new DistributionDetails();
        details.setDiceNumber(rollConfiguration.getDiceNumber());
        details.setDiceSide(rollConfiguration.getDiceSide());
        details.setSimulationsNumber(rollConfiguration.getSimulations().size());
        details.setRollsNumber(countRollsNumber(rollConfiguration.getSimulations()));
        return details;
    }

    private long countRollsNumber(List<Simulation> simulations) {
        return simulations.stream().map(Simulation::getRolls).mapToLong(List::size).sum();
    }
}
