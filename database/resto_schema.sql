-- CREATE DATABASE IF NOT EXISTS `resto`

/* !40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */
/* !80016 DEFAULT ENCRYPTION='N' */;

--  USE `resto`;

-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: resto
-- ------------------------------------------------------
-- Server version	8.4.7

/* !40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/* !40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/* !40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/* !50503 SET NAMES utf8 */;
/* !40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/* !40103 SET TIME_ZONE='+00:00' */;
/* !40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/* !40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/* !40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/* !40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/* !40101 SET @saved_cs_client     = @@character_set_client */;
/* !50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients`
(
    `id_client`         int                                    NOT NULL AUTO_INCREMENT,
    `first_name_client` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
    `last_name_client`  varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/* !40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/* !40101 SET @saved_cs_client     = @@character_set_client */;
/* !50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes`
(
    `id_dish`          int                                     NOT NULL AUTO_INCREMENT,
    `name_dish`        varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL,
    `price_dish`       decimal(15, 2)                          NOT NULL,
    `description_dish` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
    `available_dish`   tinyint(1)                              NOT NULL DEFAULT 1,
    `category_dish`    varchar(50) null,
    PRIMARY KEY (`id_dish`),
    UNIQUE KEY `name_dish` (`name_dish`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/* !40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/* !40101 SET @saved_cs_client     = @@character_set_client */;
/* !50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status`
(
    `id_status`    int                                    NOT NULL AUTO_INCREMENT,
    `label_status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id_status`),
    UNIQUE KEY `label_status` (`label_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/* !40101 SET character_set_client = @saved_cs_client */;
/* !40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/* !40101 SET @saved_cs_client     = @@character_set_client */;
/* !50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders`
(
    `id_order`            int      NOT NULL AUTO_INCREMENT,
    `daily_id_order`      int      NOT NULL,
    `date_creation_order` datetime NOT NULL,
    `id_status`           int,
    `id_client`           int      NOT NULL,
    PRIMARY KEY (`id_order`),
    KEY                   `id_status` (`id_status`),
    KEY                   `id_client` (`id_client`),
    CONSTRAINT `fk_orders_clients` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_client`),
    CONSTRAINT `fk_orders_status` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/* !40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/* !40101 SET @saved_cs_client     = @@character_set_client */;
/* !50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items`
(
    `id_order_item`       int NOT NULL AUTO_INCREMENT,
    `id_order`            int NOT NULL,
    `id_dish`             int NOT NULL,
    `quantity_order_item` int NOT NULL,
    PRIMARY KEY (`id_order_item`),
    KEY                   `id_dish` (`id_dish`),
    KEY                   `id_order` (`id_order`),
    KEY                   `id_order_item` (`id_order_item`),
    CONSTRAINT `fk_order_items_orders` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`),
    CONSTRAINT `fk_order_items_dishes` FOREIGN KEY (`id_dish`) REFERENCES `dishes` (`id_dish`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/* !40101 SET character_set_client = @saved_cs_client */;

/* !40101 SET SQL_MODE=@OLD_SQL_MODE */;
/* !40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/* !40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/* !40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/* !40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/* !40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/* !40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-01 16:50:52
