package com.university.usermanagement.integration;

import com.university.usermanagement.aggregates.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

public class UsersApiIT extends BaseIntegrationTest {

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveStudentCourse() {
        User user = User.builder()
                .socialSecurityNumber("12312")
                .lastName("tester")
                .firstName("test")
                .phone("phone")
                .fatherName("test")
                .motherName("test")
                .email("ok")
                .build();
        HttpEntity<User> entityCreate = new HttpEntity<>(user, headers);

        ResponseEntity<User> responseCreate = restTemplate.exchange(
                createURLWithPort("/user/"),
                HttpMethod.POST, entityCreate, User.class);

        Assertions.assertEquals(HttpStatusCode.valueOf(201), responseCreate.getStatusCode());
        Assertions.assertNotNull(responseCreate.getBody().getUuid());

        HttpEntity<User> entity = new HttpEntity<>(null, headers);

        ResponseEntity<List<User>> response = restTemplate.exchange(
                createURLWithPort("/user/all"), HttpMethod.GET,
                entity, new ParameterizedTypeReference<>() {
                });

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        Assertions.assertEquals(1, response.getBody().size());
        Assertions.assertNotNull(response.getBody().get(0).getUuid());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
