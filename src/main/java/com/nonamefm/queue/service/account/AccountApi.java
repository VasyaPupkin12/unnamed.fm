package com.nonamefm.queue.service.account;

import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.dto.registration.web.AccountInput;

public interface AccountApi {

    void registerAccount(AccountInput accountInput);

    Account getCurrentAccount();
}
