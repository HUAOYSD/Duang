/*
SQLyog Ultimate v8.32 
MySQL - 5.7.3-m13-log : Database - duang
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`duang` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `duang`;

/*Table structure for table `invest_list` */

DROP TABLE IF EXISTS `invest_list`;

CREATE TABLE `invest_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `invest_sum_money` float(50,6) DEFAULT '0.000000' COMMENT '投资总金额',
  `invest_doing_money` float(50,6) DEFAULT '0.000000' COMMENT '投资中金额',
  `invest_usable_balance` float(50,6) DEFAULT '0.000000' COMMENT '可用金额',
  `account_sum_balance` float(50,6) DEFAULT '0.000000' COMMENT '账面总金额',
  `freeze_balance` float(50,6) DEFAULT '0.000000' COMMENT '冻结余额',
  `not_transfer_balance` float(50,6) DEFAULT '0.000000' COMMENT '未转结余额',
  `usable_integral` int(11) DEFAULT '0' COMMENT '可用积分',
  `last_date` datetime DEFAULT NULL COMMENT '活动日期',
  `is_contract_user` int(11) DEFAULT '0' COMMENT '契约用户 0不是 1是',
  `open_date` datetime DEFAULT NULL COMMENT '开户日期',
  `is_online` int(11) DEFAULT '1' COMMENT '允许上线  0不允许 1允许',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `invest_list` */

/*Table structure for table `invest_member` */

DROP TABLE IF EXISTS `invest_member`;

CREATE TABLE `invest_member` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `idcard` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `bank_card` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡号',
  `bank` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属银行',
  `user_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
  `idcard_img1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证前照',
  `idcard_img2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证后照',
  `cust_manager_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户经理',
  `manager_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户经理姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `invest_member` */

/*Table structure for table `invest_product` */

DROP TABLE IF EXISTS `invest_product`;

