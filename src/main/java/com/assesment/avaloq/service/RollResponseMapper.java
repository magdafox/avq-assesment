package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.model.DiceDistributionResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class to map dice distribution API responses.
 */
@UtilityClass
public class RollResponseMapper {

    public List<DiceDistributionResponse> mapDistributionList(List<Roll> rollList) {
        Map<Integer, Long> totalCounts = countByTotalSum(rollList);
        return totalCounts.entrySet().stream()
                .map(t -> mapDistribution(t.getKey(), t.getValue())).collect(Collectors.toList());
    }

    private Map<Integer, Long> countByTotalSum(List<Roll> rollList) {
        return rollList.stream().collect(Collectors.groupingBy(Roll::getTotalSum, Collectors.counting()));
    }

    private DiceDistributionResponse mapDistribution(Integer total, Long amount) {
        DiceDistributionResponse response = new DiceDistributionResponse();
        response.setTotalSum(total);
        response.setCount(amount);
        return response;
    }
}
