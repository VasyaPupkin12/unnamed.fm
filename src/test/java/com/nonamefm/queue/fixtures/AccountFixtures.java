package com.nonamefm.queue.fixtures;

import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.domains.user.UserRole;
import com.nonamefm.queue.repositories.user.AccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountFixtures {

    @Autowired
    AccountRepositories accountRepositories;

    @Autowired
    AccountSettingFixtures accountSettingFixtures;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static final String ACCOUNT_NAME = "name";
    public static final String ACCOUNT_PASSWORD = "password";
    public static final UserRole ACCOUNT_ROLE = UserRole.ADMIN;

    public Account createAccount() {
        return accountRepositories.save(buildAccount());
    }

    private Account buildAccount() {
        return Account.builder()
                .name(ACCOUNT_NAME)
                .password(passwordEncoder.encode(ACCOUNT_PASSWORD))
                .role(ACCOUNT_ROLE)
                .accountSettings(accountSettingFixtures.createSettings())
                .build();
    }
}
