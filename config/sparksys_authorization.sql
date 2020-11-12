/*
 Navicat Premium Data Transfer

 Source Server         : 云数据库
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : rm-bp1cp68o8t1gq604p5o.mysql.rds.aliyuncs.com:3306
 Source Schema         : sparksys_authorization

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 12/11/2020 18:09:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_menu`;
CREATE TABLE `c_auth_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'code',
  `describe_` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `is_public` bit(1) DEFAULT b'0' COMMENT '公共菜单\nTrue是无需分配所有人就可以访问的',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '组件',
  `is_enable` bit(1) DEFAULT b'1' COMMENT '状态',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '菜单图标',
  `group_` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '分组',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单ID',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `INX_STATUS` (`is_enable`,`is_public`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='菜单';

-- ----------------------------
-- Records of c_auth_menu
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_menu` VALUES (101, '工作台', 'dashboard-workplace', '首页', b'1', '/dashboard/workplace', 'Workplace', b'1', 1, 'el-icon-user-solid', '', 0, 1, '2019-07-25 15:35:12', 3, '2019-11-11 14:32:02');
INSERT INTO `c_auth_menu` VALUES (102, '用户中心', 'user-center', '用户组织机构', b'0', '/user-center', 'Layout', b'1', 2, 'el-icon-user-solid', '', 0, 1, '2019-07-25 15:35:12', 3, '2019-11-11 14:32:02');
INSERT INTO `c_auth_menu` VALUES (103, '权限管理', 'auth', '管理权限相关', b'0', '/auth', 'Layout', b'1', 3, 'el-icon-lock', '', 0, 1, '2019-07-27 11:48:49', 3, '2019-11-11 14:35:39');
INSERT INTO `c_auth_menu` VALUES (104, '基础配置', 'base', '基础的配置', b'0', '/base', 'Layout', b'1', 4, 'el-icon-set-up', '', 0, 1, '2019-11-11 14:38:29', 3, '2019-11-11 14:35:42');
INSERT INTO `c_auth_menu` VALUES (105, '开发者管理', 'developer', '开发者', b'0', '/developer', 'Layout', b'1', 5, 'el-icon-user-solid', '', 0, 1, '2019-11-11 14:38:34', 3, '2019-11-11 14:35:44');
INSERT INTO `c_auth_menu` VALUES (106, '文件中心', 'file', '附件接口', b'0', '/file', 'Layout', b'1', 7, 'el-icon-folder-add', '', 0, 1, '2019-11-11 14:38:38', 3, '2019-11-11 14:35:51');
INSERT INTO `c_auth_menu` VALUES (603976297063910529, '菜单配置', 'auth-menu', '', b'0', '/auth/menu', 'zuihou/auth/menu/Index', b'1', 0, '', '', 103, 1, '2019-07-25 15:46:11', 3, '2019-11-11 14:31:52');
INSERT INTO `c_auth_menu` VALUES (603981723864141121, '角色管理', 'auth-role', '', b'0', '/auth/role', 'zuihou/auth/role/Index', b'1', 1, '', '', 103, 1, '2019-07-25 16:07:45', 3, '2019-11-11 14:31:57');
INSERT INTO `c_auth_menu` VALUES (603982542332235201, '组织管理', 'user-org', '', b'0', '/user/org', 'zuihou/user/org/Index', b'1', 0, '', '', 102, 1, '2019-07-25 16:11:00', 3, '2019-11-11 14:28:40');
INSERT INTO `c_auth_menu` VALUES (603982713849908801, '岗位管理', 'user-station', '', b'0', '/user/station', 'zuihou/user/station/Index', b'1', 1, '', '', 102, 1, '2019-07-25 16:11:41', 3, '2019-11-11 14:28:43');
INSERT INTO `c_auth_menu` VALUES (603983082961243905, '用户管理', 'user-user', '', b'0', '/user/user', 'zuihou/user/user/Index', b'1', 2, '', '', 102, 1, '2019-07-25 16:13:09', 3, '2019-11-11 14:28:49');
INSERT INTO `c_auth_menu` VALUES (605078371293987105, '数据字典维护', 'base-dict', '', b'0', '/base/dict', 'zuihou/base/dict/Index', b'1', 0, '', '', 104, 1, '2019-07-28 16:45:26', 3, '2019-11-11 14:34:23');
INSERT INTO `c_auth_menu` VALUES (605078463069552993, '地区信息维护', 'base-area', '', b'0', '/base/area', 'zuihou/base/area/Index', b'1', 1, '', '', 104, 1, '2019-07-28 16:45:48', 3, '2019-11-11 14:34:26');
INSERT INTO `c_auth_menu` VALUES (605078538881597857, '应用管理', 'developer-application', '', b'0', '/developer/application', 'zuihou/developer/application/Index', b'1', 0, '', '', 105, 1, '2019-07-28 16:46:06', 3, '2019-12-25 16:19:43');
INSERT INTO `c_auth_menu` VALUES (605078672772170209, '操作日志', 'developer-optLog', '', b'0', '/developer/optLog', 'zuihou/developer/optLog/Index', b'1', 3, '', '', 105, 1, '2019-07-28 16:46:38', 3, '2019-11-11 14:35:14');
INSERT INTO `c_auth_menu` VALUES (605078979149300257, '数据库监控', 'developer-db', '', b'0', '/developer/db', 'zuihou/developer/db/Index', b'1', 2, '', '', 105, 1, '2019-07-28 16:47:51', 3, '2019-11-16 16:35:50');
INSERT INTO `c_auth_menu` VALUES (605079239015793249, '接口文档', 'http://127.0.0.1:8760/api/gate/doc.html', '', b'0', 'http://127.0.0.1:8760/api/gate/doc.html', 'Layout', b'1', 5, '', '', 105, 1, '2019-07-28 16:48:53', 3, '2019-11-16 10:55:03');
INSERT INTO `c_auth_menu` VALUES (605079411338773153, '注册&配置中心', 'http://127.0.0.1:8848/nacos', '', b'0', 'http://127.0.0.1:8848/nacos', 'Layout', b'1', 6, '', '', 105, 1, '2019-07-28 16:49:34', 3, '2019-11-16 10:55:06');
INSERT INTO `c_auth_menu` VALUES (605079545585861345, '缓存监控', 'http://www.baidu.com', '', b'0', 'http://www.baidu.com', 'Layout', b'1', 7, '', '', 105, 1, '2019-07-28 16:50:06', 3, '2019-11-16 10:55:08');
INSERT INTO `c_auth_menu` VALUES (605079658416833313, '服务器监控', 'http://127.0.0.1:8762/zuihou-monitor', '', b'0', 'http://127.0.0.1:8762/zuihou-monitor', 'Layout', b'1', 8, '', '', 105, 1, '2019-07-28 16:50:33', 3, '2019-11-16 10:55:15');
INSERT INTO `c_auth_menu` VALUES (605080648767505601, '附件列表', 'file-attachment', '', b'0', '/file/attachment', 'zuihou/file/attachment/Index', b'1', 0, '', '', 106, 1, '2019-07-28 16:54:29', 3, '2019-11-11 14:28:07');
INSERT INTO `c_auth_menu` VALUES (605080816296396097, '定时调度中心', 'http://127.0.0.1:8767/zuihou-jobs-server', '', b'0', 'http://127.0.0.1:8767/zuihou-jobs-server', 'Layout', b'1', 9, '', '', 105, 1, '2019-07-28 16:55:09', 3, '2019-11-16 10:55:18');
INSERT INTO `c_auth_menu` VALUES (605424535633666945, '接口查询', 'developer-systemApi', '', b'0', '/developer/systemApi', 'zuihou/developer/systemApi/Index', b'1', 1, '', '', 105, 1, '2019-07-29 15:40:58', 3, '2019-12-24 14:40:47');
INSERT INTO `c_auth_menu` VALUES (644111530555611361, '链路调用监控', 'http://127.0.0.1:8772/zipkin', '', b'0', 'http://127.0.0.1:8772/zipkin', 'Layout', b'1', 10, '', '', 105, 3, '2019-11-13 09:49:16', 3, '2019-11-13 09:56:51');
INSERT INTO `c_auth_menu` VALUES (645215230518909025, '登录日志', 'developer-loginLog', '', b'0', '/developer/loginLog', 'zuihou/developer/loginLog/Index', b'1', 4, '', '', 105, 3, '2019-11-16 10:54:59', 3, '2019-11-16 10:54:59');
INSERT INTO `c_auth_menu` VALUES (1225042542827929600, '参数配置', 'base-parameter', '', b'0', '/base/parameter', 'zuihou/base/parameter/Index', b'1', 3, '', '', 104, 3, '2020-02-05 21:04:37', 3, '2020-02-05 21:04:37');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_resource`;
CREATE TABLE `c_auth_resource` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '编码\n规则：\n链接：\n数据列：\n按钮：',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID\n#c_auth_menu',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求路径',
  `describe_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`) USING BTREE COMMENT '编码唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='资源';

-- ----------------------------
-- Records of c_auth_resource
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_resource` VALUES (1, 'process:list', '流程列表', 603982542332235201, '/modeler/model/list', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (2, 'user:page', '用户分页列表', 603982542332235201, '/user/listPage', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (3, 'dictionaryItem:list', '根据字典类型查询字典数据', 603982542332235201, '/common/dictionaryItem', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (4, 'user:currentUser', '查询当前用户', 603982542332235201, '/user/currentUser', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (5, 'org:orgs', '查询组织列表', 603982542332235201, '/org/orgs', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (6, 'station:stationList', '查询所有岗位列表', 603982542332235201, '/station/stationList', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (7, 'user:save', '保存用户', 603982542332235201, '/user/saveAuthUser', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (8, 'user:update', '更新用户', 603982542332235201, '/user/updateAuthUser', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (9, 'user:delete', '删除用户', 603982542332235201, '/user/deleteAuthUser', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (10, 'process:instances', '查询流程实例列表', 603982542332235201, '/act/process/instances', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (11, 'station:page', '分页查询岗位列表', 603982542332235201, '/station/stations', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (12, 'station:save', '保存岗位', 603982542332235201, '/station/station', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (13, 'org:org', '保存,更新,删除组织', 603982542332235201, '/org/org', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (14, 'org:delete', '删除组织', 603982542332235201, '/org/org/batch', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (15, 'role:page', '角色分页', 603982542332235201, '/role/page', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (16, 'role:save', '角色保存', 603982542332235201, '/role/save', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (17, 'role:update', '角色更新', 603982542332235201, '/role/update', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (18, 'role:delete', '角色删除', 603982542332235201, '/role/delete', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (19, 'role:roleUserList', '角色用户', 603982542332235201, '/role/roleUserList', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (20, 'role:saveRoleUser', '角色用户', 603982542332235201, '/role/saveRoleUser', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (21, 'role:deleteRoleUser', '删除角色用户', 603982542332235201, '/role/deleteRoleUser', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (22, 'user:list', '查询用户列表', 603982542332235201, '/user/list', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (23, 'process:detail', '查询流程详细', 603982542332235201, '/process/detail/processDetail', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
INSERT INTO `c_auth_resource` VALUES (24, 'process:rule_action', '查询流程节点规则', 603982542332235201, '/process/rule/action', '', 3, '2019-11-11 13:39:28', 3, '2019-11-11 13:39:50');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role`;
CREATE TABLE `c_auth_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '编码',
  `describe_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly` bit(1) DEFAULT b'0' COMMENT '是否内置角色',
  `ds_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'SELF' COMMENT '数据权限类型\n#DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of c_auth_role
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_role` VALUES (100, '管理员', 'ADMIN', '平台管理员', b'1', b'1', 'ALL', 1, '2019-10-25 13:46:00', 1248084109452902400, '2020-10-08 11:07:45');
INSERT INTO `c_auth_role` VALUES (1248085631792316416, '测试员', 'TEST', '测试员', b'1', b'0', 'THIS_LEVEL_CHILDREN', 3, '2020-04-09 11:09:38', 1248084109452902400, '2020-10-08 11:07:57');
INSERT INTO `c_auth_role` VALUES (1248088058155237376, '员工', 'EMPLOYEES', '员工', b'1', b'0', 'SELF', 1248084109452902400, '2020-04-09 11:19:16', 1248084109452902400, '2020-04-09 11:19:16');
INSERT INTO `c_auth_role` VALUES (1314038324048715778, '操作员', 'OPERATOR', '操作员', b'1', b'0', 'SELF', 1248084109452902400, '2020-10-08 11:02:05', 1248084109452902400, '2020-10-26 21:08:39');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role_authority`;
CREATE TABLE `c_auth_role_authority` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `authority_id` bigint(20) NOT NULL COMMENT '资源id\n#c_auth_resource\n#c_auth_menu',
  `authority_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'MENU' COMMENT '权限类型\n#AuthorizeType{MENU:菜单;RESOURCE:资源;}',
  `role_id` bigint(20) NOT NULL COMMENT '角色id\n#c_auth_role',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_KEY` (`role_id`,`authority_type`,`authority_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色的资源';

-- ----------------------------
-- Records of c_auth_role_authority
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_role_authority` VALUES (1, 1, 'RESOURCE', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (2, 2, 'RESOURCE', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (3, 3, 'RESOURCE', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (4, 4, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (5, 5, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (6, 6, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (7, 7, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (8, 8, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (9, 9, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (10, 10, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (11, 11, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (12, 12, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (13, 13, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (14, 14, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (15, 15, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (16, 16, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (17, 17, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (18, 18, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (19, 19, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (20, 20, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (21, 21, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (22, 22, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (23, 23, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (24, 24, 'RESOURCE', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116855488514, 645215230518909025, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116859682816, 605079411338773153, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116859682817, 603982542332235201, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116859682818, 605078371293987105, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116859682819, 644111530555611361, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116863877120, 605079751035454305, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116863877121, 605078672772170209, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116863877122, 603976297063910529, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116863877123, 605079545585861345, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116868071424, 605080107379327969, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116868071425, 605078979149300257, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116868071426, 605080359394083937, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116872265728, 605078463069552993, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116872265729, 605080816296396097, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116872265730, 1225042542827929600, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116872265731, 603982713849908801, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116876460032, 605079658416833313, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116876460033, 605078538881597857, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116876460034, 605080648767505601, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116880654336, 603983082961243905, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116880654337, 603981723864141121, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116880654338, 605424535633666945, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116884848640, 605080023753294753, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116884848641, 605079239015793249, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116884848642, 101, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116884848643, 102, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116889042944, 103, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116889042945, 104, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116889042946, 105, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116889042947, 106, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1237036116893237249, 643464272629728001, 'MENU', 100, '2020-03-09 23:22:48', 3);
INSERT INTO `c_auth_role_authority` VALUES (1295255724951076865, 645215230518909025, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724959465473, 605079411338773153, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724959465475, 605080648767505601, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724959465477, 603983082961243905, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724959465479, 603982542332235201, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724963659777, 603981723864141121, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724963659779, 605424535633666945, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724963659781, 605078371293987105, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724963659783, 644111530555611361, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724963659785, 605079751035454305, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724963659787, 605080023753294753, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724963659789, 605079239015793249, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724963659791, 605078672772170209, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854081, 603976297063910529, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854083, 605079545585861345, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854085, 605080107379327969, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854087, 605078979149300257, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854089, 605080359394083937, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854091, 101, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854093, 102, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854095, 103, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854097, 104, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724967854099, 105, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724972048385, 106, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724972048387, 605078463069552993, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724972048389, 605080816296396097, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724972048391, 1225042542827929600, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724972048393, 603982713849908801, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724972048395, 643464272629728001, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724972048397, 605079658416833313, 'MENU', 100, '2020-08-17 15:06:44', NULL);
INSERT INTO `c_auth_role_authority` VALUES (1295255724972048399, 605078538881597857, 'MENU', 100, '2020-08-17 15:06:44', NULL);
COMMIT;

-- ----------------------------
-- Table structure for c_auth_role_org
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_role_org`;
CREATE TABLE `c_auth_role_org` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID\n#c_auth_role',
  `org_id` bigint(20) DEFAULT NULL COMMENT '部门ID\n#c_core_org',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色组织关系';

-- ----------------------------
-- Records of c_auth_role_org
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_role_org` VALUES (1248085547373559808, 100, 100, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547377754112, 100, 101, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547377754113, 100, 102, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547381948416, 100, 643775612976106881, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547381948417, 100, 643775664683486689, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547381948418, 100, 643775904077582049, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547381948419, 100, 643776324342648929, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547386142720, 100, 643776407691858113, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547386142721, 100, 643776508795556193, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547386142722, 100, 643776594376135105, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547390337024, 100, 643776633823564321, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547390337025, 100, 643776668917305985, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547390337026, 100, 643776713909605089, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085547390337027, 100, 643776757199016769, '2020-04-09 11:09:17', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631926534144, 1248085631792316416, 100, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631939117056, 1248085631792316416, 101, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631939117057, 1248085631792316416, 102, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631939117058, 1248085631792316416, 643775612976106881, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631943311360, 1248085631792316416, 643775664683486689, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631943311361, 1248085631792316416, 643775904077582049, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631943311362, 1248085631792316416, 643776324342648929, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631960088576, 1248085631792316416, 643776407691858113, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631964282880, 1248085631792316416, 643776508795556193, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631964282881, 1248085631792316416, 643776594376135105, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631964282882, 1248085631792316416, 643776633823564321, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631964282883, 1248085631792316416, 643776668917305985, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631968477184, 1248085631792316416, 643776713909605089, '2020-04-09 11:09:38', 3);
INSERT INTO `c_auth_role_org` VALUES (1248085631968477185, 1248085631792316416, 643776757199016769, '2020-04-09 11:09:38', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_user`;
CREATE TABLE `c_auth_user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID',
  `station_id` bigint(20) DEFAULT NULL COMMENT '岗位ID',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机',
  `sex` int(1) unsigned zerofill DEFAULT NULL COMMENT '性别',
  `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '头像',
  `nation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '民族',
  `education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学历',
  `position_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '职位状态',
  `work_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '工作描述比如：  市长、管理员、局长等等   用于登陆展示',
  `password_error_num` int(11) DEFAULT '0' COMMENT '密码错误次数',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_ACCOUNT` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
-- Records of c_auth_user
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_user` VALUES (1248084109452902400, 'zhouxinlei', '$2a$10$r4LbSmyEjXHn53iuoW4sMeq..akBe94fqzSBLOYUWp/J3/CpkU1xW', '周鑫磊', 643776594376135105, 645200151886964289, 'zhouxinlei298@163.com', '18817280664', 1, b'1', 'https://minio.en2hr.com/hongneng/image/images-icon_1586839683912.jpg', 'mz_hanz', 'BOSHIHOU', 'WORKING', '生活不止眼前的苟且，还有诗和远方', 0, '2020-04-10 17:57:07', 3, '2020-04-09 11:03:35', 1248084109452902400, '2020-10-25 00:01:17');
INSERT INTO `c_auth_user` VALUES (1248086612902936576, 'zhangming', '$2a$10$r4LbSmyEjXHn53iuoW4sMeq..akBe94fqzSBLOYUWp/J3/CpkU1xW', '刘怡', 643776633823564321, 645200304014370561, '34353535@163.com', '18709287623', 2, b'1', 'https://minio.en2hr.com/hongneng/image/images-icon_1586839683912.jpg', 'mz_hanz', 'BOSHI', 'WORKING', '', 0, NULL, 1248084109452902400, '2020-04-09 11:13:32', 1248084109452902400, '2020-10-26 21:09:39');
INSERT INTO `c_auth_user` VALUES (1280708543502401538, 'admin', '$2a$10$v7mPmtnDyNLtXScdWa366uBEcIrtcSgHMqBYszQhMHefwWZIHA1O.', '管理员', 643775664683486689, 642032719487828225, 'admin@163.com', '18817280664', 1, b'1', 'https://minio.en2hr.com/hongneng/image/images-icon_1586839683912.jpg', 'mz_hanz', 'BOSHI', 'WORKING', '', 0, NULL, 1248084109452902400, '2020-07-08 11:41:26', 1248084109452902400, '2020-07-08 11:41:26');
INSERT INTO `c_auth_user` VALUES (1313411653306261506, 'chuyy', '$2a$10$aHoHqog1w.WUURNBsKZfjuuBriU0c5HTlHRc7d2Ev9nVg4D4/rmSW', '楚云阳', 643776594376135105, 100, 'chuyunyang@163.com', '18817280664', 1, b'1', '', 'mz_hanz', 'BOSHIHOU', 'WORKING', '己所不欲勿施于人', 0, NULL, 1248084109452902400, '2020-10-06 17:31:55', 1248084109452902400, '2020-10-06 17:31:55');
INSERT INTO `c_auth_user` VALUES (1313417106174099458, 'zhouy', '$2a$10$Tbt3dnM.kUtUEJTJQQoagOFLAvnPGaNGjlr3.nHz6GZnsd1N707pC', '周煜', 643776594376135105, 100, 'zhouyu@163.com', '18817280664', 1, b'1', '', 'mz_hanz', 'BOSHIHOU', 'WORKING', '今天也是元气满满的一天', 0, NULL, 1248084109452902400, '2020-10-06 17:53:35', 1248084109452902400, '2020-10-06 17:53:35');
INSERT INTO `c_auth_user` VALUES (1313417547549097986, 'tangwr', '$2a$10$JDY08HxbIbSX0yJR1uxgkO0NwgNH3LpJ4KpL8K8FCIS3Webr.NJEq', '唐婉茹', 643776633823564321, 645199745026892801, 'tangwanru@163.com', '18817280664', 2, b'1', '', 'mz_hanz', 'BOSHI', 'WORKING', '元气少女101', 0, NULL, 1248084109452902400, '2020-10-06 17:55:20', 1248084109452902400, '2020-10-06 17:55:20');
INSERT INTO `c_auth_user` VALUES (1313418769509236738, 'renyq', '$2a$10$/Bpby8tabdGMS00I6JYelOs2HE06izRr7NqvFbz2/kIGxDtyjNcJ2', '任雨晴', 643776594376135105, 645200304014370561, 'renyuqing@163.com', '18817280664', 2, b'1', '', 'mz_hanz', 'BOSHI', 'WORKING', '', 0, NULL, 1248084109452902400, '2020-10-06 18:00:11', 1248084109452902400, '2020-10-06 18:00:11');
COMMIT;

-- ----------------------------
-- Table structure for c_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_user_role`;
CREATE TABLE `c_auth_user_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID\n#c_auth_role',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID\n#c_core_accou',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_KEY` (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色分配\r\n账号角色绑定';

-- ----------------------------
-- Records of c_auth_user_role
-- ----------------------------
BEGIN;
INSERT INTO `c_auth_user_role` VALUES (1317297210184785921, 100, 1248084109452902400);
INSERT INTO `c_auth_user_role` VALUES (1317297210184785922, 100, 1248084109452902400);
INSERT INTO `c_auth_user_role` VALUES (1317296916105355266, 1248085631792316416, 1248086612902936576);
INSERT INTO `c_auth_user_role` VALUES (1317296916113743873, 1248085631792316416, 1313417106174099458);
INSERT INTO `c_auth_user_role` VALUES (1248088163772006400, 1248088058155237376, 1248086612902936576);
COMMIT;

-- ----------------------------
-- Table structure for c_common_area
-- ----------------------------
DROP TABLE IF EXISTS `c_common_area`;
CREATE TABLE `c_common_area` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '编码',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '全名',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `longitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '经度',
  `latitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '维度',
  `level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '行政区级\n\n',
  `source_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据来源',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_CODE` (`code`) USING BTREE,
  KEY `IDX_PARENT_ID` (`parent_id`,`label`) USING BTREE COMMENT '查询'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='地区表';

-- ----------------------------
-- Records of c_common_area
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_common_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `c_common_dictionary`;
CREATE TABLE `c_common_dictionary` (
  `id` bigint(20) NOT NULL,
  `type_` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '编码\r\n一颗树仅仅有一个统一的编码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `describe_` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `status_` bit(1) DEFAULT b'1' COMMENT '状态',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典类型';

-- ----------------------------
-- Records of c_common_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `c_common_dictionary` VALUES (1, 'NATION', '民族', '', b'1', 1, '2019-06-01 09:42:50', 1, '2019-06-01 09:42:54');
INSERT INTO `c_common_dictionary` VALUES (2, 'POSITION_STATUS', '在职状态', '', b'1', 1, '2019-06-04 11:37:15', 1, '2019-06-04 11:37:15');
INSERT INTO `c_common_dictionary` VALUES (3, 'EDUCATION', '学历', '', b'1', 1, '2019-06-04 11:33:52', 1, '2019-06-04 11:33:52');
INSERT INTO `c_common_dictionary` VALUES (4, 'AREA_LEVEL', '行政区级', '', b'1', 3, '2020-01-20 15:12:05', 3, '2020-01-20 15:12:05');
COMMIT;

-- ----------------------------
-- Table structure for c_common_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `c_common_dictionary_item`;
CREATE TABLE `c_common_dictionary_item` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `dictionary_id` bigint(20) NOT NULL COMMENT '类型ID',
  `dictionary_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `status_` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dict_code_item_code_uniq` (`dictionary_type`,`code`) USING BTREE COMMENT '字典编码与字典项目编码联合唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典项';

-- ----------------------------
-- Records of c_common_dictionary_item
-- ----------------------------
BEGIN;
INSERT INTO `c_common_dictionary_item` VALUES (1, 4, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', '', 1, 3, '2020-01-20 15:12:57', 3, '2020-01-20 15:12:57');
INSERT INTO `c_common_dictionary_item` VALUES (2, 4, 'AREA_LEVEL', 'PROVINCE', '省份/直辖市', b'1', '', 2, 3, '2020-01-20 15:13:45', 3, '2020-01-20 15:13:45');
INSERT INTO `c_common_dictionary_item` VALUES (3, 4, 'AREA_LEVEL', 'CITY', '地市', b'1', '', 3, 3, '2020-01-20 15:14:16', 3, '2020-01-20 15:14:16');
INSERT INTO `c_common_dictionary_item` VALUES (4, 4, 'AREA_LEVEL', 'COUNTY', '区县', b'1', '', 4, 3, '2020-01-20 15:14:54', 3, '2020-01-20 15:14:54');
INSERT INTO `c_common_dictionary_item` VALUES (38, 3, 'EDUCATION', 'ZHUANKE', '专科', b'1', '', 4, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (39, 3, 'EDUCATION', 'COLLEGE', '本科', b'1', '', 5, 1, '2019-06-04 11:36:19', 1, '2019-06-04 11:36:19');
INSERT INTO `c_common_dictionary_item` VALUES (40, 3, 'EDUCATION', 'SUOSHI', '硕士', b'1', '', 6, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (41, 3, 'EDUCATION', 'BOSHI', '博士', b'1', '', 7, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (42, 3, 'EDUCATION', 'BOSHIHOU', '博士后', b'1', '', 8, 1, '2019-06-04 11:36:29', 1, '2019-06-04 11:36:29');
INSERT INTO `c_common_dictionary_item` VALUES (43, 1, 'NATION', 'mz_hanz', '汉族', b'1', '', 0, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (44, 1, 'NATION', 'mz_zz', '壮族', b'1', '', 1, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (45, 1, 'NATION', 'mz_mz', '满族', b'1', '', 2, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (46, 1, 'NATION', 'mz_hz', '回族', b'1', '', 3, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (47, 1, 'NATION', 'mz_miaoz', '苗族', b'1', '', 4, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (48, 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', '', 5, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (49, 1, 'NATION', 'mz_tjz', '土家族', b'1', '', 6, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (50, 1, 'NATION', 'mz_yz', '彝族', b'1', '', 7, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (51, 1, 'NATION', 'mz_mgz', '蒙古族', b'1', '', 8, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (52, 1, 'NATION', 'mz_zhangz', '藏族', b'1', '', 9, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (53, 1, 'NATION', 'mz_byz', '布依族', b'1', '', 10, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (54, 1, 'NATION', 'mz_dz', '侗族', b'1', '', 11, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (55, 1, 'NATION', 'mz_yaoz', '瑶族', b'1', '', 12, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (56, 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', '', 13, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (57, 1, 'NATION', 'mz_bz', '白族', b'1', '', 14, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (58, 1, 'NATION', 'mz_hnz', '哈尼族', b'1', '', 15, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (59, 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', '', 16, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (60, 1, 'NATION', 'mz_lz', '黎族', b'1', '', 17, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (61, 1, 'NATION', 'mz_daiz', '傣族', b'1', '', 18, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (62, 1, 'NATION', 'mz_sz', '畲族', b'1', '', 19, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (63, 1, 'NATION', 'mz_llz', '傈僳族', b'1', '', 20, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (64, 1, 'NATION', 'mz_glz', '仡佬族', b'1', '', 21, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (65, 1, 'NATION', 'mz_dxz', '东乡族', b'1', '', 22, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (66, 1, 'NATION', 'mz_gsz', '高山族', b'1', '', 23, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (67, 1, 'NATION', 'mz_lhz', '拉祜族', b'1', '', 24, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (68, 1, 'NATION', 'mz_shuiz', '水族', b'1', '', 25, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (69, 1, 'NATION', 'mz_wz', '佤族', b'1', '', 26, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (70, 1, 'NATION', 'mz_nxz', '纳西族', b'1', '', 27, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (71, 1, 'NATION', 'mz_qz', '羌族', b'1', '', 28, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (72, 1, 'NATION', 'mz_tz', '土族', b'1', '', 29, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (73, 1, 'NATION', 'mz_zlz', '仫佬族', b'1', '', 30, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (74, 1, 'NATION', 'mz_xbz', '锡伯族', b'1', '', 31, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (75, 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', '', 32, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (76, 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', '', 33, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (77, 1, 'NATION', 'mz_jpz', '景颇族', b'1', '', 34, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (78, 1, 'NATION', 'mz_mlz', '毛南族', b'1', '', 35, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (79, 1, 'NATION', 'mz_slz', '撒拉族', b'1', '', 36, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (80, 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', '', 37, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (81, 1, 'NATION', 'mz_acz', '阿昌族', b'1', '', 38, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (82, 1, 'NATION', 'mz_pmz', '普米族', b'1', '', 39, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (83, 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', '', 40, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (84, 1, 'NATION', 'mz_nz', '怒族', b'1', '', 41, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (85, 1, 'NATION', 'mz_jz', '京族', b'1', '', 42, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (86, 1, 'NATION', 'mz_jnz', '基诺族', b'1', '', 43, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (87, 1, 'NATION', 'mz_daz', '德昂族', b'1', '', 44, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (88, 1, 'NATION', 'mz_baz', '保安族', b'1', '', 45, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (89, 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', '', 46, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (90, 1, 'NATION', 'mz_ygz', '裕固族', b'1', '', 47, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (91, 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', '', 48, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (92, 1, 'NATION', 'mz_mbz', '门巴族', b'1', '', 49, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (93, 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', '', 50, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (94, 1, 'NATION', 'mz_dlz', '独龙族', b'1', '', 51, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (95, 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', '', 52, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (96, 1, 'NATION', 'mz_hzz', '赫哲族', b'1', '', 53, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (97, 1, 'NATION', 'mz_lbz', '珞巴族', b'1', '', 54, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (98, 1, 'NATION', 'mz_blz', '布朗族', b'1', '', 55, 1, '2018-03-15 20:11:01', 1, '2018-03-15 20:11:04');
INSERT INTO `c_common_dictionary_item` VALUES (99, 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', '', 1, 1, '2019-06-04 11:38:16', 1, '2019-06-04 11:38:16');
INSERT INTO `c_common_dictionary_item` VALUES (100, 2, 'POSITION_STATUS', 'QUIT', '离职', b'1', '', 2, 1, '2019-06-04 11:38:50', 1, '2019-06-04 11:38:50');
INSERT INTO `c_common_dictionary_item` VALUES (1237038877428940800, 4, 'AREA_LEVEL', 'TOWNS', '乡镇', b'1', '', 5, 3, '2020-03-09 23:33:46', 3, '2020-03-09 23:33:46');
INSERT INTO `c_common_dictionary_item` VALUES (1237038991044247552, 3, 'EDUCATION', 'XIAOXUE', '小学', b'1', '', 1, 3, '2020-03-09 23:34:13', 3, '2020-03-09 23:34:13');
INSERT INTO `c_common_dictionary_item` VALUES (1237039071537135616, 3, 'EDUCATION', 'ZHONGXUE', '中学', b'1', '', 2, 3, '2020-03-09 23:34:32', 3, '2020-03-09 23:34:32');
INSERT INTO `c_common_dictionary_item` VALUES (1237039105171259392, 3, 'EDUCATION', 'GAOZHONG', '高中', b'1', '', 3, 3, '2020-03-09 23:34:40', 3, '2020-03-09 23:34:40');
INSERT INTO `c_common_dictionary_item` VALUES (1237039160271831040, 3, 'EDUCATION', 'QITA', '其他', b'1', '', 20, 3, '2020-03-09 23:34:54', 3, '2020-03-09 23:34:54');
INSERT INTO `c_common_dictionary_item` VALUES (1237040064488275968, 1, 'NATION', 'mz_qt', '其他', b'1', '', 100, 3, '2020-03-09 23:38:29', 3, '2020-03-09 23:38:29');
INSERT INTO `c_common_dictionary_item` VALUES (1237040319480987648, 2, 'POSITION_STATUS', 'LEAVE', '请假', b'1', '', 3, 3, '2020-03-09 23:39:30', 3, '2020-03-09 23:39:30');
COMMIT;

-- ----------------------------
-- Table structure for c_common_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_common_login_log`;
CREATE TABLE `c_common_login_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `request_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录IP',
  `user_id` bigint(20) DEFAULT NULL COMMENT '登录人ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录人姓名',
  `account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录人账号',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录描述',
  `login_date` date DEFAULT NULL COMMENT '登录时间',
  `ua` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '浏览器请求头',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '浏览器名称',
  `browser_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '浏览器版本',
  `operating_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作系统',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录地点',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_BROWSER` (`browser`) USING BTREE,
  KEY `IDX_OPERATING` (`operating_system`) USING BTREE,
  KEY `IDX_LOGIN_DATE` (`login_date`,`account`) USING BTREE,
  KEY `IDX_ACCOUNT_IP` (`account`,`request_ip`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='登录日志';

-- ----------------------------
-- Records of c_common_login_log
-- ----------------------------
BEGIN;
INSERT INTO `c_common_login_log` VALUES (1278307107473723392, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-01', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-01 20:38:59', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280303774381117440, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 08:53:01', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280319678070394880, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 09:56:13', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280322225136996352, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 10:06:20', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280323570619387904, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 10:11:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280323676361986048, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 10:12:06', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280323682884128768, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 10:12:08', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280324397014716416, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 10:14:58', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280325730748862464, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 10:20:16', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280486505249509376, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 20:59:08', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280515013971546112, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 22:52:25', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280517338685509632, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 23:01:39', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280517732601958400, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 23:03:13', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280521373291384832, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 23:17:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280523723284418560, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 23:27:01', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280526214394810368, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 23:36:51', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280528076166664192, '192.168.3.3', 1248086612902936576, '张明', 'zhangming', '登录成功', '2020-07-07', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-07 23:44:19', 1248086612902936576);
INSERT INTO `c_common_login_log` VALUES (1280533543794249728, '192.168.3.3', 1248086612902936576, '张明', 'zhangming', '登录成功', '2020-07-08', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-08 00:06:03', 1248086612902936576);
INSERT INTO `c_common_login_log` VALUES (1280533858719371264, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-08', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-08 00:07:18', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280698600264044544, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-08', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-08 11:01:55', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1280850599307644928, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-08', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-08 21:05:55', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281038249444511744, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-09', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-09 09:31:34', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281039406862700544, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-09', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-09 09:36:10', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281170449687121920, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-09', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-09 18:16:53', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281172862817013760, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-09', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-09 18:26:28', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281190177952501760, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-09', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-09 19:35:16', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281752069393616896, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 08:48:02', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281767765745864704, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 09:50:24', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281771794894950400, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 10:06:25', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281773342916087808, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 10:12:34', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281780587468951552, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 10:41:21', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281785495379251200, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 11:00:51', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281788433526951936, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 11:12:32', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281789550533021696, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 11:16:58', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281791000344530944, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 11:22:44', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281799281607708672, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 11:55:38', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281799473522282496, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 11:56:24', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281808155450544128, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 12:30:54', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281808449680969728, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 12:32:04', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281809821121908736, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 12:37:31', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281813037943361536, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 12:50:18', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281815442776920064, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 12:59:51', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281816832509218816, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 13:05:23', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281825298804117504, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 13:39:01', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281826867029217280, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 13:45:15', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281827793836183552, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 13:48:56', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281828075332702208, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 13:50:03', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281829109828423680, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 13:54:10', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281837632247304192, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 14:28:02', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281839292315078656, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 14:34:37', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281840556641882112, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 14:39:39', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281867484115505152, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 16:26:39', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281868074283438080, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 16:29:00', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281878376311951360, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 17:09:56', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1281896906847686656, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-11', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-11 18:23:34', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1282180213879607296, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-12', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-12 13:09:19', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1282183306608381952, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-12', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-12 13:21:37', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1282239583862657024, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-12', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-12 17:05:14', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1282275789556158464, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-12', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-12 19:29:06', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283065098420883456, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-14', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-14 23:45:32', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283231284454494208, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-15', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-15 10:45:54', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283342560505630720, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-15', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-15 18:08:04', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283380227050049536, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-15', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-15 20:37:45', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283385233782345728, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-15', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-15 20:57:39', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283395478298562560, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-15', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-15 21:38:21', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283407622163402752, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-15', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-15 22:26:36', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283575496538591232, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-16 09:33:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283575630777290752, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-16 09:34:13', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283576279736782848, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-16 09:36:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283576309499564032, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-16 09:36:55', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283576323495956480, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-16 09:36:58', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283576331104423936, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-16 09:37:00', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283576337051947008, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-16 09:37:01', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283576343637004288, '192.168.193.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-16 09:37:03', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283667518456860672, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-16 15:39:20', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283667639034712064, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-16 15:39:49', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283667667585339392, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-16 15:39:56', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283667726439813120, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-16 15:40:10', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283677640700727296, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-16', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-16 16:19:34', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283754534086115328, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '密码不正确', '2020-07-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36', 'Chrome', '83.0.4103.116', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-16 21:25:07', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283754638696251392, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '密码不正确', '2020-07-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36', 'Chrome', '83.0.4103.116', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-16 21:25:32', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283755530690494464, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '密码不正确', '2020-07-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36', 'Chrome', '83.0.4103.116', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-16 21:29:04', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283760090452590592, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '密码不正确', '2020-07-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36', 'Chrome', '83.0.4103.116', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-16 21:47:11', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283760132081057792, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '密码不正确', '2020-07-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36', 'Chrome', '83.0.4103.116', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-16 21:47:21', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283761012322861056, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '密码不正确', '2020-07-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36', 'Chrome', '83.0.4103.116', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-16 21:50:51', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283762323151912960, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '密码不正确', '2020-07-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36', 'Chrome', '83.0.4103.116', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-16 21:56:04', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1283765018688487424, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '密码不正确', '2020-07-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36', 'Chrome', '83.0.4103.116', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-16 22:06:46', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284279090991333376, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-18', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-18 08:09:31', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284279279906979840, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-18', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-18 08:10:16', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284282958542274560, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-18', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-18 08:24:53', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284508265652158464, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-18', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-18 23:20:10', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284644894807162880, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-19', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-19 08:23:05', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284697446596739072, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-19', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-19 11:51:54', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284722526542364672, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-19', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-19 13:31:34', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284746169041289216, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-19', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-19 15:05:31', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284762512532701184, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-19', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-19 16:10:27', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1284806261631614976, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-19', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-19 19:04:18', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1285039184850976768, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-20', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-20 10:29:51', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286122679090282496, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-23 10:15:16', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286125239373135872, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:25:27', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286125404398026752, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:26:06', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286125613362446336, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:26:56', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286125740022038528, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:27:26', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286126052493492224, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:28:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286126617101336576, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:30:55', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286127742307270656, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:35:24', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286128340012367872, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:37:46', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286128950707224576, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:40:12', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286129249995980800, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:41:23', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286129702280364032, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:43:11', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286129929653583872, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:44:05', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286130159761489920, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:45:00', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286130269719363584, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:45:26', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286130655607914496, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:46:58', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286131100199944192, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:48:44', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286131241078226944, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:49:18', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286131365904908288, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:49:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286132123404599296, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:52:48', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286132461608108032, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:54:09', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286132770862530560, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:55:22', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286132914026708992, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 10:55:57', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286134969776734208, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:04:07', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286135023430270976, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:04:19', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286135457821753344, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:06:03', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286135568429744128, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:06:29', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286136204231704576, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:09:01', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286137361633116160, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:13:37', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286137466687848448, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:14:02', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286137608333688832, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:14:36', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286138133481521152, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-23 11:16:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286261647303770112, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-23', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36', 'Chrome', '84.0.4147.89', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-23 19:27:29', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286538423028416512, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-24', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-24 13:47:17', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286544901697175552, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-24', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-24 14:13:02', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286555832816762880, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-24', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-24 14:56:28', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286572017264361472, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-24', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-24 16:00:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286592622399324160, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-24', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-24 17:22:40', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286849804810321920, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-25', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36', 'Chrome', '84.0.4147.89', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-25 10:24:37', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286872055773396992, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-25', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36', 'Chrome', '84.0.4147.89', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-25 11:53:02', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286887627936497664, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-25', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36', 'Chrome', '84.0.4147.89', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-25 12:54:54', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286902785282932736, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-25', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36', 'Chrome', '84.0.4147.89', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-25 13:55:08', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1286906599926923264, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-25', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36', 'Chrome', '84.0.4147.89', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-25 14:10:18', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287557932619137024, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-27', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-27 09:18:27', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287572457170927616, '172.34.89.213', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-27', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36', 'Chrome', '75.0.3770.100', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-27 10:16:10', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287574247824162816, '172.34.89.213', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-27', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36', 'Chrome', '75.0.3770.100', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-27 10:23:17', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287586756689920000, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-27', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-27 11:13:00', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287651677205168128, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-27', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-27 15:30:58', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287687208794324992, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-27', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-27 17:52:09', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287735192974262272, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-27', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36', 'Chrome', '84.0.4147.89', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-07-27 21:02:50', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287983224764497920, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-28', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-28 13:28:25', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1287983541564473344, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-28', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-07-28 13:29:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1288118181142986752, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-28', 'PostmanRuntime/7.26.1', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-07-28 22:24:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1288311947493638144, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-29', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-29 11:14:39', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1288391444725235712, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-29', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-29 16:30:32', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1288394087577157632, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-29', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-29 16:41:02', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1288395962875641856, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-29', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-29 16:48:29', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1288676230786187264, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-07-30', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-07-30 11:22:11', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289501217801633792, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-01', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-01 18:00:23', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289572081851170816, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-01', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-01 22:41:58', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289860970440556544, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-02', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-02 17:49:54', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289885967401877504, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-02', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-02 19:29:14', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289897748660748288, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-02', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-02 20:16:03', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289902685037789184, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-02', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-02 20:35:40', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289934720305463296, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-02', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-02 22:42:58', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289937444099063808, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-02', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-02 22:53:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289939614710104064, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-02', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-02 23:02:25', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1289940407462920192, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-02', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-02 23:05:34', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290115230126309376, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-08-03 10:40:15', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290116393571713024, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-08-03 10:44:52', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290116938298556416, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-08-03 10:47:02', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290131164920545280, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-08-03 11:43:34', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290131434865950720, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-08-03 11:44:38', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290221388036046848, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-08-03 17:42:05', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290223990249357312, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '美国|0|0|0|T-Mobile', '2020-08-03 17:52:25', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290255801411174400, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-03 19:58:50', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290258023859617792, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-03 20:07:39', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290261423271706624, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-03 20:21:10', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290265832290516992, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-03', 'PostmanRuntime/7.26.2', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-08-03 20:38:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290577561985744896, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-04', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-04 17:17:23', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290578256092725248, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-04', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-04 17:20:09', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290580036067262464, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-04', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-04 17:27:13', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290583687104036864, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-04', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-04 17:41:44', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290837252150132736, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 10:29:18', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290838739609714688, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 10:35:13', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290845679672885248, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 11:02:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290912255562481664, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 15:27:20', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290913191429144576, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 15:31:03', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290917834091659264, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 15:49:30', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290943680781221888, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 17:32:13', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290947750845743104, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 17:48:23', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290951647459016704, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 18:03:52', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290952380191342592, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 18:06:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290954979682549760, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 18:17:07', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1290955903842910208, '172.34.89.236', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-05', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '美国|0|0|0|T-Mobile', '2020-08-05 18:20:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294513224036646913, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-15', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-15 13:56:18', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294513925009702913, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-15', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-15 13:59:05', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294514194913165313, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-15', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-15 14:00:10', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294514720354598913, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-15', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-15 14:02:15', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294515085519093761, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-15', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-15 14:03:42', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294515316113539073, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-15', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-15 14:04:37', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294515541347663873, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-15', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-15 14:05:31', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294516339439828993, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-15', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-15 14:08:41', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294831841307525121, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:02:22', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294835259883388929, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:15:57', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294835533658193921, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:17:03', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294836222794924032, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:19:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294836375320788993, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:20:23', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294837953675132929, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:26:40', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294845261796868097, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:55:42', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294845793504591873, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:57:49', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294845926334005249, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:58:21', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294846158513897473, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 11:59:16', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294847158813458433, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 12:03:14', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294847798662922241, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 12:05:47', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294847986706153473, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 12:06:32', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294848062035853313, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 12:06:50', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294848925219422209, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 12:10:16', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294849172653998081, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 12:11:15', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294850827256594433, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 12:17:49', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1294851425456619521, '192.168.3.3', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-16', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36', 'Chrome', '84.0.4147.125', 'Mac OS X', '0|0|0|内网IP|内网IP', '2020-08-16 12:20:12', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295190458686767105, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 10:47:23', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295191237669683201, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 10:50:29', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295191848523923457, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 10:52:55', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295192305908580353, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 10:54:44', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295196426346692609, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 11:11:06', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295200141870891009, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 11:25:52', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295243361770274817, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:17:37', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295244254955700225, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:21:10', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295245335362273281, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:25:27', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295245462386769921, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:25:57', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295245789211131905, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:27:15', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295246072121131009, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:28:23', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295246657557889025, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:30:42', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295246936349081601, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:31:49', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295248474215809025, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:37:55', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295251005667016705, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:47:59', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295251260345155585, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:49:00', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295251634305105921, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:50:29', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295251727355740161, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:50:51', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295251829189246977, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:51:15', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295252397118980097, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-17', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-17 14:53:31', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295602078990729217, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-18', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-18 14:03:01', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1295602202634616833, '127.0.0.1,192.168.227.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-08-18', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 'Chrome', '80.0.3987.149', 'Windows 10', '0|0|0|内网IP|内网IP', '2020-08-18 14:03:31', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1303977829913853953, '169.254.73.74', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-09-10', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.100 Safari/537.36', 'Chrome', '80.0.3987.100', 'Windows 7', '0|0|0|内网IP|内网IP', '2020-09-10 16:45:16', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1304038615445143553, '127.0.0.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-09-10', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36', 'Chrome', '78.0.3904.108', 'Windows 7', '0|0|0|内网IP|内网IP', '2020-09-10 20:46:48', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1304038853828411393, '127.0.0.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-09-10', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36', 'Chrome', '78.0.3904.108', 'Windows 7', '0|0|0|内网IP|内网IP', '2020-09-10 20:47:45', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1304040738434056193, '127.0.0.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-09-10', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36', 'Chrome', '78.0.3904.108', 'Windows 7', '0|0|0|内网IP|内网IP', '2020-09-10 20:55:15', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1304060376257658881, '127.0.0.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-09-10', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.100 Safari/537.36', 'Chrome', '80.0.3987.100', 'Windows 7', '0|0|0|内网IP|内网IP', '2020-09-10 22:13:17', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1304060432788488193, '127.0.0.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-09-10', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.100 Safari/537.36', 'Chrome', '80.0.3987.100', 'Windows 7', '0|0|0|内网IP|内网IP', '2020-09-10 22:13:30', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1304216517985959937, '127.0.0.1', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-09-11', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.100 Safari/537.36', 'Chrome', '80.0.3987.100', 'Windows 7', '0|0|0|内网IP|内网IP', '2020-09-11 08:33:44', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312631724311576577, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 13:52:45', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312633548410191873, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 14:00:00', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312643674064027649, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 14:40:14', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312647396697571329, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 14:55:02', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312657889311064065, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 15:36:43', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312660481109917697, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 15:47:01', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312660686387544065, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 15:47:50', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312660801621852161, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 15:48:18', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312661746216861697, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 15:52:03', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312662343414448129, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 15:54:25', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312664214682206209, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 16:01:52', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312672808785936385, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 16:36:01', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312688007907639297, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 17:36:24', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312689050414481409, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 17:40:33', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312693373424893953, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 17:57:44', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312695581671424001, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 18:06:30', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312699858168315905, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-04', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-04 18:23:30', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312947674975305729, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-05', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-05 10:48:14', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312953055772409857, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-05', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-05 11:09:37', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1312958093613858817, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-05', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-05 11:29:38', 1248084109452902400);
INSERT INTO `c_common_login_log` VALUES (1317287631885959169, '192.168.3.14', 1248084109452902400, '周鑫磊', 'zhouxinlei', '登录成功', '2020-10-17', 'PostmanRuntime/7.26.5', 'Unknown', NULL, 'Unknown', '0|0|0|内网IP|内网IP', '2020-10-17 10:13:40', 1248084109452902400);
COMMIT;

-- ----------------------------
-- Table structure for c_common_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `c_common_opt_log`;
CREATE TABLE `c_common_opt_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `request_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作IP',
  `type` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'OPT' COMMENT '日志类型\n#LogType{OPT:操作类型;EX:异常类型}',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作描述',
  `class_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '类路径',
  `action_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '请求方法',
  `request_uri` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '请求地址',
  `http_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'GET' COMMENT '请求类型\n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
  `params` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '请求参数',
  `result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '返回值',
  `ex_desc` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '异常详情信息',
  `ex_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '异常描述',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  `ua` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '浏览器',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_type` (`type`) USING BTREE COMMENT '日志类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of c_common_opt_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_common_parameter
-- ----------------------------
DROP TABLE IF EXISTS `c_common_parameter`;
CREATE TABLE `c_common_parameter` (
  `id` bigint(20) NOT NULL,
  `key_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '参数键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '参数名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '参数值',
  `describe_` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `status_` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly_` bit(1) DEFAULT NULL COMMENT '只读',
  `create_user` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_KEY` (`key_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='参数配置';

-- ----------------------------
-- Records of c_common_parameter
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_core_org
-- ----------------------------
DROP TABLE IF EXISTS `c_core_org`;
CREATE TABLE `c_core_org` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `abbreviation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '简称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
  `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT ',' COMMENT '树结构',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  FULLTEXT KEY `FU_PATH` (`tree_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='组织';

-- ----------------------------
-- Records of c_core_org
-- ----------------------------
BEGIN;
INSERT INTO `c_core_org` VALUES (100, '星火云科技有限公司', '星火云', 0, ',', 1, b'1', '初始化数据', '2019-07-10 17:02:18', 1, '2020-10-07 18:52:29', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (101, '星火云科技有限公司杭州分公司', '星火云杭州分部', 100, ',100,', 1, b'1', '初始化数据', '2019-08-06 09:10:53', 1, '2020-10-07 18:52:51', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (102, '星火云科技有限公司上海分公司', '星火云上海分部', 100, ',100,', 2, b'1', '初始化数据', '2019-11-07 16:13:09', 1, '2020-10-07 18:53:04', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643775612976106881, '综合部', '综合部', 101, ',100,101,', 1, b'1', '前台&HR', '2019-11-12 11:34:27', 3, '2020-10-26 21:08:54', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643775664683486689, '管理层', '', 100, ',100,', 3, b'1', '', '2019-11-12 11:34:39', 3, '2019-11-12 11:36:16', 3);
INSERT INTO `c_core_org` VALUES (643775904077582049, '总经办', '', 100, ',100,', 4, b'1', '', '2019-11-12 11:35:37', 3, '2020-10-07 18:50:32', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643776324342648929, '财务部', '', 100, ',100,', 5, b'1', '', '2019-11-12 11:37:17', 3, '2020-10-07 18:50:39', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643776407691858113, '市场部', '', 100, ',100,', 6, b'1', '', '2019-11-12 11:37:37', 3, '2020-10-07 18:50:58', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643776508795556193, '销售部', '', 100, ',100,', 7, b'1', '', '2019-11-12 11:38:01', 3, '2020-10-07 18:50:47', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643776594376135105, '研发部', '', 101, ',100,101,', 2, b'1', '', '2019-11-12 11:38:21', 3, '2020-10-07 18:50:02', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643776633823564321, '产品部', '', 101, ',100,101,', 3, b'1', '', '2019-11-12 11:38:31', 3, '2020-10-07 18:50:08', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643776668917305985, '综合部', '', 102, ',100,102,', 0, b'1', '', '2019-11-12 11:38:39', 3, '2019-11-12 11:38:39', 3);
INSERT INTO `c_core_org` VALUES (643776713909605089, '研发部', '', 102, ',100,102,', 2, b'1', '', '2019-11-12 11:38:50', 3, '2020-10-07 18:51:49', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (643776757199016769, '销售部', '', 102, ',100,102,', 3, b'1', '', '2019-11-12 11:39:00', 3, '2020-10-07 18:51:51', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (1313785745283219457, '销售部', '', 101, ',', 4, b'1', '', '2020-10-07 18:18:25', 1248084109452902400, '2020-10-07 18:50:15', 1248084109452902400);
INSERT INTO `c_core_org` VALUES (1313791199967182849, '324234', '423423', 100, ',', 1, b'0', '234234', '2020-10-07 18:40:06', 1248084109452902400, '2020-10-07 18:40:06', 1248084109452902400);
COMMIT;

-- ----------------------------
-- Table structure for c_core_station
-- ----------------------------
DROP TABLE IF EXISTS `c_core_station`;
CREATE TABLE `c_core_station` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `org_id` bigint(20) DEFAULT '0' COMMENT '组织ID\n#c_core_org',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='岗位';

-- ----------------------------
-- Records of c_core_station
-- ----------------------------
BEGIN;
INSERT INTO `c_core_station` VALUES (100, '总经理', 643775904077582049, b'1', '总部-1把手', '2019-07-10 17:03:03', 1, '2020-10-07 12:37:09', 1248084109452902400);
INSERT INTO `c_core_station` VALUES (101, '副总经理', 643775904077582049, b'1', '总部-2把手', '2019-07-22 17:07:55', 1, '2019-11-16 09:59:21', 3);
INSERT INTO `c_core_station` VALUES (642032719487828225, '研发经理', 643776594376135105, b'1', '子公司-研发部老大', '2019-11-07 16:08:49', 3, '2019-11-16 09:53:42', 3);
INSERT INTO `c_core_station` VALUES (645199319300842081, '副总经理', 101, b'1', '子公司-老大', '2019-11-16 09:51:45', 3, '2019-11-16 09:53:50', 3);
INSERT INTO `c_core_station` VALUES (645199745026892801, '产品经理', 643776633823564321, b'1', '子公司-产品部老大', '2019-11-16 09:53:27', 3, '2019-11-16 09:54:01', 3);
INSERT INTO `c_core_station` VALUES (645200064280536545, '人事经理', 643775612976106881, b'1', '子公司-综合老大', '2019-11-16 09:54:43', 3, '2019-11-16 09:54:43', 3);
INSERT INTO `c_core_station` VALUES (645200151886964289, 'Java工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 09:55:04', 3, '2019-11-16 09:55:04', 3);
INSERT INTO `c_core_station` VALUES (645200250243393185, '需求工程师', 643776633823564321, b'1', '普通员工', '2019-11-16 09:55:27', 3, '2019-11-16 09:55:27', 3);
INSERT INTO `c_core_station` VALUES (645200304014370561, 'UI工程师', 643776633823564321, b'1', '普通员工', '2019-11-16 09:55:40', 3, '2019-11-16 09:55:40', 3);
INSERT INTO `c_core_station` VALUES (645200358959753057, '运维工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 09:55:53', 3, '2019-11-16 09:55:53', 3);
INSERT INTO `c_core_station` VALUES (645200405453612993, '前台小姐姐', 643775612976106881, b'1', '普通员工', '2019-11-16 09:56:04', 3, '2019-11-16 09:56:04', 3);
INSERT INTO `c_core_station` VALUES (645200545698555937, '人事经理', 643776668917305985, b'1', '北京分公司-综合部老大', '2019-11-16 09:56:38', 3, '2019-11-16 09:56:38', 3);
INSERT INTO `c_core_station` VALUES (645200670781089921, '研发经理', 643776713909605089, b'1', '北京分公司-研发部老大', '2019-11-16 09:57:07', 3, '2019-11-16 09:57:07', 3);
INSERT INTO `c_core_station` VALUES (645200806559099105, '销售经理', 643776757199016769, b'1', '北京销售部老大', '2019-11-16 09:57:40', 3, '2019-11-16 09:57:40', 3);
INSERT INTO `c_core_station` VALUES (645200885772724545, '行政', 643776668917305985, b'1', '普通员工', '2019-11-16 09:57:59', 3, '2019-11-16 09:57:59', 3);
INSERT INTO `c_core_station` VALUES (645200938289605025, '大前端工程师', 643776713909605089, b'1', '普通员工', '2019-11-16 09:58:11', 3, '2019-11-16 09:58:11', 3);
INSERT INTO `c_core_station` VALUES (645201064705927681, '销售员工', 643776757199016769, b'1', '普通员工', '2019-11-16 09:58:41', 3, '2019-11-16 09:58:41', 3);
INSERT INTO `c_core_station` VALUES (645201184268757601, '销售总监', 643775664683486689, b'1', '总部2把手', '2019-11-16 09:59:10', 3, '2019-11-16 09:59:10', 3);
INSERT INTO `c_core_station` VALUES (645201307765844833, '财务总监', 643776324342648929, b'1', '总部2把手', '2019-11-16 09:59:39', 3, '2019-11-16 09:59:39', 3);
INSERT INTO `c_core_station` VALUES (645201405757369281, '市场经理', 643776407691858113, b'1', '总部市场部老大', '2019-11-16 10:00:03', 3, '2019-11-16 10:00:03', 3);
INSERT INTO `c_core_station` VALUES (645201481133206561, '销售总监', 643776508795556193, b'1', '总部销售部老大', '2019-11-16 10:00:21', 3, '2019-11-16 10:00:21', 3);
INSERT INTO `c_core_station` VALUES (645201573391117441, '前端工程师', 643776594376135105, b'1', '普通员工', '2019-11-16 10:00:43', 3, '2019-11-16 10:00:43', 3);
INSERT INTO `c_core_station` VALUES (1313689474904096769, '测试工程师', 643776594376135105, b'1', '主要负责测试系统功能', '2020-10-07 11:55:53', 1248084109452902400, '2020-10-07 11:55:53', 1248084109452902400);
COMMIT;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `clientId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('sparkzxl', NULL, '$2a$10$ogVT3TV6f1Dxjj2Nz2bhUuqy4V.CvHEP.iZlTFBK/d/mtki9b2BZq', 'all', 'authorization_code,refresh_token,implicit,password', 'https://www.sparksys.top', NULL, 3600, 864000, NULL, 'true');
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
