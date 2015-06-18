package com.planitpal.plan.templateservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

import static org.springframework.util.StringUtils.commaDelimitedListToStringArray;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
public class ProfilesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static String APPLICATION_PROFILES = "application.profiles";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setActiveProfiles(load(environment));
//        log.info("Spring Profile {} activated.", environment.getProperty(APPLICATION_PROFILES));
    }

    private String[] load(
            ConfigurableEnvironment environment) {
        String profiles = StringUtils.EMPTY;
        try {
            environment.getPropertySources().addFirst(new ResourcePropertySource("classpath:configuration/application.properties"));
            profiles = environment.getProperty(APPLICATION_PROFILES);

        } catch (IOException e) {
//            log.error("Could not load application properties", e);
        }
        return hasText(profiles) ? commaDelimitedListToStringArray(profiles) : new String[0];
    }
}
