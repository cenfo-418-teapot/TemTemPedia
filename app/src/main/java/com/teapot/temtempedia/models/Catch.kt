package com.teapot.temtempedia.models

import com.google.firebase.firestore.Exclude

data class Catch(
    var id: String? = "",
    val userId: String? = "",
    val temtemIds: MutableList<Int>? = ArrayList()
)
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "user_id" to userId,
            "temtem_ids" to temtemIds,
        )
    }
}
