package com.nonamefm.queue.service;

import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.dto.registration.web.AccountInput;
import com.nonamefm.queue.repositories.user.AccountRepositories;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service("customUserDetailsService")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    AccountRepositories accountRepositories;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return accountRepositories.findByName(name).map(
           account -> User.builder()
            .accountLocked(false)
            .username(account.getName())
            .password(account.getPassword())
            .authorities(account.getRole().toString())
            .disabled(false)
            .build()
        ).orElseThrow(() -> new UsernameNotFoundException("Can not find account by name: " + name));

    }

}
