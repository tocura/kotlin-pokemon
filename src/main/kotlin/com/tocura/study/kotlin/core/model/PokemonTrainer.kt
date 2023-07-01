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
)