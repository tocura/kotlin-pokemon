package com.tocura.study.kotlin.adapter.controller.pokemontrainer

import com.ninjasquad.springmockk.MockkBean
import com.tocura.study.kotlin.core.ports.PokemonTrainerService
import com.tocura.study.kotlin.exceptions.InternalServerErrorException
import com.tocura.study.kotlin.exceptions.PokeApiNotFoundException
import com.tocura.study.kotlin.exceptions.TrainerNotFoundException
import com.tocura.study.kotlin.mocks.fixture.DomainMocks
import com.tocura.study.kotlin.mocks.fixture.RequestMocks
import com.tocura.study.kotlin.mocks.fixture.ResponseMocks
import io.mockk.every
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@ExtendWith(SpringExtension::class)
@WebMvcTest(PokemonTrainerController::class)
class PokemonTrainerControllerTest(@Autowired var mockMvc: MockMvc) {

    @MockkBean
    private lateinit var pokemonTrainerSrv: PokemonTrainerService

    @Nested
    inner class PostCreateTrainerTest {
        @Test
        fun `POST create success 201`() {
            val requestMock = RequestMocks()
            val domainMock = DomainMocks()
            val responseMock = ResponseMocks()

            every { pokemonTrainerSrv.create(any()) } returns domainMock.pokemonTrainer()

            mockMvc.post("/pokemon-api/trainer") {
                contentType = MediaType.APPLICATION_JSON
                content = requestMock.pokemonTrainerRequest()
            }.andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(responseMock.pokemonTrainerResponse()) }
            }
        }

        @Test
        fun `POST create error invalid body request 400`() {
            val requestMock = RequestMocks()

            mockMvc.post("/pokemon-api/trainer") {
                contentType = MediaType.APPLICATION_JSON
                content = requestMock.pokemonTrainerBadRequest()
            }.andExpect {
                status { isBadRequest() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }

        @Test
        fun `POST create pokemon trainer returns 500`() {
            val requestMock = RequestMocks()

            every { pokemonTrainerSrv.create(any()) } throws InternalServerErrorException()

            mockMvc.post("/pokemon-api/trainer") {
                contentType = MediaType.APPLICATION_JSON
                content = requestMock.pokemonTrainerRequest()
            }.andExpect {
                status { isInternalServerError() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }

        @Test
        fun `POST create pokemon trainer returns 404`() {
            val requestMock = RequestMocks()

            every { pokemonTrainerSrv.create(any()) } throws PokeApiNotFoundException()

            mockMvc.post("/pokemon-api/trainer") {
                contentType = MediaType.APPLICATION_JSON
                content = requestMock.pokemonTrainerRequest()
            }.andExpect {
                status { isNotFound() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }
    }

    @Nested
    inner class GetPokemonTrainerTest {
        @Test
        fun `GET pokemon trainer with success 200`() {
            val domainMock = DomainMocks()
            val responseMock = ResponseMocks()

            every { pokemonTrainerSrv.findById(any()) } returns domainMock.pokemonTrainer()

            mockMvc.get("/pokemon-api/trainer/2")
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    content { json(responseMock.pokemonTrainerResponse()) }
                }
        }

        @Test
        fun `GET pokemon trainer return error 404`() {
            every { pokemonTrainerSrv.findById(any()) } throws TrainerNotFoundException()

            mockMvc.get("/pokemon-api/trainer/2")
                .andExpect {
                    status { isNotFound() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }
    }

    @Nested
    inner class PatchPokemonTrainer {
        @Test
        fun `PATCH pokemon trainer with success 200`() {
            val requestMock = RequestMocks()
            val domainMock = DomainMocks()
            val responseMock = ResponseMocks()

            every { pokemonTrainerSrv.patchPokemons(any(), any()) } returns domainMock.pokemonTrainer()

            mockMvc.patch("/pokemon-api/trainer/2/pokemons") {
                contentType = MediaType.APPLICATION_JSON
                content = requestMock.patchPokemonTrainerRequest()
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(responseMock.pokemonTrainerResponse()) }
            }
        }

        @Test
        fun `PATCH pokemon trainer returns error 404`() {
            val requestMock = RequestMocks()

            every { pokemonTrainerSrv.patchPokemons(any(), any()) } throws TrainerNotFoundException()

            mockMvc.patch("/pokemon-api/trainer/2/pokemons") {
                contentType = MediaType.APPLICATION_JSON
                content = requestMock.patchPokemonTrainerRequest()
            }.andExpect {
                status { isNotFound() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }
    }
}