-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:9

CREATE TABLE users (
   id INT NOT NULL AUTO_INCREMENT,
   username VARCHAR_IGNORECASE NOT NULL,
   password VARCHAR_IGNORECASE NOT NULL,
   PRIMARY KEY (id)
);