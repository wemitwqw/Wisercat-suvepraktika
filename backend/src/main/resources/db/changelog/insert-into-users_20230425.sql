-- noinspection SqlNoDataSourceInspectionForFile
-- liquibase formatted sql
-- changeset liquibase:10

insert into users (username, password, enabled) values ('user1', 'password1', true);
