--liquibase formatted sql
--changeset tposiadala:4
create table users(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username varchar ( 50 ) not null UNIQUE,
    password varchar ( 100 ) not null,
    enabled boolean not null
);
--changeset tposiadala:5
create table authorities (
    username varchar ( 50 ) not null,
    authority varchar ( 50 ) not null,
    constraint fk_authorities_users foreign key (username) references
    users(username),
    UNIQUE KEY username_authority (username, authority)
);
--changeset tposiadala:6
insert into users (id, username, password, enabled)
values (1, 'test', '{bcrypt}$2a$10$LA4gt4vuqZ0Ol8Pa.iErk.elgjs536hRD9ztbtXCmbfn323.bJPDa', true);
insert into users (id, username, password, enabled)
values (2, 'idiot', '{bcrypt}$2a$10$fZF8xFROv6gbQvIsci12quezEGUJWeHvX5KUYHyBVtMQTh7cnMtPe', true);

insert into authorities (username, authority) values ('test','USER');
insert into authorities (username, authority) values ('idiot','USER');