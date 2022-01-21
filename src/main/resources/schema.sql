
DROP TABLE IF EXISTS Alumnos;
create table Alumnos (matricula varchar(10), nombre varchar(25), paterno varchar(25), fnac date, estatura decimal(10,2), PRIMARY KEY (matricula));

DROP TABLE IF EXISTS Calificaciones;
create table Calificaciones(id integer not null primary key, materia varchar(30), calificacion integer, alumnos_matricula varchar(10));
