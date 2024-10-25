package com.example.sportspredictions.puntingstats

import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.removeCommaFromNumberString
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getAllTables
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getResponsiveTableFromTitle
import com.example.sportspredictions.webscraping.WebScrapeUtils.Companion.getRowsFromFirstTable
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span

class PuntingStatsProvider {
    companion object {
        fun getAllPuntingPlayerStats(pageTables: List<DocElement>): List<PuntingStats> {
            val puntingTables =
                getAllTables(getResponsiveTableFromTitle("Punting", pageTables).toString())

            val playerNameTable = puntingTables[0]
            val playerStatsTable = puntingTables[1]

            val playerList: MutableList<PuntingStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()

                val playerStats = getStatsFromTable(playerStatsTable, index)
                val player = PuntingStats(
                    playerName = nameAndPositionPair.first,
                    position = nameAndPositionPair.second,
                    gamesPlayed = playerStats[0].toInt(),
                    punts = playerStats[1].toInt(),
                    puntYards = playerStats[2].removeCommaFromNumberString().toInt(),
                    longestPunt = playerStats[3].toInt(),
                    grossAvgPuntYards = playerStats[4].toDouble(),
                    netAvgPuntYards = playerStats[5].toDouble(),
                    puntsBlocked = playerStats[6].toInt(),
                    puntsInside20 = playerStats[7].toInt(),
                    touchBacks = playerStats[8].toInt(),
                    fairCatches = playerStats[9].toInt(),
                    puntsReturned = playerStats[10].toInt(),
                    puntsReturnedYards = playerStats[11].removeCommaFromNumberString().toInt(),
                    avgPuntReturnYards = playerStats[12].toDouble()
                )
                playerList.add(player)
            }
            return playerList
        }
    }
}