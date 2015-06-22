package com.planitpal.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.springframework.context.annotation.FilterType.ANNOTATION;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@EnableWebMvc
@Configuration
@ComponentScan(
        basePackages = {"com.planitpal.service"},
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = ANNOTATION, value = {Configuration.class, Controller.class, Component.class})},
        excludeFilters = {@ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = ApplicationConfiguration.class)}
)
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

}
