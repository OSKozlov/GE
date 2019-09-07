CREATE TABLE datafileevent (
  id              SERIAL PRIMARY KEY,
  timestamp       timestamp NOT NULL,
  topic           VARCHAR(150) NOT NULL,
  type           VARCHAR(150) NOT NULL,
  filename       VARCHAR(200) NOT NULL
);