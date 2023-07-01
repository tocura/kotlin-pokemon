package com.tocura.study.kotlin.core.service.pokemontrainer

import com.tocura.study.kotlin.core.model.PokemonTrainer
import com.tocura.study.kotlin.core.ports.Database
import com.tocura.study.kotlin.core.ports.PokeApiClient
import com.tocura.study.kotlin.core.ports.PokemonTrainerService
import com.tocura.study.kotlin.exceptions.PokeApiNotFoundException
import de.huxhorn.sulky.ulid.ULID
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Service
class PokemonTrainerServiceImpl(
    val database: Database,
    val pokeApiClient: PokeApiClient,
    val mutex: Mutex,
    val ulid: ULID
): PokemonTrainerService {
    private val log = KotlinLogging.logger("core.service.pokemontrainer")

    override fun create(trainer: PokemonTrainer): PokemonTrainer {
        trainer.id = this.ulid.nextULID()

        runBlocking {
            asyncEnrichPokemonInfo(trainer)
        }

        return this.database.save(trainer)
    }

    override fun findById(id: String): PokemonTrainer {
        return this.database.findById(id)
    }

    override fun patchPokemons(id: String, pokemons: List<String>): PokemonTrainer {
        TODO("Not yet implemented")
    }

    suspend fun asyncEnrichPokemonInfo(trainer: PokemonTrainer) = coroutineScope {
        val deferredPokemonsInfo = mutableListOf<Deferred<Any>>()
        for (pokemon in trainer.pokemons) {
            deferredPokemonsInfo.add(async {
                getPokemonInfo(pokemon.name, trainer)
            })
        }
        deferredPokemonsInfo.awaitAll()
    }

    suspend fun getPokemonInfo(pokemonName: String, trainer: PokemonTrainer) {
        try {
            val pokemonData = this.pokeApiClient.getByName(pokemonName, trainer.gameVersion)

            mutex.withLock {
                val pokemon = trainer.pokemons.first{ it.name == pokemonName }
                pokemon.id = this.ulid.nextULID()
                pokemon.type = pokemonData.type
                pokemon.pokedexId = pokemonData.pokedexId
                pokemon.baseExperience = pokemonData.baseExperience
            }
        } catch (e: NoSuchElementException) {
            log.error(e) {"pokemon $pokemonName does not exists in game version ${trainer.gameVersion}"}
            throw PokeApiNotFoundException("pokemon $pokemonName does not exists in game version ${trainer.gameVersion}")
        }
    }
}