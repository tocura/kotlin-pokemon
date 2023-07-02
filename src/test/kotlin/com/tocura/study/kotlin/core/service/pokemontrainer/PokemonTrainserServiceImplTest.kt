package com.tocura.study.kotlin.core.service.pokemontrainer

import com.tocura.study.kotlin.core.ports.Database
import com.tocura.study.kotlin.core.ports.PokeApiClient
import com.tocura.study.kotlin.core.ports.PokemonTrainerService
import com.tocura.study.kotlin.exceptions.PokeApiNotFoundException
import com.tocura.study.kotlin.exceptions.TrainerNotFoundException
import com.tocura.study.kotlin.mocks.fixture.DomainMocks
import de.huxhorn.sulky.ulid.ULID
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.PersistenceException
import kotlinx.coroutines.sync.Mutex
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonTrainserServiceImplTest {

    private val database: Database = mockk()
    private val pokeApiClient: PokeApiClient = mockk()
    private val mutex: Mutex = Mutex()
    private val ulid: ULID = ULID()

    private val pokemonTrainerSrv: PokemonTrainerService = PokemonTrainerServiceImpl(
        database,
        pokeApiClient,
        mutex,
        ulid
    )

    @Nested
    inner class CreateTest {
        @Test
        fun `create with success`() {
            val domainMock = DomainMocks()

            every { pokeApiClient.getByName(any(), any()) } returns domainMock.pokeApi()
            every { database.save(any()) } returns domainMock.pokemonTrainer()

            val actualPokemonTrainer = pokemonTrainerSrv.create(domainMock.pokemonTrainer())

            val expectedPokemonTrainer = domainMock.pokemonTrainer()

            expectedPokemonTrainer.id = actualPokemonTrainer.id
            expectedPokemonTrainer.pokemons[0].id = actualPokemonTrainer.pokemons[0].id

            actualPokemonTrainer shouldBe expectedPokemonTrainer
        }

        @Test
        fun `create throws PokeApiNotFoundException`() {
            every { pokeApiClient.getByName(any(), any()) } throws NoSuchElementException()

            shouldThrow<PokeApiNotFoundException> {
                pokemonTrainerSrv.create(DomainMocks().pokemonTrainer())
            }
        }

        @Test
        fun `create throws PersistenceException`() {
            val domainMock = DomainMocks()

            every { pokeApiClient.getByName(any(), any()) } returns domainMock.pokeApi()
            every { database.save(any()) } throws PersistenceException()

            shouldThrow<PersistenceException> {
                pokemonTrainerSrv.create(DomainMocks().pokemonTrainer())
            }
        }
    }

    @Nested
    inner class FindByIdTest {
        @Test
        fun `findById success`() {
            val domainMock = DomainMocks()

            every { database.findById(any()) } returns domainMock.pokemonTrainer()

            val actualPokemonTrainer = pokemonTrainerSrv.findById("2")

            val expectedPokemonTrainer = domainMock.pokemonTrainer()

            actualPokemonTrainer shouldBe expectedPokemonTrainer
        }

        @Test
        fun `findById throw TrainerNotFoundException`() {
            every { database.findById(any()) } throws TrainerNotFoundException()

            shouldThrow<TrainerNotFoundException> {
                pokemonTrainerSrv.findById("1")
            }
        }
    }

    @Nested
    inner class PatchPokemonsTest {
        @Test
        fun `patch pokemons with success`() {
            val domainMock = DomainMocks()

            every { database.findById(any()) } returns domainMock.pokemonTrainer()
            every { pokeApiClient.getByName(any(), any()) } returns domainMock.pokeApi()
            every { database.save(any()) } returns domainMock.pokemonTrainer()

            val actualPokemonTrainer = pokemonTrainerSrv.patchPokemons("2", listOf("blastoise"))

            val expectedPokemonTrainer = domainMock.pokemonTrainer()

            actualPokemonTrainer shouldBe expectedPokemonTrainer
        }

        @Test
        fun `patch pokemons throw PokeApiNotFoundException`() {
            val domainMock = DomainMocks()

            every { database.findById(any()) } returns domainMock.pokemonTrainer()
            every { pokeApiClient.getByName(any(), any()) } throws NoSuchElementException()

            shouldThrow<PokeApiNotFoundException> {
                pokemonTrainerSrv.patchPokemons("2", listOf("blastoise"))
            }
        }

        @Test
        fun `patch pokemons throw PersistenceException`() {
            val domainMock = DomainMocks()

            every { database.findById(any()) } returns domainMock.pokemonTrainer()
            every { pokeApiClient.getByName(any(), any()) } returns domainMock.pokeApi()
            every { database.save(any()) } throws PersistenceException()

            shouldThrow<PersistenceException> {
                pokemonTrainerSrv.patchPokemons("2", listOf("blastoise"))
            }
        }
    }
}