/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : user_center_server

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 14/04/2020 10:09:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `user_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK 用户id ',
  `country` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '国家',
  `province` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '省',
  `city` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '市',
  `street_address` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '街道地址',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `title` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章标题',
  `content` blob COMMENT '文章内容',
  `user_id` varchar(48) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `article_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章id',
  `tag_name` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签内容JSON',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for register_record
-- ----------------------------
DROP TABLE IF EXISTS `register_record`;
CREATE TABLE `register_record` (
  `id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK id',
  `email` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `captcha` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '验证码',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id主键',
  `password` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(16) COLLATE utf8mb4_general_ci NOT NULL,
  `area_number` char(4) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(16) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_verified` char(1) COLLATE utf8mb4_general_ci DEFAULT '0',
  `phone_number` varchar(16) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_username_index` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for user_preference
-- ----------------------------
DROP TABLE IF EXISTS `user_preference`;
CREATE TABLE `user_preference` (
  `user_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id FK',
  `todo_notice` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否接收待办通知：0不接受，1接收',
  `sys_message_notice` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否接收系统通知：0不接受，1接收',
  `other_user_message_notice` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否接收其他用户信息通知：0不接受，1接收',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `user_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK 用户id',
  `photo` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片路径',
  `personal_profile` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个人简介',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for user_tag
-- ----------------------------
DROP TABLE IF EXISTS `user_tag`;
CREATE TABLE `user_tag` (
  `user_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id FK',
  `tag_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
