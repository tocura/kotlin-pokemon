package com.tocura.study.kotlin.core.model

class Pokemon(
    var id: String? = null,
    var name: String,
    var type: String,
    var pokedexId: Int,
    var baseExperience: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Pokemon)  {
            return false
        }

        val pokemon = other as Pokemon

        return pokemon.id == this.id && pokemon.name == this.name
                && pokemon.type == this.type && pokemon.pokedexId == this.pokedexId
                && pokemon.baseExperience == this.baseExperience
    }
}