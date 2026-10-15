-- Optional demo data. Dates are relative to the day the script runs,
-- so the dashboard always shows recent activity.

INSERT INTO activity (date, steps, distance, calories_burned) VALUES
    (CURRENT_DATE - 6,  7420, 5.30, 290),
    (CURRENT_DATE - 5, 10250, 7.80, 410),
    (CURRENT_DATE - 4,  6100, 4.20, 240),
    (CURRENT_DATE - 3, 12030, 9.10, 480),
    (CURRENT_DATE - 2,  8840, 6.40, 350),
    (CURRENT_DATE - 1,  9560, 7.00, 380),
    (CURRENT_DATE,      4310, 3.10, 170);

INSERT INTO workout (type, date, duration, calories_burned) VALUES
    ('Running',         CURRENT_DATE - 6, 35, 360),
    ('Weight Training', CURRENT_DATE - 5, 50, 300),
    ('Cycling',         CURRENT_DATE - 4, 60, 520),
    ('Yoga',            CURRENT_DATE - 3, 40, 150),
    ('HIIT',            CURRENT_DATE - 2, 25, 330),
    ('Swimming',        CURRENT_DATE - 1, 45, 420);

INSERT INTO goal (description, start_date, end_date, achieved) VALUES
    ('Walk 10,000 steps a day for a week', CURRENT_DATE - 14, CURRENT_DATE - 7, TRUE),
    ('Run a 5K under 30 minutes',          CURRENT_DATE - 30, CURRENT_DATE - 2, TRUE),
    ('Complete 12 workouts this month',    CURRENT_DATE - 10, CURRENT_DATE + 20, FALSE),
    ('Cycle 100 km in total',              CURRENT_DATE - 5,  CURRENT_DATE + 25, FALSE);
