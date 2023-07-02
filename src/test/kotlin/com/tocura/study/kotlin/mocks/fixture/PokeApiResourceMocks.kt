package com.tocura.study.kotlin.mocks.fixture

import com.tocura.study.kotlin.adapter.external.pokeapi.resource.*

class PokeApiResourceMocks() {

    fun pokeApiResponse(): PokeAPIResponse {
        val pokemonType = PokemonTypesResponse(PokemonTypeResponse("water"))
        val gameIndice = GameIndicesResponse(9, GameVersionResponse("firered"))

        return PokeAPIResponse(
            "blastoise",
            mutableListOf(pokemonType),
            mutableListOf(gameIndice),
            265
        )
    }
}