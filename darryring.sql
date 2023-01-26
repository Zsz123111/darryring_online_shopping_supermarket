/*
Navicat MySQL Data Transfer

Source Server         : 根访问
Source Server Version : 50736
Source Host           : localhost:3306
Source Database       : darryring

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2023-01-17 23:24:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(255) NOT NULL,
  `aaddress` varchar(255) NOT NULL,
  `aphone` varchar(255) NOT NULL,
  `apostcode` varchar(6) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`aid`),
  KEY `fk_uid` (`uid`),
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '张仕钊', '广东省东莞市虎门镇', '13207959357', '336000', '1');
INSERT INTO `address` VALUES ('2', '佯装装', '贵州省铜仁市碧江区', '19828374632', '231323', '1');
INSERT INTO `address` VALUES ('3', '刘芮林', '北京市海淀区', '11111111111', '111111', '2');
INSERT INTO `address` VALUES ('4', '余泽涛', '湖北省武汉市武昌区', '13233443332', '222222', '2');
INSERT INTO `address` VALUES ('6', '张仕钊', '牛牛街道', '13207959357', '336000', '3');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `oid` varchar(255) NOT NULL,
  `odate` date NOT NULL,
  `uid` int(11) NOT NULL,
  `ostatues` enum('1','0') NOT NULL DEFAULT '0',
  `oaid` int(11) NOT NULL,
  `otransports` varchar(255) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_ooaid` (`oaid`),
  KEY `fk_ouid` (`uid`),
  CONSTRAINT `fk_ooaid` FOREIGN KEY (`oaid`) REFERENCES `address` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ouid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('00000002', '2023-01-04', '2', '1', '3', '中通快递');
INSERT INTO `order` VALUES ('291d2022-a4e0-400b-a134-f92b726a7f4f', '2023-01-17', '1', '1', '1', '顺丰快递');
INSERT INTO `order` VALUES ('848f03b9-b8a8-4f9f-9992-4962daba239b', '2023-01-17', '1', '1', '1', '顺丰快递');
INSERT INTO `order` VALUES ('cdfc7d68-e977-424d-9253-0707a7f06756', '2023-01-17', '1', '1', '1', '中通快递');
INSERT INTO `order` VALUES ('edbd8f91-1a34-422d-a28f-8f23bdc40b7a', '2023-01-17', '1', '1', '2', '中通快递');

-- ----------------------------
-- Table structure for `order_ring`
-- ----------------------------
DROP TABLE IF EXISTS `order_ring`;
CREATE TABLE `order_ring` (
  `oid` varchar(255) NOT NULL,
  `rid` int(11) NOT NULL,
  `ornum` int(11) NOT NULL,
  `orprice` double NOT NULL,
  KEY `fk_oroid` (`oid`),
  KEY `fk_orrid` (`rid`),
  CONSTRAINT `fk_oroid` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_orrid` FOREIGN KEY (`rid`) REFERENCES `ring` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_ring
-- ----------------------------
INSERT INTO `order_ring` VALUES ('00000002', '2', '1', '11000');
INSERT INTO `order_ring` VALUES ('cdfc7d68-e977-424d-9253-0707a7f06756', '1', '1', '25700');
INSERT INTO `order_ring` VALUES ('cdfc7d68-e977-424d-9253-0707a7f06756', '11', '1', '9988');
INSERT INTO `order_ring` VALUES ('cdfc7d68-e977-424d-9253-0707a7f06756', '7', '1', '39888');
INSERT INTO `order_ring` VALUES ('edbd8f91-1a34-422d-a28f-8f23bdc40b7a', '11', '1', '9988');
INSERT INTO `order_ring` VALUES ('edbd8f91-1a34-422d-a28f-8f23bdc40b7a', '4', '1', '22200');
INSERT INTO `order_ring` VALUES ('848f03b9-b8a8-4f9f-9992-4962daba239b', '4', '1', '22200');
INSERT INTO `order_ring` VALUES ('848f03b9-b8a8-4f9f-9992-4962daba239b', '5', '1', '59998');
INSERT INTO `order_ring` VALUES ('291d2022-a4e0-400b-a134-f92b726a7f4f', '7', '1', '39888');

-- ----------------------------
-- Table structure for `ring`
-- ----------------------------
DROP TABLE IF EXISTS `ring`;
CREATE TABLE `ring` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) NOT NULL,
  `rprice` double NOT NULL,
  `rsales` int(11) NOT NULL,
  `rurl` varchar(255) NOT NULL DEFAULT 'images/',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ring
-- ----------------------------
INSERT INTO `ring` VALUES ('1', 'Forever系列 经典款 50分 H色', '25700', '11113', 'images/2014090119350717386d7a1e.jpg');
INSERT INTO `ring` VALUES ('2', 'True Love系列 简奢款 30分 E色', '11000', '23232', 'images/2014091515351160b3d26880.jpg');
INSERT INTO `ring` VALUES ('3', 'True Love系列 电压 40分 F色', '19150', '2232', 'images/201412081512070b82d519cb.jpg');
INSERT INTO `ring` VALUES ('4', 'Forever系列 简奢款 50分 I-J色', '22200', '11232', 'images/20140903175947fa15145af3.jpg');
INSERT INTO `ring` VALUES ('5', 'My Heart系列 简奢款 50分 H色', '59998', '172', 'images/20141203164814cbaa761ecb.jpg');
INSERT INTO `ring` VALUES ('6', 'My Heart系列 奢华款 30分 E色', '99988', '13', 'images/20141208151207e3fa9cdc9e.jpg');
INSERT INTO `ring` VALUES ('7', 'My Heart系列 经典款 40分 F色', '39888', '1132', 'images/20150422114249c8b953947f.jpg');
INSERT INTO `ring` VALUES ('8', 'I Swear系列 经典款 50分 F色', '13998', '8988', 'images/20150425140852a0a0f1e3e2.jpg');
INSERT INTO `ring` VALUES ('9', 'I Swear系列 豪华款 50分 I色', '99988', '498', 'images/20141208151441a7c5365eda.jpg');
INSERT INTO `ring` VALUES ('10', 'Just You系列 经典款 30分 F色', '29998', '2300', 'images/20141208151356af0991c81c.jpg');
INSERT INTO `ring` VALUES ('11', 'Just You系列 经典款 40分 E色', '9988', '49909', 'images/20141208151343c33b1c06ce.jpg');

-- ----------------------------
-- Table structure for `shopcart`
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  KEY `fk_suid` (`uid`),
  KEY `fk_srid` (`rid`),
  CONSTRAINT `fk_srid` FOREIGN KEY (`rid`) REFERENCES `ring` (`rid`),
  CONSTRAINT `fk_suid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopcart
-- ----------------------------
INSERT INTO `shopcart` VALUES ('2', '1');
INSERT INTO `shopcart` VALUES ('3', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uphone` varchar(255) NOT NULL,
  `upassword` varchar(255) NOT NULL,
  `urealname` varchar(255) DEFAULT '',
  `ugender` varchar(255) DEFAULT '',
  `ubirthday` date DEFAULT '2000-01-01',
  `uurl` varchar(255) NOT NULL DEFAULT 'images/person/default_person.png',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '13207959357', '123456', '张仕钊', '男', '2001-03-09', 'images/person/13207959357.png');
INSERT INTO `user` VALUES ('2', '18970560153', '123456', '刘芮林', '女', '2000-01-12', 'images/person/18970560153.jpg');
INSERT INTO `user` VALUES ('3', '13207756463', '123456', '杨娇娇', '女', '2002-02-20', 'images/person/13207756463.jpg');
