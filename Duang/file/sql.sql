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

insert  into `invest_product`(`id`,`name_zh`,`name`,`name_describe`,`yield_describe`,`yield`,`charge_ratio`,`title1`,`title2`,`min_deadline`,`min_money`,`refund_type`,`is_sell`,`is_lottery`,`is_red_envel`,`is_new_product`,`is_recommend`,`product_describe`,`risk_control`,`details`,`isdelete`,`createtime`,`modifytime`,`createuser`,`modifyuser`) values ('71d59b80dbea4ce2b9163fa3dae5f9e9','er','we','','',0.200000,'','','','6','6','1','1','0','0','1','1','','','',0,NULL,NULL,NULL,NULL),('d79a0f74392f4505ab1c050d4eb8eb26','sdf','sdf','sdf','asdf',0.230000,'','sdf','sd','6','5000','2','0','1','1','1','0','sdfsdf','sdfsdf','sdfsdfsdf',0,NULL,NULL,NULL,NULL);

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

insert  into `sys_power`(`id`,`name`,`url`,`parent_id`,`sort_index`,`option_time`,`icon`,`remark`) values ('099b882f3f5749079af62aac20400782','业务中心','/','syspowers',1,'2016-07-29 14:45:39','icon-rosette',''),('1553ee3cc9874033a1cf3d07dd6bd791','张三1','1','e8ba0702782b49ef9bffeaba7021329f',1,'2016-07-29 13:17:20','',''),('303bff33608c46478eafe0b2720d949e','用户管理','sysuser!showUser.do','7518b13cf6da4e5580aeaa7decfc71bb',1,'2016-07-29 14:41:58','icon-people','用户管理'),('4fd45e3452b7488580d2a198f758b5e6','客户管理','/','099b882f3f5749079af62aac20400782',1,'2016-07-29 14:45:58','icon-group',''),('665cb2abea514a35b1528bc195af4175','李四2','3','d35cf70a67b143bcacc0bfae1b1c94e0',3,'2016-07-29 13:18:08','',''),('7518b13cf6da4e5580aeaa7decfc71bb','系统设置','/','syspowers',100,'2016-07-29 14:41:35','icon-2012080404391','系统用户权限角色的设置'),('a007affa6bca4c20adc45e2ca5550bf8','角色管理','sysrole!showRole.do','7518b13cf6da4e5580aeaa7decfc71bb',2,'2016-07-29 14:45:16','icon-status_be_right_back','角色管理'),('a59137b1774f4839b9bb89b40d51cf87','产品列表','investpro!investProList.do','099b882f3f5749079af62aac20400782',2,'2016-07-29 15:48:35','icon-tag_green','产品的列表'),('bcc2df5e881c4af68f4743dbda55ec1f','张三2','3','e8ba0702782b49ef9bffeaba7021329f',3,'2016-07-29 13:17:54','',''),('d35cf70a67b143bcacc0bfae1b1c94e0','李四的权限','321','syspowers',5,'2016-07-29 14:40:54','',''),('e25ebca75b12481d9fb782b21ef842f0','权限管理','syspower!showPower.do','7518b13cf6da4e5580aeaa7decfc71bb',3,'2016-07-29 14:44:57','icon-user_key','权限管理'),('e8ba0702782b49ef9bffeaba7021329f','张三的权限','1','syspowers',2,'2016-07-29 14:40:37','',''),('eea9712d2051499c9e38e314fa6617f2','李四1','3','d35cf70a67b143bcacc0bfae1b1c94e0',3,'2016-07-29 13:17:45','',''),('syspowers','系统权限','/','0',1,NULL,NULL,NULL);

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

insert  into `sys_role`(`id`,`role_name`,`role_desc`,`option_time`) values ('398f5389af8f49749977cf21dc846f2e','李四权限','李四权限测试','2016-07-29 13:19:35'),('526b3b81287c4b4a897676ef194d04d4','超级用户','管理所有后台','2016-07-29 13:18:42'),('e4fa3ab5c9304eafb326449012a063a2','张三角色','张三角色测试','2016-07-29 13:19:03');

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

