package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.RollConfiguration;
import com.assesment.avaloq.model.DiceCombinationDetails;
import com.assesment.avaloq.model.RelativeDistributionDetails;
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

    public List<TotalSumResult> mapTotalSumList(List<Roll> rolls) {
        Map<Integer, Long> totalCounts = RollSimulationUtil.countByTotalSum(rolls);
        return totalCounts.entrySet().stream().map(t -> mapTotalSum(t.getKey(), t.getValue()))
                .sorted(Comparator.comparing(TotalSumResult::getTotalSum))
                .collect(Collectors.toList());
    }

    private TotalSumResult mapTotalSum(Integer total, Long amount) {
        TotalSumResult response = new TotalSumResult();
        response.setTotalSum(total);
        response.setCount(amount);
        return response;
    }

    public List<DiceCombinationDetails> mapAllDiceCombinations(List<RollConfiguration> rollConfigurations) {
        return rollConfigurations.stream().map(RollResponseMapper::mapDiceCombination)
                .sorted(Comparator.comparing(DiceCombinationDetails::getDiceNumber)
                        .thenComparing(DiceCombinationDetails::getDiceSide))
                .collect(Collectors.toList());
    }

    private DiceCombinationDetails mapDiceCombination(RollConfiguration rollConfiguration) {
        DiceCombinationDetails details = new DiceCombinationDetails();
        details.setDiceNumber(rollConfiguration.getDiceNumber());
        details.setDiceSide(rollConfiguration.getDiceSide());
        details.setSimulationsNumber(rollConfiguration.getSimulations().size());

        List<Roll> rolls = RollSimulationUtil.extractRolls(rollConfiguration.getSimulations());
        details.setRollsNumber(rolls.size());
        details.setDistribution(mapDistribution(rolls));
        return details;
    }

    private static List<RelativeDistributionDetails> mapDistribution(List<Roll> rolls) {
        Map<Integer, Long> totalCounts = RollSimulationUtil.countByTotalSum(rolls);
        return totalCounts.entrySet().stream().map(t -> mapRelativeDistribution(t.getKey(), t.getValue(), rolls.size()))
                .sorted(Comparator.comparing(RelativeDistributionDetails::getTotalSum))
                .collect(Collectors.toList());
    }

    private RelativeDistributionDetails mapRelativeDistribution(Integer total, Long amount, long size) {
        RelativeDistributionDetails distribution = new RelativeDistributionDetails();
        distribution.setTotalSum(total);
        distribution.setRelativeDistribution(RollSimulationUtil.countRelativeDistribution(amount, size));
        return distribution;
    }
}
