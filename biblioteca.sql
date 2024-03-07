CREATE DATABASE  IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `biblioteca`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `idautor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  PRIMARY KEY (`idautor`),
  UNIQUE KEY `idautor_UNIQUE` (`idautor`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (16,'Vladimir','Nabokov'),(17,'George','Orwell'),(18,'J.K.','Rowling'),(19,'J.R.R.','Tolkien');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuotas`
--

DROP TABLE IF EXISTS `cuotas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuotas` (
  `idcuotas` int NOT NULL AUTO_INCREMENT,
  `fechaPago` date DEFAULT NULL,
  `fechaDesde` date NOT NULL,
  `fechaHasta` date NOT NULL,
  `idSocio` int NOT NULL,
  `estado` varchar(45) DEFAULT 'Pendiente',
  PRIMARY KEY (`idcuotas`),
  KEY `FK_cuotasToSocio_idx` (`idSocio`),
  CONSTRAINT `FK_cuotasToSocio` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idsocio`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotas`
--

LOCK TABLES `cuotas` WRITE;
/*!40000 ALTER TABLE `cuotas` DISABLE KEYS */;
INSERT INTO `cuotas` VALUES (7,'2023-08-15','2022-12-02','2023-01-02',4,''),(8,'2023-08-15','2022-01-01','2022-02-01',4,''),(9,'2024-01-23','2022-12-02','2023-02-02',4,'Pagada'),(10,'2024-02-23','2022-12-02','2023-02-02',4,'Pagada'),(11,'2024-02-23','2023-03-15','2023-05-15',4,'Pagada'),(12,'2024-02-27','2023-06-10','2023-08-10',4,'Pagada'),(15,NULL,'2024-03-01','2024-04-01',5,'A_Confirmar'),(16,NULL,'2024-04-01','2024-05-01',5,'A_Confirmar'),(21,'2024-03-03','2024-03-05','2024-04-05',1,'Pagada'),(22,'2024-03-06','2024-03-05','2024-04-05',2,'Pagada'),(23,NULL,'2024-03-05','2024-04-05',4,'Pendiente');
/*!40000 ALTER TABLE `cuotas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejemplar`
--

DROP TABLE IF EXISTS `ejemplar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejemplar` (
  `idejemplar` int NOT NULL AUTO_INCREMENT,
  `idLibro` int unsigned NOT NULL,
  `disponible` tinyint DEFAULT '1',
  PRIMARY KEY (`idejemplar`),
  UNIQUE KEY `idejemplar_UNIQUE` (`idejemplar`),
  KEY `FK_EjemplarToLibro_idx` (`idLibro`),
  CONSTRAINT `FK_EjemplarToLibro` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`idlibro`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplar`
--

LOCK TABLES `ejemplar` WRITE;
/*!40000 ALTER TABLE `ejemplar` DISABLE KEYS */;
INSERT INTO `ejemplar` VALUES (25,12,0),(26,12,1),(27,12,1),(28,12,1),(29,12,1),(30,13,0),(31,13,0),(32,13,0),(33,13,0),(34,13,0),(35,13,0),(36,14,1),(37,14,1),(38,15,1),(39,14,1),(40,13,1),(41,14,1),(42,13,1);
/*!40000 ALTER TABLE `ejemplar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `idlibro` int unsigned NOT NULL AUTO_INCREMENT,
  `isbn` varchar(45) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `editorial` varchar(45) NOT NULL,
  `fechaEdicion` date NOT NULL,
  `cantDiasMaxPrestamo` int NOT NULL,
  `idAutor` int NOT NULL,
  PRIMARY KEY (`idlibro`),
  UNIQUE KEY `idlibro_UNIQUE` (`idlibro`),
  KEY `FK_LibroToAutor_idx` (`idAutor`),
  CONSTRAINT `FK_LibroToAutor` FOREIGN KEY (`idAutor`) REFERENCES `autor` (`idautor`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (12,'978-0451524935','1984','Signet Classic','1950-07-01',14,17),(13,'978-0545162074','Harry Potter and the Sorcerer Stone','Scholastic','1998-09-01',14,18),(14,'978-0547928227','The Hobbit','Houghton Mifflin Harcourt','2012-09-18',14,19),(15,'958-0451525335','Lolita','Marquez','2001-11-26',30,16);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineadeprestamo`
--

DROP TABLE IF EXISTS `lineadeprestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lineadeprestamo` (
  `idlineadeprestamo` int NOT NULL AUTO_INCREMENT,
  `fechaDevolucionTeorica` date NOT NULL,
  `fechaDevolucionReal` date DEFAULT NULL,
  `idPrestamo` int unsigned NOT NULL,
  `idEjemplar` int NOT NULL,
  PRIMARY KEY (`idlineadeprestamo`),
  UNIQUE KEY `idlineadeprestamo_UNIQUE` (`idlineadeprestamo`),
  KEY `FK_lineadeprestamoToEjemplar_idx` (`idEjemplar`),
  KEY `FK_lineadeprestamoToPrestamo_idx` (`idPrestamo`),
  CONSTRAINT `FK_lineadeprestamoToEjemplar` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplar` (`idejemplar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_lineadeprestamoToPrestamo` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamo` (`idprestamo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineadeprestamo`
--

LOCK TABLES `lineadeprestamo` WRITE;
/*!40000 ALTER TABLE `lineadeprestamo` DISABLE KEYS */;
INSERT INTO `lineadeprestamo` VALUES (22,'2024-03-20','2024-03-06',10,25),(23,'2024-03-20','2024-03-06',10,30),(24,'2024-03-20',NULL,11,32),(25,'2024-03-20',NULL,11,33),(26,'2024-03-20',NULL,11,30),(27,'2024-03-20',NULL,11,31),(28,'2024-03-20',NULL,12,34),(29,'2024-03-20',NULL,12,35),(30,'2024-03-20',NULL,13,25);
/*!40000 ALTER TABLE `lineadeprestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `politicaprestamo`
--

DROP TABLE IF EXISTS `politicaprestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `politicaprestamo` (
  `idPoliticaPrestamo` int NOT NULL AUTO_INCREMENT,
  `fechaDesde` date NOT NULL,
  `cantMaxLibrosPend` int NOT NULL,
  PRIMARY KEY (`idPoliticaPrestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politicaprestamo`
--

LOCK TABLES `politicaprestamo` WRITE;
/*!40000 ALTER TABLE `politicaprestamo` DISABLE KEYS */;
INSERT INTO `politicaprestamo` VALUES (4,'2024-02-06',5),(9,'2023-06-07',9);
/*!40000 ALTER TABLE `politicaprestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `politicasancion`
--

DROP TABLE IF EXISTS `politicasancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `politicasancion` (
  `idpoliticasancion` int NOT NULL AUTO_INCREMENT,
  `diasDeAtrasoDesde` int NOT NULL,
  `diasSancion` int NOT NULL,
  PRIMARY KEY (`idpoliticasancion`),
  UNIQUE KEY `idpoliticasancion_UNIQUE` (`idpoliticasancion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politicasancion`
--

LOCK TABLES `politicasancion` WRITE;
/*!40000 ALTER TABLE `politicasancion` DISABLE KEYS */;
/*!40000 ALTER TABLE `politicasancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `idprestamo` int unsigned NOT NULL AUTO_INCREMENT,
  `fechaPrestamo` date NOT NULL,
  `idSocio` int NOT NULL,
  PRIMARY KEY (`idprestamo`),
  UNIQUE KEY `idprestamo_UNIQUE` (`idprestamo`),
  KEY `FK_prestamoToSocio_idx` (`idSocio`),
  CONSTRAINT `FK_prestamoToSocio` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idsocio`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,'2023-07-19',2),(2,'2023-08-14',2),(3,'2024-02-23',1),(4,'2024-02-24',1),(5,'2024-02-24',1),(6,'2024-02-26',1),(7,'2024-02-27',5),(8,'2024-02-28',4),(9,'2024-02-29',4),(10,'2024-03-06',4),(11,'2024-03-06',2),(12,'2024-03-06',2),(13,'2024-03-06',4);
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socio` (
  `idsocio` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `domicilio` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `contrasenia` varchar(256) NOT NULL,
  `isAdmin` tinyint NOT NULL DEFAULT '0',
  `usuario` varchar(45) NOT NULL,
  PRIMARY KEY (`idsocio`),
  UNIQUE KEY `idsocio_UNIQUE` (`idsocio`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
INSERT INTO `socio` VALUES (1,'Perez','Juan','juan@perz.com','Av 1234','12345678','50eec946d18107695ed9965c772d01c8ddd63b2aa53adfa69f2d7cac114b8e1b',1,'juanperez'),(2,'Doe','John','john@doe.com','Av 66544','3783748374','ec08a62bede177c9307f5c827847697ea7eb32bde9d17ba585929e36af58b44d',0,'johndoe'),(4,'Guillermo','Casey','guillermocasey@gmail.com','Av siempreviva 123','3423432432','4f1b6d801016c7731be4d7d8e951e156786ac469ed03e9dbe3e096a12e32e195',0,'gcasey'),(5,'Bernardo','Dominguez','bernidom@gmail.com','AV berni 123','23123438','02a941fd4718195c221d3480bd1a132d0f3ef78923bfba19572ddddea7e460ca',1,'bdominguez');
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valorcuotas`
--

DROP TABLE IF EXISTS `valorcuotas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valorcuotas` (
  `fechaDesde` date NOT NULL,
  `valorcuotascol` double unsigned NOT NULL,
  PRIMARY KEY (`fechaDesde`),
  UNIQUE KEY `idvalorcuotas_UNIQUE` (`fechaDesde`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valorcuotas`
--

LOCK TABLES `valorcuotas` WRITE;
/*!40000 ALTER TABLE `valorcuotas` DISABLE KEYS */;
INSERT INTO `valorcuotas` VALUES ('2020-01-01',300),('2022-01-01',400);
/*!40000 ALTER TABLE `valorcuotas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-06 21:28:17
