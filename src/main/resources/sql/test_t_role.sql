CREATE TABLE t_role
(
    id   varchar(36)  NULL,
    name varchar(200) NULL,
    memo varchar(200) NULL
);

INSERT INTO test.t_role (id, name, memo) VALUES ('1', 'admin', '超级管理员');
INSERT INTO test.t_role (id, name, memo) VALUES ('2', 'test', '测试账户');