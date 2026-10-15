package com.server.controller;

import com.server.dto.ActivityDto;
import com.server.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/activity")
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityDto postActivity(@Valid @RequestBody ActivityDto dto) {
        return activityService.postActivity(dto);
    }

    @GetMapping("/activities")
    public List<ActivityDto> getActivities() {
        return activityService.getActivities();
    }
}
