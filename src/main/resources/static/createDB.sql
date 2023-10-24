
CREATE TABLE Sensor(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE CONSTRAINT min_max_sym_count CHECK (length(name)>=3 AND length(name)<=30)
);

CREATE TABLE Measurement(
    id SERIAL PRIMARY KEY,
    value FLOAT NOT NULL CONSTRAINT min_max_value CHECK (value <= 100 AND value >=-100),
    raining BOOLEAN NOT NULL,
    sensor_id INTEGER NOT NULL,
    FOREIGN KEY(sensor_id) REFERENCES Sensor(id) ON DELETE CASCADE,
    creating_date_time TIMESTAMP
    )