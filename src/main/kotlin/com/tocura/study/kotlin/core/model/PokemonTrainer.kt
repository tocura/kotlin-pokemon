package com.tocura.study.kotlin.core.model

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.enums.Gender

class PokemonTrainer(
    var id: String? = null,
    var name: String,
    var gender: Gender,
    var birthdate: String,
    var gameVersion: GameVersion,
    var pokemons: List<Pokemon>,
) {
    override fun equals(other: Any?): Boolean {
        if (other !is PokemonTrainer) {
            return false
        }

        val trainer = other as PokemonTrainer

        return this.id == trainer.id && this.name == trainer.name && this.gender == trainer.gender
                && this.birthdate == trainer.birthdate && this.gameVersion == trainer.gameVersion
                && this.pokemons == trainer.pokemons
    }
}