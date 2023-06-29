package com.tocura.study.kotlin.core.ports

import com.tocura.study.kotlin.core.model.PokemonTrainer

interface PokemonTrainerService {
    fun create(trainer: PokemonTrainer) : PokemonTrainer
    fun findById(id: String): PokemonTrainer
    fun patchPokemons(id: String, pokemons: List<String>): PokemonTrainer
}