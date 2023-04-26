-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:10

INSERT INTO users (username, password, enabled)
VALUES ('user1', '$2a$08$Bm5V2LxQHP6LQPPcZNbX5.yV5RkBMw9qYSt/NNKalMWpzTim5Ro9i', true);

INSERT INTO users (username, password, enabled)
VALUES ('user2', '$2a$08$Bm5V2LxQHP6LQPPcZNbX5.yV5RkBMw9qYSt/NNKalMWpzTim5Ro9i', true);

INSERT INTO users (username, password, enabled)
VALUES ('user3', '$2a$08$Bm5V2LxQHP6LQPPcZNbX5.yV5RkBMw9qYSt/NNKalMWpzTim5Ro9i', true);

