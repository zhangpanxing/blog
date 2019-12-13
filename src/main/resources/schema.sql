CREATE DATABASE IF NOT EXISTS `fsblog`;
USE `fsblog`;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(12) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PrimaryKey',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(40) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `github` varchar(40) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `wecaht` varchar(20) DEFAULT NULL,
  `hobby` text,
  `motto` text,
  `md_info` text,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像url',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(12) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PrimaryKey',
  `title` varchar(50) DEFAULT NULL,
  `introduction` text,
  `html_material` text COMMENT '文章 html 内容',
  `md_material` text COMMENT '文章内容',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tag_article
-- ----------------------------
DROP TABLE IF EXISTS `tag_article`;
CREATE TABLE `tag_article` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `tag_id` int(12) NOT NULL COMMENT 'Tag_ID',
  `article_id` int(12) NOT NULL COMMENT 'Article_ID',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`tag_id`,`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(12) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PrimaryKey',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `github` varchar(40) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `wecaht` varchar(20) DEFAULT NULL,
  `hobby` text,
  `motto` text,
  `md_info` text,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像url',
  `status` int(3) NOT NULL DEFAULT '0' COMMENT '状态/0:正常/1:封禁',
  `level` int(2) NOT NULL DEFAULT '0' COMMENT '等级',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
  `id` int(12) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PrimaryKey',
  `title` varchar(50) DEFAULT NULL,
  `introduction` text,
  `html_material` text COMMENT '文章 html 内容',
  `md_material` text COMMENT '文章内容',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;