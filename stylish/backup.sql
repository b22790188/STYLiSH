-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: stylish
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.22.04.1

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

--
-- Table structure for table `campaign`
--

DROP TABLE IF EXISTS `campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaign` (
  `product_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `picture` varchar(255) NOT NULL,
  `story` text NOT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `campaign_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign`
--

LOCK TABLES `campaign` WRITE;
/*!40000 ALTER TABLE `campaign` DISABLE KEYS */;
INSERT INTO `campaign` VALUES (15,'http://52.69.33.14/uploads/52c9be46-eb4e-4d9d-963a-93e513d7a3de-FRTH6-dVEAAxdmq.jpg','Campaign text here'),(18,'http://52.69.33.14/uploads/796b868d-807d-48c0-81f3-2a33a8276cb9-Exploring_unusual_problems.png','efs'),(20,'http://52.69.33.14/uploads/198c1c4e-6cb5-4c45-b992-49a2872bd9fc-Exploring_unusual_problems.png','asdf');
/*!40000 ALTER TABLE `campaign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (1,'藍色','#33A8FF'),(2,'綠色','#33FFA5'),(3,'紅色','#FF5733');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint unsigned NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `image_product_id_foreign` (`product_id`),
  CONSTRAINT `image_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,1,'http://52.69.33.14/uploads/c2c05c1d-72ac-4ee7-af2c-62dd0c0942da-Exploring_unusual_problems.png'),(2,2,'http://52.69.33.14/uploads/fbdd89f8-97b6-45c6-af77-4fa44c6a8382-Exploring_unusual_problems.png'),(3,3,'http://52.69.33.14/uploads/ad341106-49d2-4eaa-8fe9-070fb833c897-Exploring_unusual_problems.png'),(4,4,'http://52.69.33.14/uploads/74a1d1ad-ab5e-470d-9c71-0147203c82c6-Exploring_unusual_problems.png'),(5,5,'http://52.69.33.14/uploads/48b485b1-4c80-44ec-add7-1fed93cbca97-Exploring_unusual_problems.png'),(6,6,'http://52.69.33.14/uploads/8f73021d-f8f7-433d-bc99-4e74f138228f-Exploring_unusual_problems.png'),(7,7,'http://52.69.33.14/uploads/ae3b0272-2a89-4921-86fb-ce0d05bc61a2-Exploring_unusual_problems.png'),(8,8,'http://52.69.33.14/uploads/d718f78c-c32a-48a6-8051-dc1cee7b7b9b-Exploring_unusual_problems.png'),(9,9,'http://52.69.33.14/uploads/c153f519-b9ea-4700-ab44-ebd539f279dc-Exploring_unusual_problems.png'),(10,10,'http://52.69.33.14/uploads/69c374c5-2154-4690-adda-abd9fed42634-Exploring_unusual_problems.png'),(11,11,'http://52.69.33.14/uploads/1d3dd6eb-af51-4c06-8195-f5f7eeb0b0a3-Exploring_unusual_problems.png'),(12,12,'http://52.69.33.14/uploads/2983304b-f530-45ea-8628-2a45bc4d4739-Exploring_unusual_problems.png'),(13,13,'http://52.69.33.14/uploads/699d72c0-c182-43b2-ade6-d6b554ec6794-Exploring_unusual_problems.png'),(14,14,'http://52.69.33.14/uploads/ca374107-bccd-4913-aff9-ce010a5ce4a7-Exploring_unusual_problems.png'),(15,15,'http://52.69.33.14/uploads/fbdfdf72-d7d2-4ebd-8e98-5932d24c7c30-Exploring_unusual_problems.png'),(16,16,'http://52.69.33.14/uploads/2c85413c-683c-4528-a062-46827c0a7a9b-Exploring_unusual_problems.png'),(17,17,'http://52.69.33.14/uploads/c69bf69c-0dfc-41f1-8e31-9951cd8668c0-Exploring_unusual_problems.png'),(18,18,'http://52.69.33.14/uploads/fdcb2d34-7eaf-489c-8637-b51f73ac35d7-Exploring_unusual_problems.png'),(19,19,'http://52.69.33.14/uploads/c76f2f21-4acc-44cb-a18b-24910d3e8d27-Exploring_unusual_problems.png'),(20,20,'http://52.69.33.14/uploads/67a2dc65-b0d4-496a-b82f-895bc352018b-Exploring_unusual_problems.png'),(21,21,'http://52.69.33.14/uploads/bbd00957-45b8-4903-b44e-ca7b231dfa68-Exploring_unusual_problems.png'),(22,22,'http://52.69.33.14/uploads/275c0955-5113-408d-8680-a113b12ba228-Exploring_unusual_problems.png'),(23,23,'http://52.69.33.14/uploads/5b1eb85b-44cb-402a-b7fa-492c82a93335-Exploring_unusual_problems.png'),(24,24,'http://52.69.33.14/uploads/bc579737-bfcc-4a65-b8e0-4ee3b4141f04-Exploring_unusual_problems.png'),(25,25,'http://52.69.33.14/uploads/290d3dde-8a3c-4043-bf76-36fae2e7b8cf-Exploring_unusual_problems.png'),(26,26,'http://52.69.33.14/uploads/be7706e1-724b-4c39-98af-9787ae86e649-Exploring_unusual_problems.png'),(27,27,'http://52.69.33.14/uploads/b48d213c-3fc2-415c-afd2-6a989296bda7-Exploring_unusual_problems.png'),(28,28,'http://52.69.33.14/uploads/722b349e-d62b-425e-948c-08e94266f32a-Exploring_unusual_problems.png'),(29,29,'http://52.69.33.14/uploads/999c57bd-1aca-4830-b85c-b97f2ca7c6f7-Exploring_unusual_problems.png'),(30,30,'http://52.69.33.14/uploads/9ae74e9f-d617-435e-8e79-5ea1fa161fb8-Exploring_unusual_problems.png'),(31,31,'http://52.69.33.14/uploads/cda32049-e32d-48e1-a7c5-bc493b2a0509-Exploring_unusual_problems.png'),(32,32,'http://52.69.33.14/uploads/b35ef055-c476-4bc8-b9d1-3b9c32fefa68-Exploring_unusual_problems.png'),(33,33,'http://52.69.33.14/uploads/af50286f-55bb-4b8c-a6a6-6993d2b904e5-201807242222_0.jpg'),(34,33,'http://52.69.33.14/uploads/b02a1004-795d-47f8-8987-b58e944abbdc-201807242222_1.jpg'),(35,33,'http://52.69.33.14/uploads/6dca83e6-1714-4f53-8664-50a636987cfa-201807242222_main.jpg'),(36,34,'http://52.69.33.14/uploads/11b73ee4-f74c-4734-8681-9aa20a3664b9-11851311.jpeg'),(37,34,'http://52.69.33.14/uploads/b3dff020-bf5c-4db7-a288-9b4cd1a1513d-201807201824_0.jpg'),(38,34,'http://52.69.33.14/uploads/94e069e3-0722-44f3-8761-f8f99777ee3a-201807201824_1.jpg'),(39,34,'http://52.69.33.14/uploads/76207222-6cfb-445f-904d-dd695d9bea3e-201807202140_0.jpg'),(40,34,'http://52.69.33.14/uploads/b66895d0-9289-4756-a37e-b0ffee857d14-201807202140_1.jpg'),(41,34,'http://52.69.33.14/uploads/e127f498-4f57-4e9b-b5c8-ce3ac37097f5-201807202150_0.jpg'),(42,34,'http://52.69.33.14/uploads/61ed4f96-e7ce-4ad8-9cbf-d91a7e7909f0-201807202150_1.jpg'),(43,14,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(44,5,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(45,13,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(46,17,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(47,16,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(48,27,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(49,22,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(50,28,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(51,10,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(52,30,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(53,25,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(54,1,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(55,26,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(56,28,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(62,1,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(63,15,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(64,8,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(65,26,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(66,9,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(67,1,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(68,6,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(69,30,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(70,2,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(71,14,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(77,3,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(78,1,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(79,29,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(80,14,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(81,15,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(82,1,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(83,23,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(84,17,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(85,14,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(86,18,'http://52.69.33.14/uploads/846671dc-1f65-4cf8-aafe-07f841de684f-bear.jpeg'),(92,35,'http://52.69.33.14/uploads/8ef937b9-07c9-4f98-9515-25b2c9c7bf22-201807242222_main.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `shipping` varchar(255) NOT NULL,
  `payment` varchar(255) NOT NULL,
  `subtotal` int NOT NULL,
  `freight` int NOT NULL,
  `total` int NOT NULL,
  `isPaid` tinyint unsigned NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` bigint unsigned NOT NULL,
  `recipient_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_user_id_foreign` (`user_id`),
  KEY `order_recipient_id_foreign` (`recipient_id`),
  CONSTRAINT `order_recipient_id_foreign` FOREIGN KEY (`recipient_id`) REFERENCES `recipient` (`id`),
  CONSTRAINT `order_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'delivery','credit_card',1234,14,1300,1,'2024-08-01 07:25:07','2024-08-01 07:25:08',1,1),(2,'delivery','credit_card',1234,14,1300,1,'2024-08-02 00:36:44','2024-08-02 00:36:44',1,2),(3,'delivery','credit_card',1234,14,1300,1,'2024-08-02 02:21:17','2024-08-02 02:21:17',1,3),(4,'delivery','credit_card',100,30,130,1,'2024-08-04 07:36:34','2024-08-04 07:36:35',1,4),(5,'delivery','credit_card',100,30,130,1,'2024-08-04 08:00:52','2024-08-04 08:00:52',1,5),(6,'delivery','credit_card',100,30,130,2,'2024-08-04 08:18:21','2024-08-04 08:18:21',1,6),(7,'delivery','credit_card',100,30,130,0,'2024-08-04 08:19:57','2024-08-04 08:19:57',1,7),(8,'delivery','credit_card',100,30,130,1,'2024-08-04 08:20:43','2024-08-04 08:20:43',1,8),(9,'delivery','credit_card',100,30,130,1,'2024-08-05 03:05:35','2024-08-05 03:05:36',1,9);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_variant`
--

DROP TABLE IF EXISTS `order_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_variant` (
  `order_id` bigint unsigned NOT NULL,
  `variant_id` bigint unsigned NOT NULL,
  `qty` bigint NOT NULL,
  PRIMARY KEY (`order_id`,`variant_id`),
  KEY `order_variant_variant_id_foreign` (`variant_id`),
  CONSTRAINT `order_variant_order_id_foreign` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `order_variant_variant_id_foreign` FOREIGN KEY (`variant_id`) REFERENCES `variant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_variant`
--

LOCK TABLES `order_variant` WRITE;
/*!40000 ALTER TABLE `order_variant` DISABLE KEYS */;
INSERT INTO `order_variant` VALUES (1,1,1),(2,1,1),(3,1,1),(4,169,1),(5,169,1),(6,78,1),(8,78,1),(9,78,1);
/*!40000 ALTER TABLE `order_variant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` int NOT NULL,
  `texture` varchar(255) NOT NULL,
  `wash` varchar(255) NOT NULL,
  `place` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `story` text NOT NULL,
  `main_image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'men','男裝1','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/af50286f-55bb-4b8c-a6a6-6993d2b904e5-201807242222_0.jpg'),(2,'men','男裝2','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/af50286f-55bb-4b8c-a6a6-6993d2b904e5-201807242222_0.jpg'),(3,'men','男裝3','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/4ac264c1-9989-4f47-918f-edc726729b02-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(4,'men','男裝4','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/e808a48a-5b17-4e12-a3bc-85c9118830d2-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(5,'women','女裝1','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/61ed4f96-e7ce-4ad8-9cbf-d91a7e7909f0-201807202150_1.jpg'),(6,'women','女裝2','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/61ed4f96-e7ce-4ad8-9cbf-d91a7e7909f0-201807202150_1.jpg'),(7,'women','女裝3','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/61ed4f96-e7ce-4ad8-9cbf-d91a7e7909f0-201807202150_1.jpg'),(8,'women','女裝4','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/95e8e21f-9812-48ae-a908-38190d0778b7-5a54ebc4.jpeg'),(9,'women','女裝5','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/95e8e21f-9812-48ae-a908-38190d0778b7-5a54ebc4.jpeg'),(10,'women','女裝6','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/95e8e21f-9812-48ae-a908-38190d0778b7-5a54ebc4.jpeg'),(11,'women','女裝7','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/95e8e21f-9812-48ae-a908-38190d0778b7-5a54ebc4.jpeg'),(12,'women','女裝8','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/95e8e21f-9812-48ae-a908-38190d0778b7-5a54ebc4.jpeg'),(13,'women','女裝9','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/4df6ec2e-a43a-4211-b86f-928803f3aab8-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(14,'women','女裝10','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/f22ee340-5acc-4604-be0f-4252cb904314-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(15,'women','女裝11','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/76dcb2a7-6300-4b02-a3b2-e0b037474e38-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(16,'women','女裝12','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/2401dbfa-21ac-409c-a9cf-fb9aaaa49e19-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(17,'women','女裝13','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/77e5492a-7a2c-4f1b-897c-c6b819d57680-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(18,'accessories','配件1','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/d6d50daf-194b-4f05-bb26-2f36c3d55720-201807242230_main.jpg'),(19,'accessories','配件2','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/d6d50daf-194b-4f05-bb26-2f36c3d55720-201807242230_main.jpg'),(20,'accessories','配件3','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/d6d50daf-194b-4f05-bb26-2f36c3d55720-201807242230_main.jpg'),(21,'accessories','配件4','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/eb29c002-c660-423e-bdbd-bc825b73753a-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(22,'accessories','配件5','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/7c80aa26-4e12-4d14-93ae-e7ae888f44b8-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(23,'accessories','配件6','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/8029dba8-aa56-47cf-aef5-42d3fead9685-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(24,'accessories','配件7','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/d3ab6645-b2b1-4d1b-94f9-402dd3a6b06d-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(25,'accessories','配件8','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/b6ca9ade-a6ea-4892-acb2-23cf4d6a4180-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(26,'accessories','配件9','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/2a305e05-3222-4e5d-b58e-256035a3abc8-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(27,'accessories','配件10','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/fa2d0b99-34e5-4e52-ae9c-d0a9ee92487e-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(28,'accessories','配件11','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/924c9ab6-56a1-48e7-ae89-be40bed8e890-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(29,'accessories','配件12','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/44e9140c-427c-4375-a072-cbfd1e449cb7-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(30,'accessories','配件13','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/7ab0b990-b6c2-408e-b57e-00bf39a995eb-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(31,'accessories','配件14','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/e736a8e9-c3d2-42fb-a82b-61ec8a30c651-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(32,'accessories','配件15','test3',100,'棉 100%','test6','素材產地/韓國','實品顏色以單品照為主','test9','http://52.69.33.14/uploads/0956b097-9098-4cc6-a00e-4924ad12f6d6-1_1L-wyk1Kk2Dd-79gb0AvPw.jpg'),(33,'men','男裝5','好穿的衣服',20000,'棉 100%','手洗','素材產地/韓國','實品顏色以單品照為主','耶','http://52.69.33.14/uploads/67b07d0a-d00e-4c72-aada-ba5fa1b0cac1-201807242216_main.jpg'),(34,'women','女裝','好穿的衣服',20000,'棉 100%','手洗','素材產地/韓國','實品顏色以單品照為主','耶','http://52.69.33.14/uploads/95e8e21f-9812-48ae-a908-38190d0778b7-5a54ebc4.jpeg'),(35,'men','男裝6','好穿的衣服',1000,'棉 100%','手洗','素材產地/韓國','實品顏色以單品照為主','沒有故事','http://52.69.33.14/uploads/d6d50daf-194b-4f05-bb26-2f36c3d55720-201807242230_main.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_color`
--

DROP TABLE IF EXISTS `product_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_color` (
  `product_id` bigint unsigned NOT NULL,
  `color_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`product_id`,`color_id`),
  KEY `product_color_color_id_foreign` (`color_id`),
  CONSTRAINT `product_color_color_id_foreign` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  CONSTRAINT `product_color_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_color`
--

LOCK TABLES `product_color` WRITE;
/*!40000 ALTER TABLE `product_color` DISABLE KEYS */;
INSERT INTO `product_color` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(2,3),(3,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(13,3),(14,3),(15,3),(16,3),(17,3),(18,3),(19,3),(20,3),(21,3),(22,3),(23,3),(24,3),(25,3),(26,3),(27,3),(28,3),(29,3),(30,3),(31,3),(32,3),(35,3);
/*!40000 ALTER TABLE `product_color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_size`
--

DROP TABLE IF EXISTS `product_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_size` (
  `product_id` bigint unsigned NOT NULL,
  `size_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`product_id`,`size_id`),
  KEY `product_size_size_id_foreign` (`size_id`),
  CONSTRAINT `product_size_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `product_size_size_id_foreign` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_size`
--

LOCK TABLES `product_size` WRITE;
/*!40000 ALTER TABLE `product_size` DISABLE KEYS */;
INSERT INTO `product_size` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(33,2),(34,2),(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(13,3),(14,3),(15,3),(16,3),(17,3),(18,3),(19,3),(20,3),(21,3),(22,3),(23,3),(24,3),(25,3),(26,3),(27,3),(28,3),(29,3),(30,3),(31,3),(32,3);
/*!40000 ALTER TABLE `product_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipient`
--

DROP TABLE IF EXISTS `recipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipient` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `time` enum('morning','afternoon','anytime') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipient`
--

LOCK TABLES `recipient` WRITE;
/*!40000 ALTER TABLE `recipient` DISABLE KEYS */;
INSERT INTO `recipient` VALUES (1,'Luke','0987654321','luke@gmail.com','市政府站','morning'),(2,'Luke','0987654321','luke@gmail.com','市政府站','morning'),(3,'Luke','0987654321','luke@gmail.com','市政府站','morning'),(4,'Luke','0987654321','luke@gmail.com','市政府站','morning'),(5,'Luke','0987654321','luke@gmail.com','市政府站','morning'),(6,'Luke','0987654321','luke@gmail.com','市政府站','morning'),(7,'Luke','0987654321','luke@gmail.com','市政府站','morning'),(8,'Luke','0987654321','luke@gmail.com','市政府站','morning'),(9,'Luke','0987654321','luke@gmail.com','市政府站','morning');
/*!40000 ALTER TABLE `recipient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `size` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,'S'),(2,'L'),(3,'M');
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `provider` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `picture` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'native','test','test@test.com','$2a$10$uTzSaWrTpwHIr1FLx1gBuORQAdDY3o3BObdfKwpWFD9i4OOvtEYqm','image'),(2,'native','test','test1@test.com','$2a$10$p1DwSF9wGH7lwrBw.pb07u08DcOUwmfKBHwCSHcxLtO47au9ppKMu','image'),(3,'native','test','test2@test.com','$2a$10$3PeUBGGk//NpEyufpT2vNeOFj39hW76TqH5LABYluLpWBMiJ6bV.W','image'),(4,'native','test5','test5@test.com','$2a$10$/c../4eHmLerri/uggn7Fe4cu93M0jPElvuwfBcuLAVvRgfypx2NS','image'),(5,'native','黑色','shane@gmail','$2a$10$CE.z6bd/xjBENWPhgBhdS.LNIqVKHnoxSLAxvhoacGFyPeMWIkddu','image');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variant`
--

DROP TABLE IF EXISTS `variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `variant` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint unsigned NOT NULL,
  `color_id` bigint unsigned NOT NULL,
  `size_id` bigint unsigned NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `variant_product_id_unique` (`product_id`,`color_id`,`size_id`),
  KEY `variant_size_id_foreign` (`size_id`),
  KEY `variant_color_id_foreign` (`color_id`),
  CONSTRAINT `variant_color_id_foreign` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  CONSTRAINT `variant_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `variant_size_id_foreign` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variant`
--

LOCK TABLES `variant` WRITE;
/*!40000 ALTER TABLE `variant` DISABLE KEYS */;
INSERT INTO `variant` VALUES (1,1,1,1,5),(2,1,1,2,8),(3,2,1,1,0),(4,2,1,2,0),(5,3,1,1,5),(6,3,1,2,8),(7,4,1,1,5),(8,4,1,2,0),(9,5,1,1,5),(10,5,1,2,8),(11,6,1,1,5),(12,6,1,2,8),(13,7,1,1,5),(14,7,1,2,8),(15,8,1,1,5),(16,8,1,2,8),(17,9,1,1,5),(18,9,1,2,8),(19,10,1,1,5),(20,10,1,2,0),(21,11,1,1,5),(22,11,1,2,8),(23,12,1,1,5),(24,12,1,2,8),(25,13,1,1,5),(26,13,1,2,8),(27,14,1,1,5),(28,14,1,2,0),(29,15,1,1,0),(30,15,1,2,0),(31,16,1,1,5),(32,16,1,2,0),(33,17,1,1,5),(34,17,1,2,8),(35,18,1,1,5),(36,18,1,2,8),(37,19,1,1,5),(38,19,1,2,8),(39,20,1,1,5),(40,20,1,2,8),(41,21,1,1,5),(42,21,1,2,8),(43,22,1,1,5),(44,22,1,2,8),(45,23,1,1,5),(46,23,1,2,8),(47,24,1,1,5),(48,24,1,2,8),(49,25,1,1,5),(50,25,1,2,8),(51,26,1,1,0),(52,26,1,2,8),(53,27,1,1,5),(54,27,1,2,8),(55,28,1,1,5),(56,28,1,2,8),(57,29,1,1,5),(58,29,1,2,0),(59,30,1,1,0),(60,30,1,2,0),(61,31,1,1,0),(62,31,1,2,8),(63,32,1,1,5),(64,32,1,2,8),(68,1,2,1,2),(69,1,2,2,1),(70,1,2,3,6),(71,1,1,3,6),(72,2,3,1,0),(73,2,3,2,7),(74,2,3,3,0),(75,2,2,1,1),(76,2,2,2,0),(77,2,2,3,8),(78,2,1,3,10),(79,3,3,1,0),(80,3,3,2,9),(81,3,3,3,10),(82,3,2,1,5),(83,3,2,2,1),(84,3,2,3,3),(85,3,1,3,5),(89,4,2,1,1),(90,4,2,2,9),(91,4,2,3,4),(92,4,1,3,9),(96,5,2,1,8),(97,5,2,2,0),(98,5,2,3,5),(99,5,1,3,4),(100,6,3,1,0),(101,6,3,2,8),(102,6,3,3,1),(103,6,2,1,9),(104,6,2,2,0),(105,6,2,3,3),(106,6,1,3,2),(107,7,3,1,2),(108,7,3,2,2),(109,7,3,3,6),(110,7,2,1,2),(111,7,2,2,1),(112,7,2,3,10),(113,7,1,3,5),(114,8,3,1,4),(115,8,3,2,4),(116,8,3,3,9),(117,8,2,1,4),(118,8,2,2,3),(119,8,2,3,10),(120,8,1,3,5),(121,9,3,1,9),(122,9,3,2,9),(123,9,3,3,9),(124,9,2,1,0),(125,9,2,2,1),(126,9,2,3,0),(127,9,1,3,8),(128,10,3,1,7),(129,10,3,2,3),(130,10,3,3,2),(131,10,2,1,0),(132,10,2,2,3),(133,10,2,3,0),(134,10,1,3,5),(135,11,3,1,0),(136,11,3,2,6),(137,11,3,3,10),(138,11,2,1,1),(139,11,2,2,3),(140,11,2,3,5),(141,11,1,3,0),(142,12,3,1,3),(143,12,3,2,9),(144,12,3,3,8),(145,12,2,1,0),(146,12,2,2,1),(147,12,2,3,3),(148,12,1,3,4),(149,13,3,1,1),(150,13,3,2,5),(151,13,3,3,10),(152,13,2,1,6),(153,13,2,2,10),(154,13,2,3,0),(155,13,1,3,0),(156,14,3,1,0),(157,14,3,2,6),(158,14,3,3,1),(159,14,2,1,6),(160,14,2,2,6),(161,14,2,3,3),(162,14,1,3,9),(163,15,3,1,0),(164,15,3,2,0),(165,15,3,3,0),(166,15,2,1,0),(167,15,2,2,5),(168,15,2,3,1),(169,15,1,3,10),(170,16,3,1,3),(171,16,3,2,5),(172,16,3,3,0),(173,16,2,1,4),(174,16,2,2,10),(175,16,2,3,10),(176,16,1,3,0),(177,17,3,1,7),(178,17,3,2,1),(179,17,3,3,2),(180,17,2,1,0),(181,17,2,2,0),(182,17,2,3,9),(183,17,1,3,9),(184,18,3,1,5),(185,18,3,2,9),(186,18,3,3,7),(187,18,2,1,8),(188,18,2,2,8),(189,18,2,3,0),(190,18,1,3,10),(191,19,3,1,3),(192,19,3,2,4),(193,19,3,3,1),(194,19,2,1,2),(195,19,2,2,7),(196,19,2,3,0),(197,19,1,3,4),(198,20,3,1,1),(199,20,3,2,2),(200,20,3,3,6),(201,20,2,1,4),(202,20,2,2,3),(203,20,2,3,1),(204,20,1,3,2),(205,21,3,1,9),(206,21,3,2,8),(207,21,3,3,10),(208,21,2,1,8),(209,21,2,2,7),(210,21,2,3,3),(211,21,1,3,10),(212,22,3,1,3),(213,22,3,2,6),(214,22,3,3,10),(215,22,2,1,2),(216,22,2,2,10),(217,22,2,3,2),(218,22,1,3,0),(219,23,3,1,10),(220,23,3,2,0),(221,23,3,3,8),(222,23,2,1,5),(223,23,2,2,2),(224,23,2,3,2),(225,23,1,3,2),(226,24,3,1,10),(227,24,3,2,0),(228,24,3,3,5),(229,24,2,1,10),(230,24,2,2,0),(231,24,2,3,0),(232,24,1,3,1),(233,25,3,1,3),(234,25,3,2,9),(235,25,3,3,0),(236,25,2,1,1),(237,25,2,2,1),(238,25,2,3,10),(239,25,1,3,4),(240,26,3,1,0),(241,26,3,2,9),(242,26,3,3,10),(243,26,2,1,2),(244,26,2,2,10),(245,26,2,3,4),(246,26,1,3,4),(247,27,3,1,8),(248,27,3,2,9),(249,27,3,3,10),(250,27,2,1,2),(251,27,2,2,10),(252,27,2,3,2),(253,27,1,3,9),(254,28,3,1,1),(255,28,3,2,10),(256,28,3,3,4),(257,28,2,1,9),(258,28,2,2,3),(259,28,2,3,7),(260,28,1,3,3),(261,29,3,1,6),(262,29,3,2,3),(263,29,3,3,5),(264,29,2,1,4),(265,29,2,2,0),(266,29,2,3,6),(267,29,1,3,0),(268,30,3,1,1),(269,30,3,2,5),(270,30,3,3,2),(271,30,2,1,4),(272,30,2,2,3),(273,30,2,3,3),(274,30,1,3,5),(275,31,3,1,10),(276,31,3,2,6),(277,31,3,3,1),(278,31,2,1,6),(279,31,2,2,7),(280,31,2,3,4),(281,31,1,3,6),(282,32,3,1,0),(283,32,3,2,3),(284,32,3,3,10),(285,32,2,1,0),(286,32,2,2,3),(287,32,2,3,8),(288,32,1,3,1),(289,33,1,1,0),(290,33,1,2,0),(291,34,1,1,5),(292,34,1,2,2),(293,35,3,1,8);
/*!40000 ALTER TABLE `variant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-05 16:13:09
