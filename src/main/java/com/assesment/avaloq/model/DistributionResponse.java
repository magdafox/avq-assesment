package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Result of the requested distribution for all combinations.
 */
@Getter
@Setter
public class DistributionResponse {
    private List<DiceCombinationDetails> diceCombinations;
}
