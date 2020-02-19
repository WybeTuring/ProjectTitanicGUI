CREATE DATABASE TerraDb;
Use TerraDb;

CREATE TABLE OBSERVATORY(
OID mediumint auto_increment primary key,
name varchar(255) not null,
country varchar(255),
startYear year,
area int
);

CREATE TABLE GALAMSEY(
GID mediumint auto_increment primary key,
observeName varchar(255) not null,
vegColour enum("Brown", "Yellow", "Green"),
colourValue enum("1", "2", "3"),
lat float,
longi float,
eventYear year)
; 

Insert into OBSERVATORY values(1, 'Kumasi Observatory', 'Armenia', '2008', 456.89);
Insert into OBSERVATORY values(2, 'Accra Observatory', 'Lesotho', '2008', 43.78);
Insert into OBSERVATORY values(3, 'Koforidua Observatory', 'Ghana', '2008', 67.90);
Insert into OBSERVATORY values(4, 'Ho Observatory', 'Puerto Rico', '2008', 2345.87);
Insert into OBSERVATORY values(5, 'Takoradi Observatory', 'Benin', '2008', 34.90);

Insert into GALAMSEY values(1,'Kumasi Observatory', 'Yellow', '2', 45.8, 90.6,'1998');
Insert into GALAMSEY values(2,'Ho Observatory','Green', '3', -89, 90.6,'2006');
Insert into GALAMSEY values(3,'Kumasi Observatory','Green', '3', 45, 90.6, '2002');
Insert into GALAMSEY values(4,'Accra Observatory','Brown', '1', 29.6, 90.6,'1999');
Insert into GALAMSEY values(5,'Takoradi Observatory', 'Yellow', '2', 34.7, 90.6,'2002');

select *
from galamsey
where colourValue > 2;
