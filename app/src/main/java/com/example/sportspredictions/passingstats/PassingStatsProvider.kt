package com.example.sportspredictions.passingstats

import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getColumnsFromRow
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class PassingStatsProvider {

    companion object {
        fun getPassingStatsFromTable(
            passingStatsTable: DocElement,
            currentPlayerIndex: Int
        ): List<String> {
            val statsTableRows = getRowsFromFirstTable(passingStatsTable.toString())
            val statsRow = getColumnsFromRow(statsTableRows[currentPlayerIndex])
            val statsList: MutableList<String> = mutableListOf()
            statsRow.forEach { statsList.add(it.text) }
            return statsList
        }

        fun String.splitNameFromPosition(): Pair<String, String> {
            val result = this.split(" ", limit = 3)
            val name = result[0] + " " + result[1]
            return Pair(name, result[2])
        }

        fun getAllPassingPlayerStats(pageTables: List<DocElement>): List<PassingStats> {
            val passingTable = getResponsiveTableFromTitle("Passing", pageTables)
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
                val playerStats = getPassingStatsFromTable(playerStatsTable, index)
                val player = PassingStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    passCompletions = playerStats[1].toInt(),
                    passAttempts = playerStats[2].toInt(),
                    percentComplete = playerStats[3].toDouble(),
                    passingYards = playerStats[4].toInt(),
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