import { formatDate } from '@angular/common';

// Backend expects LocalDate ("yyyy-MM-dd"); toISOString() would shift the day by the UTC offset.
export function toApiDate(date: Date): string {
  return formatDate(date, 'yyyy-MM-dd', 'en-US');
}
