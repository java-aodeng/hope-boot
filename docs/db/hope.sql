/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : hope

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-18 14:38:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，资源表',
  `resourceId` varchar(20) NOT NULL COMMENT '装逼的id',
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '权限访问路径',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `parent_id` int(11) DEFAULT NULL COMMENT '父编号',
  `type` int(1) DEFAULT NULL COMMENT '资源类型:0目录 1菜单 2按钮',
  `priority` int(3) DEFAULT NULL COMMENT '显示顺序',
  `icon` varchar(50) DEFAULT '0' COMMENT '图标',
  `status` int(1) NOT NULL COMMENT '是否可用:1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
INSERT INTO `sys_resource` VALUES ('1', '1', '主页', '主页', '', NULL, '0', '0', '1', 'fa fa-home', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('2', '2', '权限管理', '权限管理', '', null, '0', '0', '2', 'fa fa-th-list', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('3', '3', '系统管理', '系统管理', '', null, '0', '0', '3', 'fa fa-th-list', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('4', '4', '系统工具', '系统工具', '', null, '0', '0', '4', 'fa fa-th-list', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
-- 二级菜单
INSERT INTO `sys_resource` VALUES ('100', '100', 'hope-plus', 'hope-plus', '/index', 'index', '1', '1', '1', 'fa fa-circle-o', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('101', '101', '低调小熊猫', '低调小熊猫博客', '/https://aodeng.cc', 'blogs', '1', '1', '1', 'fa fa-circle-o', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('102', '102', '用户管理', '用户管理', '/users', 'users', '2', '1', '1', 'fa fa-circle-o', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('103', '103', '角色管理', '角色管理', '/roles', 'roles', '2', '1', '2', 'fa fa-circle-o', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('104', '104', '资源管理', '资源管理', '/resources', 'resources', '2', '1', '3', 'fa fa-circle-o', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('105', '105', '在线用户', '在线用户', '/onlineusers', 'onlineusers', '3', '1', '4', 'fa fa-circle-o', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('106', '106', '数据监控', '数据监控', '/databases', 'databases', '4', '1', '1', 'fa fa-circle-o', '1', '2018-07-06 15:19:55', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('107', '107', '图标工具', '图标工具', '/icons', 'icons', '4', '1', '1', 'fa fa-circle-o', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
-- 用户管理按钮
INSERT INTO `sys_resource` VALUES ('1000', '1000', '用户查询', '用户列表查询', '/user/list', 'user:list', '102', '2', '0', null, '1', '2018-11-18 15:09:24', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1001', '1001', '用户新增', '用户新增', '/user/add', 'user:add', '102', '2', '0', null, '1', '2018-11-18 15:06:50', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1002', '1002', '用户编辑', '用户编辑', '/user/edit', 'user:edit', '102', '2', '0', null, '1', '2018-11-18 15:08:03', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1003', '1003', '用户删除', '用户删除', '/user/delete', 'user:delete', '102', '2', '0', null, '1', '2018-11-18 15:08:42', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1004', '1004', '用户导出', '用户导出', '/user/export', 'user:export', '102', '2', '0', '', '1', '2018-07-11 01:53:09', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1005', '1005', '重置密码', '重置密码', '/user/resetPwd', 'user:resetPwd', '102', '2', '0', null, '1', '2018-11-18 15:09:24', '2018-11-18 15:09:24');
-- 角色管理按钮
INSERT INTO `sys_resource` VALUES ('1006', '1006', '角色查询', '角色列表查询', '/role/list', 'role:list', '103', '2', '0', null, '1', '2018-11-18 15:31:36', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1007', '1007', '角色新增', '新增角色', '/role/add', 'role:add', '103', '2', '0', null, '1', '2018-11-18 14:39:46', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1008', '1008', '角色编辑', '编辑角色', '/role/edit', 'role:edit', '103', '2', '0', null, '1', '2018-11-18 14:40:15', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1009', '1009', '角色删除', '删除角色', '/role/delete', 'role:delete', '103', '2', '0', null, '1', '2018-11-18 14:40:57', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1010', '1010', '角色导出', '批量删除角色', '/role/export', 'role:export', '103', '2', '0', '', '1', '2018-11-18 22:20:43', '2018-11-18 22:20:43');
-- 资源管理按钮
INSERT INTO `sys_resource` VALUES ('1011', '1011', '资源查询', '资源查询', '/resource/list', 'resource:list', '104', '2', '0', null, '1', '2018-11-18 16:25:28', '2018-11-18 16:25:33');
INSERT INTO `sys_resource` VALUES ('1012', '1012', '资源新增', '资源新增', '/resource/add', 'resource:add', '104', '2', '0', null, '1', '2018-11-18 08:06:58', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1013', '1013', '资源编辑', '资源编辑', '/resource/edit', 'resource:edit', '104', '2', '0', null, '1', '2018-11-18 21:29:04', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1014', '1014', '资源删除', '资源删除', '/resource/delete', 'resource:delete', '104', '2', '0', null, '1', '2018-11-18 21:29:50', '2018-11-18 10:53:14');
-- 在线用户按钮
INSERT INTO `sys_resource` VALUES ('1015', '1015', '在线用户查询', '在线用户查询', '/onlineuser/list', 'onlineuser:list', '105', '2', '0', null, '1', '2018-11-18 21:01:25', '2018-11-18 12:48:04');
INSERT INTO `sys_resource` VALUES ('1016', '1016', '踢出用户', '踢出用户', '/onlineuser/kickout', 'onlineuser:kickout', '105', '2', '0', null, '1', '2018-11-18 21:41:54', '2018-11-18 12:48:25');
INSERT INTO `sys_resource` VALUES ('1017', '1017', '批量踢出', '批量踢出', '/onlineuser/batchkickout', 'onlineuser:batchkickout', '105', '2', '0', '', '1', '2018-11-18 12:49:30', '2018-11-18 12:49:30');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，角色表',
  `roleId` varchar(20) NOT NULL COMMENT '装逼的id',
  `role` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(100) NOT NULL COMMENT '角色描述',
  `status` int(1) NOT NULL COMMENT '是否可用：1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '超级管理员', '超级管理员', '1', '2018-09-18 14:00:02', '2018-09-18 14:00:02');
INSERT INTO `sys_role` VALUES ('2', '2', '管理员', '管理员', '1', '2018-09-18 14:00:02', '2018-09-18 14:00:02');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，角色-资源关联表',
  `roleId` varchar(20) NOT NULL COMMENT '角色id',
  `resourceId` varchar(20) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '1', '1');
INSERT INTO `sys_role_resource` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键,用户表',
  `userId` varchar(20) NOT NULL COMMENT '装逼的id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `sex` int(1) DEFAULT NULL COMMENT '性别：1男2女3变态',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(1) NOT NULL COMMENT '用户状态：1有效2删除0禁止',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', '872359cc44c637cc73b7cd55c06d95e4', '8cd50474d2a3c1e88298e91df8bf6f1c', '523179414@qq.com', '187888899991', '1', '22', '1', '2018-09-18 14:00:02', '2018-09-18 14:00:02', '2018-09-18 14:00:02');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，用户-角色关联表',
  `userId` varchar(20) NOT NULL COMMENT '用户id',
  `roleId` varchar(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
