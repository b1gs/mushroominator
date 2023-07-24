CREATE TABLE IF NOT EXISTS mushroominator.device_restart_history (
  id SERIAL PRIMARY KEY,
  device_id VARCHAR REFERENCES mushroominator.device(device_id),
  creation_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
