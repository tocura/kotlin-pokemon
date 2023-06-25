package com.tocura.study.kotlin.core.model

import com.tocura.study.kotlin.core.enums.GameVersion
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import java.util.Date

@AllArgsConstructor
@Builder
@Data
class PokemonTrainer {
    private var id: String? = null
    private var gender: String? = null
    private var birthdate: Date? = null
    private var gameVersion: GameVersion? = null
    private var pokemons: List<Pokemon>? = null
}