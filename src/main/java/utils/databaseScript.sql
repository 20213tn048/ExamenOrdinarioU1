create database examenU1;
use examenU1;
create table rfc(
	nombre varchar (40) not null,
    apellidoP varchar (40) not null,
    apellidoM varchar (40) not null,
    curp varchar (30) not null,
    born varchar(20) not null,
    rfc varchar (30) not null
);

select * from rfc;

drop table rfc;