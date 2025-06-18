CREATE DATABASE userdb;

CREATE TABLE user (
	id INT AUTO_INCREMENT PRIMARY KEY,
    ic_num VARCHAR(14) UNIQUE ,
    gender ENUM('Male', 'Female') NOT NULL,
    dob DATE NOT NULL,
    postcode VARCHAR(10),
    town VARCHAR(100)
);

DESCRIBE user;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS postcode_mapping;

CREATE TABLE postcode_mapping (
    postcode VARCHAR(10) PRIMARY KEY NOT NULL,
    town VARCHAR(100) NOT NULL
);

INSERT INTO postcode_mapping (postcode, town) VALUES ('08000', 'Sungai Petani');
INSERT INTO postcode_mapping (postcode, town) VALUES ('43200', 'Cheras');
INSERT INTO postcode_mapping (postcode, town) VALUES ('47000', 'Sungai Buloh');
