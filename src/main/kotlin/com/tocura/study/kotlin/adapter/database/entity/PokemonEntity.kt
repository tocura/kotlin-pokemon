package com.tocura.study.kotlin.adapter.database.entity

import com.tocura.study.kotlin.core.model.Pokemon
import de.huxhorn.sulky.ulid.ULID
import jakarta.persistence.*
import org.springframework.beans.factory.annotation.Autowired

@Entity
@Table(name = "pokemon")
class PokemonEntity() {

    @Id
    internal var id: String = ""

    internal var name: String = ""

    internal var type: String = ""

    @Column(name = "pokedex_id")
    internal var pokedexId: Int = 0

    @Column(name = "base_experience")
    internal var baseExperience: Int = 0

    @Autowired
    private lateinit var ulid: ULID

    constructor(id: String, name: String, type: String, pokedexId: Int, baseExperience: Int) : this() {
        this.id = id
        this.name = name
        this.type = type
        this.pokedexId = pokedexId
        this.baseExperience = baseExperience
    }

    fun toDomain(): Pokemon {
        return Pokemon(this.id, this.name, this.type, this.pokedexId, this.baseExperience)
    }

    fun toEntity(pokemon: Pokemon): PokemonEntity {
        val id = this.ulid.nextULID()
        return PokemonEntity(
            id,
            pokemon.name,
            pokemon.type,
            pokemon.pokedexId,
            pokemon.baseExperience,
        )
    }
}