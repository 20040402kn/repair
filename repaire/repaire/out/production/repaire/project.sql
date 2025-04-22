/*
 Navicat Premium Data Transfer

 Source Server         : Mysql80
 Source Server Type    : MySQL
 Source Server Version : 80040
 Source Host           : localhost:3307
 Source Schema         : project

 Target Server Type    : MySQL
 Target Server Version : 80040
 File Encoding         : 65001

 Date: 06/01/2025 07:08:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_build
-- ----------------------------
DROP TABLE IF EXISTS `t_build`;
CREATE TABLE `t_build`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `build_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_build
-- ----------------------------
INSERT INTO `t_build` VALUES (1, '11号宿舍楼', '西校区');
INSERT INTO `t_build` VALUES (2, '23号宿舍楼', '西校区');
INSERT INTO `t_build` VALUES (3, '9号宿舍楼', '西校区');
INSERT INTO `t_build` VALUES (4, '9号宿舍楼', '东校区');

-- ----------------------------
-- Table structure for t_build_dorm
-- ----------------------------
DROP TABLE IF EXISTS `t_build_dorm`;
CREATE TABLE `t_build_dorm`  (
  `id` int(0) NOT NULL,
  `build_id` int(0) NOT NULL,
  `dorm_id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_8`(`build_id`) USING BTREE,
  INDEX `t_build_dorm_t_dorm_id_fk`(`dorm_id`) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`build_id`) REFERENCES `t_build` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_build_dorm_t_dorm_id_fk` FOREIGN KEY (`dorm_id`) REFERENCES `t_dorm` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_build_dorm
-- ----------------------------
INSERT INTO `t_build_dorm` VALUES (1, 1, 1);
INSERT INTO `t_build_dorm` VALUES (2, 2, 2);
INSERT INTO `t_build_dorm` VALUES (3, 2, 9);
INSERT INTO `t_build_dorm` VALUES (4, 3, 3);
INSERT INTO `t_build_dorm` VALUES (5, 4, 10);

-- ----------------------------
-- Table structure for t_college
-- ----------------------------
DROP TABLE IF EXISTS `t_college`;
CREATE TABLE `t_college`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_college
-- ----------------------------
INSERT INTO `t_college` VALUES (1, '计算机学院');
INSERT INTO `t_college` VALUES (2, '电子工程学院');

-- ----------------------------
-- Table structure for t_college_major
-- ----------------------------
DROP TABLE IF EXISTS `t_college_major`;
CREATE TABLE `t_college_major`  (
  `college_id` int(0) NOT NULL,
  `major_id` int(0) NOT NULL,
  PRIMARY KEY (`college_id`, `major_id`) USING BTREE,
  INDEX `FK_CollegeMajor_College`(`college_id`) USING BTREE,
  INDEX `FK_CollegeMajor_Major`(`major_id`) USING BTREE,
  CONSTRAINT `FK_CollegeMajor_College` FOREIGN KEY (`college_id`) REFERENCES `t_college` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_CollegeMajor_Major` FOREIGN KEY (`major_id`) REFERENCES `t_major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_dorm
-- ----------------------------
DROP TABLE IF EXISTS `t_dorm`;
CREATE TABLE `t_dorm`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dorm_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `capacity` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dorm
-- ----------------------------
INSERT INTO `t_dorm` VALUES (1, '550', 4);
INSERT INTO `t_dorm` VALUES (2, '548', 6);
INSERT INTO `t_dorm` VALUES (3, '345', 6);
INSERT INTO `t_dorm` VALUES (9, '123', 6);
INSERT INTO `t_dorm` VALUES (10, '110', 6);

-- ----------------------------
-- Table structure for t_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `t_evaluation`;
CREATE TABLE `t_evaluation`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `request_id` int(0) DEFAULT NULL,
  `student_id` int(0) DEFAULT NULL,
  `rating` int(0) DEFAULT NULL,
  `comment` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_RepairEvaluation_Request`(`request_id`) USING BTREE,
  INDEX `FK_RepairEvaluation_Student`(`student_id`) USING BTREE,
  CONSTRAINT `FK_RepairEvaluation_Request` FOREIGN KEY (`request_id`) REFERENCES `t_request` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_RepairEvaluation_Student` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_item
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '报修项目名称',
  `remark` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '报修项目描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '报修项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_item
-- ----------------------------
INSERT INTO `t_item` VALUES (1, '阀门开关类', '阀门开关拧不紧');
INSERT INTO `t_item` VALUES (2, '水龙头类', '水龙头漏水');
INSERT INTO `t_item` VALUES (4, '开关插座类', '');

-- ----------------------------
-- Table structure for t_item_type
-- ----------------------------
DROP TABLE IF EXISTS `t_item_type`;
CREATE TABLE `t_item_type`  (
  `item_id` int(0) NOT NULL DEFAULT 0,
  `type_id` int(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`item_id`, `type_id`) USING BTREE,
  INDEX `item_id`(`item_id`) USING BTREE,
  INDEX `type_id`(`type_id`) USING BTREE,
  CONSTRAINT `item_id` FOREIGN KEY (`item_id`) REFERENCES `t_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_item_type
-- ----------------------------
INSERT INTO `t_item_type` VALUES (1, 1);
INSERT INTO `t_item_type` VALUES (2, 1);
INSERT INTO `t_item_type` VALUES (4, 2);

-- ----------------------------
-- Table structure for t_major
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `major_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_major
-- ----------------------------
INSERT INTO `t_major` VALUES (1, '软件工程');
INSERT INTO `t_major` VALUES (2, '通信工程');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `link_url` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `path` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `priority` int(0) DEFAULT NULL,
  `icon` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `parent_menuId` int(0) DEFAULT NULL,
  `level` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_13`(`parent_menuId`) USING BTREE,
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parent_menuId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '宿舍', NULL, '2', 1, 'fa-user-md', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (2, '会员档案', 'request.html', '/2-1', 1, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (3, '体检上传', NULL, '/2-2', 2, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (4, '会员统计', NULL, '/2-3', 3, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (5, '报修管理', NULL, '3', 2, 'fa-tty', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (6, '预约列表', 'orderslist.html', '/3-1', 1, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (7, '预约设置', 'ordersetting.html', '/3-2', 2, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (8, '分配管理', 'allocation.html', '/3-3', 3, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (9, '宿舍管理', 'dorm.html', '/3-4', 4, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (10, '报修项管理', 'item.html', '/3-5', 5, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (11, '健康评估', NULL, '4', 3, 'fa-stethoscope', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (12, '中医体质辨识', NULL, '/4-1', 1, NULL, NULL, 11, 2);
INSERT INTO `t_menu` VALUES (13, '统计分析', NULL, '5', 4, 'fa-heartbeat', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (15, '工作列表', NULL, '6', 5, 'fa-users', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (16, '菜单管理', 'menu.html', '/6-1', 1, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (17, '权限管理', 'permission.html', '/6-2', 2, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (18, '角色管理', 'role.html', '/6-3', 3, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (19, '用户管理', 'user.html', '/6-4', 4, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (20, '报修项占比', 'report_item.html', '/5-2', 2, NULL, NULL, 13, 2);
INSERT INTO `t_menu` VALUES (21, '报修数据', 'report_business.html', '/5-3', 3, NULL, NULL, 13, 2);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) DEFAULT NULL COMMENT '员会id',
  `order_date` date DEFAULT NULL COMMENT '约预日期',
  `order_type` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '约预类型 电话预约/微信预约',
  `order_status` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '预约状态（是否到诊）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_member_id`(`user_id`) USING BTREE,
  CONSTRAINT `key_member_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (17, 1, '2022-04-16', '微信预约', '未到店');
INSERT INTO `t_order` VALUES (18, 2, '2021-03-24', '微信公众号预约', '未到店');
INSERT INTO `t_order` VALUES (19, 3, '2021-04-19', '微信公众号预约', '未到店');
INSERT INTO `t_order` VALUES (20, 1, '2021-04-15', '微信公众号预约', '未到店');
INSERT INTO `t_order` VALUES (21, 2, '2022-04-18', '微信预约', '未到店');
INSERT INTO `t_order` VALUES (22, 3, '2022-04-19', '微信预约', '未到店');

-- ----------------------------
-- Table structure for t_ordersetting
-- ----------------------------
DROP TABLE IF EXISTS `t_ordersetting`;
CREATE TABLE `t_ordersetting`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL COMMENT '约预日期',
  `number` int(0) DEFAULT NULL COMMENT '可预约人数',
  `reservations` int(0) DEFAULT 0 COMMENT '已预约人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 269 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_ordersetting
-- ----------------------------
INSERT INTO `t_ordersetting` VALUES (191, '2022-04-12', 100, 0);
INSERT INTO `t_ordersetting` VALUES (192, '2022-04-13', 80, 0);
INSERT INTO `t_ordersetting` VALUES (193, '2022-04-14', 120, 0);
INSERT INTO `t_ordersetting` VALUES (194, '2022-04-15', 100, 0);
INSERT INTO `t_ordersetting` VALUES (195, '2022-04-16', 100, 99);
INSERT INTO `t_ordersetting` VALUES (197, '2022-04-18', 100, 89);
INSERT INTO `t_ordersetting` VALUES (198, '2022-04-19', 100, 1);
INSERT INTO `t_ordersetting` VALUES (199, '2022-04-20', 100, 78);
INSERT INTO `t_ordersetting` VALUES (200, '2022-04-21', 200, 90);
INSERT INTO `t_ordersetting` VALUES (201, '2022-04-22', 100, 0);
INSERT INTO `t_ordersetting` VALUES (202, '2022-04-23', 100, 100);
INSERT INTO `t_ordersetting` VALUES (203, '2022-04-24', 100, 0);
INSERT INTO `t_ordersetting` VALUES (204, '2022-04-25', 100, 0);
INSERT INTO `t_ordersetting` VALUES (205, '2022-04-26', 100, 0);
INSERT INTO `t_ordersetting` VALUES (206, '2022-04-27', 100, 0);
INSERT INTO `t_ordersetting` VALUES (207, '2022-04-28', 100, 0);
INSERT INTO `t_ordersetting` VALUES (208, '2022-04-29', 100, 0);
INSERT INTO `t_ordersetting` VALUES (209, '2022-04-30', 100, 0);
INSERT INTO `t_ordersetting` VALUES (210, '2022-05-01', 100, 0);
INSERT INTO `t_ordersetting` VALUES (211, '2022-05-02', 100, 0);
INSERT INTO `t_ordersetting` VALUES (212, '2022-05-03', 100, 0);
INSERT INTO `t_ordersetting` VALUES (213, '2022-05-04', 100, 0);
INSERT INTO `t_ordersetting` VALUES (214, '2022-05-05', 100, 0);
INSERT INTO `t_ordersetting` VALUES (215, '2022-05-06', 100, 0);
INSERT INTO `t_ordersetting` VALUES (216, '2022-05-07', 100, 0);
INSERT INTO `t_ordersetting` VALUES (217, '2022-05-08', 100, 0);
INSERT INTO `t_ordersetting` VALUES (218, '2022-05-09', 100, 0);
INSERT INTO `t_ordersetting` VALUES (219, '2022-05-10', 100, 0);
INSERT INTO `t_ordersetting` VALUES (220, '2022-05-11', 100, 0);
INSERT INTO `t_ordersetting` VALUES (221, '2022-05-12', 100, 0);
INSERT INTO `t_ordersetting` VALUES (222, '2022-05-13', 100, 0);
INSERT INTO `t_ordersetting` VALUES (223, '2022-05-14', 100, 0);
INSERT INTO `t_ordersetting` VALUES (224, '2022-05-15', 100, 0);
INSERT INTO `t_ordersetting` VALUES (225, '2022-05-16', 100, 0);
INSERT INTO `t_ordersetting` VALUES (226, '2022-05-17', 100, 0);
INSERT INTO `t_ordersetting` VALUES (227, '2022-05-18', 100, 0);
INSERT INTO `t_ordersetting` VALUES (228, '2022-05-19', 100, 0);
INSERT INTO `t_ordersetting` VALUES (229, '2022-05-20', 100, 0);
INSERT INTO `t_ordersetting` VALUES (230, '2022-05-21', 100, 0);
INSERT INTO `t_ordersetting` VALUES (231, '2022-05-25', 20, 0);
INSERT INTO `t_ordersetting` VALUES (232, '2024-12-31', 30, 0);
INSERT INTO `t_ordersetting` VALUES (233, '2025-01-01', 31, 0);
INSERT INTO `t_ordersetting` VALUES (234, '2025-01-02', 32, 0);
INSERT INTO `t_ordersetting` VALUES (235, '2025-01-03', 33, 0);
INSERT INTO `t_ordersetting` VALUES (236, '2025-01-04', 34, 0);
INSERT INTO `t_ordersetting` VALUES (237, '2025-01-05', 35, 0);
INSERT INTO `t_ordersetting` VALUES (238, '2025-01-06', 36, 0);
INSERT INTO `t_ordersetting` VALUES (239, '2025-01-07', 37, 0);
INSERT INTO `t_ordersetting` VALUES (240, '2025-01-08', 38, 0);
INSERT INTO `t_ordersetting` VALUES (241, '2025-01-09', 39, 0);
INSERT INTO `t_ordersetting` VALUES (242, '2025-01-10', 40, 0);
INSERT INTO `t_ordersetting` VALUES (243, '2025-01-11', 41, 0);
INSERT INTO `t_ordersetting` VALUES (244, '2025-01-12', 42, 0);
INSERT INTO `t_ordersetting` VALUES (245, '2025-01-13', 43, 0);
INSERT INTO `t_ordersetting` VALUES (246, '2025-01-14', 44, 0);
INSERT INTO `t_ordersetting` VALUES (247, '2025-01-15', 45, 0);
INSERT INTO `t_ordersetting` VALUES (248, '2025-01-16', 46, 0);
INSERT INTO `t_ordersetting` VALUES (249, '2025-01-17', 47, 0);
INSERT INTO `t_ordersetting` VALUES (250, '2025-01-18', 48, 0);
INSERT INTO `t_ordersetting` VALUES (251, '2025-01-19', 49, 0);
INSERT INTO `t_ordersetting` VALUES (252, '2025-01-20', 50, 0);
INSERT INTO `t_ordersetting` VALUES (253, '2025-01-21', 51, 0);
INSERT INTO `t_ordersetting` VALUES (254, '2025-01-22', 52, 0);
INSERT INTO `t_ordersetting` VALUES (255, '2025-01-23', 53, 0);
INSERT INTO `t_ordersetting` VALUES (256, '2025-01-24', 54, 0);
INSERT INTO `t_ordersetting` VALUES (257, '2025-01-25', 55, 0);
INSERT INTO `t_ordersetting` VALUES (258, '2025-01-26', 56, 0);
INSERT INTO `t_ordersetting` VALUES (259, '2025-01-27', 57, 0);
INSERT INTO `t_ordersetting` VALUES (260, '2025-01-28', 58, 0);
INSERT INTO `t_ordersetting` VALUES (261, '2025-01-29', 59, 0);
INSERT INTO `t_ordersetting` VALUES (262, '2025-01-30', 60, 0);
INSERT INTO `t_ordersetting` VALUES (263, '2025-01-31', 61, 0);
INSERT INTO `t_ordersetting` VALUES (264, '2025-02-01', 62, 0);
INSERT INTO `t_ordersetting` VALUES (265, '2025-02-02', 63, 0);
INSERT INTO `t_ordersetting` VALUES (266, '2025-02-03', 64, 0);
INSERT INTO `t_ordersetting` VALUES (267, '2025-02-04', 65, 0);
INSERT INTO `t_ordersetting` VALUES (268, '2025-02-05', 66, 0);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `keyword` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '新增检查项', 'CHECKITEM_ADD', NULL);
INSERT INTO `t_permission` VALUES (2, '删除检查项', 'CHECKITEM_DELETE', NULL);
INSERT INTO `t_permission` VALUES (3, '编辑检查项', 'CHECKITEM_EDIT', NULL);
INSERT INTO `t_permission` VALUES (4, '查询检查项', 'CHECKITEM_QUERY', NULL);
INSERT INTO `t_permission` VALUES (5, '新增检查组', 'CHECKGROUP_ADD', NULL);
INSERT INTO `t_permission` VALUES (6, '删除检查组', 'CHECKGROUP_DELETE', NULL);
INSERT INTO `t_permission` VALUES (7, '编辑检查组', 'CHECKGROUP_EDIT', NULL);
INSERT INTO `t_permission` VALUES (8, '查询检查组', 'CHECKGROUP_QUERY', NULL);
INSERT INTO `t_permission` VALUES (9, '新增套餐', 'SETMEAL_ADD', NULL);
INSERT INTO `t_permission` VALUES (10, '删除套餐', 'SETMEAL_DELETE', NULL);
INSERT INTO `t_permission` VALUES (11, '编辑套餐', 'SETMEAL_EDIT', NULL);
INSERT INTO `t_permission` VALUES (12, '查询套餐', 'SETMEAL_QUERY', NULL);
INSERT INTO `t_permission` VALUES (13, '预约设置', 'ORDERSETTING', NULL);
INSERT INTO `t_permission` VALUES (14, '查看统计报表', 'REPORT_VIEW', NULL);
INSERT INTO `t_permission` VALUES (15, '新增菜单', 'MENU_ADD', NULL);
INSERT INTO `t_permission` VALUES (16, '删除菜单', 'MENU_DELETE', NULL);
INSERT INTO `t_permission` VALUES (17, '编辑菜单', 'MENU_EDIT', NULL);
INSERT INTO `t_permission` VALUES (18, '查询菜单', 'MENU_QUERY', NULL);
INSERT INTO `t_permission` VALUES (19, '新增角色', 'ROLE_ADD', NULL);
INSERT INTO `t_permission` VALUES (20, '删除角色', 'ROLE_DELETE', NULL);
INSERT INTO `t_permission` VALUES (21, '编辑角色', 'ROLE_EDIT', NULL);
INSERT INTO `t_permission` VALUES (22, '查询角色', 'ROLE_QUERY', NULL);
INSERT INTO `t_permission` VALUES (23, '新增用户', 'USER_ADD', NULL);
INSERT INTO `t_permission` VALUES (24, '删除用户', 'USER_DELETE', NULL);
INSERT INTO `t_permission` VALUES (25, '编辑用户', 'USER_EDIT', NULL);
INSERT INTO `t_permission` VALUES (26, '查询用户', 'USER_QUERY', NULL);

-- ----------------------------
-- Table structure for t_request
-- ----------------------------
DROP TABLE IF EXISTS `t_request`;
CREATE TABLE `t_request`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `worker_id` int(0) DEFAULT NULL,
  `request_date` date NOT NULL,
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
  `status_id` int(0) DEFAULT NULL,
  `item_id` int(0) DEFAULT NULL COMMENT '报修项目ID',
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_RepairRequest_Status`(`worker_id`) USING BTREE,
  INDEX `FK_RepairRequest_RepairWorker`(`student_id`) USING BTREE,
  INDEX `FK_RepairRequest_Student`(`status_id`) USING BTREE,
  INDEX `FK_RepairRequest_RepairItem`(`item_id`) USING BTREE,
  CONSTRAINT `FK_RepairRequest_RepairItem` FOREIGN KEY (`item_id`) REFERENCES `t_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_RepairRequest_RepairWorker` FOREIGN KEY (`worker_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_RepairRequest_Student` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_request_t_status_id_fk` FOREIGN KEY (`status_id`) REFERENCES `t_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_request
-- ----------------------------
INSERT INTO `t_request` VALUES (1, 2, '1', 4, '2025-01-02', '3', 1, 1, '1.jpg');
INSERT INTO `t_request` VALUES (2, 3, '1', 1, '2024-12-31', NULL, 4, 2, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `keyword` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', 'ROLE_ADMIN', NULL);
INSERT INTO `t_role` VALUES (2, '学生', 'ROLE_STUDENT', NULL);
INSERT INTO `t_role` VALUES (3, '教职工', 'ROLE_TEACHER', NULL);
INSERT INTO `t_role` VALUES (4, '维修人员', 'ROLE_REPAIRMAN', NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `role_id` int(0) NOT NULL,
  `menu_id` int(0) NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `FK_Reference_10`(`menu_id`) USING BTREE,
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1);
INSERT INTO `t_role_menu` VALUES (2, 1);
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (2, 2);
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (2, 3);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (2, 4);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (1, 7);
INSERT INTO `t_role_menu` VALUES (1, 8);
INSERT INTO `t_role_menu` VALUES (1, 9);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `role_id` int(0) NOT NULL,
  `permission_id` int(0) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FK_Reference_12`(`permission_id`) USING BTREE,
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1);
INSERT INTO `t_role_permission` VALUES (2, 1);
INSERT INTO `t_role_permission` VALUES (1, 2);
INSERT INTO `t_role_permission` VALUES (1, 3);
INSERT INTO `t_role_permission` VALUES (2, 3);
INSERT INTO `t_role_permission` VALUES (1, 4);
INSERT INTO `t_role_permission` VALUES (2, 4);
INSERT INTO `t_role_permission` VALUES (1, 5);
INSERT INTO `t_role_permission` VALUES (2, 5);
INSERT INTO `t_role_permission` VALUES (1, 6);
INSERT INTO `t_role_permission` VALUES (2, 6);
INSERT INTO `t_role_permission` VALUES (1, 7);
INSERT INTO `t_role_permission` VALUES (2, 7);
INSERT INTO `t_role_permission` VALUES (1, 8);
INSERT INTO `t_role_permission` VALUES (2, 8);
INSERT INTO `t_role_permission` VALUES (1, 9);
INSERT INTO `t_role_permission` VALUES (2, 9);
INSERT INTO `t_role_permission` VALUES (1, 10);
INSERT INTO `t_role_permission` VALUES (2, 10);
INSERT INTO `t_role_permission` VALUES (1, 11);
INSERT INTO `t_role_permission` VALUES (2, 11);
INSERT INTO `t_role_permission` VALUES (1, 12);
INSERT INTO `t_role_permission` VALUES (2, 12);
INSERT INTO `t_role_permission` VALUES (1, 13);
INSERT INTO `t_role_permission` VALUES (2, 13);
INSERT INTO `t_role_permission` VALUES (1, 14);
INSERT INTO `t_role_permission` VALUES (2, 14);
INSERT INTO `t_role_permission` VALUES (1, 15);
INSERT INTO `t_role_permission` VALUES (1, 16);
INSERT INTO `t_role_permission` VALUES (1, 17);
INSERT INTO `t_role_permission` VALUES (1, 18);
INSERT INTO `t_role_permission` VALUES (1, 19);
INSERT INTO `t_role_permission` VALUES (1, 20);
INSERT INTO `t_role_permission` VALUES (1, 21);
INSERT INTO `t_role_permission` VALUES (1, 22);
INSERT INTO `t_role_permission` VALUES (1, 23);
INSERT INTO `t_role_permission` VALUES (1, 24);
INSERT INTO `t_role_permission` VALUES (1, 25);
INSERT INTO `t_role_permission` VALUES (1, 26);

-- ----------------------------
-- Table structure for t_status
-- ----------------------------
DROP TABLE IF EXISTS `t_status`;
CREATE TABLE `t_status`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_status
-- ----------------------------
INSERT INTO `t_status` VALUES (1, '未分配');
INSERT INTO `t_status` VALUES (2, '已分配待处理');
INSERT INTO `t_status` VALUES (3, '处理中');
INSERT INTO `t_status` VALUES (4, '已处理');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, '水');
INSERT INTO `t_type` VALUES (2, '电');
INSERT INTO `t_type` VALUES (3, '校园网');
INSERT INTO `t_type` VALUES (4, '灯');
INSERT INTO `t_type` VALUES (5, '空调');
INSERT INTO `t_type` VALUES (6, '门');
INSERT INTO `t_type` VALUES (7, '窗户');

-- ----------------------------
-- Table structure for t_unit
-- ----------------------------
DROP TABLE IF EXISTS `t_unit`;
CREATE TABLE `t_unit`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '维修单位名称',
  `contact_person` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '维修单位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_unit
-- ----------------------------
INSERT INTO `t_unit` VALUES (1, '维修中心A', '张三', '13800138000', '主校区维修楼');
INSERT INTO `t_unit` VALUES (2, '维修中心B', '李四', '13900139000', '主校区维修楼');

-- ----------------------------
-- Table structure for t_unit_user
-- ----------------------------
DROP TABLE IF EXISTS `t_unit_user`;
CREATE TABLE `t_unit_user`  (
  `unit_id` int(0) NOT NULL,
  `user_id` int(0) NOT NULL,
  PRIMARY KEY (`unit_id`, `user_id`) USING BTREE,
  INDEX `FK_RepairUnitUser_RepairUnit`(`unit_id`) USING BTREE,
  INDEX `FK_RepairUnitUser_User`(`user_id`) USING BTREE,
  CONSTRAINT `FK_RepairUnitUser_RepairUnit` FOREIGN KEY (`unit_id`) REFERENCES `t_unit` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_RepairUnitUser_User` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '维修单位与用户联系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_unit_user
-- ----------------------------
INSERT INTO `t_unit_user` VALUES (1, 4);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `gender` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `password` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `remark` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `station` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, NULL, NULL, 'admin', 'yXVUkR45PFz0UfpbDB8/ew==', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (2, NULL, NULL, 'xiaoming', '123456', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (3, NULL, NULL, 'test', '123456', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (4, NULL, NULL, 'lzy', '123456', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_build
-- ----------------------------
DROP TABLE IF EXISTS `t_user_build`;
CREATE TABLE `t_user_build`  (
  `user_id` int(0) NOT NULL,
  `builddorm_id` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `builddorm_id`) USING BTREE,
  INDEX `FK_UserBuild_User`(`user_id`) USING BTREE,
  INDEX `FK_UserBuild_Build`(`builddorm_id`) USING BTREE,
  CONSTRAINT `FK_UserBuild_User` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_build_t_build_dorm_id_fk` FOREIGN KEY (`builddorm_id`) REFERENCES `t_build_dorm` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_build
-- ----------------------------
INSERT INTO `t_user_build` VALUES (1, 1);
INSERT INTO `t_user_build` VALUES (2, 2);

-- ----------------------------
-- Table structure for t_user_major_worker
-- ----------------------------
DROP TABLE IF EXISTS `t_user_major_worker`;
CREATE TABLE `t_user_major_worker`  (
  `user_id` int(0) NOT NULL COMMENT '用户ID',
  `major_id` int(0) NOT NULL COMMENT '专业ID',
  `tutor_id` int(0) NOT NULL COMMENT '工人ID',
  PRIMARY KEY (`user_id`, `major_id`, `tutor_id`) USING BTREE,
  INDEX `FK_UserMajorWorker_User`(`user_id`) USING BTREE,
  INDEX `FK_UserMajorWorker_Major`(`major_id`) USING BTREE,
  INDEX `FK_UserMajorWorker_Tutor`(`tutor_id`) USING BTREE,
  CONSTRAINT `FK_UserMajorWorker_Major` FOREIGN KEY (`major_id`) REFERENCES `t_major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_UserMajorWorker_Tutor` FOREIGN KEY (`tutor_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_UserMajorWorker_User` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户与工人与专业联系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_major_worker
-- ----------------------------
INSERT INTO `t_user_major_worker` VALUES (1, 1, 3);
INSERT INTO `t_user_major_worker` VALUES (2, 2, 3);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `user_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FK_Reference_8`(`role_id`) USING BTREE,
  CONSTRAINT `FK_Reference_71` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_81` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (2, 2);
INSERT INTO `t_user_role` VALUES (3, 3);
INSERT INTO `t_user_role` VALUES (4, 4);

SET FOREIGN_KEY_CHECKS = 1;
