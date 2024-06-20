/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : db_mypj1

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 20/06/2024 11:10:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `consignee` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '收货人',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认 0 否 1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '地址簿' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address_book
-- ----------------------------
INSERT INTO `address_book` VALUES (2, 1, '张三', '1', '1234567890', '123456', '省份', '12345678', '城市', '123456789', '区县', '详细地址', '标签', 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '顺序',
  `status` int NULL DEFAULT NULL COMMENT '分类状态 0:禁用，1:启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_category_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '菜品及套餐分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '烧烤', 1, 0, '2024-03-18 09:19:37', '2024-06-18 21:36:22', 1, 1);
INSERT INTO `category` VALUES (2, '甜点', 2, 1, '2024-04-19 11:22:43', '2024-04-19 11:22:43', 1, 1);
INSERT INTO `category` VALUES (29, '主食', 3, 0, '2024-04-19 11:22:43', '2024-04-19 11:22:43', 1, 1);
INSERT INTO `category` VALUES (30, '饮料', 4, 0, '2024-04-19 11:23:02', '2024-04-19 11:23:02', 1, 1);

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品价格',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '描述信息',
  `status` int NULL DEFAULT 1 COMMENT '0 停售 1 起售',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_dish_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '菜品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (1, '蛋炒饭', 29, 12.00, '/admin/getImage/1/1', '蛋炒饭是中国传统的美食之一。', 1, '2024-03-19 21:02:11', '2024-04-16 21:05:27', NULL, 1);
INSERT INTO `dish` VALUES (77, '豆奶', 30, 3.00, '/admin/getImage/77/1', '鲜榨非转基因豆奶', 1, '2024-04-19 11:24:46', '2024-04-19 11:24:46', 1, 1);
INSERT INTO `dish` VALUES (78, '烤羊肉串', 1, 2.00, '/admin/getImage/78/1', '香喷喷的羊肉串', 1, '2024-04-19 11:26:04', '2024-04-19 11:26:04', 1, 1);
INSERT INTO `dish` VALUES (79, '烤牛肉串', 1, 3.00, '/admin/getImage/78/1', '香喷喷的牛肉', 1, '2024-04-19 11:28:08', '2024-04-19 11:28:08', 1, 1);
INSERT INTO `dish` VALUES (80, '烤鸡腿', 1, 9.00, '/admin/getImage/80/1', '香喷喷的烤鸡腿', 1, '2024-04-19 11:28:24', '2024-06-10 21:12:53', 1, 1);

