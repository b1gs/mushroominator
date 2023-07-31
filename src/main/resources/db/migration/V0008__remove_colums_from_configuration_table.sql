ALTER TABLE mushroominator.device_configuration
            DROP COLUMN IF EXISTS broker_port,
            DROP COLUMN IF EXISTS broker_ip;