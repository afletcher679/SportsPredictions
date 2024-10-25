package com.example.sportspredictions.webscraping

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.DocElement
import it.skrape.selects.html5.span
import it.skrape.selects.html5.table
import it.skrape.selects.html5.tbody
import it.skrape.selects.html5.tr

class WebScrapeUtils {

    companion object {
        fun getAllResponsiveTables(htmlDoc: String): List<DocElement> {
            return htmlDocument(htmlDoc) {
                "div.ResponsiveTable" {
                    findAll { this }
                }
            }
        }

        fun getResponsiveTableFromTitle(title: String, tables: List<DocElement>): DocElement? {
            return tables.find { table -> table.text.startsWith(title) }
        }

        fun getAllTables(htmlDoc: String): List<DocElement> {
            return htmlDocument(htmlDoc) {
                table {
                    findAll { this }
                }
            }
        }

        fun getRowsFromFirstTable(htmlDoc: String): List<DocElement> {
            return htmlDocument(htmlDoc) {
                table {
                    findFirst {
                        tbody {
                            tr {
                                findAll { this }
                            }
                        }
                    }
                }
            }
        }

        fun getColumnsFromRow(row: DocElement): List<DocElement> {
            return row.span {
                findAll {
                    this
                }
            }
        }

        fun skrapeUrl(urlToScrape:String): String {
            val html: String = skrape(HttpFetcher) {
                // perform a GET request to the specified URL
                request {
                    url = urlToScrape
                }

                response {
                    // retrieve the HTML element from the
                    // document as a string
                    htmlDocument {
                        //parsed Doc object is available here
                        html
                    }
                }
            }

            return html
        }
    }
}
