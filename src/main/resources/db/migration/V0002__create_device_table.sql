CREATE TABLE IF NOT EXISTS device (
  id SERIAL PRIMARY KEY,
  device_id VARCHAR,
  description VARCHAR,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
