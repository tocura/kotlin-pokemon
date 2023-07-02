package com.tocura.study.kotlin.mocks.fixture

import com.tocura.study.kotlin.adapter.database.entity.PokemonEntity
import com.tocura.study.kotlin.adapter.database.entity.PokemonTrainerEntity
import java.time.LocalDate

class EntityMocks() {

    fun pokemon(): PokemonEntity {
        return PokemonEntity("1", "water", "blastoise", 9, 265)
    }

    fun pokemonTrainer(): PokemonTrainerEntity {
        val date = LocalDate.parse("2000-10-13")
        return PokemonTrainerEntity("2", "name", "OTHERS", date, "FIRERED", mutableListOf(pokemon()))
    }
}