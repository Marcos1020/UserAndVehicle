create table CARS_TB(
ID_CAR bigint(50) NOT NULL AUTO_INCREMENT,
ID_USER bigint(50) NOT NULL,
LICENSE_PLATE varchar(20),
COUNTRY varchar(20)NOT null,
STATE varchar(50) NOT null,
CITY varchar(80)NOT null,
VEHICLE_MODEL varchar(70),
PRIMARY KEY(`ID_CAR`));