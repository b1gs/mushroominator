CREATE TABLE IF NOT EXISTS mushroominator.device (
  id SERIAL PRIMARY KEY,
  device_id VARCHAR UNIQUE,
  description VARCHAR,
  creation_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
