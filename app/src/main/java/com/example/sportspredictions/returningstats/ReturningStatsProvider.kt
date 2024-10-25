package com.example.sportspredictions.returningstats

import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class ReturningStatsProvider {
    companion object {
        fun getAllReturningPlayerStats(pageTables: List<DocElement>): List<ReturningStats> {
            val returningTables =
                getAllTables(getResponsiveTableFromTitle("Returning", pageTables).toString())

            val playerNameTable = returningTables[0]
            val playerStatsTable = returningTables[1]

            val playerList: MutableList<ReturningStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()

                val playerStats = getStatsFromTable(playerStatsTable, index)
                val player = ReturningStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    kickReturns = playerStats[1].toInt(),
                    kickReturnYards = playerStats[2].toInt(),
                    avgKickReturnYards = playerStats[3].toDouble(),
                    longestKickReturn = playerStats[4].toInt(),
                    kickReturnTD = playerStats[5].toInt(),
                    puntReturns = playerStats[6].toInt(),
                    puntReturnYards = playerStats[7].toInt(),
                    avgPuntReturnYards = playerStats[8].toDouble(),
                    longestPuntReturn = playerStats[9].toInt(),
                    puntReturnTD = playerStats[10].toInt(),
                    puntReturnFairCatches = playerStats[11].toInt()
                )
                playerList.add(player)
            }
            return playerList
        }
    }
}