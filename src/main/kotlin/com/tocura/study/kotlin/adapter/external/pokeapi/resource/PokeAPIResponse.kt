package com.tocura.study.kotlin.adapter.external.pokeapi.resource

import com.fasterxml.jackson.annotation.JsonProperty
import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.model.PokeAPI

class PokeAPIResponse(
    var name: String,
    var types: List<PokemonTypesResponse>,
    @JsonProperty("game_indices") var gameIndices: List<GameIndicesResponse>,
    @JsonProperty("base_experience") var baseExperience: Int
) {
    fun toDomain(gameVersion: GameVersion): PokeAPI {
        val gameIndiceFiltered = this.gameIndices.first { it.version.name == gameVersion.toString().lowercase() }

        return PokeAPI(
            name = this.name,
            type = this.types[0].type.name,
            pokedexId = gameIndiceFiltered.pokedexId,
            baseExperience = this.baseExperience,
        )
    }
}

class PokemonTypesResponse(
    var type: PokemonTypeResponse
)

class PokemonTypeResponse(
    var name: String
)

class GameIndicesResponse(
    @JsonProperty("game_index") var pokedexId: Int,
    var version: GameVersionResponse
)

class GameVersionResponse(
    var name: String,
)