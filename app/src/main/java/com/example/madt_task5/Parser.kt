package com.example.madt_task5

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONObject
import java.io.InputStream

class Parser {
    fun parse(inputStream: InputStream): List<String> {
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)
        val rates = jsonObject.getJSONObject("rates")
        val result = ArrayList<String>()
        for (key in rates.keys()) {
            val value = rates.getDouble(key)
            result.add("$key - $value")
        }
        return result
    }
}