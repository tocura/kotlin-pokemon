package com.tocura.study.kotlin.adapter.database.entity

import com.tocura.study.kotlin.core.model.Pokemon
import jakarta.persistence.*

@Entity
@Table(name = "pokemon")
class PokemonEntity() {

    @Id
    internal var id: String = ""

    @Column(nullable = false)
    internal var name: String = ""

    @Column(nullable = false)
    internal var type: String = ""

    @Column(name = "pokedex_id", nullable = false)
    internal var pokedexId: Int = 0

    @Column(name = "base_experience", nullable = false)
    internal var baseExperience: Int = 0

    constructor(id: String, name: String, type: String, pokedexId: Int, baseExperiece: Int) : this() {
        this.id = id
        this.name = name
        this.type = type
        this.pokedexId = pokedexId
        this.baseExperience = baseExperiece
    }

    fun toDomain(): Pokemon {
        return Pokemon(this.id, this.name, this.type, this.pokedexId, this.baseExperience)
    }

    fun fromDomain(pokemon: Pokemon): PokemonEntity {
        return PokemonEntity(
            pokemon.id!!,
            pokemon.name,
            pokemon.type,
            pokemon.pokedexId,
            pokemon.baseExperience
        )
    }
}