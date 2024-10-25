package com.example.sportspredictions.webscraping

import com.example.sportspredictions.TestData
import com.example.sportspredictions.TestData.Companion.removeSpacesBetweenBraces
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test


class ScrapeTests {

    @Test
    fun scrapeUrl_returns_string() {
        val url: String = "https://www.espn.com/nfl/team/stats/_/name/jax/jacksonville-jaguars"
        // Act
        val result = WebScrapeUtils.skrapeUrl(url)

        // Assert
        assertNotEquals(result.isEmpty() || result.isBlank(), result)
    }

    @Test
    fun getRowsFromFirstTable_returns_correct_row_count() {
        // Act
        val rows = WebScrapeUtils.getRowsFromFirstTable(TestData.oneTableString)

        // Assert
        assertEquals(3, rows.count())
    }

    @Test
    fun getAllTables_returns_correct_table_count() {
        // Act
        val tables = WebScrapeUtils.getAllTables(TestData.twoTablesString)

        // Assert
        assertEquals(2, tables.count())
    }

    @Test
    fun getAllResponsiveTables_returns_correct_table_count() {
        // Act
        val tables = WebScrapeUtils.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)

        // Assert
        assertEquals(1, tables.count())
    }

    @Test
    fun getResponsiveTableFromTitle_returns_correct_table() {
        // Arrange
        val tableTitle = "Passing"
        val twoTableString =
            TestData.passingResponsiveTableWithTitle + TestData.rushingResponsiveTableWithTitle
        val tables = WebScrapeUtils.getAllResponsiveTables(twoTableString)

        // Act
        val table = WebScrapeUtils.getResponsiveTableFromTitle(tableTitle, tables)
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
        val table = WebScrapeUtils.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)[0]

        // Act
        val rows = WebScrapeUtils.getRowsFromFirstTable(table.toString())

        // Assert
        assertEquals(3, rows.count())
    }

    @Test
    fun getColumnsFromRow_returns_correct_column_count() {
        // Arrange
        val responsiveTable =
            WebScrapeUtils.getAllResponsiveTables(TestData.passingResponsiveTableWithTitle)[0]
        val statsTable = WebScrapeUtils.getAllTables(responsiveTable.toString())[1]
        val row = WebScrapeUtils.getRowsFromFirstTable(statsTable.toString())[0]

        // Act
        val columns = WebScrapeUtils.getColumnsFromRow(row)

        // Assert
        assertEquals(13, columns.count())
    }
}