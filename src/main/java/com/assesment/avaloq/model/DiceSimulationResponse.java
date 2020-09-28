package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiceSimulationResponse {
    /**
     * Summed roll numbers from the dices.
     */
    private int sum;

    /**
     * Distribution of the rolled total sums.
     */
    private List<DiceDistributionResponse> distribution;
}
