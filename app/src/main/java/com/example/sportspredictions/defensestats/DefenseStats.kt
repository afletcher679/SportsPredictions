package com.example.sportspredictions.defensestats

data class DefenseStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var soloTackles: Int = 0,
    var assistedTackles: Int = 0,
    var totalTackles: Int = 0,
    var sacks: Double = 0.0,
    var sackYards: Int = 0,
    var tacklesForLoss: Int = 0,
    var passesDefended: Int = 0,
    var interceptions: Int = 0,
    var interceptionYards: Int = 0,
    var longestInterception: Int = 0,
    var interceptionTD: Int = 0,
    var forcedFumbles: Int = 0,
    var fumbleRecoveries: Int = 0,
    var fumbleTD: Int = 0,
    var kicksBlocked: Int = 0
)
