package com.tocura.study.kotlin.core.service

import com.tocura.study.kotlin.core.model.PokemonTrainer
import com.tocura.study.kotlin.core.ports.Database
import com.tocura.study.kotlin.core.ports.PokeApiClient
import com.tocura.study.kotlin.core.ports.PokemonTrainerService
import org.springframework.stereotype.Service

@Service
class PokemonTrainserServiceImpl(
    val database: Database,
    val pokeApiClient: PokeApiClient
): PokemonTrainerService {
    override fun create(trainer: PokemonTrainer): PokemonTrainer {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): PokemonTrainer {
        TODO("Not yet implemented")
    }

    override fun patchPokemons(id: String, pokemons: List<String>): PokemonTrainer {
        TODO("Not yet implemented")
    }
}