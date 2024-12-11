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

 Date: 11/12/2024 11:11:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category_info
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '自增分类',
  `category_code` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类编码',
  `category_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `p_category_id` int NOT NULL COMMENT '父类分级ID',
  `icon` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
  `background` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '背景图',
  `sort` tinyint NOT NULL COMMENT '排序号',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE KEY `idx_key_category_code` (`category_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='分类信息';

-- ----------------------------
-- Records of category_info
-- ----------------------------
BEGIN;
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (37, 'Artificial Intellegence', '人工智能', 0, 'cover/202411/Y2hXIzZMRMgNI7pDo9EcUgWnZAd5LG.png', '', 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (38, 'deep learning', '深度学习', 37, NULL, NULL, 3);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (39, 'robot learning', '机器学习', 37, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (40, 'linear algebra', '线性代数', 37, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (41, 'Programing', '编程', 0, 'cover/202411/h82Al6E9YjsuUkVZ45Zn4fFFYDKCP1.png', '', 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (42, 'python', 'Python', 41, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (43, 'java', 'Java', 41, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (44, 'life', '日常生活', 0, 'cover/202411/QKcQhhZX4bvDjzhyGVwkpBxSJ8cQpl.png', '', 3);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (45, 'view', '风光', 44, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (46, 'game', '游戏', 0, 'cover/202411/Y1NVmW5XietT46ZdKFPEN96SLlxpMf.png', '', 4);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (47, 'car', '汽车', 0, 'cover/202411/ZxzcUsiiDgz2wx27vsmeniyLGNdjKS.png', NULL, 5);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (48, 'news', '资讯', 0, 'cover/202411/4ntywxZhsRndjdXPTwt334nq2aoY0R.png', NULL, 6);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (49, 'music', '音乐', 0, 'cover/202411/k9wSr3IW0CETV4DMvC1KeLYcRbPtbk.png', NULL, 7);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (50, 'movie', '影视', 0, 'cover/202411/to4z4yANWlEcQ0CEWFuklcNsy27rKU.png', NULL, 8);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (51, 'food', '美食', 0, 'cover/202411/xIZbAQ8tkXWIc94FF30PPjyBLO0nKI.png', NULL, 9);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (52, 'course', '公开课', 0, 'cover/202411/6uVIFm1aoFQeqoZG8LsbsNzpP0N6a2.png', NULL, 10);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (53, 'knowledge', '知识', 0, 'cover/202411/R3XB5Ywv2LdXTuj8gzm8fdf5xHgdqn.png', NULL, 11);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (54, 'anime', '番剧', 0, 'cover/202411/kLNmnQ8ABMoiJ3z8l4c0t0PKUYrR1h.png', NULL, 12);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (55, 'dance', '舞蹈', 0, 'cover/202411/bSzMgqrLqxrsjI86JrI4MFJTOzwX5E.png', NULL, 13);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (56, 'fashion', '时尚', 0, 'cover/202411/fM7e7iYnzWBWJoKOcGIl8e96o8sxia.png', NULL, 14);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (57, 'digital', '数码', 0, 'cover/202411/gmBsqWeZkGjudAHgeP0ARR1UzLMhqk.png', NULL, 15);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (58, 'family', '亲子', 44, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (59, 'diy', '手工', 44, NULL, NULL, 3);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (60, '3A', '3A游戏', 46, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (61, 'online-game', '网络游戏', 46, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (62, 'game-competition', '游戏竞赛', 46, NULL, NULL, 3);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (63, 'family-car', '家用汽车', 47, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (64, 'fancy-car', '跑车/豪华车', 47, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (65, 'Hot-news', '热点', 48, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (66, 'society', '社会', 48, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (67, 'vocaloid', '虚拟歌姬', 49, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (68, 'Chinese-music', '华语音乐', 49, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (69, 'foreign-music', '外语音乐', 49, NULL, NULL, 3);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (70, 'moive-detective', '悬疑', 50, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (71, 'movie-action', '动作', 50, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (72, 'Chinese-food', '中华美食', 51, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (73, 'western-food', '西餐', 51, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (74, 'Chinese-course', '国内课程', 52, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (75, 'foreign-course', '国外课程', 52, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (76, 'science-universal', '科普', 53, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (77, 'society-history', '人文历史', 53, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (78, 'Chinese-anime', '国创动画', 54, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (79, 'janpanese-anime', '日本番剧', 54, '', '', 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (80, 'home-dance', '宅舞', 55, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (81, 'city-dance', '街舞', 55, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (82, 'clothes', '时装', 56, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (83, 'make-up', '配饰妆造', 56, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (84, 'camera', '相机', 57, NULL, NULL, 1);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (85, 'phone', '手机', 57, NULL, NULL, 2);
INSERT INTO `category_info` (`category_id`, `category_code`, `category_name`, `p_category_id`, `icon`, `background`, `sort`) VALUES (86, 'computer', '电脑', 57, NULL, NULL, 3);
COMMIT;

-- ----------------------------
-- Table structure for statistics_info
-- ----------------------------
DROP TABLE IF EXISTS `statistics_info`;
CREATE TABLE `statistics_info` (
  `statistics_date` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '统计日期',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `data_type` tinyint(1) NOT NULL COMMENT '数据统计类型',
  `statistics_count` int NOT NULL COMMENT '统计数值',
  PRIMARY KEY (`statistics_date`,`user_id`,`data_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='数据统计';

-- ----------------------------
-- Records of statistics_info
-- ----------------------------
BEGIN;
INSERT INTO `statistics_info` (`statistics_date`, `user_id`, `data_type`, `statistics_count`) VALUES ('2024-12-08', '8990397154', 0, 33);
INSERT INTO `statistics_info` (`statistics_date`, `user_id`, `data_type`, `statistics_count`) VALUES ('2024-12-09', '8990397154', 0, 11);
INSERT INTO `statistics_info` (`statistics_date`, `user_id`, `data_type`, `statistics_count`) VALUES ('2024-12-09', '8990397154', 2, 3);
INSERT INTO `statistics_info` (`statistics_date`, `user_id`, `data_type`, `statistics_count`) VALUES ('2024-12-09', '8990397154', 3, 3);
INSERT INTO `statistics_info` (`statistics_date`, `user_id`, `data_type`, `statistics_count`) VALUES ('2024-12-09', '8990397154', 4, 3);
INSERT INTO `statistics_info` (`statistics_date`, `user_id`, `data_type`, `statistics_count`) VALUES ('2024-12-09', '8990397154', 5, 2);
COMMIT;

-- ----------------------------
-- Table structure for user_action
-- ----------------------------
DROP TABLE IF EXISTS `user_action`;
CREATE TABLE `user_action` (
  `action_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `video_id` varchar(18) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `video_user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频用户ID',
  `comment_id` int NOT NULL DEFAULT '0' COMMENT '评论ID',
  `action_type` tinyint(1) NOT NULL COMMENT '0:评论点赞 1:讨厌评论 2:视频点赞 3:视频收藏 4:视频投币',
  `action_count` int NOT NULL COMMENT '数量',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `action_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`action_id`) USING BTREE,
  UNIQUE KEY `idx_key_video_comment_type_user` (`video_id`,`comment_id`,`action_type`,`user_id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_type` (`action_type`) USING BTREE,
  KEY `idx_action_time` (`action_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户行为 点赞、评论';

-- ----------------------------
-- Records of user_action
-- ----------------------------
BEGIN;
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (171, 'wWGtjD0Gup', '8990397154', 0, 2, 1, '7857543673', '2024-12-07 21:43:50');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (173, 'wWGtjD0Gup', '8990397154', 0, 3, 1, '7857543673', '2024-12-07 21:44:39');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (174, 'lEAuInY9Zn', '8990397154', 0, 3, 1, '7857543673', '2024-12-07 22:17:52');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (176, 'lEAuInY9Zn', '8990397154', 0, 2, 1, '7857543673', '2024-12-07 22:19:05');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (177, 'lRP0sT831T', '8990397154', 0, 2, 1, '7857543673', '2024-12-07 22:19:12');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (178, 'YcpJHGVqbg', '8990397154', 0, 2, 1, '7857543673', '2024-12-09 15:03:42');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (179, 'YcpJHGVqbg', '8990397154', 0, 4, 1, '7857543673', '2024-12-09 15:03:45');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (180, 'YcpJHGVqbg', '8990397154', 0, 3, 1, '7857543673', '2024-12-09 15:03:46');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (181, 'zEggigt6WH', '8990397154', 0, 2, 1, '7857543673', '2024-12-09 15:15:10');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (182, 'zEggigt6WH', '8990397154', 0, 4, 1, '7857543673', '2024-12-09 15:15:13');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (183, 'zEggigt6WH', '8990397154', 0, 3, 1, '7857543673', '2024-12-09 15:15:14');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (184, 'IxDGR9WN3Z', '8990397154', 0, 2, 1, '7857543673', '2024-12-09 16:33:56');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (185, 'IxDGR9WN3Z', '8990397154', 0, 3, 1, '7857543673', '2024-12-09 16:33:56');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (186, 'IxDGR9WN3Z', '8990397154', 0, 4, 1, '7857543673', '2024-12-09 16:34:01');
COMMIT;

-- ----------------------------
-- Table structure for user_focus
-- ----------------------------
DROP TABLE IF EXISTS `user_focus`;
CREATE TABLE `user_focus` (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `focus_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `focus_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`focus_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_focus
-- ----------------------------
BEGIN;
INSERT INTO `user_focus` (`user_id`, `focus_user_id`, `focus_time`) VALUES ('7857543673', '8990397154', '2024-11-30 20:50:47');
INSERT INTO `user_focus` (`user_id`, `focus_user_id`, `focus_time`) VALUES ('8990397154', '7857543673', '2024-11-30 21:16:16');
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
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
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` (`user_id`, `nick_name`, `email`, `password`, `sex`, `birthday`, `school`, `person_introduction`, `join_time`, `last_login_time`, `last_login_ip`, `status`, `notice_info`, `total_coin_count`, `current_coin_count`, `theme`, `avatar`) VALUES ('7857543673', 'test1', 'test1@qq.com', '08cace34a1afebc4114675e51a8167f2', 2, '', '', '', '2024-11-27 21:57:50', '2024-12-09 15:03:31', '127.0.0.1', 1, '', 10, 6, 1, 'cover/20241201/JtX2mvatrtqB8pWcjaxbyZXI7svW8y.png');
INSERT INTO `user_info` (`user_id`, `nick_name`, `email`, `password`, `sex`, `birthday`, `school`, `person_introduction`, `join_time`, `last_login_time`, `last_login_ip`, `status`, `notice_info`, `total_coin_count`, `current_coin_count`, `theme`, `avatar`) VALUES ('8067577440', 'Fakeruncode', 'woaixkzero@gmail.com', 'c9502c867f34528020a0ad7cc5a2b8fd', 2, NULL, NULL, NULL, '2024-11-19 12:54:08', NULL, NULL, 1, NULL, 10, 10, 1, NULL);
INSERT INTO `user_info` (`user_id`, `nick_name`, `email`, `password`, `sex`, `birthday`, `school`, `person_introduction`, `join_time`, `last_login_time`, `last_login_ip`, `status`, `notice_info`, `total_coin_count`, `current_coin_count`, `theme`, `avatar`) VALUES ('8990397154', 'GUMI coder', 'storywriter@gmail.com', 'f420a7897f9c77aa3990fedd25ad3c45', 1, '2004-03-29', 'University of California, Berkeley', '热爱代码的二次元歌姬', '2024-11-20 20:20:23', '2024-12-07 16:38:29', '127.0.0.1', 1, '和我一起写代码吧', 24, 14, 9, 'cover/20241130/PTvsmbiWGq9h9DBkutDnP6g08FWdeI.png');
COMMIT;

-- ----------------------------
-- Table structure for user_message
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `message_id` int NOT NULL AUTO_INCREMENT COMMENT '消息ID自增列',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `video_id` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主站ID',
  `message_type` tinyint(1) DEFAULT NULL COMMENT '消息类型',
  `send_user_id` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发送方ID',
  `read_type` tinyint(1) DEFAULT NULL COMMENT '创建读未读 1:已读',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `extend_json` text COLLATE utf8mb4_general_ci COMMENT '扩展信息',
  PRIMARY KEY (`message_id`) USING BTREE,
  KEY `idx_user` (`user_id`) USING BTREE,
  KEY `idx_read_type` (`read_type`) USING BTREE,
  KEY `idx_message_type` (`message_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户消息表';

-- ----------------------------
-- Records of user_message
-- ----------------------------
BEGIN;
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (24, '8990397154', 'lEAuInY9Zn', 2, '7857543673', 1, '2024-12-07 20:49:44', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (25, '8990397154', 'wWGtjD0Gup', 2, '7857543673', 1, '2024-12-07 21:38:36', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (26, '8990397154', 'lEAuInY9Zn', 3, '7857543673', 1, '2024-12-07 22:17:52', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (27, '8990397154', 'wWGtjD0Gup', 3, '7857543673', 1, '2024-12-07 22:18:30', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (28, '8990397154', 'lRP0sT831T', 3, '7857543673', 1, '2024-12-07 22:18:40', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (29, '8990397154', 'lRP0sT831T', 2, '7857543673', 1, '2024-12-07 22:19:13', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (30, '8990397154', 'wWGtjD0Gup', 4, '7857543673', 1, '2024-12-07 22:30:55', '{\"messageContent\":\"贝爷牛逼\"}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (31, '7857543673', 'wWGtjD0Gup', 4, '8990397154', 1, '2024-12-07 22:45:14', '{\"messageContent\":\"贝爷确实帅\",\"messageContentReply\":\"贝爷牛逼\"}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (32, '8990397154', 'wWGtjD0Gup', 4, '7857543673', 1, '2024-12-07 22:45:29', '{\"messageContent\":\"爱贝爷更爱mc\",\"messageContentReply\":\"贝爷确实帅\"}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (33, '8990397154', 'wWGtjD0Gup', 4, '7857543673', 1, '2024-12-07 22:49:05', '{\"messageContent\":\"还有什么比贝爷帅！\",\"messageContentReply\":\"贝爷确实帅\"}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (34, '8990397154', 'zEggigt6WH', 1, NULL, 1, '2024-12-08 11:24:54', '{\"auditStatus\":3}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (35, '8990397154', 'zEggigt6WH', 1, NULL, 1, '2024-12-08 11:25:51', '{\"auditStatus\":3}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (37, '8990397154', 'YcpJHGVqbg', 1, NULL, 1, '2024-12-08 17:09:46', '{\"auditStatus\":3}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (39, '8990397154', 'lRP0sT831T', 1, NULL, 1, '2024-12-08 17:12:41', '{\"auditStatus\":3}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (40, '8990397154', 'YcpJHGVqbg', 2, '7857543673', 1, '2024-12-09 15:03:42', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (42, '8990397154', 'YcpJHGVqbg', 3, '7857543673', 1, '2024-12-09 15:03:46', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (43, '8990397154', 'YcpJHGVqbg', 4, '7857543673', 1, '2024-12-09 15:03:57', '{\"messageContent\":\"讲的通俗易懂，感谢Up\"}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (44, '8990397154', 'zEggigt6WH', 2, '7857543673', 1, '2024-12-09 15:15:10', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (46, '8990397154', 'zEggigt6WH', 3, '7857543673', 1, '2024-12-09 15:15:14', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (47, '8990397154', 'zEggigt6WH', 4, '7857543673', 1, '2024-12-09 15:15:21', '{\"messageContent\":\"Mac还是有前景的\"}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (48, '8990397154', 'YcpJHGVqbg', 4, '7857543673', 1, '2024-12-09 16:33:51', '{\"messageContent\":\"up真帅\"}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (49, '8990397154', 'IxDGR9WN3Z', 2, '7857543673', 1, '2024-12-09 16:33:56', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (50, '8990397154', 'IxDGR9WN3Z', 3, '7857543673', 1, '2024-12-09 16:33:57', '{}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (52, '8990397154', 'IxDGR9WN3Z', 4, '7857543673', 1, '2024-12-09 16:34:03', '{\"messageContent\":\"好好玩\"}');
INSERT INTO `user_message` (`message_id`, `user_id`, `video_id`, `message_type`, `send_user_id`, `read_type`, `create_time`, `extend_json`) VALUES (53, '8990397154', 'IxDGR9WN3Z', 4, '7857543673', 1, '2024-12-09 16:37:56', '{\"messageContent\":\"!!\"}');
COMMIT;

-- ----------------------------
-- Table structure for user_video_series
-- ----------------------------
DROP TABLE IF EXISTS `user_video_series`;
CREATE TABLE `user_video_series` (
  `series_id` int NOT NULL AUTO_INCREMENT COMMENT '列表ID',
  `series_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列表名称',
  `series_description` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `sort` tinyint NOT NULL COMMENT '排序',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`series_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户视频序列归档';

-- ----------------------------
-- Records of user_video_series
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_video_series_video
-- ----------------------------
DROP TABLE IF EXISTS `user_video_series_video`;
CREATE TABLE `user_video_series_video` (
  `series_id` int NOT NULL COMMENT '列表ID',
  `video_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `sort` tinyint NOT NULL COMMENT '排序',
  PRIMARY KEY (`series_id`,`video_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_video_series_video
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for video_comment
-- ----------------------------
DROP TABLE IF EXISTS `video_comment`;
CREATE TABLE `video_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `p_comment_id` int NOT NULL COMMENT '父级评论ID',
  `video_id` varchar(18) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `video_user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频用户ID',
  `content` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '回复内容',
  `img_path` varchar(150) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片',
  `user_id` varchar(15) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `reply_user_id` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '回复人ID',
  `top_type` tinyint DEFAULT '0' COMMENT '0:未置顶 1:置顶',
  `post_time` datetime NOT NULL COMMENT '发布时间',
  `like_count` int NOT NULL DEFAULT '0',
  `hate_count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`comment_id`) USING BTREE,
  KEY `idx_post_time` (`post_time`) USING BTREE,
  KEY `idx_top` (`top_type`) USING BTREE,
  KEY `idx_p_id` (`p_comment_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='评论';

-- ----------------------------
-- Records of video_comment
-- ----------------------------
BEGIN;
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (53, 0, 'wWGtjD0Gup', '8990397154', '贝爷牛逼', '', '7857543673', NULL, 0, '2024-12-07 22:30:55', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (54, 53, 'wWGtjD0Gup', '8990397154', '贝爷确实帅', NULL, '8990397154', NULL, 0, '2024-12-07 22:45:14', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (55, 53, 'wWGtjD0Gup', '8990397154', '爱贝爷更爱mc', NULL, '7857543673', '8990397154', 0, '2024-12-07 22:45:29', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (57, 53, 'wWGtjD0Gup', '8990397154', '还有什么比贝爷帅！', NULL, '7857543673', '8990397154', 0, '2024-12-07 22:49:05', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (58, 0, 'YcpJHGVqbg', '8990397154', '讲的通俗易懂，感谢Up', '', '7857543673', NULL, 0, '2024-12-09 15:03:57', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (59, 0, 'zEggigt6WH', '8990397154', 'Mac还是有前景的', '', '7857543673', NULL, 0, '2024-12-09 15:15:21', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (60, 0, 'YcpJHGVqbg', '8990397154', 'up真帅', '', '7857543673', NULL, 0, '2024-12-09 16:33:51', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (61, 0, 'IxDGR9WN3Z', '8990397154', '好好玩', '', '7857543673', NULL, 0, '2024-12-09 16:34:03', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (62, 0, 'IxDGR9WN3Z', '8990397154', '!!', '', '7857543673', NULL, 0, '2024-12-09 16:37:56', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for video_danmu
-- ----------------------------
DROP TABLE IF EXISTS `video_danmu`;
CREATE TABLE `video_danmu` (
  `danmu_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `video_id` varchar(18) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `file_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一ID',
  `user_id` varchar(15) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `post_time` datetime DEFAULT NULL COMMENT '发布时间',
  `text` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '内容',
  `mode` tinyint(1) DEFAULT NULL COMMENT '展示位置',
  `color` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '颜色',
  `time` int DEFAULT NULL COMMENT '展示时间',
  PRIMARY KEY (`danmu_id`) USING BTREE,
  KEY `idx_file_id` (`file_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频弹幕';

-- ----------------------------
-- Records of video_danmu
-- ----------------------------
BEGIN;
INSERT INTO `video_danmu` (`danmu_id`, `video_id`, `file_id`, `user_id`, `post_time`, `text`, `mode`, `color`, `time`) VALUES (42, 'YcpJHGVqbg', 'SCX24Gz3hBjmuibsm83A', '8990397154', '2024-12-09 15:34:11', 'nb', 0, '#FFFFFF', 4);
COMMIT;

-- ----------------------------
-- Table structure for video_info
-- ----------------------------
DROP TABLE IF EXISTS `video_info`;
CREATE TABLE `video_info` (
  `video_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `video_cover` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频封面',
  `video_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频名称',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime NOT NULL COMMENT '最后更新时间',
  `p_category_id` int NOT NULL COMMENT '父级分类ID',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `post_type` tinyint NOT NULL COMMENT '0:自制作 1:转载',
  `origin_info` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '原资源说明',
  `tags` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
  `introduction` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简介',
  `interaction` varchar(5) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '互动设置',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频信息';

-- ----------------------------
-- Records of video_info
-- ----------------------------
BEGIN;
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('iSq5pqzhDU', 'cover/20241207/fvIckkWOYHWWHIRceuR90l1dyb4dL4.png', '【线代应用】学线性代数到底有什么用？', '8990397154', '2024-12-07 22:53:34', '2024-12-07 22:53:34', 37, 40, 0, NULL, '线性代数,数学', '线性代数有什么用呢？和up一起探索吧！', '', 540, 5, 0, 0, 0, 0, 0, 0, '2024-12-09 14:14:03');
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('IxDGR9WN3Z', 'cover/20241207/qeKoqMjSmg8rl5ZpkOS64qspHkTFl2.png', '“接下来有请红星集团党委书记” ~！!', '8990397154', '2024-12-07 22:55:32', '2024-12-07 23:08:34', 50, 71, 0, NULL, '搞笑,香港电影,电影,红星', '红星集团？', '', 39, 7, 1, 0, 2, 1, 1, 0, '2024-12-09 17:40:05');
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('lEAuInY9Zn', 'cover/20241205/yiHGLsIpBL8TiIycOIkvLD5AJS8KsZ.png', '【初代NDS】你说得对，但任天堂其实非常懂手机游戏', '8990397154', '2024-12-05 10:20:26', '2024-12-05 10:45:58', 46, 46, 0, NULL, '任天堂,游戏,游戏机,数码', '', '', 680, 17, 1, 0, 0, 0, 1, 1, '2024-12-09 15:01:13');
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('lRP0sT831T', 'cover/20241208/KZ1hzBbVtB2rcc3cTDu1SbrnlJDAM1.png', '早期数值怪  葫芦娃！', '8990397154', '2024-12-04 23:17:28', '2024-12-08 17:12:04', 50, 50, 0, NULL, '葫芦娃,搞笑', '葫芦娃！！~', '', 41, 15, 1, 0, 1, 0, 0, 0, '2024-12-08 16:07:27');
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('wWGtjD0Gup', 'cover/20241205/DU0GKEaMHq2QWxutQnsDhdoPwSKPdE.png', '贝爷展示如何在《我的世界》游戏的艰难环境中求生!', '8990397154', '2024-12-05 10:38:34', '2024-12-05 10:45:49', 46, 46, 0, NULL, '游戏,我的世界', '贝尔格里尔斯在游戏中的表现', '', 167, 55, 1, 0, 1, 0, 1, 0, '2024-12-08 16:15:55');
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('YcpJHGVqbg', 'cover/20241208/C06KD7ZA3WCjLe92MzCLslJ30Gaxna.png', '浅显易懂，5分钟搞懂什么是机器学习 ', '8990397154', '2024-12-08 17:09:35', '2024-12-08 17:09:35', 37, 39, 0, NULL, '机器学习,人工智能,机器解决问题', '机器学习是如何解决问题的？', NULL, 312, 6, 1, 1, 2, 1, 1, 0, '2024-12-09 16:33:42');
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('zEggigt6WH', 'cover/20241208/ZLeOOTvlnl8dDZNwHxuktkHS0LhKq2.png', '坏了！苹果真有生产力了：我用3500块的Mac mini跑了这些大模型 ！', '8990397154', '2024-12-08 11:24:27', '2024-12-08 11:25:45', 37, 39, 0, NULL, '科技,大模型,Mac,苹果,Apple, Mac mini', 'Mac mini也能跑大模型？', '', 431, 14, 1, 0, 1, 1, 1, 0, '2024-12-09 15:15:09');
COMMIT;

-- ----------------------------
-- Table structure for video_info_file
-- ----------------------------
DROP TABLE IF EXISTS `video_info_file`;
CREATE TABLE `video_info_file` (
  `file_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一ID',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `video_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `file_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名',
  `file_index` int NOT NULL COMMENT '文件索引',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件路径',
  `duration` int DEFAULT NULL COMMENT '持续时间（秒）',
  PRIMARY KEY (`file_id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频文件信息';

-- ----------------------------
-- Records of video_info_file
-- ----------------------------
BEGIN;
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('Cq5s1KoiEa8jXANYLTPj', '8990397154', 'lEAuInY9Zn', '【初代NDS】你说得对，但任天堂其实非常懂手游 - 001 - 【初代NDS】你说得对，但任天堂其实非常懂手游', 1, 49886084, 'video/20241205/8990397154QVdymsWpyYWb7Cg', 680);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('d0mAhuxSEjnYTAMjNuid', '8990397154', 'lRP0sT831T', '早期数值怪 - 001 - 早期数值怪', 2, 6357418, 'video/20241205/8990397154wS1NvrvqXR5NZgR', 41);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('FkxJfkXxgt8iVG8ocx6g', '8990397154', 'IxDGR9WN3Z', '“接下来有请红星集团党委书记”', 1, 1213131, 'video/20241207/8990397154R2nN7xcjz1bezpx', 41);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('liznjRe5g00iom70Sqzl', '8990397154', 'zEggigt6WH', '坏了！苹果真有生产力了：我用3500块的Mac mini跑了这些大模型 - 001 - 坏了！苹果真有生产力了：我用3500块的Mac mini跑了这些大模型', 1, 17539535, 'video/20241208/8990397154dGHh3Fk3WP762EQ', 431);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('mTELmFAwabpspBDoFlnI', '8990397154', 'wWGtjD0Gup', '贝爷展示如何在《我的世界》的艰难环境中求生 - 001 - 贝爷展示如何在《我的世界》的艰难环境中求生', 1, 19560756, 'video/20241205/8990397154HPFPabVU3PvPuT0', 167);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('RbpryXx5daaTh4RrC44T', '8990397154', 'iSq5pqzhDU', '【线代应用】学线性代数到底有什么用？ - 001 - 【线代应用】学线性代数到底有什么用？', 1, 16045925, 'video/20241207/8990397154MIYQNekLw5MM9dW', 540);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('SCX24Gz3hBjmuibsm83A', '8990397154', 'YcpJHGVqbg', '浅显易懂，5分钟搞懂什么是机器学习 - 001 - 浅显易懂，5分钟搞懂什么是机器学习', 1, 19123024, 'video/20241208/8990397154bK8IuUsRUxuGkJJ', 312);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('Y4Tp82hKXCL49SegCZwt', '8990397154', 'lRP0sT831T', '早期数值怪 - 001 - 早期数值怪！', 1, 6357418, 'video/20241204/8990397154HeFRO0WhOgeRbi8', 41);
COMMIT;

-- ----------------------------
-- Table structure for video_info_file_post
-- ----------------------------
DROP TABLE IF EXISTS `video_info_file_post`;
CREATE TABLE `video_info_file_post` (
  `file_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一ID',
  `upload_id` varchar(15) COLLATE utf8mb4_general_ci NOT NULL COMMENT '上传ID',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `video_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `file_index` int NOT NULL COMMENT '文件索引',
  `file_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件路径',
  `update_type` tinyint DEFAULT NULL COMMENT '0:无更新 1:有更新',
  `transfer_result` tinyint DEFAULT NULL COMMENT '0:转码中 1:转码成功 2:转码失败',
  `duration` int DEFAULT NULL COMMENT '持续时间（秒）',
  PRIMARY KEY (`file_id`) USING BTREE,
  UNIQUE KEY `idx_key_upload_id` (`upload_id`,`user_id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频文件信息';

-- ----------------------------
-- Records of video_info_file_post
-- ----------------------------
BEGIN;
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('Cq5s1KoiEa8jXANYLTPj', 'QVdymsWpyYWb7Cg', '8990397154', 'lEAuInY9Zn', 1, '【初代NDS】你说得对，但任天堂其实非常懂手游 - 001 - 【初代NDS】你说得对，但任天堂其实非常懂手游', 49886084, 'video/20241205/8990397154QVdymsWpyYWb7Cg', NULL, NULL, 680);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('d0mAhuxSEjnYTAMjNuid', 'wS1NvrvqXR5NZgR', '8990397154', 'lRP0sT831T', 2, '早期数值怪 - 001 - 早期数值怪', 6357418, 'video/20241205/8990397154wS1NvrvqXR5NZgR', NULL, NULL, 41);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('FkxJfkXxgt8iVG8ocx6g', 'R2nN7xcjz1bezpx', '8990397154', 'IxDGR9WN3Z', 1, '“接下来有请红星集团党委书记”', 1213131, 'video/20241207/8990397154R2nN7xcjz1bezpx', NULL, NULL, 41);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('liznjRe5g00iom70Sqzl', 'dGHh3Fk3WP762EQ', '8990397154', 'zEggigt6WH', 1, '坏了！苹果真有生产力了：我用3500块的Mac mini跑了这些大模型 - 001 - 坏了！苹果真有生产力了：我用3500块的Mac mini跑了这些大模型', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('mTELmFAwabpspBDoFlnI', 'HPFPabVU3PvPuT0', '8990397154', 'wWGtjD0Gup', 1, '贝爷展示如何在《我的世界》的艰难环境中求生 - 001 - 贝爷展示如何在《我的世界》的艰难环境中求生', 19560756, 'video/20241205/8990397154HPFPabVU3PvPuT0', NULL, NULL, 167);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('RbpryXx5daaTh4RrC44T', 'MIYQNekLw5MM9dW', '8990397154', 'iSq5pqzhDU', 1, '【线代应用】学线性代数到底有什么用？ - 001 - 【线代应用】学线性代数到底有什么用？', 16045925, 'video/20241207/8990397154MIYQNekLw5MM9dW', 1, 1, 540);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('SCX24Gz3hBjmuibsm83A', 'bK8IuUsRUxuGkJJ', '8990397154', 'YcpJHGVqbg', 1, '浅显易懂，5分钟搞懂什么是机器学习 - 001 - 浅显易懂，5分钟搞懂什么是机器学习', 19123024, 'video/20241208/8990397154bK8IuUsRUxuGkJJ', 1, 1, 312);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('Y4Tp82hKXCL49SegCZwt', 'HeFRO0WhOgeRbi8', '8990397154', 'lRP0sT831T', 1, '早期数值怪 - 001 - 早期数值怪！', 6357418, 'video/20241204/8990397154HeFRO0WhOgeRbi8', NULL, NULL, 41);
COMMIT;

-- ----------------------------
-- Table structure for video_info_post
-- ----------------------------
DROP TABLE IF EXISTS `video_info_post`;
CREATE TABLE `video_info_post` (
  `video_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '视频ID',
  `video_cover` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频封面',
  `video_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频名称',
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime NOT NULL COMMENT '最后更新时间',
  `p_category_id` int NOT NULL COMMENT '父级分类ID',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `status` tinyint(1) NOT NULL COMMENT '0:转码中 1:转码失败 2:待审核 3:审核成功 4:审核失败',
  `post_type` tinyint NOT NULL COMMENT '0:自制作 1:转载',
  `origin_info` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '原资源说明',
  `tags` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
  `introduction` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简介',
  `interaction` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '互动设置',
  `duration` int DEFAULT NULL COMMENT '持续时间（秒）',
  PRIMARY KEY (`video_id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_category_id` (`category_id`) USING BTREE,
  KEY `idx_pcategory_id` (`p_category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频信息';

-- ----------------------------
-- Records of video_info_post
-- ----------------------------
BEGIN;
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('iSq5pqzhDU', 'cover/20241207/fvIckkWOYHWWHIRceuR90l1dyb4dL4.png', '【线代应用】学线性代数到底有什么用？', '8990397154', '2024-12-07 22:53:34', '2024-12-07 22:53:34', 37, 40, 3, 0, NULL, '线性代数,数学', '线性代数有什么用呢？和up一起探索吧！', '', 540);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('IxDGR9WN3Z', 'cover/20241207/qeKoqMjSmg8rl5ZpkOS64qspHkTFl2.png', '“接下来有请红星集团党委书记” ~！!', '8990397154', '2024-12-07 22:55:32', '2024-12-07 23:08:34', 50, 71, 3, 0, NULL, '搞笑,香港电影,电影,红星', '红星集团？', '', 39);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('lEAuInY9Zn', 'cover/20241205/yiHGLsIpBL8TiIycOIkvLD5AJS8KsZ.png', '【初代NDS】你说得对，但任天堂其实非常懂手机游戏', '8990397154', '2024-12-05 10:20:26', '2024-12-05 10:45:58', 46, 46, 3, 0, NULL, '任天堂,游戏,游戏机,数码', '', '', 680);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('lRP0sT831T', 'cover/20241208/KZ1hzBbVtB2rcc3cTDu1SbrnlJDAM1.png', '早期数值怪  葫芦娃！', '8990397154', '2024-12-04 23:17:28', '2024-12-08 17:12:04', 50, 50, 3, 0, NULL, '葫芦娃,搞笑', '葫芦娃！！~', '', 41);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('wWGtjD0Gup', 'cover/20241205/DU0GKEaMHq2QWxutQnsDhdoPwSKPdE.png', '贝爷展示如何在《我的世界》游戏的艰难环境中求生!', '8990397154', '2024-12-05 10:38:34', '2024-12-05 10:45:49', 46, 46, 3, 0, NULL, '游戏,我的世界', '贝尔格里尔斯在游戏中的表现', '', 167);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('YcpJHGVqbg', 'cover/20241208/C06KD7ZA3WCjLe92MzCLslJ30Gaxna.png', '浅显易懂，5分钟搞懂什么是机器学习 ', '8990397154', '2024-12-08 17:09:35', '2024-12-08 17:09:35', 37, 39, 3, 0, NULL, '机器学习,人工智能,机器解决问题', '机器学习是如何解决问题的？', NULL, 312);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('zEggigt6WH', 'cover/20241208/ZLeOOTvlnl8dDZNwHxuktkHS0LhKq2.png', '坏了！苹果真有生产力了：我用3500块的Mac mini跑了这些大模型 ！', '8990397154', '2024-12-08 11:24:27', '2024-12-08 11:27:11', 37, 37, 3, 0, NULL, '科技,大模型,Mac,苹果,Apple, Mac mini', 'Mac mini也能跑大模型？', '', 431);
COMMIT;

-- ----------------------------
-- Table structure for video_play_history
-- ----------------------------
DROP TABLE IF EXISTS `video_play_history`;
CREATE TABLE `video_play_history` (
  `user_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `video_id` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `file_index` int NOT NULL COMMENT '文件索引',
  `last_update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`,`video_id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频播放历史';

-- ----------------------------
-- Records of video_play_history
-- ----------------------------
BEGIN;
INSERT INTO `video_play_history` (`user_id`, `video_id`, `file_index`, `last_update_time`) VALUES ('7857543673', 'IxDGR9WN3Z', 1, '2024-12-09 17:40:05');
INSERT INTO `video_play_history` (`user_id`, `video_id`, `file_index`, `last_update_time`) VALUES ('7857543673', 'YcpJHGVqbg', 1, '2024-12-09 16:33:42');
INSERT INTO `video_play_history` (`user_id`, `video_id`, `file_index`, `last_update_time`) VALUES ('7857543673', 'zEggigt6WH', 1, '2024-12-09 15:15:10');
INSERT INTO `video_play_history` (`user_id`, `video_id`, `file_index`, `last_update_time`) VALUES ('8990397154', 'iSq5pqzhDU', 1, '2024-12-09 14:14:04');
INSERT INTO `video_play_history` (`user_id`, `video_id`, `file_index`, `last_update_time`) VALUES ('8990397154', 'lEAuInY9Zn', 1, '2024-12-09 15:01:13');
INSERT INTO `video_play_history` (`user_id`, `video_id`, `file_index`, `last_update_time`) VALUES ('8990397154', 'YcpJHGVqbg', 1, '2024-12-09 15:34:08');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
