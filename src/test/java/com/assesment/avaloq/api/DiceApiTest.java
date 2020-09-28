package com.assesment.avaloq.api;

import com.assesment.avaloq.service.DiceService;
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
@WebMvcTest(value = DiceApi.class)
class DiceApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiceService diceService;

    @Test
    void simulateWithNumberOfDicesIncorrect() throws Exception {
        mockMvc.perform(
                post("/dices/distributions")
                        .param("numberOfDices", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void simulateWithNumberOfDicesCorrect() throws Exception {
        mockMvc.perform(
                post("/dices/distributions")
                        .param("numberOfDices", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void simulateWithSidesOfDiceIncorrect() throws Exception {
        mockMvc.perform(
                post("/dices/distributions")
                        .param("sidesOfDice", "3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void simulateWithSidesOfDiceCorrect() throws Exception {
        mockMvc.perform(
                post("/dices/distributions")
                        .param("sidesOfDice", "4"))
                .andExpect(status().isOk());
    }

    @Test
    void simulateWithNumberOfRollsIncorrect() throws Exception {
        mockMvc.perform(
                post("/dices/distributions")
                        .param("numberOfRolls", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void simulateWithNumberOfRollsCorrect() throws Exception {
        mockMvc.perform(
                post("/dices/distributions")
                        .param("numberOfRolls", "1"))
                .andExpect(status().isOk());
    }
}
