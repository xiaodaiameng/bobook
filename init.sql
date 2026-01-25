-- init.sql
USE sp001_db;

CREATE TABLE IF NOT EXISTS students (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        nickname VARCHAR(100) NOT NULL UNIQUE,
    pwd VARCHAR(100) NOT NULL,
    purchase_times INT DEFAULT 0,
    sales_times INT DEFAULT 0,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS books (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     bookname VARCHAR(200) NOT NULL,
    price INT NOT NULL,
    sellerId INT NOT NULL,
    buyerId INT DEFAULT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS messages (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    is_secret TINYINT DEFAULT 0,
    message_type VARCHAR(20) DEFAULT '其他',
    content TEXT NOT NULL,
    status TINYINT DEFAULT 1,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

INSERT IGNORE INTO students (nickname, pwd) VALUES
('admin', 'admin123');