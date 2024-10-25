package com.example.sportspredictions.rushingstats

data class RushingStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var rushAttempts: Int = 0,
    var rushYards: Int = 0,
    var yardsPerAttempt: Double = 0.0,
    var longestRush: Int = 0,
    var playsWith20PlusYards: Int = 0,
    var rushTD: Int = 0,
    var rushYardsPerGame: Double = 0.0,
    var rushFumbles: Int = 0,
    var rushFumblesLost: Int = 0,
    var rushFirstDowns: Int = 0
)
