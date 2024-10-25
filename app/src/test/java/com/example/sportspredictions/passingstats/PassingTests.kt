package com.example.sportspredictions.passingstats

import com.example.sportspredictions.TestData
import com.example.sportspredictions.passingstats.PassingStatsProvider.Companion.getAllPassingPlayerStats
import com.example.sportspredictions.webscraping.WebScrapeUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class PassingTests {

    @Test
    fun getAllPassingPlayerStats_returns_stats() {
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
        assertEquals(testList[0], TestData.tLawrencePassingStats)
        assertEquals(testList[1], mJonesStats)
    }
}