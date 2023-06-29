package com.tocura.study.kotlin.core.ports

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.model.PokeAPI

interface PokeAPIClient {
    fun GetByName(name: String, gameVersion: GameVersion): PokeAPI
}