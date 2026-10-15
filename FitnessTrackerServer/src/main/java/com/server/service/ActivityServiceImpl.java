package com.server.service;

import com.server.dto.ActivityDto;
import com.server.entity.Activity;
import com.server.repository.ActivityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public ActivityDto postActivity(ActivityDto dto) {
        Activity activity = new Activity();
        activity.setDate(dto.date());
        activity.setSteps(dto.steps());
        activity.setDistance(dto.distance());
        activity.setCaloriesBurned(dto.caloriesBurned());
        return activityRepository.save(activity).getActivityDto();
    }

    @Override
    public List<ActivityDto> getActivities() {
        return activityRepository.findAll(Sort.by(Sort.Direction.DESC, "date")).stream()
                .map(Activity::getActivityDto)
                .toList();
    }
}
