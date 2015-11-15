package com.atoz.config;

import com.atoz.auth.AuthManager;
import com.atoz.service.UserService;
import com.atoz.ui.LoginFormListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.atoz.ui" , "com.atoz.auth", "com.atoz.service"})
public class AppConfig {

    @Bean
    public AuthManager authManager() {
        AuthManager res = new AuthManager();
        return res;
    }

    @Bean
    public UserService userService() {
        UserService res = new UserService();
        return res;
    }

    @Bean
    public LoginFormListener loginFormListener() {
        return new LoginFormListener();
    }
}
