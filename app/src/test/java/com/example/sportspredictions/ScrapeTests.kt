package com.example.sportspredictions

import com.example.sportspredictions.TestData.Companion.removeSpacesBetweenBraces
import com.example.sportspredictions.webscraping.PasserStats
import com.example.sportspredictions.webscraping.WebScrape
import com.example.sportspredictions.webscraping.WebScrape.Companion.splitNameFromPosition
import org.junit.Test

import org.junit.Assert.*


class ScrapeTests {
    val tLawrenceStats = PasserStats(
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
    fun scrapeESPN_returns_string() {
        // Act
        val result = WebScrape.skrapeESPN()

        // Assert
        assertNotEquals(result.isEmpty() || result.isBlank(), result)
    }

    @Test
    fun getRowsFromFirstTable_returns_correct_row_count() {
        // Act
        val rows = WebScrape.getRowsFromFirstTable(TestData.oneTableString)

        // Assert
        assertEquals(3, rows.count())
    }

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
        val mJonesStats = PasserStats(
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
        val tables = WebScrape.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)

        // Act
        val testList: List<PasserStats> = WebScrape.getAllPassingPlayerStats(tables)

        // Assert
        assertEquals(2, testList.count())
        assertEquals(testList[0], tLawrenceStats)
        assertEquals(testList[1], mJonesStats)
    }

    @Test
    fun getAllTables_returns_correct_table_count() {
        // Act
        val tables = WebScrape.getAllTables(TestData.twoTablesString)

        // Assert
        assertEquals(2, tables.count())
    }

    @Test
    fun getAllResponsiveTables_returns_correct_table_count() {
        // Act
        val tables = WebScrape.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)

        // Assert
        assertEquals(1, tables.count())
    }

    @Test
    fun getResponsiveTableFromTitle_returns_correct_table() {
        // Arrange
        val tableTitle = "Passing"
        val twoTableString =
            TestData.passingResponsiveTableWithTitle + TestData.rushingResponsiveTableWithTitle
        val tables = WebScrape.getAllResponsiveTables(twoTableString)

        // Act
        val table = WebScrape.getResponsiveTableFromTitle(tableTitle, tables)
        val selectedTableString = table.toString().removeSpacesBetweenBraces()
            .replace(">\\s+${tableTitle}\\s+<".toRegex(), ">${tableTitle}<")

        // Assert
        assertEquals(2, tables.count())
        assertEquals(
            TestData.passingResponsiveTableWithTitle.removeSpacesBetweenBraces(),
            selectedTableString
        )
    }

    @Test
    fun getRowsFromFirstTable_fromDocElement_returns_rows() {
        // Arrange
        val table = WebScrape.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)[0]

        // Act
        val rows = WebScrape.getRowsFromFirstTable(table.toString())

        // Assert
        assertEquals(3, rows.count())
    }

    @Test
    fun getColumnsFromRow_returns_correct_column_count() {
        // Arrange
        val responsiveTable =
            WebScrape.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)[0]
        val statsTable = WebScrape.getAllTables(responsiveTable.toString())[1]
        val row = WebScrape.getRowsFromFirstTable(statsTable.toString())[0]

        // Act
        val columns = WebScrape.getColumnsFromRow(row)

        // Assert
        assertEquals(13, columns.count())
    }

    @Test
    fun getPasserStats_returns_expected_stats() {
        // Arrange
        val expectedStats:List<String> = listOf(
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
            WebScrape.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)[0]
        val statsTable = WebScrape.getAllTables(responsiveTable.toString())[1]

        val results = WebScrape.getPasserStats(statsTable, 0)
        assertEquals(expectedStats,results)
    }
}