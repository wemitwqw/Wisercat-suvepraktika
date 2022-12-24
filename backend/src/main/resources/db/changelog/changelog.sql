-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE pets (id INT NOT NULL AUTO_INCREMENT, name VARCHAR, code VARCHAR, type VARCHAR, fur_color VARCHAR, PRIMARY KEY (id));
INSERT INTO pets (name, code, type, fur_color) VALUES ('Greg', '192-SM', 'cat', 'orange');
INSERT INTO pets (name, code, type, fur_color) VALUES ('Rufus', '128736841', 'dog', 'white');
INSERT INTO pets (name, code, type, fur_color) VALUES ('Peep', 'h123by412', 'hamster', 'grey');