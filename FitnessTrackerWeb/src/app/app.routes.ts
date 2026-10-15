import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    title: 'Dashboard',
    loadComponent: () => import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent)
  },
  {
    path: 'activity',
    title: 'Activities',
    loadComponent: () => import('./pages/activity/activity.component').then(m => m.ActivityComponent)
  },
  {
    path: 'workout',
    title: 'Workouts',
    loadComponent: () => import('./pages/workout/workout.component').then(m => m.WorkoutComponent)
  },
  {
    path: 'goal',
    title: 'Goals',
    loadComponent: () => import('./pages/goal/goal.component').then(m => m.GoalComponent)
  },
  { path: '**', redirectTo: '' }
];
