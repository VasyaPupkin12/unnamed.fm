package com.nonamefm.queue.repositories.user;

import com.nonamefm.queue.domains.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepositories extends JpaRepository<Account, Long> {
    Optional<Account> findByName(String name);
}
