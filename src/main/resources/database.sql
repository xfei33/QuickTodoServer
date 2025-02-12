-- 创建数据库
CREATE DATABASE quicktodo;

-- 使用数据库
USE quicktodo;

-- 创建用户表
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL
);

-- 创建待办事项表
CREATE TABLE todos (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       due_date DATETIME NOT NULL,
                       user_id BIGINT NOT NULL,
                       priority VARCHAR(50),
                       tags VARCHAR(255),
                       completed BOOLEAN DEFAULT FALSE,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 创建 MySQL 用户并授权
CREATE USER 'quicktodo_user'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON quicktodo.* TO 'quicktodo_user'@'localhost';
FLUSH PRIVILEGES;
