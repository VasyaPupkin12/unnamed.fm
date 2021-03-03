package com.nonamefm.queue.fixtures;

import com.nonamefm.queue.domains.user.AccountSettings;
import com.nonamefm.queue.repositories.settings.AccountSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountSettingFixtures {

    @Autowired
    AccountSettingsRepository accountSettingsRepository;

    public static final long COUNT_SONGS = 1L;

    public AccountSettings createSettings() {
        return accountSettingsRepository.save(buildSettings());
    }

    private static AccountSettings buildSettings() {
        return AccountSettings.builder()
                .countSongs(COUNT_SONGS)
                .build();
    }

}
