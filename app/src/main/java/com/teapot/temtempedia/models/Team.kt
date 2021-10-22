package com.teapot.temtempedia.models

data class Team(
    val id: String? = "",
    val userId: String? = "",
    val title: String? = "New Team",
    val temtemIds: MutableList<Int?> = mutableListOf()
)