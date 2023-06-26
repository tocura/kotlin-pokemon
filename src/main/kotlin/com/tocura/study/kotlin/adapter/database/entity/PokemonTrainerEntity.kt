package com.tocura.study.kotlin.adapter.database.entity

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.model.Pokemon
import com.tocura.study.kotlin.core.model.PokemonTrainer
import de.huxhorn.sulky.ulid.ULID
import jakarta.persistence.*
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import java.util.Date

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

    constructor(id: String, gender: String, birthDate: String, gameVersion: GameVersion, pokemons: List<PokemonEntity>): this() {
        this.id = id
        this.gender = gender
        this.birthDate = LocalDate.parse(birthDate)
        this.gameVersion = gameVersion.toString()
        this.pokemons = pokemons
    }

    fun toDomain(): PokemonTrainer {
        val pokemons = mutableListOf<Pokemon>()

        for (pokemon in this.pokemons!!) {
            pokemons.add(Pokemon(
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
            this.birthDate.toString(),
            GameVersion.valueOf(this.gameVersion),
            pokemons.toList()
        )
    }

    fun toEntity(trainer: PokemonTrainer): PokemonTrainerEntity {
        val pokemonEntities = mutableListOf<PokemonEntity>()

        for (pokemon in trainer.pokemons) {
            val pokemonEntity = PokemonEntity()
            pokemonEntities.add(pokemonEntity.toEntity(pokemon))
        }

        return PokemonTrainerEntity(
            trainer.id!!,
            trainer.gender,
            trainer.birthDate,
            trainer.gameVersion,
            pokemonEntities.toList()
        )
    }
}