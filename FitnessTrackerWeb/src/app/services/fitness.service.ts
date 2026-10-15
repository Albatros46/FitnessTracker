import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Activity, Goal, Stats, Workout } from '../models/fitness.models';

const BASE_URL = 'http://localhost:8036/api';

@Injectable({ providedIn: 'root' })
export class FitnessService {
  private readonly http = inject(HttpClient);

  postActivity(activity: Activity): Observable<Activity> {
    return this.http.post<Activity>(`${BASE_URL}/activity`, activity);
  }

  getActivities(): Observable<Activity[]> {
    return this.http.get<Activity[]>(`${BASE_URL}/activities`);
  }

  postWorkout(workout: Workout): Observable<Workout> {
    return this.http.post<Workout>(`${BASE_URL}/workout`, workout);
  }

  getWorkouts(): Observable<Workout[]> {
    return this.http.get<Workout[]>(`${BASE_URL}/workouts`);
  }

  postGoal(goal: Omit<Goal, 'achieved'>): Observable<Goal> {
    return this.http.post<Goal>(`${BASE_URL}/goal`, goal);
  }

  getGoals(): Observable<Goal[]> {
    return this.http.get<Goal[]>(`${BASE_URL}/goals`);
  }

  updateGoalStatus(id: number, achieved: boolean): Observable<Goal> {
    return this.http.put<Goal>(`${BASE_URL}/goal/${id}/status`, null, { params: { achieved } });
  }

  getStats(): Observable<Stats> {
    return this.http.get<Stats>(`${BASE_URL}/stats`);
  }
}
