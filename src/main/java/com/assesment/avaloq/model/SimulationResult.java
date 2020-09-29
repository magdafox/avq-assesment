package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Result of the executed simulation.
 */
@Getter
@Setter
public class SimulationResult {

    /**
     * Distribution of the rolled total sums.
     */
    private List<TotalSumResult> results;
}
