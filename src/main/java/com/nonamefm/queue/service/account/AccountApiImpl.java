package com.nonamefm.queue.service.account;

import com.nonamefm.queue.common.exception.InvalidArgumentException;
import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.domains.user.AccountSettings;
import com.nonamefm.queue.dto.registration.web.AccountInput;
import com.nonamefm.queue.repositories.settings.AccountSettingsRepository;
import com.nonamefm.queue.repositories.user.AccountRepositories;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccountApiImpl implements AccountApi {

    AccountRepositories accountRepositories;

    AccountSettingsRepository accountSettingsRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void registerAccount(AccountInput accountInput) {
        if (checkAccountName(accountInput)) {
             throw new InvalidArgumentException("User is already registered with name: " + accountInput.getName());
        }
        Account account = Account.createAccount(accountInput,
                                                passwordEncoder.encode(accountInput.getPassword()),
                                                createEmptySettings());
        saveAccount(account);
    }

    @Override
    public Account getCurrentAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return accountRepositories.findByName(auth.getName()).orElseGet(null);
    }

    private void saveAccount(Account account) {
        accountRepositories.save(account);
    }

    private boolean checkAccountName(AccountInput account) {
        return accountRepositories.findByName(account.getName()).isPresent();
    }

    private AccountSettings createEmptySettings() {
        AccountSettings settings = new AccountSettings();
        return accountSettingsRepository.save(settings);
    }

}
