package com.tocura.study.kotlin.core.ports

import com.tocura.study.kotlin.core.model.PokemonTrainer

interface Database {
    fun save(trainer: PokemonTrainer): PokemonTrainer
    fun findById(id: String): PokemonTrainer?
}