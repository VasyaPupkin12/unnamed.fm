package com.nonamefm.queue.service.settings;

import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.dto.account.web.AccountSettingsInput;
import com.nonamefm.queue.service.account.AccountApi;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AccountSettingServiceImpl implements AccountSettingService {

    @NonNull AccountApi accountApi;

    @Override
    public void addSettings(AccountSettingsInput input) {
        Account currentAccount = accountApi.getCurrentAccount();
    }
}
