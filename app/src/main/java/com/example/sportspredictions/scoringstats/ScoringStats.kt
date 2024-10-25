package com.example.sportspredictions.scoringstats

data class ScoringStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var rushTDs: Int = 0,
    var receivingTDs: Int = 0,
    var returnTDs: Int = 0,
    var totalTDs: Int = 0,
    var fieldGoals: Int = 0,
    var kickExtraPoints: Int = 0,
    var total2PointConversions: Int = 0,
    var totalPoints: Int = 0,
    var totalPointsPerGame: Double = 0.0
)
