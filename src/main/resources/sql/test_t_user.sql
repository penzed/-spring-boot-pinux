CREATE TABLE t_user
(
    id          varchar(36)  NOT NULL,
    user_name   varchar(50)  NULL,
    password    varchar(100) NULL,
    create_time datetime     NULL,
    status      varchar(10)  NULL,
    CONSTRAINT t_user_id_uindex
        UNIQUE (id)
);

ALTER TABLE t_user
    ADD PRIMARY KEY (id);

INSERT INTO test.t_user (id, user_name, password, create_time, status) VALUES ('1', 'mrbird', '42ee25d1e43e9f57119a00d0a39e5250', '2019-11-13 09:11:46', '1');
INSERT INTO test.t_user (id, user_name, password, create_time, status) VALUES ('2', 'test', '7a38c13ec5e9310aed731de58bbc4214', '2019-11-13 08:11:09', '1');