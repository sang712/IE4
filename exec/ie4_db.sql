CREATE DATABASE  IF NOT EXISTS `ie4_web_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ie4_web_db`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 13.125.117.139    Database: ie4_web_db
-- ------------------------------------------------------
-- Server version	8.0.26

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
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (64,'공지사항',3,'코로나 공지사항입니다.','<p>조심 또 조심</p>','2021-08-19 07:50:46','이효진',103),(65,'공지사항',3,'싸피 초등학교 코로나 규칙입니다.','<p>참고하세요</p>','2021-08-19 07:51:03','이효진',103),(66,'공지사항',3,'학년별 시험범위 공지합니다.','<p>화이팅하세요!</p>','2021-08-19 07:51:22','이효진',103),(67,'학습자료',3,'오늘의 학습자료','<p>ㅇ</p>','2021-08-19 07:51:40','이효진',103),(68,'학습자료',3,'1학년 3반 시험범위 공지','<p>겅부해</p>','2021-08-19 07:51:54','이효진',103),(69,'학습자료',3,'숙제첨부합니다. 확인하세요~','<p>ㅎㅎㅎ</p>','2021-08-19 07:52:07','이효진',103),(70,'학습자료',3,'이번주 랭킹발표~!~!','<p>내가 일등</p>','2021-08-19 07:52:45','이효진',103),(71,'공지사항',16,'싸피 초등학교 도서관 에티켓 필독!','<p>도서관에서 꼭 지켜야 할 에티켓!</p>','2021-08-19 08:49:23','Tom hardy',601),(75,'학습자료',16,'1반 미술 학습자료','<p>미술 수업 전에 꼭 자료 확인하고 올 것!</p>','2021-08-19 09:03:47','Tom hardy',601),(76,'학습자료',1,'1반 친구들아','<p>손씻기 확인하고 꼭꼭 지키기!! <strong>약속~</strong></p>','2021-08-19 12:05:53','강현구',101),(77,'공지사항',8,'온라인 수업 규칙 필독!','<p>친구들과 <strong>좋은 온라인 수업 환경</strong>을 만들기 위해 다같이 <strong>노력</strong>해봅시다~~!!</p>','2021-08-19 14:31:09','신상훈',301),(78,'공지사항',1,'대면수업이 생겼을 때 안전한 학교생활규칙을 지켜보아요','<p>우리 친구들!! 상황이 좋아져서 대면수업을 하게 된다면 꼭 읽고 와주세요~!!</p>','2021-08-19 14:34:40','강현구',101),(79,'학습자료',6,'오늘 수업자료입니다.','<p>참고하시고 내일 발표 화이팅하세요~</p>','2021-08-19 17:30:17','최인국',203),(80,'학습자료',6,'오늘 숙제 참고하세요~','<ol><li>배포 끝내기</li><li>글쓰기</li><li>지라 활용하기</li></ol><p>&nbsp;</p><p>완성후 MM바랍니다.</p>','2021-08-19 17:31:10','최인국',203),(81,'학습자료',7,'배포가 어려운 친구들 보렴~','<p>Nginx 관련 설정 파일입니다.</p><p><strong>친구들에게 아주 큰 도움이 될거라고 확신해요!!</strong></p>','2021-08-19 17:31:28','신상훈',301),(82,'학습자료',6,'다음주 2학년 3반 일정입니다.','<p>다음주<strong> 8월 23일 ~ 8월 29</strong></p><p>더위가 좀 꺾인 것 같아요&nbsp;</p><h3>현장학습은 코로나로 취소되었습니다.&nbsp;</h3><p>아쉽지만 비대면 수업을 통해 진도 나가겠습니다!</p>','2021-08-19 17:32:59','최인국',203),(83,'학습자료',6,'이번주 랭킹은??','<p>두구두구두구</p><p>&nbsp;</p><p>이효진~~~</p>','2021-08-19 17:33:34','최인국',203),(84,'학습자료',6,'성적표 배부','<h4>다음주 월요일 성적표 배부날입니다.&nbsp;</h4><p>긴장하고 오세요 ^^</p>','2021-08-19 17:34:02','최인국',203),(85,'학습자료',7,'지리 시간 학습자료 배포!','<p>세계지리 15p ~30p 내용이란다</p>','2021-08-19 17:35:49','신상훈',301),(86,'학습자료',6,'꽃사진 구경하세요','<p>코로나라 밖에 나가지못하는데,</p><p>이쁜 꽃 다운받아서 구경하세요</p><p>무료이미지입니다.&nbsp;</p>','2021-08-19 17:36:32','최인국',203),(87,'학습자료',7,'여름 특강 궁금한 친구들','<p>참고하면 좋을 것 같다^^</p>','2021-08-19 17:37:07','신상훈',301),(88,'공지사항',6,'내일 최종 프로젝트 발표날 입니다.','<p>모든 팀들 응원하겠습니다.</p><p>아자아자 화이팅 6반@!</p>','2021-08-19 17:37:36','최인국',203),(89,'학습자료',7,'시낭송에 관심있는 친구들','<p>참가하면 좋을 것 같다^^</p>','2021-08-19 17:39:22','신상훈',301),(90,'학습자료',7,'현장 체험학습 신청서 작성해야해','<p>.</p>','2021-08-19 17:41:44','신상훈',301),(91,'학습자료',7,'온라인 수업에서 지켜야할 규칙들이야','<p>잘 읽어보고 꼭 지켜주렴^^</p>','2021-08-19 17:43:08','신상훈',301);
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_file`
--

LOCK TABLES `board_file` WRITE;
/*!40000 ALTER TABLE `board_file` DISABLE KEYS */;
INSERT INTO `board_file` VALUES (23,71,'도서관에티켓.jpg',196165,'ie4_upload/boardfiles/31a4b209-48e6-4aab-8454-9c3a5c692cfb.jpg','2021-08-19 08:49:23'),(27,75,'현대미술.pdf',4052902,'ie4_upload/boardfiles/a0fb7275-5279-4b77-ac3f-75f173ab7aae.pdf','2021-08-19 09:03:46'),(28,76,'손씻기.jpg',231211,'ie4_upload/boardfiles/cf369257-ee1e-4ccd-90f0-3a8133f0ae4c.jpg','2021-08-19 12:05:52'),(29,77,'온라인 수업 규칙.png',200251,'ie4_upload/boardfiles/5e93fdcb-b836-45a7-bf16-4b7ec779b325.png','2021-08-19 14:31:09'),(30,78,'안전한학교생활.jpg',377787,'ie4_upload/boardfiles/752730e9-7137-414c-873c-67ed1930d2e2.jpg','2021-08-19 14:34:40'),(31,79,'폼.JPG',31225,'ie4_upload/boardfiles/34e0f41f-edd3-4fc4-98ab-039f8173c8c0.JPG','2021-08-19 17:30:16'),(32,81,'Nginx등601.pptx',466958,'ie4_upload/boardfiles/d6ce835b-f22e-4f03-9851-7974cdf6fb1e.pptx','2021-08-19 17:31:28'),(33,85,'지리자료.pdf',1491469,'ie4_upload/boardfiles/0aaf44f4-ff5e-4a53-bd7c-7a772348a303.pdf','2021-08-19 17:35:48'),(34,86,'flowers-4232255_1920.jpg',447836,'ie4_upload/boardfiles/e64a45e3-90a4-46a6-97a9-e409e264878e.jpg','2021-08-19 17:36:32'),(35,87,'여름특강자료.jpg',729622,'ie4_upload/boardfiles/f0107067-01f1-4e9a-b89c-a2dadc8a535e.jpg','2021-08-19 17:37:07'),(36,89,'시낭송대회.jpg',540372,'ie4_upload/boardfiles/4d8f6d74-f90c-4454-bc79-15c124e69194.jpg','2021-08-19 17:39:21'),(37,90,'현장체험학습신청서.jpg',432353,'ie4_upload/boardfiles/0e4bc491-f3ba-413b-a5d5-352e57bd4f1f.jpg','2021-08-19 17:41:43'),(38,91,'온라인수업예절.pdf',6773147,'ie4_upload/boardfiles/9b05a66e-90d2-4c0b-b02b-92d267f65b88.pdf','2021-08-19 17:43:07');
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
  `CONFERENCE_ACTIVE` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_class`
