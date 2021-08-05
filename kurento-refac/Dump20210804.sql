CREATE DATABASE  IF NOT EXISTS `ie4_web_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ie4_web_db`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: ie4_web_db
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `BOARD_TYPE` varchar(10) NOT NULL,
  `USER_ID` int unsigned NOT NULL,
  `TITLE` varchar(50) NOT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `REG_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_name` varchar(255) DEFAULT NULL,
  `CLASS_ID` int unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `bu_fk_idx` (`USER_ID`),
  KEY `bec_fk_idx` (`CLASS_ID`),
  CONSTRAINT `bec_fk` FOREIGN KEY (`CLASS_ID`) REFERENCES `edu_class` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bu_fk` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (2,'공지사항',3,'Test-Update-Title','Test-Update-Board-Content','2021-07-27 07:53:58','이효진',103),(4,'공지사항',3,'boardTest3','로또 당첨 기원~~ ','2021-07-27 08:15:54','이효진',103),(5,'학습자료',3,'boardTest4','\"살려줘...','2021-07-27 08:16:32','이효진',103),(6,'공지사항',3,'boardTest','example_content','2021-07-27 12:14:49','이효진',103),(7,'공지사항',3,'boardTest','example_content','2021-07-27 14:09:44','이효진',103),(8,'학습자료',3,'boardTest','example_content','2021-07-27 14:20:18','이효진',103),(9,'학습자료',3,'boardTest','example_content','2021-07-28 02:25:00','이효진',103),(10,'학습자료',3,'제목입니다.','살려줘','2021-07-28 14:40:27','이효진',103),(11,'공지사항',3,'제목입니다.','살려줘','2021-07-28 14:51:15','이효진',103),(12,'공지사항',3,'제목입니다.','살려줘...','2021-07-28 15:00:18','이효진',103),(13,'학습자료',3,'제목입니다','그만그..만..','2021-07-28 15:40:27','이효진',103),(14,'학습자료',3,'오늘의 학습자료','1시 실화','2021-07-28 15:52:09','이효진',103),(15,'학습자료',3,'오늘의 학습자료','1시 실화니','2021-07-28 16:31:28','이효진',103),(16,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 16:36:40','이효진',103),(17,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 16:50:14','이효진',103),(18,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 16:54:56','이효진',103),(19,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 16:58:08','이효진',103),(20,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 16:59:53','이효진',103),(22,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 17:07:38','이효진',103),(23,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 17:12:46','이효진',103),(24,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 17:17:08','이효진',103),(25,'학습자료',3,'오늘의 학습자료','마지','2021-07-28 17:19:19','이효진',103),(26,'공지사항',3,'오늘의 공지','그만 ','2021-07-28 17:23:40','이효진',103),(27,'공지사항 ',3,'오늘의 공지사항입니다^^ ','오늘은7월29일 ','2021-07-29 01:21:45','이효진',103),(28,'공지사항 ',3,'오늘의 공지사항입니다^^ ','오늘은7월29일 ','2021-07-29 01:26:19','이효진',103),(29,'공지사항 ',3,'공지사항을 보세요 ','오늘은7월29일 성공을 기원합니다~~~ ','2021-07-29 01:33:27','이효진',103),(32,'학습자료',3,'루피수정확인','루피를 수정합니다','2021-07-29 07:22:48','이효진',103);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board_file`
--

DROP TABLE IF EXISTS `board_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_file` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `BOARD_ID` int unsigned NOT NULL,
  `FILE_NAME` varchar(500) DEFAULT NULL,
  `FILE_SIZE` bigint DEFAULT NULL,
  `FILE_URL` varchar(500) DEFAULT NULL,
  `REG_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `bfb_fk_idx` (`BOARD_ID`),
  CONSTRAINT `bfb_fk` FOREIGN KEY (`BOARD_ID`) REFERENCES `board` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_file`
--

