package com.tocura.study.kotlin.adapter.controller.pokemontrainer

import com.tocura.study.kotlin.core.enums.GameVersion
import com.tocura.study.kotlin.core.ports.PokeApiClient
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

    @GetMapping("/{trainer_id}")
    fun findById(@PathVariable("trainer_id") id: String): ResponseEntity<Any> {
        return ResponseEntity(this.pokemonTrainerSrv.findById(id), HttpStatus.OK)
    }
}