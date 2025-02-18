CREATE TABLE employees
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(15) NOT NULL,
    surname VARCHAR(15) NOT NULL,
    birth_date DATE NOT NULL,
    nationality VARCHAR(20) NOT NULL,
    address VARCHAR(150) NOT NULL,
    gender VARCHAR(15) NOT NULL,
    availability VARCHAR(15) NOT NULL,
    employment_status VARCHAR(15) NOT NULL,
    modality_type VARCHAR(15) NOT NULL,
    id_number VARCHAR(13) NOT NULL,
    nuit VARCHAR(9) NOT NULL,
    salary NUMERIC(7, 2) NOT NULL,
    email VARCHAR(32) NOT NULL,
    phone_number VARCHAR(9) NOT NULL,
    started_at DATE NOT NULL,
    terminated_at DATE ,
    contract_years SMALLINT,
    social_security_number VARCHAR(9) NOT NULL,

    CONSTRAINT pk_employees PRIMARY KEY (id)
);

CREATE TABLE employee_roles
(
    employee_id BIGINT NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (employee_id, role),
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);