import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { en_US, provideNzI18n } from 'ng-zorro-antd/i18n';
import { NZ_ICONS } from 'ng-zorro-antd/icon';
import {
  CheckOutline,
  DashboardOutline,
  FireOutline,
  FlagOutline,
  ThunderboltOutline,
  UndoOutline
} from '@ant-design/icons-angular/icons';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withFetch } from '@angular/common/http';

registerLocaleData(en);

export const appIcons = [DashboardOutline, ThunderboltOutline, FireOutline, FlagOutline, CheckOutline, UndoOutline];

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    // No provideClientHydration(): ng-zorro 17 components (table, select, date picker)
    // break hydration (NG0502), so the browser re-renders the server-rendered page instead.
    provideNzI18n(en_US),
    { provide: NZ_ICONS, useValue: appIcons },
    provideAnimationsAsync(),
    provideHttpClient(withFetch())
  ]
};
