CREATE TABLE IF NOT EXISTS mushroominator.sensor_data (
  id SERIAL PRIMARY KEY,
  device_id VARCHAR,
  temperature INT,
  humidity INT,
  creation_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (device_id) REFERENCES mushroominator.device(device_id)
);
