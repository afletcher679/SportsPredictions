package com.example.sportspredictions.passingstats

import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.removeCommaFromNumberString
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class PassingStatsProvider {

    companion object {
        fun getAllPassingPlayerStats(pageTables: List<DocElement>): List<PassingStats> {
            val passingTables =
                getAllTables(getResponsiveTableFromTitle("Passing", pageTables).toString())

            val playerNameTable = passingTables[0]
            val playerStatsTable = passingTables[1]

            val playerList: MutableList<PassingStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()

                val playerStats = getStatsFromTable(playerStatsTable, index)
                val player = PassingStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    passCompletions = playerStats[1].toInt(),
                    passAttempts = playerStats[2].toInt(),
                    percentComplete = playerStats[3].toDouble(),
                    passingYards = playerStats[4].removeCommaFromNumberString().toInt(),
                    yardsPerAttempt = playerStats[5].toDouble(),
                    yardsPerGame = playerStats[6].toDouble(),
                    longestPass = playerStats[7].toInt(),
                    passingTD = playerStats[8].toInt(),
                    interceptions = playerStats[9].toInt(),
                    totalSacks = playerStats[10].toInt(),
                    sackYardsLost = playerStats[11].toInt(),
                    passerRating = playerStats[12].toDouble()
                )
                playerList.add(player)
            }
            return playerList
        }
    }
}