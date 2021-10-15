package com.teapot.temtempedia

data class Temtem(
    var id: Int,
    var nombre: String,
    var ratioCaptura: Int,
    var porcentajeM: Float,
    var porcentajeF: Float,
    var minutosEclosion: Int,
    var fotoNormal: String,
    var fotoLuma: String,
    var tipos: List<String>,
    var stats: Stats,
    var fortalezas: Fortalezas,
    var rasgos: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Temtem

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
