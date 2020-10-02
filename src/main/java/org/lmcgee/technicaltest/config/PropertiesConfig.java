package org.lmcgee.technicaltest.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class PropertiesConfig {
    @Value("${application.all.users}")
    private String allUsersEndpoint;
    @Value("${spring.profiles.active}")
    private String activeProfile;
}
