package com.server.repository;

import com.server.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT COALESCE(SUM(a.steps), 0) FROM Activity a")
    long sumSteps();

    @Query("SELECT COALESCE(SUM(a.distance), 0) FROM Activity a")
    double sumDistance();

    @Query("SELECT COALESCE(SUM(a.caloriesBurned), 0) FROM Activity a")
    long sumCaloriesBurned();
}
