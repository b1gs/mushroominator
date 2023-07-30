CREATE TABLE IF NOT EXISTS mushroominator.device_configuration (
  id SERIAL PRIMARY KEY,
  device_id VARCHAR UNIQUE REFERENCES mushroominator.device(device_id),
  sensor_data_interval INTEGER NOT NULL,
  sensor_read_interval INTEGER NOT NULL,
  fan_work_interval INTEGER NOT NULL,
  pump_work_interval INTEGER NOT NULL,
  fan_work_period_interval INTEGER NOT NULL,
  humidity_threshold INTEGER NOT NULL,
  fan_relay_pin INTEGER NOT NULL,
  pump_relay_pin INTEGER NOT NULL,
  dht_sensor_1 INTEGER NOT NULL,
  dht_sensor_2 INTEGER NOT NULL,
  broker_ip varchar NOT NULL,
  broker_port INTEGER NOT NULL,
  create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
