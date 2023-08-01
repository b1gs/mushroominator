CREATE TABLE telegram_chat (
    id SERIAL PRIMARY KEY,
    t_user_id INTEGER NOT NULL,
    t_chat_id BIGINT UNIQUE,
    t_username VARCHAR(255),
    t_firstname VARCHAR(255),
    t_lastname VARCHAR(255),
    device_id VARCHAR UNIQUE REFERENCES mushroominator.device(device_id),
    create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);