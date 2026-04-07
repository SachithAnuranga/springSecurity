package com.example.springSecurity.springSecurity.config;

import org.springframework.context.annotation.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;


/**
 * This configuration class is responsible for defining and managing
 * the ModelMapper bean within the Spring application context.
 *
 * ModelMapper is used to automatically map data between objects,
 * typically between Entity and DTO classes, reducing manual mapping code.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and returns a ModelMapper bean.
     *
     * This bean can be injected anywhere in the application
     * to perform object-to-object mapping.
     *
     * @return ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}