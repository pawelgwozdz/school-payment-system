DROP SCHEMA IF EXISTS "payment_settlement" CASCADE;

CREATE SCHEMA "payment_settlement";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS "payment_settlement".schools CASCADE;

CREATE TABLE "payment_settlement".schools
(
    id uuid NOT NULL,
    hour_price numeric(10,2) NOT NULL,
    name character varying COLLATE pg_catalog."default",
    CONSTRAINT schools_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "payment_settlement".parents CASCADE;

CREATE TABLE "payment_settlement".parents
(
    id uuid NOT NULL,
    first_name character varying COLLATE pg_catalog."default",
    last_name character varying COLLATE pg_catalog."default",
    CONSTRAINT parents_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "payment_settlement".children CASCADE;

CREATE TABLE "payment_settlement".children
(
    id uuid NOT NULL,
    first_name character varying COLLATE pg_catalog."default",
    last_name character varying COLLATE pg_catalog."default",
    parent_id uuid NOT NULL,
    school_id uuid NOT NULL,
    CONSTRAINT children_pkey PRIMARY KEY (id)
);

ALTER TABLE "payment_settlement".children
    ADD CONSTRAINT "FK_PARENT_ID" FOREIGN KEY (parent_id)
    REFERENCES "payment_settlement".parents (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

ALTER TABLE "payment_settlement".children
    ADD CONSTRAINT "FK_SCHOOL_ID" FOREIGN KEY (school_id)
    REFERENCES "payment_settlement".schools (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS "payment_settlement".child_attendances CASCADE;

CREATE TABLE "payment_settlement".child_attendances
(
    id uuid NOT NULL,
    entry_date TIMESTAMP NOT NULL,
    exit_date TIMESTAMP NOT NULL,
    child_id uuid NOT NULL,
    CONSTRAINT child_attendances_pkey PRIMARY KEY (id)
);

ALTER TABLE "payment_settlement".child_attendances
    ADD CONSTRAINT "FK_CHILD_ID" FOREIGN KEY (child_id)
    REFERENCES "payment_settlement".children (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;


