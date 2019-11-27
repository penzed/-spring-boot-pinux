CREATE TABLE t_permission
(
    id   varchar(36)  NOT NULL,
    url  varchar(200) NULL,
    name varchar(100) NULL,
    CONSTRAINT t_permission_id_uindex
        UNIQUE (id)
);

ALTER TABLE t_permission
    ADD PRIMARY KEY (id);

INSERT INTO test.t_permission (id, url, name) VALUES ('1', '/user', 'user:user');
INSERT INTO test.t_permission (id, url, name) VALUES ('2', '/user/add', 'user:add');
INSERT INTO test.t_permission (id, url, name) VALUES ('3', '/user/delete', 'user:delete');