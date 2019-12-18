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

 Date: 18/12/2019 14:15:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bbs_floor_inf
-- ----------------------------
DROP TABLE IF EXISTS `bbs_floor_inf`;
CREATE TABLE `bbs_floor_inf`  (
  `floor_id` int(11) NOT NULL AUTO_INCREMENT,
  `floor_number` int(11) NOT NULL,
  `floor_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `post_id` int(11) NOT NULL,
  `create_uid` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`floor_id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bbs_floor_inf
-- ----------------------------
INSERT INTO `bbs_floor_inf` VALUES (1, 1, '买东西先绑定手机安全令，这样以后就方便了,记得淘宝买挂，反正被封后花钱就可以解了，随便开', 1, 5, '2019-12-12 09:33:47', 0);
INSERT INTO `bbs_floor_inf` VALUES (2, 2, '兄弟们，我可能被5e误封号了，联系在线客服让我写邮件过去，现在邮件发过去了，但是看到细则说申述会被忽视…那我该咋办。。之所以说误封是因为我把号借给一老哥玩了一晚上，第二天就登不上去了。我去查了下战绩基本都是负数的，也不像开了挂呀', 1, 5, '2019-12-12 09:45:47', 0);
INSERT INTO `bbs_floor_inf` VALUES (3, 3, '本来也想买一把淬屎但是听到好多人说容易传家', 1, 5, '2019-12-16 16:49:59', 0);
INSERT INTO `bbs_floor_inf` VALUES (4, 4, '等淬火底价上去了才能出，现在原价摆上去都难出', 1, 5, '2019-12-16 16:50:11', 0);
INSERT INTO `bbs_floor_inf` VALUES (5, 5, '卖终于等到了一个有缘人，一班tec9地下水卖一千都说等一个有缘人', 1, 5, '2019-12-16 16:50:20', 0);
INSERT INTO `bbs_floor_inf` VALUES (6, 6, '应该还可以吧，你看前面那么多还价失败的。这东西就是不好出而已，实话说我不敢碰淬火任何饰品。\n', 1, 5, '2019-12-16 16:50:33', 0);
INSERT INTO `bbs_floor_inf` VALUES (7, 1, '只要不碰液体，A队基本上没太大希望，毕竟X9现在拉胯的太厉害。', 2, 5, '2019-12-16 16:52:52', 0);
INSERT INTO `bbs_floor_inf` VALUES (8, 2, 'x9拉跨就全队没了？a队是靠的枪法？先不说枪法，就a队战术你液体能刚得住？？就液体现在那总监，只能全队靠树懒和🐔哥，a队磨男和阿杜不是枪男？？，虽然对于其他职业哥来说，不是最顶级的，但别人枪法也不差好吧，别无脑喷', 2, 5, '2019-12-16 16:54:23', 0);
INSERT INTO `bbs_floor_inf` VALUES (9, 3, '我记得你，就是那个说5e 2300组队刚枪能刚过A队的 \n老A黑了', 2, 5, '2019-12-16 16:54:51', 0);
INSERT INTO `bbs_floor_inf` VALUES (10, 4, '液体可太惨了，连个伪强队都打不过……伪强队都能得4个major', 2, 5, '2019-12-16 16:55:04', 0);
INSERT INTO `bbs_floor_inf` VALUES (11, 1, '那些cs的带妹的奥，不是我说，素质好点\n带不动了不丢人，带不动了4个人骂另一个，是纯纯的SM行为奥，4个憨P YY语音点也不报打你🐴呢', 3, 5, '2019-12-16 17:00:00', 0);
INSERT INTO `bbs_floor_inf` VALUES (12, 2, '没实力，硬装p属实啥也不是', 3, 5, '2019-12-16 17:00:43', 0);

-- ----------------------------
-- Table structure for bbs_post_inf
-- ----------------------------
DROP TABLE IF EXISTS `bbs_post_inf`;
CREATE TABLE `bbs_post_inf`  (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int(11) NOT NULL,
  `create_uid` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `room_id`(`room_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bbs_post_inf
-- ----------------------------
INSERT INTO `bbs_post_inf` VALUES (1, '大家好，既将加入rushb大家庭，新人有什么要注意的吗', 1, 5, '2019-12-12 09:33:47', '2019-12-12 09:33:47', 0);
INSERT INTO `bbs_post_inf` VALUES (2, '今年还有什么大赛么？伪强队A队何时原形毕露。', 1, 5, '2019-12-16 16:52:52', '2019-12-16 16:52:52', 0);
INSERT INTO `bbs_post_inf` VALUES (3, '那些cs的带妹的奥，不是我说，素质好点带不动了不丢人', 1, 5, '2019-12-16 17:00:00', '2019-12-16 17:00:00', 0);

-- ----------------------------
-- Table structure for bbs_reply_inf
-- ----------------------------
DROP TABLE IF EXISTS `bbs_reply_inf`;
CREATE TABLE `bbs_reply_inf`  (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `reply_number` int(11) NOT NULL,
  `reply_content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `reply_object_id` int(11) NULL DEFAULT NULL,
  `floor_id` int(11) NOT NULL,
  `create_uid` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`reply_id`) USING BTREE,
  INDEX `floor_id`(`floor_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bbs_reply_inf
-- ----------------------------
INSERT INTO `bbs_reply_inf` VALUES (1, 1, '负数就是没开挂吗?负数就是没开挂吗?负数就是没开挂吗?负数就是没开挂吗?负数就是没开挂吗?负数就是没开挂吗?负数就是没开挂吗?', NULL, 2, 5, '2019-12-12 09:52:07', '0');
INSERT INTO `bbs_reply_inf` VALUES (2, 1, '液体是总监有伤，你看明年不恢复不', NULL, 10, 5, '2019-12-16 16:56:15', '0');
INSERT INTO `bbs_reply_inf` VALUES (3, 2, '吧宠开心就好', 2, 10, 5, '2019-12-16 16:57:11', '0');

-- ----------------------------
-- Table structure for care_inf
-- ----------------------------
DROP TABLE IF EXISTS `care_inf`;
CREATE TABLE `care_inf`  (
  `care_id` int(11) NOT NULL AUTO_INCREMENT,
  `care_room_id` int(11) NOT NULL,
  `belong_uid` int(11) NOT NULL,
  `care_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`care_id`) USING BTREE,
  INDEX `belong_uid`(`belong_uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for image_inf
-- ----------------------------
DROP TABLE IF EXISTS `image_inf`;
CREATE TABLE `image_inf`  (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `image_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `image_upload_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`image_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image_inf
-- ----------------------------
INSERT INTO `image_inf` VALUES (1, '4b24be77-4f69-459f-8cd0-b4a2889cf28f', 'image/png', '2019-12-16 17:21:53');
INSERT INTO `image_inf` VALUES (2, 'ce54a7f5-6ea4-46ab-a8f0-a161e48cbc4f', 'image/png', '2019-12-18 12:09:45');
INSERT INTO `image_inf` VALUES (3, 'cfb3d087-ba72-4845-9297-3dbe1ca1cc04', 'image/png', '2019-12-18 12:10:24');
INSERT INTO `image_inf` VALUES (4, '41981856-1bce-4066-a65b-3bb0274cb509', 'image/jpeg', '2019-12-18 12:44:57');
INSERT INTO `image_inf` VALUES (5, '122b1df6-480a-4938-b5c7-3e49455b8a71', 'image/jpeg', '2019-12-18 12:46:22');
INSERT INTO `image_inf` VALUES (6, 'e576e4a5-b1de-4977-8a30-c0ac4c09f7b2', 'image/jpeg', '2019-12-18 12:47:03');
INSERT INTO `image_inf` VALUES (7, 'ddbe7499-0457-4071-a49c-9fb9a7e03b5c', 'image/jpeg', '2019-12-18 12:47:30');
INSERT INTO `image_inf` VALUES (8, '1e5d80e2-0465-4dca-96f7-5ac300d246c5', 'image/jpeg', '2019-12-18 12:48:41');

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
INSERT INTO `permission_inf` VALUES (1, '/api/admin/', 'admin', NULL);
INSERT INTO `permission_inf` VALUES (2, '/api/room/', 'anchor', NULL);
INSERT INTO `permission_inf` VALUES (3, '/api/', 'common_user', NULL);

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
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_uid` int(11) NOT NULL,
  `room_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `room_image_id` int(11) NULL DEFAULT NULL,
  `room_create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `room_last_live_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE,
  UNIQUE INDEX `room_uid`(`room_uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_inf
-- ----------------------------
INSERT INTO `room_inf` VALUES (1, 5, 'CSGO直播间', NULL, '2019-12-12 09:30:10', '2019-12-12 09:30:10');
INSERT INTO `room_inf` VALUES (2, 6, '最真实的直播间', 8, '2019-12-18 12:48:41', NULL);

-- ----------------------------
-- Table structure for room_key_inf
-- ----------------------------
DROP TABLE IF EXISTS `room_key_inf`;
CREATE TABLE `room_key_inf`  (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_key` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`room_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_key_inf
-- ----------------------------
INSERT INTO `room_key_inf` VALUES (2, '3558841e-9dd3-458b-a59b-bdc66316aa14', '2019-12-18 13:53:47');

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
  `register_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uid`, `email`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES (5, 'Aplus', '2712440261a0f6f469f89aff7acd892b', '695199262@qq.com', 1, '2019-12-18 02:51:56');
INSERT INTO `user_inf` VALUES (6, '听风说语', 'e99a18c428cb38d5f260853678922e03', 'abc123lzf@126.com', 1, '2019-12-18 02:51:59');

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
