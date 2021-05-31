SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Curso`;
DROP TABLE IF EXISTS `Asignatura`;
DROP TABLE IF EXISTS `Unidad`;
DROP TABLE IF EXISTS `Alumno`;
DROP TABLE IF EXISTS `CursoReferencia`;
DROP TABLE IF EXISTS `CursoReferencia_Alumno`;
DROP TABLE IF EXISTS `Usuario`;
DROP TABLE IF EXISTS `Asignatura_cursoReferencia_profesor`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `Curso` (
    `nivel` VARCHAR(30) NOT NULL,
    `tipoDivisionAnual` VARCHAR(30) NOT NULL,
		`estado` VARCHAR(50) DEFAULT 'Habilitado',
    PRIMARY KEY (`nivel`)
);

CREATE TABLE `Asignatura` (
    `nombre` VARCHAR(100) NOT NULL,
		`estado` VARCHAR(50) DEFAULT 'Habilitado',
    `refCurso` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`nombre`, `refCurso`)
);

CREATE TABLE `Unidad` (
    `nombre` VARCHAR(100) NOT NULL,
    `numero` int NOT NULL,
    `divisionAnual` int NOT NULL,
		`estado` VARCHAR(50) DEFAULT 'Habilitado',
    `refAsignatura` VARCHAR(100) NOT NULL,
    `refCurso` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`nombre`)
);

CREATE TABLE `Alumno` (
    `nombre` VARCHAR(100) NOT NULL,
    `run` VARCHAR(50) NOT NULL,
    `edad` int NOT NULL,
		`estado` VARCHAR(50) DEFAULT 'Habilitado',
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
    `tipoUsuario` VARCHAR(50) NOT NULL,
    `especialidad` VARCHAR(100),
    `estado` VARCHAR(50) DEFAULT 'Habilitado',
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

ALTER TABLE `Asignatura` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`nivel`);
ALTER TABLE `Unidad` ADD FOREIGN KEY (`refAsignatura`, `refCurso`) REFERENCES `Asignatura`(`nombre`,`refCurso`);
ALTER TABLE `Alumno` ADD FOREIGN KEY (`refApoderado`) REFERENCES `Usuario`(`run`);
ALTER TABLE `CursoReferencia` ADD FOREIGN KEY (`refCurso`) REFERENCES `Curso`(`nivel`);
ALTER TABLE `CursoReferencia` ADD FOREIGN KEY (`refProfesorEncargado`) REFERENCES `Usuario`(`run`);
ALTER TABLE `CursoReferencia_Alumno` ADD FOREIGN KEY (`refAlumno`) REFERENCES `Alumno`(`run`);
ALTER TABLE `CursoReferencia_Alumno` ADD FOREIGN KEY (`refLetraCurso`, `refAñoCurso`) REFERENCES `CursoReferencia`(`letra`,`año`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refAsignatura`, `refCursoAsignatura`) REFERENCES `Asignatura`(`nombre`,`refCurso`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refLetraCurso`, `refAñoCurso`) REFERENCES `CursoReferencia`(`letra`,`año`);
ALTER TABLE `Asignatura_cursoReferencia_profesor` ADD FOREIGN KEY (`refProfesor`) REFERENCES `Usuario`(`run`);