-- ----------------------------
-- Table structure for dish_flavor
-- ----------------------------
DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` bigint NOT NULL COMMENT '菜品',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '口味名称',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '口味数据list',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 155 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '菜品口味关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish_flavor
-- ----------------------------
INSERT INTO `dish_flavor` VALUES (115, 77, '冰度', '常温');
INSERT INTO `dish_flavor` VALUES (116, 77, '甜度', '冰冻');
INSERT INTO `dish_flavor` VALUES (117, 78, '辣度', '微辣');
INSERT INTO `dish_flavor` VALUES (118, 78, '甜度', '不辣');
INSERT INTO `dish_flavor` VALUES (119, 78, '甜度', '变态辣');
INSERT INTO `dish_flavor` VALUES (120, 79, '辣度', '微辣');
INSERT INTO `dish_flavor` VALUES (121, 79, '辣度', '不辣');
INSERT INTO `dish_flavor` VALUES (122, 79, '辣度', '变态辣');
INSERT INTO `dish_flavor` VALUES (123, 80, '辣度', '微辣');
INSERT INTO `dish_flavor` VALUES (124, 80, '辣度', '不辣');
INSERT INTO `dish_flavor` VALUES (125, 80, '辣度', '变态辣');
INSERT INTO `dish_flavor` VALUES (126, 84, 'taste1', 'taste2');
INSERT INTO `dish_flavor` VALUES (127, 84, 'taste3', 'taste4');
INSERT INTO `dish_flavor` VALUES (128, 84, 'taste5', 'taste6');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 0:禁用，1:启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '员工信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', 1, '2022-02-15 15:51:20', '2022-02-17 09:16:20', 10, 1);

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片地址',
  `dish_id` int NOT NULL COMMENT '逻辑外键',
  `img_index` int NOT NULL COMMENT '第几个菜品的第几张图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1803058709038850050 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图片路径\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img
-- ----------------------------
INSERT INTO `img` VALUES (1781239351140667394, 'D:\\CBBC\\IDEA_SPACE\\MYPJ\\CB_MYPJ1\\myimage\\dish_80image_1.jpg', 80, 1);
INSERT INTO `img` VALUES (1781259942501515265, 'D:\\CBBC\\IDEA_SPACE\\MYPJ\\CB_MYPJ1\\myimage\\dish_78image_1.jpg', 78, 1);
INSERT INTO `img` VALUES (1781936642281381890, 'D:\\CBBC\\IDEA_SPACE\\MYPJ\\CB_MYPJ1\\myimage\\dish_77image_1.jpg', 77, 1);
INSERT INTO `img` VALUES (1784749115854241794, 'D:\\CBBC\\IDEA_SPACE\\MYPJ\\CB_MYPJ1\\myimage\\dish_1image_1.jpg', 1, 1);
INSERT INTO `img` VALUES (1803058455270875138, 'D:\\CBBC\\IDEA_SPACE\\MYPJ\\CB_MYPJ1\\myimage\\dish_2313image_0.jpg', 2313, 0);
INSERT INTO `img` VALUES (1803058709038850050, 'D:\\CBBC\\IDEA_SPACE\\MYPJ\\CB_MYPJ1\\myimage\\dish_231image_0.jpg', 231, 0);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '名字',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '图片',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品id',
  `dish_flavor` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '订单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (5, '饼干', 'dd', 16, 2, NULL, 2, 22.00);
INSERT INTO `order_detail` VALUES (6, '饼干', 'dd', 17, 2, NULL, 1, 11.00);
INSERT INTO `order_detail` VALUES (7, '饼干', 'dd', 18, 2, NULL, 3, 33.00);
INSERT INTO `order_detail` VALUES (8, '饼干', 'dd', 19, 2, NULL, 1, 11.00);
INSERT INTO `order_detail` VALUES (9, '饼干', 'dd', 20, 2, NULL, 1, 11.00);
INSERT INTO `order_detail` VALUES (10, '饼干', 'dd', 21, 2, NULL, 1, 11.00);
INSERT INTO `order_detail` VALUES (11, '蛋炒饭', '/admin/getImage/3/1', 22, 1, NULL, 1, 12.00);
INSERT INTO `order_detail` VALUES (12, '烤鸡腿', 'chicken.jpg', 23, 80, NULL, 2, 6.00);
INSERT INTO `order_detail` VALUES (13, '烤牛肉串', '/admin/getImage/78/1', 24, 79, NULL, 1, 3.00);
INSERT INTO `order_detail` VALUES (14, '烤牛肉串', '/admin/getImage/78/1', 25, 79, NULL, 1, 3.00);
INSERT INTO `order_detail` VALUES (15, '豆奶', '/admin/getImage/77/1', 26, 77, NULL, 1, 3.00);
INSERT INTO `order_detail` VALUES (16, '烤鸡腿', '/admin/getImage/80/1', 27, 80, NULL, 1, 9.00);
INSERT INTO `order_detail` VALUES (17, '烤鸡腿', '/admin/getImage/80/1', 27, 80, NULL, 3, 27.00);
INSERT INTO `order_detail` VALUES (18, '烤鸡腿', '/admin/getImage/80/1', 28, 80, NULL, 4, 36.00);
INSERT INTO `order_detail` VALUES (19, '烤鸡腿', '/admin/getImage/80/1', 29, 80, NULL, 1, 9.00);
INSERT INTO `order_detail` VALUES (20, '烤鸡腿', '/admin/getImage/80/1', 30, 80, NULL, 1, 9.00);
INSERT INTO `order_detail` VALUES (21, '烤鸡腿', '/admin/getImage/80/1', 31, 80, NULL, 2, 18.00);
INSERT INTO `order_detail` VALUES (22, '烤鸡腿', '/admin/getImage/80/1', 32, 80, NULL, 1, 9.00);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单号',
  `status` int NOT NULL DEFAULT 1 COMMENT '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
  `user_id` bigint NOT NULL COMMENT '下单用户',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime NULL DEFAULT NULL COMMENT '结账时间',
  `pay_method` int NOT NULL DEFAULT 1 COMMENT '支付方式 1微信,2支付宝',
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '支付状态 0未支付 1已支付 2退款',
  `amount` decimal(10, 2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '备注',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '地址',
  `user_name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '用户名称',
  `consignee` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '收货人',
  `cancel_reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单取消原因',
  `rejection_reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单拒绝原因',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '订单取消时间',
  `estimated_delivery_time` datetime NULL DEFAULT NULL COMMENT '预计送达时间',
  `delivery_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '配送状态  1立即送出  0选择具体时间',
  `delivery_time` datetime NULL DEFAULT NULL COMMENT '送达时间',
  `pack_amount` int NULL DEFAULT NULL COMMENT '打包费',
  `tableware_number` int NULL DEFAULT NULL COMMENT '餐具数量',
  `tableware_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '餐具数量状态  1按餐量提供  0选择具体数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (16, '1712451771904', 5, 1, 2, '2024-04-19 09:02:52', NULL, 1, 1, 123.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', '用户取消', '拒绝订单原因', '2024-04-10 15:21:35', '2021-09-01 12:00:00', 1, '2024-06-10 22:06:40', 10, 0, 0);
