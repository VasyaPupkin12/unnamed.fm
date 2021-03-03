package com.nonamefm.queue.controller;

import com.nonamefm.queue.common.JUnitTestCase;
import com.nonamefm.queue.dto.registration.web.AccountInput;
import com.nonamefm.queue.fixtures.AccountFixtures;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InvalidExceptionTest extends JUnitTestCase {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void invalidRequest() {
        AccountInput accountInput = new AccountInput(AccountFixtures.ACCOUNT_NAME, AccountFixtures.ACCOUNT_NAME, null);
        ResponseEntity<String> request = testRestTemplate.postForEntity("http://localhost:" + port + "/registrations/create", accountInput, String.class);
        Assertions.assertEquals(request.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
