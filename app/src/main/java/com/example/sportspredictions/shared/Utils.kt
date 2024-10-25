package com.example.sportspredictions.shared

import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getColumnsFromRow
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement

class Utils {

    companion object{
        fun String.splitNameFromPosition(): Pair<String, String> {
            val result = this.split(" ", limit = 3)
            val name = result[0] + " " + result[1]
            return Pair(name, result[2])
        }

        fun String.removeCommaFromNumberString(): String {
            return this.replace(",", "")
        }

        fun getStatsFromTable(
            statsTable: DocElement,
            currentPlayerIndex: Int
        ): List<String> {
            val statsTableRows = getRowsFromFirstTable(statsTable.toString())
            val statsRow = getColumnsFromRow(statsTableRows[currentPlayerIndex])
            val statsList: MutableList<String> = mutableListOf()
            statsRow.forEach { statsList.add(it.text) }
            return statsList
        }
    }
}