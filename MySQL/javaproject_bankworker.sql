CREATE DATABASE  IF NOT EXISTS javaproject /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE javaproject;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: javaproject
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table bankworker
--

DROP TABLE IF EXISTS bankworker;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE bankworker (
  bankworker_id int NOT NULL GENERATED ALWAYS AS IDENTITY ,
  Fname varchar(50) NOT NULL,
  Lname varchar(50) NOT NULL,
  Login varchar(100) NOT NULL,
  Password varchar(100) NOT NULL,
  PRIMARY KEY (bankworker_id),
  UNIQUE KEY Login_UNIQUE (Login),
  UNIQUE KEY bankworker_id_UNIQUE (bankworker_id)
);
--
-- Dumping data for table bankworker
--

/*!40000 ALTER TABLE bankworker DISABLE KEYS */;
INSERT INTO bankworker VALUES (1,'Zayn','Sehr','zayniB_090','whosDot_01'),(2,'Nick','Toddler','nikMin(2001)','ezPasw_009'),(3,'Aidai','Cholponkulova','dakusya93','GorkiySt2000'),(4,'Sofya','Zabickaya','ssfZB_001','ezPas_worD(9)'),(5,'nurbol','Tashtan ','nrbltsh','qwerty2'),(6,'Akylbek','Bolsunbekov','akyl','akyl'),(7,'asfasd','asdfasd','asdfas','asdfasdf');
/*!40000 ALTER TABLE bankworker ENABLE KEYS */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-11  2:09:38