insert  into `sys_role_power`(`role_power_id`,`role_id`,`power_id`) values ('0d2965a7f43f419e8593103f9743b0e4','398f5389af8f49749977cf21dc846f2e','d35cf70a67b143bcacc0bfae1b1c94e0'),('11d36a44a13e4b0c8118dc6120509897','526b3b81287c4b4a897676ef194d04d4','eea9712d2051499c9e38e314fa6617f2'),('2432ac58d91a49ee87f847794341fa67','526b3b81287c4b4a897676ef194d04d4','303bff33608c46478eafe0b2720d949e'),('4e9b3ae8bb294971a0854e4911ced593','526b3b81287c4b4a897676ef194d04d4','e25ebca75b12481d9fb782b21ef842f0'),('4f31666300a64496923a6b611ad67f95','526b3b81287c4b4a897676ef194d04d4','665cb2abea514a35b1528bc195af4175'),('55bdcf3d28f74f2e9fb87daf359ba4a8','398f5389af8f49749977cf21dc846f2e','bcc2df5e881c4af68f4743dbda55ec1f'),('5a29203a8bd54d8fa4a56e542393023a','526b3b81287c4b4a897676ef194d04d4','4fd45e3452b7488580d2a198f758b5e6'),('5a9aa147623c4c00b7a9d2ef097a5d5a','526b3b81287c4b4a897676ef194d04d4','7518b13cf6da4e5580aeaa7decfc71bb'),('609386b31b794bce967b0606bcd297a1','526b3b81287c4b4a897676ef194d04d4','099b882f3f5749079af62aac20400782'),('6c2f652ca94f4bd2987427c3a716627e','398f5389af8f49749977cf21dc846f2e','eea9712d2051499c9e38e314fa6617f2'),('6ebb2ef4e07d4a07a4fc8e8fa7bf069a','e4fa3ab5c9304eafb326449012a063a2','e8ba0702782b49ef9bffeaba7021329f'),('7cf1c02c23f24ab49877efafa8b79bfd','526b3b81287c4b4a897676ef194d04d4','bcc2df5e881c4af68f4743dbda55ec1f'),('82564e8c818f43e29e8cca388c54e75a','526b3b81287c4b4a897676ef194d04d4','e8ba0702782b49ef9bffeaba7021329f'),('8c1b4de5d60e4e40a06a4ab3febcee72','e4fa3ab5c9304eafb326449012a063a2','bcc2df5e881c4af68f4743dbda55ec1f'),('93d260928f744a78b08cafa487c8a259','526b3b81287c4b4a897676ef194d04d4','a59137b1774f4839b9bb89b40d51cf87'),('ab6d9e5b04b9475195ecd3cc60293aea','398f5389af8f49749977cf21dc846f2e','665cb2abea514a35b1528bc195af4175'),('c0830779f31444e882170929d2ce006d','526b3b81287c4b4a897676ef194d04d4','syspowers'),('c56f62df97d44cc388ba9fb7c83ed8d7','526b3b81287c4b4a897676ef194d04d4','1553ee3cc9874033a1cf3d07dd6bd791'),('cc9161147cd64acca0ca3b08c5b64760','e4fa3ab5c9304eafb326449012a063a2','1553ee3cc9874033a1cf3d07dd6bd791'),('e161336ef2e649cbb39cab2cb7bd1f6d','526b3b81287c4b4a897676ef194d04d4','d35cf70a67b143bcacc0bfae1b1c94e0'),('f476b3d23fd6409aa63db23f0588d1f1','526b3b81287c4b4a897676ef194d04d4','a007affa6bca4c20adc45e2ca5550bf8');

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

insert  into `sys_user`(`id`,`name`,`password`,`role_id`,`create_time`,`update_time`,`remark`,`phone`,`email`,`idcard`) values ('0','admin','21232f297a57a5a743894a0e4a801fc3','526b3b81287c4b4a897676ef194d04d4','2016-07-29 13:23:07','2016-07-29 13:25:11','','','',''),('5cc6c63d1069403b8ab97cd1876724ef','ls','73882ab1fa529d7273da0db6b49cc4f3','398f5389af8f49749977cf21dc846f2e','2016-07-29 13:23:07','2016-07-29 13:23:07','','','',''),('97d57f49db95473ba1f5127deffdccd0','zs','f63f4fbc9f8c85d409f2f59f2b9e12d5','e4fa3ab5c9304eafb326449012a063a2','2016-07-29 12:56:19','2016-07-29 13:22:43','','','','510821199401108853');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
