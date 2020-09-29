package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Details of the combination of dice number - dice side.
 */
@Getter
@Setter
public class DiceCombinationDetails {

    private int diceNumber;

    private int diceSide;

    private int simulationsNumber;

    private long rollsNumber;

    private List<RelativeDistributionDetails> distribution;
}
