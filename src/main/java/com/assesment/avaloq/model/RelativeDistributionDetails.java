package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Response for relative distribution of specified totalSum.
 */
@Getter
@Setter
public class RelativeDistributionDetails {

    /**
     * Total sum of the rolled dices.
     */
    private int totalSum;

    /**
     * Percentage number of given totalSum rolls compared to the total rolls.
     */
    private BigDecimal relativeDistribution;
}
