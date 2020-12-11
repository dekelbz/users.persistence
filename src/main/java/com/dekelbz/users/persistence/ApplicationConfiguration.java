package com.dekelbz.users.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.BytesJsonMessageConverter;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public BytesJsonMessageConverter jsonMessageConverter() {
        return new BytesJsonMessageConverter();
    }

}
