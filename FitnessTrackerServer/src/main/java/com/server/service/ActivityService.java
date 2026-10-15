package com.server.service;

import com.server.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    ActivityDto postActivity(ActivityDto dto);

    List<ActivityDto> getActivities();
}
