/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : ez4webcast

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 11/12/2019 11:40:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bbs_floor_inf
-- ----------------------------
DROP TABLE IF EXISTS `bbs_floor_inf`;
CREATE TABLE `bbs_floor_inf`  (
  `floor_id` int(11) NOT NULL,
  `floor_number` int(11) NOT NULL,
  `floor_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `post_id` int(11) NOT NULL,
  `create_uid` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`floor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bbs_post_inf
-- ----------------------------
DROP TABLE IF EXISTS `bbs_post_inf`;
CREATE TABLE `bbs_post_inf`  (
  `post_id` int(11) NOT NULL,
  `post_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int(11) NOT NULL,
  `create_uid` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL,
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `room_id`(`room_id`) USING BTREE,
  INDEX `update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bbs_reply_inf
-- ----------------------------
DROP TABLE IF EXISTS `bbs_reply_inf`;
CREATE TABLE `bbs_reply_inf`  (
  `reply_id` int(11) NOT NULL,
  `reply_content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `reply_object_id` int(11) NULL DEFAULT NULL,
  `floor_id` int(11) NOT NULL,
  `create_uid` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`reply_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for image_inf
-- ----------------------------
DROP TABLE IF EXISTS `image_inf`;
CREATE TABLE `image_inf`  (
  `image_id` int(11) NOT NULL,
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `image_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `image_upload_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`image_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission_inf
-- ----------------------------
DROP TABLE IF EXISTS `permission_inf`;
CREATE TABLE `permission_inf`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_inf
-- ----------------------------
INSERT INTO `permission_inf` VALUES (1, '/admin/', 'admin', NULL);
INSERT INTO `permission_inf` VALUES (2, '/room/', 'anchor', NULL);
INSERT INTO `permission_inf` VALUES (3, '/', 'common_user', NULL);

-- ----------------------------
-- Table structure for role_inf
-- ----------------------------
DROP TABLE IF EXISTS `role_inf`;
CREATE TABLE `role_inf`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_inf
-- ----------------------------
INSERT INTO `role_inf` VALUES (1, 'common_user');
INSERT INTO `role_inf` VALUES (2, 'anchor');
INSERT INTO `role_inf` VALUES (3, 'admin');

-- ----------------------------
-- Table structure for role_permission_inf
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_inf`;
CREATE TABLE `role_permission_inf`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission_inf
-- ----------------------------
INSERT INTO `role_permission_inf` VALUES (2, 2);
INSERT INTO `role_permission_inf` VALUES (3, 1);

-- ----------------------------
-- Table structure for room_inf
-- ----------------------------
DROP TABLE IF EXISTS `room_inf`;
CREATE TABLE `room_inf`  (
  `room_id` int(11) NOT NULL,
  `room_uid` int(11) NOT NULL,
  `room_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `room_image_id` int(11) NULL DEFAULT NULL,
  `room_create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `room_last_live_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE,
  UNIQUE INDEX `room_uid`(`room_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for room_key_inf
-- ----------------------------
DROP TABLE IF EXISTS `room_key_inf`;
CREATE TABLE `room_key_inf`  (
  `room_id` int(11) NOT NULL,
  `room_key` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`room_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `head_image_id` int(11) NULL DEFAULT NULL,
  `register_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uid`, `email`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES (5, 'Aplus', '2712440261a0f6f469f89aff7acd892b', '695199262@qq.com', NULL, '2019-12-10 01:00:05');

-- ----------------------------
-- Table structure for user_role_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_role_inf`;
CREATE TABLE `user_role_inf`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_inf
-- ----------------------------
INSERT INTO `user_role_inf` VALUES (1, 1);
INSERT INTO `user_role_inf` VALUES (1, 2);
INSERT INTO `user_role_inf` VALUES (1, 3);

SET FOREIGN_KEY_CHECKS = 1;
