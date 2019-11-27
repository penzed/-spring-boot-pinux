CREATE TABLE t_role_permission
(
    id            varchar(36) NULL,
    role_id       varchar(36) NULL,
    permission_id varchar(36) NULL
);

INSERT INTO test.t_role_permission (id, role_id, permission_id) VALUES ('1', '1', '2');
INSERT INTO test.t_role_permission (id, role_id, permission_id) VALUES ('2', '1', '3');
INSERT INTO test.t_role_permission (id, role_id, permission_id) VALUES ('3', '2', '1');
INSERT INTO test.t_role_permission (id, role_id, permission_id) VALUES ('4', '1', '1');