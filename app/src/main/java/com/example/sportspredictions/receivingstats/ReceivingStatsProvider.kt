package com.example.sportspredictions.receivingstats

import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class ReceivingStatsProvider {
    companion object {
        fun getAllReceivingPlayerStats(pageTables: List<DocElement>): List<ReceivingStats> {
            val receivingTables =
                getAllTables(getResponsiveTableFromTitle("Receiving", pageTables).toString())

            val playerNameTable = receivingTables[0]
            val playerStatsTable = receivingTables[1]

            val playerList: MutableList<ReceivingStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()

                val playerStats = getStatsFromTable(playerStatsTable, index)
                val player = ReceivingStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    receptions = playerStats[1].toInt(),
                    receivingTargets = playerStats[2].toInt(),
                    receivingYards = playerStats[3].toInt(),
                    yardsPerReception = playerStats[4].toDouble(),
                    receivingTD = playerStats[5].toInt(),
                    longestReception = playerStats[6].toInt(),
                    playsWith20PlusYards = playerStats[7].toInt(),
                    receivingYardsPerGame = playerStats[8].toDouble(),
                    receivingFumbles = playerStats[9].toInt(),
                    receivingFumblesLost = playerStats[10].toInt(),
                    receivingYardsAfterCatch = playerStats[11].toInt(),
                    receivingFirstDowns = playerStats[12].toInt()
                )
                playerList.add(player)
            }
            return playerList
        }
    }
}