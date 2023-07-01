package com.tocura.study.kotlin.adapter.controller.pokemontrainer.presenter

import com.fasterxml.jackson.annotation.JsonProperty
import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.model.PokemonTrainer

class PokemonTrainerResponse() {

    @JsonProperty("id")
    private var id: String = ""

    @JsonProperty("name")
    private var name: String = ""

    @JsonProperty("gender")
    private var gender: String = ""

    @JsonProperty("birthdate")
    private var birthdate: String = ""

    @JsonProperty("game_version")
    private var gameVersion: String = ""

    @JsonProperty("pokemons")
    private var pokemons: List<PokemonResponse> = emptyList()
    constructor(id: String, name: String, gender: String, birthdate: String, gameVersion: String, pokemons: List<PokemonResponse>) : this() {
        this.id = id
        this.name = name
        this.gender = gender
        this.birthdate = birthdate
        this.gameVersion = gameVersion
        this.pokemons = pokemons
    }

    fun fromDomain(trainer: PokemonTrainer): PokemonTrainerResponse {
        val pokemonsResponse = mutableListOf<PokemonResponse>()

        for (pokemon in trainer.pokemons) {
            pokemonsResponse.add(PokemonResponse().fromDomain(pokemon))
        }

        return PokemonTrainerResponse(
            trainer.id!!,
            trainer.name,
            trainer.gender.toString(),
            trainer.birthdate,
            trainer.gameVersion.toString(),
            pokemonsResponse
        )
    }
}