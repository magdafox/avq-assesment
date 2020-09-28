package com.assesment.avaloq.api;

import com.assesment.avaloq.model.DiceSimulationResponse;
import com.assesment.avaloq.service.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@Validated
public class DiceApi {

    @Autowired
    private DiceService diceService;

    @PostMapping(value = "/dices/distributions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiceSimulationResponse> executeSimulation(@RequestParam(value = "numberOfDices", required = false) @Min(1) Integer numberOfDices,
                                                                    @RequestParam(value = "sidesOfDice", required = false) @Min(4) Integer sidesOfDice,
                                                                    @RequestParam(value = "numberOfRolls", required = false) @Min(1) Integer numberOfRolls) {
        return diceService.executeSimulation(numberOfDices, sidesOfDice, numberOfRolls);
    }
}
