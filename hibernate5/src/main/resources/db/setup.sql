-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.12 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for telephone_dir
CREATE DATABASE IF NOT EXISTS `telephone_dir` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `telephone_dir`;


-- Dumping structure for table telephone_dir.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table telephone_dir.phone_details
CREATE TABLE IF NOT EXISTS `phone_details` (
  `id` bigint(20) NOT NULL,
  `phone_num` varchar(50) NOT NULL,
  `num_type` varchar(100) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  KEY `FKaotbhp61qy7o9yywupj85ye8m` (`person_id`),
  CONSTRAINT `FKaotbhp61qy7o9yywupj85ye8m` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `person_id_FK` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table telephone_dir.sequence_table
CREATE TABLE IF NOT EXISTS `sequence_table` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
