package org.example.config;

import org.example.facade.encryptions.Base64System;
import org.example.facade.encryptions.BcryptSystem;
import org.example.facade.encryptions.EncryptionFacade;
import org.example.facade.encryptions.IEncryptFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeansConfiguration {

    @Bean
    public BcryptSystem bcryptSystem() {
        return new BcryptSystem(new BCryptPasswordEncoder());
    }

    @Bean
    public Base64System base64System() {
        return new Base64System();
    }

    public IEncryptFacade encryptFacade() {
        return new EncryptionFacade(new BCryptPasswordEncoder(), base64System());
    }
}
