package com.example.sportspredictions.rushingstats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.rushingstats.RushingStatsProvider.Companion.getAllRushingPlayerStats
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class RushingStatsTests {
    @Test
    fun getAllRushingPlayerStats_returns_stats() {
        // Arrange
        val tBigsbyRushingStats = RushingStats(
            "T. Bigsby",
            "RB",
            7,
            67,
            415,
            6.2,
            65,
            4,
            4,
            59.3,
            1,
            0,
            17
        )

        val dDuvernayRushingStats = RushingStats(
            "D. Duvernay",
            "WR",
            5,
            3,
            10,
            3.3,
            4,
            0,
            0,
            2.0,
            0,
            0,
            1
        )
        val tables = WebScrapeUtils.getAllResponsiveTables(TestData.rushingResponsiveTableWithTitle)

        // Act
        val testList: List<RushingStats> = getAllRushingPlayerStats(tables)

        // Assert
        assertEquals(6, testList.count())
        assertEquals(testList[0], tBigsbyRushingStats)
        assertEquals(testList[testList.count() - 1], dDuvernayRushingStats)
    }
}