package com.tocura.study.kotlin.core.model

import com.tocura.study.kotlin.core.enums.GameVersion
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import java.util.Date

class PokemonTrainer(
    var id: Long? = null,
    var gender: String? = null,
    var birthDate: Date? = null,
    var gameVersion: GameVersion? = null,
    var pokemons: List<Pokemon>? = null,
)