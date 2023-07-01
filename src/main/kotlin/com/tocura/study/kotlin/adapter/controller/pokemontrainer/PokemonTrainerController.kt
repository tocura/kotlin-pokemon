package com.tocura.study.kotlin.adapter.controller.pokemontrainer

import com.tocura.study.kotlin.adapter.controller.pokemontrainer.presenter.PokemonTrainerResponse
import com.tocura.study.kotlin.adapter.controller.pokemontrainer.request.PatchPokemonsRequest
import com.tocura.study.kotlin.adapter.controller.pokemontrainer.request.PokemonTrainerRequest
import com.tocura.study.kotlin.core.ports.PokemonTrainerService
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pokemon-api/trainer")
class PokemonTrainerController(val pokemonTrainerSrv: PokemonTrainerService) {
    private val log = KotlinLogging.logger{}

    @PostMapping
    fun create(@Valid @RequestBody trainer: PokemonTrainerRequest): ResponseEntity<Any> {
        val trainer = this.pokemonTrainerSrv.create(trainer.toDomain())
        return ResponseEntity(PokemonTrainerResponse().fromDomain(trainer), HttpStatus.CREATED)
    }

    @GetMapping("/{trainer_id}")
    fun findById(@PathVariable("trainer_id") id: String): ResponseEntity<Any> {
        val trainer = this.pokemonTrainerSrv.findById(id)
        return ResponseEntity(PokemonTrainerResponse().fromDomain(trainer), HttpStatus.OK)
    }

    @PatchMapping("/{trainer_id}/pokemons")
    fun patchPokemons(@Valid @RequestBody pokemons: PatchPokemonsRequest, @PathVariable("trainer_id") id: String): ResponseEntity<Any> {
        val trainerUpdated = this.pokemonTrainerSrv.patchPokemons(id, pokemons.pokemons)
        return ResponseEntity(PokemonTrainerResponse().fromDomain(trainerUpdated), HttpStatus.OK)
    }
}