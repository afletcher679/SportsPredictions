package com.example.sportspredictions.defensestats

import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class DefenseStatsProvider {
    companion object {
        fun getAllDefensePlayerStats(pageTables: List<DocElement>): List<DefenseStats> {
            val defenseTables =
                getAllTables(getResponsiveTableFromTitle("Defense", pageTables).toString())

            val playerNameTable = defenseTables[0]
            val playerStatsTable = defenseTables[1]

            val playerList: MutableList<DefenseStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()

                val playerStats = getStatsFromTable(playerStatsTable, index)
                val player = DefenseStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    soloTackles = playerStats[1].toInt(),
                    assistedTackles = playerStats[2].toInt(),
                    totalTackles = playerStats[3].toInt(),
                    sacks = playerStats[4].toDouble(),
                    sackYards = playerStats[5].toInt(),
                    tacklesForLoss = playerStats[6].toInt(),
                    passesDefended = playerStats[7].toInt(),
                    interceptions = playerStats[8].toInt(),
                    interceptionYards = playerStats[9].toInt(),
                    longestInterception = playerStats[10].toInt(),
                    interceptionTD = playerStats[11].toInt(),
                    forcedFumbles = playerStats[12].toInt(),
                    fumbleRecoveries = playerStats[13].toInt(),
                    fumbleTD = playerStats[14].toInt(),
                    kicksBlocked = playerStats[15].toInt()
                )
                playerList.add(player)
            }
            return playerList
        }
    }
}