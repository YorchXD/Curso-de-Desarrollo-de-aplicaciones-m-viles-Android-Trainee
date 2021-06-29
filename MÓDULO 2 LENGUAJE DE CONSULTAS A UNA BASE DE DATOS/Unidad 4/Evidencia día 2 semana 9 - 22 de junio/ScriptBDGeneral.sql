SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Curso`;
DROP TABLE IF EXISTS `Unidad`;
DROP TABLE IF EXISTS `Alumno`;
DROP TABLE IF EXISTS `CursoReferencia`;
DROP TABLE IF EXISTS `CursoReferencia_Alumno`;
DROP TABLE IF EXISTS `AsignaturaReferencia`;
DROP TABLE IF EXISTS `Asignatura`;
DROP TABLE IF EXISTS `Curso_Asignatura`;
DROP TABLE IF EXISTS `Profesor`;
DROP TABLE IF EXISTS `Apoderado`;
DROP TABLE IF EXISTS `Admistrador`;
DROP TABLE IF EXISTS `Actividad`;
DROP TABLE IF EXISTS `Nota`;
DROP TABLE IF EXISTS `Asistencia`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `Curso` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nivel` VARCHAR(30) NOT NULL,
    `tipoDivisionAnual` VARCHAR(30) NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Unidad` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    `numero` int NOT NULL,
    `divisionAnual` int NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    `refCurso` int NOT NULL,
    `refAsignatura` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Alumno` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `edad` int NOT NULL,
    `refApoderado` VARCHAR(50),
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`run`)
);

CREATE TABLE `CursoReferencia` (
    `id` int NOT NULL AUTO_INCREMENT,
    `letra` VARCHAR(1) NOT NULL,
    `año` int NOT NULL,
    `refCurso` int NOT NULL,
    `refProfesorEncargado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `CursoReferencia_Alumno` (
    `refCursoReferencia` Int NOT NULL,
    `refAlumno` VARCHAR(50) NOT NULL
);

CREATE TABLE `AsignaturaReferencia` (
    `id` int NOT NULL AUTO_INCREMENT,
    `refCursoReferencia` int NOT NULL,
    `refCurso` int NOT NULL,
    `refAsignatura` int NOT NULL,
    `refProfesor` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Asignatura` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Curso_Asignatura` (
    `refCurso` int NOT NULL,
    `refAsignatura` int NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`refCurso`, `refAsignatura`)
);

CREATE TABLE `Profesor` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `especialidad` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `clave` VARCHAR(100) NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`run`)
);

CREATE TABLE `Apoderado` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `clave` VARCHAR(100) NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`run`)
);

CREATE TABLE `Admistrador` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `clave` VARCHAR(100) NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`run`)
);

CREATE TABLE `Actividad` (
    `id` int NOT NULL AUTO_INCREMENT,
    `tipo` VARCHAR(50) NOT NULL,
    `fecha` DATE NOT NULL,
    `refUnidad` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Nota` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nota` int NOT NULL,
    `refAlumno` VARCHAR(50) NOT NULL,
    `refActividad` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Asistencia` (
    `fecha` DATE NOT NULL,
    `presente` VARCHAR(50) NOT NULL,
    `refAlumno` VARCHAR(50) NOT NULL,
    `refAsignaturaReferencia` int NOT NULL,
    PRIMARY KEY (`fecha`, `refAlumno`, `refAsignaturaReferencia`)
);

ALTER TABLE `Unidad` ADD FOREIGN KEY (`refCurso`, `refAsignatura`) REFERENCES `Curso_Asignatura`(`refCurso`,`refAsignatura`);
ALTER TABLE `Alumno` ADD FOREIGN KEY (`refApoderado`) REFERENCES `Apoderado`(`run`);
ALTER TABLE `CursoReferencia` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`id`);
ALTER TABLE `CursoReferencia` ADD FOREIGN KEY (`refProfesorEncargado`) REFERENCES `Profesor`(`run`);
ALTER TABLE `CursoReferencia_Alumno` ADD FOREIGN KEY (`refAlumno`) REFERENCES `Alumno`(`run`);
ALTER TABLE `CursoReferencia_Alumno` ADD FOREIGN KEY (`refCursoReferencia`) REFERENCES `CursoReferencia`(`id`);
ALTER TABLE `AsignaturaReferencia` ADD FOREIGN KEY (`refCursoReferencia`) REFERENCES `CursoReferencia`(`id`);
ALTER TABLE `AsignaturaReferencia` ADD FOREIGN KEY (`refCurso`, `refAsignatura`) REFERENCES `Curso_Asignatura`(`refCurso`,`refAsignatura`);
ALTER TABLE `AsignaturaReferencia` ADD FOREIGN KEY (`refProfesor`) REFERENCES `Profesor`(`run`);
ALTER TABLE `Curso_Asignatura` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`id`);
ALTER TABLE `Curso_Asignatura` ADD FOREIGN KEY (`refAsignatura`) REFERENCES `Asignatura`(`id`);
ALTER TABLE `Actividad` ADD FOREIGN KEY (`refUnidad`) REFERENCES `Unidad`(`id`);
ALTER TABLE `Asistencia` ADD FOREIGN KEY (`refAlumno`) REFERENCES `Alumno`(`run`);
ALTER TABLE `Asistencia` ADD FOREIGN KEY (`refAsignaturaReferencia`) REFERENCES `AsignaturaReferencia`(`id`);