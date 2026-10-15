package com.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record WorkoutDto(
        Long id,
        @NotBlank String type,
        @NotNull LocalDate date,
        @Positive int duration,
        @PositiveOrZero int caloriesBurned) {
}
