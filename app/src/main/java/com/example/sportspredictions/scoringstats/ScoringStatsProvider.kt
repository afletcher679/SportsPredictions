package com.example.sportspredictions.scoringstats

import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class ScoringStatsProvider {
    companion object {
        fun getAllScoringPlayerStats(pageTables: List<DocElement>): List<ScoringStats> {
            val scoringTables =
                getAllTables(getResponsiveTableFromTitle("Scoring", pageTables).toString())

            val playerNameTable = scoringTables[0]
            val playerStatsTable = scoringTables[1]

            val playerList: MutableList<ScoringStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()

                val playerStats = getStatsFromTable(playerStatsTable, index)
                val player = ScoringStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    rushTDs = playerStats[1].toInt(),
                    receivingTDs = playerStats[2].toInt(),
                    returnTDs = playerStats[3].toInt(),
                    totalTDs = playerStats[4].toInt(),
                    fieldGoals = playerStats[5].toInt(),
                    kickExtraPoints = playerStats[6].toInt(),
                    total2PointConversions = playerStats[7].toInt(),
                    totalPoints = playerStats[8].toInt(),
                    totalPointsPerGame = playerStats[9].toDouble()
                )
                playerList.add(player)
            }
            return playerList
        }
    }
}