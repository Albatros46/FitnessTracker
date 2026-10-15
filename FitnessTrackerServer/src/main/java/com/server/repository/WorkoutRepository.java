package com.server.repository;

import com.server.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    @Query("SELECT COALESCE(SUM(w.duration), 0) FROM Workout w")
    long sumDuration();

    @Query("SELECT COALESCE(SUM(w.caloriesBurned), 0) FROM Workout w")
    long sumCaloriesBurned();
}
