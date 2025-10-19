-- =======================================================
-- RHFD SEED SCRIPT - COMMON FOR ALL ENVIRONMENTS
-- Run: mysql -u root -p rhfd_db < seed.sql
-- =======================================================

-- 1️⃣ Create database (if not exists)
CREATE DATABASE IF NOT EXISTS local_db;
USE local_db;

-- 2️⃣ Drop old tables (optional)
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS riders;
DROP TABLE IF EXISTS drivers;
DROP TABLE IF EXISTS trips;

-- 3️⃣ Create tables
CREATE TABLE riders (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(10) NOT NULL,
  created_at DATE NOT NULL DEFAULT (CURRENT_DATE)
);

CREATE TABLE drivers (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(10) NOT NULL,
  vehicle_type VARCHAR(50) NOT NULL,
  vehicle_plate VARCHAR(10) NOT NULL,
  is_active BOOLEAN NOT NULL
);

CREATE TABLE trips (
  id INT PRIMARY KEY,
  rider_id INT NOT NULL,
  driver_id INT NOT NULL,
  pickup_zone VARCHAR(255) NOT NULL,
  drop_zone VARCHAR(255) NOT NULL,
  status ENUM('ONGOING', 'ACCEPTED', 'CANCELLED', 'REQUESTED', 'COMPLETED') NOT NULL DEFAULT 'ACCEPTED',
  requested_at DATE NOT NULL DEFAULT (CURRENT_DATE),
  distance_km DECIMAL(10,2) NOT NULL,
  base_fare DECIMAL(10,2) NOT NULL,
  surge_multiplier DECIMAL(10,2) NOT NULL,
  total_fare DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (rider_id) REFERENCES riders(id),
  FOREIGN KEY (driver_id) REFERENCES drivers(id)
);

CREATE TABLE payments (
  id INT PRIMARY KEY,
  trip_id INT NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  method VARCHAR(50) NOT NULL,
  status ENUM('FAILED', 'PENDING', 'SUCCESS') NOT NULL DEFAULT 'PENDING',
  reference VARCHAR(255) NOT NULL,
  created_at DATE NOT NULL DEFAULT (CURRENT_DATE),
  FOREIGN KEY (trip_id) REFERENCES trips(id)
);

CREATE TABLE ratings (
  id INT PRIMARY KEY,
  trip_id INT NOT NULL,
  rider_rating INT,
  driver_rating INT,
  comment TEXT,
  FOREIGN KEY (trip_id) REFERENCES trips(id)
);

-- 4️⃣ Load CSVs into tables
-- Adjust path if needed (absolute or relative to MySQL server)
LOAD DATA LOCAL INFILE 'C:/Users/veron/Documents/Group-58-Microservice/seed/rhfd_drivers.csv'
INTO TABLE drivers
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, name, phone, vehicle_type, vehicle_plate, is_active);

LOAD DATA LOCAL INFILE 'C:/Users/veron/Documents/Group-58-Microservice/seed/rhfd_riders.csv'
INTO TABLE riders
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, name, email, phone, created_at);

LOAD DATA LOCAL INFILE 'C:/Users/veron/Documents/Group-58-Microservice/seed/rhfd_trips.csv'
INTO TABLE trips
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, rider_id, driver_id, pickup_zone, drop_zone, status, requested_at, distance_km, base_fare, surge_multiplier, total_fare);

LOAD DATA LOCAL INFILE 'C:/Users/veron/Documents/Group-58-Microservice/seed/rhfd_payments.csv'
INTO TABLE payments
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, trip_id, amount, method, status, reference, created_at);

LOAD DATA LOCAL INFILE 'C:/Users/veron/Documents/Group-58-Microservice/seed/rhfd_ratings.csv'
INTO TABLE ratings
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(id, trip_id, rider_rating, driver_rating, comment);

-- ✅ Done