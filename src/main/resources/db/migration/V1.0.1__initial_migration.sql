ALTER TABLE employees
    ADD CONSTRAINT uq_employees_email UNIQUE (email),
    ADD CONSTRAINT uq_employees_id_number UNIQUE (id_number),
    ADD CONSTRAINT uq_employees_nuit UNIQUE (nuit),
    ADD CONSTRAINT uq_employees_social_security_number UNIQUE (social_security_number);
