CREATE TABLE customer (
  id UUID NOT NULL,
   industry VARCHAR(255),
   name VARCHAR(255),
   status VARCHAR(255),
   tax_rate DOUBLE PRECISION,
   time_stamp TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE customer_notes (
  customer_id UUID NOT NULL,
   notes_id UUID NOT NULL
);

CREATE TABLE contact (
  id UUID NOT NULL,
   name VARCHAR(255),
   position VARCHAR(255),
   type VARCHAR(255),
   time_stamp TIMESTAMP WITHOUT TIME ZONE,
   customer_id UUID,
   location_id UUID,
   CONSTRAINT pk_contact PRIMARY KEY (id)
);

CREATE TABLE contact_notes (
  contact_id UUID NOT NULL,
   notes_id UUID NOT NULL
);

CREATE TABLE location (
  id UUID NOT NULL,
   address VARCHAR(255),
   city VARCHAR(255),
   country VARCHAR(255),
   name VARCHAR(255),
   state VARCHAR(255),
   tax_rate DOUBLE PRECISION,
   zip VARCHAR(255),
   name_address_state_city VARCHAR(255),
   time_stamp TIMESTAMP WITHOUT TIME ZONE,
   customer_id UUID,
   CONSTRAINT pk_location PRIMARY KEY (id)
);

CREATE TABLE location_notes (
  location_id UUID NOT NULL,
   notes_id UUID NOT NULL
);

CREATE TABLE note (
  id UUID NOT NULL,
   note VARCHAR(255),
   time_stamp TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_note PRIMARY KEY (id)
);

CREATE INDEX idx_customer_id_industry_name ON customer(id, industry, name, status);

ALTER TABLE customer_notes ADD CONSTRAINT fk_cusnot_on_customer FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE customer_notes ADD CONSTRAINT fk_cusnot_on_note FOREIGN KEY (notes_id) REFERENCES note (id);




CREATE INDEX idx_contact_id_name_position ON contact(id, name, position, type);

ALTER TABLE contact ADD CONSTRAINT FK_CONTACT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE contact ADD CONSTRAINT FK_CONTACT_ON_LOCATION FOREIGN KEY (location_id) REFERENCES location (id);

ALTER TABLE contact_notes ADD CONSTRAINT fk_connot_on_contact FOREIGN KEY (contact_id) REFERENCES contact (id);

ALTER TABLE contact_notes ADD CONSTRAINT fk_connot_on_note FOREIGN KEY (notes_id) REFERENCES note (id);



CREATE INDEX idx_location_address_city ON location(address, city, country, name, state, name_address_state_city, zip, id);

ALTER TABLE location ADD CONSTRAINT FK_LOCATION_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE location_notes ADD CONSTRAINT fk_locnot_on_location FOREIGN KEY (location_id) REFERENCES location (id);

ALTER TABLE location_notes ADD CONSTRAINT fk_locnot_on_note FOREIGN KEY (notes_id) REFERENCES note (id);

