package com.tocura.study.kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class RestClientConfig {

    @Bean
    fun restClient(): WebClient {
        return WebClient.builder()
            .exchangeStrategies(
                ExchangeStrategies.builder()
                    .codecs { c -> c.defaultCodecs()
                        .maxInMemorySize(1024 * 1024)
                    }
                    .build()
            )
            .build()
    }
}