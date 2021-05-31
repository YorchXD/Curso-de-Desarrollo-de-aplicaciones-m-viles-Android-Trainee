-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: acuclass
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asignatura_curso_referencia_profesor`
--

DROP TABLE IF EXISTS `asignatura_curso_referencia_profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignatura_curso_referencia_profesor` (
  `refLetraCurso` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refAñoCurso` int NOT NULL,
  `refProfesor` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `refCursoAsignatura` int NOT NULL,
  KEY `refLetraCurso` (`refLetraCurso`,`refAñoCurso`) USING BTREE,
  KEY `refProfesor` (`refProfesor`) USING BTREE,
  KEY `refCursoAsignatura` (`refCursoAsignatura`) USING BTREE,
  CONSTRAINT `asignatura_curso_referencia_profesor_ibfk_1` FOREIGN KEY (`refLetraCurso`, `refAñoCurso`) REFERENCES `curso_referencia` (`letra`, `año`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `asignatura_curso_referencia_profesor_ibfk_2` FOREIGN KEY (`refProfesor`) REFERENCES `usuario` (`run`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `asignatura_curso_referencia_profesor_ibfk_3` FOREIGN KEY (`refCursoAsignatura`) REFERENCES `curso_asignatura` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-29 12:09:12
