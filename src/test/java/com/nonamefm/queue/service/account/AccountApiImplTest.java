package com.nonamefm.queue.service.account;

import com.nonamefm.queue.common.JUnitTestCase;
import com.nonamefm.queue.common.exception.InvalidArgumentException;
import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.dto.registration.web.AccountInput;
import com.nonamefm.queue.fixtures.AccountFixtures;
import com.nonamefm.queue.repositories.user.AccountRepositories;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountApiImplTest extends JUnitTestCase {

    @Autowired
    AccountApi accountApi;

    @Autowired
    AccountRepositories accountRepositories;

    @Autowired
    AccountFixtures accountFixtures;

    @AfterEach
    void tearDown() {
        accountRepositories.deleteAll();
    }

    @Test
    void registerAccount_validInput() {
        accountApi.registerAccount(createAccountInput());
        int countAccount = accountRepositories.findAll().size();
        Assertions.assertEquals(countAccount, 1);
    }

    @Test
    void registerAccount_invalidName() {
        Account account = accountFixtures.createAccount();
        accountRepositories.save(account);
        Assertions.assertThrows(InvalidArgumentException.class, () -> accountApi.registerAccount(createAccountInput()));
    }

    @Test
    void registerAccount_invalidRole() {
        AccountInput accountInput = new AccountInput(AccountFixtures.ACCOUNT_NAME, AccountFixtures.ACCOUNT_PASSWORD, "role1");
        Assertions.assertThrows(InvalidArgumentException.class, () -> accountApi.registerAccount(accountInput));
    }



    private AccountInput createAccountInput() {
        return new AccountInput(AccountFixtures.ACCOUNT_NAME,
                AccountFixtures.ACCOUNT_PASSWORD,
                AccountFixtures.ACCOUNT_ROLE.toString());
    }
}