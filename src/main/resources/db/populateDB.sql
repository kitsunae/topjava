DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO meals (id, datetime, description, calories, user_id) VALUES
  (1, '2015-07-12 12:00:00', 'Test meal', 1200, 100000),
  (2, '2015-07-12 13:05:00', 'Test meal 2', 200, 100000),
  (3, '2015-08-12 18:00:00', 'Test meal 3', 1500, 100000),
  (4, '2017-01-01 18:00:00', 'Test meal now', 3000, 100000);