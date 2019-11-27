DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                 NOT NULL,
    lastname         VARCHAR                 NOT NULL,
    surname          VARCHAR                 NOT NULL
);


CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE notes
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id     INTEGER   NOT NULL,
    name        VARCHAR   NOT NULL,
    lastname    VARCHAR,
    surname     VARCHAR,
    number      BIGINT    NOT NULL,
    comment     TEXT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);