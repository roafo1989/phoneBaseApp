DELETE FROM user_roles;
DELETE FROM notes;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', 'password'),
('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001),
('ROLE_USER', 100001);

INSERT INTO notes (name, number, comment, user_id)
VALUES ('Иван',89998887766,'',100000),
       ('Ольга',89119008877,'',100000),
       ('Сергей',89818007766,'',100001),
       ('Петр',88008008080,'',100001),
       ('Илья',84953001515,'',100001);

