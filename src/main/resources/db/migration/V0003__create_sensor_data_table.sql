CREATE TABLE IF NOT EXISTS sensor_data (
  id SERIAL PRIMARY KEY,
  device_id VARCHAR,
  temperature INT,
  humidity INT,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
