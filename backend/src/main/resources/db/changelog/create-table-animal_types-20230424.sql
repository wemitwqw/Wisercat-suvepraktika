-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:1

CREATE TABLE animal_types (
    id INT NOT NULL AUTO_INCREMENT,
    type VARCHAR UNIQUE,
    PRIMARY KEY (id)
    );
