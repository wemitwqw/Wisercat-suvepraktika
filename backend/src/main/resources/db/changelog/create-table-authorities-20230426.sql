-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:12

CREATE TABLE authorities (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_authorities_users FOREIGN KEY(user_id) REFERENCES users(id)
);