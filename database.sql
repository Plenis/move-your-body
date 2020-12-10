
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
    exercise_done TEXT NOT NULL,
    time_completed INT NOT NULL,
    intensity TEXT NOT NULL,
    weight_lost INT NOT NULL,
    player_id INT NOT NULL,
    exercise_id INT NOT NULL,
    FOREIGN KEY (player_id) REFERENCES player(id),
    FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);



