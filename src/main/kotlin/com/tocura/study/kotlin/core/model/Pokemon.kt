package com.tocura.study.kotlin.core.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data

class Pokemon(
    val id: Long? = null,
    var name: String? = null,
    var type: String? = null,
    var pokedexId: Int? = null,
    var baseExperience: Int? = null,
)