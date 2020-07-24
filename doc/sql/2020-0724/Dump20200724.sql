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
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'测试权限',0,1,'1','path',0,'test:list',0,1,NULL,1,NULL,'0','测试权限'),(2,'用户管理',0,2,'2','user',0,'userMenage',0,1,NULL,1,NULL,'0','用户管理'),(3,'角色管理',0,3,'2','user',0,'roleMenage',0,1,NULL,1,NULL,'0','角色管理'),(4,'菜单管理',0,4,'2','user',0,'menuMenage',0,1,NULL,1,NULL,'0','菜单管理'),(5,'日志管理',0,5,'2','user',0,'logMenage',0,1,NULL,1,NULL,'0','日志管理'),(6,'用户列表',2,3,'2','user',0,'userMenage:list',1,1,NULL,1,NULL,'0','用户列表'),(7,'角色列表',3,3,'2','user',0,'roleMenage:list',1,1,NULL,1,NULL,'0','角色列表'),(8,'菜单列表',4,3,'2','user',0,'menuMenage:list',1,1,NULL,1,NULL,'0','菜单列表'),(9,'日志列表',5,3,'2','user',0,'logMenage:list',1,1,NULL,1,NULL,'0','日志列表'),(10,'test1',9,1,'test1','test1',1,'test1',0,-1,1595569967430,-1,1595570270814,'1','test'),(11,'t1',1,1,'t1','t1',1,'t1',0,-1,1595570288165,-1,1595571259568,'1','t1'),(12,'t2',2,3,'t1','t1',1,'t12',0,-1,1595571252905,-1,1595571265083,'1','t');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'ROLE_ADMIN','1',1,NULL,1,NULL,'0',0),(2,'test','rwar',-1,1595564749658,-1,1595565495219,NULL,1),(3,'t1','t13',-1,1595564826626,-1,1595565545270,NULL,1),(4,'t2','t2',-1,1595564923518,-1,1595565029148,'1',1),(5,'te33','tet',-1,1595565694671,-1,1595565728011,'1',1),(6,'t3','3t',-1,1595565721045,-1,1595565728012,'1',1),(7,'t1',NULL,-1,1595565741943,-1,1595565741943,'0',1),(8,'t2',NULL,-1,1595565747245,-1,1595565747245,'0',1),(9,'t3',NULL,-1,1595565753161,-1,1595565753161,'0',1),(10,'t4',NULL,-1,1595565757808,-1,1595565757808,'0',1),(11,'t5',NULL,-1,1595565764464,-1,1595565764464,'0',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','admin','wg2p+RW6HARvP1vPoklko7AcWGCCDABOwG9Z1p8w0VRKhWVlkCNdSVb8Kj9xHUp4XWZUt8BW1zIX3gYm8Ox3eg==',1,1,'13823379117',1,NULL,NULL,NULL,0,'hb_cxtl@163.com','file:///home/xt/data/doc/img/1.jpeg',NULL),(2,'test','test','wg2p+RW6HARvP1vPoklko7AcWGCCDABOwG9Z1p8w0VRKhWVlkCNdSVb8Kj9xHUp4XWZUt8BW1zIX3gYm8Ox3eg==',1,1,'13823379317',1,NULL,-1,1595486879352,0,'hb_cztl@163.com','file:///home/xt/data/doc/img/2.jpeg',NULL),(3,NULL,'cxt','123123',1,1,'13823391120',NULL,NULL,-1,1595486875606,0,'yy@qq.com',NULL,NULL),(4,'cxt','cxt','123123',1,1,'13823391120',NULL,NULL,NULL,1595493015354,1,'yy@qq.com',NULL,NULL),(5,'cxt','cxt','123123',1,1,'13823391120',NULL,NULL,NULL,1595493015354,1,'yy@qq.com',NULL,NULL),(6,'cxt','cxt','123123',1,1,'13823391120',NULL,NULL,NULL,1595493823983,1,'yy@qq.com',NULL,NULL),(7,'cxt','cxt','123123',1,1,'13823391120',NULL,NULL,NULL,1595493823983,1,'yy@qq.com',NULL,NULL),(8,'cxt','cxt','123123',1,1,'13823391120',NULL,NULL,NULL,NULL,0,'yy@qq.com',NULL,NULL),(9,'cxt','cxt','123123',1,1,'13823391120',NULL,NULL,NULL,1595491750030,1,'yy@qq.com',NULL,NULL),(10,'cxt','cxt','123123',1,1,'13823391120',-1,NULL,-1,1595491750030,1,'yy@qq.com',NULL,NULL),(11,'cxt','cxt','123123',1,1,'13823391120',-1,NULL,-1,NULL,0,'yy@qq.com',NULL,NULL),(12,'cxt','cxt','123123',1,1,'13823391120',-1,NULL,-1,1595491756534,1,'yy@qq.com',NULL,NULL),(13,'cxt','cxt','123123',1,1,'13823391120',-1,1595412946486,-1,1595412980145,0,'yy@qq.com',NULL,NULL),(14,'c1','c1','123123123123',1,1,'13923380118',-1,1595413149770,-1,1595491690415,0,'gg@qq.com',NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-24 17:22:27
