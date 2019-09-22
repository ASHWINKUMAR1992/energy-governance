-- DROP TABLE IF EXISTS hotels.hotels;

CREATE TABLE hotels.hotels
(
    id bigserial NOT NULL,
    updated timestamp without time zone NOT NULL,
    hotelcode character varying(255) COLLATE pg_catalog."default",
    hotelname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT hotels_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE hotels.hotels
    OWNER to postgres;

—- DROP TABLE IF EXISTS hotels.electricity_consumption;

CREATE TABLE hotels.electricity_consumption
(
    id bigserial NOT NULL,
    updated timestamp without time zone NOT NULL,
    quantity character varying(255) COLLATE pg_catalog."default" NOT NULL,
    hotel_id bigint NOT NULL,
    CONSTRAINT electricity_consumption_pkey PRIMARY KEY (id),
    CONSTRAINT fk8vlo7p4mkhl11imbi3m37vwhj FOREIGN KEY (hotel_id)
        REFERENCES hotels.hotels (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE hotels.electricity_consumption
    OWNER to postgres;

-- DROP TABLE hotels.waste_generation;

CREATE TABLE hotels.waste_generation
(
    id bigserial NOT NULL,
    updated timestamp without time zone NOT NULL,
    quantity character varying(255) COLLATE pg_catalog."default" NOT NULL,
    hotel_id bigint NOT NULL,
    CONSTRAINT waste_generation_pkey PRIMARY KEY (id),
    CONSTRAINT fkrunmnacvu1hvrriatyxkchoao FOREIGN KEY (hotel_id)
        REFERENCES hotels.hotels (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE hotels.waste_generation
    OWNER to postgres;

-- DROP TABLE hotels.water_consumption;

CREATE TABLE hotels.water_consumption
(
    id bigserial NOT NULL,
    updated timestamp without time zone NOT NULL,
    quantity character varying(255) COLLATE pg_catalog."default" NOT NULL,
    hotel_id bigint NOT NULL,
    CONSTRAINT water_consumption_pkey PRIMARY KEY (id),
    CONSTRAINT fk594px88oco1s54h60vcunv20g FOREIGN KEY (hotel_id)
        REFERENCES hotels.hotels (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE hotels.water_consumption
    OWNER to postgres;

  CREATE SEQUENCE hibernate_sequence  INCREMENT 1  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;