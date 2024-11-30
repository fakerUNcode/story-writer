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

 Date: 30/11/2024 14:04:37
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
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='分类信息';

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
-- Table structure for user_action
-- ----------------------------
DROP TABLE IF EXISTS `user_action`;
CREATE TABLE `user_action` (
  `action_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `video_id` varchar(18) NOT NULL COMMENT '视频ID',
  `video_user_id` varchar(10) NOT NULL COMMENT '视频用户ID',
  `comment_id` int NOT NULL DEFAULT '0' COMMENT '评论ID',
  `action_type` tinyint(1) NOT NULL COMMENT '0:评论点赞 1:讨厌评论 2:视频点赞 3:视频收藏 4:视频投币',
  `action_count` int NOT NULL COMMENT '数量',
  `user_id` varchar(10) NOT NULL COMMENT '用户ID',
  `action_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`action_id`) USING BTREE,
  UNIQUE KEY `idx_key_video_comment_type_user` (`video_id`,`comment_id`,`action_type`,`user_id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_type` (`action_type`) USING BTREE,
  KEY `idx_action_time` (`action_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户行为 点赞、评论';

-- ----------------------------
-- Records of user_action
-- ----------------------------
BEGIN;
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (71, 'WMzrMh0yru', '8990397154', 0, 3, 1, '8990397154', '2024-11-27 20:57:53');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (81, 'hHszQZ8ER0', '8990397154', 0, 4, 1, '7857543673', '2024-11-27 22:04:05');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (82, 'hHszQZ8ER0', '8990397154', 0, 2, 1, '7857543673', '2024-11-27 22:04:17');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (83, 'hHszQZ8ER0', '8990397154', 0, 3, 1, '7857543673', '2024-11-27 22:04:18');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (84, 'hHszQZ8ER0', '8990397154', 0, 2, 1, '8990397154', '2024-11-27 22:05:01');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (85, 'hHszQZ8ER0', '8990397154', 0, 3, 1, '8990397154', '2024-11-27 22:05:02');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (134, 'ysUORUetuQ', '8990397154', 30, 1, 1, '8990397154', '2024-11-28 23:27:35');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (137, 'ysUORUetuQ', '8990397154', 35, 0, 1, '8990397154', '2024-11-28 23:28:46');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (138, 'ysUORUetuQ', '8990397154', 34, 0, 1, '8990397154', '2024-11-28 23:28:47');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (140, 'ysUORUetuQ', '8990397154', 32, 1, 1, '8990397154', '2024-11-28 23:28:48');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (141, 'ysUORUetuQ', '8990397154', 31, 0, 1, '8990397154', '2024-11-28 23:30:06');
INSERT INTO `user_action` (`action_id`, `video_id`, `video_user_id`, `comment_id`, `action_type`, `action_count`, `user_id`, `action_time`) VALUES (144, 'ysUORUetuQ', '8990397154', 33, 0, 1, '8990397154', '2024-11-28 23:30:09');
COMMIT;

-- ----------------------------
-- Table structure for user_focus
-- ----------------------------
DROP TABLE IF EXISTS `user_focus`;
CREATE TABLE `user_focus` (
  `user_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `focus_user_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `focus_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`focus_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_focus
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
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` (`user_id`, `nick_name`, `email`, `password`, `sex`, `birthday`, `school`, `person_introduction`, `join_time`, `last_login_time`, `last_login_ip`, `status`, `notice_info`, `total_coin_count`, `current_coin_count`, `theme`, `avatar`) VALUES ('7857543673', 'test1', 'test1@qq.com', '08cace34a1afebc4114675e51a8167f2', 2, NULL, NULL, NULL, '2024-11-27 21:57:50', '2024-11-28 20:00:19', '127.0.0.1', 1, NULL, 10, 9, 1, NULL);
INSERT INTO `user_info` (`user_id`, `nick_name`, `email`, `password`, `sex`, `birthday`, `school`, `person_introduction`, `join_time`, `last_login_time`, `last_login_ip`, `status`, `notice_info`, `total_coin_count`, `current_coin_count`, `theme`, `avatar`) VALUES ('8067577440', 'Fakeruncode', 'woaixkzero@gmail.com', 'c9502c867f34528020a0ad7cc5a2b8fd', 2, NULL, NULL, NULL, '2024-11-19 12:54:08', NULL, NULL, 1, NULL, 10, 10, 1, NULL);
INSERT INTO `user_info` (`user_id`, `nick_name`, `email`, `password`, `sex`, `birthday`, `school`, `person_introduction`, `join_time`, `last_login_time`, `last_login_ip`, `status`, `notice_info`, `total_coin_count`, `current_coin_count`, `theme`, `avatar`) VALUES ('8990397154', 'fakerUNcode2', 'storywriter@gmail.com', 'f420a7897f9c77aa3990fedd25ad3c45', 2, NULL, NULL, NULL, '2024-11-20 20:20:23', '2024-11-29 21:45:51', '127.0.0.1', 1, NULL, 11, 11, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_video_series
-- ----------------------------
DROP TABLE IF EXISTS `user_video_series`;
CREATE TABLE `user_video_series` (
  `series_id` int NOT NULL AUTO_INCREMENT COMMENT '列表ID',
  `series_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '列表名称',
  `series_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `user_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `sort` tinyint NOT NULL COMMENT '排序',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`series_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户视频序列归档';

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
  `video_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频ID',
  `user_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='评论';

-- ----------------------------
-- Records of video_comment
-- ----------------------------
BEGIN;
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (32, 0, 'ysUORUetuQ', '8990397154', '程序员也太厉害了吧', '', '8990397154', NULL, 0, '2024-11-28 19:37:07', 0, 1);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (34, 0, 'ysUORUetuQ', '8990397154', '😃show maker!', '', '8990397154', NULL, 0, '2024-11-28 20:09:17', 1, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (35, 0, 'ysUORUetuQ', '8990397154', '多喝热水', 'cover/20241128/tOTj4JC6kvgg5naDLaUPSRIPwi04hL.jpg', '8990397154', NULL, 0, '2024-11-28 20:09:40', 1, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (37, 0, 'ysUORUetuQ', '8990397154', '123', '', '8990397154', NULL, 0, '2024-11-29 00:23:33', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (39, 0, 'ysUORUetuQ', '8990397154', '把水军的评论全都删掉😜', '', '8990397154', NULL, 0, '2024-11-29 19:33:53', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (40, 0, 'ysUORUetuQ', '8990397154', 'Java 是一种 面向对象的编程语言，由 Sun Microsystems 于 1995 年首次发布（现归属 Oracle 公司）。Java 是一门 跨平台、高性能、多功能 的编程语言，被广泛用于各种应用开发。', '', '8990397154', NULL, 1, '2024-11-29 21:09:33', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (41, 0, 'ysUORUetuQ', '8990397154', 'Java 最大的特点之一是“一次编写，到处运行”（Write Once, Run Anywhere，WORA）。', '', '8990397154', NULL, 0, '2024-11-29 21:09:46', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (42, 0, 'ysUORUetuQ', '8990397154', 'Java 程序在编译后生成中间字节码（Bytecode），可以在任何安装了 Java 虚拟机（JVM）的系统上运行，无需修改代码。', '', '8990397154', NULL, 0, '2024-11-29 21:10:01', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (43, 0, 'ysUORUetuQ', '8990397154', 'Java 是一种 面向对象的编程语言，以类（Class）和对象（Object）为核心。', '', '8990397154', NULL, 0, '2024-11-29 21:10:17', 0, 0);
INSERT INTO `video_comment` (`comment_id`, `p_comment_id`, `video_id`, `video_user_id`, `content`, `img_path`, `user_id`, `reply_user_id`, `top_type`, `post_time`, `like_count`, `hate_count`) VALUES (44, 0, 'ysUORUetuQ', '8990397154', 'Java 提供了一个庞大的 标准类库（Java API），支持网络通信、多线程、数据结构、数据库连接、GUI 开发等多种功能。', '', '8990397154', NULL, 0, '2024-11-29 21:10:31', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for video_danmu
-- ----------------------------
DROP TABLE IF EXISTS `video_danmu`;
CREATE TABLE `video_danmu` (
  `danmu_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `video_id` varchar(18) NOT NULL COMMENT '视频ID',
  `file_id` varchar(20) NOT NULL COMMENT '唯一ID',
  `user_id` varchar(15) NOT NULL COMMENT '用户ID',
  `post_time` datetime DEFAULT NULL COMMENT '发布时间',
  `text` varchar(300) DEFAULT NULL COMMENT '内容',
  `mode` tinyint(1) DEFAULT NULL COMMENT '展示位置',
  `color` varchar(10) DEFAULT NULL COMMENT '颜色',
  `time` int DEFAULT NULL COMMENT '展示时间',
  PRIMARY KEY (`danmu_id`) USING BTREE,
  KEY `idx_file_id` (`file_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频弹幕';

-- ----------------------------
-- Records of video_danmu
-- ----------------------------
BEGIN;
INSERT INTO `video_danmu` (`danmu_id`, `video_id`, `file_id`, `user_id`, `post_time`, `text`, `mode`, `color`, `time`) VALUES (23, 'WMzrMh0yru', 'HI7mRuzahrCaUnddv7E6', '8990397154', '2024-11-27 16:21:32', '23', 0, '#FFFFFF', 0);
INSERT INTO `video_danmu` (`danmu_id`, `video_id`, `file_id`, `user_id`, `post_time`, `text`, `mode`, `color`, `time`) VALUES (24, 'WMzrMh0yru', 'HI7mRuzahrCaUnddv7E6', '8990397154', '2024-11-27 16:21:42', '我是帅哥', 0, '#FFFFFF', 0);
INSERT INTO `video_danmu` (`danmu_id`, `video_id`, `file_id`, `user_id`, `post_time`, `text`, `mode`, `color`, `time`) VALUES (25, 'WMzrMh0yru', 'HI7mRuzahrCaUnddv7E6', '8990397154', '2024-11-27 16:23:46', '好大的集装箱', 0, '#CC0273', 3);
INSERT INTO `video_danmu` (`danmu_id`, `video_id`, `file_id`, `user_id`, `post_time`, `text`, `mode`, `color`, `time`) VALUES (26, 'ysUORUetuQ', 'H9hbwLpT5nKYJyHKjpDS', '8990397154', '2024-11-29 19:36:41', '第一条弹幕！', 0, '#FFFFFF', 4);
INSERT INTO `video_danmu` (`danmu_id`, `video_id`, `file_id`, `user_id`, `post_time`, `text`, `mode`, `color`, `time`) VALUES (27, 'ysUORUetuQ', 'c0vKjcXENVE1YEx5UsQ1', '8990397154', '2024-11-29 19:37:05', '太帅了吧！', 0, '#CC0273', 3);
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
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('C7UmsmRwae', 'cover/20241124/FLDNT6kZzpT3RS0HVM1nSpBb5YUvBB.png', 'clock_1', '8990397154', '2024-11-24 20:26:58', '2024-11-24 20:26:58', 37, 40, 0, NULL, 'clock_1', NULL, NULL, 10, 0, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('hHszQZ8ER0', 'cover/20241126/8d7x58buWysgfwkHebYwCRqVWkAW1B.png', '少儿编程所有结局!', '8990397154', '2024-11-26 16:51:10', '2024-11-26 16:51:10', 41, 42, 0, NULL, '编程,骗局', '你还相信少儿编程吗？', '0', 167, 0, 2, 0, 0, 1, 2, 0, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('KUnEwdoRB1', 'cover/20241126/W61JR1sXPHpHXwXf733VonafhxKhjh.png', '方尖碑旁的家庭', '8990397154', '2024-11-26 15:29:40', '2024-11-26 15:29:40', 44, 45, 0, NULL, '阿根廷,人文风光', '阿根廷 布宜诺斯艾利斯 方尖碑旁的家庭', NULL, 20, 0, 0, 0, 0, 0, 0, 1, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('McpnUhepmW', 'cover/20241129/CSPJ5jGeIlZFIllyfq7tJpbvUuSr07.png', '浅显易懂，5分钟搞懂什么是机器学习 - 001 - 浅显易懂，5分钟搞懂什么是机器学习', '8990397154', '2024-11-29 22:43:13', '2024-11-29 22:43:13', 37, 39, 0, NULL, '机器学习,人工智能,ai', '带你五分钟搞懂机器学习！', NULL, 312, 0, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('Pqo91imcmB', 'cover/20241129/yLv4dBkMh7TQoEkZUnvdx0kNnEpyBI.png', '20分钟学完一遍python基础 - 001 - 20分钟学完一遍python基础', '8990397154', '2024-11-29 22:44:34', '2024-11-29 22:44:34', 41, 42, 0, NULL, 'Python,编程,教程', '带你二十分钟过一遍python基础！', NULL, 1252, 0, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('TwICf5CQOP', 'cover/20241129/mgkpjPQvMPX8wNUUY7J5nJhnx92lxy.png', '像18岁那年一样穷游旅行!', '8990397154', '2024-11-29 22:39:17', '2024-11-29 22:39:17', 44, 45, 0, NULL, '旅行,风景,日本,摄影', '像18岁那年一样穷游旅行!想当年刚刚留学的我，最开心的事就是放寒暑假的时候，买上一张青春18的车票，跟朋友们一站一站坐着最慢的电车摇到目的地。我永远忘不了第一次去北海道的时候，我乘坐在驶向美瑛站的电车上，电车飞驰而过，压过铁道发出有规律的旋律，窗外的飞雪随风飘向后方\r\n\r\n自从有了车之后，我基本上就告别了电车旅行。带来更多便利的同时，也失去了通过只有电车才能看到的窗外的悠然风景。\r\n\r\n于是这一次我和苍老师一起，买了一张青春18的车票，带上vivo X100 Ultra开始了像18岁那年一样的穷游旅行。敬请收看关于青春18的旅行———夏季篇。\r\n\r\n\r\n关于青春18：\r\n是JR铁道公司在每年春季，夏季，冬季推出的一种车票。这是一张一日票，任何人，无论年龄大小，购买此票都可以可让无限次乘坐 JR 全国线的普通列车和特急列车。一张票可以使用五天。可以用相对便宜的价格去往远方旅行。缺点是只能坐最慢的车，比较花时间，像极了我们18岁时的旅行方式。', '0', 1111, 0, 0, 0, 0, 0, 0, 1, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('WMzrMh0yru', 'cover/20241126/AEIPYuR2JkZPQl2yDqhkh5UO8whhWp.png', '集装箱', '8990397154', '2024-11-26 14:45:07', '2024-11-26 14:45:07', 41, 43, 0, NULL, '13', NULL, NULL, 5, 0, 1, 3, 0, 0, 1, 1, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('yitbioeqj4', 'cover/20241129/bwzG72qe79i1FkFYGXdSd9bxp8fNRu.png', '【线代应用】学线性代数到底有什么用？ - 001 - 【线代应用】学线性代数到底有什么用？', '8990397154', '2024-11-29 22:45:53', '2024-11-29 22:45:53', 37, 40, 0, NULL, '线性代数,Linear algebra', '线性代数的作用到底是什么呢？一起来研究吧', NULL, 540, 0, 0, 0, 0, 0, 0, 1, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('ysUORUetuQ', 'cover/20241126/XN4js9LRrHvIBwNZHiQg6ig7bgwiXb.png', '来看看java程序员的帅气操作吧！', '8990397154', '2024-11-26 15:41:38', '2024-11-26 15:41:38', 41, 43, 0, NULL, 'Java,编程,代码', '欣赏Java代码吧！', NULL, 24, 0, 0, 2, 18, 0, 0, 1, NULL);
INSERT INTO `video_info` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`, `play_count`, `like_count`, `danmu_count`, `comment_count`, `coin_count`, `collect_count`, `recommend_type`, `last_play_time`) VALUES ('YuDi8fFrfb', 'cover/20241126/uY3OMIBxY6UzhPlwE8ozGXY4xSoTpV.png', '布宜诺斯艾利斯方尖碑矗立在七月九日的大道上', '8990397154', '2024-11-26 14:59:29', '2024-11-26 14:59:29', 44, 45, 0, NULL, '美景', NULL, '0', 14, 0, 0, 0, 0, 0, 0, 1, NULL);
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
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('c0vKjcXENVE1YEx5UsQ1', '8990397154', 'ysUORUetuQ', 'java编码', 1, 1551846, 'video/20241126/89903971543ugaOi9uYKkrIah', 14);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('dLoihcFj3GGAAacGMFrV', '8990397154', 'hHszQZ8ER0', '少儿编程所有结局', 1, 4461544, 'video/20241126/89903971540yYzq7s5AmCdKsl', 167);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('eBsrUCOGPoCzaxcmJ4Df', '8990397154', 'TwICf5CQOP', '像18岁那年一样穷游旅行!', 1, 95424517, 'video/20241129/8990397154FLGMdN2ruyEChTq', 1111);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('H9hbwLpT5nKYJyHKjpDS', '8990397154', 'ysUORUetuQ', '代码片段', 2, 3472191, 'video/20241126/8990397154aHtevLQwlwRp3hX', 10);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('HI7mRuzahrCaUnddv7E6', '8990397154', 'WMzrMh0yru', 'preview', 1, 1957193, 'video/20241126/8990397154NRKoiY0KqVNfLVb', 5);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('kVGHhOYva3eFD3XsI6G3', '8990397154', 'YuDi8fFrfb', '505803_buenos_aires_plaza_de_la_republica_plaza_Argentina Buenos Aires obelisk stands on July 9 avenue720p5000br', 1, 8832818, 'video/20241126/8990397154n1Nr89sXR9aaGc0', 14);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('nrDCaCiacynepsTk2CuU', '8990397154', 'C7UmsmRwae', 'clock', 1, 5055878, 'video/20241124/8990397154NjtuuOahOLK7Zpo', 10);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('TJaBvQZy77lvHwNkSAHi', '8990397154', 'McpnUhepmW', '浅显易懂，5分钟搞懂什么是机器学习 - 001 - 浅显易懂，5分钟搞懂什么是机器学习', 1, 19123024, 'video/20241129/89903971543e2OWv42DMbkhoe', 312);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('w3eAwUcFNBYLDggsyB7c', '8990397154', 'KUnEwdoRB1', '阿根廷 布宜诺斯艾利斯 方尖碑旁的家庭', 2, 6621817, 'video/20241126/8990397154OVCXQBCfdxnA49c', 10);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('Y4woE990xTbLpsfDSqMh', '8990397154', 'yitbioeqj4', '【线代应用】学线性代数到底有什么用？ - 001 - 【线代应用】学线性代数到底有什么用？', 1, 16045925, 'video/20241129/8990397154nLGrsQ58rSb8kEh', 540);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('zIqEjcpX4aLYC5Ge89Y3', '8990397154', 'KUnEwdoRB1', '方尖碑旁的家庭', 1, 6447721, 'video/20241126/8990397154YIjAz3Yi3Faqrsr', 10);
INSERT INTO `video_info_file` (`file_id`, `user_id`, `video_id`, `file_name`, `file_index`, `file_size`, `file_path`, `duration`) VALUES ('znT0CRlljM87wmXvP7p6', '8990397154', 'Pqo91imcmB', '20分钟学完一遍python基础 - 001 - 20分钟学完一遍python基础', 1, 39522962, 'video/20241129/8990397154aRcQ39HkKcxdHsn', 1252);
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
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('c0vKjcXENVE1YEx5UsQ1', '3ugaOi9uYKkrIah', '8990397154', 'ysUORUetuQ', 1, 'java编码', 1551846, 'video/20241126/89903971543ugaOi9uYKkrIah', 0, 1, 14);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('dLoihcFj3GGAAacGMFrV', '0yYzq7s5AmCdKsl', '8990397154', 'hHszQZ8ER0', 1, '少儿编程所有结局', 4461544, 'video/20241126/89903971540yYzq7s5AmCdKsl', 0, 1, 167);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('eBsrUCOGPoCzaxcmJ4Df', 'FLGMdN2ruyEChTq', '8990397154', 'TwICf5CQOP', 1, '像18岁那年一样穷游旅行!', 95424517, 'video/20241129/8990397154FLGMdN2ruyEChTq', 0, 1, 1111);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('H9hbwLpT5nKYJyHKjpDS', 'aHtevLQwlwRp3hX', '8990397154', 'ysUORUetuQ', 2, '代码片段', 3472191, 'video/20241126/8990397154aHtevLQwlwRp3hX', 0, 1, 10);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('HI7mRuzahrCaUnddv7E6', 'NRKoiY0KqVNfLVb', '8990397154', 'WMzrMh0yru', 1, 'preview', 1957193, 'video/20241126/8990397154NRKoiY0KqVNfLVb', 0, 1, 5);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('kVGHhOYva3eFD3XsI6G3', 'n1Nr89sXR9aaGc0', '8990397154', 'YuDi8fFrfb', 1, '505803_buenos_aires_plaza_de_la_republica_plaza_Argentina Buenos Aires obelisk stands on July 9 avenue720p5000br', 8832818, 'video/20241126/8990397154n1Nr89sXR9aaGc0', 0, 1, 14);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('kzIkFs8HkUwlzgdBwTlF', 'v0EnqqzlWMI0btg', '8990397154', 'FprmMY3cNK', 1, 'preview', 1957193, 'video/20241125/8990397154v0EnqqzlWMI0btg', 0, 1, 5);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('nrDCaCiacynepsTk2CuU', 'NjtuuOahOLK7Zpo', '8990397154', 'C7UmsmRwae', 1, 'clock', 5055878, 'video/20241124/8990397154NjtuuOahOLK7Zpo', 0, 1, 10);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('TJaBvQZy77lvHwNkSAHi', '3e2OWv42DMbkhoe', '8990397154', 'McpnUhepmW', 1, '浅显易懂，5分钟搞懂什么是机器学习 - 001 - 浅显易懂，5分钟搞懂什么是机器学习', 19123024, 'video/20241129/89903971543e2OWv42DMbkhoe', 0, 1, 312);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('w3eAwUcFNBYLDggsyB7c', 'OVCXQBCfdxnA49c', '8990397154', 'KUnEwdoRB1', 2, '阿根廷 布宜诺斯艾利斯 方尖碑旁的家庭', 6621817, 'video/20241126/8990397154OVCXQBCfdxnA49c', 0, 1, 10);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('Y4woE990xTbLpsfDSqMh', 'nLGrsQ58rSb8kEh', '8990397154', 'yitbioeqj4', 1, '【线代应用】学线性代数到底有什么用？ - 001 - 【线代应用】学线性代数到底有什么用？', 16045925, 'video/20241129/8990397154nLGrsQ58rSb8kEh', 0, 1, 540);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('zIqEjcpX4aLYC5Ge89Y3', 'YIjAz3Yi3Faqrsr', '8990397154', 'KUnEwdoRB1', 1, '方尖碑旁的家庭', 6447721, 'video/20241126/8990397154YIjAz3Yi3Faqrsr', 0, 1, 10);
INSERT INTO `video_info_file_post` (`file_id`, `upload_id`, `user_id`, `video_id`, `file_index`, `file_name`, `file_size`, `file_path`, `update_type`, `transfer_result`, `duration`) VALUES ('znT0CRlljM87wmXvP7p6', 'aRcQ39HkKcxdHsn', '8990397154', 'Pqo91imcmB', 1, '20分钟学完一遍python基础 - 001 - 20分钟学完一遍python基础', 39522962, 'video/20241129/8990397154aRcQ39HkKcxdHsn', 0, 1, 1252);
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
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('C7UmsmRwae', 'cover/20241124/FLDNT6kZzpT3RS0HVM1nSpBb5YUvBB.png', 'clock_1', '8990397154', '2024-11-24 20:26:58', '2024-11-24 20:26:58', 37, 40, 3, 0, NULL, 'clock_1', NULL, NULL, 10);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('FprmMY3cNK', 'cover/20241125/9s0IGwZBRCimEZ8HyYnIx2JJOxgjXh.png', '集装箱', '8990397154', '2024-11-25 17:02:45', '2024-11-25 17:02:45', 41, 43, 4, 0, NULL, 'sea', '集装箱', '1', 5);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('hHszQZ8ER0', 'cover/20241126/8d7x58buWysgfwkHebYwCRqVWkAW1B.png', '少儿编程所有结局!', '8990397154', '2024-11-26 16:51:10', '2024-11-26 16:51:10', 41, 42, 3, 0, NULL, '编程,骗局', '你还相信少儿编程吗？', '0', 167);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('KUnEwdoRB1', 'cover/20241126/W61JR1sXPHpHXwXf733VonafhxKhjh.png', '方尖碑旁的家庭', '8990397154', '2024-11-26 15:29:40', '2024-11-26 15:29:40', 44, 45, 3, 0, NULL, '阿根廷,人文风光', '阿根廷 布宜诺斯艾利斯 方尖碑旁的家庭', NULL, 20);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('McpnUhepmW', 'cover/20241129/CSPJ5jGeIlZFIllyfq7tJpbvUuSr07.png', '浅显易懂，5分钟搞懂什么是机器学习 - 001 - 浅显易懂，5分钟搞懂什么是机器学习', '8990397154', '2024-11-29 22:43:13', '2024-11-29 22:43:13', 37, 39, 3, 0, NULL, '机器学习,人工智能,ai', '带你五分钟搞懂机器学习！', NULL, 312);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('Pqo91imcmB', 'cover/20241129/yLv4dBkMh7TQoEkZUnvdx0kNnEpyBI.png', '20分钟学完一遍python基础 - 001 - 20分钟学完一遍python基础', '8990397154', '2024-11-29 22:44:34', '2024-11-29 22:44:34', 41, 42, 3, 0, NULL, 'Python,编程,教程', '带你二十分钟过一遍python基础！', NULL, 1252);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('TwICf5CQOP', 'cover/20241129/mgkpjPQvMPX8wNUUY7J5nJhnx92lxy.png', '像18岁那年一样穷游旅行!', '8990397154', '2024-11-29 22:39:17', '2024-11-29 22:39:17', 44, 45, 3, 0, NULL, '旅行,风景,日本,摄影', '像18岁那年一样穷游旅行!想当年刚刚留学的我，最开心的事就是放寒暑假的时候，买上一张青春18的车票，跟朋友们一站一站坐着最慢的电车摇到目的地。我永远忘不了第一次去北海道的时候，我乘坐在驶向美瑛站的电车上，电车飞驰而过，压过铁道发出有规律的旋律，窗外的飞雪随风飘向后方\r\n\r\n自从有了车之后，我基本上就告别了电车旅行。带来更多便利的同时，也失去了通过只有电车才能看到的窗外的悠然风景。\r\n\r\n于是这一次我和苍老师一起，买了一张青春18的车票，带上vivo X100 Ultra开始了像18岁那年一样的穷游旅行。敬请收看关于青春18的旅行———夏季篇。\r\n\r\n\r\n关于青春18：\r\n是JR铁道公司在每年春季，夏季，冬季推出的一种车票。这是一张一日票，任何人，无论年龄大小，购买此票都可以可让无限次乘坐 JR 全国线的普通列车和特急列车。一张票可以使用五天。可以用相对便宜的价格去往远方旅行。缺点是只能坐最慢的车，比较花时间，像极了我们18岁时的旅行方式。', '0', 1111);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('WMzrMh0yru', 'cover/20241126/AEIPYuR2JkZPQl2yDqhkh5UO8whhWp.png', '集装箱', '8990397154', '2024-11-26 14:45:07', '2024-11-26 14:45:07', 41, 43, 3, 0, NULL, '13', NULL, NULL, 5);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('yitbioeqj4', 'cover/20241129/bwzG72qe79i1FkFYGXdSd9bxp8fNRu.png', '【线代应用】学线性代数到底有什么用？ - 001 - 【线代应用】学线性代数到底有什么用？', '8990397154', '2024-11-29 22:45:53', '2024-11-29 22:45:53', 37, 40, 3, 0, NULL, '线性代数,Linear algebra', '线性代数的作用到底是什么呢？一起来研究吧', NULL, 540);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('ysUORUetuQ', 'cover/20241126/XN4js9LRrHvIBwNZHiQg6ig7bgwiXb.png', '来看看java程序员的帅气操作吧！', '8990397154', '2024-11-26 15:41:38', '2024-11-26 15:41:38', 41, 43, 3, 0, NULL, 'Java,编程,代码', '欣赏Java代码吧！', NULL, 24);
INSERT INTO `video_info_post` (`video_id`, `video_cover`, `video_name`, `user_id`, `create_time`, `last_update_time`, `p_category_id`, `category_id`, `status`, `post_type`, `origin_info`, `tags`, `introduction`, `interaction`, `duration`) VALUES ('YuDi8fFrfb', 'cover/20241126/uY3OMIBxY6UzhPlwE8ozGXY4xSoTpV.png', '布宜诺斯艾利斯方尖碑矗立在七月九日的大道上', '8990397154', '2024-11-26 14:59:29', '2024-11-26 14:59:29', 44, 45, 3, 0, NULL, '美景', NULL, '0', 14);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
