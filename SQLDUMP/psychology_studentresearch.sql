-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: psychology
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `studentresearch`
--

DROP TABLE IF EXISTS `studentresearch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentresearch` (
  `StudentResearchID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserID` bigint(20) NOT NULL,
  `ResearchSlotID` bigint(20) NOT NULL,
  `IsCompleted` tinyint(1) NOT NULL DEFAULT '0',
  `IsDropped` tinyint(1) NOT NULL DEFAULT '0',
  `CompletedOn` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `CreatedOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`StudentResearchID`),
  KEY `UserID` (`UserID`),
  KEY `ResearchSlotID` (`ResearchSlotID`),
  CONSTRAINT `studentresearch_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`),
  CONSTRAINT `studentresearch_ibfk_2` FOREIGN KEY (`ResearchSlotID`) REFERENCES `researchslot` (`ResearchSlotID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentresearch`
--

LOCK TABLES `studentresearch` WRITE;
/*!40000 ALTER TABLE `studentresearch` DISABLE KEYS */;
INSERT INTO `studentresearch` VALUES (1,1,1,1,0,'1900-01-01 00:00:00','2017-10-26 19:00:09'),(2,1,3,0,0,'1900-01-01 00:00:00','2017-10-26 19:00:09'),(3,2,2,1,0,'1900-01-01 00:00:00','2017-10-26 19:00:09'),(4,2,4,0,0,'1900-01-01 00:00:00','2017-10-26 19:00:09'),(5,2,7,1,0,'1900-01-01 00:00:00','2017-10-26 19:00:09'),(6,1,4,1,0,'1900-01-01 00:00:00','2017-10-26 19:00:09'),(7,2,1,0,0,'1900-01-01 00:00:00','2017-10-26 19:00:09');
/*!40000 ALTER TABLE `studentresearch` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-30  9:32:47
