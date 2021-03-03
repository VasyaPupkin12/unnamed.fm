package com.nonamefm.queue.endpoints.registration;

import com.nonamefm.queue.dto.registration.web.AccountInput;
import com.nonamefm.queue.service.account.AccountApi;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrations")
@AllArgsConstructor
public class RegistrationController {

    @NonNull AccountApi accountApi;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createAccount(@RequestBody final AccountInput accountInput) {
        accountApi.registerAccount(accountInput);
    }

}
