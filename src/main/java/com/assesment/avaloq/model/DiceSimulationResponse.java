package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiceSimulationResponse {

    /**
     * Distribution of the rolled total sums.
     */
    private List<DiceDistributionResponse> diceDistribution;
}
