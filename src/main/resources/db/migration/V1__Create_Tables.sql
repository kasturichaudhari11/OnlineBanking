-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinebankingschema
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET FOREIGN_KEY_CHECKS = 0;

--
-- Current Database: `onlinebankingschema`
--

CREATE DATABASE IF NOT EXISTS `onlinebankingschema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `onlinebankingschema`;

--
-- Table structure for table `checking_account`
--

-- DROP TABLE IF EXISTS `checking_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `checking_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j8hn0jd3fdk2myjrn9n98v7lv` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checking_checkbook`
--

-- DROP TABLE IF EXISTS `checking_checkbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `checking_checkbook` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number_of_pages` int DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `checking_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrw3g12mo27g4fglyg9plobxo1` (`checking_account_id`),
  CONSTRAINT `FKrw3g12mo27g4fglyg9plobxo1` FOREIGN KEY (`checking_account_id`) REFERENCES `checking_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checking_checkbook_request`
--

-- DROP TABLE IF EXISTS `checking_checkbook_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `checking_checkbook_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_approved` varchar(255) DEFAULT NULL,
  `date_requested` varchar(255) DEFAULT NULL,
  `request_approved` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `authorizer_id` int DEFAULT NULL,
  `checking_account_id` int DEFAULT NULL,
  `checking_checkbook_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfdqx93pq7yobelrcf9tyohxn2` (`authorizer_id`),
  KEY `FKjw5vj4achxi15kqxkvuxl4vm6` (`checking_account_id`),
  KEY `FK15j2s0ce7y8l2ltxob09lsmtm` (`checking_checkbook_id`),
  CONSTRAINT `FK15j2s0ce7y8l2ltxob09lsmtm` FOREIGN KEY (`checking_checkbook_id`) REFERENCES `checking_checkbook` (`id`),
  CONSTRAINT `FKfdqx93pq7yobelrcf9tyohxn2` FOREIGN KEY (`authorizer_id`) REFERENCES `user_table` (`id`),
  CONSTRAINT `FKjw5vj4achxi15kqxkvuxl4vm6` FOREIGN KEY (`checking_account_id`) REFERENCES `checking_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `checking_transaction`
--

-- DROP TABLE IF EXISTS `checking_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `checking_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `available_balance` double DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `checking_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4n7jwmwbrdwmnraen5r133i5s` (`checking_account_id`),
  CONSTRAINT `FK4n7jwmwbrdwmnraen5r133i5s` FOREIGN KEY (`checking_account_id`) REFERENCES `checking_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `recipient`
--

-- DROP TABLE IF EXISTS `recipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `recipient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f1esgsavcv9gsw6en71i18i1d` (`name`),
  KEY `FK4wfj4jt4gp2gnuotfo8qccl9f` (`user_id`),
  CONSTRAINT `FK4wfj4jt4gp2gnuotfo8qccl9f` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `savings_account`
--

-- DROP TABLE IF EXISTS `savings_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `savings_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ne76l09lxb4bute9887aarrk` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `savings_checkbook`
--

-- DROP TABLE IF EXISTS `savings_checkbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `savings_checkbook` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number_of_pages` int DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `savings_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3njgl65xsllig2dtsi9imo3w5` (`savings_account_id`),
  CONSTRAINT `FK3njgl65xsllig2dtsi9imo3w5` FOREIGN KEY (`savings_account_id`) REFERENCES `savings_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `savings_checkbook_request`
--

-- DROP TABLE IF EXISTS `savings_checkbook_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `savings_checkbook_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_approved` varchar(255) DEFAULT NULL,
  `date_requested` varchar(255) DEFAULT NULL,
  `request_approved` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `authorizer_id` int DEFAULT NULL,
  `savings_account_id` int DEFAULT NULL,
  `savings_checkbook_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdnysg0hopf5iwlb5ft0vygwc1` (`authorizer_id`),
  KEY `FKr3q9wpv4foqjnmbh0rs8tdkay` (`savings_account_id`),
  KEY `FKpodpb6t8tcukpgsnu3twy6gv` (`savings_checkbook_id`),
  CONSTRAINT `FKdnysg0hopf5iwlb5ft0vygwc1` FOREIGN KEY (`authorizer_id`) REFERENCES `user_table` (`id`),
  CONSTRAINT `FKpodpb6t8tcukpgsnu3twy6gv` FOREIGN KEY (`savings_checkbook_id`) REFERENCES `savings_checkbook` (`id`),
  CONSTRAINT `FKr3q9wpv4foqjnmbh0rs8tdkay` FOREIGN KEY (`savings_account_id`) REFERENCES `savings_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `savings_transaction`
--

-- DROP TABLE IF EXISTS `savings_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `savings_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `available_balance` double DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `savings_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4bt1l2090882974glyn79q2s9` (`savings_account_id`),
  CONSTRAINT `FK4bt1l2090882974glyn79q2s9` FOREIGN KEY (`savings_account_id`) REFERENCES `savings_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `user_table`
--

-- DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS  `user_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `checking_account_id` int DEFAULT NULL,
  `savings_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eamk4l51hm6yqb8xw37i23kb5` (`email`),
  UNIQUE KEY `UK_en3wad7p8qfu8pcmh62gvef6v` (`username`),
  KEY `FK7xgqe4cv8mssl1xprvg0cv0y8` (`checking_account_id`),
  KEY `FK7l2ss6j5glr1aodwaajouo1po` (`savings_account_id`),
  CONSTRAINT `FK7l2ss6j5glr1aodwaajouo1po` FOREIGN KEY (`savings_account_id`) REFERENCES `savings_account` (`id`),
  CONSTRAINT `FK7xgqe4cv8mssl1xprvg0cv0y8` FOREIGN KEY (`checking_account_id`) REFERENCES `checking_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-20 16:23:17
