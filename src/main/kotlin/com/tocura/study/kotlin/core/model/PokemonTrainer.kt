package com.tocura.study.kotlin.core.model

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.enums.Gender
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import java.util.Date

class PokemonTrainer(
    var id: String? = null,
    var gender: Gender,
    var birthDate: String,
    var gameVersion: GameVersion,
    var pokemons: List<Pokemon>,
)