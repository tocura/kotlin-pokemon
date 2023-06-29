package com.tocura.study.kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class RestClientConfig {

    @Bean
    fun restClient(): WebClient.Builder {
        return WebClient.builder()
    }
}