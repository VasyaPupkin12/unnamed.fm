package com.nonamefm.queue.domains.user;

import com.nonamefm.queue.dto.account.web.AccountSettingsInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_account_settings")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long countSongs;

    public static AccountSettings createAccountSettings(AccountSettingsInput input) {
        return AccountSettings.builder()
                .countSongs(input.getCountSong())
                .build();
    }

}
