package com.example.sportspredictions.passingstats

data class PassingStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var passCompletions: Int = 0,
    var passAttempts: Int = 0,
    var percentComplete: Double = 0.0,
    var passingYards: Int = 0,
    var yardsPerAttempt: Double = 0.0,
    var yardsPerGame: Double = 0.0,
    var longestPass: Int = 0,
    var passingTD: Int = 0,
    var interceptions: Int = 0,
    var totalSacks: Int = 0,
    var sackYardsLost: Int = 0,
    var passerRating: Double = 0.0
)
