package com.server.service;

import com.server.dto.WorkoutDto;

import java.util.List;

public interface WorkoutService {
    WorkoutDto postWorkout(WorkoutDto dto);

    List<WorkoutDto> getWorkouts();
}
