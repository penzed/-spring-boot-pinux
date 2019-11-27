CREATE TABLE t_user_role
(
    id      varchar(36) NULL,
    user_id varchar(36) NULL,
    role_id varchar(36) NULL
);

INSERT INTO test.t_user_role (id, user_id, role_id) VALUES ('1', '1', '1');
INSERT INTO test.t_user_role (id, user_id, role_id) VALUES ('2', '2', '2');