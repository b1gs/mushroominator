CREATE TABLE mushroominator.user (
   id SERIAL PRIMARY KEY,
   u_firstname VARCHAR(100),
   u_lastname VARCHAR(100),
   u_email VARCHAR(100) UNIQUE,
   u_roles VARCHAR(100),
   u_password VARCHAR(255),
   create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   update_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);