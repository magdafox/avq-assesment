package com.assesment.avaloq.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RollSimulationApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void executeSimulationIntegrationTest() throws Exception {
        mockMvc.perform(
                post("/dice/simulations")
                        .param("diceNumber", "1")
                        .param("diceSide", "4")
                        .param("numberOfRolls", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void getDistributionDetailsIntegrationTest() throws Exception {
        mockMvc.perform(
                get("/dice/simulations/distributions"))
                .andExpect(status().isOk());
    }
}
