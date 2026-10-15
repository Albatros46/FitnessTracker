import { Component, OnInit, inject } from '@angular/core';
import { DecimalPipe, DatePipe } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzInputNumberModule } from 'ng-zorro-antd/input-number';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzTableModule } from 'ng-zorro-antd/table';
import { Workout } from '../../models/fitness.models';
import { FitnessService } from '../../services/fitness.service';
import { toApiDate } from '../../services/date.util';

@Component({
  selector: 'app-workout',
  standalone: true,
  imports: [
    ReactiveFormsModule, DatePipe, DecimalPipe,
    NzButtonModule, NzCardModule, NzDatePickerModule, NzFormModule, NzGridModule, NzInputNumberModule,
    NzSelectModule, NzTableModule
  ],
  templateUrl: './workout.component.html'
})
export class WorkoutComponent implements OnInit {
  private readonly fitnessService = inject(FitnessService);
  private readonly message = inject(NzMessageService);

  readonly workoutTypes = ['Running', 'Cycling', 'Swimming', 'Walking', 'Weight Training', 'Yoga', 'HIIT', 'Other'];

  readonly form = inject(FormBuilder).nonNullable.group({
    type: ['', Validators.required],
    date: [new Date(), Validators.required],
    duration: [30, [Validators.required, Validators.min(1)]],
    caloriesBurned: [0, [Validators.required, Validators.min(0)]]
  });

  workouts: Workout[] = [];
  loading = false;
  saving = false;

  ngOnInit(): void {
    this.loadWorkouts();
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const { date, ...rest } = this.form.getRawValue();
    this.saving = true;
    this.fitnessService.postWorkout({ ...rest, date: toApiDate(date) }).subscribe({
      next: () => {
        this.message.success('Workout saved');
        this.form.reset();
        this.saving = false;
        this.loadWorkouts();
      },
      error: () => {
        this.message.error('Could not save workout');
        this.saving = false;
      }
    });
  }

  private loadWorkouts(): void {
    this.loading = true;
    this.fitnessService.getWorkouts().subscribe({
      next: workouts => {
        this.workouts = workouts;
        this.loading = false;
      },
      error: () => {
        this.message.error('Could not load workouts');
        this.loading = false;
      }
    });
  }
}
