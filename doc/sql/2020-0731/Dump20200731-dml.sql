-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: zc
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fin_account`
--

DROP TABLE IF EXISTS `fin_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fin_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帐套信息编码',
  `account_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帐套名称',
  `company_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司名称',
  `tax_number` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '纳税识别号',
  `address` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `phone` varchar(13) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
  `bank_account` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '开户银行',
  `bank_card_number` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '银行卡号',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-帐套信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fin_asset`
--

DROP TABLE IF EXISTS `fin_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fin_asset` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帐套信息编码',
  `total_balance` decimal(11,2) DEFAULT NULL COMMENT '总余额',
  `freeze_balance` decimal(11,2) DEFAULT NULL COMMENT '冻结余额',
  `available_balance` decimal(11,2) DEFAULT NULL COMMENT '可用余额',
  `status` int DEFAULT NULL COMMENT '状态',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-余额信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fin_asset_record`
--

DROP TABLE IF EXISTS `fin_asset_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fin_asset_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `asset_id` bigint DEFAULT NULL COMMENT '创建人',
  `account_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帐套信息编码',
  `subject_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '科目编码',
  `receive_amount` decimal(11,2) DEFAULT NULL COMMENT '应收金额',
  `pay_amount` decimal(11,2) DEFAULT NULL COMMENT '应付金额',
  `real_receive_amount` decimal(11,2) DEFAULT NULL COMMENT '实收金额',
  `real_pay_amount` decimal(11,2) DEFAULT NULL COMMENT '实付金额',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `balance_before` decimal(11,2) DEFAULT NULL COMMENT '变动前余额',
  `balance_after` decimal(11,2) DEFAULT NULL COMMENT '变动后余额',
  `type` int DEFAULT NULL COMMENT '类型 1 收入 2 支出',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-余额记录信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fin_invest`
--

DROP TABLE IF EXISTS `fin_invest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fin_invest` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帐套信息编码',
  `invest_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '投资人名称',
  `invest_fund` decimal(11,2) DEFAULT NULL COMMENT '投资款',
  `invest_amount` decimal(11,2) DEFAULT NULL COMMENT '投资总额',
  `invest_ratio` int DEFAULT NULL COMMENT '投资比例 %',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `subject_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '科目编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-投资信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fin_subject`
--

DROP TABLE IF EXISTS `fin_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fin_subject` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `subject_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '科目编号',
  `subject_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '科目名称',
  `parent_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父科目编号',
  `subject_type` int DEFAULT NULL COMMENT '科目类型 1 资产 2负载 3权益 4成本 5其他',
  `borrow_flag` int DEFAULT NULL COMMENT '借贷方向 0:借 1:贷',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-会计科目信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report_asset`
--

DROP TABLE IF EXISTS `report_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_asset` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帐套信息编码',
  `total_asset` decimal(11,2) DEFAULT NULL COMMENT '总资产/天',
  `total_profit` decimal(11,2) DEFAULT NULL COMMENT '总利润/天',
  `total_income` decimal(11,2) DEFAULT NULL COMMENT '总收入/天',
  `total_expenditure` decimal(11,2) DEFAULT NULL COMMENT '总支出/天',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `start_time` bigint DEFAULT NULL COMMENT '开始时间',
  `end_time` bigint DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='报表-资产报表(每天统计)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `client_ip` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端IP',
  `server_ip` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务端IP',
  `server_sort` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务端端口',
  `req_method` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方法',
  `req_url` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求资源',
  `ua` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器信息',
  `res_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '响应状态码',
  `res_agreement` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求协议',
  `res_time` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `pid` bigint NOT NULL COMMENT '父ID',
  `sort` int NOT NULL COMMENT '排序',
  `icon` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `path` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '路径',
  `hidden` int NOT NULL COMMENT '是否隐藏 0:未隐藏1:已隐藏',
  `permission` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限',
  `type` int NOT NULL COMMENT '类型 0:菜单权限1:按钮权限',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL,
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL,
  `is_deleted` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单表 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL,
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL,
  `is_deleted` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `is_enable` int NOT NULL COMMENT '是否启用 0:否 1:是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色菜单表 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nick_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `username` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `status` int NOT NULL COMMENT '状态 0:未激活1:已激活2:已锁定3:已注销4::账号异常',
  `sex` int DEFAULT NULL COMMENT '性别',
  `phone` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL,
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL,
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `email` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `head_url` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user_account`
--

DROP TABLE IF EXISTS `sys_user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `account_code` bigint NOT NULL COMMENT '帐套编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户帐套关联表 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色关联表 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_work`
--

DROP TABLE IF EXISTS `sys_work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_work` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `work_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事务名称',
  `status` int DEFAULT NULL COMMENT '状态 1 待处理 2 处理中 3 已完成',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-事务表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-31 19:42:47
