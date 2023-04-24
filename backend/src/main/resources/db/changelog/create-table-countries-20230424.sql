-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:2

CREATE TABLE countries (
    id INT NOT NULL AUTO_INCREMENT,
    country VARCHAR UNIQUE,
    PRIMARY KEY (id)
    );