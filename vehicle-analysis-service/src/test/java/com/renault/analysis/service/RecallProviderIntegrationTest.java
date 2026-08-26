package com.renault.analysis.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.test.util.ReflectionTestUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecallProviderIntegrationTest {

    private WireMockServer wireMock;
    private RecallProvider provider;

    @BeforeEach
    void setUp() {
        wireMock = new WireMockServer(0);
        wireMock.start();

        provider = new RecallProvider();
        ReflectionTestUtils.setField(
                provider,
                "providerUrl",
                "http://localhost:" + wireMock.port()
        );
    }

    @AfterEach
    void tearDown() {
        wireMock.stop();
    }

    @Test
    void shouldUseWireMockResponse() {
        wireMock.stubFor(get(urlEqualTo("/recalls/VIN123"))
                .willReturn(okJson("true")));

        assertTrue(provider.hasRecall("VIN123"));

        wireMock.verify(getRequestedFor(urlEqualTo("/recalls/VIN123")));
    }
}
