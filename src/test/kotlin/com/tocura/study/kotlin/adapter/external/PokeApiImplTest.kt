package com.tocura.study.kotlin.adapter.external

import com.tocura.study.kotlin.adapter.external.pokeapi.PokeApiImpl
import com.tocura.study.kotlin.adapter.external.pokeapi.resource.PokeAPIResponse
import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.ports.PokeApiClient
import com.tocura.study.kotlin.exceptions.InternalServerErrorException
import com.tocura.study.kotlin.exceptions.PokeApiNotFoundException
import com.tocura.study.kotlin.mocks.fixture.DomainMocks
import com.tocura.study.kotlin.mocks.fixture.PokeApiResourceMocks
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono
import java.net.URI

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokeApiImplTest {

    private val restClient: WebClient = mockk()
    private val requestBodyUriSpec: WebClient.RequestBodyUriSpec = mockk()
    private val requestBodySpec: WebClient.RequestBodySpec = mockk()
    private val responseSpec: WebClient.ResponseSpec = mockk()

    private val pokeApi: PokeApiClient = PokeApiImpl(restClient)

    @Test
    fun `get pokemon info with success`() {
        val pokeApiResponseMock = PokeApiResourceMocks()
        val domainMocks = DomainMocks()

        every { restClient.get() } returns requestBodyUriSpec
        every { requestBodyUriSpec.uri(any<URI>()) } returns requestBodySpec
        every { requestBodySpec.retrieve() } returns responseSpec
        every { responseSpec.bodyToMono(PokeAPIResponse::class.java) } returns Mono.just(pokeApiResponseMock.pokeApiResponse())

        val actualPokeApi = pokeApi.getByName("blastoise", GameVersion.FIRERED)

        val expectedPokeApi = domainMocks.pokeApi()

        actualPokeApi shouldBe expectedPokeApi
    }

    @Test
    fun `getByName throws PokeApiNotFoundException`() {
        every { restClient.get() } returns requestBodyUriSpec
        every { requestBodyUriSpec.uri(any<URI>()) } returns requestBodySpec
        every { requestBodySpec.retrieve() } returns responseSpec
        every { responseSpec.bodyToMono(PokeAPIResponse::class.java) } throws
                WebClientResponseException(
                    404,
                    HttpStatus.NOT_FOUND.toString(),
                    null,
                    null,
                    null
                )

        shouldThrow<PokeApiNotFoundException> {
            pokeApi.getByName("blastoise", GameVersion.FIRERED)
        }
    }

    @Test
    fun `getByName throws InternalServerErrorException`() {
        every { restClient.get() } returns requestBodyUriSpec
        every { requestBodyUriSpec.uri(any<URI>()) } returns requestBodySpec
        every { requestBodySpec.retrieve() } returns responseSpec
        every { responseSpec.bodyToMono(PokeAPIResponse::class.java) } throws
                WebClientResponseException(
                    408,
                    HttpStatus.REQUEST_TIMEOUT.toString(),
                    null,
                    null,
                    null
                )

        shouldThrow<InternalServerErrorException> {
            pokeApi.getByName("blastoise", GameVersion.FIRERED)
        }
    }
}