ALTER TABLE IF EXISTS mushroominator.sensor_data
            ALTER COLUMN temperature TYPE VARCHAR,
            ALTER COLUMN humidity TYPE VARCHAR;
