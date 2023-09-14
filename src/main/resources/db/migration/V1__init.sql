CREATE TABLE IF NOT EXISTS users
(
    uuid character(36) NOT NULL,
    social_security_number character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    first_name character varying(50),
    phone character(10),
    email character varying(50),
    password character varying(100),
    father_name character varying(50),
    mother_name character varying(50),
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT users_phone_key UNIQUE (phone),
    CONSTRAINT users_ssn_key UNIQUE (social_security_number)
    );


-- ALTER TABLE IF EXISTS users
--     OWNER to dimpetr;

CREATE TABLE IF NOT EXISTS roles
(
    uuid character(36) NOT NULL,
    name character varying(50) NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (uuid),
    CONSTRAINT roles_name_key UNIQUE (name)
    );

-- ALTER TABLE IF EXISTS roles
--     OWNER to dimpetr;

CREATE TABLE IF NOT EXISTS users_roles
(
    user_uuid character(36) NOT NULL,
    role_uuid character(36) NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_uuid, role_uuid),
    CONSTRAINT users_roles_role_uuid_fkey FOREIGN KEY (role_uuid)
    REFERENCES roles (uuid) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT
    NOT VALID,
    CONSTRAINT users_roles_user_uuid_fkey FOREIGN KEY (user_uuid)
    REFERENCES users (uuid) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT
    NOT VALID
    );


-- ALTER TABLE IF EXISTS users_roles
--     OWNER to dimpetr;
