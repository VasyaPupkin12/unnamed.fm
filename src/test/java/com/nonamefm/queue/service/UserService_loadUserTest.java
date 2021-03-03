package com.nonamefm.queue.service;

import com.nonamefm.queue.common.JUnitTestCase;
import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.fixtures.AccountFixtures;
import com.nonamefm.queue.repositories.user.AccountRepositories;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

class UserService_loadUserTest extends JUnitTestCase {

    public static final String WRONG_NAME = "account1";

    @Autowired
    AccountFixtures accountFixtures;

    @Autowired
    AccountRepositories accountRepositories;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
       accountFixtures.createAccount();
    }

    @AfterEach
    void cleanAll() {
        accountRepositories.deleteAll();
    }

    @Test
    void loadUserByUsername_validAccount() {
        UserDetails userDetails = userService.loadUserByUsername(AccountFixtures.ACCOUNT_NAME);
        Assertions.assertEquals(userDetails.getUsername(), AccountFixtures.ACCOUNT_NAME);
    }

    @Test
    void loadUserByUsername_notValidAccountName() {
        Assertions.assertThrows(UsernameNotFoundException.class, (() -> userService.loadUserByUsername(WRONG_NAME)));
    }
}