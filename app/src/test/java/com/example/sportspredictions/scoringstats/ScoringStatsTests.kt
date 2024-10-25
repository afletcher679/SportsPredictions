package com.example.sportspredictions.scoringstats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.scoringstats.ScoringStatsProvider.Companion.getAllScoringPlayerStats
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoringStatsTests {
    @Test
    fun getAllScoringPlayerStats_returns_stats() {
        // Arrange
        val cLittleStats=ScoringStats(
            "C. Little",
            "PK",
            7,
            0,
            0,
            0,
            0,
            11,
            14,
            0,
            47,
            6.7
        )

        val pWashingtonStats=ScoringStats(
            "P. Washington",
            "WR",
            7,
            0,
            0,
            1,
            1,
            0,
            0,
            0,
            6,
            0.9
        )

        val tables = WebScrapeUtils.getAllResponsiveTables(TestData.scoringResponsiveTableWithTitle)

        // Act
        val testList: List<ScoringStats> = getAllScoringPlayerStats(tables)

        // Assert
        assertEquals(8, testList.count())
        assertEquals(testList[0], cLittleStats)
        assertEquals(testList[testList.count() - 1], pWashingtonStats)
    }
}