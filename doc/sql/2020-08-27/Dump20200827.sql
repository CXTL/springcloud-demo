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
-- Dumping data for table `fin_account`
--

LOCK TABLES `fin_account` WRITE;
/*!40000 ALTER TABLE `fin_account` DISABLE KEYS */;
INSERT INTO `fin_account` VALUES (1,'001','中国总公司','中国总公司','123','123','123','123','123',-1,1596176964243,-1,1596176964243,0,'123'),(2,'002','杭州分公司','杭州分公司','11','11','11','11','11',-1,1596176983697,-1,1596176983697,0,'11'),(3,'003','武汉分公司','武汉分公司','111','22','22','22','22',-1,1596177005442,-1,1596177005442,0,'22');
/*!40000 ALTER TABLE `fin_account` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-余额信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_asset`
--

LOCK TABLES `fin_asset` WRITE;
/*!40000 ALTER TABLE `fin_asset` DISABLE KEYS */;
INSERT INTO `fin_asset` VALUES (1,'001',623.00,133.00,623.00,1,-1,1596188637948,-1,1597058533697,0,'test'),(2,'002',1510.00,100.00,1510.00,1,-1,1596189171095,-1,1597058349007,0,'test'),(3,'003',379.00,0.00,379.00,0,-1,1596516116030,-1,1596531925883,0,NULL);
/*!40000 ALTER TABLE `fin_asset` ENABLE KEYS */;
UNLOCK TABLES;

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
  `asset_date` bigint DEFAULT NULL COMMENT '收支日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-余额记录信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_asset_record`
--

