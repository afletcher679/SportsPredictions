package com.example.sportspredictions.returningstats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.returningstats.ReturningStatsProvider.Companion.getAllReturningPlayerStats
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class ReturningStatsTests {
    @Test
    fun getAllReturningPlayerStats_returns_stats() {
        // Arrange
        val tBigsbyStats = ReturningStats(
            "T. Bigsby",
            "RB",
            7,
            3,
            102,
            34.0,
            41,
            0,
            0,
            0,
            0.0,
            0,
            0
        )

        val tJonesStats = ReturningStats(
            "T. Jones",
            "WR",
            7,
            1,
            23,
            23.0,
            23,
            0,
            0,
            0,
            0.0,
            0,
            0
        )

        val tables = WebScrapeUtils.getAllResponsiveTables(TestData.returningResponsiveTableWithTitle)

        // Act
        val testList: List<ReturningStats> = getAllReturningPlayerStats(tables)

        // Assert
        assertEquals(4, testList.count())
        assertEquals(testList[0], tBigsbyStats)
        assertEquals(testList[testList.count() - 1], tJonesStats)
    }
}