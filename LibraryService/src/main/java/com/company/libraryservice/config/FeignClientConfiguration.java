package com.company.libraryservice.config;

import com.company.libraryservice.client.RetrieveMessageErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    private final ObjectFactory<HttpMessageConverters> messageConverterObjectFactory;

    public FeignClientConfiguration(ObjectFactory<HttpMessageConverters> messageConverterObjectFactory) {
        this.messageConverterObjectFactory = messageConverterObjectFactory;
    }

//    Feign client error handling

    @Bean
    public ErrorDecoder errorDecoder(){
        return new RetrieveMessageErrorDecoder();
    }

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
