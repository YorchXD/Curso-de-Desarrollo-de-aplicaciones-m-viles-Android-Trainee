SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Curso`;
DROP TABLE IF EXISTS `Asignatura`;
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
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `Curso` (
    `nivel` VARCHAR(30) NOT NULL,
    `tipoDivisionAnual` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`nivel`)
);

CREATE TABLE `Asignatura` (
    `nombre` VARCHAR(100) NOT NULL,
    `refCurso` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`nombre`, `refCurso`)
);

CREATE TABLE `Unidad` (
    `nombre` VARCHAR(100) NOT NULL,
    `numero` int NOT NULL,
    `divisionAnual` int NOT NULL,
    `refAsignatura` VARCHAR(100) NOT NULL,
    `refCurso` VARCHAR(30) NOT NULL,
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
    `letra` VARCHAR(1) NOT NULL,
    `año` int NOT NULL,
    `refCurso` VARCHAR(50) NOT NULL,
    `refProfesorEncargado` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`letra`, `año`)
);

CREATE TABLE `CursoReferencia_Alumno` (
    `refLetraCurso` VARCHAR(1) NOT NULL,
    `refAñoCurso` Int NOT NULL,
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
    `refLetraCurso` VARCHAR(1) NOT NULL,
    `refAñoCurso` int NOT NULL,
    `refAsignatura` VARCHAR(100) NOT NULL,
    `refCursoAsignatura` VARCHAR(30) NOT NULL,
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

ALTER TABLE `Asignatura` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`nivel`);
ALTER TABLE `Unidad` ADD FOREIGN KEY (`refAsignatura`, `refCurso`) REFERENCES `Asignatura`(`nombre`,`refCurso`);
ALTER TABLE `Alumno` ADD FOREIGN KEY (`refApoderado`) REFERENCES `Usuario`(`run`);
ALTER TABLE `CursoReferencia` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`nivel`);
ALTER TABLE `CursoReferencia` ADD FOREIGN KEY (`refProfesorEncargado`) REFERENCES `Usuario`(`run`);
ALTER TABLE `CursoReferencia_Alumno` ADD FOREIGN KEY (`refAlumno`) REFERENCES `Alumno`(`run`);
ALTER TABLE `CursoReferencia_Alumno` ADD FOREIGN KEY (`refLetraCurso`, `refAñoCurso`) REFERENCES `CursoReferencia`(`letra`,`año`);
ALTER TABLE `Usuario` ADD FOREIGN KEY (`refRol`) REFERENCES `Rol`(`id`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refAsignatura`, `refCursoAsignatura`) REFERENCES `Asignatura`(`nombre`,`refCurso`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refLetraCurso`, `refAñoCurso`) REFERENCES `CursoReferencia`(`letra`,`año`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refProfesor`) REFERENCES `Usuario`(`run`);
ALTER TABLE `Operaciones` ADD FOREIGN KEY (`refModulo`) REFERENCES `Modulo`(`id`);
ALTER TABLE `Rol_Operacion` ADD FOREIGN KEY (`refRol`) REFERENCES `Rol`(`id`);
ALTER TABLE `Rol_Operacion` ADD FOREIGN KEY (`refOperacion`) REFERENCES `Operaciones`(`id`);
ALTER TABLE `Usuario_Rol` ADD FOREIGN KEY (`refUsuario`) REFERENCES `Usuario`(`run`);
ALTER TABLE `Usuario_Rol` ADD FOREIGN KEY (`refRol`) REFERENCES `Rol`(`id`);