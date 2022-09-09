package com.example.fuckdaka.config;

import cc.renxing.MessageTools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RenxingPushConfig {
    @Value("${app.renxing-push-cipher}")
    String cipher;

    @Bean
    public MessageTools messageTools() {
        return new MessageTools(cipher);
    }
}
