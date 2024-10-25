package com.example.sportspredictions.puntingstats

data class PuntingStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var punts: Int = 0,
    var puntYards: Int = 0,
    var longestPunt: Int = 0,
    var grossAvgPuntYards: Double = 0.0,
    var netAvgPuntYards: Double = 0.0,
    var puntsBlocked: Int = 0,
    var puntsInside20: Int = 0,
    var touchBacks: Int = 0,
    var fairCatches: Int = 0,
    var puntsReturned: Int = 0,
    var puntsReturnedYards: Int = 0,
    var avgPuntReturnYards: Double = 0.0
)
