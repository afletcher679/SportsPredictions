package com.example.sportspredictions.defensestats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.defensestats.DefenseStatsProvider.Companion.getAllDefensePlayerStats
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class DefenseStatsTests {
    @Test
    fun getAllDefensePlayerStats_returns_stats() {
        // Arrange
        val vMillerStats = DefenseStats(
            "V. Miller",
            "LB",
            7,
            33,
            14,
            47,
            0.0,
            0,
            2,
            1,
            0,
            0,
            0,
            0,
            1,
            0,
            0,
            0
        )
        val lCookeStats = DefenseStats(
            "L. Cooke",
            "P",
            7,
            1,
            0,
            1,
            0.0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        )
        val tables = WebScrapeUtils.getAllResponsiveTables(TestData.defenseResponsiveTableWithTitle)

        // Act
        val testList: List<DefenseStats> = getAllDefensePlayerStats(tables)

        // Assert
        assertEquals(37, testList.count())
        assertEquals(testList[0], vMillerStats)
        assertEquals(testList[testList.count()-1], lCookeStats)
    }
}