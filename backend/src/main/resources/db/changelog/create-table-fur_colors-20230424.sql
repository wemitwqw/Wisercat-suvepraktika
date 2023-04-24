-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:3

CREATE TABLE fur_colors (
    id INT NOT NULL AUTO_INCREMENT,
    fur_color VARCHAR UNIQUE,
    PRIMARY KEY (id)
    );
