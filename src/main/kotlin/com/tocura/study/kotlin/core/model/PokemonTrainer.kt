package com.tocura.study.kotlin.core.model

import com.tocura.study.kotlin.core.enums.GameVersion
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import java.util.Date

class PokemonTrainer(
    var id: String? = null,
    var gender: String,
    var birthDate: Date,
    var gameVersion: GameVersion,
    var pokemons: List<Pokemon>,
)