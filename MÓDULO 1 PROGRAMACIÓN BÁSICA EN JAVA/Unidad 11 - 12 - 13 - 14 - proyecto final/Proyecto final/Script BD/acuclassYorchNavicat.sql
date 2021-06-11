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

 Date: 09/06/2021 21:28:08
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
  `refApoderado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`run`) USING BTREE,
  INDEX `refApoderado`(`refApoderado`) USING BTREE,
  CONSTRAINT `alumno_ibfk_1` FOREIGN KEY (`refApoderado`) REFERENCES `usuario` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of alumno
-- ----------------------------

-- ----------------------------
-- Table structure for asignatura
-- ----------------------------
DROP TABLE IF EXISTS `asignatura`;
CREATE TABLE `asignatura`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of asignatura
-- ----------------------------
INSERT INTO `asignatura` VALUES (1, 'Ingles');
INSERT INTO `asignatura` VALUES (2, 'Lenguaje');
INSERT INTO `asignatura` VALUES (3, 'Musica');

-- ----------------------------
-- Table structure for asignatura_curso_referencia_profesor
-- ----------------------------
DROP TABLE IF EXISTS `asignatura_curso_referencia_profesor`;
CREATE TABLE `asignatura_curso_referencia_profesor`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `refCursoReferencia` int NULL DEFAULT NULL,
  `refProfesor` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refCursoAsignatura` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refCursoReferencia`(`refCursoReferencia`) USING BTREE,
  INDEX `refProfesor`(`refProfesor`) USING BTREE,
  INDEX `refCursoAsignatura`(`refCursoAsignatura`) USING BTREE,
  CONSTRAINT `refCursoAsignatura_ibfk_3` FOREIGN KEY (`refCursoAsignatura`) REFERENCES `curso_asignatura` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `refCursoReferencia_ibfk_1` FOREIGN KEY (`refCursoReferencia`) REFERENCES `curso_referencia` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `refProfesor_ibfk_2` FOREIGN KEY (`refProfesor`) REFERENCES `usuario` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of asignatura_curso_referencia_profesor
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of curso
-- ----------------------------
INSERT INTO `curso` VALUES (1, 'PRIMERO_BASICO', 'SEMESTRAL', 'HABILITADO');
INSERT INTO `curso` VALUES (2, 'SEGUNDO_BASICO', 'TRIMESTRAL', 'HABILITADO');

-- ----------------------------
-- Table structure for curso_asignatura
-- ----------------------------
DROP TABLE IF EXISTS `curso_asignatura`;
CREATE TABLE `curso_asignatura`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `refCurso` int NOT NULL,
  `refAsignatura` int NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refCurso`(`refCurso`) USING BTREE,
  INDEX `refAsignatura`(`refAsignatura`) USING BTREE,
  CONSTRAINT `curso_asignatura_ibfk_1` FOREIGN KEY (`refCurso`) REFERENCES `curso` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `curso_asignatura_ibfk_2` FOREIGN KEY (`refAsignatura`) REFERENCES `asignatura` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of curso_asignatura
-- ----------------------------
INSERT INTO `curso_asignatura` VALUES (1, 1, 1, 'HABILITADO');
INSERT INTO `curso_asignatura` VALUES (2, 1, 2, 'HABILITADO');
INSERT INTO `curso_asignatura` VALUES (3, 1, 3, 'HABILITADO');
INSERT INTO `curso_asignatura` VALUES (4, 2, 2, 'HABILITADO');