--

LOCK TABLES `edu_class` WRITE;
/*!40000 ALTER TABLE `edu_class` DISABLE KEYS */;
INSERT INTO `edu_class` VALUES (101,1,1,'ie4_upload/timetable/5e527687-ba06-4497-b8c4-856a74256c62.jpg','오늘도 파이팅^^!','close'),(102,1,2,NULL,'급훈을 설정하세요','close'),(103,1,3,'ie4_upload/timetable/1913aa8d-3aa6-4deb-852b-99a19bb42312.jpg','우리가 미래다.','close'),(201,2,1,'ie4_upload/timetable/298b9657-4ed6-4000-9902-2788ae2b55bc.jpg','급훈을 설정하세요','close'),(202,2,2,NULL,'급훈을 설정하세요','close'),(203,2,3,'ie4_upload/timetable/a2e37348-d396-4d23-a571-e8b026f4d5cf.jpg','오늘을 살자','close'),(301,3,1,'ie4_upload/timetable/e0cc874d-def1-4ac8-8cf1-410429b4297a.jpg','배포있는 사람이 되자!','close'),(302,3,2,NULL,'어린이는 어린이답게!!','close'),(303,3,3,NULL,'급훈을 설정하세요','close'),(401,4,1,NULL,'급훈을 설정하세요','close'),(402,4,2,NULL,'급훈을 설정하세요','close'),(403,4,3,NULL,'급훈을 설정하세요','close'),(501,5,1,'ie4_upload/timetable/b599f0bf-7d5a-4c36-abda-f1438b19c9f2.jpg','1반 아자아자','close'),(502,5,2,'ie4_upload/timetable/e44a4a6c-5642-43a5-9bcb-38e6a08a52eb.jpg','성실하게 발랄하게','close'),(503,5,3,NULL,'급훈을 설정하세요','close'),(601,6,1,'ie4_upload/timetable/2f953e8a-63d3-4815-beeb-36fd3fa69feb.jpg','We Are Venom','close'),(602,6,2,NULL,'급훈을 설정하세요','close'),(603,6,3,NULL,'급훈을 설정하세요','close');
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
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2,20,1,'01029387598','키우고 있는 애완동물의 이름은?','뽀삐'),(3,21,2,'01032874394','가장 좋아하는 색깔은?','파랑색'),(4,22,3,'01023945862','가장 좋아하는 색깔은?','검정색'),(5,23,4,'01032984575','가장 좋아하는 색깔은?','하양색'),(6,24,5,'01039482734','가장 좋아하는 색깔은?','노랑색'),(7,25,6,'01023236565','가장 좋아하는 색깔은?','노랑색'),(8,26,7,'01039475834','가장 좋아하는 색깔은?','핑크색'),(9,27,8,'01023984839','키우고 있는 애완동물의 이름은?','해피'),(10,28,1,'01038974394','가장 좋아하는 색깔은?','초록색'),(11,29,2,'01032987943','장례희망은?','연예인'),(12,30,9,'01084738957','키우고 있는 애완동물의 이름은?','두부'),(13,31,10,'01038547473','본인의 별명은?','변태'),(14,32,11,'01029384561','장례희망은?','대통령'),(15,33,12,'01094588432','가장 좋아하는 음식은?','김치찌개'),(16,34,13,'01012348832','키우고 있는 애완동물의 이름은?','나비'),(17,35,14,'01029938821','가장 좋아하는 색깔은?','남색'),(18,36,15,'01059385384','가장 좋아하는 색깔은?','주황색'),(19,37,16,'01021928376','키우고 있는 애완동물의 이름은?','탱이'),(20,38,17,'01029381818','장례희망은?','선생님'),(21,39,18,'01029384636','가장 좋아하는 색깔은?','노란색'),(22,40,19,'01092837473','장례희망은?','변호사'),(23,41,20,'01092392293','장례희망은?','의사'),(24,42,3,'01029393847','키우고 있는 애완동물의 이름은?','삐삐'),(25,43,1,'01029382838','장례희망은?','선생님'),(26,44,2,'01099998838','장례희망은?','연예인'),(27,45,3,'01029382949','장례희망은?','판사'),(28,60,1,'01019291029','가장 좋아하는 음식은?','라면'),(29,61,2,'01039485738','장례희망은?','국회의원'),(30,62,3,'01090129022','가장 좋아하는 음식은?','치킨'),(31,63,2,'01049019201','가장 좋아하는 음식은?','피자'),(32,64,1,'01090420192','가장 좋아하는 음식은?','아이스크림'),(33,65,3,'01029386732','가장 좋아하는 색깔은?','검정색'),(34,66,1,'01029129199','가장 좋아하는 음식은?','떡볶이'),(35,67,2,'01029349102','가장 좋아하는 음식은?','치킨'),(36,68,3,'01029381928','키우고 있는 애완동물의 이름은?','꽁이'),(37,69,1,'01029391928','가장 좋아하는 색깔은?','노란색'),(38,70,2,'01029287438','가장 좋아하는 음식은?','피자'),(39,71,3,'01029384738','키우고 있는 애완동물의 이름은?','밀키'),(40,72,1,'01049382812','가장 좋아하는 음식은?','곱창'),(41,73,2,'01029305739','가장 좋아하는 음식은?','치킨'),(42,74,3,'01021293092','가장 좋아하는 음식은?','떡볶이'),(43,75,1,'01029381029','장례희망은?','선생님'),(44,76,2,'01029193837','가장 좋아하는 음식은?','피자'),(45,77,3,'01029348281','장례희망은?','의사'),(46,78,1,'01029382818','가장 좋아하는 음식은?','치킨'),(47,79,2,'01029391919','장례희망은?','의사'),(48,80,3,'01029382910','장례희망은?','의사'),(49,81,1,'01096943039','키우고 있는 애완동물의 이름은?','니나'),(50,82,2,'01048939283','가장 좋아하는 음식은?','치킨'),(51,83,3,'01048392938','가장 좋아하는 색깔은?','보라색'),(52,84,1,'01048392838','가장 좋아하는 음식은?','김치찌개'),(53,85,2,'01039829493','장례희망은?','의사'),(54,86,3,'01038492819','가장 좋아하는 색깔은?','하양색'),(55,87,1,'01029382819','가장 좋아하는 음식은?','돈까스'),(56,88,2,'01039021029','가장 좋아하는 음식은?','돈까스'),(57,89,3,'01039029392','장례희망은?','변호사'),(58,90,1,'01029109394','키우고 있는 애완동물의 이름은?','오복이'),(59,91,2,'01029382910','가장 좋아하는 음식은?','된장찌개'),(60,92,3,'01029193829','가장 좋아하는 색깔은?','노란색'),(61,93,1,'01023892910','가장 좋아하는 음식은?','초밥'),(62,94,2,'01021903948','가장 좋아하는 색깔은?','핑크색'),(63,95,3,'01089543829','가장 좋아하는 음식은?','김치볶음밥'),(64,96,1,'01029381928','가장 좋아하는 음식은?','아구찜'),(65,97,2,'01029381920','가장 좋아하는 색깔은?','초록색'),(66,98,3,'01021213244','키우고 있는 애완동물의 이름은?','호두'),(67,99,1,'01090858943','장례희망은?','가수'),(68,100,2,'01039042911','가장 좋아하는 색깔은?','노랑색'),(69,101,3,'01093829901','가장 좋아하는 색깔은?','보라색'),(70,102,1,'01090489032','가장 좋아하는 음식은?','김치찌개'),(71,103,2,'01028937474','키우고 있는 애완동물의 이름은?','닝닝'),(72,104,3,'01029192930','가장 좋아하는 음식은?','초콜릿'),(73,105,45,'01011111111','본인의 별명은?','행구');
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
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'교사','601101','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','강현구','남',101,'01012345678','서울시 강남구','ie4_upload/profileImg/3518b1b5-a759-408e-8bae-1c88e3d203ac.png','체육부장'),(2,'교사','601102','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','김윤주','여',102,'01033358493','서울시 송파구',NULL,'오락부장'),(3,'교사','601103','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','이효진','여',103,'01039482948','서울시 강서구','ie4_upload/profileImg/5a9a14ee-c482-4cdd-881e-c8c4314234d0.jpg','기술부장'),(4,'교사','601201','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','임아연','여',201,'01058473849','서울시 구로구','ie4_upload/profileImg/2f333ad8-2192-48a4-bfd5-86f6a485346b.png','음악부장'),(5,'교사','601202','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','한상길','남',202,'01033849283','서울시 동작구',NULL,'학생부장'),(6,'교사','601203','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','최인국','남',203,'01094857483','서울시 노원구','ie4_upload/profileImg/7e8202a7-315b-4251-ae14-927fc9196eac.png','교육부장'),(7,'교사','601301','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','신상훈','남',301,'01029375647','서울시 영등포구','ie4_upload/profileImg/ee910116-b951-42e4-a652-39a26ab51fae.png','교육부'),(8,'교사','601302','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','이은택','남',302,'01066573839','서울시 송파구','ie4_upload/profileImg/df72183e-c5ff-4b93-bad8-18ab2a56002f.png','교육부'),(9,'교사','601303','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','황현승','남',303,'01029348382','서울시 수정구',NULL,'선도부'),(10,'교사','601401','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','현지우','여',401,'01029383783','서울시 중원구',NULL,'음악부'),(11,'교사','601402','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','박제니','여',402,'01032487649','서울시 구로구',NULL,'음악부'),(12,'교사','601403','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','임시완','남',403,'01092475674','서울시 구로구',NULL,'기술부'),(13,'교사','601501','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','차은우','남',501,'01052893746','서울시 서대문구','ie4_upload/profileImg/9edba200-802e-4a3a-959f-2dee12b2a12d.png','기술부'),(14,'교사','601502','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','송중기','남',502,'01092348576','서울시 은평구','ie4_upload/profileImg/6d9d3256-3c4d-4488-ac56-610f17c4734f.png','오락부'),(15,'교사','601503','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','마동석','남',503,'01029837483','서울시 강서구',NULL,'선도부장'),(16,'교사','601601','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','Tom hardy','남',601,'01089327569','서울시 강남구','ie4_upload/profileImg/e58a4e37-b159-4dc5-a318-7dd1221f615d.png','오락부'),(17,'교사','601602','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','Cindy','여',602,'01093485739','서울시 송파구',NULL,'체육부'),(18,'교사','601603','$2y$12$Axb2zFwY4uoTzdNEcnnGCu/1RRPH9tWwTUCMzcd6kXxoPbKzIODLS','Christina','여',603,'01038947629','서울시 서초구','ie4_upload/profileImg/19b9f82b-5c16-4d09-997f-d3835427c7d8.jpg','학생부'),(20,'학생','jihyun','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','한지현','여',101,'01032659705','서울시 구로구','ie4_upload/profileImg/0de30623-d345-48c4-bd90-1dc0b4ec47b1.png',NULL),(21,'학생','jian','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','권지안','여',101,'01023984769','서울시 노원구','ie4_upload/profileImg/67912334-c676-40ac-843a-ef0591cbac20.jpg',NULL),(22,'학생','nara','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','장나라','여',101,'01029384948','서울시 광진구','ie4_upload/profileImg/8736ab9b-e7b3-47c0-8a1b-42493474137c.png',NULL),(23,'학생','doyun','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','권도윤','남',101,'01038974598','서울시 송파구','ie4_upload/profileImg/a81c824e-79b9-4ce7-a81d-5a9be633e4f5.png',NULL),(24,'학생','gywls','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','황지니','여',101,'01023985469','서울시 노원구','ie4_upload/profileImg/15b2626a-3a60-44bc-9f5d-420850e8b31b.jpeg',NULL),(25,'학생','jjang','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','장은빈','여',101,'01098986565','서울시 구로구','ie4_upload/profileImg/4a9ab27f-b68c-4899-9215-ad30b39f3683.png',NULL),(26,'학생','hyungman','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','신형만','남',101,'01051254658','서울시 노원구',NULL,NULL),(27,'학생','gooreum','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','이구름','남',101,'01011112222','서울시 구로구',NULL,NULL),(28,'학생','minjoon','$2a$10$2mqOMPHUiI2DNnBZ0vePbuNF3cXY.c/mdXAwEhtYBF6BoOE0nA5xS','김민준','남',103,'01018293048','서울시 중구',NULL,NULL),(29,'학생','seoyeon','$2a$10$Zcq6pVtUSZ2LquWPBCUyoe0kyqH3WqsaHnEJsxWsAAKPKKlayXlMe','정서연','여',103,'01048395831','서울시 중구',NULL,NULL),(30,'학생','minjeong','$2a$10$nFgSJkXTTPH1FG.5CITDAeHxeB3R3Gs31ygrzYo/Vkaq9DiKD6aF6','염민정','여',101,'01037482667','서울시 강북구',NULL,NULL),(31,'학생','woojin','$2a$10$SrxWqeYmr4OZQ/ZLNP88B.GYROOudvQ8Dyn6NJ5raHyXwKakA.btG','변우진','남',101,'01058473976','서울시 노원구','ie4_upload/profileImg/9acfb96f-b1be-4a9c-9eee-b7335078d71b.png',NULL),(32,'학생','hyunjun','$2a$10$Uk9jB3rSIaU/4JIkmU7iQeO/gaGImxc//j6u3O86PQ1XbF/0sTlfG','함현준','남',101,'01039069584','서울시 구로구',NULL,NULL),(33,'학생','minseong','$2a$10$NVDhQJAR8fTf4gVVvwZVAu7/tDGxLLN8JSklg/d3A/aXAJ25U4NO.','추민성','남',101,'01087665744','서울시 노원구',NULL,NULL),(34,'학생','minju','$2a$10$5PXx3pPPC0VXteW8JmOWwOjFkzNXq.llyudiBYbiapuuJY6uqbpT2','금민주','여',101,'01077548832','서울시 강북구','ie4_upload/profileImg/b693eb5d-6566-47ba-a903-8f9facbefd15.png',NULL),(35,'학생','yoonseo','$2a$10$o.MZXu6H3q1xWOtrTtLzbOxgx9XBsQbkbVqi1jJ0qiXv7bt.21qbe','기윤서','여',101,'01087742881','서울시 ',NULL,NULL),(36,'학생','jiyoung','$2a$10$hGYT8H3efl9jM0SYSE9aXuSVNThd7YT.Z2dB/l3dKsUpMGB/DpSf.','여지영','여',101,'01022119445','서울시 마포구',NULL,NULL),(37,'학생','gongjun','$2a$10$sSa8MgrLx8.1uJ4DbE/wduiPkRCAQJFuUaBSGqKHL/fOV.fhkfzIa','공준영','남',101,'01073957328','서울시 마포구','ie4_upload/profileImg/839c1a33-cd42-4243-8e57-0d0309aed233.png',NULL),(38,'학생','minkyung','$2a$10$94vqMR5s.Ie.Us5JemBc.u287FPWKbxR8vyWkeRYqlmt8eBDUSVdK','백민경','여',101,'01092837462','서울시',NULL,NULL),(39,'학생','jieun','$2a$10$sbEVykw2TCwleGJXJFlcZ.zL9B23z0ejPcXJOHS1bJkbgw4hCcJWy','류지은','여',101,'01098636362','서울시 강서구',NULL,NULL),(40,'학생','nagyeong','$2a$10$0Mi5knEFKBXMDB6eLucQue.e8z6xSx8iK9kS1gd..pePL23P8hJou','안나경','여',101,'01049382939','서울시 강서구',NULL,NULL),(41,'학생','seungu','$2a$10$y5NaGG4STXYfuBkYIoj/Beim4.hjQMtjLXfQy/9KA5e4J/SzH/dhK','장승우','남',101,'01083747777','서울시 중구',NULL,NULL),(42,'학생','jiu','$2a$10$fKZPJLBSP8YtHx.pKS7sjOtl3t8bxizoYG8g/iUxIrnE.JaffpyI6','강지유','여',103,'01029394838','서울시 강남구',NULL,NULL),(43,'학생','jihwan','$2a$10$SMUlIUUoBkwecSYMlovzU.YVoR07cA49R9bV2D8gYblZIlhMFGmZa','박지환','남',102,'01087372837','서울시 마포구',NULL,NULL),(44,'학생','sungmin','$2a$10$Fjpxw/wUshagulrpZsGisut..YHvs9CQPAowu3tjiTVGaQymjLJWK','조성민','남',102,'01099228373','서울시 구로구',NULL,NULL),(45,'학생','gyuri','$2a$10$qaE6W5XwXQZCogFaYZb2QeRVN7Ju3j4N8g4dPjbFDaPBGa7hPfdwu','심규리','여',102,'01028373746','서울시 동작구',NULL,NULL),(60,'학생','seongjuni','$2a$10$AFVkULzMVy.9Wau4ky.DGu/zQYu3y5TRPkNDDLMmy.N3IGcp0JMPO','홍성준','남',201,'01029391920','서울시 노원구',NULL,NULL),(61,'학생','yeonwoo','$2a$10$xIpngfTr5GqaqVP.IU/wkeYLi7IcLcnn8/tK8.3qPQBFZKeYhtcv6','남연우','여',202,'01019291292','서울시 강북구',NULL,NULL),(62,'학생','minsu','$2a$10$zQhYFcRSH/eSXn9Te5CZN.IJLkxq0JC1IIq/IY.1mMjt1WVCwd2hW','김민수','남',201,'01010293120','서울시 용산구',NULL,NULL),(63,'학생','bokyung','$2a$10$81GDMWlUVAFFM.yiiOM1b.4qHahND7ANftU3pS5C0hFn0LOTlqjWK','곽보경','여',201,'01010231023','서울시 마포구',NULL,NULL),(64,'학생','jungmin','$2a$10$P0FTfh9XRMX62QWdnoLuUuJbQ2lMxX3cu.K5aZtK/s3VdF8BvRyjC','강정민','남',202,'01022012312','서울시 중구',NULL,NULL),(65,'학생','seongjun','$2a$10$2IUuVND1leWrZ05vjvsUYOPCjy24jKTsyav/YO7lMkaIoWos.ndgW','허성준','남',202,'01058784839','서울시 영등포구',NULL,NULL),(66,'학생','jaeyoon','$2a$10$6Wms.3UhHoNL8PLv7/uEnen1hdatGzgtMd6j.sE1Ef8qs9OvNuk8e','이재윤','남',203,'01092939123','서울시 노원구',NULL,NULL),(67,'학생','nayeon','$2a$10$VvqQu9wJi6LsmPwNAx9RFuw9x05CGuXA.MskHdDivUAJweh83DEza','권나연','여',203,'01010293029','서울시 마포구',NULL,NULL),(68,'학생','hyeonjeong','$2a$10$g/bRhq57BhpHnLm2FOAFkOlkT.vSg9Dy1UCozW2G5tLfIzZLTmHIC','장현정','여',203,'01029023920','서울시 강남구',NULL,NULL),(69,'학생','jeonghoon','$2a$10$lvNPc2dwvb2FIsxzt4AOI.2xWJM.RLNdNDhMRZBipJ7ArCbWm2Fkm','방정훈','남',301,'01098389291','서울시 영등포구',NULL,NULL),(70,'학생','siwoo','$2a$10$efn9NFcF0cCxQS0EJT/HiODM.wKWXuzjqI9jSzwztZOcIp7GCyox2','최시우','남',301,'01027848382','서울시 용산구',NULL,NULL),(71,'학생','jiwon','$2a$10$m0utnXE8h4l3tROD8ni6Nuc2g5wDt26vb6hIuOdk8fTGDe4Bnqa8q','차지원','여',301,'01048390293','서울시 서초구',NULL,NULL),(72,'학생','soyun','$2a$10$KIlTza4jeqoByvJfIfT.ZeJafNcdxDkE9sFyjKoQqyq4/2.wR7lQy','이소윤','여',302,'01029382910','서울시 서초구',NULL,NULL),(73,'학생','sujeong','$2a$10$BmhsZGDL4JhIioiD7PU/FuV4s87c/I0iN6r1bOu3xSpSAwwdXWyTy','한수정','여',302,'01089020919','서울시 서초구',NULL,NULL),(74,'학생','pyojeong','$2a$10$Syo451X0ZTVg40ZeQ.Ci6uGQPFWqVoKZdIMPw0MbdVnSmPpC87x6i','표정훈','남',302,'01094392939','서울시 성동구',NULL,NULL),(75,'학생','sulsoyun','$2a$10$/EiyckaP4QGa5lCS0t2EPu3A4T/RoIL2VYImRp2e9UJATowiP2N..','설소윤','여',303,'01029102939','서울시 서초구',NULL,NULL),(76,'학생','sumin','$2a$10$c2TNZE7WLuvmvfb9pGtHyuFCnAP4NdZoUtbpNQ3rzaiLxZ4OdVxl2','연수민','여',303,'01092839201','서울시 영등포구',NULL,NULL),(77,'학생','jiho','$2a$10$sMkmTy6DF59f6K8R2tFJCOWqDybKIltDXA8ImZh.TwS5MJLSH/G02','김지호','남',303,'01020391029','서울시 영등포구',NULL,NULL),(78,'학생','hyunsoo','$2a$10$UoVvFC4ddhgrnXlo5xrpk.BSm/r2U.FZJ5ULlyGY8muDHOgNnnin6','심현수','남',401,'01029382910','서울시 마포구',NULL,NULL),(79,'학생','dohoon','$2a$10$77MH2ditWjPX.nk3nlW7duqrhLxeTZDxg4wPjtNS1ThSxrOKTPpr6','나도훈','남',401,'01029102939','서울시 성동구',NULL,NULL),(80,'학생','nayun','$2a$10$qw9.8RF2/A9KKkFfijUv7uFAe7SAipNQCNmxI70J.vOcEUb3/vjFW','김나윤','여',401,'01020102938','서울시 강남구',NULL,NULL),(81,'학생','yuna','$2a$10$CrchAaIuizeeUUtyPsWXruV32k.Q/5shfkR.R0tpsi6miwWOazxp2','이유나','여',402,'01029309540','서울시 마포구',NULL,NULL),(82,'학생','hajun','$2a$10$sLIwwm0N4SZGes3P6rvS5On5b82k5w3.JrfUNH3Z.Q9gDcGh5QHu2','김하준','남',402,'01032920493','서울시 동작구',NULL,NULL),(83,'학생','hyeonju','$2a$10$HjN32DTndOKKvvi0TbRrxOpyOHhANwICZ1c/9J46hFbANcfMDCXyu','홍현주','여',402,'01029384839','서울시 마포구',NULL,NULL),(84,'학생','yoonjeong','$2a$10$KonavyhDHeca7tYmVnq8J.eil60HpBXEtyATDrskEnTMhUwQmlUcG','함윤정','여',403,'01043857387','서울시 동작구',NULL,NULL),(85,'학생','mingi','$2a$10$CaIK19OstcJ8UD3U7koHb./5lWhpogM76kjVP5RlszeHiD7Y/B8Gi','권민기','남',403,'01054839203','서울시 송파구',NULL,NULL),(86,'학생','eunseo','$2a$10$i7LafVgu0nJdRD34AUXOguSq4JAcXFe1kzEVUG5vZ21YYeoXTSmfO','고은서','여',403,'01045839284','서울시 송파구',NULL,NULL),(87,'학생','hayoon','$2a$10$1vzk9kOIydkRKTHqqzgw5OyI5.oZLI4jNhpcPX9XK76DBa9mT9rDS','손하윤','여',501,'01048392819','서울시 동작구',NULL,NULL),(88,'학생','sihyeon','$2a$10$G0v9NZrDHxz0uhqNMgzFF..finmLFrgoR92Q1QlTTe/MnZcaHRkyG','이시현','남',501,'01029819203','서울시 노원구',NULL,NULL),(89,'학생','jihye','$2a$10$5NX7MrrlhfT53TC9sx5Fx.gKEz2/YYD.VyNoIkNTTWZMkPYzbyQ2K','백지혜','여',501,'01039201291','서울시 강남구',NULL,NULL),(90,'학생','yeonseo','$2a$10$rBKIwIJEnKfK4UqDWebeNOJjK6AAVDXIotC7PSwsKIudEkFJ839wi','하연서','여',502,'01080549329','서울시 동대문구',NULL,NULL),(91,'학생','chaeyoon','$2a$10$27H.K35sLhixIMIFAZ5Bxen6VCR91fHco/XaKhASF.5b/KgH/QN8O','한채윤','여',502,'01034893920','서울시 강남구',NULL,NULL),(92,'학생','seungwon','$2a$10$iEf/FTTx8LBVJbMcvizPLeCHYXv9yfFFLyC1gcWUPseIwdBxEGznG','정승원','남',502,'01058930293','서울시 강남구',NULL,NULL),(93,'학생','beomjun','$2a$10$rgW8T3SBpbpKZJs6lJzB5uv8G5NpXvnm5AYoVcs.Dm4s8aZ.g0fXm','차범준','남',503,'01029301929','서울시 영등포구',NULL,NULL),(94,'학생','yewon','$2a$10$9e2NxNEvm62M/RNP7w/Luevr07paD9XytC/fcbprZkgQXdn7zLn56','노예원','여',503,'01032938928','서울시 성북구',NULL,NULL),(95,'학생','taeyeon','$2a$10$TpSQ7py.OOvAEbONINVd1edvm2m07uReo4Mb2sYYbWc2dT8S5KMTG','하태연','여',503,'01047389281','서울시 동대문구',NULL,NULL),(96,'학생','hyunseok','$2a$10$Khi8YVqwBlWaCbvil6MkjeLeH4XcRedLog0h.zld5syXtYpRyHcra','신현석','남',601,'01048392819','서울시 영등포구',NULL,NULL),(97,'학생','chaeyoung','$2a$10$jQey0qUaoCobdNvpVUYLcOp93.B2DczrbBj0a/C3Q5LNQgId6w7qW','유채영','여',601,'01029382819','서울시 영등포구',NULL,NULL),(98,'학생','seongwon','$2a$10$JAgB5oCQSKNhsILLYjl1jO8Uog28PtGxwxTOxGyPFmYfWR5/WglzW','조성원','남',601,'01028387281',' 서울시 강북구',NULL,NULL),(99,'학생','jungah','$2a$10$Sf9h8N3vuIN0d0kq.qIU5OUvdT0UC9PpOC49ShZJwdSA6IXdTkWKi','하정아','여',602,'01021934890','서울시 서초구',NULL,NULL),(100,'학생','juhwan','$2a$10$/DmheEvOTI4PoJrKhIv2ve.3LLyBrdhYgokkU390cf5VEN26N4h1C','윤주환','남',602,'01023913920','서울시 서초구',NULL,NULL),(101,'학생','bomin','$2a$10$fEco0amKqv0KCN855X/o9eJrmcGawqYS5v.MzevWaOz2Ax.dJrU3C','문보민','여',602,'01090489382','서울시 강남구',NULL,NULL),(102,'학생','yoonhee','$2a$10$0V0T7dr9dOMZz6v5K7FUg.ooGm/TbWb8u1KUWmFgaCu1rhRMgVHTm','이윤희','여',603,'01090348391','서울시 영등포구',NULL,NULL),(103,'학생','seunghoon','$2a$10$3LkkrTVtYdwVKbEnriWTpOeh3d8QcFzyyDZd6exoGbL3azh833JPG','권승훈','남',603,'01099489302','서울시 서초구',NULL,NULL),(104,'학생','sangwoo','$2a$10$yuRoLJzEVsN.weN9DQBPOumZ5GdjmvpJV3IcbVTlKHSRyyLnGxOQ.','강상우','남',603,'01099289938','서울시 구로구',NULL,NULL),(105,'학생','hyungu1','$2a$10$zZJRx6ssS1B2cKEJj2ojiuX3m71qJqFKqMhkI/LMnE9kxAxgWpzla','강행구','남',101,'01012341234','서울시 졸리다구','ie4_upload/profileImg/8c233e6b-b5b1-400d-89c0-467d5835041f.jpg',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_point`
--

LOCK TABLES `user_point` WRITE;
/*!40000 ALTER TABLE `user_point` DISABLE KEYS */;
INSERT INTO `user_point` VALUES (2,20,5,'2021-08-14 09:50:10'),(3,23,5,'2021-08-14 09:50:10'),(4,22,10,'2021-08-14 09:50:10'),(6,22,5,'2021-08-14 09:50:10'),(7,22,5,'2021-08-14 09:50:10'),(8,23,10,'2021-08-14 09:50:10'),(9,24,3,'2021-08-18 11:39:58'),(10,23,3,'2021-08-18 11:42:15'),(11,24,3,'2021-08-18 11:44:04'),(12,24,3,'2021-08-18 11:47:06'),(13,24,3,'2021-08-18 11:55:16'),(14,24,3,'2021-08-18 11:58:14'),(15,24,3,'2021-08-18 12:18:56'),(16,24,3,'2021-08-18 12:21:06'),(17,24,3,'2021-08-18 12:24:29'),(18,24,3,'2021-08-18 12:24:29'),(19,24,3,'2021-08-18 12:36:08'),(20,22,3,'2021-08-18 12:39:47'),(21,23,3,'2021-08-18 12:51:21'),(22,24,3,'2021-08-18 14:04:59'),(23,25,3,'2021-08-18 14:12:09'),(24,25,3,'2021-08-18 14:13:30'),(25,1,3,'2021-08-18 14:53:12'),(26,24,3,'2021-08-18 14:53:21'),(27,25,3,'2021-08-18 14:54:13'),(28,3,3,'2021-08-18 15:04:30'),(29,1,3,'2021-08-18 16:45:23'),(30,1,3,'2021-08-18 16:53:49'),(31,1,3,'2021-08-18 16:57:51'),(32,25,3,'2021-08-18 17:01:55'),(33,25,3,'2021-08-18 17:10:45'),(34,25,3,'2021-08-18 17:37:45'),(35,25,3,'2021-08-19 02:56:20'),(36,25,3,'2021-08-19 02:56:22'),(37,27,3,'2021-08-19 02:56:37'),(38,3,3,'2021-08-19 05:36:39'),(39,26,3,'2021-08-19 13:13:39'),(40,26,3,'2021-08-19 13:13:59'),(41,26,3,'2021-08-19 13:15:28'),(42,26,3,'2021-08-19 13:17:25'),(43,26,3,'2021-08-19 13:17:54'),(44,26,3,'2021-08-19 14:08:38'),(45,26,3,'2021-08-19 14:08:39'),(46,26,3,'2021-08-19 14:08:41'),(47,26,3,'2021-08-19 14:08:57'),(48,26,3,'2021-08-19 14:08:58'),(49,26,3,'2021-08-19 14:08:59'),(50,26,3,'2021-08-19 14:09:00'),(51,26,3,'2021-08-19 14:09:02'),(52,20,3,'2021-08-19 14:15:01'),(53,20,3,'2021-08-19 14:15:02'),(54,20,3,'2021-08-19 14:15:10'),(55,20,3,'2021-08-19 14:15:11'),(56,20,3,'2021-08-19 14:15:15'),(57,20,3,'2021-08-19 14:15:16'),(58,20,3,'2021-08-19 14:15:19'),(59,20,3,'2021-08-19 14:15:21'),(60,20,3,'2021-08-19 14:15:23'),(61,20,3,'2021-08-19 14:15:27'),(62,105,3,'2021-08-19 17:16:12'),(63,105,3,'2021-08-19 17:16:13'),(64,105,3,'2021-08-19 17:16:15'),(65,105,3,'2021-08-19 17:16:17'),(66,105,3,'2021-08-19 17:16:18'),(67,105,3,'2021-08-19 17:16:19'),(68,105,3,'2021-08-19 17:16:20'),(69,105,3,'2021-08-19 17:16:22'),(70,105,3,'2021-08-19 17:16:23'),(71,105,3,'2021-08-19 17:16:56'),(72,105,3,'2021-08-19 17:16:57'),(73,105,3,'2021-08-19 17:16:59'),(74,105,3,'2021-08-19 17:17:00'),(75,105,3,'2021-08-19 17:17:02'),(76,105,3,'2021-08-19 17:17:03'),(77,105,3,'2021-08-19 17:17:04'),(78,105,3,'2021-08-19 17:17:06'),(79,105,3,'2021-08-19 17:17:07'),(80,105,3,'2021-08-19 17:17:09'),(81,105,3,'2021-08-19 17:17:10'),(82,105,3,'2021-08-19 17:17:11'),(83,105,3,'2021-08-19 17:17:14'),(84,66,3,'2021-08-19 17:39:52'),(85,66,3,'2021-08-19 17:39:52'),(86,67,3,'2021-08-19 17:39:52'),(87,66,3,'2021-08-19 17:39:52'),(88,67,3,'2021-08-19 17:39:52'),(89,68,3,'2021-08-19 17:39:52'),(90,69,3,'2021-08-19 17:42:37'),(91,69,3,'2021-08-19 17:42:37'),(92,70,3,'2021-08-19 17:42:37'),(93,71,3,'2021-08-19 17:42:37'),(94,71,3,'2021-08-19 17:42:37'),(95,69,3,'2021-08-19 17:42:37');
/*!40000 ALTER TABLE `user_point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ie4_web_db'
--

--
-- Dumping routines for database 'ie4_web_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-20  2:47:12
