-- MySQL dump 10.14  Distrib 5.5.52-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: InventoryItemDb
-- ------------------------------------------------------
-- Server version	5.5.52-MariaDB

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
-- Table structure for table `Class`
--

DROP TABLE IF EXISTS `Class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Class` (
  `classId` int(11) NOT NULL AUTO_INCREMENT,
  `classTitle` varchar(6) DEFAULT NULL,
  `className` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Class`
--

LOCK TABLES `Class` WRITE;
/*!40000 ALTER TABLE `Class` DISABLE KEYS */;
INSERT INTO `Class` (`classId`, `classTitle`, `className`) VALUES (1,'ISTE43','Data Mining'),(2,'ISTE68','Data Mining'),(3,'NSSA34','Networking Systems Analysis'),(4,'ISTE24','Web II'),(6,'ISTE24','Web II'),(7,'IST590','Web III'),(8,NULL,NULL),(9,'ISTE24','Web II'),(10,'ISTE43','Data Mining'),(11,'ISTE4','Data Mining'),(12,'ISTE5','Data Mining'),(13,'ISTE6','Data Mining');
/*!40000 ALTER TABLE `Class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Damage`
--

DROP TABLE IF EXISTS `Damage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Damage` (
  `damageId` int(11) NOT NULL AUTO_INCREMENT,
  `damageName` varchar(45) DEFAULT NULL,
  `damageDescription` text,
  `Severity` int(3) DEFAULT NULL,
  PRIMARY KEY (`damageId`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Damage`
--

LOCK TABLES `Damage` WRITE;
/*!40000 ALTER TABLE `Damage` DISABLE KEYS */;
INSERT INTO `Damage` (`damageId`, `damageName`, `damageDescription`, `Severity`) VALUES (1,'Cracked Screen','There is a crack in the glass covering the screen',10),(2,'Port Broken','Port 3 doesnt recognize any cables',60),(3,'Chipped Screen corner','There is a chip in the glass covering the screen',2),(4,'Cracked Screen','There is a crack in the glass covering the screen',10),(5,'Cracked Screen','There is a crack in the glass covering the screen',10),(6,'Shattered Screen','The screen has shattered completely',10),(7,'Cracked Screen','There is a crack in the glass covering the screen',10),(8,'Cracked Screen','There is a crack in the glass covering the screen',10),(9,'Cracked Screen','There is a crack in the glass covering the screen',7),(10,'Cracked Screen','There is a crack in the glass covering the screen',10),(12,'Cracked Screen','There is a crack in the glass covering the screen',7),(13,'Cracked Screen','There is a crack in the glass covering the screen',7),(14,'Cracked Screen','There is a crack in the glass covering the screen',7),(15,'Cracked Screen','There is a crack in the glass covering the screen',7),(16,'Cracked Screen','There is a crack in the glass covering the screen',7),(17,'Cracked Screen','There is a crack in the glass covering the screen',7),(18,'Cracked Screen','There is a crack in the glass covering the screen',7),(19,'Cracked Screen','There is a crack in the glass covering the screen',7),(20,'Cracked Screen','There is a crack in the glass covering the screen',7),(21,'Cracked Screen','There is a crack in the glass covering the screen',7),(22,'Cracked Screen','There is a crack in the glass covering the screen',7),(23,'Cracked Screen','There is a crack in the glass covering the screen',7),(24,'Cracked Screen','There is a crack in the glass covering the screen',7),(25,'Cracked Screen','There is a crack in the glass covering the screen',7),(26,'Cracked Screen','There is a crack in the glass covering the screen',7),(27,'Cracked Screen','There is a crack in the glass covering the screen',7),(28,'Cracked Screen','There is a crack in the glass covering the screen',7),(29,'Cracked Screen','There is a crack in the glass covering the screen',7),(30,'Cracked Screen','There is a crack in the glass covering the screen',7),(31,'Cracked Screen','There is a crack in the glass covering the screen',7),(32,'Cracked Screen','There is a crack in the glass covering the screen',7),(33,'Cracked Screen','There is a crack in the glass covering the screen',7),(34,'Cracked Screen','There is a crack in the glass covering the screen',7),(35,'Cracked Screen','There is a crack in the glass covering the screen',7),(36,'Cracked Screen','There is a crack in the glass covering the screen',7),(37,'Cracked Screen','There is a crack in the glass covering the screen',7),(38,'Cracked Screen','There is a crack in the glass covering the screen',7),(39,'Cracked Screen','There is a crack in the glass covering the screen',7),(40,'Cracked Screen','There is a crack in the glass covering the screen',7),(41,'Cracked Screen','There is a crack in the glass covering the screen',7),(42,'Cracked Screen','There is a crack in the glass covering the screen',7),(43,'Cracked Screen','There is a crack in the glass covering the screen',7),(44,'Cracked Screen','There is a crack in the glass covering the screen',7),(45,'Cracked Screen','There is a crack in the glass covering the screen',7),(46,'Cracked Screen','There is a crack in the glass covering the screen',7),(47,'Cracked Screen','There is a crack in the glass covering the screen',7),(48,'Cracked Screen','There is a crack in the glass covering the screen',7),(49,'Cracked Screen','There is a crack in the glass covering the screen',7),(50,'Cracked Screen','There is a crack in the glass covering the screen',7),(51,'Cracked Screen','There is a crack in the glass covering the screen',7),(52,'Cracked Screen','There is a crack in the glass covering the screen',7),(53,'Cracked Screen','There is a crack in the glass covering the screen',7),(54,'Cracked Screen','There is a crack in the glass covering the screen',7),(55,'Cracked Screen','There is a crack in the glass covering the screen',7),(56,'Cracked Screen','There is a crack in the glass covering the screen',7),(57,'Cracked Screen','There is a crack in the glass covering the screen',7),(58,'Cracked Screen','There is a crack in the glass covering the screen',7),(59,'Cracked Screen','There is a crack in the glass covering the screen',8),(60,'Cracked Screen','There is a crack in the glass covering the screen',9);
/*!40000 ALTER TABLE `Damage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` blob,
  `serialNumber` varchar(45) DEFAULT NULL,
  `department` varchar(14) DEFAULT NULL,
  `aquireDate` date DEFAULT NULL,
  `yellowTag` int(8) DEFAULT NULL,
  `procurementOrder` varchar(45) DEFAULT NULL,
  `cost` decimal(12,2) DEFAULT NULL,
  `assetTag` varchar(45) DEFAULT NULL,
  `ItemType_itemTypeId` int(11) NOT NULL,
  PRIMARY KEY (`itemId`),
  KEY `fk_Item_ItemType_idx` (`ItemType_itemTypeId`),
  CONSTRAINT `fk_Item_ItemType` FOREIGN KEY (`ItemType_itemTypeId`) REFERENCES `ItemType` (`itemTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` (`itemId`, `barcode`, `serialNumber`, `department`, `aquireDate`, `yellowTag`, `procurementOrder`, `cost`, `assetTag`, `ItemType_itemTypeId`) VALUES (1,NULL,'3544949227338030','WMC','2009-02-27',147522,'#839f87',0.00,'839763',2),(2,NULL,'5510797673195882',NULL,'2009-05-27',617565,'#671674',0.00,'499458',3),(3,NULL,'3565849257252057','CS','2009-12-06',911433,'#3fc1c9',0.00,'834939',4),(4,NULL,'3544232451743419',NULL,'2009-09-07',705871,'#af2631',0.00,'222785',4),(5,NULL,'4508832307339288','ISTE','2008-07-07',425603,'#7022a7',0.00,'234993',1),(6,NULL,'3580520492410385',NULL,'2008-12-16',841689,'#f777ff',0.00,'897743',2),(7,NULL,'5010125847133156','ISTE','2008-10-31',236469,'#dbb73b',0.00,'589583',3),(8,NULL,'4844924124441463','WMC','2010-03-06',781798,'#404298',0.00,'724322',1),(9,NULL,'374283678241621','ISTE','2009-06-16',811624,'#6f6aae',0.00,'328460',1),(10,NULL,'4903068947062256286',NULL,'2010-03-15',517647,'#347ce0',0.00,'191574',5),(11,NULL,'30590414520647','ISTE','2008-10-14',686449,'#d1db46',0.00,'142417',5),(12,NULL,'3577449636217057','ISTE','2008-06-28',687583,'#c8a203',0.00,'874207',5),(13,NULL,'3563979439540558','WMC','2008-08-15',131893,'#80870d',0.00,'751922',2),(14,NULL,'3579595199344577','CS','2010-02-03',973546,'#3ff540',0.00,'950743',5),(15,NULL,'337941164544677','ISTE','2009-11-27',752791,'#9c1207',0.00,'842210',4),(16,NULL,'3562397567580547','CS','2009-07-08',219894,'#2c50e3',0.00,'659485',5),(17,NULL,'5757574656','WMC','2009-02-26',147522,'#939f87',199.99,'839763',6),(18,NULL,'3257574656','WMC','2009-02-26',147522,'#939f87',199.99,'839763',7),(19,NULL,'11257574656','WMC','2009-02-26',147522,'#939f87',199.99,'839763',8),(20,NULL,'3544949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',9),(21,NULL,'3544949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',10),(22,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',877.00,'839763',11),(23,'2423424234','343264949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',13),(24,'92423424234','143264949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',14),(25,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',23),(29,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',27),(30,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',28),(31,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',29),(32,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',31),(33,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',32),(34,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',33),(35,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',35),(36,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',36),(37,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',37),(38,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',3),(39,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',3),(40,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',35),(41,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',3),(42,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',35),(43,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',35),(44,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',35),(45,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',37),(46,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',37),(47,'12335667','3244949227338030','WMC','2009-02-26',147522,'#839f87',0.00,'839763',37),(48,'12335667','3244949227338030','WMC','2009-02-27',147522,'#839f87',0.00,'839763',37),(49,'12335667','3244949227338030','WMC','2009-02-27',147522,'#839f87',0.00,'839763',37),(50,'12335667','3244949227338030','WMC','2009-02-27',147522,'#839f87',0.00,'839763',37),(51,'123325667','32445949227338030','WMC','2009-02-27',147522,'#839f87',0.00,'839763',37),(52,'1233325667','324459492273387030','WMC','2009-02-27',147522,'#839f87',0.00,'839763',37),(53,'12343234325667','324459434922733870230','WMC','2009-02-27',147522,'#839f87',0.00,'839763',34),(54,'1234323434325667','324423459434922733870230','WMC','2009-02-27',147522,'#839f87',0.00,'839763',34);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ItemType`
--

DROP TABLE IF EXISTS `ItemType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemType` (
  `ItemTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `itemTypeName` varchar(45) DEFAULT NULL,
  `image` blob,
  `manufacturer` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ItemTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ItemType`
--

LOCK TABLES `ItemType` WRITE;
/*!40000 ALTER TABLE `ItemType` DISABLE KEYS */;
INSERT INTO `ItemType` (`ItemTypeId`, `itemTypeName`, `image`, `manufacturer`, `model`) VALUES (1,'Laptop',NULL,'Dell','XPS13'),(2,'Laptop',NULL,'Dell','Inspiron 7800'),(3,'Router',NULL,'Cisco','RV325'),(4,'Router',NULL,'Cisco','RV320'),(5,'Tablet',NULL,'Apple','Ipad 4'),(6,'Laptop',NULL,'Dell','Inspiron 7800'),(7,'Laptop',NULL,'Dell','Inspiron 7800'),(8,'Mini Laptop',NULL,'Dell','Inspiron 7800'),(9,'Laptop',NULL,'Dell','Inspiron 7800'),(10,'Laptop',NULL,'Dell','Inspiron 7800'),(11,'Laptop',NULL,'Dell','Inspiron 7800'),(12,'Laptop',NULL,'Dell','Inspiron 7800'),(13,'Laptop',NULL,'Dell','Inspiron 7800'),(14,'Laptop',NULL,'Dell','Inspiron 7800'),(16,'Laptop','image.jpg','Dell','Inspiron 7900'),(17,'Laptop','image.jpg','Dell','Inspiron 8900'),(18,'Laptop','image.jpg','Dell','Inspiron 8900'),(19,'Laptop','image.jpg','Dell','Inspiron 8900'),(20,'Laptop','image.jpg','Dell','Inspiron 8900'),(21,'Laptop','image.jpg','Dell','Inspiron 8900'),(22,'Laptop','image.jpg','Dell','Inspiron 8900'),(23,'Laptop','example.jpg','Dell','Inspiron 7800'),(24,'Laptop','example.jpg','Dell','Inspiron 7800'),(25,'Laptop','example.jpg','Dell','Inspiron 7800'),(26,'Laptop','example.jpg','Dell','Inspiron 7800'),(27,'Laptop','example.jpg','Dell','Inspiron 7800'),(28,'Laptop','example.jpg','Dell','Inspiron 7800'),(29,'Laptop','example.jpg','Dell','Inspiron 7800'),(30,'Laptop','image.jpg','Dell','Inspiron 8900'),(31,'Mini Laptop','image.jpg','Dell','Inspiron 8900'),(32,'Mini Laptop','image.jpg','Dell','Inspiron 8900'),(33,NULL,'example.jpg',NULL,NULL),(34,'Laptop','image.jpg','Dell','Inspiron 8900'),(35,NULL,'example.jpg',NULL,NULL),(36,NULL,'example.jpg',NULL,NULL),(37,NULL,'example.jpg',NULL,NULL),(38,'Laptop','image.jpg','Dell','Inspiron 8900'),(39,'Laptop2','image.jpg','Dell','Inspiron 8900'),(40,'Laptop3','image.jpg','Dell','Inspiron 8900'),(41,'Laptop4','image.jpg','Dell','Inspiron 8900');
/*!40000 ALTER TABLE `ItemType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item_has_Damage`
--

DROP TABLE IF EXISTS `Item_has_Damage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item_has_Damage` (
  `Item_itemId` int(11) NOT NULL,
  `Damage_damageId` int(11) NOT NULL,
  PRIMARY KEY (`Item_itemId`,`Damage_damageId`),
  KEY `fk_Item_has_Damage_Damage_idx` (`Damage_damageId`),
  KEY `fk_Item_has_Damage_Item_idx` (`Item_itemId`),
  CONSTRAINT `fk_Item_has_Damage_Damage` FOREIGN KEY (`Damage_damageId`) REFERENCES `Damage` (`damageId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_Damage_Item` FOREIGN KEY (`Item_itemId`) REFERENCES `Item` (`itemId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item_has_Damage`
--

LOCK TABLES `Item_has_Damage` WRITE;
/*!40000 ALTER TABLE `Item_has_Damage` DISABLE KEYS */;
INSERT INTO `Item_has_Damage` (`Item_itemId`, `Damage_damageId`) VALUES (1,1),(20,4),(21,5),(22,6),(23,7),(24,8),(25,10),(31,10),(33,10),(33,13);
/*!40000 ALTER TABLE `Item_has_Damage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item_has_Warranty`
--

DROP TABLE IF EXISTS `Item_has_Warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item_has_Warranty` (
  `Item_itemId` int(11) NOT NULL,
  `Warranty_warrentyId` int(11) NOT NULL,
  PRIMARY KEY (`Item_itemId`,`Warranty_warrentyId`),
  KEY `fk_Item_has_Warranty_Warranty_idx` (`Warranty_warrentyId`),
  KEY `fk_Item_has_Warranty_Item_idx` (`Item_itemId`),
  CONSTRAINT `fk_Item_has_Warranty_Item` FOREIGN KEY (`Item_itemId`) REFERENCES `Item` (`itemId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_Warranty_Warranty` FOREIGN KEY (`Warranty_warrentyId`) REFERENCES `Warranty` (`warrentyId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item_has_Warranty`
--

LOCK TABLES `Item_has_Warranty` WRITE;
/*!40000 ALTER TABLE `Item_has_Warranty` DISABLE KEYS */;
INSERT INTO `Item_has_Warranty` (`Item_itemId`, `Warranty_warrentyId`) VALUES (1,1),(20,3),(21,4),(22,5),(23,6),(24,7),(25,8),(29,8),(30,8);
/*!40000 ALTER TABLE `Item_has_Warranty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Major`
--

DROP TABLE IF EXISTS `Major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Major` (
  `majorID` int(11) NOT NULL AUTO_INCREMENT,
  `majorTitle` varchar(45) DEFAULT NULL,
  `majorAbbreviation` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`majorID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Major`
--

LOCK TABLES `Major` WRITE;
/*!40000 ALTER TABLE `Major` DISABLE KEYS */;
INSERT INTO `Major` (`majorID`, `majorTitle`, `majorAbbreviation`) VALUES (1,'Information Technology','IST'),(2,'Software Engineering','SE'),(3,'Software Engineering','SE'),(4,'Software Engineering','SE'),(5,'Electrical Engineering','EE'),(6,NULL,NULL),(7,NULL,NULL),(8,NULL,NULL),(9,'Software Engineering','SE'),(10,'Software Engineering','SE'),(11,'Software Engineering II','SE'),(12,'Software Engineering III','SEE'),(13,'Software Engineering III','SEE2'),(14,'Software Engineering IV','SEE2');
/*!40000 ALTER TABLE `Major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Offense`
--

DROP TABLE IF EXISTS `Offense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Offense` (
  `offenseId` int(11) NOT NULL AUTO_INCREMENT,
  `offenseName` varchar(45) NOT NULL,
  `offenseDescription` text,
  `offenseDate` date NOT NULL,
  `rentalId` int(11) DEFAULT NULL,
  `itemId` varchar(45) NOT NULL,
  PRIMARY KEY (`offenseId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Offense`
--

LOCK TABLES `Offense` WRITE;
/*!40000 ALTER TABLE `Offense` DISABLE KEYS */;
INSERT INTO `Offense` (`offenseId`, `offenseName`, `offenseDescription`, `offenseDate`, `rentalId`, `itemId`) VALUES (1,'Broken Screen','looks like somebody dropped it','2017-05-07',NULL,'1');
/*!40000 ALTER TABLE `Offense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Priviledge`
--

DROP TABLE IF EXISTS `Priviledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Priviledge` (
  `priviledgeId` int(11) NOT NULL AUTO_INCREMENT,
  `priviledgeName` varchar(40) NOT NULL,
  PRIMARY KEY (`priviledgeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Priviledge`
--

LOCK TABLES `Priviledge` WRITE;
/*!40000 ALTER TABLE `Priviledge` DISABLE KEYS */;
INSERT INTO `Priviledge` (`priviledgeId`, `priviledgeName`) VALUES (1,'Lab Assistant'),(2,'Graduate Assistant'),(3,'Student'),(4,'Administrator'),(5,'Faculty');
/*!40000 ALTER TABLE `Priviledge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rental`
--

DROP TABLE IF EXISTS `Rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rental` (
  `rentalId` int(11) NOT NULL AUTO_INCREMENT,
  `signature` varchar(45) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `User_userId` int(11) NOT NULL,
  PRIMARY KEY (`rentalId`,`User_userId`),
  KEY `fk_Rental_User_idx` (`User_userId`),
  CONSTRAINT `fk_Rental_User` FOREIGN KEY (`User_userId`) REFERENCES `User` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rental`
--

LOCK TABLES `Rental` WRITE;
/*!40000 ALTER TABLE `Rental` DISABLE KEYS */;
INSERT INTO `Rental` (`rentalId`, `signature`, `startDate`, `endDate`, `User_userId`) VALUES (2,NULL,'2017-05-15','2017-06-15',1),(3,NULL,'2017-05-15','2017-06-15',2),(39,'signature','2017-04-21','2017-05-27',1),(40,'signature','2017-04-21','2017-05-27',1),(41,'signature','2017-04-21','2017-05-27',1),(42,'signature','2017-04-20','2017-05-06',1),(43,'signature','2017-04-20','2017-05-06',1),(44,'signature','2017-04-20','2017-05-06',1),(45,'signature','2017-04-20','2017-05-06',1),(46,'signature','2017-04-20','2017-05-06',1),(47,'signature','2017-04-20','2017-05-06',297005120),(48,'signature','2017-04-20','2017-05-06',2),(49,'signature','2017-04-20','2017-05-06',2),(50,'signature','2017-04-20',NULL,2),(51,'signature',NULL,NULL,3),(52,'signature',NULL,NULL,3),(53,'signature',NULL,NULL,3),(54,'signature',NULL,NULL,3),(55,'signature',NULL,NULL,3),(56,'signature',NULL,NULL,3),(57,'signature',NULL,NULL,3),(58,'signature',NULL,NULL,3),(59,'signature',NULL,NULL,4),(60,'signature',NULL,NULL,4),(61,'signature','2017-04-23','2017-05-05',300),(62,'signature','2017-04-23','2017-05-05',300),(63,'signature','2017-04-23','2017-05-05',300),(64,NULL,NULL,NULL,300),(65,NULL,NULL,NULL,300),(66,NULL,NULL,NULL,300),(67,NULL,NULL,NULL,300),(68,'signature','2017-05-23','2017-06-05',300),(69,NULL,NULL,NULL,300),(70,'300 signature','2019-05-26','2018-06-25',300),(71,NULL,NULL,NULL,4),(72,NULL,NULL,NULL,3),(73,NULL,NULL,NULL,3),(74,'signature','2017-04-20',NULL,2),(75,NULL,NULL,NULL,6),(76,'signature','2017-04-20',NULL,6),(77,NULL,NULL,NULL,2),(78,NULL,NULL,NULL,6),(79,'signature','2017-04-20',NULL,6),(80,'signature','2017-05-23','2017-06-05',7),(81,'signature',NULL,NULL,7),(82,'signature','2017-05-23','2017-06-05',300),(83,'signature','2017-05-23','2017-06-05',300),(84,'signature','2017-05-23','2017-06-05',300),(85,'signature','2017-05-23','2017-06-05',300),(86,'signature','2017-05-23','2017-06-05',300),(87,'signature','2017-05-23','2017-06-05',300),(88,'signature','2017-05-23','2017-06-05',300),(89,'signature','2017-05-23','2017-06-05',300),(90,'signature','2017-05-23','2017-06-05',300),(91,'signature',NULL,NULL,7),(92,'signature',NULL,NULL,7),(93,NULL,NULL,NULL,8),(94,NULL,NULL,NULL,8),(95,NULL,NULL,NULL,8),(96,NULL,NULL,NULL,8),(97,NULL,NULL,NULL,8),(98,NULL,NULL,NULL,8),(99,NULL,NULL,NULL,8),(100,NULL,NULL,NULL,8),(101,NULL,NULL,NULL,8),(102,'signature',NULL,NULL,8),(103,'signature',NULL,NULL,8),(104,'signature',NULL,NULL,297005120),(105,'signature',NULL,NULL,297005120),(106,'signature',NULL,NULL,297005120),(107,'signature',NULL,NULL,297005120),(108,NULL,NULL,NULL,300),(109,NULL,NULL,NULL,300),(110,NULL,NULL,NULL,300),(111,NULL,NULL,NULL,300),(112,NULL,NULL,NULL,300),(113,NULL,NULL,NULL,300),(114,NULL,NULL,NULL,300),(115,NULL,NULL,NULL,300),(116,NULL,NULL,NULL,300),(117,NULL,NULL,NULL,300),(118,NULL,NULL,NULL,300),(119,NULL,NULL,NULL,300),(120,NULL,NULL,NULL,300),(121,NULL,NULL,NULL,300),(122,'signature','2017-05-23','2017-06-05',300),(123,'signature','2017-05-23','2017-06-05',300),(125,NULL,NULL,NULL,311),(126,NULL,NULL,NULL,311),(127,NULL,NULL,NULL,311),(128,NULL,NULL,NULL,311),(129,NULL,NULL,NULL,311),(130,NULL,NULL,NULL,311);
/*!40000 ALTER TABLE `Rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rental_has_Item`
--

DROP TABLE IF EXISTS `Rental_has_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rental_has_Item` (
  `Rental_rentalId` int(11) NOT NULL,
  `Rental_Student_studentId` int(11) NOT NULL,
  `Item_itemId` int(11) NOT NULL,
  PRIMARY KEY (`Rental_rentalId`,`Rental_Student_studentId`,`Item_itemId`),
  KEY `fk_Rental_has_Item_Item_idx` (`Item_itemId`),
  KEY `fk_Rental_has_Item_Rental_idx` (`Rental_rentalId`,`Rental_Student_studentId`),
  CONSTRAINT `fk_Rental_has_Item_Item` FOREIGN KEY (`Item_itemId`) REFERENCES `Item` (`itemId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rental_has_Item_Rental` FOREIGN KEY (`Rental_rentalId`, `Rental_Student_studentId`) REFERENCES `Rental` (`rentalId`, `User_userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rental_has_Item`
--

LOCK TABLES `Rental_has_Item` WRITE;
/*!40000 ALTER TABLE `Rental_has_Item` DISABLE KEYS */;
INSERT INTO `Rental_has_Item` (`Rental_rentalId`, `Rental_Student_studentId`, `Item_itemId`) VALUES (2,1,2),(41,1,5),(45,1,5),(46,1,5),(48,2,5),(49,2,5),(50,2,5),(59,4,5),(60,4,2),(63,300,11),(70,300,2),(70,300,3),(80,7,1),(80,7,2),(80,7,3),(81,7,1),(81,7,2),(81,7,3),(103,8,3),(103,8,10);
/*!40000 ALTER TABLE `Rental_has_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `reservationId` int(11) NOT NULL AUTO_INCREMENT,
  `User_reserverId` int(11) NOT NULL,
  `Rental_rentalId` int(11) NOT NULL,
  `ItemType_itemTypeId` int(11) NOT NULL,
  PRIMARY KEY (`reservationId`),
  KEY `fk_Reservervation_User_idx` (`User_reserverId`),
  KEY `fk_Reservervation_Rental_idx` (`Rental_rentalId`),
  KEY `fk_Reservervation_ItemType_idx` (`ItemType_itemTypeId`),
  CONSTRAINT `fk_Reservervation_User` FOREIGN KEY (`User_reserverId`) REFERENCES `User` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservervation_ItemType` FOREIGN KEY (`ItemType_itemTypeId`) REFERENCES `ItemType` (`ItemTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservervation_Rental` FOREIGN KEY (`Rental_rentalId`) REFERENCES `Rental` (`rentalId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` (`reservationId`, `User_reserverId`, `Rental_rentalId`, `ItemType_itemTypeId`) VALUES (2,1,2,2),(9,1,40,3),(10,1,41,1),(11,1,42,2),(12,1,43,5),(13,1,44,5),(14,1,45,1),(15,1,46,1),(47,297005120,47,1),(48,2,48,1),(49,2,49,1),(50,2,50,1),(51,3,51,1),(52,3,52,1),(53,3,53,1),(54,3,54,3),(55,3,55,1),(56,3,56,5),(57,3,57,5),(58,3,58,8),(59,4,59,1),(60,4,60,3),(63,300,64,3),(64,300,65,3),(65,300,66,3),(66,300,67,3),(67,300,69,3),(68,4,71,3),(69,3,72,2),(70,3,73,3),(71,6,75,4),(72,2,77,1),(73,6,78,1),(83,300,108,3),(84,300,109,3),(85,300,110,3),(86,300,111,3),(90,300,119,3),(91,300,120,3),(92,300,121,3),(93,311,125,3),(94,311,126,4),(95,311,127,5),(96,311,128,5),(97,311,129,5),(98,311,130,5);
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Section`
--

DROP TABLE IF EXISTS `Section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Section` (
  `Class_classId` int(11) NOT NULL,
  `section` int(2) NOT NULL,
  PRIMARY KEY (`Class_classId`,`section`),
  CONSTRAINT `fk_Section_Class` FOREIGN KEY (`Class_classId`) REFERENCES `Class` (`classId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Section`
--

LOCK TABLES `Section` WRITE;
/*!40000 ALTER TABLE `Section` DISABLE KEYS */;
INSERT INTO `Section` (`Class_classId`, `section`) VALUES (1,2),(2,1),(2,2),(2,3),(2,4),(2,5);
/*!40000 ALTER TABLE `Section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `flagged` tinyint(1) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=300000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` (`userId`, `username`, `email`, `flagged`, `password`) VALUES (1,'abc1234','abc1234',1,'123'),(2,'abcics','abcics',0,'123'),(3,'ab1234','ab1234',0,'123'),(4,'axb1234','axb1234',0,'123'),(5,'acd1234','acd1234',0,'123'),(6,'add1234','add1234',0,'123'),(7,'abb1234','abb1234',0,'123'),(8,'aqs1234','aqs1234',0,'123'),(9,'addics','addics',0,'123'),(10,'a11111','abc1234',0,'123'),(11,'abc2234','abc2234',1,'123'),(12,'abc2234','abc2234',1,'123'),(13,'abc2234','abc2234',1,'123'),(14,'abc2234','abc2234',1,'123'),(100,'abc1234','abc1234',1,'123'),(200,'abc1234','abc1234',1,'123'),(300,'abc300','abc300',0,'123'),(301,'abc1234','abc1234',0,'123'),(302,'abc1234','email',0,'123'),(303,'abc1234','email',0,'123'),(304,'abc12345','email',0,'123'),(305,'abc123456','email2',0,'123'),(306,'abc1234567','email3',0,'123'),(311,'abc1234','abc1234',0,'123'),(297005120,'abc2234','abc2234',0,'123');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_has_Class`
--

DROP TABLE IF EXISTS `User_has_Class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_has_Class` (
  `User_userId` int(11) NOT NULL,
  `Class_classId` int(11) NOT NULL,
  PRIMARY KEY (`User_userId`,`Class_classId`),
  KEY `fk_User_has_Class_Class_idx` (`Class_classId`),
  KEY `fk_User_has_Class_User_idx` (`User_userId`),
  CONSTRAINT `fk_User_has_Class_User` FOREIGN KEY (`User_userId`) REFERENCES `User` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Class_Class` FOREIGN KEY (`Class_classId`) REFERENCES `Class` (`classId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_has_Class`
--

LOCK TABLES `User_has_Class` WRITE;
/*!40000 ALTER TABLE `User_has_Class` DISABLE KEYS */;
INSERT INTO `User_has_Class` (`User_userId`, `Class_classId`) VALUES (1,1),(1,2),(2,1),(11,7),(13,2),(14,1),(300,7),(297005120,2);
/*!40000 ALTER TABLE `User_has_Class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_has_Major`
--

DROP TABLE IF EXISTS `User_has_Major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_has_Major` (
  `User_userId` int(11) NOT NULL,
  `Major_majorID` int(11) NOT NULL,
  PRIMARY KEY (`User_userId`,`Major_majorID`),
  KEY `fk_User_has_Major_Major_idx` (`Major_majorID`),
  KEY `fk_User_has_Major_User_idx` (`User_userId`),
  CONSTRAINT `fk_User_has_Major_User` FOREIGN KEY (`User_userId`) REFERENCES `User` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Major_Major` FOREIGN KEY (`Major_majorID`) REFERENCES `Major` (`majorID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_has_Major`
--

LOCK TABLES `User_has_Major` WRITE;
/*!40000 ALTER TABLE `User_has_Major` DISABLE KEYS */;
INSERT INTO `User_has_Major` (`User_userId`, `Major_majorID`) VALUES (13,10),(14,1),(14,10),(300,5),(297005120,1);
/*!40000 ALTER TABLE `User_has_Major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_has_Offense`
--

DROP TABLE IF EXISTS `User_has_Offense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_has_Offense` (
  `User_userId` int(11) NOT NULL,
  `Offense_offenseId` int(11) NOT NULL,
  PRIMARY KEY (`User_userId`,`Offense_offenseId`),
  KEY `fk_User_has_Offense_Offense_idx` (`Offense_offenseId`),
  KEY `fk_User_has_Offense_User_idx` (`User_userId`),
  CONSTRAINT `fk_User_has_Offense_User` FOREIGN KEY (`User_userId`) REFERENCES `User` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Offense_Offense` FOREIGN KEY (`Offense_offenseId`) REFERENCES `Offense` (`offenseId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_has_Offense`
--

LOCK TABLES `User_has_Offense` WRITE;
/*!40000 ALTER TABLE `User_has_Offense` DISABLE KEYS */;
/*!40000 ALTER TABLE `User_has_Offense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_has_Priviledge`
--

DROP TABLE IF EXISTS `User_has_Priviledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_has_Priviledge` (
  `User_userId` int(11) NOT NULL,
  `Priviledge_priviledgeId` int(11) NOT NULL,
  PRIMARY KEY (`User_userId`,`Priviledge_priviledgeId`),
  KEY `fk_User_has_Priviledge_Priviledge_idx` (`Priviledge_priviledgeId`),
  KEY `fk_User_has_Priviledge_User_idx` (`User_userId`),
  CONSTRAINT `fk_User_has_Priviledge_User` FOREIGN KEY (`User_userId`) REFERENCES `User` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Priviledge_Priviledge` FOREIGN KEY (`Priviledge_priviledgeId`) REFERENCES `Priviledge` (`priviledgeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_has_Priviledge`
--

LOCK TABLES `User_has_Priviledge` WRITE;
/*!40000 ALTER TABLE `User_has_Priviledge` DISABLE KEYS */;
INSERT INTO `User_has_Priviledge` (`User_userId`, `Priviledge_priviledgeId`) VALUES (13,1),(14,2),(14,4),(297005120,5);
/*!40000 ALTER TABLE `User_has_Priviledge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Warranty`
--

DROP TABLE IF EXISTS `Warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Warranty` (
  `warrentyId` int(11) NOT NULL AUTO_INCREMENT,
  `warrentyName` varchar(45) DEFAULT NULL,
  `warantyCompany` varchar(45) DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `warrantyDescription` text,
  PRIMARY KEY (`warrentyId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Warranty`
--

LOCK TABLES `Warranty` WRITE;
/*!40000 ALTER TABLE `Warranty` DISABLE KEYS */;
INSERT INTO `Warranty` (`warrentyId`, `warrentyName`, `warantyCompany`, `endDate`, `warrantyDescription`) VALUES (1,'A warranty name','A warranty company','2017-05-07','A warranty description.'),(2,'A new warranty name','A warranty company','2017-05-06','A warranty description.'),(3,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(4,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(5,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(6,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(7,'A warranty name','A warranty company','2018-05-06','An updated warranty description.'),(8,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(9,'A warranty name','A warranty company','2017-05-05','A warranty description.'),(10,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(11,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(12,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(13,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(14,'A warranty name','A warranty company','2017-05-06','A warranty description.'),(15,'A warranty name','A warranty company','2018-05-07','A warranty description.'),(16,'A warranty name','A warranty company','2018-05-08','A warranty description.'),(17,'A warranty name','A warranty company','2018-05-28','A warranty description.'),(18,'A warranty name','A warranty company','2018-05-27','A warranty description.'),(19,'A warranty name','A warranty company','2018-05-29','A warranty description.'),(20,'A warranty name','A warranty company','2017-05-29','A warranty description.'),(21,'A warranty name','A warranty company','2017-05-30','A warranty description.');
/*!40000 ALTER TABLE `Warranty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-02  5:31:02
