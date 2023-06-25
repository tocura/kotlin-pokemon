package com.tocura.study.kotlin.adapter.database.entity

import com.tocura.study.kotlin.core.model.Pokemon
import jakarta.persistence.*

@Entity
@Table(name = "pokemon")
class Pokemon(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var name: String,
    var type: String,
    @Column(name = "pokedex_id") var pokedexId: Int,
    @Column(name = "base_experience") var baseExperience: Int
) {
    fun toDomain(): Pokemon {
        return Pokemon(this.id, this.name, this.type, this.pokedexId, this.baseExperience)
    }

    fun toEntity(pokemon: Pokemon): com.tocura.study.kotlin.adapter.database.entity.Pokemon {
        return com.tocura.study.kotlin.adapter.database.entity.Pokemon(
            pokemon.id,
            pokemon.name!!,
            pokemon.type!!,
            pokemon.pokedexId!!,
            pokemon.baseExperience!!,
        )
    }
}