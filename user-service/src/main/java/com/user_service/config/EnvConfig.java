package com.user_service.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EnvConfig implements ApplicationListener<ApplicationReadyEvent> {
    
    private final ConfigurableEnvironment environment;

    public EnvConfig(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("../")
                .ignoreIfMissing()
                .load();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Dotenv dotenv = dotenv();
        Map<String, Object> envProperties = new HashMap<>();

        dotenv.entries().forEach(entry -> envProperties.put(entry.getKey(), entry.getValue()));

        environment.getPropertySources().addFirst(new MapPropertySource("dotenvProperties", envProperties));
    }
}
