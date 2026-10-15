package com.server.service;

import com.server.dto.GoalDto;
import com.server.entity.Goal;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.GoalRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public GoalDto postGoal(GoalDto dto) {
        Goal goal = new Goal();
        goal.setDescription(dto.description().trim());
        goal.setStartDate(dto.startDate());
        goal.setEndDate(dto.endDate());
        goal.setAchieved(false);
        return goalRepository.save(goal).getGoalDto();
    }

    @Override
    public List<GoalDto> getGoals() {
        return goalRepository.findAll(Sort.by(Sort.Direction.ASC, "endDate")).stream()
                .map(Goal::getGoalDto)
                .toList();
    }

    @Override
    @Transactional
    public GoalDto updateStatus(Long id, boolean achieved) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goal not found: " + id));
        goal.setAchieved(achieved);
        return goal.getGoalDto();
    }
}
