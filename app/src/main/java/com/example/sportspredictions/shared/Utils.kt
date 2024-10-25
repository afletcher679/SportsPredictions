package com.example.sportspredictions.shared

import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getColumnsFromRow
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement

class Utils {

    companion object{
        fun String.splitNameFromPosition(): Pair<String, String> {
            var name:String = ""
            var result = this.split(" ", limit = 4)
            val position = result.last()
            result = ArrayList(result.dropLast(1))
            result.forEach {
                name += if (it != result.last())
                    "$it "
                else
                    it
            }
            return Pair(name, position)
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