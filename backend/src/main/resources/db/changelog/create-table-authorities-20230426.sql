-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:12

CREATE TABLE authorities (
    username VARCHAR(30) NOT NULL,
    authority VARCHAR(10) NOT NULL,
    PRIMARY KEY (username),
    FOREIGN KEY (username) references users(username)
);