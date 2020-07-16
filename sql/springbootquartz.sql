/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50561
 Source Host           : localhost:3306
 Source Schema         : springbootquartz

 Target Server Type    : MySQL
 Target Server Version : 50561
 File Encoding         : 65001

 Date: 16/07/2020 10:49:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for quartz
-- ----------------------------
DROP TABLE IF EXISTS `quartz`;
CREATE TABLE `quartz`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `class_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '全类名',
  `group_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名',
  `status` int(2) NOT NULL COMMENT '任务状态(0停止，1运行）',
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cron表达式',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of quartz
-- ----------------------------
INSERT INTO `quartz` VALUES (1, 'com.zys.springbootquartz.job.MyJob', 'group1', 0, '0/1 * * * * ?', '测试任务1，一秒出发一次', '2020-07-16 10:15:17');
INSERT INTO `quartz` VALUES (2, 'com.zys.springbootquartz.job.MyJob2', 'group2', 0, '0/3 * * * * ?', '定时任务2,每3秒执行一次', '2020-07-16 10:43:04');

SET FOREIGN_KEY_CHECKS = 1;
