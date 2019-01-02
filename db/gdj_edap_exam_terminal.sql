/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost:3306
 Source Schema         : gdj_edap_exam_terminal

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 11/04/2018 16:07:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for examinfo
-- ----------------------------
DROP TABLE IF EXISTS `examinfo`;
CREATE TABLE `examinfo`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试名称',
  `examTime` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试时间',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updataDate` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examinfo
-- ----------------------------
INSERT INTO `examinfo` VALUES ('305e8c37188448f6b747031defaa8f01', '考试1', '2018-4-15 9:00:00', '2018-04-10 15:38:06', '2018-04-10 15:38:06');
INSERT INTO `examinfo` VALUES ('8d1e4d25fa9a42f0b75653a818c26ace', '考试3', '2018-4-16 10:30:00', '2018-04-10 16:04:13', '2018-04-10 16:04:13');
INSERT INTO `examinfo` VALUES ('c29e91dcd87a4c03853d6ff5000a53d8', '考试2', '2018-4-25 14:00:00', '2018-04-10 15:39:49', '2018-04-10 15:39:49');

-- ----------------------------
-- Table structure for identify
-- ----------------------------
DROP TABLE IF EXISTS `identify`;
CREATE TABLE `identify`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `examId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本次考试ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证姓名',
  `nation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证民族',
  `birthday` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证的出生日期',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证的住址',
  `idNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证的号码',
  `issued` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证的签发机关',
  `issuedDate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证的签发日期',
  `validDate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证的过期日期',
  `idImgPath` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证相片附件路径',
  `cameraImgPath` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本次摄像头照片附件路径',
  `score` int(4) NULL DEFAULT NULL COMMENT '本次识别分数(重新计算)',
  `rawScore` float NULL DEFAULT NULL COMMENT '本次识别分数',
  `passScore` float NULL DEFAULT NULL COMMENT '本次识别及格分数',
  `isPass` int(1) NULL DEFAULT NULL COMMENT '识别结果：1.通过 0.不通过',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of identify
-- ----------------------------
INSERT INTO `identify` VALUES ('551207387c02447497ad9a597e1f952b', '305e8c37188448f6b747031defaa8f01', '梁建龙', '汉', '1990.05.08', '广东省中山市石岐区青云街48号', '442000199005080711', '中山市公安局', '2016.05.12', '2036.05.12', '\\2018-04-11_305e8c37188448f6b747031defaa8f01\\442000199005080711_551207387c02447497ad9a597e1f952b_idc.jpg', '\\2018-04-11_305e8c37188448f6b747031defaa8f01\\442000199005080711_551207387c02447497ad9a597e1f952b_cma.jpg', 99, 0.99, 0.3, 1, '2018-04-11 14:42:01');
INSERT INTO `identify` VALUES ('5a69dbd5f70845d6809f0d898147b41a', '305e8c37188448f6b747031defaa8f01', '梁建龙', '汉', '1990.05.08', '广东省中山市石岐区青云街48号', '442000199005080711', '中山市公安局', '2016.05.12', '2036.05.12', '\\2018-04-11_305e8c37188448f6b747031defaa8f01\\442000199005080711_5a69dbd5f70845d6809f0d898147b41a_idc.jpg', '\\2018-04-11_305e8c37188448f6b747031defaa8f01\\442000199005080711_5a69dbd5f70845d6809f0d898147b41a_cma.jpg', 99, 0.99, 0.3, 1, '2018-04-11 14:42:30');

-- ----------------------------
-- Table structure for statisticsinfo
-- ----------------------------
DROP TABLE IF EXISTS `statisticsinfo`;
CREATE TABLE `statisticsinfo`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `examId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试Id',
  `total` int(10) NULL DEFAULT NULL COMMENT '考试总人数',
  `ideTotal` int(10) NULL DEFAULT NULL COMMENT '考试识别人数',
  `ideTime` int(10) NULL DEFAULT NULL COMMENT '识别总人次',
  `idePassTotal` int(10) NULL DEFAULT NULL COMMENT '识别通过人数',
  `idePassTime` int(10) NULL DEFAULT NULL COMMENT '识别通过人次',
  `ideFailTotal` int(10) NULL DEFAULT NULL COMMENT '识别失败人数',
  `ideFailTime` int(10) NULL DEFAULT NULL COMMENT '识别失败人次',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statisticsinfo
-- ----------------------------
INSERT INTO `statisticsinfo` VALUES ('837ea7b2bf034864b58c67a491385b58', '305e8c37188448f6b747031defaa8f01', 1, 1, 2, 1, 2, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
