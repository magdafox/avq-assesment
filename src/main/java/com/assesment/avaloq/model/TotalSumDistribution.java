package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiceDistributionResponse {

    /**
     * Total sum of the rolled dices.
     */
    private int totalSum;

    /**
     * How many times given total sum has been rolled.
     */
    private long count;
}
