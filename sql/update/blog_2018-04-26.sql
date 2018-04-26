# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.21)
# Database: blog
# Generation Time: 2018-04-26 08:03:02 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table article_place
# ------------------------------------------------------------

DROP TABLE IF EXISTS `article_place`;

CREATE TABLE `article_place` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `place_tag` varchar(20) NOT NULL DEFAULT '' COMMENT '归档标签',
  `place_value` varchar(100) NOT NULL DEFAULT '' COMMENT '归档值',
  `place_type` tinyint(4) DEFAULT '0' COMMENT '归档分类',
  PRIMARY KEY (`id`),
  KEY `place_tag` (`place_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `article_place` WRITE;
/*!40000 ALTER TABLE `article_place` DISABLE KEYS */;

INSERT INTO `article_place` (`id`, `place_tag`, `place_value`, `place_type`)
VALUES
	(1,'2018/2','10',0),
	(2,'2018/3','6',0),
	(3,'2018/4','8',0);

/*!40000 ALTER TABLE `article_place` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
