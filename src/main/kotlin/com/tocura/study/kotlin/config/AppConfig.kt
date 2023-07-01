package com.tocura.study.kotlin.config

import de.huxhorn.sulky.ulid.ULID
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlinx.coroutines.sync.Mutex

@Configuration
class AppConfig {

    @Bean
    fun ulid(): ULID = ULID()

    @Bean
    fun mutex(): Mutex = Mutex()
}