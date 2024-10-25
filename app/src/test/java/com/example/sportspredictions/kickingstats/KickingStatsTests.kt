package com.example.sportspredictions.kickingstats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.kickingstats.KickingStatsProvider.Companion.getAllKickingPlayerStats
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class KickingStatsTests {
    @Test
    fun getAllKickingPlayerStats_returns_stats() {
        // Arrange
        val cLittleStats: KickingStats = KickingStats(
            "C. Little",
            "PK",
            7,
            11,
            12,
            91.7,
            53,
            "0-0",
            "4-4",
            "2-2",
            "3-4",
            "2-2",
            14,
            14,
            100.0
        )

        val tables = WebScrapeUtils.getAllResponsiveTables(TestData.kickingResponsiveTableWithTitle)

        // Act
        val testList: List<KickingStats> = getAllKickingPlayerStats(tables)

        // Assert
        assertEquals(1, testList.count())
        assertEquals(testList[0], cLittleStats)
    }
}