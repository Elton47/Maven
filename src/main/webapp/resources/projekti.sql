-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: projekti
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `child`
--

DROP TABLE IF EXISTS `child`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `child` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Full Name` varchar(45) NOT NULL,
  `Age` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  `Validity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Full Name` (`Full Name`),
  KEY `Employee_ID` (`Employee_ID`),
  CONSTRAINT `child_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `child`
--

LOCK TABLES `child` WRITE;
/*!40000 ALTER TABLE `child` DISABLE KEYS */;
/*!40000 ALTER TABLE `child` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(16) NOT NULL,
  `Validity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Country` (`Name`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Albania',1);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(16) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Budget` int(11) NOT NULL,
  `Validity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code` (`Code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'D0001','Networking',1000000,1),(2,'D0002','Information Technology',2000000,1);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SSN` varchar(16) NOT NULL,
  `Full Name` varchar(45) NOT NULL,
  `CellNo` varchar(16) NOT NULL,
  `Wage` int(11) NOT NULL,
  `Country_ID` int(11) NOT NULL,
  `Sector_ID` int(11) NOT NULL,
  `Validity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SSN` (`SSN`),
  UNIQUE KEY `Full Name` (`Full Name`),
  KEY `Country_ID` (`Country_ID`),
  KEY `Sector_ID` (`Sector_ID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`Country_ID`) REFERENCES `country` (`ID`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`Sector_ID`) REFERENCES `sector` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Validity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'Full Control',1);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Validity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator',1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `ID` int(11) DEFAULT NULL,
  `Permission_ID` int(11) NOT NULL,
  KEY `Role_ID` (`ID`),
  KEY `Permission_ID` (`Permission_ID`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`Permission_ID`) REFERENCES `permission` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(16) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Department_ID` int(11) NOT NULL,
  `Employee_ID` int(11) DEFAULT '0',
  `Validity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code` (`Code`),
  KEY `Department_ID` (`Department_ID`),
  KEY `Employee_ID` (`Employee_ID`),
  CONSTRAINT `sector_ibfk_1` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`ID`),
  CONSTRAINT `sector_ibfk_2` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (1,'S0001','Hardware',1,NULL,1);
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `First Name` varchar(16) NOT NULL,
  `Middle Name` varchar(16) NOT NULL,
  `Last Name` varchar(16) NOT NULL,
  `Username` varchar(16) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `Department_ID` int(11) NOT NULL,
  `Role_ID` int(11) NOT NULL,
  `Validity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Username` (`Username`),
  KEY `Department_ID` (`Department_ID`),
  KEY `Role_ID` (`Role_ID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`ID`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`Role_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Elton','47','Memishaj','admin','admin123',1,1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-20 18:12:59
