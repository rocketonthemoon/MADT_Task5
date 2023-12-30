package com.example.madt_task5

import android.os.AsyncTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DataLoader(private val activity: MainActivity) : AsyncTask<Void, Void, List<Currency>>() {
    override fun doInBackground(vararg params: Void?): List<Currency> {
        val url = URL("http://api.exchangeratesapi.io/v1/latest?access_key=5e7b02ee41a973f96c4621fb32cb9284")
        val connection = url.openConnection() as HttpURLConnection
        val inputStream = connection.inputStream
        val reader = InputStreamReader(inputStream)
        val gson = Gson()
        val type = object : TypeToken<Map<String, Any>>() {}.type
        val response: Map<String, Any> = gson.fromJson(reader, type)
        val rates = response["rates"] as Map<String, Double>
        return rates.map { Currency(it.key, it.value) }
    }

    override fun onPostExecute(result: List<Currency>) {
        super.onPostExecute(result)
        activity.updateData(result)
    }
}