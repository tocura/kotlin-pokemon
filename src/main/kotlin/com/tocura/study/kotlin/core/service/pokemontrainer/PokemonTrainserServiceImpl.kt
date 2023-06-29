package com.tocura.study.kotlin.core.service.pokemontrainer

import com.tocura.study.kotlin.core.model.PokemonTrainer
import com.tocura.study.kotlin.core.ports.Database
import com.tocura.study.kotlin.core.ports.PokeApiClient
import com.tocura.study.kotlin.core.ports.PokemonTrainerService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class PokemonTrainserServiceImpl(
    val database: Database,
    val pokeApiClient: PokeApiClient
): PokemonTrainerService {
    private val log = KotlinLogging.logger("core.service.pokemontrainer")
    override fun create(trainer: PokemonTrainer): PokemonTrainer {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): PokemonTrainer {
        return this.database.findById(id)
    }

    override fun patchPokemons(id: String, pokemons: List<String>): PokemonTrainer {
        TODO("Not yet implemented")
    }
}