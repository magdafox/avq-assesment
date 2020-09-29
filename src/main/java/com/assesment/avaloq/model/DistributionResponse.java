package com.assesment.avaloq.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DistributionResponse {
    private List<DiceCombinationDetails> diceCombinations;
}
