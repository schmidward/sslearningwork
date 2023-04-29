CREATE database ericdschmid;

use ericdschmid;

CREATE TABLE users (
id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(45) NOT NULL,
password VARCHAR(45) NOT NULL,
enabled INT NOT NULL,
PRIMARY KEY (id));

CREATE TABLE authorities (
id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(45) NOT NULL,
authority VARCHAR(45) NOT NULL,
PRIMARY KEY (id));

-- Creating new tables for custom columns for authentication


INSERT IGNORE INTO users VALUES(NULL, 'hello', '12345', '1');
INSERT IGNORE INTO authorities VALUES(NULL, 'hello', 'write');

INSERT IGNORE INTO authorities VALUES(NULL, 'hello', 'read');