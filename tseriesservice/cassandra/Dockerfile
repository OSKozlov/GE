FROM cassandra:3.11
WORKDIR /docker-entrypoint-initdb.d
COPY initial-seed.cql /docker-entrypoint-initdb.d/initial-seed.cql
EXPOSE 9042