package com.example.sportspredictions.kickingstats

import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class KickingStatsProvider {
    companion object {
        fun getAllKickingPlayerStats(pageTables: List<DocElement>): List<KickingStats> {
            val kickingTables =
                getAllTables(getResponsiveTableFromTitle("Kicking", pageTables).toString())

            val playerNameTable = kickingTables[0]
            val playerStatsTable = kickingTables[1]

            val playerList: MutableList<KickingStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()

                val playerStats = getStatsFromTable(playerStatsTable, index)
                val player = KickingStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    fieldGoalsMade = playerStats[1].toInt(),
                    fieldGoalsAttempts = playerStats[2].toInt(),
                    fieldGoalPercentage = playerStats[3].toDouble(),
                    fieldGoalsLongestMade = playerStats[4].toInt(),
                    fieldGoals1To19 = playerStats[5],
                    fieldGoals20To29 = playerStats[6],
                    fieldGoals30To39 = playerStats[7],
                    fieldGoals40To49 = playerStats[8],
                    fieldGoals50Plus = playerStats[9],
                    extraPointsMade = playerStats[10].toInt(),
                    extraPointsAttempted = playerStats[11].toInt(),
                    extraPointPercentage = playerStats[12].toDouble()
                )
                playerList.add(player)
            }
            return playerList
        }
    }
}