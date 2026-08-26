package com.renault.analysis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class RecallProvider {

    @Value("${provider.url}")
    private String providerUrl;

    public boolean hasRecall(String vin) {
        try {
            Boolean result = RestClient.create(providerUrl)
                    .get()
                    .uri("/recalls/{vin}", vin)
                    .retrieve()
                    .body(Boolean.class);
            return Boolean.TRUE.equals(result);
        } catch (Exception e) {
            return false;
        }
    }
}
