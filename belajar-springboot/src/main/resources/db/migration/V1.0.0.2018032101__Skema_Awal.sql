CREATE TABLE produk (
    id character varying(255) NOT NULL,
    harga numeric(19,2),
    kode character varying(10) NOT NULL,
    nama character varying(255) NOT NULL,
    CONSTRAINT produk_harga_check CHECK ((harga >= (0)::numeric))
);

CREATE TABLE s_permission (
    id character varying(255) NOT NULL,
    label character varying(255),
    name character varying(255)
);

CREATE TABLE s_role (
    id character varying(255) NOT NULL,
    description character varying(255),
    name character varying(255)
);

CREATE TABLE s_role_permission (
    id_role character varying(255) NOT NULL,
    id_permission character varying(255) NOT NULL
);

CREATE TABLE s_user (
    id character varying(255) NOT NULL,
    active boolean,
    email character varying(255),
    fullname character varying(255),
    username character varying(255),
    id_role character varying(255)
);

CREATE TABLE s_user_password (
    id character varying(255) NOT NULL,
    password character varying(255),
    id_user character varying(255)
);

ALTER TABLE ONLY produk
    ADD CONSTRAINT produk_pkey PRIMARY KEY (id);

ALTER TABLE ONLY s_permission
    ADD CONSTRAINT s_permission_pkey PRIMARY KEY (id);

ALTER TABLE ONLY s_role_permission
    ADD CONSTRAINT s_role_permission_pkey PRIMARY KEY (id_role, id_permission);

ALTER TABLE ONLY s_role
    ADD CONSTRAINT s_role_pkey PRIMARY KEY (id);

ALTER TABLE ONLY s_user_password
    ADD CONSTRAINT s_user_password_pkey PRIMARY KEY (id);

ALTER TABLE ONLY s_user
    ADD CONSTRAINT s_user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY s_role_permission
    ADD CONSTRAINT fk9cj7fdg3hf41td1n0vebmfa5x FOREIGN KEY (id_role) REFERENCES s_role(id);

ALTER TABLE ONLY s_user
    ADD CONSTRAINT fk9g0pu3y0px1480kjlac10qd80 FOREIGN KEY (id_role) REFERENCES s_role(id);

ALTER TABLE ONLY s_user_password
    ADD CONSTRAINT fkawyesj4b8c49ofvrmpa3lx3lv FOREIGN KEY (id_user) REFERENCES s_user(id);

ALTER TABLE ONLY s_role_permission
    ADD CONSTRAINT fkctdvc3x67tpy90xh905iiautx FOREIGN KEY (id_permission) REFERENCES s_permission(id);
