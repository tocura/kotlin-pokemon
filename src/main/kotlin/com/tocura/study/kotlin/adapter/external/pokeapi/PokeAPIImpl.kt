package com.tocura.study.kotlin.adapter.external.pokeapi

import com.tocura.study.kotlin.adapter.external.pokeapi.resource.PokeAPIResponse
import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.model.PokeAPI
import com.tocura.study.kotlin.core.ports.PokeAPIClient
import com.tocura.study.kotlin.exceptions.InternalServerErrorException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

@Component
class PokeAPIImpl(val restClient: WebClient.Builder) : PokeAPIClient {
    private val log = KotlinLogging.logger("adapter.external.pokeapi")

    @Value("\${pokeapi.host}")
    private val host: String = ""

    @Value("\${pokeapi.path.get-pokemon}")
    private val getPokemonEndpoint: String = ""

    override fun GetByName(name: String, gameVersion: GameVersion): PokeAPI {
        val endpoint = this.getPokemonEndpoint.replace("{pokemon_name}", name, true)
        val baseUrl = this.host + endpoint

        val client = this.restClient
            .baseUrl(baseUrl)
            .build()

        try {
            val response = client.get()
                .retrieve()
                .bodyToMono(PokeAPIResponse::class.java)
                .block()

//            log.info {"**** pokeapi response ${response!!.types[0].type}"}

            return response!!.toDomain(gameVersion)
        } catch (e: WebClientResponseException) {
            log.error(e) {"error to integrate with pokeapi"}
            throw InternalServerErrorException("error to integrate with pokeapi")
        }
    }
}