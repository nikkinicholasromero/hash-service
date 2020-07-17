package com.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HashController.class)
public class HashControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HashService hashService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        when(hashService.generateRandomSalt()).thenReturn("someRandomSalt");
        when(hashService.hash(anyString(), anyString())).thenReturn("someHashedText");

        objectMapper = new ObjectMapper();
    }

    @Test
    public void getRandomSalt() throws Exception {
        mockMvc.perform(get("/salt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(equalTo("someRandomSalt")));

        verify(hashService, times(1)).generateRandomSalt();
    }

    @Test
    public void hash() throws Exception {
        HashRequest hashRequest = new HashRequest("someClearText", "someSalt");

        mockMvc.perform(post("/hash")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(hashRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(equalTo("someHashedText")));

        verify(hashService, times(1)).hash("someClearText", "someSalt");
    }
}