LOCK TABLES `fin_asset_record` WRITE;
/*!40000 ALTER TABLE `fin_asset_record` DISABLE KEYS */;
INSERT INTO `fin_asset_record` VALUES (1,1,'001','001',133.00,133.00,133.00,133.00,266.00,133.00,133.00,1,-1,1596188637961,-1,1596188637961,0,'13',NULL),(2,2,'002','001',100.00,100.00,0.00,0.00,0.00,100.00,100.00,1,-1,1596189171103,-1,1596189171103,0,'0',NULL),(3,1,'001','001',100.00,100.00,100.00,100.00,200.00,100.00,100.00,1,-1,1596191087272,-1,1596191087272,0,'tet',NULL),(4,1,'001','001',122.00,122.00,0.00,0.00,0.00,333.00,333.00,1,-1,1596191579722,-1,1596191579722,0,'122',NULL),(5,1,'001','001',100.00,0.00,100.00,0.00,100.00,433.00,333.00,1,-1,1596192129025,-1,1596192129025,0,'100',NULL),(6,2,'002','001',144.00,0.00,144.00,0.00,144.00,244.00,100.00,1,-1,1596507595325,-1,1596507595325,0,'test',NULL),(7,2,'002','001',100.00,100.00,10.00,10.00,0.00,244.00,244.00,2,-1,1596507620779,-1,1596507620779,0,'test',NULL),(8,3,'003','001',155.00,1.00,100.00,1.00,99.00,198.00,99.00,1,-1,1596516116035,-1,1596516116035,0,'test',NULL),(9,2,'002','001',100.00,0.00,100.00,0.00,100.00,344.00,244.00,1,-1,1596516161680,-1,1596516161680,0,'100',NULL),(10,2,'002','001',133.00,10.00,133.00,0.00,133.00,477.00,344.00,1,-1,1596516186202,-1,1596516186202,0,'133',NULL),(11,2,'002','001',411.00,1.00,411.00,1.00,410.00,887.00,477.00,1,-1,1596516211573,-1,1596516211573,0,'test',NULL),(12,2,'002','334',100.00,0.00,100.00,0.00,100.00,987.00,887.00,1,-1,1596521007609,-1,1596521007609,0,'100',NULL),(13,2,'002','332',0.00,100.00,0.00,100.00,100.00,1087.00,987.00,0,-1,1596521035983,-1,1596521035983,0,'100',NULL),(14,2,'002','443',100.00,0.00,100.00,0.00,100.00,1187.00,1087.00,1,-1,1596521054778,-1,1596521054778,0,'100',NULL),(15,3,'003','334',0.00,100.00,0.00,100.00,100.00,199.00,99.00,2,-1,1596521076060,-1,1596521076060,0,'100',NULL),(16,2,'002','334',166.00,0.00,33.00,0.00,33.00,1220.00,1187.00,1,-1,1596531583923,-1,1596531583923,0,'166',NULL),(17,3,'003','443',145.00,10.00,100.00,10.00,90.00,289.00,199.00,2,-1,1596531646908,-1,1596531646908,0,'200',NULL),(18,3,'003','443',100.00,10.00,100.00,10.00,90.00,379.00,289.00,1,-1,1596531925884,-1,1596531925884,0,'100',NULL),(19,1,'001','334',100.00,0.00,100.00,0.00,100.00,533.00,433.00,1,-1,1596610503858,-1,1596610503858,0,'100',NULL),(20,2,'002','332',20.00,10.00,10.00,0.00,10.00,1230.00,1220.00,1,-1,1597053988773,-1,1597053988773,0,'test',1595692800000),(21,2,'002','443',10.00,10.00,200.00,10.00,190.00,1420.00,1230.00,1,-1,1597058284472,-1,1597058284472,0,'test',1597680000000),(22,2,'002','334',100.00,10.00,100.00,10.00,90.00,1510.00,1420.00,2,-1,1597058349013,-1,1597058349013,0,'test',1598457600000),(23,1,'001','443',100.00,10.00,100.00,10.00,90.00,623.00,533.00,1,-1,1597058533700,-1,1597058533700,0,'test',1597680000000);
/*!40000 ALTER TABLE `fin_asset_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_invest_flow`
--

DROP TABLE IF EXISTS `fin_invest_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fin_invest_flow` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帐套信息编码',
  `investor_id` bigint DEFAULT NULL COMMENT '投资人id',
  `actual_invest_amount` decimal(11,2) DEFAULT NULL COMMENT '实际投资金额',
  `total_invest_amount` decimal(11,2) DEFAULT NULL COMMENT '投资总额',
  `should_invest_amount` decimal(11,2) DEFAULT NULL COMMENT '应投资金额',
  `subject_code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '科目编码',
  `invest_ratio` double DEFAULT NULL COMMENT '投资比例 %',
  `invest_date` bigint DEFAULT NULL COMMENT '投资日期',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-投资流水信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_invest_flow`
--

LOCK TABLES `fin_invest_flow` WRITE;
/*!40000 ALTER TABLE `fin_invest_flow` DISABLE KEYS */;
INSERT INTO `fin_invest_flow` VALUES (1,'001',1,10.00,10.00,110.00,'332',1,1597766400000,-1,1598519683890,-1,1598519683890,0,'test'),(2,'001',1,10.00,20.00,0.00,'332',0.5,1597248000000,-1,1598520360437,-1,1598520360437,0,'test');
/*!40000 ALTER TABLE `fin_invest_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fin_investor`
--

DROP TABLE IF EXISTS `fin_investor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fin_investor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `investor_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '投资人名称',
  `investor_phone` varchar(13) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '投资人电话',
  `investor_address` varchar(13) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '投资人地址',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int DEFAULT NULL COMMENT '是否删除 0:未删除1:已删除',
  `remark` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-投资人信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_investor`
--

LOCK TABLES `fin_investor` WRITE;
/*!40000 ALTER TABLE `fin_investor` DISABLE KEYS */;
INSERT INTO `fin_investor` VALUES (1,'张三','13232213446','西湖区',-1,1598514439075,-1,1598515074182,0,'tet'),(2,'李四','13242213224','test',-1,1598514742061,-1,1598515114847,1,'test'),(3,'王五','18922219993','test',-1,1598515104793,-1,1598515110102,1,'test'),(4,'李四','13923381119','test',-1,1598515132299,-1,1598515132299,0,'test'),(5,'赵六','13823391118','test',-1,1598515161129,-1,1598515161129,0,'test');
/*!40000 ALTER TABLE `fin_investor` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='财务-会计科目信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fin_subject`
--

LOCK TABLES `fin_subject` WRITE;
/*!40000 ALTER TABLE `fin_subject` DISABLE KEYS */;
INSERT INTO `fin_subject` VALUES (1,'000','顶级科目','0',NULL,NULL,-1,1596186328352,-1,1596186328352,0,NULL),(2,'001','test','000',1,0,-1,1596186366064,-1,1596186366064,0,'tset'),(3,'443','收入','000',1,0,-1,1596520821756,-1,1596520821756,0,'test'),(4,'334','支出','000',1,0,-1,1596520863113,-1,1596520863113,0,'tset'),(5,'332','投资','000',1,1,-1,1596520977867,-1,1596520977867,0,'test');
/*!40000 ALTER TABLE `fin_subject` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='报表-资产报表(每天统计)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_asset`
--

LOCK TABLES `report_asset` WRITE;
/*!40000 ALTER TABLE `report_asset` DISABLE KEYS */;
INSERT INTO `report_asset` VALUES (1,'12',12.00,0.00,12.00,12.00,1596014735022,0,NULL,1595865600000,1595951999999),(2,'44',7.00,-1.00,8.00,9.00,1596014735022,0,NULL,1595865600000,1595951999999),(3,'122',2.00,-32.05,12.00,44.05,1596014735022,0,NULL,1595865600000,1595951999999),(4,'12',2.00,-37.00,62.00,99.00,1596071401483,0,NULL,1595952000000,1596038399999),(5,'44',4.00,0.00,4.00,4.00,1596071401483,0,NULL,1595952000000,1596038399999),(6,'122',123.00,0.00,123.00,123.00,1596071401483,0,NULL,1595952000000,1596038399999),(7,'12',21.00,-988.00,375.00,1363.00,1596124810027,0,NULL,1596038400000,1596124799999),(8,'44',123.00,328.00,463.00,135.00,1596124810027,0,NULL,1596038400000,1596124799999),(9,'122',123.00,0.00,123.00,123.00,1596124810028,0,NULL,1596038400000,1596124799999),(10,'2333',123.00,0.00,123.00,123.00,1596124810028,0,NULL,1596038400000,1596124799999),(11,'001',333.00,100.00,333.00,233.00,1596211210051,0,NULL,1596124800000,1596211199999),(12,'002',100.00,0.00,0.00,0.00,1596211210051,0,NULL,1596124800000,1596211199999),(13,'001',100.00,0.00,0.00,0.00,1596297610032,0,NULL,1596211200000,1596297599999),(14,'002',100.00,0.00,0.00,0.00,1596297610032,0,NULL,1596211200000,1596297599999),(15,'003',100.00,0.00,0.00,0.00,1596297610032,0,NULL,1596211200000,1596297599999),(16,'001',100.00,0.00,0.00,0.00,1596384010034,0,NULL,1596297600000,1596383999999),(17,'002',100.00,0.00,0.00,0.00,1596384010034,0,NULL,1596297600000,1596383999999),(18,'003',100.00,0.00,0.00,0.00,1596384010034,0,NULL,1596297600000,1596383999999),(19,'001',100.00,0.00,0.00,0.00,1596470410041,0,NULL,1596384000000,1596470399999),(20,'002',100.00,0.00,0.00,0.00,1596470410041,0,NULL,1596384000000,1596470399999),(21,'003',100.00,0.00,0.00,0.00,1596470410041,0,NULL,1596384000000,1596470399999),(22,'002',1187.00,920.00,1031.00,111.00,1596556810036,0,NULL,1596470400000,1596556799999),(23,'003',289.00,179.00,300.00,121.00,1596556810036,0,NULL,1596470400000,1596556799999),(24,'001',100.00,0.00,0.00,0.00,1596556810036,0,NULL,1596470400000,1596556799999),(25,'001',433.00,100.00,100.00,0.00,1596643210029,0,NULL,1596556800000,1596643199999),(26,'002',289.00,179.00,300.00,121.00,1596643210029,0,NULL,1596556800000,1596643199999),(27,'003',289.00,179.00,300.00,121.00,1596643210029,0,NULL,1596556800000,1596643199999),(28,'001',533.00,90.00,100.00,10.00,1597075210046,0,NULL,1596988800000,1597075199999),(29,'002',1420.00,290.00,310.00,20.00,1597075210046,0,NULL,1596988800000,1597075199999),(30,'001',1420.00,290.00,310.00,20.00,1597161610039,0,NULL,1597075200000,1597161599999),(31,'002',1420.00,290.00,310.00,20.00,1597161610039,0,NULL,1597075200000,1597161599999),(32,'003',1420.00,290.00,310.00,20.00,1597161610039,0,NULL,1597075200000,1597161599999),(33,'001',1420.00,290.00,310.00,20.00,1597248010047,0,NULL,1597161600000,1597247999999),(34,'002',1420.00,290.00,310.00,20.00,1597248010047,0,NULL,1597161600000,1597247999999),(35,'003',1420.00,290.00,310.00,20.00,1597248010047,0,NULL,1597161600000,1597247999999);
/*!40000 ALTER TABLE `report_asset` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单表 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'测试权限',0,1,'1','path',0,'test:list',0,1,1596192129010,1,NULL,'0','测试权限'),(2,'用户管理',0,2,'2','user',0,'userMenage',0,1,1596192129010,1,NULL,'0','用户管理'),(3,'角色管理',0,3,'2','user',0,'roleMenage',0,1,1596192129010,1,NULL,'0','角色管理'),(4,'菜单管理',0,4,'2','user',0,'menuMenage',0,1,1596192129010,1,NULL,'0','菜单管理'),(5,'日志管理',0,5,'2','user',0,'logMenage',0,1,1596192129010,1,NULL,'0','日志管理'),(6,'用户列表',2,3,'2','user',0,'userMenage:list',1,1,1596192129010,1,NULL,'0','用户列表'),(7,'角色列表',3,3,'2','user',0,'roleMenage:list',1,1,1596192129010,1,NULL,'0','角色列表'),(8,'菜单列表',4,3,'2','user',0,'menuMenage:list',1,1,1596192129010,1,NULL,'0','菜单列表'),(9,'日志列表',5,3,'2','user',0,'logMenage:list',1,1,1596192129010,1,NULL,'0','日志列表'),(10,'test1',9,1,'test1','test1',1,'test1',0,-1,1596192129010,-1,1595570270814,'1','test'),(11,'t1',1,1,'t1','t1',1,'t1',0,-1,1596192129010,-1,1595571259568,'1','t1'),(12,'t2',2,3,'t1','t1',1,'t12',0,-1,1596192129010,-1,1595571265083,'1','t'),(13,'test',1,1,'test','test',1,'test',0,-1,1596609111591,-1,1596609204512,'1',NULL),(14,'test',1,1,'test','test',1,'test',0,-1,1596609230208,-1,1596609230208,'0',NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'ROLE_ADMIN','1',1,1596192129010,1,NULL,'0',0),(2,'test','rwar',-1,1596192129010,-1,1595565495219,NULL,1),(3,'t1','t13',-1,1596192129010,-1,1595565545270,NULL,1),(4,'t2','t2',-1,1596192129010,-1,1595565029148,'1',1),(5,'te33','tet',-1,1596192129010,-1,1595565728011,'1',1),(6,'t3','3t',-1,1596192129010,-1,1595565728012,'1',1),(7,'t1',NULL,-1,1596192129010,-1,1595565741943,'0',1),(8,'t2',NULL,-1,1596192129010,-1,1595565747245,'0',1),(9,'t3',NULL,-1,1596192129010,-1,1595565753161,'0',1),(10,'t4',NULL,-1,1596192129010,-1,1595565757808,'0',1),(11,'t5',NULL,-1,1596192129010,-1,1595565764464,'0',1),(12,'test',NULL,-1,1596609256393,-1,1596609260985,'1',1);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','admin','wg2p+RW6HARvP1vPoklko7AcWGCCDABOwG9Z1p8w0VRKhWVlkCNdSVb8Kj9xHUp4XWZUt8BW1zIX3gYm8Ox3eg==',1,1,'13823379117',1,1596192129010,NULL,NULL,0,'hb_cxtl@163.com','file:///home/xt/data/doc/img/1.jpeg',NULL),(2,'test','test','wg2p+RW6HARvP1vPoklko7AcWGCCDABOwG9Z1p8w0VRKhWVlkCNdSVb8Kj9xHUp4XWZUt8BW1zIX3gYm8Ox3eg==',1,1,'13823379317',1,1596192129010,-1,1595486879352,0,'hb_cztl@163.com','file:///home/xt/data/doc/img/2.jpeg',NULL),(3,NULL,'cxt','123123',1,1,'13823391120',NULL,1596192129010,-1,1595486875606,0,'yy@qq.com',NULL,NULL),(4,'cxt','cxt','123123',1,1,'13823391120',NULL,1596192129010,NULL,1595493015354,1,'yy@qq.com',NULL,NULL),(5,'cxt','cxt','123123',1,1,'13823391120',NULL,1596192129010,NULL,1595493015354,1,'yy@qq.com',NULL,NULL),(6,'cxt','cxt','123123',1,1,'13823391120',NULL,1596192129010,NULL,1595493823983,1,'yy@qq.com',NULL,NULL),(7,'cxt','cxt','123123',1,1,'13823391120',NULL,1596192129010,NULL,1595493823983,1,'yy@qq.com',NULL,NULL),(8,'cxt','cxt','123123',1,1,'13823391120',NULL,1596192129010,NULL,NULL,0,'yy@qq.com',NULL,NULL),(9,'cxt','cxt','123123',1,1,'13823391120',NULL,1596192129010,NULL,1595491750030,1,'yy@qq.com',NULL,NULL),(10,'cxt','cxt','123123',1,1,'13823391120',-1,1596192129010,-1,1595491750030,1,'yy@qq.com',NULL,NULL),(11,'cxt','cxt','123123',1,1,'13823391120',-1,1596192129010,-1,NULL,0,'yy@qq.com',NULL,NULL),(12,'cxt','cxt','123123',1,1,'13823391120',-1,1596192129010,-1,1595491756534,1,'yy@qq.com',NULL,NULL),(13,'cxt','cxt','123123',1,1,'13823391120',-1,1596192129010,-1,1595412980145,0,'yy@qq.com',NULL,NULL),(14,'c1','c1','123123123123',1,1,'13923380118',-1,1596192129010,-1,1595491690415,0,'gg@qq.com',NULL,NULL),(15,'test1','test1','test111',1,1,'13823370116',-1,1596447894315,-1,1596447894315,0,'test@11.com',NULL,'test');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_account`
--

DROP TABLE IF EXISTS `sys_user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `account_code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '帐套编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户帐套关联表 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_account`
--

LOCK TABLES `sys_user_account` WRITE;
/*!40000 ALTER TABLE `sys_user_account` DISABLE KEYS */;
INSERT INTO `sys_user_account` VALUES (2,1,'001'),(3,1,'002');
/*!40000 ALTER TABLE `sys_user_account` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1,1),(4,2,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `sys_work`
--

LOCK TABLES `sys_work` WRITE;
/*!40000 ALTER TABLE `sys_work` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_work` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-27 17:36:49
