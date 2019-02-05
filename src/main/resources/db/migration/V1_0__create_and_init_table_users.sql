-- create users --
CREATE TABLE users (
  id                BIGSERIAL PRIMARY KEY,
  email             VARCHAR   NOT NULL UNIQUE,
  password          VARCHAR   NOT NULL,
  first_name        VARCHAR,
  last_name         VARCHAR,
  phone_number      VARCHAR,
  enabled           BOOLEAN   NOT NULL DEFAULT TRUE
);
--
INSERT INTO users (email, password, first_name, last_name, phone_number, enabled)
VALUES ('pasha.gmz@gmail.com', '202cb962ac59075b964b07152d234b70', 'Pavel', 'Homza', '375447836982', true);

-- create modules --
CREATE TABLE modules (
  id   BIGSERIAL PRIMARY KEY,
  name VARCHAR NOT NULL,
  code VARCHAR NOT NULL UNIQUE
);
INSERT INTO modules (code, name) VALUES ('USER_MANAGEMENT', 'User management');

-- create permissions --
CREATE TABLE permission_types (
  id   BIGSERIAL PRIMARY KEY,
  code VARCHAR NOT NULL UNIQUE,
  name VARCHAR NOT NULL
);
INSERT INTO permission_types (id, code, name) VALUES (1, 'READ', 'Read');
INSERT INTO permission_types (id, code, name) VALUES (2, 'WRITE', 'Write');
--
CREATE TABLE permissions (
  id          BIGSERIAL PRIMARY KEY,
  module_id BIGINT NOT NULL REFERENCES permission_types,
  type_id BIGINT NOT NULL REFERENCES permission_types,
  unique(module_id, type_id)
);

INSERT INTO permissions (module_id, type_id)
select
  (select id from modules where code = 'USER_MANAGEMENT'),
  (select id from permission_types where code = 'READ');

INSERT INTO permissions (module_id, type_id)
select
  (select id from modules where code = 'USER_MANAGEMENT'),
  (select id from permission_types where code = 'WRITE');


-- create roles --
CREATE TABLE roles (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR NOT NULL,
  code VARCHAR NOT NULL UNIQUE
);
INSERT INTO roles (code, name) VALUES ('USER', 'User');
INSERT INTO roles (code, name) VALUES ('ADMIN', 'Admin');

-- create roles_permissions --
CREATE TABLE roles_permissions (
  role_id BIGINT NOT NULL REFERENCES roles,
  permission_id BIGINT NOT NULL REFERENCES permissions,
  PRIMARY KEY (role_id, permission_id)
);
--
INSERT INTO roles_permissions(role_id, permission_id)
select
  (select id from roles where code = 'ADMIN'),
  (select id from permissions where id = 1);

-- create users-roles --
CREATE TABLE users_roles (
  user_id BIGINT NOT NULL REFERENCES users,
  role_id BIGINT NOT NULL REFERENCES roles,
  PRIMARY KEY (user_id, role_id)
);

--
INSERT INTO users_roles(user_id, role_id)
select
  (select id from users where email = 'pasha.gmz@gmail.com'),
  (select id from roles where code = 'ADMIN');