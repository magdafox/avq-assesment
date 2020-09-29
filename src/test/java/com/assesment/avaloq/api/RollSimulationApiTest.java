package com.assesment.avaloq.api;

import com.assesment.avaloq.service.RollSimulationApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RollSimulationApi.class)
class RollSimulationApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RollSimulationApiService rollSimulationApiService;

    @Test
    void simulateWithNumberOfDicesIncorrect() throws Exception {
        mockMvc.perform(
                post("/dice/simulations")
                        .param("diceNumber", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void simulateWithNumberOfDicesCorrect() throws Exception {
        mockMvc.perform(
                post("/dice/simulations")
                        .param("diceNumber", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void simulateWithSidesOfDiceIncorrect() throws Exception {
        mockMvc.perform(
                post("/dice/simulations")
                        .param("diceSide", "3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void simulateWithSidesOfDiceCorrect() throws Exception {
        mockMvc.perform(
                post("/dice/simulations")
                        .param("diceSide", "4"))
                .andExpect(status().isOk());
    }

    @Test
    void simulateWithNumberOfRollsIncorrect() throws Exception {
        mockMvc.perform(
                post("/dice/simulations")
                        .param("numberOfRolls", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void simulateWithNumberOfRollsCorrect() throws Exception {
        mockMvc.perform(
                post("/dice/simulations")
                        .param("numberOfRolls", "1"))
                .andExpect(status().isOk());
    }
}
