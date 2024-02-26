CREATE DATABASE  IF NOT EXISTS `javaproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `javaproject`;
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
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `sum` int NOT NULL,
  `currency` varchar(20) NOT NULL,
  `country` varchar(60) DEFAULT 'Kyrgyzstan',
  `city` varchar(60) NOT NULL,
  `transaction_date` date NOT NULL,
  `sender_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `code` varchar(9) NOT NULL,
  PRIMARY KEY (`transaction_id`,`sender_id`,`receiver_id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `transaction_id_UNIQUE` (`transaction_id`),
  KEY `fk_transaction_client_idx` (`sender_id`),
  KEY `fk_transaction_client1_idx` (`receiver_id`),
  CONSTRAINT `fk_transaction_client` FOREIGN KEY (`sender_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `fk_transaction_client1` FOREIGN KEY (`receiver_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,10000,'dollar','Kyrgyzstan','Bishkek','2010-02-12',1,2,'312KKw019'),(2,20000,'som','Kyrgyzstan','Osh','2011-06-14',7,5,'100LLp023'),(3,1400,'dollar','Russia','Moscow','2011-05-23',2,4,'101ErA020'),(4,1200,'som','Kyrgyzstan','Naryn','2011-06-14',5,8,'102asp111'),(5,240,'dollar','Turkey','Ankara','2012-01-02',3,5,'MkL019Dss'),(6,4500,'som','United States of America','Los Angeles','2021-10-26',4,5,'500qqo889'),(7,12400,'som','Kyrgyzstan','Bishkek','2021-10-26',10,7,'571bcw848'),(8,999,'dollar','Italy','Milan','2021-10-26',3,9,'421ols480'),(9,5000,'dollar','Kyrgyzstan','Tokmok','2021-10-26',1,2,'670srm965'),(10,100,'dollar','Kyrgyzstan','Talas','2021-10-27',8,9,'663pfe324'),(11,84000,'dollar','France','Paris','2021-10-27',9,10,'643unl700'),(12,195000,'dollar','Germany','Berlin','2021-10-27',5,9,'428zkk355'),(13,21500,'dollar','Kyrgyzstan','Bishkek','2021-10-27',3,7,'330azo461'),(14,1950,'dollar','Kyrgyzstan','Osh','2021-10-27',7,3,'726uiz794'),(15,68650,'dollar','Kyrgyzstan','Issyk Kul','2021-10-27',1,5,'204lro163'),(16,13200,'dollar','Japon','Tokio','2021-10-27',7,10,'736kju455'),(17,149000,'dollar','Kyrgyzstan','Batken','2021-10-27',4,9,'360rph567'),(18,20010,'som','Russia','Moscow','2021-10-27',9,3,'511dsm399'),(19,5000,'dollar','Kyrgyzstan','Naryn','2021-10-28',2,3,'562yzs133'),(20,13000,'dollar','Kyrgyzstan','Osh','2021-10-28',2,5,'596ufo651'),(21,130,'dollar','Russia','Moscow','2021-10-28',7,4,'992pmx732'),(22,12000,'dollar','Kyrgyzstan','Osh','2021-10-29',9,8,'359cdg632'),(23,100,'dollar','Kyrgyzstan','Osh','2021-11-11',9,10,'840fzj774'),(24,33,'dollar','Kyrgyzstan','Balyktchy','2022-04-08',11,11,'372iui492'),(25,4246,'dollar','USA','New York','2022-04-11',5,11,'173wua953'),(26,475,'som','USA','New York','2022-04-11',10,9,'852mlk577');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-11  2:09:37