CREATE TABLE `invest_product` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `name_zh` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '总名称  如：年年余',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称  6月期，12月期',
  `name_describe` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `yield_describe` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收益率描述',
  `yield` float(50,6) NOT NULL COMMENT '准确的收益率',
  `charge_ratio` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手续费比例率',
  `title1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题1',
  `title2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题2',
  `min_deadline` char(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '起投期限',
  `min_money` char(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '起投金额',
  `refund_type` char(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '还款方式',
  `is_sell` char(11) COLLATE utf8_unicode_ci DEFAULT '1' COMMENT '是否起售 0：停售 1：起售',
  `is_lottery` char(11) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '是否抽奖 0否  1是抽奖',
  `is_red_envel` char(11) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '是否红包 0否 1是',
  `is_new_product` char(11) COLLATE utf8_unicode_ci DEFAULT '1' COMMENT '是否为新品  0:否  1：是',
  `is_recommend` char(11) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '是否推荐 0否 1是推荐',
  `product_describe` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '介绍',
  `risk_control` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '风险控制',
  `details` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更多详情',
  `isdelete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除 0未删除',
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `createuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财产品表';

/*Data for the table `invest_product` */

insert  into `invest_product`(`id`,`name_zh`,`name`,`name_describe`,`yield_describe`,`yield`,`charge_ratio`,`title1`,`title2`,`min_deadline`,`min_money`,`refund_type`,`is_sell`,`is_lottery`,`is_red_envel`,`is_new_product`,`is_recommend`,`product_describe`,`risk_control`,`details`,`isdelete`,`createtime`,`modifytime`,`createuser`,`modifyuser`) values ('0012','fasf','asdf','sdf','sdf',6.000000,'','8.1%','语气阿斯蒂芬','6','10000','1','1','0','0','1','0','asdf','sdfs','asdfs',0,'2016-07-26 16:42:49','2016-07-26 16:42:53','001','002'),('0a42a5741a5c4498ada9e375f7956fe7','556','55','55','55',55.000000,'55','55','55','12','55, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('0acdacb6616840d38834843e3875db1d','88','8','8','8',8.000000,'8','8','8','8','8, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('161417c1f2aa4bcd934f57ebd69ee0e1','23','23','23','23',23.000000,'23','23','23','12','23, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('2499da7fcfff4007a6f5026f81276e9f','34','12','45','34',34.000000,'34','34','34','12','34, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('29e2a8e3cab44dc997a846f5d45b7ceb','你猜','asdfa','asdfasd','34',34.000000,'34','34','3434','12','34, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('2fec0ae5948549f0acfed7dee3f2957c','sdft','sdf','d','2',3.000000,'3','3','3','3','3, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('314fb2f26485402c972f60d7cea2c5bf','45','46','456+','23',456.000000,'123','23','78','12','78, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('32b212cd6e6a4b10b8d918227e461e6f','66','66','66','66',66.000000,'66','66','66','12','66, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('3d426c3432244797948ba8c6394e91d7','asdf爱的','2','33','34',3.000000,'3','3','3','3','3, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('3ed29e1e919341e2beb67c2fcbc41b80','99','23','345','345',345.000000,'345','345','345','12','345, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('54d81ebd9f344bc99f4193d35518c030','asdf','asdf','54','45',454.000000,'456','45','123','12','123, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('5ddf79d3236442b4947b8c9dc63089f7','士大夫','1','1','1',1.000000,'1','1','1','1','1, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('5f5e68c4a6e44f3da10f17535d70bc09','33','34','34','34',34.000000,'34','34','34','12','34, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('6c8b6dd52494488498f648f1f6d47ba5','00','0','0','0.2%',0.000000,'0','0','0','0','0, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('7485d147ddb44e56a126b284ef9d2e9e','12','12','12','12',12.000000,'12','12','12','12','12, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('7b95bbf32e884e77ab51ab57cc09b696','1','1','234','2342',234.000000,'23','234','234','12','234, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('87a50dc95295493a805e2c989bea1ced','ll','56','56','56',56.000000,'56','56','556','12','56, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('93d6bd24640d4d59a47667b945360cbe','12','12','21','12',12.000000,'12','12','12','12','12, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('9a912310a2784fc0b8c1b7f0677c6157','23','234','23','23',23.000000,'23','23','234','12','23, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('a163a2142d4248af93bdb733547ff91f','33','33','33','33',33.000000,'33','33','33','3','3, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('aeec27485dab4bf885085d18c59b2571','asdf','asdf','23','23',23.000000,'23','23','23','12','23, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('b1c803f1ff0c4be4bdebe9194f39d6ac','77','7','7','7',7.000000,'7','7','7','7','7, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('b86210f34fc4444aafa3dc3130e3ecf6','你猜','asdfa','asdfasd','34',34.000000,'34','34','3434','12','34, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('ca67e334a2dc4832a94fd138b6040731','44','wr','23','23',23.000000,'23','23','23','12','23, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('cb0e66546654431abc283bc13efc67c7','00','0','0','0.2%',0.000000,'0','0','0','0','0, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('d0d5070bf4f34d6da79e57a3b879e675','sdfasd','asdf','asdf','asdf',2132132.000000,'asdf','asdf','asdf','6','3000, 1',NULL,'1','0','0','1','1','asa撒打发士大夫是','阿阿斯顿傻的发送到发送到发送到发送到非是打发撒打发士大夫阿斯顿发送到发阿斯顿发送到发是阿斯顿发送到发送到阿斯顿发送到发送到发送大法师打发士大夫','阿斯顿发送到发士大夫时代阿斯顿发送到发送到发送到发送到发送到 阿斯顿发送到发啊阿道夫阿斯顿发送到发而二微软waSDFASAS ASDF ASasdfasdf as爱大是大非',0,NULL,NULL,NULL,NULL),('d8fd09641fa249e5b2dafeb107f3a9d2','sdfds','sdf','sf','34',234.000000,'34','34','34','12','34, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('f1e26692a3f94cf6bf6d9264001fe3d4','sadf','asdfa','asdfasd','23',23.000000,'23','23','23','12','23, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('f7af67bbba154c1f9f85565a50133acc','234','234','234','234',234.000000,'234','234','234','12','234, 1',NULL,'1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL);

/*Table structure for table `loan_list` */

DROP TABLE IF EXISTS `loan_list`;

CREATE TABLE `loan_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `contract_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '合同编号',
  `is_sell` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否起售',
  `state` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态',
  `loan_money` float(50,6) DEFAULT NULL COMMENT '借款金额',
  `handling_charge` float(50,6) DEFAULT NULL COMMENT '手续费',
  `should_loan_money` float(50,6) DEFAULT NULL COMMENT '应放宽金额',
  `last_storage` float(50,6) unsigned zerofill DEFAULT NULL COMMENT '目的库存',
  `last_int_money` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '目的整数金额',
  `yet_loan_int_money` int(11) DEFAULT '0' COMMENT '已经借到整数金额',
  `not_loan_int_money` int(11) DEFAULT '0' COMMENT '未借到整数金额',
  `not_loan_int_storage` int(11) DEFAULT '0' COMMENT '未借到整数库存',
  `last_dec_money` float(255,0) DEFAULT NULL COMMENT '目的小数金额',
  `yet_loan_dec_money` float(255,0) DEFAULT NULL COMMENT '已经借到的小数金额',
  `not_loan_dec_money` float(255,0) DEFAULT NULL COMMENT '未借到的小数金额',
  `loan_type` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款类型',
  `loan_purpose` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款用途',
  `loan_interest` float(255,0) DEFAULT NULL COMMENT '借款利息',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `contract_date` datetime DEFAULT NULL COMMENT '签约日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `loan_list` */

/*Table structure for table `loan_member` */

DROP TABLE IF EXISTS `loan_member`;

CREATE TABLE `loan_member` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `idcard` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份账号',
  `bank_card` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡号',
  `bank` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开户银行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `loan_member` */

/*Table structure for table `member_extra_info` */

DROP TABLE IF EXISTS `member_extra_info`;

CREATE TABLE `member_extra_info` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '住址',
  `degree` int(11) DEFAULT NULL COMMENT '学位  博士后，博士，研究生，本科，大专，中专，高中',
  `job_type` int(11) DEFAULT NULL COMMENT '工作类型',
  `school` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '毕业院校',
  `salary_year` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '年薪',
  `hobby` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '爱好',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户额外信息（住址，爱好，毕业院校，学位等）';

/*Data for the table `member_extra_info` */

/*Table structure for table `member_info` */

DROP TABLE IF EXISTS `member_info`;

CREATE TABLE `member_info` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录名',
  `real_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '真实姓名',
  `nickname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号',
  `describe` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `isdelete` int(11) DEFAULT '0' COMMENT '是否删除  1：已删除 0未删除',
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `createuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
  `is_elite_account` int(11) DEFAULT '0' COMMENT '是否为金账户 0否 1是',
  `type` int(11) DEFAULT NULL COMMENT '客户类型',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `price` int(11) DEFAULT NULL COMMENT '财力值',
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `hand_password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手势密码',
  `is_freeze` int(11) DEFAULT NULL COMMENT '是否冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `member_info` */

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` char(36) NOT NULL COMMENT '日志ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `model_name` varchar(50) DEFAULT NULL COMMENT '操作模块',
  `log_content` varchar(2046) DEFAULT NULL COMMENT '日志内容',
  `log_action` varchar(200) DEFAULT NULL COMMENT '操作Action',
  `log_type` varchar(10) DEFAULT NULL COMMENT '日志操作类型：1：添加；2：删除；3：修改；4：登录；5：退出；6：导出',
  `log_ip` varchar(32) DEFAULT NULL COMMENT '操作者IP',
  `option_time` datetime DEFAULT NULL COMMENT '操作时间',
  `log_status` varchar(2) DEFAULT NULL COMMENT '操作状态 ：1：成功，2失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志流水表';

/*Data for the table `sys_log` */

/*Table structure for table `sys_power` */

DROP TABLE IF EXISTS `sys_power`;

CREATE TABLE `sys_power` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限id',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限名称',
  `url` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '访问地址',
  `parent_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上级权限id :0 ：顶级',
  `sort_index` int(11) DEFAULT NULL COMMENT '排序',
  `option_time` datetime DEFAULT NULL COMMENT '操作时间',
  `icon` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作图标',
  `remark` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统权限表';

/*Data for the table `sys_power` */

insert  into `sys_power`(`id`,`name`,`url`,`parent_id`,`sort_index`,`option_time`,`icon`,`remark`) values ('0a0f26dc495c4e468dc3d8cfef8099a4','3','312','f1e4e48d57744f28bb12380785c6b210',312,'2016-07-27 11:01:44',NULL,NULL),('4f5bcb4a40a14aba8693e19d67d48974','业务中心','www.baidu.com','4f5bcb4gfgs54aba8693e19d67d48974',12,'2016-07-27 10:54:30',NULL,NULL),('4f5bcb4gfgs54aba8693e19d67d48974','系统权限','/','0',1,NULL,NULL,NULL),('776edce1ee204d799d19bc0257f58f88','22','31','d21a418df34045dfa5bf003aab2b419d',312,'2016-07-27 14:04:05',NULL,NULL),('a20cf91d30d84e2487e0dadc3dcc4e3f','业务中心1','www.baidu.com','4f5bcb4gfgs54aba8693e19d67d48974',11111,'2016-07-27 14:01:51',NULL,NULL),('d21a418df34045dfa5bf003aab2b419d','1','1','4f5bcb4gfgs54aba8693e19d67d48974',1,'2016-07-27 14:03:28',NULL,NULL),('f1e4e48d57744f28bb12380785c6b210','业务中心1321','www.baidu.com','4f5bcb4gfgs54aba8693e19d67d48974',312,'2016-07-27 11:01:34',NULL,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色id',
  `role_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `option_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_desc`,`option_time`) values ('608be6bbe85d4e8e86fe6fc8160444fb','uuuu','放电饭锅','2016-07-27 15:43:45'),('aa0acfa851734c12b00ddecf632ae6ed','31232131231223123','312312','2016-07-27 15:21:16'),('c1a2aa0a267745d5a08ffe1ddc0e4537','有','321','2016-07-27 15:16:56'),('e81ae6ffac214001ba32e6e761956c57','发生大幅33','发生大幅333','2016-07-27 15:17:39'),('ee89e8654bee46f59d3a3e488c708c64','无','321312','2016-07-27 14:44:07');

/*Table structure for table `sys_role_power` */

DROP TABLE IF EXISTS `sys_role_power`;

CREATE TABLE `sys_role_power` (
  `role_power_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限角色id',
  `role_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色id',
  `power_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限id',
  PRIMARY KEY (`role_power_id`),
  KEY `FK_ROLE_POWER_ROLE` (`role_id`),
  KEY `FK_ROLE_POWER_POWER` (`power_id`),
  CONSTRAINT `FK_ROLE_POWER_POWER` FOREIGN KEY (`power_id`) REFERENCES `sys_power` (`id`),
  CONSTRAINT `FK_ROLE_POWER_ROLE` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限角色表';

/*Data for the table `sys_role_power` */

insert  into `sys_role_power`(`role_power_id`,`role_id`,`power_id`) values ('0a652a77eb8e440e946014c68f54732e','c1a2aa0a267745d5a08ffe1ddc0e4537','4f5bcb4a40a14aba8693e19d67d48974'),('23c4c3194fb94d3b990c276ee2e35426','608be6bbe85d4e8e86fe6fc8160444fb','4f5bcb4a40a14aba8693e19d67d48974'),('33271c7074b64c6fb8af2964b5c4a0fe','aa0acfa851734c12b00ddecf632ae6ed','a20cf91d30d84e2487e0dadc3dcc4e3f'),('59123f1fec2241f88e83cd2b5e0c75d1','aa0acfa851734c12b00ddecf632ae6ed','0a0f26dc495c4e468dc3d8cfef8099a4'),('75da9116cde741ad9bf6f313fa9967da','ee89e8654bee46f59d3a3e488c708c64','776edce1ee204d799d19bc0257f58f88'),('814a6281947f4ad9b5c60a92125bbaf6','aa0acfa851734c12b00ddecf632ae6ed','f1e4e48d57744f28bb12380785c6b210'),('829ff5fb9f624ef2abfd89126ce603c0','ee89e8654bee46f59d3a3e488c708c64','0a0f26dc495c4e468dc3d8cfef8099a4'),('8a96d356932a4430af8fe7c092b92ae0','c1a2aa0a267745d5a08ffe1ddc0e4537','a20cf91d30d84e2487e0dadc3dcc4e3f'),('906f9efdbe444e2184901ac753f7fe9c','ee89e8654bee46f59d3a3e488c708c64','d21a418df34045dfa5bf003aab2b419d'),('bc2b7f7a69fe4d488aa93af6f9ca0bc1','608be6bbe85d4e8e86fe6fc8160444fb','0a0f26dc495c4e468dc3d8cfef8099a4'),('c48b8179525d46a0aa6f3e69594bab48','ee89e8654bee46f59d3a3e488c708c64','f1e4e48d57744f28bb12380785c6b210'),('f00f758d034e47f58b64c58895b133dd','608be6bbe85d4e8e86fe6fc8160444fb','f1e4e48d57744f28bb12380785c6b210');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '系统用户主键',
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '系统用户登录名',
  `password` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '系统用户登录密码',
  `role_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属角色',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idcard` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ROLE` (`role_id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`name`,`password`,`role_id`,`create_time`,`update_time`,`remark`,`phone`,`email`,`idcard`) values ('00964d7c990346d496a7394189a68f9b','ewqeqw','96e79218965eb72c92a549dd5a330112','ee89e8654bee46f59d3a3e488c708c64','2016-07-28 15:28:09','2016-07-28 15:28:09','admin','15600046101','4894@qe.com',''),('1683240499944311858d41d2e2db1edf','111','96e79218965eb72c92a549dd5a330112','aa0acfa851734c12b00ddecf632ae6ed','2016-07-28 15:37:15','2016-07-28 15:37:15','admin','','',''),('30fe04c4df474147aa7ae87714aaf308','你好饭店ff3231','96e79218965eb72c92a549dd5a330112','608be6bbe85d4e8e86fe6fc8160444fb','2016-07-28 15:52:25','2016-07-28 15:58:22','admin','','',''),('9bf73ee2023c4740b03a695615f2851b','11','96e79218965eb72c92a549dd5a330112','e81ae6ffac214001ba32e6e761956c57','2016-07-28 15:37:03','2016-07-28 15:37:03','admin','','',''),('a40eff127785410d9e17df05458837e3','zs','96e79218965eb72c92a549dd5a330112','c1a2aa0a267745d5a08ffe1ddc0e4537','2016-07-28 13:57:50','2016-07-28 13:57:51','admin','fds','',''),('f942292769ce4d44b45d9becbb5e876d','分公司感受到官方TT','9db06bcff9248837f86d1a6bcf41c9e7','e81ae6ffac214001ba32e6e761956c57','2016-07-28 16:13:41','2016-07-28 16:49:46','adminRR','','','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
