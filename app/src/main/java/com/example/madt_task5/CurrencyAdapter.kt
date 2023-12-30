package com.example.madt_task5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import java.util.Locale

class CurrencyAdapter(context: Context, private val dataSource: ArrayList<Currency>) : ArrayAdapter<Currency>(context, R.layout.list_item, dataSource),
    Filterable {
    private var filteredData: List<Currency> = dataSource

    override fun getCount(): Int = filteredData.size

    override fun getItem(position: Int): Currency = filteredData[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_item, parent, false)

        val currency = getItem(position)

        val textView = rowView.findViewById<TextView>(R.id.textView)
        textView.text = "${currency.name} - ${currency.rate}"

        return rowView
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()

            if (constraint.isNullOrEmpty()) {
                filterResults.values = dataSource
                filterResults.count = dataSource.size
            } else {
                val filterString = constraint.toString().lowercase(Locale.ROOT)
                val filteredList = dataSource.filter { it.name.lowercase(Locale.ROOT).contains(filterString) }

                filterResults.values = filteredList
                filterResults.count = filteredList.size
            }

            return filterResults
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredData = results?.values as List<Currency>
            notifyDataSetChanged()
        }
    }
}