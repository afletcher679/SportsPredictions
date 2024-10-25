package com.example.sportspredictions.passingstats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.passingstats.PassingStatsProvider.Companion.getAllPassingPlayerStats
import com.example.sportspredictions.passingstats.PassingStatsProvider.Companion.getPassingStatsFromTable
import com.example.sportspredictions.passingstats.PassingStatsProvider.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class PassingTests {
    private val tLawrenceStats = PassingStats(
        "T.Lawrence",
        "QB",
        7,
        131,
        211,
        62.1,
        1527,
        7.2,
        218.1,
        85,
        9,
        3,
        15,
        117,
        92.3
    )

    @Test
    fun splitNameAndPosition_returns_split_string() {
        // Arrange
        val testNameAndPosition = "T. Tester QB"

        // Act
        val resultPair = testNameAndPosition.splitNameFromPosition()

        // Assert
        assertEquals(Pair("T. Tester", "QB"), resultPair)
    }

    @Test
    fun getPasserStats_returns_stats() {
        // Arrange
        val mJonesStats = PassingStats(
            "M. Jones",
            "QB",
            2,
            6,
            9,
            66.7,
            28,
            3.1,
            14.0,
            9,
            0,
            0,
            2,
            27,
            70.6
        )
        val tables = WebScrapeUtils.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)

        // Act
        val testList: List<PassingStats> = getAllPassingPlayerStats(tables)

        // Assert
        assertEquals(2, testList.count())
        assertEquals(testList[0], tLawrenceStats)
        assertEquals(testList[1], mJonesStats)
    }

    @Test
    fun getPasserStats_returns_expected_stats() {
        // Arrange
        val expectedStats: List<String> = listOf(
            tLawrenceStats.gamesPlayed.toString(),
            tLawrenceStats.passCompletions.toString(),
            tLawrenceStats.passAttempts.toString(),
            tLawrenceStats.percentComplete.toString(),
            "1,527",
            tLawrenceStats.yardsPerAttempt.toString(),
            tLawrenceStats.yardsPerGame.toString(),
            tLawrenceStats.longestPass.toString(),
            tLawrenceStats.passingTD.toString(),
            tLawrenceStats.interceptions.toString(),
            tLawrenceStats.totalSacks.toString(),
            tLawrenceStats.sackYardsLost.toString(),
            tLawrenceStats.passerRating.toString()
        )

        val responsiveTable =
            WebScrapeUtils.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)[0]
        val statsTable = WebScrapeUtils.getAllTables(responsiveTable.toString())[1]

        // Act
        val results = getPassingStatsFromTable(statsTable, 0)

        // Assert
        assertEquals(expectedStats, results)
    }
}