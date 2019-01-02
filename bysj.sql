/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bysj

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-01-02 16:45:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `good_id` int(11) NOT NULL COMMENT '物品id',
  `users_id` int(11) NOT NULL COMMENT '用户id',
  `count` int(11) DEFAULT '1' COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goodorder
-- ----------------------------
DROP TABLE IF EXISTS `goodorder`;
CREATE TABLE `goodorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `users_id` int(11) DEFAULT NULL COMMENT '用户id',
  `good_id` int(11) DEFAULT NULL COMMENT '商品id',
  `count` int(11) DEFAULT NULL COMMENT '订单数量',
  `addr` varchar(255) DEFAULT NULL COMMENT '订单地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单时间',
  `is_send` tinyint(1) DEFAULT '0' COMMENT '确认',
  `is_recive` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `seller_id` int(11) NOT NULL DEFAULT '0' COMMENT '卖家id',
  `buy_times` int(11) NOT NULL DEFAULT '0' COMMENT '被购买的次数',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `price` int(11) NOT NULL COMMENT '商品价格',
  `sell_point` varchar(255) NOT NULL DEFAULT '好东西',
  `img_url` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `seller` (`seller_id`)
) ENGINE=MyISAM AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods_copy
-- ----------------------------
DROP TABLE IF EXISTS `goods_copy`;
CREATE TABLE `goods_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `seller_id` int(11) NOT NULL DEFAULT '0' COMMENT '卖家id',
  `buy_times` int(11) NOT NULL DEFAULT '0' COMMENT '被购买的次数',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `price` int(11) NOT NULL COMMENT '商品价格',
  `sell_point` varchar(255) NOT NULL DEFAULT '好东西',
  `img_url` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `seller` (`seller_id`)
) ENGINE=MyISAM AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `name` varchar(255) NOT NULL COMMENT '店铺名称',
  `username` varchar(255) NOT NULL COMMENT '登录帐号',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `remark` varchar(255) DEFAULT NULL COMMENT '商家备注',
  `addr` varchar(255) NOT NULL COMMENT '商家地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `addr1` varchar(255) DEFAULT NULL COMMENT '地址1',
  `addr2` varchar(255) DEFAULT NULL COMMENT '地址2',
  `addr3` varchar(255) DEFAULT NULL COMMENT '地址3',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