INSERT INTO `orders` VALUES (17, '1712452115387', 5, 1, 2, '2024-04-18 09:08:35', NULL, 1, 2, 123.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', '用户取消', NULL, '2024-04-07 14:58:04', '2021-09-01 12:00:00', 1, NULL, 10, 0, 0);
INSERT INTO `orders` VALUES (19, '1712457570075', 6, 1, 2, '2024-04-07 10:39:30', NULL, 1, 0, 11.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', '订单超时，自动取消', NULL, '2024-04-07 21:03:00', '2021-09-01 12:00:00', 1, NULL, 10, 0, 0);
INSERT INTO `orders` VALUES (20, '1712562194005', 6, 1, 2, '2024-04-08 15:43:14', NULL, 1, 1, 11.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', NULL, NULL, '2024-06-10 22:11:51', '2021-09-01 12:00:00', 1, NULL, 10, 0, 0);
INSERT INTO `orders` VALUES (21, '1712562376645', 5, 1, 2, '2024-04-08 15:46:17', NULL, 1, 1, 11.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', NULL, NULL, NULL, '2021-09-01 12:00:00', 1, '2024-06-10 22:10:49', 10, 0, 0);
INSERT INTO `orders` VALUES (22, '1713486105786', 5, 1, 2, '2024-04-19 08:21:46', NULL, 1, 1, 12.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', NULL, NULL, NULL, '2021-09-01 12:00:00', 1, '2024-04-19 11:31:43', 10, 0, 0);
INSERT INTO `orders` VALUES (23, '1713497430878', 5, 1, 2, '2024-04-19 11:30:31', NULL, 1, 1, 6.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', NULL, NULL, NULL, '2021-09-01 12:00:00', 1, '2024-04-19 11:31:47', 10, 0, 0);
INSERT INTO `orders` VALUES (24, '1714281138188', 5, 1, 2, '2024-04-28 13:12:18', NULL, 1, 0, 3.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', '订单超时，自动取消', NULL, '2024-04-28 19:32:00', '2021-09-01 12:00:00', 1, NULL, 10, 1, 2);
INSERT INTO `orders` VALUES (25, '1714281281242', 5, 1, 2, '2024-04-28 13:14:41', NULL, 1, 0, 3.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', '订单超时，自动取消', NULL, '2024-04-28 19:32:01', '2021-09-01 12:00:00', 1, NULL, 10, 1, 2);
INSERT INTO `orders` VALUES (26, '1714351225682', 5, 1, 2, '2024-04-29 08:40:26', NULL, 1, 1, 3.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', NULL, NULL, NULL, '2021-09-01 12:00:00', 1, '2024-04-29 08:45:21', 10, 1, 2);
INSERT INTO `orders` VALUES (27, '1714353971845', 5, 1, 2, '2024-04-29 09:26:12', NULL, 1, 1, 36.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', NULL, NULL, NULL, '2021-09-01 12:00:00', 1, '2024-04-29 10:13:17', 10, 1, 2);
INSERT INTO `orders` VALUES (28, '1714435962069', 5, 1, 2, '2024-04-30 08:12:42', NULL, 1, 1, 36.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', NULL, NULL, NULL, '2021-09-01 12:00:00', 1, '2024-04-30 08:14:27', 10, 1, 2);
INSERT INTO `orders` VALUES (29, '1714436351285', 6, 1, 2, '2024-04-30 08:19:11', NULL, 1, 0, 9.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', '用户取消', NULL, '2024-04-30 08:19:34', '2021-09-01 12:00:00', 1, NULL, 10, 1, 2);
INSERT INTO `orders` VALUES (30, '1718718162806', 5, 1, 2, '2024-06-18 21:42:43', NULL, 1, 1, 9.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', NULL, NULL, NULL, '2021-09-01 12:00:00', 1, '2024-06-20 10:51:09', 10, 1, 2);
INSERT INTO `orders` VALUES (31, '1718718638169', 6, 1, 2, '2024-06-18 21:50:38', NULL, 1, 0, 18.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', '订单超时，自动取消', NULL, '2024-06-20 10:07:00', '2021-09-01 12:00:00', 1, NULL, 10, 1, 2);
INSERT INTO `orders` VALUES (32, '1718718647597', 6, 1, 2, '2024-06-18 21:50:48', NULL, 1, 0, 9.00, '这是一条备注', '1234567890', '详细地址', 'cb', '张三', '订单超时，自动取消', NULL, '2024-06-20 10:07:00', '2021-09-01 12:00:00', 1, NULL, 10, 1, 2);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '商品名称',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '图片',
  `user_id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint NOT NULL COMMENT '菜品id',
  `flavor_id` bigint NOT NULL COMMENT '口味id',
  `number` int NOT NULL DEFAULT 1 COMMENT '数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态：0失效 1有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '性别',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'cb', '123', '1', 'aa', '2024-03-28 20:39:17');
INSERT INTO `user` VALUES (4, 'John Doe', '1234567890', '1', 'https://example.com/avatar.jpg', '2024-04-27 08:50:41');
INSERT INTO `user` VALUES (5, 'csk', '18922073500', '1', 'https://example.com/avatar.jpg', '2024-04-27 08:50:41');
INSERT INTO `user` VALUES (7, 'ccbb', '192712111', '1', 'https://example.com/avatar.jpg', '2024-04-19 08:50:41');
INSERT INTO `user` VALUES (8, 'bluishcb', '19527456811', '1', 'https://example.com/avatar.jpg', '2024-04-29 09:03:05');
INSERT INTO `user` VALUES (9, 'cbcb', '123456', '1', 'https://example.com/avatar.jpg', '2024-06-18 08:17:53');

SET FOREIGN_KEY_CHECKS = 1;
