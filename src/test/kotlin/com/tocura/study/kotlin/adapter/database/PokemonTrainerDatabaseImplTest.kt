package com.tocura.study.kotlin.adapter.database

import com.tocura.study.kotlin.core.ports.Database
import com.tocura.study.kotlin.exceptions.TrainerNotFoundException
import com.tocura.study.kotlin.mocks.fixture.DomainMocks
import com.tocura.study.kotlin.mocks.fixture.EntityMocks
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.PersistenceException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonTrainerDatabaseImplTest {

    private val pokemonTrainerRepo: PokemonTrainerRepository = mockk()

    private val pokemonTrainerRepoImpl: Database = PokemonTrainerDatabaseImpl(pokemonTrainerRepo)

    @Nested
    inner class SaveTrainerTest {
        @Test
        fun `save with success`() {
            val entityMock = EntityMocks()
            val domainMock = DomainMocks()
            every { pokemonTrainerRepo.save(any()) } returns entityMock.pokemonTrainer()

            val actualPokemonTrainer = pokemonTrainerRepoImpl.save(domainMock.pokemonTrainer())

            val expectedPokemonTrainer = domainMock.pokemonTrainer()

            actualPokemonTrainer shouldBe expectedPokemonTrainer
        }

        @Test
        fun `save error`() {
            every { pokemonTrainerRepo.save(any()) } throws PersistenceException()

            shouldThrow<PersistenceException> {
                pokemonTrainerRepoImpl.save(DomainMocks().pokemonTrainer())
            }
        }
    }

    @Nested
    inner class FindByIdTest {
        @Test
        fun `findById with success`() {
            val entityMock = EntityMocks()
            val domainMock = DomainMocks()

            every { pokemonTrainerRepo.findById(any()) } returns Optional.of(entityMock.pokemonTrainer())

            val actualPokemonTrainer = pokemonTrainerRepoImpl.findById("2")

            val expectedPokemonTrainer = domainMock.pokemonTrainer()

            actualPokemonTrainer shouldBe expectedPokemonTrainer
        }

        @Test
        fun `findById not found exception`() {
            every { pokemonTrainerRepo.findById(any()) } throws TrainerNotFoundException()

            shouldThrow<TrainerNotFoundException> {
                pokemonTrainerRepoImpl.findById("2")
            }
        }
    }
}