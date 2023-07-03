package com.tocura.study.kotlin.mocks.fixture

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.enums.Gender
import com.tocura.study.kotlin.core.model.PokeAPI
import com.tocura.study.kotlin.core.model.Pokemon
import com.tocura.study.kotlin.core.model.PokemonTrainer

class DomainMocks() {
    fun pokemon(): Pokemon {
        return Pokemon("1", "blastoise", "water", 9, 265)
    }

    fun pokemonTrainer(): PokemonTrainer {
        return PokemonTrainer("2", "name", Gender.OTHERS, "2000-10-13", GameVersion.FIRERED, mutableListOf(pokemon()))
    }

    fun pokeApi(): PokeAPI {
        return PokeAPI("blastoise", "water", 9, 265)
    }
}