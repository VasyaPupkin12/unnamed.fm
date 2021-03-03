package com.nonamefm.queue.domains.user;

import com.nonamefm.queue.common.exception.InvalidArgumentException;
import com.nonamefm.queue.dto.registration.web.AccountInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "auth_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    String name;

    @NonNull
    String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    UserRole role;

    @NonNull
    @OneToOne
    AccountSettings accountSettings;

    public static Account createAccount(AccountInput accountInput, String password, AccountSettings settings) {
        UserRole role = mapAccountRole(accountInput);
        return Account.builder()
                .name(accountInput.getName())
                .password(password)
                .role(role)
                .accountSettings(settings)
                .build();
    }

    private static UserRole mapAccountRole(AccountInput accountInput) {
        if ((accountInput.getRole() == null)) {
            throw new InvalidArgumentException("Wrong role is " + accountInput.getRole());
        }
        UserRole role;
        try {
            role = UserRole.valueOf(accountInput.getRole());
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Wrong role is " + accountInput.getRole());
        }
        return role;
    }

}
