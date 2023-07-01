package com.tocura.study.kotlin.adapter.external.pokeapi

import com.tocura.study.kotlin.adapter.external.pokeapi.resource.PokeAPIResponse
import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.model.PokeAPI
import com.tocura.study.kotlin.core.ports.PokeApiClient
import com.tocura.study.kotlin.exceptions.InternalServerErrorException
import com.tocura.study.kotlin.exceptions.PokeApiNotFoundException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.net.URI

@Component
class PokeApiImpl(val restClient: WebClient) : PokeApiClient {
    private val log = KotlinLogging.logger("adapter.external.pokeapi")

    @Value("\${pokeapi.host}")
    private val host: String = ""

    @Value("\${pokeapi.path.get-pokemon}")
    private val getPokemonEndpoint: String = ""

    override fun getByName(name: String, gameVersion: GameVersion): PokeAPI {
        val endpoint = this.getPokemonEndpoint.replace("{pokemon_name}", name, true)
        val baseUrl = this.host + endpoint

        try {
            val response = this.restClient.get()
                .uri(URI(baseUrl))
                .retrieve()
                .bodyToMono(PokeAPIResponse::class.java)

            return response.block()!!.toDomain(gameVersion)
        } catch (e: WebClientResponseException) {
            log.error(e) {"error to integrate with pokeapi"}
            when(e.statusCode) {
                HttpStatus.NOT_FOUND -> throw PokeApiNotFoundException("$name not found")
                else -> throw InternalServerErrorException("error to integrate with pokeapi")
            }
        }
    }
}