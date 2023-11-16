package com.sirv.backend.config;

import com.sirv.backend.EndUserException;
import com.sirv.backend.dto.request.RegisterRequest;
import com.sirv.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
@RequiredArgsConstructor
public class SetupInitialUser implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup=false;

    private final UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        if (alreadySetup) return;

        try {
            userService.createUser(new RegisterRequest("admin", "333", "Cl 1", "admin","calle2"), true);
        } catch (EndUserException ignored) {}

        alreadySetup = true;
    }
}
