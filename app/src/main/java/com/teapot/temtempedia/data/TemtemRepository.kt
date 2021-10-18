package com.teapot.temtempedia.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teapot.temtempedia.models.Temtem
import java.io.IOException

fun getAllTemtems(context: Context): List<Temtem> {
    val jsonFileString = getJsonDataFromAsset(context, "TemtemSource.json")
    val gson = Gson()
    val listJson = object : TypeToken<List<Temtem>>() {}.type
    return gson.fromJson(jsonFileString, listJson)
}

private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}
