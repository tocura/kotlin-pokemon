package com.tocura.study.kotlin.adapter.database.entity

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.model.PokemonTrainer
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "trainer")
class PokemonTrainer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var gender: String,
    var birthDate: Date,
    var gameVersion: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true) @JoinColumn(name = "trainer_id") var pokemons: List<Pokemon>,
) {
    fun toDomain(): PokemonTrainer {
        var pokemons = mutableListOf<com.tocura.study.kotlin.core.model.Pokemon>()

        for (pokemon in this.pokemons) {
            pokemons.add(
                com.tocura.study.kotlin.core.model.Pokemon(
                pokemon.id,
                pokemon.name,
                pokemon.type,
                pokemon.pokedexId,
                pokemon.baseExperience
            ))
        }

        return PokemonTrainer(
            this.id,
            this.gender,
            this.birthDate,
            GameVersion.valueOf(this.gameVersion),
            pokemons
        )
    }

    fun toEntity(trainer: PokemonTrainer): com.tocura.study.kotlin.adapter.database.entity.PokemonTrainer {
        var pokemons = mutableListOf<Pokemon>()

        for (pokemon in trainer.pokemons!!) {
            pokemons.add(Pokemon(
                pokemon.id,
                pokemon.name!!,
                pokemon.type!!,
                pokemon.pokedexId!!,
                pokemon.baseExperience!!
            ))
        }

        return com.tocura.study.kotlin.adapter.database.entity.PokemonTrainer(
            trainer.id,
            trainer.gender!!,
            trainer.birthDate!!,
            trainer.gameVersion.toString(),
            pokemons
        )
    }
}