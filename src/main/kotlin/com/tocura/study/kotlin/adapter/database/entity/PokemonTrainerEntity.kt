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

    @Column(nullable = false)
    internal var name: String = ""

    @Column(nullable = false)
    internal var gender: String = ""

    @Column(nullable = false)
    internal var birthdate: LocalDate = LocalDate.now()

    @Column(name = "game_version", nullable = false)
    internal var gameVersion: String = ""

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    @JoinColumn(name = "trainer_id", nullable = false)
    internal var pokemons: List<PokemonEntity>? = null

    constructor(id: String, name: String, gender: String, birthDate: LocalDate,
                gameVersion: String, pokemons: List<PokemonEntity>) : this() {
        this.id = id
        this.name = name
        this.gender = gender
        this.birthdate = birthDate
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
            this.name,
            Gender.valueOf(this.gender),
            this.birthdate.toString(),
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
            trainer.name,
            trainer.gender.toString(),
            LocalDate.parse(trainer.birthdate),
            trainer.gameVersion.toString(),
            pokemonEntities.toList()
        )
    }
}