package com.example.sportspredictions.rushingstats

import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class RushingStatsProvider {
    companion object {
        fun getAllRushingPlayerStats(pageTables: List<DocElement>): List<RushingStats> {
            val rushingTables =
                getAllTables(getResponsiveTableFromTitle("Rushing", pageTables).toString())

            val playerNameTable = rushingTables[0]
            val playerStatsTable = rushingTables[1]

            val playerList: MutableList<RushingStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()

                val playerStats = getStatsFromTable(playerStatsTable, index)
                val player = RushingStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    rushAttempts = playerStats[1].toInt(),
                    rushYards = playerStats[2].toInt(),
                    yardsPerAttempt = playerStats[3].toDouble(),
                    longestRush = playerStats[4].toInt(),
                    playsWith20PlusYards = playerStats[5].toInt(),
                    rushTD = playerStats[6].toInt(),
                    rushYardsPerGame = playerStats[7].toDouble(),
                    rushFumbles = playerStats[8].toInt(),
                    rushFumblesLost = playerStats[9].toInt(),
                    rushFirstDowns = playerStats[10].toInt()
                )
                playerList.add(player)
            }
            return playerList
        }
    }
}