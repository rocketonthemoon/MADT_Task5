package com.example.madt_task5

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: CurrencyAdapter
    private lateinit var dataList: ArrayList<Currency>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        dataList = ArrayList()

        DataLoader(this).execute()

        val editTextFilter: EditText = findViewById(R.id.editTextFilter)
        editTextFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                adapter.filter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    fun updateData(data: Collection<Currency>) {
        dataList.clear()
        dataList.addAll(data)
        adapter = CurrencyAdapter(this, dataList)
        listView.adapter = adapter
    }
}