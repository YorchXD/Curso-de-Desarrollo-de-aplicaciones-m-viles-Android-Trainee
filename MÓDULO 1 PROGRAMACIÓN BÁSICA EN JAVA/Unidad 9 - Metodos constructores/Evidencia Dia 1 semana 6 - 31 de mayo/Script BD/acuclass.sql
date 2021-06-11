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

 Date: 25/05/2021 20:35:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alumno
-- ----------------------------
DROP TABLE IF EXISTS `alumno`;
CREATE TABLE `alumno`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `run` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `edad` int NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'HABILITADO',
  `refApoderado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`run`) USING BTREE,
  INDEX `refApoderado`(`refApoderado`) USING BTREE,
  CONSTRAINT `alumno_ibfk_1` FOREIGN KEY (`refApoderado`) REFERENCES `usuario` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for asignatura
-- ----------------------------
DROP TABLE IF EXISTS `asignatura`;
CREATE TABLE `asignatura`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'HABILITADO',
  `refCurso` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`nombre`, `refCurso`) USING BTREE,
  INDEX `refCurso`(`refCurso`) USING BTREE,
  CONSTRAINT `asignatura_ibfk_1` FOREIGN KEY (`refCurso`) REFERENCES `curso` (`nivel`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for asignatura_cursoreferencia_profesor
-- ----------------------------
DROP TABLE IF EXISTS `asignatura_cursoreferencia_profesor`;
CREATE TABLE `asignatura_cursoreferencia_profesor`  (
  `refLetraCurso` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refAñoCurso` int NOT NULL,
  `refAsignatura` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refCursoAsignatura` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refProfesor` int NOT NULL,
  INDEX `refAsignatura`(`refAsignatura`, `refCursoAsignatura`) USING BTREE,
  INDEX `refLetraCurso`(`refLetraCurso`, `refAñoCurso`) USING BTREE,
  CONSTRAINT `asignatura_cursoreferencia_profesor_ibfk_1` FOREIGN KEY (`refAsignatura`, `refCursoAsignatura`) REFERENCES `asignatura` (`nombre`, `refCurso`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `asignatura_cursoreferencia_profesor_ibfk_2` FOREIGN KEY (`refLetraCurso`, `refAñoCurso`) REFERENCES `cursoreferencia` (`letra`, `año`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for curso
-- ----------------------------
DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso`  (
  `nivel` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tipoDivisionAnual` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`nivel`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cursoreferencia
-- ----------------------------
DROP TABLE IF EXISTS `cursoreferencia`;
CREATE TABLE `cursoreferencia`  (
  `letra` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `año` int NOT NULL,
  `refCurso` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refProfesorEncargado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`letra`, `año`) USING BTREE,
  INDEX `refCurso`(`refCurso`) USING BTREE,
  INDEX `refProfesorEncargado`(`refProfesorEncargado`) USING BTREE,
  CONSTRAINT `cursoreferencia_ibfk_1` FOREIGN KEY (`refCurso`) REFERENCES `curso` (`nivel`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cursoreferencia_ibfk_2` FOREIGN KEY (`refProfesorEncargado`) REFERENCES `usuario` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cursoreferencia_alumno
-- ----------------------------
DROP TABLE IF EXISTS `cursoreferencia_alumno`;
CREATE TABLE `cursoreferencia_alumno`  (
  `refLetraCurso` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refAñoCurso` int NOT NULL,
  `refAlumno` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  INDEX `refAlumno`(`refAlumno`) USING BTREE,
  INDEX `refLetraCurso`(`refLetraCurso`, `refAñoCurso`) USING BTREE,
  CONSTRAINT `cursoreferencia_alumno_ibfk_1` FOREIGN KEY (`refAlumno`) REFERENCES `alumno` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cursoreferencia_alumno_ibfk_2` FOREIGN KEY (`refLetraCurso`, `refAñoCurso`) REFERENCES `cursoreferencia` (`letra`, `año`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for unidad
-- ----------------------------
DROP TABLE IF EXISTS `unidad`;
CREATE TABLE `unidad`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `numero` int NOT NULL,
  `divisionAnual` int NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'HABILITADO',
  `refAsignatura` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refCurso` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`nombre`) USING BTREE,
  INDEX `refAsignatura`(`refAsignatura`, `refCurso`) USING BTREE,
  CONSTRAINT `unidad_ibfk_1` FOREIGN KEY (`refAsignatura`, `refCurso`) REFERENCES `asignatura` (`nombre`, `refCurso`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `run` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tipoUsuario` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `especialidad` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'HABILITADO',
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `clave` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`run`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Procedure structure for buscarCurso
-- ----------------------------
DROP PROCEDURE IF EXISTS `buscarCurso`;
delimiter ;;
CREATE PROCEDURE `buscarCurso`(IN `in_nivel` VARCHAR(30))
BEGIN
		SELECT *
		FROM curso
		WHERE curso.nivel = in_nivel;
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
		SELECT *
		FROM usuario
		WHERE usuario.tipoUsuario = in_tipoUsuario AND usuario.email = in_email AND usuario.clave = in_clave;

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
		/*Crea la resolucion*/
		INSERT INTO curso(curso.nivel, curso.tipoDivisionAnual)
		VALUES (in_nivel, in_tipoDivAnual);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
