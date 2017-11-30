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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-30  9:32:48
