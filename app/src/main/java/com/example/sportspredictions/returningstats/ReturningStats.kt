package com.example.sportspredictions.returningstats

data class ReturningStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var kickReturns: Int = 0,
    var kickReturnYards: Int = 0,
    var avgKickReturnYards: Double = 0.0,
    var longestKickReturn: Int = 0,
    var kickReturnTD: Int = 0,
    var puntReturns: Int = 0,
    var puntReturnYards: Int = 0,
    var avgPuntReturnYards: Double = 0.0,
    var longestPuntReturn: Int = 0,
    var puntReturnTD: Int = 0,
    var puntReturnFairCatches: Int = 0
)
