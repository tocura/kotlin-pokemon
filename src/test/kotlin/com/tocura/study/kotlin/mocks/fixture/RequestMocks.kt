package com.tocura.study.kotlin.mocks.fixture

import com.fasterxml.jackson.databind.ObjectMapper
import com.tocura.study.kotlin.adapter.controller.pokemontrainer.request.PatchPokemonsRequest
import com.tocura.study.kotlin.adapter.controller.pokemontrainer.request.PokemonTrainerRequest
import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.enums.Gender

class RequestMocks() {
    fun pokemonTrainerRequest(): String {
        val trainer = PokemonTrainerRequest(
            "name",
            Gender.OTHERS,
            "2000-10-13",
            GameVersion.FIRERED,
            mutableListOf("blastoise")
        )

        return ObjectMapper().writeValueAsString(trainer)
    }

    fun pokemonTrainerBadRequest(): String {
        val trainer = PokemonTrainerRequest(
            "name",
            Gender.OTHERS,
            "13/10/2000",
            GameVersion.FIRERED,
            mutableListOf("blastoise")
        )

        return ObjectMapper().writeValueAsString(trainer)
    }

    fun patchPokemonTrainerRequest(): String {
        val patchPokemonTrainer = PatchPokemonsRequest(
            pokemons = mutableListOf("blastoise")
        )

        return ObjectMapper().writeValueAsString(patchPokemonTrainer)
    }
}