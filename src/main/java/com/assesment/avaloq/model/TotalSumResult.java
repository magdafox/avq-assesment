package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Each totalSum executed in the simulation counted.
 */
@Getter
@Setter
public class TotalSumResult {

    /**
     * Total sum of the rolled dices.
     */
    private int totalSum;

    /**
     * How many times given total sum has been rolled.
     */
    private long count;
}
