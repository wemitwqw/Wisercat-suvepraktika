-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:9

CREATE TABLE users (
   username VARCHAR_IGNORECASE NOT NULL,
   password VARCHAR_IGNORECASE NOT NULL,
   enabled boolean NOT NULL,
   PRIMARY KEY (username)
);