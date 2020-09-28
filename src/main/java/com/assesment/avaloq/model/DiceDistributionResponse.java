package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiceDistributionResponse {

    /**
     * Total sum of the rolled dices.
     */
    private int total;

    /**
     * How many times given total has been rolled.
     */
    private int amount;
}
