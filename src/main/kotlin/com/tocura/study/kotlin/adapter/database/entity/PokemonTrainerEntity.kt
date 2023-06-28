package com.tocura.study.kotlin.adapter.database.entity

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.enums.Gender
import com.tocura.study.kotlin.core.model.Pokemon
import com.tocura.study.kotlin.core.model.PokemonTrainer
import jakarta.persistence.*
import java.time.LocalDate
@Entity
@Table(name = "trainer")
class PokemonTrainerEntity() {

    @Id
    internal var id: String = ""

    internal var gender: String = ""

    internal var birthDate: LocalDate = LocalDate.now()

    internal var gameVersion: String = ""

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    @JoinColumn(name = "trainer_id")
    internal var pokemons: List<PokemonEntity>? = null

    constructor(id: String, gender: String, birthDate: LocalDate,
                gameVersion: String, pokemons: List<PokemonEntity>) : this() {
        this.id = id
        this.gender = gender
        this.birthDate = birthDate
        this.gameVersion = gameVersion
        this.pokemons = pokemons
    }

    fun toDomain(): PokemonTrainer {
        val pokemons = mutableListOf<Pokemon>()

        for (pokemonEntity in this.pokemons!!) {
            pokemons.add(pokemonEntity.toDomain())
        }

        return PokemonTrainer(
            this.id,
            Gender.valueOf(this.gender),
            this.birthDate.toString(),
            GameVersion.valueOf(this.gameVersion),
            pokemons.toList()
        )
    }

    fun fromDomain(trainer: PokemonTrainer): PokemonTrainerEntity {
        val pokemonEntities = mutableListOf<PokemonEntity>()

        for (pokemon in trainer.pokemons) {
            val pokemonEntity = PokemonEntity()
            pokemonEntities.add(pokemonEntity.fromDomain(pokemon))
        }

        return PokemonTrainerEntity(
            trainer.id!!,
            trainer.gender.toString(),
            LocalDate.parse(trainer.birthDate),
            trainer.gameVersion.toString(),
            pokemonEntities.toList()
        )
    }
}