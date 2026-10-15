import { Component, OnInit, inject } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzProgressModule } from 'ng-zorro-antd/progress';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzStatisticModule } from 'ng-zorro-antd/statistic';
import { Stats } from '../../models/fitness.models';
import { FitnessService } from '../../services/fitness.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DecimalPipe, NzCardModule, NzGridModule, NzProgressModule, NzSpinModule, NzStatisticModule],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  private readonly fitnessService = inject(FitnessService);
  private readonly message = inject(NzMessageService);

  stats?: Stats;
  loading = false;

  get totalGoals(): number {
    return this.stats ? this.stats.achievedGoals + this.stats.notAchievedGoals : 0;
  }

  get goalPercent(): number {
    return this.totalGoals ? Math.round((this.stats!.achievedGoals / this.totalGoals) * 100) : 0;
  }

  ngOnInit(): void {
    this.loading = true;
    this.fitnessService.getStats().subscribe({
      next: stats => {
        this.stats = stats;
        this.loading = false;
      },
      error: () => {
        this.message.error('Could not load statistics');
        this.loading = false;
      }
    });
  }
}
