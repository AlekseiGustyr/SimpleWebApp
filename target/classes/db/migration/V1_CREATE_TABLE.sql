create table hibernate_sequence(
    next_val bigint
) engine=MyISAM




CREATE TABLE IF NOT EXISTS employee(
  employee_id BIGINT UNIQUE AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  department_id BIGINT NOT NULL,
  job_tittle VARCHAR(255) NOT NULL,
  gender VARCHAR(255) NOT NULL,
  date_of_birth TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT pk_employee PRIMARY KEY (employee_id)
)engine=MyISAM;
