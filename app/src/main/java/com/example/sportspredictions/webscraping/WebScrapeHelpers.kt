package com.example.sportspredictions.webscraping

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span
import it.skrape.selects.html5.table
import it.skrape.selects.html5.tbody
import it.skrape.selects.html5.tr

data class PasserStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var passCompletions: Int = 0,
    var passAttempts: Int = 0,
    var percentComplete: Double = 0.0,
    var passingYards: Int = 0,
    var yardsPerAttempt: Double = 0.0,
    var yardsPerGame: Double = 0.0,
    var longestPass: Int = 0,
    var passingTD: Int = 0,
    var interceptions: Int = 0,
    var totalSacks: Int = 0,
    var sackYardsLost: Int = 0,
    var passerRating: Double = 0.0
)

class WebScrape {

    companion object {
        fun getAllResponsiveTables(htmlDoc: String): List<DocElement> {
            return htmlDocument(htmlDoc) {
                "div.ResponsiveTable" {
                    findAll { this }
                }
            }
        }

        fun getResponsiveTableFromTitle(title: String, tables: List<DocElement>): DocElement? {
            return tables.find { table -> table.text.startsWith(title) }
        }

        fun getAllTables(htmlDoc: String): List<DocElement> {
            return htmlDocument(htmlDoc) {
                table {
                    findAll { this }
                }
            }
        }

        fun String.splitNameFromPosition(): Pair<String, String> {
            val result = this.split(" ", limit = 3)
            val name = result[0] + " " + result[1]
            return Pair(name, result[2])
        }

        fun getRowsFromFirstTable(htmlDoc: String): List<DocElement> {
            return htmlDocument(htmlDoc) {
                table {
                    findFirst {
                        tbody {
                            tr {
                                findAll { this }
                            }
                        }
                    }
                }
            }
        }

        fun getAllPassingPlayerStats(pageTables: List<DocElement>): List<PasserStats> {
            val passingTable = getResponsiveTableFromTitle("Passing", pageTables)
            val passingTables =
                getAllTables(getResponsiveTableFromTitle("Passing", pageTables).toString())

            val playerNameTable = passingTables[0]
            val playerStatsTable = passingTables[1]

            val playerList: MutableList<PasserStats> = mutableListOf()

            val nameTableRows = getRowsFromFirstTable(playerNameTable.toString())

            nameTableRows.dropLast(1).mapIndexed { index, tableRow ->
                val nameAndPositionPair: Pair<String, String> = tableRow.span {
                    findFirst { text }
                }.splitNameFromPosition()
                val playerStats = getPasserStats(playerStatsTable, index)
                val player = PasserStats(
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

        fun getColumnsFromRow(row: DocElement): List<DocElement> {
            return row.span {
                findAll {
                    this
                }
            }
        }

        fun getPasserStats(passingStatsTable: DocElement, currentPlayerIndex: Int): List<String> {
            val statsTableRows = getRowsFromFirstTable(passingStatsTable.toString())
            val statsRow = getColumnsFromRow(statsTableRows[currentPlayerIndex])
            val statsList: MutableList<String> = mutableListOf()
            statsRow.forEach { statsList.add(it.text) }
            return statsList
        }

        fun skrapeESPN(): String {
            val html: String = skrape(HttpFetcher) {
                // perform a GET request to the specified URL
                request {
                    url = "https://www.espn.com/nfl/team/stats/_/name/jax/jacksonville-jaguars"
                }

                response {
                    // retrieve the HTML element from the
                    // document as a string
                    htmlDocument {
                        //parsed Doc object is available here
                        html
                    }
                }
            }
            // print the source HTML of the target page
            println(html)

            return html
        }
    }
}
