package com.server.controller;

import com.server.dto.GoalDto;
import com.server.service.GoalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping("/goal")
    @ResponseStatus(HttpStatus.CREATED)
    public GoalDto postGoal(@Valid @RequestBody GoalDto dto) {
        return goalService.postGoal(dto);
    }

    @GetMapping("/goals")
    public List<GoalDto> getGoals() {
        return goalService.getGoals();
    }

    @PutMapping("/goal/{id}/status")
    public GoalDto updateStatus(@PathVariable Long id, @RequestParam boolean achieved) {
        return goalService.updateStatus(id, achieved);
    }
}
