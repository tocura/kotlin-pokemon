package com.tocura.study.kotlin.adapter.controller.pokemontrainer.presenter

import com.fasterxml.jackson.annotation.JsonProperty
import com.tocura.study.kotlin.core.model.Pokemon

class PokemonResponse(
    var id: String,
    var type: String,
    @JsonProperty("pokedex_id") var pokedexId: Int,
    @JsonProperty("base_experience") var baseExperience: Int,
) {
    fun fromDomain(pokemon: Pokemon): PokemonResponse {
        return PokemonResponse(
            id,
            type,
            pokedexId,
            baseExperience
        )
    }
}