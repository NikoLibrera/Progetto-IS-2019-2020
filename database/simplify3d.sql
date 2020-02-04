CREATE DATABASE  IF NOT EXISTS `simplify3d` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `simplify3d`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: simplify3d
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `commento`
--

DROP TABLE IF EXISTS `commento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commento` (
  `id_commento` int(11) NOT NULL,
  `contenuto` varchar(255) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `id_progetto` int(11) NOT NULL,
  PRIMARY KEY (`id_commento`),
  KEY `username_idx` (`username`),
  KEY `id_progetto_idx` (`id_progetto`),
  CONSTRAINT `id_progetto` FOREIGN KEY (`id_progetto`) REFERENCES `progetto` (`id_progetto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usernameCommento` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `download`
--

DROP TABLE IF EXISTS `download`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `download` (
  `username` varchar(15) NOT NULL,
  `id_progetto` int(11) NOT NULL,
  PRIMARY KEY (`username`,`id_progetto`),
  KEY `id_progettoDownload_idx` (`id_progetto`),
  CONSTRAINT `id_progettoDownload` FOREIGN KEY (`id_progetto`) REFERENCES `progetto` (`id_progetto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usernameDownload` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notifica`
--

DROP TABLE IF EXISTS `notifica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifica` (
  `id_notifica` int(11) NOT NULL,
  `immagine` longblob,
  `titolo` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `isClicked` tinyint(4) DEFAULT NULL,
  `id_commento` int(11) DEFAULT NULL,
  `id_risposta` int(11) DEFAULT NULL,
  `id_progetto` int(11) DEFAULT NULL,
  `id_valutazione` int(11) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_notifica`),
  KEY `id_progettoNotifica_idx` (`id_progetto`),
  KEY `id_valutazioneNotifica_idx` (`id_valutazione`),
  KEY `id_rispostaNotifica_idx` (`id_risposta`),
  KEY `usernameNotifica_idx` (`username`),
  KEY `id_commentoNotifica_idx` (`id_commento`),
  CONSTRAINT `id_commentoNotifica` FOREIGN KEY (`id_commento`) REFERENCES `commento` (`id_commento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_progettoNotifica` FOREIGN KEY (`id_progetto`) REFERENCES `progetto` (`id_progetto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_rispostaNotifica` FOREIGN KEY (`id_risposta`) REFERENCES `risposta_commento` (`id_risposta_commento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_valutazioneNotifica` FOREIGN KEY (`id_valutazione`) REFERENCES `valutazione` (`id_valutazione`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usernameNotifica` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `preferiti`
--

DROP TABLE IF EXISTS `preferiti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferiti` (
  `username` varchar(15) NOT NULL,
  `id_progetto` int(11) NOT NULL,
  PRIMARY KEY (`username`,`id_progetto`),
  KEY `id_progetto_idx` (`id_progetto`),
  CONSTRAINT `id_progettoPreferiti` FOREIGN KEY (`id_progetto`) REFERENCES `progetto` (`id_progetto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usernamePreferiti` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `progetto`
--

DROP TABLE IF EXISTS `progetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progetto` (
  `id_progetto` int(11) NOT NULL,
  `titolo` varchar(255) DEFAULT NULL,
  `descrizione` varchar(500) DEFAULT NULL,
  `file_modello` longblob,
  `immagine` longblob,
  `consigli` varchar(500) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `versione` int(11) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_progetto`),
  KEY `username_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `risposta_commento`
--

DROP TABLE IF EXISTS `risposta_commento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `risposta_commento` (
  `id_risposta_commento` int(11) NOT NULL,
  `contenuto` varchar(500) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `id_commento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_risposta_commento`),
  KEY `usernameRisposta_idx` (`username`),
  KEY `id_commento_idx` (`id_commento`),
  CONSTRAINT `id_commento` FOREIGN KEY (`id_commento`) REFERENCES `commento` (`id_commento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usernameRisposta` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `username` varchar(15) NOT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `data_nascita` date DEFAULT NULL,
  `isAdmin` tinyint(4) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nazionalita` varchar(255) DEFAULT NULL,
  `confermato` tinyint(4) DEFAULT NULL,
  `codice` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `valutazione`
--

DROP TABLE IF EXISTS `valutazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valutazione` (
  `id_valutazione` int(11) NOT NULL,
  `voto` int(11) DEFAULT NULL,
  `id_progetto` int(11) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_valutazione`),
  KEY `id_progettoValutazione_idx` (`id_progetto`),
  KEY `usernameValutazione_idx` (`username`),
  CONSTRAINT `id_progettoValutazione` FOREIGN KEY (`id_progetto`) REFERENCES `progetto` (`id_progetto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usernameValutazione` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-04 16:56:10
