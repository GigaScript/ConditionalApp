package com.example.conditionalapp.config;

import com.example.conditionalapp.model.DevProfile;
import com.example.conditionalapp.model.ProductionProfile;
import com.example.conditionalapp.model.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(name = "conditionallapp.profile.dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "conditionallapp.profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
