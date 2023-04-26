-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:10

INSERT INTO users (username, password, enabled)
VALUES ('user1', 'password', true);

INSERT INTO users (username, password, enabled)
VALUES ('user2', 'password', true);

INSERT INTO users (username, password, enabled)
VALUES ('user3', 'password', true);

