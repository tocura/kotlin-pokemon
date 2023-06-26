package com.tocura.study.kotlin.core.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data

class Pokemon(
    val id: String? = null,
    var name: String,
    var type: String,
    var pokedexId: Int,
    var baseExperience: Int,
)