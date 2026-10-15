# FitnessTrackerWeb

Angular 17 frontend of the Fitness Tracker. See the [main README](../README.md) for the full project overview.

```bash
npm install
npm start          # dev server on http://localhost:4200
npx ng build       # production build (SSR) in dist/
npx ng test        # unit tests
```

The app expects the backend API on `http://localhost:8036/api` (see `src/app/services/fitness.service.ts`).
