import { Component, OnInit, inject } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzTagModule } from 'ng-zorro-antd/tag';
import { Goal } from '../../models/fitness.models';
import { FitnessService } from '../../services/fitness.service';
import { toApiDate } from '../../services/date.util';

@Component({
  selector: 'app-goal',
  standalone: true,
  imports: [
    ReactiveFormsModule, DatePipe,
    NzButtonModule, NzCardModule, NzDatePickerModule, NzFormModule, NzGridModule, NzIconModule, NzInputModule,
    NzTableModule, NzTagModule
  ],
  templateUrl: './goal.component.html'
})
export class GoalComponent implements OnInit {
  private readonly fitnessService = inject(FitnessService);
  private readonly message = inject(NzMessageService);

  readonly form = inject(FormBuilder).nonNullable.group({
    description: ['', [Validators.required, Validators.maxLength(255)]],
    range: [[] as Date[], Validators.required]
  });

  goals: Goal[] = [];
  loading = false;
  saving = false;

  ngOnInit(): void {
    this.loadGoals();
  }

  submit(): void {
    const { description, range } = this.form.getRawValue();
    if (this.form.invalid || range.length !== 2) {
      this.form.markAllAsTouched();
      return;
    }
    this.saving = true;
    this.fitnessService.postGoal({
      description,
      startDate: toApiDate(range[0]),
      endDate: toApiDate(range[1])
    }).subscribe({
      next: () => {
        this.message.success('Goal saved');
        this.form.reset();
        this.saving = false;
        this.loadGoals();
      },
      error: () => {
        this.message.error('Could not save goal');
        this.saving = false;
      }
    });
  }

  toggleAchieved(goal: Goal): void {
    if (goal.id == null) {
      return;
    }
    this.fitnessService.updateGoalStatus(goal.id, !goal.achieved).subscribe({
      next: updated => {
        this.goals = this.goals.map(g => (g.id === updated.id ? updated : g));
      },
      error: () => this.message.error('Could not update goal')
    });
  }

  private loadGoals(): void {
    this.loading = true;
    this.fitnessService.getGoals().subscribe({
      next: goals => {
        this.goals = goals;
        this.loading = false;
      },
      error: () => {
        this.message.error('Could not load goals');
        this.loading = false;
      }
    });
  }
}
