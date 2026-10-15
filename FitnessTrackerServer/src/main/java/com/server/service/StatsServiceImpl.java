package com.server.service;

import com.server.dto.StatsDto;
import com.server.repository.ActivityRepository;
import com.server.repository.GoalRepository;
import com.server.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatsServiceImpl implements StatsService {
    private final ActivityRepository activityRepository;
    private final WorkoutRepository workoutRepository;
    private final GoalRepository goalRepository;

    public StatsServiceImpl(ActivityRepository activityRepository,
                            WorkoutRepository workoutRepository,
                            GoalRepository goalRepository) {
        this.activityRepository = activityRepository;
        this.workoutRepository = workoutRepository;
        this.goalRepository = goalRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public StatsDto getStats() {
        return new StatsDto(
                goalRepository.countByAchieved(true),
                goalRepository.countByAchieved(false),
                activityRepository.sumSteps(),
                activityRepository.sumDistance(),
                activityRepository.sumCaloriesBurned(),
                workoutRepository.sumCaloriesBurned(),
                workoutRepository.sumDuration());
    }
}
