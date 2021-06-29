SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Apoderado`;
DROP TABLE IF EXISTS `Alumno`;
DROP TABLE IF EXISTS `Curso`;
DROP TABLE IF EXISTS `Asignatura`;
DROP TABLE IF EXISTS `CursoAsignatura`;
DROP TABLE IF EXISTS `Unidad`;
DROP TABLE IF EXISTS `AsignaturaReferencia`;
DROP TABLE IF EXISTS `Asistencia`;
DROP TABLE IF EXISTS `Profesor`;
DROP TABLE IF EXISTS `Actividad`;
DROP TABLE IF EXISTS `AlumnoActividad`;
DROP TABLE IF EXISTS `CursoAlumno`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `Apoderado` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    `clave` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`run`)
);

CREATE TABLE `Alumno` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `edad` int NOT NULL,
    `refApoderado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`run`)
);

CREATE TABLE `Curso` (
    `id` int NOT NULL,
    `nivel` VARCHAR(30) NOT NULL,
    `tipoDivAnual` VARCHAR(30) NOT NULL,
    `letra` VARCHAR(1) NOT NULL,
    `año` int NOT NULL,
    `refProfesor` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Asignatura` (
    `id` int NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `CursoAsignatura` (
    `refCurso` int NOT NULL,
    `refAsignatura` int NOT NULL,
    PRIMARY KEY (`refCurso`, `refAsignatura`)
);

CREATE TABLE `Unidad` (
    `id` int NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `numero` int NOT NULL,
    `divisionAnual` int NOT NULL,
    `refCurso` int NOT NULL,
    `refAsignatura` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `AsignaturaReferencia` (
    `id` int NOT NULL,
    `refCurso` int NOT NULL,
    `refAsignatura` int NOT NULL,
    `refProfesor` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`, `refCurso`, `refAsignatura`)
);

CREATE TABLE `Asistencia` (
    `refAsignaturaReferencia` int NOT NULL,
    `refAlumno` VARCHAR(50) NOT NULL,
    `fecha` DATE NOT NULL,
    `presente` BOOLEAN NOT NULL,
    PRIMARY KEY (`refAsignaturaReferencia`, `refAlumno`)
);

CREATE TABLE `Profesor` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `especialdidad` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`run`)
);

CREATE TABLE `Actividad` (
    `id` int NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `tipo` VARCHAR(100) NOT NULL,
    `refUnidad` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `AlumnoActividad` (
    `refActividad` int NOT NULL,
    `refAlumno` VARCHAR(50) NOT NULL,
    `nota` int NOT NULL,
    PRIMARY KEY (`refActividad`, `refAlumno`)
);

CREATE TABLE `CursoAlumno` (
    `refCurso` int NOT NULL,
    `refAlumno` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`refCurso`, `refAlumno`)
);

ALTER TABLE `Alumno` ADD FOREIGN KEY (`refApoderado`) REFERENCES `Apoderado`(`run`);
ALTER TABLE `Curso` ADD FOREIGN KEY (`refProfesor`) REFERENCES `Profesor`(`run`);
ALTER TABLE `CursoAsignatura` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`id`);
ALTER TABLE `CursoAsignatura` ADD FOREIGN KEY (`refAsignatura`) REFERENCES `Asignatura`(`id`);
ALTER TABLE `Unidad` ADD FOREIGN KEY (`refCurso`, `refAsignatura`) REFERENCES `CursoAsignatura`(`refCurso`,`refAsignatura`);
ALTER TABLE `AsignaturaReferencia` ADD FOREIGN KEY (`refCurso`, `refAsignatura`) REFERENCES `CursoAsignatura`(`refCurso`,`refAsignatura`);
ALTER TABLE `AsignaturaReferencia` ADD FOREIGN KEY (`refProfesor`) REFERENCES `Profesor`(`run`);
ALTER TABLE `Asistencia` ADD FOREIGN KEY (`refAlumno`) REFERENCES `Alumno`(`run`);
ALTER TABLE `Asistencia` ADD FOREIGN KEY (`refAsignaturaReferencia`) REFERENCES `AsignaturaReferencia`(`id`);
ALTER TABLE `Actividad` ADD FOREIGN KEY (`refUnidad`) REFERENCES `Unidad`(`id`);
ALTER TABLE `AlumnoActividad` ADD FOREIGN KEY (`refAlumno`) REFERENCES `Alumno`(`run`);
ALTER TABLE `AlumnoActividad` ADD FOREIGN KEY (`refActividad`) REFERENCES `Actividad`(`id`);
ALTER TABLE `CursoAlumno` ADD FOREIGN KEY (`refAlumno`) REFERENCES `Alumno`(`run`);
ALTER TABLE `CursoAlumno` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`id`);