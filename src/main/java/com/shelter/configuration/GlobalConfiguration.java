package com.shelter.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class GlobalConfiguration {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
