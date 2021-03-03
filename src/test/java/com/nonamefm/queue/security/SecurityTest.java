package com.nonamefm.queue.security;

import com.nonamefm.queue.common.JUnitTestCase;
import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.fixtures.AccountFixtures;
import com.nonamefm.queue.repositories.user.AccountRepositories;
import com.nonamefm.queue.service.account.AccountApi;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Objects;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityTest extends JUnitTestCase {

    public static final String SECURED_API = "/api/hello";

    @LocalServerPort
    int port;

    @Autowired
    AccountRepositories accountRepositories;

    @Autowired
    AccountApi accountApi;

    @Autowired
    AccountFixtures accountFixtures;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private TestRestTemplate testRestTemplate;

    @PostConstruct
    public void initialize() {
        this.testRestTemplate = new TestRestTemplate(restTemplateBuilder
                .rootUri("http://localhost:" + port),
                null, null, //I don't use basic auth, if you do you can set user, pass here
                TestRestTemplate.HttpClientOption.ENABLE_COOKIES); // I needed cookie support in this particular test, you may not have this need
    }

    @Test
    public void unauthorizedError() {
        ResponseEntity<String> forEntity = testRestTemplate.withBasicAuth(AccountFixtures.ACCOUNT_NAME, AccountFixtures.ACCOUNT_PASSWORD).getForEntity(SECURED_API, String.class);
        Assertions.assertEquals(forEntity.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void getOk() {
        Account account = accountFixtures.createAccount();
        accountRepositories.save(account);
        ResponseEntity<String> loginResponse = testRestTemplate.postForEntity("http://localhost:" + port + "/login",
                createLoginRequest(), String.class);
        Assertions.assertEquals(loginResponse.getStatusCode(), HttpStatus.OK);
        ResponseEntity<String> response = testRestTemplate.exchange(SECURED_API,
                HttpMethod.GET,
                new HttpEntity<String>(setCookies(loginResponse)),
                String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void checkLoginUser() {

    }

    private HttpEntity<MultiValueMap<String, String>> createLoginRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", AccountFixtures.ACCOUNT_NAME);
        map.add("password", AccountFixtures.ACCOUNT_PASSWORD);
        return new HttpEntity<>(map, headers);
    }

    private HttpHeaders setCookies(ResponseEntity<String> loginResponse) {
        String cookie = Objects.requireNonNull(loginResponse.getHeaders().get("Set-Cookie")).get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie );
        return headers;
    }

    @TestConfiguration
    public static class TestConfig {
        @RestController
        public static class TestApiController {
            @GetMapping(SECURED_API)
            public String helloApi() {
                return "hello";
            }
        }
    }

}
