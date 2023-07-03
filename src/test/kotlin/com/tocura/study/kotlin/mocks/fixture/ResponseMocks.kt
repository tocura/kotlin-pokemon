package com.tocura.study.kotlin.mocks.fixture

import com.fasterxml.jackson.databind.ObjectMapper
import com.tocura.study.kotlin.adapter.controller.pokemontrainer.presenter.PokemonResponse
import com.tocura.study.kotlin.adapter.controller.pokemontrainer.presenter.PokemonTrainerResponse

class ResponseMocks() {
    fun pokemonTrainerResponse(): String {
        val pokemons = mutableListOf<PokemonResponse>(
            PokemonResponse(
                id = "1",
                name = "blastoise",
                type = "water",
                pokedexId = 9,
                baseExperience = 265
            )
        )

        val trainer = PokemonTrainerResponse(
            id = "2",
            name = "name",
            gender = "OTHERS",
            birthdate = "2000-10-13",
            gameVersion = "FIRERED",
            pokemons  = pokemons
        )

        return ObjectMapper().writeValueAsString(trainer)
    }
}