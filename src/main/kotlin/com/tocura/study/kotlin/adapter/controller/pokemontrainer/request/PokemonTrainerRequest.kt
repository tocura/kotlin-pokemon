package com.tocura.study.kotlin.adapter.controller.pokemontrainer.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.enums.Gender
import com.tocura.study.kotlin.core.model.Pokemon
import com.tocura.study.kotlin.core.model.PokemonTrainer
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

class PokemonTrainerRequest(
    @field:NotNull(message = "name is mandatory")
    @field:NotBlank(message = "name cannot be blank")
    var name: String,

    @field:NotNull(message = "gender is mandatory")
    var gender: Gender,

    @field:NotNull(message = "birthdate is mandatory")
    @field:NotBlank(message = "birthdate cannot be blank")
    @field:Pattern(
        regexp = "^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$",
        message = "birthdate format is yyyy-MM-dd"
    )
    var birthdate: String,

    @field:NotNull(message = "game_version is mandatory")
    @JsonProperty("game_version")
    var gameVersion: GameVersion,

    @field:NotNull(message = "pokemons is mandatory")
    var pokemons: List<String>
) {
    fun toDomain(): PokemonTrainer {
        val pokemons = mutableListOf<Pokemon>()

        for (pokemon in this.pokemons) {
            pokemons.add(Pokemon(
                name = pokemon,
                type = "",
                pokedexId = 0,
                baseExperience = 0
            ))
        }

        return PokemonTrainer(
            name = this.name,
            gender = this.gender,
            gameVersion = this.gameVersion,
            birthdate = this.birthdate,
            pokemons = pokemons
        )
    }
}