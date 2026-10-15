package com.server.service;

import com.server.dto.WorkoutDto;
import com.server.entity.Workout;
import com.server.repository.WorkoutRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public WorkoutDto postWorkout(WorkoutDto dto) {
        Workout workout = new Workout();
        workout.setType(dto.type().trim());
        workout.setDate(dto.date());
        workout.setDuration(dto.duration());
        workout.setCaloriesBurned(dto.caloriesBurned());
        return workoutRepository.save(workout).getWorkoutDto();
    }

    @Override
    public List<WorkoutDto> getWorkouts() {
        return workoutRepository.findAll(Sort.by(Sort.Direction.DESC, "date")).stream()
                .map(Workout::getWorkoutDto)
                .toList();
    }
}
