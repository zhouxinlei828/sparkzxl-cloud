/*
 Navicat Premium Data Transfer

 Source Server         : 云数据库
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : rm-bp1cp68o8t1gq604p5o.mysql.rds.aliyuncs.com:3306
 Source Schema         : activiti

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 12/11/2020 17:43:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ext_hi_task_status
-- ----------------------------
DROP TABLE IF EXISTS `ext_hi_task_status`;
CREATE TABLE `ext_hi_task_status` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `process_instance_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程实例id',
  `task_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务id',
  `task_def_key` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务定义key',
  `task_status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='历史任务处理状态';

-- ----------------------------
-- Table structure for ext_process_detail
-- ----------------------------
DROP TABLE IF EXISTS `ext_process_detail`;
CREATE TABLE `ext_process_detail` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `model_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模型id',
  `process_definition_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '流程定义key',
  `process_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程名称',
  `task_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '任务定义key',
  `task_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名称',
  `type` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_model_id` (`model_id`) USING BTREE,
  KEY `idx_process_id_name` (`process_definition_key`,`process_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='流程详情';

-- ----------------------------
-- Table structure for ext_process_status
-- ----------------------------
DROP TABLE IF EXISTS `ext_process_status`;
CREATE TABLE `ext_process_status` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '流程实例id',
  `business_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '业务主键',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_process_instance_id` (`process_instance_id`) USING BTREE,
  KEY `idx_model_id` (`business_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='流程状态记录';

-- ----------------------------
-- Table structure for ext_process_task_rule
-- ----------------------------
DROP TABLE IF EXISTS `ext_process_task_rule`;
CREATE TABLE `ext_process_task_rule` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `process_detail_id` bigint(20) NOT NULL COMMENT '流程详细节点id',
  `task_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '目标任务定义key',
  `task_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名称',
  `act_type` int(2) NOT NULL COMMENT '流程类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_process_task_key` (`process_detail_id`,`task_def_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='流程跳转规则';

SET FOREIGN_KEY_CHECKS = 1;
