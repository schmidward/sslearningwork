CREATE database springsecurity;

use springsecurity;

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

-- Creating new tables for custom columns for authentication. Caveat is in this scenario we can't use jdbc details manager. Need own logic
CREATE TABLE customer (
id INT NOT NULL AUTO_INCREMENT,
email VARCHAR(45) NOT NULL,
pwd VARCHAR(200) NOT NULL,
role VARCHAR(45) NOT NULL,
PRIMARY KEY (id)
);

INSERT IGNORE INTO users VALUES(NULL, 'hello', '12345', '1');
INSERT IGNORE INTO authorities VALUES(NULL, 'hello', 'write');

INSERT IGNORE INTO authorities VALUES(NULL, 'hello', 'read');

INSERT INTO customers ('email', 'pwd', 'role') VALUES ('janedoe@example.org', '98765', 'admin');