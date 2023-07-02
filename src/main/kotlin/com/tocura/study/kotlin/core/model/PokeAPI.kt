package com.tocura.study.kotlin.core.model

class PokeAPI(
    var name: String,
    var type: String,
    var pokedexId: Int,
    var baseExperience: Int,
) {

    override fun equals(other: Any?): Boolean {
        if (other !is PokeAPI) {
            return false
        }

        val pokeApi = other as PokeAPI

        return this.name == pokeApi.name && this.type == pokeApi.type
                && this.pokedexId == pokeApi.pokedexId && this.baseExperience == pokeApi.baseExperience
    }
}