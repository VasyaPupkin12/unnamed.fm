package com.nonamefm.queue.repositories.settings;

import com.nonamefm.queue.domains.user.AccountSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountSettingsRepository extends JpaRepository<AccountSettings, Long> {
}
