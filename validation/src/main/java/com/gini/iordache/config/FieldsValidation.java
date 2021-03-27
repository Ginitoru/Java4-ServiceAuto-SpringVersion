package com.gini.iordache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class FieldsValidation {


                //ma ajuta sa arat error message cand bag String in loc de int, double pe fields

    @Bean
    public ResourceBundleMessageSource messageSource(){

        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages/messages");

        return source;
    }

}
