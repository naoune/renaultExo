package com.renault.telemetry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renault.telemetry.model.TelemetryRequest;
import com.renault.telemetry.service.TelemetryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TelemetryController.class)
class TelemetryControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean TelemetryService service;

    @Test
    void shouldReturn202() throws Exception {
        TelemetryRequest request = new TelemetryRequest();
        request.setVin("VIN123");
        request.setMileage(1000);
        request.setTirePressure(2.2);
        request.setBatteryVoltage(12.6);
        request.setEngineTemperature(90);
        request.setFuelLevel(50);
        request.setErrorCodes(List.of());

        mockMvc.perform(post("/api/v1/telemetry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());
    }
}
