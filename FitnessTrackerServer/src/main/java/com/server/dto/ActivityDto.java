package com.server.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record ActivityDto(
        Long id,
        @NotNull LocalDate date,
        @PositiveOrZero int steps,
        @PositiveOrZero double distance,
        @PositiveOrZero int caloriesBurned) {
}
