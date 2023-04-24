-- noinspection SqlNoDataSourceInspectionForFile

-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE animal_types (id INT NOT NULL AUTO_INCREMENT, animal_type VARCHAR UNIQUE, PRIMARY KEY (id));
INSERT INTO animal_types (animal_type) VALUES ('cat');
INSERT INTO animal_types (animal_type) VALUES ('dog');
INSERT INTO animal_types (animal_type) VALUES ('hamster');
INSERT INTO animal_types (animal_type) VALUES ('ferret');

CREATE TABLE animal_countries (id INT NOT NULL AUTO_INCREMENT, animal_country VARCHAR UNIQUE, PRIMARY KEY (id));
INSERT INTO animal_countries (animal_country) VALUES ('Estonia');
INSERT INTO animal_countries (animal_country) VALUES ('Latvia');
INSERT INTO animal_countries (animal_country) VALUES ('Lithuania');
INSERT INTO animal_countries (animal_country) VALUES ('Sweden');
INSERT INTO animal_countries (animal_country) VALUES ('Finland');

CREATE TABLE animal_fur_colors (id INT NOT NULL AUTO_INCREMENT, animal_fur_color VARCHAR UNIQUE, PRIMARY KEY (id));
INSERT INTO animal_fur_colors (animal_fur_color) VALUES ('white');
INSERT INTO animal_fur_colors (animal_fur_color) VALUES ('grey');
INSERT INTO animal_fur_colors (animal_fur_color) VALUES ('black');
INSERT INTO animal_fur_colors (animal_fur_color) VALUES ('orange');

CREATE TABLE pets (id INT NOT NULL AUTO_INCREMENT, name VARCHAR, code VARCHAR UNIQUE, animal_type_id INT, fur_color_id INT, country_id INT, PRIMARY KEY (id), FOREIGN KEY (animal_type_id) REFERENCES animal_types, FOREIGN KEY (country_id) REFERENCES animal_countries, FOREIGN KEY (fur_color_id) REFERENCES animal_fur_colors);
INSERT INTO pets (name, code, animal_type_id, fur_color_id, country_id) VALUES ('Greg', '192-SM', 1, 3, 1);
INSERT INTO pets (name, code, animal_type_id, fur_color_id, country_id) VALUES ('Rufus', '128736841', 2, 2, 3);
INSERT INTO pets (name, code, animal_type_id, fur_color_id, country_id) VALUES ('Peep', 'h123by412', 3, 1, 2);

-- CREATE TYPE pet_countries AS ENUM ('Estonia', 'Latvia', 'Lithuania');
-- CREATE TYPE pet_types AS ENUM ('cat', 'dog', 'hamster', 'parrot', 'horse');
-- CREATE TYPE pet_colors AS ENUM ('white', 'grey', 'black', 'orange', 'yellow');
