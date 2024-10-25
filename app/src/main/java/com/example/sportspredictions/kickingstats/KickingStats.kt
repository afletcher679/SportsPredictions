package com.example.sportspredictions.kickingstats

data class KickingStats(
    val playerName: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var fieldGoalsMade: Int = 0,
    var fieldGoalsAttempts: Int = 0,
    var fieldGoalPercentage: Double = 0.0,
    var fieldGoalsLongestMade: Int = 0,
    var fieldGoals1To19: String = "0-0",
    var fieldGoals20To29: String = "0-0",
    var fieldGoals30To39: String = "0-0",
    var fieldGoals40To49: String = "0-0",
    var fieldGoals50Plus: String = "0-0",
    var extraPointsMade: Int = 0,
    var extraPointsAttempted: Int = 0,
    var extraPointPercentage: Double = 0.0
)