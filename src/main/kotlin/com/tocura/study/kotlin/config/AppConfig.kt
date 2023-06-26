package com.tocura.study.kotlin.config

import de.huxhorn.sulky.ulid.ULID
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun ulid(): ULID {
        return ULID()
    }
}