package com.tocura.study.kotlin.core.ports

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.model.PokeAPI

interface PokeApiClient {
    fun GetByName(name: String, gameVersion: GameVersion): PokeAPI
}