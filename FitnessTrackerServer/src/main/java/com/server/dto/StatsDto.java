package com.server.dto;

public record StatsDto(
        long achievedGoals,
        long notAchievedGoals,
        long totalSteps,
        double totalDistance,
        long totalActivityCalories,
        long totalWorkoutCalories,
        long totalWorkoutDuration) {
}
