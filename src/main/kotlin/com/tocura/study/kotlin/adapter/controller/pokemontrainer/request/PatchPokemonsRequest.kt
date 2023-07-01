package com.tocura.study.kotlin.adapter.controller.pokemontrainer.request

import jakarta.validation.constraints.NotNull

class PatchPokemonsRequest(
    @field:NotNull
    var pokemons: List<String>
) {
}