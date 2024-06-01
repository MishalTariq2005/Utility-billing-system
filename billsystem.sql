create database bill_system;
use bill_system;

create table Register(meter_no varchar(20),name varchar(30),email varchar(50), password varchar(30), phoneno varchar(11));
select * from Register;
ALTER TABLE Register MODIFY COLUMN password VARCHAR(64);
delete from Register;
drop table Register;
create table Register(id INT auto_increment primary key,meter_no varchar(20),name varchar(30),email varchar(50), password varchar(30), phoneno varchar(11));
select * from Register;
ALTER TABLE Register MODIFY COLUMN password VARCHAR(64);
CREATE TABLE electricity (
    register_id INT,
    username VARCHAR(30),
    phoneno VARCHAR(11),
    units_consumed INT,
    bill_amount DECIMAL(10, 2),
    billing_month DATE,
    connection_date DATE,
    FOREIGN KEY (register_id) REFERENCES Register(id);
    }
    drop table Register;
    drop table electricity;
    CREATE TABLE Register (
    id INT AUTO_INCREMENT PRIMARY KEY,
    meter_no VARCHAR(20),
    name VARCHAR(30),
    email VARCHAR(50),
    password VARCHAR(64),
    phoneno VARCHAR(11),
    join_year INT,
    join_month INT
);

CREATE TABLE electricity (
    register_id INT,
    username VARCHAR(30),
    phoneno VARCHAR(11),
    units_consumed INT,
    bill_amount DECIMAL(10, 2),
    billing_year INT,
    billing_month INT,
    bill_paid BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (register_id) REFERENCES Register(id)
);
select * from Register;
select * from electricity;
