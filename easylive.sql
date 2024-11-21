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

 Date: 21/11/2024 16:56:36
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='分类信息';

-- ----------------------------
-- Records of category_info
-- ----------------------------
BEGIN;
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (37, 'Artificial Intellegence', '人工智能', 0, 'cover/202411/CNwTtyF8XBbIYn2DntASR9WTwoJLEO.jpg', '', 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (38, 'deep learning', '深度学习', 37, NULL, NULL, 3);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (39, 'robot learning', '机器学习', 37, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (40, 'linear algebra', '线性代数', 37, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (41, 'Programing', '编程', 0, 'cover/202411/YK2Qrcdd0no2TYsEv5VqueIqlOo9Y1.jpg', '', 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (42, 'python', 'Python', 41, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (43, 'java', 'Java', 41, NULL, NULL, 2);
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
INSERT INTO `user_info` (`user_id`, `nick_name`, `email`, `password`, `sex`, `birthday`, `school`, `person_introduction`, `join_time`, `last_login_time`, `last_login_ip`, `status`, `notice_info`, `total_coin_count`, `current_coin_count`, `theme`) VALUES ('8990397154', 'fakerUNcode2', 'storywriter@gmail.com', 'f420a7897f9c77aa3990fedd25ad3c45', 2, NULL, NULL, NULL, '2024-11-20 20:20:23', '2024-11-21 09:20:57', '127.0.0.1', 1, NULL, 10, 10, 1);
COMMIT;

-- ----------------------------
-- Table structure for video_info
-- ----------------------------
DROP TABLE IF EXISTS `video_info`;
CREATE TABLE `video_info` (
  `video_id` varchar(10) NOT NULL COMMENT '视频ID',
  `video_cover` varchar(50) NOT NULL COMMENT '视频封面',
  `video_name` varchar(100) NOT NULL COMMENT '视频名称',
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime NOT NULL COMMENT '最后更新时间',
  `p_category_id` int NOT NULL COMMENT '父级分类ID',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `post_type` tinyint NOT NULL COMMENT '0:自制作 1:转载',
  `origin_info` varchar(200) DEFAULT NULL COMMENT '原资源说明',
  `tags` varchar(300) DEFAULT NULL COMMENT '标签',
  `introduction` varchar(2000) DEFAULT NULL COMMENT '简介',
  `interaction` varchar(5) DEFAULT NULL COMMENT '互动设置',
  `duration` int DEFAULT '0' COMMENT '持续时间（秒）',
  `play_count` int DEFAULT '0' COMMENT '播放数量',
  `like_count` int DEFAULT '0' COMMENT '点赞数量',
  `danmu_count` int DEFAULT '0' COMMENT '弹幕数量',
  `comment_count` int DEFAULT '0' COMMENT '评论数量',
  `coin_count` int DEFAULT '0' COMMENT '投币数量',
  `collect_count` int DEFAULT '0' COMMENT '收藏数量',
  `recommend_type` tinyint(1) DEFAULT '0' COMMENT '是否推荐 0:未推荐 1:已推荐',
  `last_play_time` datetime DEFAULT NULL COMMENT '最后播放时间',
  PRIMARY KEY (`video_id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_category_id` (`category_id`) USING BTREE,
  KEY `idx_pcategory_id` (`p_category_id`) USING BTREE,
  KEY `idx_recommend_type` (`recommend_type`) USING BTREE,
  KEY `idx_last_update_time` (`last_play_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频信息';

-- ----------------------------
-- Records of video_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for video_info_file
-- ----------------------------
DROP TABLE IF EXISTS `video_info_file`;
CREATE TABLE `video_info_file` (
  `file_id` varchar(20) NOT NULL COMMENT '唯一ID',
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `video_id` varchar(10) NOT NULL COMMENT '视频ID',
  `file_name` varchar(200) DEFAULT NULL COMMENT '文件名',
  `file_index` int NOT NULL COMMENT '文件索引',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(100) DEFAULT NULL COMMENT '文件路径',
  `duration` int DEFAULT NULL COMMENT '持续时间（秒）',
  PRIMARY KEY (`file_id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频文件信息';

-- ----------------------------
-- Records of video_info_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for video_info_file_post
-- ----------------------------
DROP TABLE IF EXISTS `video_info_file_post`;
CREATE TABLE `video_info_file_post` (
  `file_id` varchar(20) NOT NULL COMMENT '唯一ID',
  `upload_id` varchar(15) NOT NULL COMMENT '上传ID',
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `video_id` varchar(10) NOT NULL COMMENT '视频ID',
  `file_index` int NOT NULL COMMENT '文件索引',
  `file_name` varchar(200) DEFAULT NULL COMMENT '文件名',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(100) DEFAULT NULL COMMENT '文件路径',
  `update_type` tinyint DEFAULT NULL COMMENT '0:无更新 1:有更新',
  `transfer_result` tinyint DEFAULT NULL COMMENT '0:转码中 1:转码成功 2:转码失败',
  `duration` int DEFAULT NULL COMMENT '持续时间（秒）',
  PRIMARY KEY (`file_id`) USING BTREE,
  UNIQUE KEY `idx_key_upload_id` (`upload_id`,`user_id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频文件信息';

-- ----------------------------
-- Records of video_info_file_post
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for video_info_post
-- ----------------------------
DROP TABLE IF EXISTS `video_info_post`;
CREATE TABLE `video_info_post` (
  `video_id` varchar(10) NOT NULL DEFAULT '0' COMMENT '视频ID',
  `video_cover` varchar(50) NOT NULL COMMENT '视频封面',
  `video_name` varchar(100) NOT NULL COMMENT '视频名称',
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime NOT NULL COMMENT '最后更新时间',
  `p_category_id` int NOT NULL COMMENT '父级分类ID',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `status` tinyint(1) NOT NULL COMMENT '0:转码中 1:转码失败 2:待审核 3:审核成功 4:审核失败',
  `post_type` tinyint NOT NULL COMMENT '0:自制作 1:转载',
  `origin_info` varchar(200) DEFAULT NULL COMMENT '原资源说明',
  `tags` varchar(300) DEFAULT NULL COMMENT '标签',
  `introduction` varchar(2000) DEFAULT NULL COMMENT '简介',
  `interaction` varchar(5) DEFAULT NULL COMMENT '互动设置',
  `duration` int DEFAULT NULL COMMENT '持续时间（秒）',
  PRIMARY KEY (`video_id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_category_id` (`category_id`) USING BTREE,
  KEY `idx_pcategory_id` (`p_category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频信息';

-- ----------------------------
-- Records of video_info_post
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
