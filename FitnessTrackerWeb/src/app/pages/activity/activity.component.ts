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
import { NzTableModule } from 'ng-zorro-antd/table';
import { Activity } from '../../models/fitness.models';
import { FitnessService } from '../../services/fitness.service';
import { toApiDate } from '../../services/date.util';

@Component({
  selector: 'app-activity',
  standalone: true,
  imports: [
    ReactiveFormsModule, DatePipe, DecimalPipe,
    NzButtonModule, NzCardModule, NzDatePickerModule, NzFormModule, NzGridModule, NzInputNumberModule, NzTableModule
  ],
  templateUrl: './activity.component.html'
})
export class ActivityComponent implements OnInit {
  private readonly fitnessService = inject(FitnessService);
  private readonly message = inject(NzMessageService);

  readonly form = inject(FormBuilder).nonNullable.group({
    date: [new Date(), Validators.required],
    steps: [0, [Validators.required, Validators.min(0)]],
    distance: [0, [Validators.required, Validators.min(0)]],
    caloriesBurned: [0, [Validators.required, Validators.min(0)]]
  });

  activities: Activity[] = [];
  loading = false;
  saving = false;

  ngOnInit(): void {
    this.loadActivities();
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const { date, ...rest } = this.form.getRawValue();
    this.saving = true;
    this.fitnessService.postActivity({ ...rest, date: toApiDate(date) }).subscribe({
      next: () => {
        this.message.success('Activity saved');
        this.form.reset();
        this.saving = false;
        this.loadActivities();
      },
      error: () => {
        this.message.error('Could not save activity');
        this.saving = false;
      }
    });
  }

  private loadActivities(): void {
    this.loading = true;
    this.fitnessService.getActivities().subscribe({
      next: activities => {
        this.activities = activities;
        this.loading = false;
      },
      error: () => {
        this.message.error('Could not load activities');
        this.loading = false;
      }
    });
  }
}
