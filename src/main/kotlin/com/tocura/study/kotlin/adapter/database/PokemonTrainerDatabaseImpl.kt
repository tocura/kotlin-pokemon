package com.tocura.study.kotlin.adapter.database

import com.tocura.study.kotlin.adapter.database.entity.PokemonEntity
import com.tocura.study.kotlin.core.model.PokemonTrainer
import com.tocura.study.kotlin.core.ports.Database
import org.springframework.stereotype.Repository

@Repository
class PokemonTrainerDatabaseImpl(
    private var pokemonTrainerRepo: PokemonTrainerRepository
) : Database {

    override fun save(trainer: PokemonTrainer): PokemonTrainer {
        var pokemonEntities = mutableListOf<PokemonEntity>()

        for (pokemon in trainer.pokemons!!) {
            pokemonEntities.add(
                PokemonEntity(
                pokemon.id,
                pokemon.name!!,
                pokemon.type!!,
                pokemon.pokedexId!!,
                pokemon.baseExperience!!
            )
            )
        }

        var trainerEntity = com.tocura.study.kotlin.adapter.database.entity.PokemonTrainerEntity(
            trainer.id,
            trainer.gender!!,
            trainer.birthDate!!,
            trainer.gameVersion.toString(),
            pokemonEntities
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