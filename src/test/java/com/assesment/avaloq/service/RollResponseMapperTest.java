package com.assesment.avaloq.service;

import com.assesment.avaloq.domain.Roll;
import com.assesment.avaloq.domain.RollConfiguration;
import com.assesment.avaloq.domain.Simulation;
import com.assesment.avaloq.model.DistributionDetails;
import com.assesment.avaloq.model.TotalSumDistribution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RollResponseMapperTest {

    @Test
    void mapRollsToTotalSumDistributionTest() {
        Roll roll1 = new Roll();
        roll1.setTotalSum(1);

        Roll roll2 = new Roll();
        roll2.setTotalSum(5);

        Roll roll3 = new Roll();
        roll3.setTotalSum(5);

        List<TotalSumDistribution> totalSums = RollResponseMapper.mapTotalSumDistribution(List.of(roll1, roll2, roll3));

        assertNotNull(totalSums);
        assertEquals(2, totalSums.size());
        TotalSumDistribution sum1 = totalSums.get(0);
        assertNotNull(sum1);
        assertEquals(1, sum1.getTotalSum());
        assertEquals(1, sum1.getCount());
        TotalSumDistribution sum2 = totalSums.get(1);
        assertNotNull(sum2);
        assertEquals(5, sum2.getTotalSum());
        assertEquals(2, sum2.getCount());
    }

    @Test
    void mapDistributionList() {
        Roll roll1 = new Roll();
        roll1.setTotalSum(1);
        Roll roll2 = new Roll();
        roll2.setTotalSum(2);

        Simulation simulation1 = new Simulation();
        simulation1.setRolls(List.of(roll1, roll2));

        RollConfiguration config1 = new RollConfiguration();
        config1.setDiceSide(1);
        config1.setDiceNumber(2);
        config1.setSimulations(List.of(simulation1));

        List<DistributionDetails> detailsList = RollResponseMapper.mapDistributionList(List.of(config1));

        assertNotNull(detailsList);
        assertEquals(1, detailsList.size());
        DistributionDetails details1 = detailsList.get(0);
        assertNotNull(details1);
        assertEquals(config1.getDiceNumber(), details1.getDiceNumber());
        assertEquals(config1.getDiceSide(), details1.getDiceSide());
        assertEquals(1, details1.getSimulationsNumber());
        assertEquals(2, details1.getRollsNumber());
    }
}
