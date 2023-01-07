package com.example.conditionalapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    private static final GenericContainer<?> conditionalDev = new GenericContainer<>("conditional-dev:1.0")
            .withExposedPorts(8080);
    private static final GenericContainer<?> conditionalProd = new GenericContainer<>("conditional-prod:1.0")
            .withExposedPorts(8081);
    private static final String profilePath = "/profile";
    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        conditionalDev.start();
        conditionalProd.start();
    }

    @Test
    public void conditionalDev() {
        ResponseEntity<String> devContext = restTemplate
                .getForEntity("http://localhost:" + conditionalDev
                        .getMappedPort(8080) + "/profile", String.class);
        String bodyFromProfile = devContext.getBody();
        Assertions.assertEquals(bodyFromProfile, "Current profile is dev");
    }

    @Test
    public void conditionalProd() {
        ResponseEntity<String> prodContext = restTemplate
                .getForEntity("http://localhost:" + conditionalProd
                        .getMappedPort(8081) + "/profile", String.class);
        String bodyFromProfile = prodContext.getBody();
        Assertions.assertEquals(bodyFromProfile, "Current profile is production");
    }

}
