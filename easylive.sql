/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : easylive

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 19/11/2024 17:15:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category_info
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '自增分类',
  `category_code` varchar(30) NOT NULL COMMENT '分类编码',
  `category_name` varchar(30) NOT NULL COMMENT '分类名称',
  `p_category_id` int NOT NULL COMMENT '父类分级ID',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `background` varchar(50) DEFAULT NULL COMMENT '背景图',
  `sort` tinyint NOT NULL COMMENT '排序号',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE KEY `idx_key_category_code` (`category_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='分类信息';

-- ----------------------------
-- Records of category_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `sex` tinyint(1) DEFAULT '2' COMMENT '性别 0女 1男 2未知',
  `birthday` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生日',
  `school` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学校',
  `person_introduction` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个人简介',
  `join_time` datetime NOT NULL COMMENT '加入时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后登录Ip',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0禁用 1正常',
  `notice_info` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '空间公告',
  `total_coin_count` int NOT NULL DEFAULT '0' COMMENT '获得过的硬币总数',
  `current_coin_count` int NOT NULL DEFAULT '0' COMMENT '当前硬币数',
  `theme` tinyint(1) NOT NULL COMMENT '主题',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` (`user_id`, `nick_name`, `email`, `password`, `sex`, `birthday`, `school`, `person_introduction`, `join_time`, `last_login_time`, `last_login_ip`, `status`, `notice_info`, `total_coin_count`, `current_coin_count`, `theme`) VALUES ('8067577440', 'Fakeruncode', 'woaixkzero@gmail.com', 'c9502c867f34528020a0ad7cc5a2b8fd', 2, NULL, NULL, NULL, '2024-11-19 12:54:08', NULL, NULL, 1, NULL, 10, 10, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
