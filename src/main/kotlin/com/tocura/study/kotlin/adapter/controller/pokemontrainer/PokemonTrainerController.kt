package com.tocura.study.kotlin.adapter.controller.pokemontrainer

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.enums.Gender
import com.tocura.study.kotlin.core.model.Pokemon
import com.tocura.study.kotlin.core.model.PokemonTrainer
import com.tocura.study.kotlin.core.ports.Database
import de.huxhorn.sulky.ulid.ULID
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemon-api/trainer")
class PokemonTrainerController(val database: Database, val ulid: ULID) {
    private val log = KotlinLogging.logger{}

    @PostMapping
    fun create(): ResponseEntity<Any> {
        return try {
            val pokemon = Pokemon(
                id = this.ulid.nextULID(),
                name = "pikachu",
                type = "eletric",
                pokedexId = 25,
                baseExperience = 112,
            )

            val pokemonTrainer = PokemonTrainer(
                id = this.ulid.nextULID(),
                gender = Gender.MALE,
                birthDate = "2023-06-26",
                gameVersion = GameVersion.FIRERED,
                pokemons = listOf(pokemon),
            )

            this.database.save(pokemonTrainer)
            ResponseEntity("Saved with success", HttpStatus.OK)
        } catch (e: Exception) {
            log.error(e) { "error to save in database" }
            ResponseEntity("error to save in database ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}