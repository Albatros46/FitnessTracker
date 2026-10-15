package com.server.controller;

import com.server.dto.WorkoutDto;
import com.server.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/workout")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutDto postWorkout(@Valid @RequestBody WorkoutDto dto) {
        return workoutService.postWorkout(dto);
    }

    @GetMapping("/workouts")
    public List<WorkoutDto> getWorkouts() {
        return workoutService.getWorkouts();
    }
}
