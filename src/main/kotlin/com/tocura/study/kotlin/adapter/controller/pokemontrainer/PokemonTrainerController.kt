package com.tocura.study.kotlin.adapter.controller.pokemontrainer

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.enums.Gender
import com.tocura.study.kotlin.core.model.Pokemon
import com.tocura.study.kotlin.core.model.PokemonTrainer
import com.tocura.study.kotlin.core.ports.Database
import com.tocura.study.kotlin.core.ports.PokemonTrainerService
import de.huxhorn.sulky.ulid.ULID
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pokemon-api/trainer")
class PokemonTrainerController(val pokemonTrainerSrv: PokemonTrainerService) {
    private val log = KotlinLogging.logger{}

    @PostMapping
    fun create(): ResponseEntity<Any> {
        val pokemons = listOf<Pokemon>(
            Pokemon(
                name = "pikachu",
                type = "",
                pokedexId = 0,
                baseExperience = 0
            ),
            Pokemon(
                name = "charizard",
                type = "",
                pokedexId = 0,
                baseExperience = 0
            )
        )

        val trainer = PokemonTrainer(
            gender = Gender.MALE,
            birthdate = "2000-11-02",
            gameVersion = GameVersion.FIRERED,
            pokemons = pokemons
        )

        return ResponseEntity(this.pokemonTrainerSrv.create(trainer), HttpStatus.CREATED)
    }

    @GetMapping("/{trainer_id}")
    fun findById(@PathVariable("trainer_id") id: String): ResponseEntity<Any> {
        return ResponseEntity(this.pokemonTrainerSrv.findById(id), HttpStatus.OK)
    }
}