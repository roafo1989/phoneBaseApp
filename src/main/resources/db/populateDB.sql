DELETE FROM user_roles;
DELETE FROM notes;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, lastname, surname) VALUES
  ('Андрей', 'Иванов','Петрович'),
  ('Игорь', 'Круглов','Степанович');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO notes (name, lastname, surname, number, comment, user_id)
VALUES ('Иван','Мышкин','Петрович',89998887766,'',100000),
       ('Ольга','Сметанина','Ильинична',89119008877,'',100000),
       ('Сергей','Маев','',89818007766,'',100001),
       ('Петр','Петров','',88008008080,'',100001),
       ('Илья','Иванов','',84953001515,'',100001);

