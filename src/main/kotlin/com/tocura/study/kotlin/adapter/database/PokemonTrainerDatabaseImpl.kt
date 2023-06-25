package com.tocura.study.kotlin.adapter.database

import com.tocura.study.kotlin.adapter.database.entity.Pokemon
import com.tocura.study.kotlin.core.model.PokemonTrainer
import com.tocura.study.kotlin.core.ports.Database
import org.springframework.stereotype.Repository

@Repository
class PokemonTrainerDatabaseImpl(
    private var pokemonTrainerRepo: PokemonTrainerRepository
) : Database {

    override fun save(trainer: PokemonTrainer): PokemonTrainer {
        var pokemons = mutableListOf<Pokemon>()

        for (pokemon in trainer.pokemons!!) {
            pokemons.add(
                Pokemon(
                pokemon.id,
                pokemon.name!!,
                pokemon.type!!,
                pokemon.pokedexId!!,
                pokemon.baseExperience!!
            )
            )
        }

        var trainerEntity = com.tocura.study.kotlin.adapter.database.entity.PokemonTrainer(
            trainer.id,
            trainer.gender!!,
            trainer.birthDate!!,
            trainer.gameVersion.toString(),
            pokemons
        )

        this.pokemonTrainerRepo.save(trainerEntity)

        return trainer
    }

    override fun findById(id: Long): PokemonTrainer? {
        var trainerEntity = this.pokemonTrainerRepo.findById(id)

        if (trainerEntity.isPresent) {
            return trainerEntity.get().toDomain()
        }

        // TODO: throw custom exception of trainer not found
        return null
    }

}