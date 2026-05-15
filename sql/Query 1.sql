-- 1️⃣ Select the database
USE foodsaver;

-- 2️⃣ Drop old tables if exist
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS users;

-- 3️⃣ Create users table
CREATE TABLE users (
    userid BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- 4️⃣ Insert sample users
INSERT INTO users (name, email, password, role)
VALUES
('Provider One', 'provider@mail.com', '1234', 'PROVIDER'),
('User One', 'user@mail.com', '1234', 'USER');

-- 5️⃣ Create food table
CREATE TABLE food (
    foodid BIGINT PRIMARY KEY AUTO_INCREMENT,
    foodname VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price INT NOT NULL,
    expire_time BIGINT NOT NULL,
    location VARCHAR(100) NOT NULL,
    active_status ENUM('ACTIVE','INACTIVE') NOT NULL,
    provider_id BIGINT NOT NULL,
    CONSTRAINT fk_food_provider
        FOREIGN KEY (provider_id)
        REFERENCES users(userid)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- 6️⃣ Insert sample foods
INSERT INTO food (foodname, quantity, price, expire_time, location, active_status, provider_id)
VALUES
('Pizza', 10, 100, UNIX_TIMESTAMP() + 3600, 'Hostel A', 'ACTIVE', 1),
('Burger', 5, 50, UNIX_TIMESTAMP() + 7200, 'Hostel B', 'ACTIVE', 1);
USE foodsaver;

SELECT userid, name, role FROM users;
-- 1. Database-ah select pannuthu
USE foodsaver;

-- 2. User role-ah update pannuthu (userid=1 ku)
UPDATE users 
SET role = 'PROVIDER' 
WHERE userid = 1;

-- 3. Changes-ah permanent-ah save pannuthu
COMMIT;

-- 4. Result-ah verify panni check pannunga
SELECT userid, name, email, role FROM users WHERE userid = 1;


-- 7️⃣ Create orders table
CREATE TABLE orders (
    order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL,
    order_time BIGINT NOT NULL,
    food_id BIGINT,
    user_id BIGINT,
    CONSTRAINT fk_order_food
        FOREIGN KEY (food_id)
        REFERENCES food(foodid)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id)
        REFERENCES users(userid)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- 8️⃣ Insert sample orders
INSERT INTO orders (quantity, order_time, food_id, user_id)
VALUES
(2, UNIX_TIMESTAMP(), 1, 2),
(1, UNIX_TIMESTAMP(), 2, 2);
UPDATE users SET role = TRIM('PROVIDER') WHERE userid = 1;
COMMIT;
USE foodsaver;
SELECT userid, name, role FROM users;
SELECT * FROM users WHERE userid=1;
COMMIT;
SELECT * FROM users;
SELECT DATABASE();
USE foodsaver;

-- 1. Pazhaya table-ah delete pannuvom
DROP TABLE IF EXISTS orders;

-- 2. Hibernate ethirpaarkura structure-la table-ah create pannuvom
USE foodsaver; -- Intha line-ah mela potta thaan matha command work aagum

DROP TABLE IF EXISTS orders;
USE foodsaver;
CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    order_time INT NOT NULL,
    food_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (food_id) REFERENCES food(foodid),
    FOREIGN KEY (user_id) REFERENCES users(userid)
);
SELECT * FROM orders;
SELECT * FROM food WHERE foodname = 'vada';
SELECT email, password, role FROM users;
USE foodsaver;
select * from food
SELECT * FROM orders 
WHERE user_id = 2 AND food_id = 1 AND quantity = 2;
USE foodsaver;
SELECT * FROM food;
SELECT * FROM user;
USE foodsaver;
UPDATE food SET active_status = 'ACTIVE' WHERE foodid IN (1, 2);
UPDATE food SET expire_time = 1800000000, active_status = 'ACTIVE', quantity = 10 WHERE foodid IN (1, 2);
UPDATE food SET active_status = 'ACTIVE', expire_time = 2000000000, quantity = 10;
COMMIT;
USE foodsaver;

-- Safe mode-ah bypass panni ellaa food items-aiyum update pannum
UPDATE food 
SET active_status = 'ACTIVE', 
    expire_time = 2000000000, 
    quantity = 10 
WHERE foodid > 0;

COMMIT;

-- Check panni paarunga 2 rows varutha nu
SELECT * FROM food;
