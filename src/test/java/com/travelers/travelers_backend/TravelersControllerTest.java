package com.travelers.travelers_backend;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelers.travelers_backend.controller.TravelersController;
import com.travelers.travelers_backend.model.Traveler;
import com.travelers.travelers_backend.service.Redis;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TravelersController.class)
public class TravelersControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    Redis redis;

    @MockBean
    Traveler traveler;

    @Test
    public void createRecord_success() throws Exception {
        Traveler RECORD_1 = new Traveler("123456789", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/travelers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.green", notNullValue()));
    }

    @Test
    public void createRecord_badRequest() throws Exception {
        Traveler RECORD_1 = new Traveler("1234", true);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/travelers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getStatus_success() throws Exception {
        Traveler RECORD_1 = new Traveler("123456789", true);
        Mockito.when(redis.get(RECORD_1.getId())).thenReturn(java.util.Optional.of(RECORD_1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/travelers/123456789")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStatus_badRequest() throws Exception {
        Traveler RECORD_1 = new Traveler("12345", true);
        Mockito.when(redis.get(RECORD_1.getId())).thenReturn(java.util.Optional.of(RECORD_1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/travelers/12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
