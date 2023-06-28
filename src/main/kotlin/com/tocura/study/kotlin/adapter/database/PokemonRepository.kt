package com.tocura.study.kotlin.adapter.database

import com.tocura.study.kotlin.adapter.database.entity.PokemonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository : JpaRepository<PokemonEntity, String>{
}