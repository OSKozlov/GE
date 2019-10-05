CREATE database userdb;
GRANT ALL PRIVILEGES ON database userdb to admin;
CREATE TABLE auth_role (
  id SERIAL PRIMARY KEY,
  name varchar(255) NOT NULL
);
INSERT INTO auth_role(name) VALUES ('ADMIN_USER');
INSERT INTO auth_role(name) VALUES ('ENGINEER_USER');
CREATE TABLE auth_user (
  id SERIAL PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role varchar(255) NOT NULL
);
insert into auth_user (username, password, role) values ('Engineer','$2a$04$tcr8gsoRRgAQRGSZAY2D9OSHyg3gtyO5nKDgk3vJQp45tCuNhmqTy','ENGINEER_USER');
insert into auth_user (username, password, role) values ('Admin','$2a$04$IlbxROkk5s0wnjjXDKFjVeMY2z3Ko6eH2ButrQnO8fjNv09Gp/pDW','ADMIN_USER');
CREATE database eventdb;
GRANT ALL PRIVILEGES ON database eventdb to admin;
CREATE TABLE datafileevent (
  id              SERIAL PRIMARY KEY,
  timestamp       timestamp NOT NULL,
  topic           VARCHAR(150) NOT NULL,
  type           VARCHAR(150) NOT NULL,
  filename       VARCHAR(200) NOT NULL
);