package com.tocura.study.kotlin.core.enums

enum class GameVersion {
    RED,
    BLUE,
    YELLOW,
    GOLD,
    SILVER,
    CRYSTAL,
    RUBY,
    SAPPHIRE,
    EMERALD,
    FIRERED,
    LEAFGREEN,
    DIAMOND,
    PEARL,
    PLATINUM,
    HEARTGOLD,
    SOULSILVER,
    BLACK,
    WHITE;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}