package com.konai.hsyang.konatoyfe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
            .build();
    @Bean
    public WebClient webClient(){

        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .exchangeStrategies(exchangeStrategies)
                .build();
    }
}
