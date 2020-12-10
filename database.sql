
CREATE TABLE player (
    id serial PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    weight INT NOT NULL,
    height INT NOT NULL,
    age INT NOT NULL
);

CREATE TABLE player_exercise (
    id SERIAL NOT NULL PRIMARY KEY,
    player_id INT NOT NULL,
    exercise_id INT NOT NULL,
    FOREIGN KEY (player_id) REFERENCES player(id),
    FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);

CREATE TABLE exercise (
    id serial PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    met INT NOT NULL
);

CREATE TABLE progress (
    id serial PRIMARY KEY NOT NULL,
    time_completed INT NOT NULL,
    calories_lost INT NOT NULL,
    intensity_id INT NOT NULL,
    player_id INT NOT NULL,
    exercise_id INT NOT NULL,
    FOREIGN KEY (player_id) REFERENCES player(id),
    FOREIGN KEY (exercise_id) REFERENCES exercise(id),
    FOREIGN KEY (intensity_id) REFERENCES intensity(id)
);

CREATE TABLE intensity (
    id serial PRIMARY KEY NOT NULL,
    name TEXT NOT NULL
);

INSERT INTO exercise (name, met) VALUES ('Squat', 2.9);
INSERT INTO exercise (name, met) VALUES ('Boxing', 3.8);
INSERT INTO exercise (name, met) VALUES ('Star Jump', 10);


INSERT INTO intensity (name) VALUES ('High');
INSERT INTO intensity (name) VALUES ('Medium');
INSERT INTO intensity (name) VALUES ('Low');


--RELATIONSHIPS




