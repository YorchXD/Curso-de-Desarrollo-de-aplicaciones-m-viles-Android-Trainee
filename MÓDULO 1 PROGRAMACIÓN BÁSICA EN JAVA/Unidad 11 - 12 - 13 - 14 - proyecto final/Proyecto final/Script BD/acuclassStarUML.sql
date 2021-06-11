SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Curso`;
DROP TABLE IF EXISTS `Unidad`;
DROP TABLE IF EXISTS `Alumno`;
DROP TABLE IF EXISTS `CursoReferencia`;
DROP TABLE IF EXISTS `CursoReferencia_Alumno`;
DROP TABLE IF EXISTS `Usuario`;
DROP TABLE IF EXISTS `Asignatura_cursoReferencia_profesor`;
DROP TABLE IF EXISTS `Rol`;
DROP TABLE IF EXISTS `Modulo`;
DROP TABLE IF EXISTS `Operaciones`;
DROP TABLE IF EXISTS `Rol_Operacion`;
DROP TABLE IF EXISTS `Usuario_Rol`;
DROP TABLE IF EXISTS `Asignatura`;
DROP TABLE IF EXISTS `Curso_Asignatura`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `Curso` (
    `id` int NOT NULL,
    `nivel` VARCHAR(30) NOT NULL,
    `tipoDivisionAnual` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Unidad` (
    `nombre` VARCHAR(100) NOT NULL,
    `numero` int NOT NULL,
    `divisionAnual` int NOT NULL,
    `refCursoAsignatura` int NOT NULL,
    PRIMARY KEY (`nombre`)
);

CREATE TABLE `Alumno` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `edad` int NOT NULL,
    `refApoderado` VARCHAR(50),
    PRIMARY KEY (`run`)
);

CREATE TABLE `CursoReferencia` (
    `id` int NOT NULL,
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

CREATE TABLE `Usuario` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(100) NOT NULL,
    `refRol` VARCHAR(50) NOT NULL,
    `especialidad` VARCHAR(100),
    `estado` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `clave` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`run`)
);

CREATE TABLE `Asignatura_cursoReferencia_profesor` (
    `refCursoReferencia` int NOT NULL,
    `refCursoAsignatura` int NOT NULL,
    `refProfesor` int NOT NULL
);

CREATE TABLE `Rol` (
    `id` int NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Modulo` (
    `id` int NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Operaciones` (
    `id` int NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    `refModulo` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Rol_Operacion` (
    `id` int NOT NULL,
    `refRol` int NOT NULL,
    `refOperacion` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Usuario_Rol` (
    `id` int NOT NULL,
    `refUsuario` int NOT NULL,
    `refRol` int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Asignatura` (
    `id` int NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Curso_Asignatura` (
    `id` int NOT NULL,
    `refCurso` int NOT NULL,
    `refAsignatura` int NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `Unidad` ADD FOREIGN KEY (`refCursoAsignatura`) REFERENCES `Curso_Asignatura`(`id`);
ALTER TABLE `Alumno` ADD FOREIGN KEY (`refApoderado`) REFERENCES `Usuario`(`run`);
ALTER TABLE `CursoReferencia` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`id`);
ALTER TABLE `CursoReferencia` ADD FOREIGN KEY (`refProfesorEncargado`) REFERENCES `Usuario`(`run`);
ALTER TABLE `CursoReferencia_Alumno` ADD FOREIGN KEY (`refAlumno`) REFERENCES `Alumno`(`run`);
ALTER TABLE `CursoReferencia_Alumno` ADD FOREIGN KEY (`refCursoReferencia`) REFERENCES `CursoReferencia`(`id`);
ALTER TABLE `Usuario` ADD FOREIGN KEY (`refRol`) REFERENCES `Rol`(`id`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refCursoReferencia`) REFERENCES `CursoReferencia`(`id`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refProfesor`) REFERENCES `Usuario`(`run`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refCursoAsignatura`) REFERENCES `Curso_Asignatura`(`id`);
ALTER TABLE `Operaciones` ADD FOREIGN KEY (`refModulo`) REFERENCES `Modulo`(`id`);
ALTER TABLE `Rol_Operacion` ADD FOREIGN KEY (`refRol`) REFERENCES `Rol`(`id`);
ALTER TABLE `Rol_Operacion` ADD FOREIGN KEY (`refOperacion`) REFERENCES `Operaciones`(`id`);
ALTER TABLE `Usuario_Rol` ADD FOREIGN KEY (`refUsuario`) REFERENCES `Usuario`(`run`);
ALTER TABLE `Usuario_Rol` ADD FOREIGN KEY (`refRol`) REFERENCES `Rol`(`id`);
ALTER TABLE `Curso_Asignatura` ADD FOREIGN KEY (`refAsignatura`) REFERENCES `Asignatura`(`id`);
ALTER TABLE `Curso_Asignatura` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`id`);