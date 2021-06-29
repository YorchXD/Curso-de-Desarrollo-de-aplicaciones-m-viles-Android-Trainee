/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 100136
 Source Host           : localhost:3306
 Source Schema         : acuclass

 Target Server Type    : MySQL
 Target Server Version : 100136
 File Encoding         : 65001

 Date: 29/06/2021 18:15:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actividad
-- ----------------------------
DROP TABLE IF EXISTS `actividad`;
CREATE TABLE `actividad`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fecha` date NOT NULL,
  `refUnidad` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refUnidad`(`refUnidad`) USING BTREE,
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`refUnidad`) REFERENCES `unidad` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for administrador
-- ----------------------------
DROP TABLE IF EXISTS `administrador`;
CREATE TABLE `administrador`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `run` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `clave` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`run`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for alumno
-- ----------------------------
DROP TABLE IF EXISTS `alumno`;
CREATE TABLE `alumno`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `run` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `edad` int NOT NULL,
  `refApoderado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`run`) USING BTREE,
  INDEX `refApoderado`(`refApoderado`) USING BTREE,
  CONSTRAINT `alumno_ibfk_1` FOREIGN KEY (`refApoderado`) REFERENCES `apoderado` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for apoderado
-- ----------------------------
DROP TABLE IF EXISTS `apoderado`;
CREATE TABLE `apoderado`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `run` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `clave` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`run`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for asignatura
-- ----------------------------
DROP TABLE IF EXISTS `asignatura`;
CREATE TABLE `asignatura`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for asignaturareferencia
-- ----------------------------
DROP TABLE IF EXISTS `asignaturareferencia`;
CREATE TABLE `asignaturareferencia`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `refCursoReferencia` int NOT NULL,
  `refCurso` int NOT NULL,
  `refAsignatura` int NOT NULL,
  `refProfesor` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refCursoReferencia`(`refCursoReferencia`) USING BTREE,
  INDEX `refCurso`(`refCurso`, `refAsignatura`) USING BTREE,
  INDEX `refProfesor`(`refProfesor`) USING BTREE,
  CONSTRAINT `asignaturareferencia_ibfk_1` FOREIGN KEY (`refCursoReferencia`) REFERENCES `cursoreferencia` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `asignaturareferencia_ibfk_2` FOREIGN KEY (`refCurso`, `refAsignatura`) REFERENCES `curso_asignatura` (`refCurso`, `refAsignatura`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `asignaturareferencia_ibfk_3` FOREIGN KEY (`refProfesor`) REFERENCES `profesor` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for asistencia
-- ----------------------------
DROP TABLE IF EXISTS `asistencia`;
CREATE TABLE `asistencia`  (
  `fecha` date NOT NULL,
  `presente` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refAlumno` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refAsignaturaReferencia` int NOT NULL,
  PRIMARY KEY (`fecha`, `refAlumno`, `refAsignaturaReferencia`) USING BTREE,
  INDEX `refAlumno`(`refAlumno`) USING BTREE,
  INDEX `refAsignaturaReferencia`(`refAsignaturaReferencia`) USING BTREE,
  CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`refAlumno`) REFERENCES `alumno` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `asistencia_ibfk_2` FOREIGN KEY (`refAsignaturaReferencia`) REFERENCES `asignaturareferencia` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for curso
-- ----------------------------
DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nivel` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tipoDivisionAnual` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for curso_asignatura
-- ----------------------------
DROP TABLE IF EXISTS `curso_asignatura`;
CREATE TABLE `curso_asignatura`  (
  `refCurso` int NOT NULL,
  `refAsignatura` int NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`refCurso`, `refAsignatura`) USING BTREE,
  INDEX `refAsignatura`(`refAsignatura`) USING BTREE,
  CONSTRAINT `curso_asignatura_ibfk_1` FOREIGN KEY (`refCurso`) REFERENCES `curso` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `curso_asignatura_ibfk_2` FOREIGN KEY (`refAsignatura`) REFERENCES `asignatura` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cursoreferencia
-- ----------------------------
DROP TABLE IF EXISTS `cursoreferencia`;
CREATE TABLE `cursoreferencia`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `refLetra` int NOT NULL,
  `anio` int NOT NULL,
  `refCurso` int NOT NULL,
  `refProfesorEncargado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refCurso`(`refCurso`) USING BTREE,
  INDEX `refProfesorEncargado`(`refProfesorEncargado`) USING BTREE,
  INDEX `refLetra`(`refLetra`) USING BTREE,
  CONSTRAINT `cursoreferencia_ibfk_1` FOREIGN KEY (`refCurso`) REFERENCES `curso` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cursoreferencia_ibfk_2` FOREIGN KEY (`refProfesorEncargado`) REFERENCES `profesor` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cursoreferencia_ibfk_3` FOREIGN KEY (`refLetra`) REFERENCES `letracurso` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cursoreferencia_alumno
-- ----------------------------
DROP TABLE IF EXISTS `cursoreferencia_alumno`;
CREATE TABLE `cursoreferencia_alumno`  (
  `refCursoReferencia` int NOT NULL,
  `refAlumno` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  INDEX `refAlumno`(`refAlumno`) USING BTREE,
  INDEX `refCursoReferencia`(`refCursoReferencia`) USING BTREE,
  CONSTRAINT `cursoreferencia_alumno_ibfk_1` FOREIGN KEY (`refAlumno`) REFERENCES `alumno` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cursoreferencia_alumno_ibfk_2` FOREIGN KEY (`refCursoReferencia`) REFERENCES `cursoreferencia` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for especialidad
-- ----------------------------
DROP TABLE IF EXISTS `especialidad`;
CREATE TABLE `especialidad`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `nombre`(`nombre`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for letracurso
-- ----------------------------
DROP TABLE IF EXISTS `letracurso`;
CREATE TABLE `letracurso`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `letra` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `letra`(`letra`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for nota
-- ----------------------------
DROP TABLE IF EXISTS `nota`;
CREATE TABLE `nota`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nota` int NOT NULL,
  `refAlumno` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refActividad` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for profesor
-- ----------------------------
DROP TABLE IF EXISTS `profesor`;
CREATE TABLE `profesor`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `run` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `clave` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`run`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for profesorespecialidad
-- ----------------------------
DROP TABLE IF EXISTS `profesorespecialidad`;
CREATE TABLE `profesorespecialidad`  (
  `refProfesor` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refEspecialidad` int NOT NULL,
  PRIMARY KEY (`refProfesor`, `refEspecialidad`) USING BTREE,
  INDEX `refEspecialidad`(`refEspecialidad`) USING BTREE,
  CONSTRAINT `profesorespecialidad_ibfk_1` FOREIGN KEY (`refProfesor`) REFERENCES `profesor` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `profesorespecialidad_ibfk_2` FOREIGN KEY (`refEspecialidad`) REFERENCES `especialidad` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for unidad
-- ----------------------------
DROP TABLE IF EXISTS `unidad`;
CREATE TABLE `unidad`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `numero` int NOT NULL,
  `divisionAnual` int NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  `refCurso` int NOT NULL,
  `refAsignatura` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refCurso`(`refCurso`, `refAsignatura`) USING BTREE,
  CONSTRAINT `unidad_ibfk_1` FOREIGN KEY (`refCurso`, `refAsignatura`) REFERENCES `curso_asignatura` (`refCurso`, `refAsignatura`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Procedure structure for actualizarAlumno
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarAlumno`;
delimiter ;;
CREATE PROCEDURE `actualizarAlumno`(in_run VARCHAR(50), in_nombre VARCHAR(100), in_edad int)
BEGIN
	update alumno 
	set nombre=in_nombre, 
			edad=in_edad 
	where run=in_run;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizarEstadoAlumno
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarEstadoAlumno`;
delimiter ;;
CREATE PROCEDURE `actualizarEstadoAlumno`(in_run VARCHAR(50), in_estado VARCHAR(100))
BEGIN
	update alumno set estado=in_estado where run=in_run;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizarEstadoAsignatura
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarEstadoAsignatura`;
delimiter ;;
CREATE PROCEDURE `actualizarEstadoAsignatura`(`in_refCurso` int, `in_refAsignatura` int, `in_estado` VARCHAR(50))
BEGIN
	UPDATE curso_asignatura
	SET curso_asignatura.estado = in_estado
	WHERE curso_asignatura.refCurso = in_refCurso AND curso_asignatura.refAsignatura = in_refAsignatura;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizarEstadoCurso
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarEstadoCurso`;
delimiter ;;
CREATE PROCEDURE `actualizarEstadoCurso`(`in_nivel` VARCHAR(50), `in_tipoDivAnual` VARCHAR(100), `in_estado` VARCHAR(50))
BEGIN
	UPDATE curso
	SET curso.estado = in_estado
	WHERE curso.nivel = in_nivel AND curso.tipoDivisionAnual = in_tipoDivAnual;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizarEstadoUnidad
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarEstadoUnidad`;
delimiter ;;
CREATE PROCEDURE `actualizarEstadoUnidad`(`in_id` int, `in_estado` VARCHAR(50))
BEGIN
	UPDATE unidad
	SET estado = in_estado
	WHERE id = in_id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizarEstadoUsuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarEstadoUsuario`;
delimiter ;;
CREATE PROCEDURE `actualizarEstadoUsuario`(in_run varchar(100), in_estado varchar(50), in_tipoUsuario varchar(50))
BEGIN
	IF in_tipoUsuario = "PROFESOR" THEN
		UPDATE profesor
		SET estado = in_estado
		WHERE run = in_run;
	ELSE
		UPDATE apoderado
		SET estado = in_estado
		WHERE run = in_run;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizarNombreAsignatura
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarNombreAsignatura`;
delimiter ;;
CREATE PROCEDURE `actualizarNombreAsignatura`(`in_id` INTEGER, `in_nombre` VARCHAR(100))
BEGIN
	UPDATE asignatura
	SET asignatura.nombre = in_nombre
	WHERE asignatura.id = in_id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizarUnidad
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarUnidad`;
delimiter ;;
CREATE PROCEDURE `actualizarUnidad`(`in_nombre` VARCHAR(100), `in_numero_unidad` INTEGER, `in_division_anual` INTEGER, `in_id` INTEGER)
BEGIN
		UPDATE unidad
		SET nombre = in_nombre,
				numero = in_numero_unidad,
				divisionAnual = in_division_anual
		WHERE id = in_id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizarUsuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarUsuario`;
delimiter ;;
CREATE PROCEDURE `actualizarUsuario`(in_nombre varchar(100), in_email varchar(100), in_clave varchar(100), in_especialidad varchar(100), in_run varchar(100), in_tipoUsuario varchar(50))
BEGIN
	IF in_tipoUsuario="PROFESOR" THEN
		UPDATE profesor 
		SET nombre=in_nombre, 
				especialidad=in_especialidad,
				email=in_email, 
				clave=in_clave
		WHERE run=in_run;
	ELSE
		UPDATE apoderado 
		SET nombre=in_nombre,
				email=in_email, 
				clave=in_clave
		WHERE run=in_run;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for asociarAlumnoApoderado
-- ----------------------------
DROP PROCEDURE IF EXISTS `asociarAlumnoApoderado`;
delimiter ;;
CREATE PROCEDURE `asociarAlumnoApoderado`(in_runAlumno VARCHAR(100), in_runApoderado VARCHAR(100))
BEGIN
	UPDATE alumno 
	SET alumno.refApoderado = in_runApoderado
	WHERE alumno.run = in_runAlumno;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for asociarEspecialidadProfesor
-- ----------------------------
DROP PROCEDURE IF EXISTS `asociarEspecialidadProfesor`;
delimiter ;;
CREATE PROCEDURE `asociarEspecialidadProfesor`(IN `in_refProfesor` VARCHAR(50),IN `in_refEspecialidad` int)
BEGIN
	INSERT INTO profesorespecialidad (refProfesor, refEspecialidad)
	VALUES (in_refProfesor, in_refEspecialidad);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for buscarAlumno
-- ----------------------------
DROP PROCEDURE IF EXISTS `buscarAlumno`;
delimiter ;;
CREATE PROCEDURE `buscarAlumno`(in_run VARCHAR(50))
BEGIN
	select * from alumno where run=in_run;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for buscarAsociacionCursoAsignatura
-- ----------------------------
DROP PROCEDURE IF EXISTS `buscarAsociacionCursoAsignatura`;
delimiter ;;
CREATE PROCEDURE `buscarAsociacionCursoAsignatura`(IN `in_refCurso` INTEGER, IN `in_refAsignatura` INTEGER)
BEGIN
		SELECT COUNT(*) AS 'existe'
		FROM curso_asignatura
		WHERE curso_asignatura.refCurso = in_refCurso AND curso_asignatura.refAsignatura=in_refAsignatura;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for buscarCurso
-- ----------------------------
DROP PROCEDURE IF EXISTS `buscarCurso`;
delimiter ;;
CREATE PROCEDURE `buscarCurso`(IN `in_nivel` VARCHAR(30), IN `in_tipoDivAnual` VARCHAR(30))
BEGIN
		SELECT *
		FROM curso
		WHERE curso.nivel = in_nivel AND curso.tipoDivisionAnual=in_tipoDivAnual;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for buscarCursoReferencia
-- ----------------------------
DROP PROCEDURE IF EXISTS `buscarCursoReferencia`;
delimiter ;;
CREATE PROCEDURE `buscarCursoReferencia`(in_refLetra int, in_anio int, in_refCurso int, in_refProfesorEncargado varchar(50))
BEGIN
	SELECT COUNT(*) AS 'existe' 
	FROM cursoreferencia 
	WHERE refLetra=in_refLetra and anio=in_anio and refCurso= in_refCurso and refProfesorEncargado=in_refProfesorEncargado;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for buscarUsuarioRunTipo
-- ----------------------------
DROP PROCEDURE IF EXISTS `buscarUsuarioRunTipo`;
delimiter ;;
CREATE PROCEDURE `buscarUsuarioRunTipo`(in_run varchar(100),in_tipoUsuario varchar(50))
BEGIN
	IF in_tipoUsuario = "PROFESOR" THEN
		SELECT *
		FROM profesor
		WHERE profesor.run = in_run;
	ELSE
		SELECT *
		FROM apoderado
		WHERE apoderado.run = in_run;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for desvincularEspecialidadProfesor
-- ----------------------------
DROP PROCEDURE IF EXISTS `desvincularEspecialidadProfesor`;
delimiter ;;
CREATE PROCEDURE `desvincularEspecialidadProfesor`(IN `in_refProfesor` VARCHAR(50),IN `in_refEspecialidad` int)
BEGIN
	DELETE FROM profesorespecialidad
		WHERE profesorespecialidad.refProfesor = in_refProfesor AND profesorespecialidad.refEspecialidad = in_refEspecialidad;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for iniciarSesion
-- ----------------------------
DROP PROCEDURE IF EXISTS `iniciarSesion`;
delimiter ;;
CREATE PROCEDURE `iniciarSesion`(IN `in_email` VARCHAR(100), IN `in_clave` VARCHAR(100), in_tipoUsuario VARCHAR(50))
BEGIN
		IF in_tipoUsuario = 'ADMINISTRADOR' THEN
			SELECT *
			FROM administrador
			WHERE administrador.email = in_email AND administrador.clave = in_clave;
		ELSEIF in_tipoUsuario = 'PROFESOR' THEN
			SELECT *
			FROM profesor
			WHERE profesor.email = in_email AND profesor.clave = in_clave;
		ELSE
			SELECT *
			FROM apoderado
			WHERE apoderado.email = in_email AND apoderado.clave = in_clave;
		END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarAlumnosCurso
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarAlumnosCurso`;
delimiter ;;
CREATE PROCEDURE `listarAlumnosCurso`(in_idCursoReferencia int)
BEGIN
	SELECT alumno.nombre, alumno.run 
	FROM cursoreferencia_alumno
	JOIN alumno ON cursoreferencia_alumno.refAlumno = alumno.run
	WHERE cursoreferencia_alumno.refCursoReferencia=in_idCursoReferencia;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarAsignaturas
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarAsignaturas`;
delimiter ;;
CREATE PROCEDURE `listarAsignaturas`()
BEGIN
		SELECT *
		FROM asignatura;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarAsignaturasCursos
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarAsignaturasCursos`;
delimiter ;;
CREATE PROCEDURE `listarAsignaturasCursos`(IN `in_refCurso` INTEGER)
BEGIN
		SELECT Asignatura.id, Asignatura.nombre, curso_asignatura.estado
		FROM asignatura
		JOIN curso_asignatura ON curso_asignatura.refAsignatura = asignatura.id AND curso_asignatura.refCurso = in_refCurso;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarCursos
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarCursos`;
delimiter ;;
CREATE PROCEDURE `listarCursos`()
BEGIN
	Select *
	From Curso;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarCursosReferencia_anio
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarCursosReferencia_anio`;
delimiter ;;
CREATE PROCEDURE `listarCursosReferencia_anio`(in_anio int)
BEGIN
	SELECT cursoreferencia.id, curso.id AS 'idCurso', curso.nivel, letraCurso.id AS 'idLetra', letraCurso.letra, anio, curso.tipoDivisionAnual,
	curso.estado, profesor.nombre, profesor.run, profesor.email, profesor.estado AS 'estadoProfesor'
	FROM cursoreferencia
	JOIN letraCurso ON letraCurso.id = cursoreferencia.refLetra
	JOIN curso ON cursoreferencia.refCurso=curso.id
	JOIN profesor ON cursoreferencia.refProfesorEncargado=profesor.run
	WHERE anio=in_anio;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarEspecialidades
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarEspecialidades`;
delimiter ;;
CREATE PROCEDURE `listarEspecialidades`()
BEGIN
	SELECT *
	FROM especialidad;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarEspecialidadesProfesor
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarEspecialidadesProfesor`;
delimiter ;;
CREATE PROCEDURE `listarEspecialidadesProfesor`(IN `in_refProfesor` VARCHAR(50))
BEGIN
	SELECT especialidad.id, especialidad.nombre
	FROM profesorespecialidad
	JOIN especialidad ON especialidad.id = profesorespecialidad.refEspecialidad
	WHERE profesorespecialidad.refProfesor=in_refProfesor;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarLetraCurso
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarLetraCurso`;
delimiter ;;
CREATE PROCEDURE `listarLetraCurso`()
BEGIN
	SELECT *
	FROM letracurso;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarProfesoresHabilitados
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarProfesoresHabilitados`;
delimiter ;;
CREATE PROCEDURE `listarProfesoresHabilitados`()
BEGIN
	SELECT profesor.nombre, profesor.run, profesor.email
	FROM profesor
	WHERE estado = "HABILITADO";
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarUnidades
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarUnidades`;
delimiter ;;
CREATE PROCEDURE `listarUnidades`(`in_refCurso` INTEGER, `in_refAsignatura` INTEGER)
BEGIN
	SELECT *
	FROM unidad
	WHERE refCurso = in_refCurso and refAsignatura = in_refAsignatura;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarAlumno
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarAlumno`;
delimiter ;;
CREATE PROCEDURE `registrarAlumno`(in_nombre varchar(100), in_run varchar(50), in_edad int)
BEGIN
insert into alumno (nombre, run, edad) values(in_nombre, in_run, in_edad);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarAsignatura
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarAsignatura`;
delimiter ;;
CREATE PROCEDURE `registrarAsignatura`(`in_nombre` VARCHAR(50))
BEGIN
		INSERT INTO asignatura(asignatura.nombre)
		VALUES (in_nombre);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarAsociacionCursoAsignatura
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarAsociacionCursoAsignatura`;
delimiter ;;
CREATE PROCEDURE `registrarAsociacionCursoAsignatura`(IN `in_refCurso` INTEGER, IN `in_refAsignatura` INTEGER)
BEGIN
		INSERT INTO curso_asignatura(curso_asignatura.refCurso,curso_asignatura.refAsignatura)
		VALUES (in_refCurso, in_refAsignatura);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarCurso
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarCurso`;
delimiter ;;
CREATE PROCEDURE `registrarCurso`(IN `in_nivel` VARCHAR(30), IN `in_tipoDivAnual` VARCHAR(30))
BEGIN
		INSERT INTO curso(curso.nivel, curso.tipoDivisionAnual)
		VALUES (in_nivel, in_tipoDivAnual);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarCursoReferencia
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarCursoReferencia`;
delimiter ;;
CREATE PROCEDURE `registrarCursoReferencia`(in_refLetra varchar(1), in_anio int, in_refCurso int, in_refProfesorEncargado varchar(50))
BEGIN
	INSERT INTO cursoreferencia (refLetra, anio, refCurso, refProfesorEncargado) 
	VALUES(in_refLetra, in_anio, in_refCurso, in_refProfesorEncargado);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarCursoReferenciaAlumno
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarCursoReferenciaAlumno`;
delimiter ;;
CREATE PROCEDURE `registrarCursoReferenciaAlumno`(in_refCursoReferencia int, in_refAlumno VARCHAR(50))
BEGIN
	INSERT INTO cursoreferencia_alumno (refCursoReferencia,refAlumno) 
	VALUES(in_refCursoReferencia, in_refAlumno);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarUnidad
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarUnidad`;
delimiter ;;
CREATE PROCEDURE `registrarUnidad`(`in_nombre` VARCHAR(100), `in_numero_unidad` INTEGER, `in_division_anual` INTEGER, `in_refAsignatura` INTEGER, `in_refCurso` INTEGER)
BEGIN
		INSERT INTO Unidad(nombre, numero, divisionAnual,refAsignatura, refCurso)
		VALUES (in_nombre, in_numero_unidad, in_division_anual, in_refAsignatura, in_refCurso);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarUsuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarUsuario`;
delimiter ;;
CREATE PROCEDURE `registrarUsuario`(in_nombre varchar(100), in_run varchar(100), in_tipoUsuario varchar(50), in_email varchar(100), in_clave varchar(100))
BEGIN
	
	IF in_tipoUsuario = "PROFESOR" THEN
		INSERT INTO profesor(nombre, run, email, clave)
		VALUES (in_nombre, in_run, in_email, in_clave);
	ELSE
		INSERT INTO apoderado(nombre, run, email, clave)
		VALUES (in_nombre, in_run, in_email, in_clave);
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for validarExistenciaCuenta
-- ----------------------------
DROP PROCEDURE IF EXISTS `validarExistenciaCuenta`;
delimiter ;;
CREATE PROCEDURE `validarExistenciaCuenta`(in_run varchar(100), in_email varchar(100), in_tipoUsuario varchar(50))
BEGIN
	
	IF in_tipoUsuario = "PROFESOR" THEN
		SELECT *
		FROM profesor
		WHERE profesor.run != in_run AND profesor.email=in_email;
	ELSE
		SELECT *
		FROM apoderado
		WHERE apoderado.run != in_run AND apoderado.email=in_email;
	END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
