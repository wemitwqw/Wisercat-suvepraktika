-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:4

CREATE TABLE pets (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR NOT NULL,
    code VARCHAR UNIQUE NOT NULL,
    animal_type_id INT NOT NULL,
    fur_color_id INT NOT NULL,
    country_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (animal_type_id) REFERENCES animal_types,
    FOREIGN KEY (country_id) REFERENCES countries,
    FOREIGN KEY (fur_color_id) REFERENCES fur_colors
);
