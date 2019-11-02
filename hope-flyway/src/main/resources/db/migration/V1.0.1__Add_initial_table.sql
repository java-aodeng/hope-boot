#hope数据库
SET FOREIGN_KEY_CHECKS=0;
#资源表
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，资源表',
  `resourceId` varchar(20) NOT NULL COMMENT '扩展资源id',
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '权限访问路径',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `parentId` int(11) DEFAULT NULL COMMENT '父编号',
  `type` int(1) DEFAULT NULL COMMENT '资源类型:0目录 1菜单 2按钮',
  `priority` int(3) DEFAULT NULL COMMENT '显示顺序',
  `icon` varchar(50) DEFAULT '0' COMMENT '图标',
  `status` int(1) NOT NULL COMMENT '是否可用:1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1032 DEFAULT CHARSET=utf8;
INSERT INTO `sys_resource` VALUES ('1', '1', '主页', '主页', ':请', ':权', '0', '0', '1', 'fa fa-home', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('2', '2', '权限管理', '权限管理', ':求', ':限', '0', '0', '2', 'fa fa-object-ungroup', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('3', '3', '系统管理', '系统管理', ':路', ':标', '0', '0', '3', 'fa fa-firefox', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('4', '4', '系统工具', '系统工具', ':径', ':识', '0', '0', '4', 'fa fa-cogs', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('100', '100', 'hope-boot', 'hope-boot', '/index', 'index', '1', '1', '1', '', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('101', '101', '低调小熊猫', '低调小熊猫博客', '/http://ilovey.live', 'blogs', '1', '1', '1', '', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('102', '102', '用户管理', '用户管理', '/user/user', 'user:user:view', '2', '1', '1', '', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('103', '103', '角色管理', '角色管理', '/roles', 'roles', '2', '1', '2', '', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('104', '104', '资源管理', '资源管理', '/resources', 'resources', '2', '1', '3', '', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('105', '105', '在线用户', '在线用户', '/onlineusers', 'onlineusers', '3', '1', '4', '', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('106', '106', '数据监控', '数据监控', '/databases', 'databases', '4', '1', '1', '', '1', '2018-07-06 15:19:55', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('107', '107', '图标工具', '图标工具', '/icons', 'icons', '4', '1', '1', '', '1', '2018-11-18 15:39:00', '2018-11-18 15:39:00');
INSERT INTO `sys_resource` VALUES ('1000', '1000', '用户查询', '用户列表查询', '/user/list', 'user:list', '102', '2', '0', null, '1', '2018-11-18 15:09:24', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1001', '1001', '用户新增', '用户新增', '/user/add', 'user:add', '102', '2', '0', null, '1', '2018-11-18 15:06:50', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1002', '1002', '用户编辑', '用户编辑', '/user/edit', 'user:edit', '102', '2', '0', null, '1', '2018-11-18 15:08:03', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1003', '1003', '用户删除', '用户删除', '/user/delete', 'user:delete', '102', '2', '0', null, '1', '2018-11-18 15:08:42', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1004', '1004', '用户分配角色', '用户分配角色', '/user/saveUserRoles', 'user:saveUserRoles', '102', '2', '0', '', '1', '2018-07-11 01:53:09', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1005', '1005', '重置密码', '重置密码', '/user/resetPwd', 'user:resetPwd', '102', '2', '0', null, '1', '2018-11-18 15:09:24', '2018-11-18 15:09:24');
INSERT INTO `sys_resource` VALUES ('1006', '1006', '角色查询', '角色列表查询', '/role/list', 'role:list', '103', '2', '0', null, '1', '2018-11-18 15:31:36', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1007', '1007', '角色新增', '新增角色', '/role/add', 'role:add', '103', '2', '0', null, '1', '2018-11-18 14:39:46', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1008', '1008', '角色编辑', '编辑角色', '/role/edit', 'role:edit', '103', '2', '0', null, '1', '2018-11-18 14:40:15', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1009', '1009', '角色删除', '删除角色', '/role/delete', 'role:delete', '103', '2', '0', null, '1', '2018-11-18 14:40:57', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1010', '1010', '角色分配资源', '角色分配资源', '/role/assign', 'role:assign', '103', '2', '0', '', '1', '2018-11-18 22:20:43', '2018-11-18 22:20:43');
INSERT INTO `sys_resource` VALUES ('1011', '1011', '资源查询', '资源查询', '/resource/list', 'resource:list', '104', '2', '0', null, '1', '2018-11-18 16:25:28', '2018-11-18 16:25:33');
INSERT INTO `sys_resource` VALUES ('1012', '1012', '资源新增', '资源新增', '/resource/add', 'resource:add', '104', '2', '0', null, '1', '2018-11-18 08:06:58', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1013', '1013', '资源编辑', '资源编辑', '/resource/edit', 'resource:edit', '104', '2', '0', null, '1', '2018-11-18 21:29:04', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1014', '1014', '资源删除', '资源删除', '/resource/delete', 'resource:delete', '104', '2', '0', null, '1', '2018-11-18 21:29:50', '2018-11-18 10:53:14');
INSERT INTO `sys_resource` VALUES ('1015', '1015', '在线用户查询', '在线用户查询', '/onlineuser/list', 'onlineuser:list', '105', '2', '0', null, '1', '2018-11-18 21:01:25', '2018-11-18 12:48:04');
INSERT INTO `sys_resource` VALUES ('1016', '1016', '踢出用户', '踢出用户', '/onlineuser/kickout', 'onlineuser:kickout', '105', '2', '0', null, '1', '2018-11-18 21:41:54', '2018-11-18 12:48:25');
INSERT INTO `sys_resource` VALUES ('1017', '1017', '批量踢出', '批量踢出', '/onlineuser/batchkickout', 'onlineuser:batchkickout', '105', '2', '0', '', '1', '2018-11-18 12:49:30', '2018-11-18 12:49:30');
INSERT INTO `sys_resource` VALUES ('1027', 'c6a93d7', '测试目录', '', '', '', '0', '0', '7', '', '1', '2018-12-26 20:55:17', '2018-12-26 20:55:17');
INSERT INTO `sys_resource` VALUES ('1031', '9ab907b', '测试', '', '', '1', '1027', '2', '1', '', '1', '2018-12-27 21:11:13', '2018-12-27 21:11:13');
#角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，角色表',
  `roleId` varchar(20) NOT NULL COMMENT '扩展角色id',
  `role` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(100) NOT NULL COMMENT '角色描述',
  `status` int(1) NOT NULL COMMENT '是否可用：1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
INSERT INTO `sys_role` VALUES ('1', '1', '超级管理员', '超级管理员', '1', '2018-09-18 14:00:02', '2018-09-18 14:00:02');
INSERT INTO `sys_role` VALUES ('37', 'b3e9e9b', '测试角色', '用来测试的角色', '1', null, '2018-12-27 21:20:32');
#角色-资源关联表
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，角色-资源关联表',
  `roleId` varchar(20) NOT NULL COMMENT '角色id',
  `resourceId` varchar(20) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=395 DEFAULT CHARSET=utf8;
INSERT INTO `sys_role_resource` VALUES ('236', '1', '1');
INSERT INTO `sys_role_resource` VALUES ('237', '1', '100');
INSERT INTO `sys_role_resource` VALUES ('238', '1', '101');
INSERT INTO `sys_role_resource` VALUES ('239', '1', '2');
INSERT INTO `sys_role_resource` VALUES ('240', '1', '102');
INSERT INTO `sys_role_resource` VALUES ('241', '1', '1000');
INSERT INTO `sys_role_resource` VALUES ('242', '1', '1001');
INSERT INTO `sys_role_resource` VALUES ('243', '1', '1002');
INSERT INTO `sys_role_resource` VALUES ('244', '1', '1003');
INSERT INTO `sys_role_resource` VALUES ('245', '1', '1004');
INSERT INTO `sys_role_resource` VALUES ('246', '1', '1005');
INSERT INTO `sys_role_resource` VALUES ('247', '1', '103');
INSERT INTO `sys_role_resource` VALUES ('248', '1', '1006');
INSERT INTO `sys_role_resource` VALUES ('249', '1', '1007');
INSERT INTO `sys_role_resource` VALUES ('250', '1', '1008');
INSERT INTO `sys_role_resource` VALUES ('251', '1', '1009');
INSERT INTO `sys_role_resource` VALUES ('252', '1', '1010');
INSERT INTO `sys_role_resource` VALUES ('253', '1', '104');
INSERT INTO `sys_role_resource` VALUES ('254', '1', '1011');
INSERT INTO `sys_role_resource` VALUES ('255', '1', '1012');
INSERT INTO `sys_role_resource` VALUES ('256', '1', '1013');
INSERT INTO `sys_role_resource` VALUES ('257', '1', '1014');
INSERT INTO `sys_role_resource` VALUES ('258', '1', '3');
INSERT INTO `sys_role_resource` VALUES ('259', '1', '105');
INSERT INTO `sys_role_resource` VALUES ('260', '1', '1015');
INSERT INTO `sys_role_resource` VALUES ('261', '1', '1016');
INSERT INTO `sys_role_resource` VALUES ('262', '1', '1017');
INSERT INTO `sys_role_resource` VALUES ('263', '1', '4');
INSERT INTO `sys_role_resource` VALUES ('264', '1', '106');
INSERT INTO `sys_role_resource` VALUES ('265', '1', '107');
INSERT INTO `sys_role_resource` VALUES ('270', '2', '1027');
INSERT INTO `sys_role_resource` VALUES ('271', '2', '1028');
INSERT INTO `sys_role_resource` VALUES ('336', '14', '1');
INSERT INTO `sys_role_resource` VALUES ('337', '14', '100');
INSERT INTO `sys_role_resource` VALUES ('338', '14', '101');
INSERT INTO `sys_role_resource` VALUES ('339', '14', '2');
INSERT INTO `sys_role_resource` VALUES ('340', '14', '102');
INSERT INTO `sys_role_resource` VALUES ('341', '14', '1000');
INSERT INTO `sys_role_resource` VALUES ('342', '14', '1001');
INSERT INTO `sys_role_resource` VALUES ('343', '14', '1002');
INSERT INTO `sys_role_resource` VALUES ('344', '14', '1003');
INSERT INTO `sys_role_resource` VALUES ('345', '14', '1004');
INSERT INTO `sys_role_resource` VALUES ('346', '14', '1005');
INSERT INTO `sys_role_resource` VALUES ('347', '14', '103');
INSERT INTO `sys_role_resource` VALUES ('348', '14', '1006');
INSERT INTO `sys_role_resource` VALUES ('349', '14', '1007');
INSERT INTO `sys_role_resource` VALUES ('350', '14', '1008');
INSERT INTO `sys_role_resource` VALUES ('351', '14', '1009');
INSERT INTO `sys_role_resource` VALUES ('352', '14', '1010');
INSERT INTO `sys_role_resource` VALUES ('353', '14', '104');
INSERT INTO `sys_role_resource` VALUES ('354', '14', '1011');
INSERT INTO `sys_role_resource` VALUES ('355', '14', '1012');
INSERT INTO `sys_role_resource` VALUES ('356', '14', '1013');
INSERT INTO `sys_role_resource` VALUES ('357', '14', '1014');
INSERT INTO `sys_role_resource` VALUES ('358', '14', '3');
INSERT INTO `sys_role_resource` VALUES ('359', '14', '105');
INSERT INTO `sys_role_resource` VALUES ('360', '14', '1015');
INSERT INTO `sys_role_resource` VALUES ('361', '14', '1016');
INSERT INTO `sys_role_resource` VALUES ('362', '14', '1017');
INSERT INTO `sys_role_resource` VALUES ('363', '14', '4');
INSERT INTO `sys_role_resource` VALUES ('364', '14', '106');
INSERT INTO `sys_role_resource` VALUES ('365', '14', '107');
INSERT INTO `sys_role_resource` VALUES ('381', '37', '2');
INSERT INTO `sys_role_resource` VALUES ('382', '37', '102');
INSERT INTO `sys_role_resource` VALUES ('383', '37', '1000');
INSERT INTO `sys_role_resource` VALUES ('384', '37', '103');
INSERT INTO `sys_role_resource` VALUES ('385', '37', '1006');
INSERT INTO `sys_role_resource` VALUES ('386', '37', '104');
INSERT INTO `sys_role_resource` VALUES ('387', '37', '1011');
INSERT INTO `sys_role_resource` VALUES ('388', '37', '3');
INSERT INTO `sys_role_resource` VALUES ('389', '37', '105');
INSERT INTO `sys_role_resource` VALUES ('390', '37', '1015');
INSERT INTO `sys_role_resource` VALUES ('391', '37', '4');
INSERT INTO `sys_role_resource` VALUES ('392', '37', '106');
INSERT INTO `sys_role_resource` VALUES ('393', '37', '1027');
INSERT INTO `sys_role_resource` VALUES ('394', '37', '1031');
#用户表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键,用户表',
  `userId` varchar(20) NOT NULL COMMENT '扩展用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `sex` int(1) DEFAULT NULL COMMENT '性别：1男2女',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(1) NOT NULL COMMENT '用户状态：1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', 'TeRARkF3rak6MokqdtwS5g==', '8cd50474d2a3c1e88298e91df8bf6f1c', '523179414@qq.com', '187888899991', '1', '22', '1', '2018-09-18 14:00:02', '2018-09-18 14:00:02', '2019-03-22 18:35:10');
INSERT INTO `sys_user` VALUES ('2', '2', '女测试', 'eedqXGyUJwa/dFCLOZ+IYg==', '8cd50474d2a3c1e88298e91df8bf6f1c', '5231794143@qq.com', '1878888999913', '0', '233', '1', '2018-09-18 14:00:02', '2018-12-31 13:33:01', '2018-12-31 15:14:01');
#用户角色关联表
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，用户-角色关联表',
  `userId` varchar(20) NOT NULL COMMENT '用户id',
  `roleId` varchar(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('17', '2', '37');
INSERT INTO `sys_user_role` VALUES ('22', '3', '');