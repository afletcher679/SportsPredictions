package com.example.sportspredictions.receivingstats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.receivingstats.ReceivingStatsProvider.Companion.getAllReceivingPlayerStats
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class ReceivingStatsTests {
    @Test
    fun getAllReceivingPlayerStats_returns_stats() {
        // Arrange
        val bThomasStats = ReceivingStats(
            "B. Thomas Jr.",
            "WR",
            7,
            30,
            45,
            513,
            17.1,
            4,
            85,
            5,
            73.3,
            0,
            0,
            191,
            21
        )

        val tLawrenceStats = ReceivingStats(
            "T. Lawrence",
            "QB",
            7,
            0,
            0,
            -5,
            0.0,
            0,
            -5,
            0,
            -0.7,
            1,
            0,
            -5,
            0
        )
        val tables =
            WebScrapeUtils.getAllResponsiveTables(TestData.receivingResponsiveTableWithTitle)

        // Act
        val testList: List<ReceivingStats> = getAllReceivingPlayerStats(tables)

        // Assert
        assertEquals(13, testList.count())
        assertEquals(testList[0], bThomasStats)
        assertEquals(testList[testList.count() - 1], tLawrenceStats)
    }
}