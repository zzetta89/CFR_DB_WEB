CREATE DATABASE  IF NOT EXISTS `cfr_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cfr_db`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: cfr_db
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `bilet`
--

DROP TABLE IF EXISTS `bilet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bilet` (
  `id_bilet` bigint NOT NULL AUTO_INCREMENT,
  `data_calatoriei` varchar(255) DEFAULT NULL,
  `destinatie` varchar(255) NOT NULL,
  `nr_loc` int NOT NULL,
  `nr_tren` varchar(255) NOT NULL,
  `nr_vagon` int NOT NULL,
  `plecare` varchar(255) NOT NULL,
  `id_calator` bigint DEFAULT NULL,
  `id_sucursala` bigint DEFAULT NULL,
  PRIMARY KEY (`id_bilet`),
  UNIQUE KEY `unique_rezervare` (`nr_tren`,`nr_vagon`,`nr_loc`,`data_calatoriei`),
  UNIQUE KEY `uq_bilet_calator` (`id_calator`,`id_sucursala`,`nr_tren`,`data_calatoriei`),
  KEY `FK1` (`id_sucursala`),
  CONSTRAINT `FK1` FOREIGN KEY (`id_sucursala`) REFERENCES `sucursale` (`id_sucursala`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2` FOREIGN KEY (`id_calator`) REFERENCES `calatori` (`id_calator`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bilet`
--

LOCK TABLES `bilet` WRITE;
/*!40000 ALTER TABLE `bilet` DISABLE KEYS */;
/*!40000 ALTER TABLE `bilet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calatori`
--

DROP TABLE IF EXISTS `calatori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calatori` (
  `id_calator` bigint NOT NULL AUTO_INCREMENT,
  `cnp` varchar(13) DEFAULT NULL,
  `data_nasterii` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nume` varchar(255) NOT NULL,
  `prenume` varchar(255) NOT NULL,
  PRIMARY KEY (`id_calator`),
  UNIQUE KEY `uq_email` (`email`),
  UNIQUE KEY `uq_cnp` (`cnp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calatori`
--

LOCK TABLES `calatori` WRITE;
/*!40000 ALTER TABLE `calatori` DISABLE KEYS */;
/*!40000 ALTER TABLE `calatori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursale`
--

DROP TABLE IF EXISTS `sucursale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sucursale` (
  `id_sucursala` bigint NOT NULL AUTO_INCREMENT,
  `adresa` varchar(255) DEFAULT NULL,
  `denumire` varchar(255) NOT NULL,
  `oras` varchar(255) NOT NULL,
  PRIMARY KEY (`id_sucursala`),
  UNIQUE KEY `uq_sucursala_oras` (`denumire`,`oras`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursale`
--

LOCK TABLES `sucursale` WRITE;
/*!40000 ALTER TABLE `sucursale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursale` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-04 18:03:42
