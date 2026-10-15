export interface Activity {
  id?: number;
  date: string;
  steps: number;
  distance: number;
  caloriesBurned: number;
}

export interface Workout {
  id?: number;
  type: string;
  date: string;
  duration: number;
  caloriesBurned: number;
}

export interface Goal {
  id?: number;
  description: string;
  startDate: string;
  endDate: string;
  achieved: boolean;
}

export interface Stats {
  achievedGoals: number;
  notAchievedGoals: number;
  totalSteps: number;
  totalDistance: number;
  totalActivityCalories: number;
  totalWorkoutCalories: number;
  totalWorkoutDuration: number;
}
