package com.example.sportspredictions.puntingstats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.puntingstats.PuntingStatsProvider.Companion.getAllPuntingPlayerStats
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class PuntingStatsTests {
    @Test
    fun getAllPuntingPlayerStats_returns_stats() {
        // Arrange
        val lCookeStats = PuntingStats(
            "L. Cooke",
            "P",
            7,
            24,
            1194,
            67,
            49.8,
            44.9,
            0,
            12,
            0,
            6,
            13,
            117,
            9.0
        )

        val tables = WebScrapeUtils.getAllResponsiveTables(TestData.puntingResponsiveTableWithTitle)

        // Act
        val testList: List<PuntingStats> = getAllPuntingPlayerStats(tables)

        // Assert
        assertEquals(1, testList.count())
        assertEquals(testList[0], lCookeStats)
    }
}