-- ----------------------------
-- Table structure for curso_referencia
-- ----------------------------
DROP TABLE IF EXISTS `curso_referencia`;
CREATE TABLE `curso_referencia`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `letra` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `anio` int NOT NULL,
  `refCurso` int NOT NULL,
  `refProfesorEncargado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refCurso`(`refCurso`) USING BTREE,
  INDEX `refProfesorEncargado`(`refProfesorEncargado`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `curso_referencia_ibfk_1` FOREIGN KEY (`refCurso`) REFERENCES `curso` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `curso_referencia_ibfk_2` FOREIGN KEY (`refProfesorEncargado`) REFERENCES `usuario` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of curso_referencia
-- ----------------------------
INSERT INTO `curso_referencia` VALUES (4, 'A', 2021, 1, '17307859-5');

-- ----------------------------
-- Table structure for curso_referencia_alumno
-- ----------------------------
DROP TABLE IF EXISTS `curso_referencia_alumno`;
CREATE TABLE `curso_referencia_alumno`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `refCursoReferencia` int NOT NULL,
  `refAlumno` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refAlumno`(`refAlumno`) USING BTREE,
  INDEX `refCursoReferencia`(`refCursoReferencia`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `curso_referencia_alumno_ibfk_1` FOREIGN KEY (`refAlumno`) REFERENCES `alumno` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `curso_referencia_alumno_ibfk_2` FOREIGN KEY (`refCursoReferencia`) REFERENCES `curso_referencia` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of curso_referencia_alumno
-- ----------------------------

-- ----------------------------
-- Table structure for modulo
-- ----------------------------
DROP TABLE IF EXISTS `modulo`;
CREATE TABLE `modulo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of modulo
-- ----------------------------

-- ----------------------------
-- Table structure for operaciones
-- ----------------------------
DROP TABLE IF EXISTS `operaciones`;
CREATE TABLE `operaciones`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refModulo` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refModulo`(`refModulo`) USING BTREE,
  CONSTRAINT `operaciones_ibfk_1` FOREIGN KEY (`refModulo`) REFERENCES `modulo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of operaciones
-- ----------------------------

-- ----------------------------
-- Table structure for rol
-- ----------------------------
DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of rol
-- ----------------------------
INSERT INTO `rol` VALUES (1, 'ADMINISTRADOR');
INSERT INTO `rol` VALUES (2, 'PROFESOR');
INSERT INTO `rol` VALUES (3, 'APODERADO');

-- ----------------------------
-- Table structure for rol_operacion
-- ----------------------------
DROP TABLE IF EXISTS `rol_operacion`;
CREATE TABLE `rol_operacion`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `refRol` int NOT NULL,
  `refOperacion` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refOperacion`(`refOperacion`) USING BTREE,
  INDEX `refRol`(`refRol`) USING BTREE,
  CONSTRAINT `rol_operacion_ibfk_1` FOREIGN KEY (`refOperacion`) REFERENCES `operaciones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rol_operacion_ibfk_2` FOREIGN KEY (`refRol`) REFERENCES `rol` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of rol_operacion
-- ----------------------------

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
  `refCursoAsignatura` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unidad_ibfk_1`(`refCursoAsignatura`) USING BTREE,
  CONSTRAINT `unidad_ibfk_1` FOREIGN KEY (`refCursoAsignatura`) REFERENCES `curso_asignatura` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of unidad
-- ----------------------------
INSERT INTO `unidad` VALUES (2, 'Verb to be', 1, 1, 'HABILITADO', 1);

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario`  (
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `run` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `especialidad` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`run`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('Administrador', '11111111-1', '');
INSERT INTO `usuario` VALUES ('Patricia Magdalena Manriquez Lisboa', '12296649-6', 'Pedagogía en matemáticas');
INSERT INTO `usuario` VALUES ('Tania Torres Reyes', '17307859-5', 'Biologia');
INSERT INTO `usuario` VALUES ('Yorch Sepúlveda', '17824523-6', 'Ingenieria civil en computación');
INSERT INTO `usuario` VALUES ('Gregory Sepúlveda', '19043138-k', NULL);

-- ----------------------------
-- Table structure for usuario_rol
-- ----------------------------
DROP TABLE IF EXISTS `usuario_rol`;
CREATE TABLE `usuario_rol`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `clave` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `estado` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HABILITADO',
  `refUsuario` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refRol` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `refUsuario`(`refUsuario`) USING BTREE,
  INDEX `refRol`(`refRol`) USING BTREE,
  CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`refUsuario`) REFERENCES `usuario` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`refRol`) REFERENCES `rol` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of usuario_rol
-- ----------------------------
INSERT INTO `usuario_rol` VALUES (1, 'admin@usuario.cl', '12345', 'HABILITADO', '11111111-1', 1);
INSERT INTO `usuario_rol` VALUES (6, 'patricia@usuario.cl', '12345', 'HABILITADO', '12296649-6', 2);
INSERT INTO `usuario_rol` VALUES (7, 'patricia@apoderado.cl', '12345', 'HABILITADO', '12296649-6', 3);
INSERT INTO `usuario_rol` VALUES (8, 'yorch@profesor.cl', '12345', 'HABILITADO', '17824523-6', 2);
INSERT INTO `usuario_rol` VALUES (14, 'tania@profesor.cl', '12345', 'HABILITADO', '17307859-5', 2);
INSERT INTO `usuario_rol` VALUES (15, 'gregory@apoderado.cl', '12345', 'HABILITADO', '19043138-k', 3);

-- ----------------------------
-- Procedure structure for actualizarAlumno
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizarAlumno`;
delimiter ;;
CREATE PROCEDURE `actualizarAlumno`(in_run VARCHAR(50), in_nombre VARCHAR(100), in_edad int)
BEGIN
	update alumno set nombre=in_nombre, edad=in_edad where run=in_run;
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
CREATE PROCEDURE `actualizarEstadoAsignatura`(`in_id` int, `in_estado` VARCHAR(50))
BEGIN
	UPDATE curso_asignatura
	SET curso_asignatura.estado = in_estado
	WHERE curso_asignatura.id = in_id;
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
	UPDATE usuario_rol 
	JOIN rol ON rol.id = usuario_rol.refRol
	SET estado = in_estado 
	WHERE usuario_rol.refUsuario = in_run AND rol.nombre = in_tipoUsuario;
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
	UPDATE usuario 
	SET nombre=in_nombre, especialidad=in_especialidad 
	WHERE run=in_run;
	
	UPDATE usuario_rol
	JOIN rol ON rol.id = usuario_rol.refRol
	SET email=in_email, clave=in_clave
	WHERE usuario_rol.refUsuario = in_run AND rol.nombre = in_tipoUsuario;
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
		SELECT id
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
CREATE PROCEDURE `buscarCursoReferencia`(in_letra varchar(1), in_anio int, in_refCurso int, in_refProfesorEncargado varchar(50))
BEGIN
	select * from curso_referencia where letra=in_letra and anio=in_anio and refCurso= in_refCurso and refProfesorEncargado=in_refProfesorEncargado;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for buscarUsuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `buscarUsuario`;
delimiter ;;
CREATE PROCEDURE `buscarUsuario`(in_run varchar(100))
BEGIN
		SELECT usuario.nombre, usuario.run, usuario.especialidad, usuario_rol.email, usuario_rol.clave, usuario_rol.estado, rol.nombre as 'tipoUsuario'
		FROM usuario
		LEFT JOIN usuario_rol ON usuario_rol.refUsuario = usuario.run
		LEFT JOIN rol ON rol.id = usuario_Rol.refRol
		WHERE usuario.run = in_run;
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
	SELECT usuario.nombre, usuario.run, usuario.especialidad, usuario_rol.email, usuario_rol.clave, usuario_rol.estado, rol.nombre AS 'tipoUsuario'
	FROM usuario
	JOIN usuario_rol ON usuario_rol.refUsuario = usuario.run
	JOIN rol ON rol.id = usuario_rol.refRol
	WHERE usuario.run = in_run AND rol.nombre = in_tipoUsuario;
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
		SELECT usuario.nombre, usuario.run, usuario_rol.email, usuario_rol.clave, usuario_rol.estado, rol.nombre as 'tipoUsuario'
		FROM usuario
		JOIN usuario_rol ON usuario_rol.refUsuario = usuario.run
		JOIN rol ON rol.id = usuario_rol.refRol
		WHERE usuario_rol.email = in_email AND usuario_rol.clave = in_clave AND rol.nombre=in_tipoUsuario;
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
select 
alumno.nombre, alumno.run from curso_referencia_alumno
inner join alumno
on curso_referencia_alumno.refAlumno = alumno.run
inner join curso_referencia
on curso_referencia_alumno.refCursoReferencia = curso_referencia.id
inner join curso
on curso_referencia.refCurso = curso.id
where curso_referencia.id=in_idCursoReferencia;
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
		SELECT curso_asignatura.id, Asignatura.nombre, curso_asignatura.estado
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
-- Procedure structure for ListarCursosReferencia_anio
-- ----------------------------
DROP PROCEDURE IF EXISTS `ListarCursosReferencia_anio`;
delimiter ;;
CREATE PROCEDURE `ListarCursosReferencia_anio`(in_anio int)
BEGIN
	select curso_referencia.id, curso.nivel, curso.tipoDivisionAnual,
	curso.estado, letra, anio, usuario.nombre, usuario.run, usuario.especialidad
	from curso_referencia
	inner join curso
	on curso_referencia.refCurso=curso.id
	inner join usuario
	on curso_referencia.refProfesorEncargado=usuario.run
	where  anio=in_anio;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listarUnidades
-- ----------------------------
DROP PROCEDURE IF EXISTS `listarUnidades`;
delimiter ;;
CREATE PROCEDURE `listarUnidades`(`in_refCursoAsignatura` INTEGER)
BEGIN
	SELECT *
	FROM unidad
	WHERE refCursoAsignatura = in_refCursoAsignatura;
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
-- Procedure structure for registrarCuenta
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarCuenta`;
delimiter ;;
CREATE PROCEDURE `registrarCuenta`(in_run varchar(100), in_tipoUsuario varchar(50), in_email varchar(100), in_clave varchar(100), in_especialidad varchar(100))
BEGIN
	/*Obtiene el id del rol al que pertenece el usuario*/
	SELECT @refRol:= id
	FROM rol
	WHERE rol.nombre = in_tipoUsuario;
	
	/*Asocia al usuario con el rol*/
	INSERT INTO usuario_rol(refUsuario, refRol, email, clave)
	VALUES(in_run, @refRol, in_email, in_clave);
	
	/*Obtiene el id del rol al que pertenece el usuario*/
	SELECT @especialidadUsuario:= especialidad
	FROM usuario
	WHERE usuario.run = in_run;
	
	IF in_especialidad IS NOT NULL THEN
			UPDATE usuario
			SET usuario.especialidad = in_especialidad
			WHERE usuario.run = in_run;
	END IF;
	
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
-- Procedure structure for RegistrarCursoReferencia
-- ----------------------------
DROP PROCEDURE IF EXISTS `RegistrarCursoReferencia`;
delimiter ;;
CREATE PROCEDURE `RegistrarCursoReferencia`(in_letra varchar(1), in_anio int, in_refCurso int, in_refProfesorEncargado varchar(50))
BEGIN
	insert into curso_referencia (letra, anio, refCurso, refProfesorEncargado) values(in_letra, in_anio, in_refCurso, in_refProfesorEncargado);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for RegistrarCursoReferenciaAlumno
-- ----------------------------
DROP PROCEDURE IF EXISTS `RegistrarCursoReferenciaAlumno`;
delimiter ;;
CREATE PROCEDURE `RegistrarCursoReferenciaAlumno`(in_refCursoReferencia int, in_refAlumno varchar(50))
BEGIN
	insert into curso_referencia_alumno (refCursoReferencia,refAlumno) values(in_refCursoReferencia, in_refAlumno);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarUnidad
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarUnidad`;
delimiter ;;
CREATE PROCEDURE `registrarUnidad`(`in_nombre` VARCHAR(100), `in_numero_unidad` INTEGER, `in_division_anual` INTEGER, `in_refAsignatura` INTEGER)
BEGIN
		INSERT INTO Unidad(nombre, numero, divisionAnual,refCursoAsignatura)
		VALUES (in_nombre, in_numero_unidad, in_division_anual, in_refAsignatura);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for registrarUsuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `registrarUsuario`;
delimiter ;;
CREATE PROCEDURE `registrarUsuario`(in_nombre varchar(100), in_run varchar(100), in_tipoUsuario varchar(50), in_especialidad varchar(100), in_email varchar(100), in_clave varchar(100))
BEGIN
	/*Registra al usuario*/
	INSERT INTO usuario (nombre, run)
	VALUES(in_nombre,in_run);
	
	CALL registrarCuenta(in_run, in_tipoUsuario, in_email, in_clave, in_especialidad);
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
	SELECT usuario.nombre, usuario.run, usuario.especialidad, usuario_rol.email, usuario_rol.clave, usuario_rol.estado, rol.nombre AS 'tipoUsuario'
	FROM usuario
	JOIN usuario_rol ON usuario_rol.refUsuario = usuario.run
	JOIN rol ON rol.id = usuario_rol.refRol
	WHERE usuario.run != in_run AND rol.nombre = in_tipoUsuario AND usuario_rol.email=in_email;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
