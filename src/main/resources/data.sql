
-- Enable these 2 lines for the very first run, then you must disable on subsequent runs
DROP SCHEMA IF EXISTS POINTS;
CREATE SCHEMA POINTS;

SET SCHEMA POINTS;

--  Dropping the transactions table first, because it has a Foreign constraint on Customers table
DROP TABLE IF EXISTS TRANSACTIONS;

DROP TABLE IF EXISTS CUSTOMERS;

CREATE TABLE POINTS.CUSTOMERS (
    customer_id integer identity unique not null,
    telephone_number varchar(255),
    email_address varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    status varchar(255),
    user_name varchar(255),
    primary key (customer_id));

INSERT INTO POINTS.CUSTOMERS VALUES (1, '234-563-3331', 'jen@gmail.com', 'Jennifer', 'Aniston', 'active', 'jena123');

INSERT INTO POINTS.CUSTOMERS VALUES (2, '345-221-3231', 'wills@gmail.com', 'Will', 'Smith', 'active', 'wills123');


CREATE TABLE POINTS.TRANSACTIONS (
    customer_id integer,
    transaction_id integer identity unique not null,
    transaction_name varchar(255) not null,
    transaction_date date not null,
    transaction_amount decimal (10,2) not null,
    foreign key (customer_id) references POINTS.CUSTOMERS(customer_id),
    primary key (transaction_id));


INSERT INTO POINTS.TRANSACTIONS (customer_id, transaction_name, transaction_date, transaction_amount)
VALUES (2, 'HYATT Hotel', '2020-01-01', '234.23');

INSERT INTO POINTS.TRANSACTIONS (customer_id, transaction_name, transaction_date, transaction_amount)
VALUES (2, 'Hibachi Grill', '2020-01-02', '104.52');

INSERT INTO POINTS.TRANSACTIONS (customer_id, transaction_name, transaction_date, transaction_amount)
VALUES (1, 'Renaissance Fair', '2020-01-20', '23.03');

INSERT INTO POINTS.TRANSACTIONS (customer_id, transaction_name, transaction_date, transaction_amount)
VALUES (1, 'World of Nations', '2020-01-21', '34.13');