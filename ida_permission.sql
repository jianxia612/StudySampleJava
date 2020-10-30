/*
 Navicat Premium Data Transfer

 Source Server         : MySQL3306
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : cloud-ida

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 30/10/2020 08:57:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ida_permission
-- ----------------------------
DROP TABLE IF EXISTS `ida_permission`;
CREATE TABLE `ida_permission`  (
  `permission_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_permission_id` int(11) NULL DEFAULT NULL,
  `permission_lv` int(11) NULL DEFAULT NULL,
  `permission_auth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE,
  UNIQUE INDEX `permission_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ida_permission
-- ----------------------------
INSERT INTO `ida_permission` VALUES (1, '用户管理', NULL, 0, 1, '');
INSERT INTO `ida_permission` VALUES (2, '新增修改', '/user-post', 1, 2, 'upost');
INSERT INTO `ida_permission` VALUES (3, '列表查询', '/user-get', 1, 2, 'uget');
INSERT INTO `ida_permission` VALUES (4, '删除用户', '/user/{id}-delete', 1, 2, 'udelete');
INSERT INTO `ida_permission` VALUES (5, '角色管理', NULL, 0, 1, '');
INSERT INTO `ida_permission` VALUES (6, '新增修改', '/role-post', 5, 2, 'rpost');
INSERT INTO `ida_permission` VALUES (7, '列表查询', '/role-get', 5, 2, 'rget');
INSERT INTO `ida_permission` VALUES (8, '删除角色', '/role/{id}-delete', 5, 2, 'rdelete');
INSERT INTO `ida_permission` VALUES (9, '角色授权', '/role-authorization-post', 5, 2, 'rauth');
INSERT INTO `ida_permission` VALUES (10, '权限管理', NULL, 0, 1, '');
INSERT INTO `ida_permission` VALUES (11, '新增修改', '/permission-post', 10, 2, 'ppost');
INSERT INTO `ida_permission` VALUES (12, '列表查询', '/permission-get', 10, 2, 'pget');
INSERT INTO `ida_permission` VALUES (13, '删除权限', '/permission/{id}-delete', 10, 2, 'pdelete');
INSERT INTO `ida_permission` VALUES (14, '预览权限', '/permission-view-get', 10, 2, 'pview');
INSERT INTO `ida_permission` VALUES (15, '菜单', NULL, 0, 1, '');
INSERT INTO `ida_permission` VALUES (16, '标签取数', '/tag-index', 15, 2, 'tindex');
INSERT INTO `ida_permission` VALUES (17, '模板取数', '/tag-fetch-data', 15, 2, 'tftemplet');
INSERT INTO `ida_permission` VALUES (18, '客户群', '/tag-cluster', 15, 2, 'tcluster');
INSERT INTO `ida_permission` VALUES (19, '常规标签配置', '/tag-simple', 15, 2, 'tsconfig');
INSERT INTO `ida_permission` VALUES (20, '标签工厂', '/tag-factory', 15, 2, 'tfconfig');
INSERT INTO `ida_permission` VALUES (21, 'tag服务权限控制', NULL, 0, 1, '');
INSERT INTO `ida_permission` VALUES (22, '通过id获取用户信息', '/api-tag/tag/getUserById', 21, 2, 'tag:get');

-- ----------------------------
-- Table structure for ida_role
-- ----------------------------
DROP TABLE IF EXISTS `ida_role`;
CREATE TABLE `ida_role`  (
  `role_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ida_role
-- ----------------------------
INSERT INTO `ida_role` VALUES (1, '用户管理员');
INSERT INTO `ida_role` VALUES (2, '角色管理员');
INSERT INTO `ida_role` VALUES (3, '权限管理员');
INSERT INTO `ida_role` VALUES (4, '超级管理员');

-- ----------------------------
-- Table structure for ida_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ida_role_permission`;
CREATE TABLE `ida_role_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `permission_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ida_role_permission
-- ----------------------------
INSERT INTO `ida_role_permission` VALUES (33, 1, 2);
INSERT INTO `ida_role_permission` VALUES (34, 1, 3);
INSERT INTO `ida_role_permission` VALUES (35, 1, 4);
INSERT INTO `ida_role_permission` VALUES (36, 1, 6);
INSERT INTO `ida_role_permission` VALUES (37, 1, 7);
INSERT INTO `ida_role_permission` VALUES (38, 1, 8);
INSERT INTO `ida_role_permission` VALUES (39, 1, 9);
INSERT INTO `ida_role_permission` VALUES (40, 1, 11);
INSERT INTO `ida_role_permission` VALUES (41, 1, 12);
INSERT INTO `ida_role_permission` VALUES (42, 1, 13);
INSERT INTO `ida_role_permission` VALUES (43, 1, 14);
INSERT INTO `ida_role_permission` VALUES (44, 1, 16);
INSERT INTO `ida_role_permission` VALUES (45, 1, 17);
INSERT INTO `ida_role_permission` VALUES (46, 1, 18);
INSERT INTO `ida_role_permission` VALUES (47, 1, 19);
INSERT INTO `ida_role_permission` VALUES (48, 1, 20);
INSERT INTO `ida_role_permission` VALUES (49, 1, 22);
INSERT INTO `ida_role_permission` VALUES (67, 4, 2);
INSERT INTO `ida_role_permission` VALUES (68, 4, 3);
INSERT INTO `ida_role_permission` VALUES (69, 4, 4);
INSERT INTO `ida_role_permission` VALUES (70, 4, 6);
INSERT INTO `ida_role_permission` VALUES (71, 4, 7);
INSERT INTO `ida_role_permission` VALUES (72, 4, 8);
INSERT INTO `ida_role_permission` VALUES (73, 4, 9);
INSERT INTO `ida_role_permission` VALUES (74, 4, 11);
INSERT INTO `ida_role_permission` VALUES (75, 4, 12);
INSERT INTO `ida_role_permission` VALUES (76, 4, 13);
INSERT INTO `ida_role_permission` VALUES (77, 4, 14);
INSERT INTO `ida_role_permission` VALUES (78, 4, 16);
INSERT INTO `ida_role_permission` VALUES (79, 4, 17);
INSERT INTO `ida_role_permission` VALUES (80, 4, 18);
INSERT INTO `ida_role_permission` VALUES (81, 4, 19);
INSERT INTO `ida_role_permission` VALUES (82, 4, 20);
INSERT INTO `ida_role_permission` VALUES (83, 4, 22);

-- ----------------------------
-- Table structure for ida_user
-- ----------------------------
DROP TABLE IF EXISTS `ida_user`;
CREATE TABLE `ida_user`  (
  `user_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_role_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ida_user
-- ----------------------------
INSERT INTO `ida_user` VALUES (1, '许耀辉', '超级管理员', 'admin', '123456');
INSERT INTO `ida_user` VALUES (2, '用户管理员', '用户管理员', 'user', '123456');

-- ----------------------------
-- Table structure for ida_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ida_user_role`;
CREATE TABLE `ida_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ida_user_role
-- ----------------------------
INSERT INTO `ida_user_role` VALUES (1, 1, 4);
INSERT INTO `ida_user_role` VALUES (2, 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
