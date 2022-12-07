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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,'Julio','Verne'),(3,'Antoine','Saint Exuperi'),(4,'William','Shakespeare'),(5,'Charles','Dickens'),(6,'Julio','Verne');
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotas`
--

LOCK TABLES `cuotas` WRITE;
/*!40000 ALTER TABLE `cuotas` DISABLE KEYS */;
INSERT INTO `cuotas` VALUES (7,'2022-12-05','2022-12-02','2023-01-02',4,'Pagada'),(8,'2022-12-05','2022-01-01','2022-02-01',4,'Pagada'),(9,NULL,'2023-01-01','2023-02-01',4,'A_Confirmar'),(10,'2022-12-07','2023-02-01','2023-03-01',4,'Pagada'),(11,NULL,'2023-03-01','2023-04-01',4,'Pendiente'),(12,NULL,'2022-03-01','2023-04-01',5,'A_Confirmar'),(13,NULL,'2022-04-01','2023-05-01',5,'Pendiente'),(14,NULL,'2022-05-01','2023-06-01',5,'Pendiente'),(15,NULL,'2022-12-01','2023-01-01',5,'Pendiente'),(16,NULL,'2023-01-01','2023-02-01',5,'Pendiente'),(17,NULL,'2023-02-01','2023-03-01',5,'Pendiente');
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
  PRIMARY KEY (`idejemplar`),
  UNIQUE KEY `idejemplar_UNIQUE` (`idejemplar`),
  KEY `FK_EjemplarToLibro_idx` (`idLibro`),
  CONSTRAINT `FK_EjemplarToLibro` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`idlibro`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplar`
--

LOCK TABLES `ejemplar` WRITE;
/*!40000 ALTER TABLE `ejemplar` DISABLE KEYS */;
INSERT INTO `ejemplar` VALUES (1,1),(5,4),(4,6);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,'ABC1234','8787878787','Luz','2020-09-12',90,1),(4,'AA1233JA','El psicoanalista','Wallace','2012-07-17',9,5),(6,'9009090','100 anios de soledad','Luz','2020-10-13',0,3);
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
  `estadoLinea` varchar(45) NOT NULL,
  `idPrestamo` int unsigned NOT NULL,
  `idEjemplar` int NOT NULL,
  PRIMARY KEY (`idlineadeprestamo`),
  UNIQUE KEY `idlineadeprestamo_UNIQUE` (`idlineadeprestamo`),
  KEY `FK_lineadeprestamoToEjemplar_idx` (`idEjemplar`),
  KEY `FK_lineadeprestamoToPrestamo_idx` (`idPrestamo`),
  CONSTRAINT `FK_lineadeprestamoToEjemplar` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplar` (`idejemplar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_lineadeprestamoToPrestamo` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamo` (`idprestamo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineadeprestamo`
--

LOCK TABLES `lineadeprestamo` WRITE;
/*!40000 ALTER TABLE `lineadeprestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `lineadeprestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineadeventa`
--

DROP TABLE IF EXISTS `lineadeventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lineadeventa` (
  `idlineadeventa` int NOT NULL AUTO_INCREMENT,
  `idVenta` int NOT NULL,
  `idEjemplar` int NOT NULL,
  PRIMARY KEY (`idlineadeventa`),
  KEY `FK_lineadeventaToEjemplar_idx` (`idEjemplar`),
  KEY `FK_lineadeventaToVenta_idx` (`idVenta`),
  CONSTRAINT `FK_lineadeventaToEjemplar` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplar` (`idejemplar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_lineadeventaToVenta` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`idventa`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineadeventa`
--

LOCK TABLES `lineadeventa` WRITE;
/*!40000 ALTER TABLE `lineadeventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `lineadeventa` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politicaprestamo`
--

LOCK TABLES `politicaprestamo` WRITE;
/*!40000 ALTER TABLE `politicaprestamo` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sancion`
--

DROP TABLE IF EXISTS `sancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sancion` (
  `idsancion` int unsigned NOT NULL AUTO_INCREMENT,
  `fechaSancion` date NOT NULL,
  `diasSancion` int NOT NULL,
  `idSocio` int NOT NULL,
  PRIMARY KEY (`idsancion`),
  UNIQUE KEY `idsancion_UNIQUE` (`idsancion`),
  KEY `FK_sancionToSocio_idx` (`idSocio`),
  CONSTRAINT `FK_sancionToSocio` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idsocio`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sancion`
--

LOCK TABLES `sancion` WRITE;
/*!40000 ALTER TABLE `sancion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sancion` ENABLE KEYS */;
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
  `estadoSocio` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `isAdmin` tinyint NOT NULL DEFAULT '0',
  `usuario` varchar(45) NOT NULL,
  PRIMARY KEY (`idsocio`),
  UNIQUE KEY `idsocio_UNIQUE` (`idsocio`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
INSERT INTO `socio` VALUES (1,'Perez','Juan','juan@perz.com','Av 1234','12345678','Activo','juanperez1',1,'juanperez'),(2,'Doe','John','john@doe.com','Av 66544','3783748374','Activo','johndoe1',1,'johndoe'),(4,'noAdmin','noAdmin','noadmin@gmail.com','asdasd','3423432432','Pendiente','noadmin',0,'juanNoAdmin'),(5,'Casey','Guillermo','guillermocasey@hotmail.com','Pellegrini 233','3323232','Activo','guillermo',0,'guillek6');
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

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `idventa` int NOT NULL AUTO_INCREMENT,
  `fechaVenta` date NOT NULL,
  `precioTotal` int DEFAULT NULL,
  `idSocio` int NOT NULL,
  PRIMARY KEY (`idventa`),
  UNIQUE KEY `idventa_UNIQUE` (`idventa`),
  KEY `FK_ventaToSocio_idx` (`idSocio`),
  CONSTRAINT `FK_ventaToSocio` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idsocio`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-07 16:45:19
