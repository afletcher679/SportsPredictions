package com.example.sportspredictions.shared

import com.example.sportspredictions.TestData
import com.example.sportspredictions.shared.Utils.Companion.getStatsFromTable
import com.example.sportspredictions.shared.Utils.Companion.splitNameFromPosition
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTests {

    @Test
    fun splitNameAndPosition_with2Spaces_returns_expectedPair() {
        // Arrange
        val testNameAndPosition = "T. Tester QB"

        // Act
        val resultPair = testNameAndPosition.splitNameFromPosition()

        // Assert
        assertEquals(Pair("T. Tester", "QB"), resultPair)
    }

    @Test
    fun splitNameAndPosition_with3Spaces_returns_expectedPair() {
        // Arrange
        val testNameAndPosition = "T. Tester Jr. QB"

        // Act
        val resultPair = testNameAndPosition.splitNameFromPosition()

        // Assert
        assertEquals(Pair("T. Tester Jr.", "QB"), resultPair)
    }

    @Test
    fun getStatsFromTables_returns_expectedPassingStats() {
        // Arrange
        val expectedStats: List<String> = listOf(
            TestData.tLawrencePassingStats.gamesPlayed.toString(),
            TestData.tLawrencePassingStats.passCompletions.toString(),
            TestData.tLawrencePassingStats.passAttempts.toString(),
            TestData.tLawrencePassingStats.percentComplete.toString(),
            "1,527",
            TestData.tLawrencePassingStats.yardsPerAttempt.toString(),
            TestData.tLawrencePassingStats.yardsPerGame.toString(),
            TestData.tLawrencePassingStats.longestPass.toString(),
            TestData.tLawrencePassingStats.passingTD.toString(),
            TestData.tLawrencePassingStats.interceptions.toString(),
            TestData.tLawrencePassingStats.totalSacks.toString(),
            TestData.tLawrencePassingStats.sackYardsLost.toString(),
            TestData.tLawrencePassingStats.passerRating.toString()
        )

        val responsiveTable =
            WebScrapeUtils.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)[0]
        val statsTable = WebScrapeUtils.getAllTables(responsiveTable.toString())[1]

        // Act
        val results = getStatsFromTable(statsTable, 0)

        // Assert
        assertEquals(expectedStats, results)
    }
}