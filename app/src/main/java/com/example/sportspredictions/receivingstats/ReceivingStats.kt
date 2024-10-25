package com.example.sportspredictions.receivingstats

data class ReceivingStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var receptions: Int = 0,
    var receivingTargets: Int = 0,
    var receivingYards: Int = 0,
    var yardsPerReception: Double = 0.0,
    var receivingTD: Int = 0,
    var longestReception: Int = 0,
    var playsWith20PlusYards: Int = 0,
    var receivingYardsPerGame: Double = 0.0,
    var receivingFumbles: Int = 0,
    var receivingFumblesLost: Int = 0,
    var receivingYardsAfterCatch: Int = 0,
    var receivingFirstDowns: Int = 0
)