LOCK TABLES `board_file` WRITE;
/*!40000 ALTER TABLE `board_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `board_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_class`
--

DROP TABLE IF EXISTS `edu_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edu_class` (
  `ID` int unsigned NOT NULL,
  `GRADE` tinyint NOT NULL,
  `CLASS_NO` tinyint NOT NULL,
  `TIMETABLE` varchar(200) DEFAULT 'NULL',
  `CLASS_MOTTO` varchar(50) DEFAULT '급훈을 설정하세요',
  `CONFERENCE_URL` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_class`
--

LOCK TABLES `edu_class` WRITE;
/*!40000 ALTER TABLE `edu_class` DISABLE KEYS */;
INSERT INTO `edu_class` VALUES (101,1,1,'NULL','급훈을 설정하세요',NULL),(102,1,2,'NULL','급훈을 설정하세요',NULL),(103,1,3,'NULL','급훈을 설정하세요',NULL),(201,2,1,'NULL','급훈을 설정하세요',NULL),(202,2,2,'NULL','급훈을 설정하세요',NULL),(203,2,3,'NULL','급훈을 설정하세요',NULL),(301,3,1,'NULL','급훈을 설정하세요',NULL),(302,3,2,'NULL','급훈을 설정하세요',NULL),(303,3,3,'NULL','급훈을 설정하세요',NULL),(401,4,1,'NULL','급훈을 설정하세요',NULL),(402,4,2,'NULL','급훈을 설정하세요',NULL),(403,4,3,'NULL','급훈을 설정하세요',NULL),(501,5,1,'NULL','급훈을 설정하세요',NULL),(502,5,2,'NULL','급훈을 설정하세요',NULL),(503,5,3,'NULL','급훈을 설정하세요',NULL),(601,6,1,'NULL','급훈을 설정하세요',NULL),(602,6,2,'NULL','급훈을 설정하세요',NULL),(603,6,3,'NULL','급훈을 설정하세요',NULL);
/*!40000 ALTER TABLE `edu_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int unsigned NOT NULL,
  `SNUM` int NOT NULL,
  `PARENT_PHONE` varchar(15) DEFAULT NULL,
  `PASSWORD_QUESTION` varchar(200) DEFAULT NULL,
  `PASSWORD_ANSWER` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `su_fk_idx` (`USER_ID`),
  CONSTRAINT `FKk5m148xqefonqw7bgnpm0snwj` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `su_fk` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `POSITION` varchar(10) NOT NULL,
  `LOGIN_ID` varchar(20) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  `SEX` varchar(10) NOT NULL,
  `CLASS_ID` int unsigned NOT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `ADDRESS` varchar(50) DEFAULT NULL,
  `PROFILE_IMG_URL` varchar(200) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `uc_fk_idx` (`CLASS_ID`),
  CONSTRAINT `uc_fk` FOREIGN KEY (`CLASS_ID`) REFERENCES `edu_class` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'교사','601101','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','강현구','남',101,NULL,NULL,NULL,'체육부장'),(2,'교사','601102','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','김윤주','여',102,NULL,NULL,NULL,'오락부장'),(3,'교사','601103','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','이효진','여',103,NULL,NULL,NULL,'기술부장'),(4,'교사','601201','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','임아연','여',201,NULL,NULL,NULL,'음악부장'),(5,'교사','601202','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','한상길','남',202,NULL,NULL,NULL,'학생부장'),(6,'교사','601203','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','최인국','남',203,NULL,NULL,NULL,'교육부장'),(7,'교사','601301','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','신상훈','남',301,NULL,NULL,NULL,'교육부'),(8,'교사','601302','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','이은택','남',302,NULL,NULL,NULL,'교육부'),(9,'교사','601303','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','황현승','남',303,NULL,NULL,NULL,'선도부'),(10,'교사','601401','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','현지우','여',401,NULL,NULL,NULL,'음악부'),(11,'교사','601402','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','박제니','여',402,NULL,NULL,NULL,'음악부'),(12,'교사','601403','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','임시완','남',403,NULL,NULL,NULL,'기술부'),(13,'교사','601501','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','차은우','남',501,NULL,NULL,NULL,'기술부'),(14,'교사','601502','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','송중기','남',502,NULL,NULL,NULL,'오락부'),(15,'교사','601503','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','마동석','남',503,NULL,NULL,NULL,'선도부장'),(16,'교사','601601','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','Tom hardy','남',601,NULL,NULL,NULL,'오락부'),(17,'교사','601602','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','Cindy','여',602,NULL,NULL,NULL,'체육부'),(18,'교사','601603','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','Christina','여',603,NULL,NULL,NULL,'학생부');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_point`
--

DROP TABLE IF EXISTS `user_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_point` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int unsigned NOT NULL,
  `POINT` int NOT NULL DEFAULT '0',
  `CREATE_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `upu_fk_idx` (`USER_ID`),
  CONSTRAINT `FK5evnproqicxekw9a5r0muk6rl` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `upu_fk` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_point`
--

LOCK TABLES `user_point` WRITE;
/*!40000 ALTER TABLE `user_point` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_point` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-04 10:26:49
