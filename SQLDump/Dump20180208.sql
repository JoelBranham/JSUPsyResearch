CREATE DATABASE  IF NOT EXISTS `psychology` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `psychology`;
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
-- Table structure for table `research`
--

DROP TABLE IF EXISTS `research`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `research` (
  `ResearchID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ResearchName` varchar(100) NOT NULL,
  `ResearchFacilitator` bigint(20) NOT NULL,
  `ResearchDescription` varchar(500) NOT NULL,
  `ResearchCredits` int(11) NOT NULL,
  `IsVisible` tinyint(1) NOT NULL DEFAULT '1',
  `IsDeleted` tinyint(1) NOT NULL DEFAULT '0',
  `CreatedOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ResearchID`),
  KEY `ResearchFacilitator` (`ResearchFacilitator`),
  CONSTRAINT `research_ibfk_1` FOREIGN KEY (`ResearchFacilitator`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `research`
--

LOCK TABLES `research` WRITE;
/*!40000 ALTER TABLE `research` DISABLE KEYS */;
INSERT INTO `research` VALUES (1,'Serial Killers',4,'We study the minds of serial killers',2,1,0,'2017-10-26 19:00:09'),(2,'Fish',5,'We study the minds of fish',3,1,0,'2017-10-26 19:00:09'),(3,'Llamas with Hats',4,'We analyze llamas with hats',1,1,0,'2017-10-26 19:00:09'),(4,'Modern Day Psychology',5,'We study modern-day psychology',2,1,0,'2017-10-26 19:00:09'),(5,'Simple Organisms',4,'We study simple organisms',3,1,0,'2017-10-26 19:00:09'),(6,'Birds',6,'We study the minds of birds',1,1,0,'2017-10-26 19:00:09');
/*!40000 ALTER TABLE `research` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `researchslot`
--

DROP TABLE IF EXISTS `researchslot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `researchslot` (
  `ResearchSlotID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ResearchID` bigint(20) NOT NULL,
  `ResearchSlotOpenings` int(11) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL DEFAULT '0',
  `CreatedOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ResearchSlotID`),
  KEY `ResearchID` (`ResearchID`),
  CONSTRAINT `researchslot_ibfk_1` FOREIGN KEY (`ResearchID`) REFERENCES `research` (`ResearchID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `researchslot`
--

LOCK TABLES `researchslot` WRITE;
/*!40000 ALTER TABLE `researchslot` DISABLE KEYS */;
INSERT INTO `researchslot` VALUES (1,1,0,'2017-08-28 13:00:00','2017-08-28 13:00:00',0,'2017-10-26 19:00:09'),(2,1,6,'2017-08-29 13:00:00','2017-08-29 13:00:00',0,'2017-10-26 19:00:09'),(3,2,5,'2017-09-28 15:30:00','2017-09-28 16:30:00',0,'2017-10-26 19:00:09'),(4,3,8,'2017-10-28 15:30:00','2017-10-28 16:30:00',0,'2017-10-26 19:00:09'),(5,3,4,'2017-10-29 15:30:00','2017-10-29 15:30:00',0,'2017-10-26 19:00:09'),(6,4,6,'2017-11-28 08:00:00','2017-11-28 10:00:00',0,'2017-10-26 19:00:09'),(7,5,5,'2017-12-28 10:00:00','2017-12-28 12:00:00',0,'2017-10-26 19:00:09'),(8,6,10,'2017-12-31 11:00:00','2017-12-31 12:00:00',0,'2017-10-26 19:00:09'),(9,6,5,'2017-12-31 13:00:00','2017-12-31 14:00:00',0,'2017-10-26 19:00:09');
/*!40000 ALTER TABLE `researchslot` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserEmail` varchar(100) NOT NULL,
  `UserPWHash` varchar(64) NOT NULL,
  `UserSalt` varchar(64) NOT NULL,
  `UserRole` varchar(64) NOT NULL,
  `UserPsychMajor` tinyint(1) NOT NULL,
  `UserPsychMinor` tinyint(1) NOT NULL,
  `CreatedOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `CreditsCompleted` bigint(20) NOT NULL,
  PRIMARY KEY (`UserID`),
  KEY `users_ibfk_1` (`UserRole`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'stu1@test.com','test1','93d8594284ab6c719f4efc49f92df34dab0d27595897fa87b9153f2996bace38','student',1,0,'2017-10-26 19:00:09','Aaron','Branham',2),(2,'stu2@test.com','test2','aefe69d8380ac59b0d905acc057448d7d9b139924e88eb316c18dfdf59421e37','student',0,1,'2017-10-26 19:00:09','Ramsay','Snow',10),(3,'admin1@test.com','test1','e62bf146cca5550383763acc9773bd7d6dfa7cdaa917695ea60fe9da5066237a','administrator',0,0,'2017-10-26 19:00:09','Joel','Branham',0),(4,'prof1@test.com','test1','cca227b5b7991554991c1593f324a5f27e855e1a99111fc85587d678daa7910f','professor',0,0,'2017-10-26 19:00:09','Walter','White',0),(5,'prof2@test.com','test2','935367ad577df4f67dac49dd3d4fa810d16cd65fc93238578145e7d3b1ad6b76','professor',0,0,'2017-10-26 19:00:09','Carlton','Banks',0),(6,'stu3@test.com','test3','e95ebd623fa6a2dfade14ac2559bfc3874fd850b5210e33f57469559ad24b2fa','student',1,0,'2017-10-26 19:00:09','Jobish','Mathews',6);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'psychology'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-08  8:54:24
