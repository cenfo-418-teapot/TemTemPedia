package com.teapot.temtempedia

data class TeamLocal(
    var id: Int,
    var nombre: String,
    var temtemId : Array<Int>
        ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TeamLocal

        if (id != other.id) return false
        if (nombre != other.nombre) return false
        if (!temtemId.contentEquals(other.temtemId)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + nombre.hashCode()
        result = 31 * result + temtemId.contentHashCode()
        return result
    }
}