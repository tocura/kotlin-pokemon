package com.tocura.study.kotlin.core.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data

@AllArgsConstructor
@Builder
@Data
class Pokemon {
    private var name: String? = null
    private var type: String? = null
    private var pokedexId: Int? = null
    private var baseExperience: Int? = null
}