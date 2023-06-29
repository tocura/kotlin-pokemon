package com.tocura.study.kotlin.adapter.database

import com.tocura.study.kotlin.adapter.database.entity.PokemonTrainerEntity
import com.tocura.study.kotlin.core.model.PokemonTrainer
import com.tocura.study.kotlin.core.ports.Database
import com.tocura.study.kotlin.exceptions.TrainerNotFoundException
import org.springframework.stereotype.Repository

@Repository
class PokemonTrainerDatabaseImpl(
    private var pokemonTrainerRepo: PokemonTrainerRepository
) : Database {

    override fun save(trainer: PokemonTrainer): PokemonTrainer {
        val pokemonTrainerEntity = PokemonTrainerEntity()
        this.pokemonTrainerRepo.save(pokemonTrainerEntity.fromDomain(trainer))
        return trainer
    }

    override fun findById(id: String): PokemonTrainer {
        var trainerEntity = this.pokemonTrainerRepo.findById(id)

        if (trainerEntity.isPresent) {
            return trainerEntity.get().toDomain()
        }

        throw TrainerNotFoundException()
    }

}