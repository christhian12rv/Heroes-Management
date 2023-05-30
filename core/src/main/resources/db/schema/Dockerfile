FROM postgres:13.1-alpine

COPY /schema/init_schema.sql /docker-entrypoint-initdb.d/
COPY /migration/*.sql /docker-entrypoint-initdb.d/