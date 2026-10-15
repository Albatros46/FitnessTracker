package com.server.service;

import com.server.dto.GoalDto;

import java.util.List;

public interface GoalService {
    GoalDto postGoal(GoalDto dto);

    List<GoalDto> getGoals();

    GoalDto updateStatus(Long id, boolean achieved);
}
