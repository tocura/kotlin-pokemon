package com.tocura.study.kotlin.adapter.controller.pokemontrainer.presenter

import com.fasterxml.jackson.annotation.JsonProperty
import com.tocura.study.kotlin.core.model.Pokemon

class PokemonResponse() {
    @JsonProperty("id")
    var id: String = ""

    @JsonProperty("name")
    var name: String = ""

    @JsonProperty("type")
    var type: String = ""

    @JsonProperty("pokedex_id")
    var pokedexId: Int = 0

    @JsonProperty("base_experience")
    var baseExperience: Int = 0

    constructor(id: String, name: String, type: String, pokedexId: Int, baseExperience: Int) : this() {
        this.id = id
        this.name = name
        this.type = type
        this.pokedexId = pokedexId
        this.baseExperience = baseExperience
    }

    fun fromDomain(pokemon: Pokemon): PokemonResponse {
        return PokemonResponse(
            pokemon.id!!,
            pokemon.name,
            pokemon.type,
            pokemon.pokedexId,
            pokemon.baseExperience
        )
    }
}