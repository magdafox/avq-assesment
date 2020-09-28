package com.assesment.avaloq.api;

import com.assesment.avaloq.model.DiceSimulationResponse;
import com.assesment.avaloq.service.DiceApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@Validated
public class DiceApi {

    @Autowired
    private DiceApiService diceApiService;

    @PostMapping(value = "/dices/distributions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiceSimulationResponse> executeSimulation(
            @RequestParam(value = "diceNumber", required = false, defaultValue = "3") @Min(1) Integer diceNumber,
            @RequestParam(value = "diceSide", required = false, defaultValue = "6") @Min(4) Integer diceSide,
            @RequestParam(value = "numberOfRolls", required = false, defaultValue = "100") @Min(1) Integer numberOfRolls) {
        return diceApiService.executeSimulation(diceNumber, diceSide, numberOfRolls);
    }

    @GetMapping(value = "/dices/distributions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getDistributions() {
        return diceApiService.getDistributions();
    }
}
