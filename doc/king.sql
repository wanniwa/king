/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : king

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 10/10/2019 00:32:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `resource_ids`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `client_secret`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `scope`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `authorized_grant_types`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `authorities`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `access_token_validity`   int(10)                                                       NULL DEFAULT NULL,
    `refresh_token_validity`  int(10)                                                       NULL DEFAULT NULL,
    `additional_information`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci         NULL,
    `autoapprove`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details`
VALUES ('client_1', '', '{noop}123456', 'all,read,write', 'client_credentials', '', 'client_credentials', 7200, NULL,
        '{}', '');
INSERT INTO `oauth_client_details`
VALUES ('client_2', '', '{noop}123456', 'all,read,write', 'password,refresh_token', '', 'password', 7200, 10000, '{}',
        '');
INSERT INTO `oauth_client_details`
VALUES ('client_3', '', '{noop}123456', 'all,read,write', 'authorization_code',
        'http://localhost:8080/signin,http://localhost:8080/callback', 'authorization_code,refresh_token', 7200, 10000,
        '{}', '');
INSERT INTO `oauth_client_details`
VALUES ('client_test', '', '{noop}123456', 'all,read,write',
        'all flow,authorization_code,client_credentials,refresh_token,password,implicit',
        'http://localhost:8080/signin,http://localhost:8080/callback', '', 7200, 10000, '{}', '');


-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`          bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '部门ID',
    `name`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '部门名称',
    `parent_id`   bigint(20)                                                    NULL DEFAULT NULL COMMENT '父级编号',
    `parent_ids`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所有父级编号',
    `sort`        int(4)                                                        NULL DEFAULT NULL COMMENT '排序',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `del_flag`    bit(1)                                                        NULL DEFAULT NULL COMMENT '删除状态：0-正常 1-删除',
    `create_time` datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint(20)                                                    NOT NULL COMMENT '菜单ID',
    `name`        varchar(32)                                                   NOT NULL COMMENT '菜单名称',
    `permission`  varchar(32)                                                        DEFAULT NULL COMMENT '菜单权限标识',
    `path`        varchar(128)                                                       DEFAULT NULL COMMENT '前端URL',
    `parent_id`   bigint(20)                                                         DEFAULT NULL COMMENT '父菜单ID',
    `parent_ids`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所有父级编号',
    `icon`        varchar(32)                                                        DEFAULT NULL COMMENT '图标',
    `component`   varchar(255)                                                       DEFAULT NULL COMMENT 'VUE页面',
    `sort`        int(11)                                                            DEFAULT '1' COMMENT '排序值',
    `keep_alive`  char(1)                                                            DEFAULT '0' COMMENT '0-开启，1- 关闭',
    `type`        tinyint(3)                                                    NULL DEFAULT NULL COMMENT '菜单类型：0-目录 1-菜单 2-按钮',
    `del_flag`    bit(1)                                                        NULL DEFAULT NULL COMMENT '删除状态：0-正常 1-删除',
    `create_time` datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表'
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '角色名称',
    `code`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '角色标识',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    `del_flag`    bit(1)                                                        NULL DEFAULT NULL COMMENT '删除状态：0-正常 1-删除',
    `create_time` datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表  '
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单表  '
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '用户名',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
    `salt`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐',
    `phone`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '简介',
    `email`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
    `dept_id`     int(11)                                                       NULL DEFAULT NULL COMMENT '部门ID',
    `is_enabled`  bit(1)                                                        NULL DEFAULT NULL COMMENT '0-未启用，1-启用',
    `is_locked`   bit(1)                                                        NULL DEFAULT NULL COMMENT '0-未锁定 1-锁定',
    `del_flag`    bit(1)                                                        NULL DEFAULT NULL COMMENT '删除状态：0-正常 1-删除',
    `create_time` datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', '{bcrypt}$2a$10$SYjcJB9OZ/kjauwD4n17GeKGY..YX3zPAvb/lSbBJoiZnRfXmzSFW', NULL, NULL, NULL, NULL, 1,
        b'1', b'0', b'0', '2019-10-09 23:37:29', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关系表  '
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
