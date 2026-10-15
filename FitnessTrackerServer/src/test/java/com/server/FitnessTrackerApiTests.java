package com.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FitnessTrackerApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsAndListsActivities() throws Exception {
        mockMvc.perform(post("/api/activity").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"date":"2026-10-01","steps":8000,"distance":5.5,"caloriesBurned":320}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.date").value("2026-10-01"));

        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].steps").value(8000));
    }

    @Test
    void rejectsInvalidActivity() throws Exception {
        mockMvc.perform(post("/api/activity").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"steps":-5}
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createsWorkout() throws Exception {
        mockMvc.perform(post("/api/workout").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"type":"Running","date":"2026-10-02","duration":45,"caloriesBurned":400}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("Running"));

        mockMvc.perform(get("/api/workouts"))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void createsGoalAndUpdatesStatus() throws Exception {
        mockMvc.perform(post("/api/goal").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description":"Run 10k","startDate":"2026-10-01","endDate":"2026-11-01"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.achieved").value(false))
                .andExpect(jsonPath("$.dateRangeValid").doesNotExist());

        mockMvc.perform(put("/api/goal/1/status").param("achieved", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.achieved").value(true));

        mockMvc.perform(put("/api/goal/999/status").param("achieved", "true"))
                .andExpect(status().isNotFound());
    }

    @Test
    void rejectsGoalEndingBeforeStart() throws Exception {
        mockMvc.perform(post("/api/goal").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description":"Bad","startDate":"2026-11-01","endDate":"2026-10-01"}
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void aggregatesStats() throws Exception {
        mockMvc.perform(get("/api/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalSteps").value(0));

        mockMvc.perform(post("/api/activity").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"date":"2026-10-01","steps":1000,"distance":1.0,"caloriesBurned":50}
                        """));
        mockMvc.perform(post("/api/workout").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"type":"Yoga","date":"2026-10-01","duration":30,"caloriesBurned":120}
                        """));

        mockMvc.perform(get("/api/stats"))
                .andExpect(jsonPath("$.totalSteps").value(1000))
                .andExpect(jsonPath("$.totalWorkoutDuration").value(30))
                .andExpect(jsonPath("$.totalWorkoutCalories").value(120));
    }

    @Test
    void allowsAngularOrigin() throws Exception {
        mockMvc.perform(options("/api/activities")
                        .header("Origin", "http://localhost:4200")
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:4200"));
    }
}
