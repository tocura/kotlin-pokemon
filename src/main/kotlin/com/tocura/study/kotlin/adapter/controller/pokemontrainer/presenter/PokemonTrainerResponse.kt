package com.tocura.study.kotlin.adapter.controller.pokemontrainer.presenter

import com.fasterxml.jackson.annotation.JsonProperty
import com.tocura.study.kotlin.core.model.PokemonTrainer

class PokemonTrainerResponse(
    private var id: String,
    private var gender: String,
    private var birthdate: String,
    @JsonProperty("game_version") private var gameVersion: String,
    private var pokemons: List<PokemonResponse>
) {
    fun fromDomain(trainer: PokemonTrainer): PokemonTrainerResponse {
        val pokemonsResponse = mutableListOf<PokemonResponse>()

        for (pokemon in trainer.pokemons) {
            pokemonsResponse.add(
                PokemonResponse(
                    pokemon.id!!,
                    pokemon.type,
                    pokemon.pokedexId,
                    pokemon.baseExperience
                )
            )
        }

        return PokemonTrainerResponse(id, gender, birthdate, gameVersion, pokemons)
    }
}