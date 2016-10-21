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

/*Table structure for table `ad` */

DROP TABLE IF EXISTS `ad`;

CREATE TABLE `ad` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '广告名称',
  `remark` mediumtext COLLATE utf8_unicode_ci COMMENT '广告说明',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `image_address` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '广告图片地址',
  `link_address` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '广告链接地址',
  `is_use` int(11) DEFAULT NULL COMMENT '是否使用 1使用，0不使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='广告';

/*Data for the table `ad` */

insert  into `ad`(`id`,`name`,`remark`,`createtime`,`image_address`,`link_address`,`is_use`) values ('11234567895236','胖大海1','清热润肺1','2016-09-05 14:00:09','ccb6c64843ad4c518aa5726498acd91f.jpg','http://localhost:8080/sdf1',0),('839a719bf9d244af8005133906018621','跑步','asdfasdfasdf爱迪生','2016-09-05 16:54:33',NULL,'http://localhost:8080/sdf',1),('9c593fca1e524d7785f0738aeb994562','13212','321','2016-09-06 15:15:24',NULL,'http://www.baidu.com',1);

/*Table structure for table `apply_loan_car` */

DROP TABLE IF EXISTS `apply_loan_car`;

CREATE TABLE `apply_loan_car` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `loan_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款信息外键',
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名字',
  `idcard` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证',
  `phone` char(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '省份',
  `city` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '城市',
  `brand` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '品牌',
  `age` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '使用年限',
  `limit` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '公里数',
  `engine` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发动机编号',
  `use` mediumtext COLLATE utf8_unicode_ci COMMENT '规划用途',
  `car_property` mediumtext COLLATE utf8_unicode_ci COMMENT '车辆性质',
  `datums` mediumtext COLLATE utf8_unicode_ci COMMENT '个人资料,多份用英文'';''隔开',
  `asset_certificates` mediumtext COLLATE utf8_unicode_ci COMMENT '收入证明,多份用英文'';''隔开',
  PRIMARY KEY (`id`),
  KEY `FK_apply_loan_car` (`loan_list_id`),
  CONSTRAINT `FK_apply_loan_car` FOREIGN KEY (`loan_list_id`) REFERENCES `loan_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车产贷款信息';

/*Data for the table `apply_loan_car` */

insert  into `apply_loan_car`(`id`,`loan_list_id`,`name`,`idcard`,`phone`,`province`,`city`,`brand`,`age`,`limit`,`engine`,`use`,`car_property`,`datums`,`asset_certificates`) values ('123121212','123456789','Aleln','123456','13467897','北京','北京','代售点','20','6','6565','asdf','24657876545','/resources/file/basic/lvbu/datums/02.jpg','/resources/file/basic/lvbu/datums/02.jpg');

/*Table structure for table `apply_loan_house` */

DROP TABLE IF EXISTS `apply_loan_house`;

CREATE TABLE `apply_loan_house` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `loan_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款信息外键',
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名字',
  `idcard` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证',
  `phone` char(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '省份',
  `city` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '城市',
  `house_number` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '房产编号',
  `datums` mediumtext COLLATE utf8_unicode_ci COMMENT '个人资料,多份用英文'';''隔开',
  `asset_certificates` mediumtext COLLATE utf8_unicode_ci COMMENT '收入证明,多份用英文'';''隔开',
  `money` double DEFAULT NULL COMMENT '估算结果',
  PRIMARY KEY (`id`),
  KEY `FK_apply_loan_house` (`loan_list_id`),
  CONSTRAINT `FK_apply_loan_house` FOREIGN KEY (`loan_list_id`) REFERENCES `loan_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='房产贷款信息';

/*Data for the table `apply_loan_house` */

insert  into `apply_loan_house`(`id`,`loan_list_id`,`name`,`idcard`,`phone`,`province`,`city`,`house_number`,`datums`,`asset_certificates`,`money`) values ('123456','5467897','fsd','78945621','123456','北京','北京','12345678','/resources/file/basic/lvbu/datums/03.jpg','/resources/file/basic/lvbu/datums/03.jpg;/resources/file/basic/lvbu/datums/01.jpg',20000);

/*Table structure for table `apply_loan_info` */

DROP TABLE IF EXISTS `apply_loan_info`;

CREATE TABLE `apply_loan_info` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `loan_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '贷款列表表外键',
  `money` double DEFAULT NULL COMMENT '贷款金额',
  `time_limit` double DEFAULT NULL COMMENT '贷款期限(单位天)',
  `sex` char(12) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `phone` char(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `idcard` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证号码',
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件',
  `native_address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '户籍所在地',
  `native_info` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '户籍详信息',
  `address` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '现在居住地',
  `live_style` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '住房方式，租房和自居房等',
  `education` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学历',
  `marriage` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '婚姻状况',
  `house` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '房产状况',
  `has_credit` char(6) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '有无信用卡',
  `credit_card` char(25) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '信用卡号码',
  `industry` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作行业',
  `job_style` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '职业性质（全职兼职）',
  `job` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '担任职务',
  `job_city` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作城市',
  `company` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作公司',
  `public_tel` char(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '公司电话',
  `salary_from_bank` char(6) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工资是否属于银行代发',
  `year_income` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '年收入',
  `use` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用途',
  `month_back` double DEFAULT NULL COMMENT '每月可归还额度',
  `urgency_person` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '紧急联系人',
  `urgency_phone` char(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '紧急联系人电话',
  `datums` mediumtext COLLATE utf8_unicode_ci COMMENT '个人资料,多份用英文'';''隔开',
  `asset_certificates` mediumtext COLLATE utf8_unicode_ci COMMENT '收入证明,多份用英文'';''隔开',
  PRIMARY KEY (`id`),
  KEY `FK_apply_info_normal` (`loan_list_id`),
  CONSTRAINT `FK_apply_info_normal` FOREIGN KEY (`loan_list_id`) REFERENCES `loan_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='普通和急速模式贷款的申请信息';

/*Data for the table `apply_loan_info` */

insert  into `apply_loan_info`(`id`,`loan_list_id`,`money`,`time_limit`,`sex`,`name`,`phone`,`idcard`,`email`,`native_address`,`native_info`,`address`,`live_style`,`education`,`marriage`,`house`,`has_credit`,`credit_card`,`industry`,`job_style`,`job`,`job_city`,`company`,`public_tel`,`salary_from_bank`,`year_income`,`use`,`month_back`,`urgency_person`,`urgency_phone`,`datums`,`asset_certificates`) values ('123456','jiedai_lvbu',12360000,24,'1','吕布','15632569874','123456789123456789','lvbu@173.com','广东','广东理财师','北京花梨坎','0','3','0','asd','1','789456258012345645','金融','1','经理','北京','爱钱金融','1234564655','中国银行','2400000','公司周转',20000,'张飞','123456856','/resources/file/basic/lvbu/datums/01.jpg;/resources/file/basic/lvbu/datums/02.jpg;/resources/file/basic/lvbu/datums/03.jpg','/resources/file/basic/lvbu/datums/03.jpg');

/*Table structure for table `bill_invest` */

DROP TABLE IF EXISTS `bill_invest`;

CREATE TABLE `bill_invest` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'invest_member表外键',
  `invest_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财记录，只有消费、手续费、赎回、收益、手续费的时候用到这个字段',
  `card` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡，只有充值和提现使用到这个字段',
  `use_type` int(11) DEFAULT NULL COMMENT '资金类型，1充值，2提现，3消费（购买理财产品），4购买手续费，5赎回（仅本金），6收益，7转让手续费',
  `money` double DEFAULT NULL COMMENT '变动金额',
  `balance` double DEFAULT NULL COMMENT '变动后账户余额',
  `asset` double DEFAULT NULL COMMENT '变动后总资产',
  `status` int(11) DEFAULT NULL COMMENT '状态，1进行中，2成功，3失败',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作说明',
  `style` int(11) DEFAULT '4' COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  PRIMARY KEY (`id`),
  KEY `FK_bill_invest_member` (`member_info`),
  KEY `FK_bill_invest` (`card`),
  KEY `FK_bill_invest_list` (`invest_list_id`),
  CONSTRAINT `FK_bill_invest` FOREIGN KEY (`card`) REFERENCES `bind_card` (`id`),
  CONSTRAINT `FK_bill_invest_list` FOREIGN KEY (`invest_list_id`) REFERENCES `invest_list` (`id`),
  CONSTRAINT `FK_bill_invest_member_info` FOREIGN KEY (`member_info`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财资金记录';

/*Data for the table `bill_invest` */

insert  into `bill_invest`(`id`,`member_info`,`invest_list_id`,`card`,`use_type`,`money`,`balance`,`asset`,`status`,`opt_time`,`remark`,`style`) values ('123','lvbu','1','123',3,5000,12,1,2,'2016-08-24 11:30:18','12',1),('3','songjiang','1','123',3,10000,12,13,2,NULL,NULL,4),('312321312','lvbu','1','123',3,10000,312,1,2,'2016-09-07 11:46:53','123',1),('321','songjiang','1','123',3,50000,13,1,2,'2016-09-12 11:47:28',NULL,4),('321312','b4961a25ab5948abba3d8852c9c6f5a5','1','123',3,500000,312,312,2,NULL,NULL,4),('33','123','1','123',3,500,312,312,2,NULL,NULL,4);

/*Table structure for table `bill_loan` */

DROP TABLE IF EXISTS `bill_loan`;

CREATE TABLE `bill_loan` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `loan_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '放款还款对象---借款表loan_list外键',
  `status` int(11) DEFAULT NULL COMMENT '状态，1放款，2还款，3增加逾期费，4还逾期费',
  `card` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡，还款所使用的银行卡',
  `money` double DEFAULT NULL COMMENT '金额，放款+，还款-',
  `done_money` double DEFAULT NULL COMMENT '距离完成还款还需要还款金额',
  `bill_status` int(11) DEFAULT NULL COMMENT '状态，1操作中，2成功，3失败',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作说明',
  `style` int(11) DEFAULT NULL COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  `member_info` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bill_loan_liar` (`loan_list_id`),
  KEY `FK_bill_loan` (`card`),
  KEY `FK_bill_loan_member_info` (`member_info`),
  CONSTRAINT `FK_bill_loan` FOREIGN KEY (`card`) REFERENCES `bind_card` (`id`),
  CONSTRAINT `FK_bill_loan_liar` FOREIGN KEY (`loan_list_id`) REFERENCES `loan_list` (`id`),
  CONSTRAINT `FK_bill_loan_member_info` FOREIGN KEY (`member_info`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='投资者资金放款还款记录';

/*Data for the table `bill_loan` */

insert  into `bill_loan`(`id`,`loan_list_id`,`status`,`card`,`money`,`done_money`,`bill_status`,`opt_time`,`remark`,`style`,`member_info`) values ('13456','5467897',1,'123',1,1,1,'2016-08-24 15:45:50','we',2,'7c6cdd66bca3437c8384fae952930d2b');

/*Table structure for table `bind_card` */

DROP TABLE IF EXISTS `bind_card`;

CREATE TABLE `bind_card` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户公共表id外键',
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名字',
  `idcard` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证',
  `phone` char(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开户手机号',
  `bank_no` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡号',
  `open_bank` varchar(90) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开户行',
  `type` int(11) DEFAULT NULL COMMENT '类型，1储蓄卡，2借记卡，3信用卡',
  `photo_img1` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡正面照',
  `photo_img2` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡背面照',
  `opt_time` datetime DEFAULT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`),
  KEY `FK_bind_card` (`member_info_id`),
  CONSTRAINT `FK_bind_card` FOREIGN KEY (`member_info_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户银行卡';

/*Data for the table `bind_card` */

insert  into `bind_card`(`id`,`member_info_id`,`name`,`idcard`,`phone`,`bank_no`,`open_bank`,`type`,`photo_img1`,`photo_img2`,`opt_time`) values ('123','123','Allen','123456789123456789','18601920463','7894561230123456789','中国银行',1,'05c9d08607674e008552fb4a437c336c.jpg',NULL,'2016-08-15 11:38:08');

/*Table structure for table `contract` */

DROP TABLE IF EXISTS `contract`;

CREATE TABLE `contract` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `conPath` varchar(2000) COLLATE utf8_unicode_ci NOT NULL COMMENT '路径',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `state` int(11) DEFAULT '1' COMMENT '状态 1正常 0删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='合同\r\n';

/*Data for the table `contract` */

insert  into `contract`(`id`,`conPath`,`createTime`,`name`,`state`) values ('13456789','resources\\POReceiveReport.pdf','2016-09-19 10:03:08','POReceiveReport.pdf',1),('asdf456sdf','sdfsdf/sdf.pdf','2016-09-19 10:03:32','POReceiveReport.pdf',1);

/*Table structure for table `customer_manager` */

DROP TABLE IF EXISTS `customer_manager`;

CREATE TABLE `customer_manager` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '系统用户表Sys_user外键',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `work_number` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工号',
  `sex` char(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `idcard` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `photo` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
  `qr` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '二维码',
  `remark` mediumtext COLLATE utf8_unicode_ci COMMENT '描述',
  `createtime` datetime DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0' COMMENT '0没删除，1删除了',
  PRIMARY KEY (`id`),
  KEY `FK_custome_sys_user` (`user_id`),
  CONSTRAINT `FK_custome_sys_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='客户经理表';

/*Data for the table `customer_manager` */

insert  into `customer_manager`(`id`,`user_id`,`name`,`work_number`,`sex`,`idcard`,`email`,`phone`,`photo`,`qr`,`remark`,`createtime`,`is_delete`) values ('2fd42073b61d4d99a25aefdb10bd167a','56f6f851a3fa452ca6364024c169d96b','王思聪','WSC21308','男','130452197811053356','15611146202@sina.com','15611146202',NULL,NULL,'日了狗了','2016-09-21 14:43:30',0),('6cdd00e4ecf8430e8e1130d30c74c6e2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),('88d6eac7c86f41008b7efff656cce61b','97d57f49db95473ba1f5127deffdccd0','王麻子','W03','女','510811199502233333','977943333@qq.com','15600046133','','','王麻子的账号儿sa33','2016-08-10 09:59:27',0),('95724ed7968744e1af90e1eb1342b219','c883c60c396f4fa385cf66d93071facc','是个','','男','','','',NULL,NULL,'','2016-08-10 15:19:37',0),('dcae8aebb8924186b3673da73a232e30','65afb5b2d9344855a546a4e318adf537','王健林','WJL1515','男','510822194505055588','15611146201@sina.com','15611146201',NULL,NULL,'王思聪他爹','2016-09-21 14:54:39',0),('dfsdf','1212121212','李永辉','12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),('wangmazi','e732795b6a1e490cafb700ba029943a3','王麻子','WMZ','男','132051199105254485','wangmazi@sina.com','15894493596',NULL,NULL,NULL,'2016-08-31 10:45:41',0);

/*Table structure for table `friends` */

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `self` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'member_info_id外键',
  `target` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'member_info_id外键',
  `together` int(11) DEFAULT '1' COMMENT '相互关注，1非,2是',
  `opt_time` datetime DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`),
  KEY `FK_friends_self` (`self`),
  KEY `FK_friends_target` (`target`),
  CONSTRAINT `FK_friends_self` FOREIGN KEY (`self`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_friends_target` FOREIGN KEY (`target`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='好友表';

/*Data for the table `friends` */

insert  into `friends`(`id`,`self`,`target`,`together`,`opt_time`) values ('123456','0712505779b9476e831e1880ccdb9382','124',1,'2016-08-23 17:32:57'),('1236588','c8eb824f8e2c4976b9e816a2b6c85d60','lvbu',2,'2016-08-23 17:33:44'),('123789','123','c8eb824f8e2c4976b9e816a2b6c85d60',2,'2016-08-23 17:33:15'),('5478asdfae45sd','da5d2a3f259c4a2994ba22ab8b9d2d5f','lvbu',1,'2016-10-12 14:57:12'),('567dgs','c8eb824f8e2c4976b9e816a2b6c85d60','124',1,'2016-10-10 16:23:47'),('as23dsdwqer2345as','da5d2a3f259c4a2994ba22ab8b9d2d5f','songjiang',2,'2016-10-12 14:57:52'),('as34566745dw234ere123','songjiang','da5d2a3f259c4a2994ba22ab8b9d2d5f',2,'2016-10-12 14:58:31');

/*Table structure for table `friends_news` */

DROP TABLE IF EXISTS `friends_news`;

CREATE TABLE `friends_news` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `content` mediumtext COLLATE utf8_unicode_ci COMMENT '内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `state` int(11) DEFAULT '1' COMMENT '状态  1显示  0 删除',
  PRIMARY KEY (`id`),
  KEY `friendsnews_member_fk` (`member_id`),
  CONSTRAINT `friendsnews_member_fk` FOREIGN KEY (`member_id`) REFERENCES `member_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='好友动态';

/*Data for the table `friends_news` */

insert  into `friends_news`(`id`,`member_id`,`content`,`createtime`,`state`) values ('1231','songjiang','今天的好吃的','2016-10-13 17:25:38',1),('54678sd','lvbu','玩的很开心','2016-10-13 17:26:08',1);

/*Table structure for table `friends_news_img` */

DROP TABLE IF EXISTS `friends_news_img`;

CREATE TABLE `friends_news_img` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `img_path` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片路径',
  `friends_news_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_index` int(11) DEFAULT NULL COMMENT '排序位置',
  PRIMARY KEY (`id`),
  KEY `img_news_fk` (`friends_news_id`),
  CONSTRAINT `img_news_fk` FOREIGN KEY (`friends_news_id`) REFERENCES `friends_news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='好友动态图片表';

/*Data for the table `friends_news_img` */

insert  into `friends_news_img`(`id`,`img_path`,`friends_news_id`,`order_index`) values ('123','asd/asdf/tt.jpg','1231',1),('254','sdfa/sdf/tt.jpg','54678sd',1),('561','asdf/jsdf/sdf.jpg','1231',2);

/*Table structure for table `invest_list` */

DROP TABLE IF EXISTS `invest_list`;

CREATE TABLE `invest_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '投资用户id',
  `money` double DEFAULT '0' COMMENT '投资总金额',
  `back_income` double DEFAULT NULL COMMENT '赎回收益',
  `back_money` double DEFAULT NULL COMMENT '赎回(本金)',
  `use_ticket` int(11) DEFAULT '1' COMMENT '是否使用了理财券，1没有，2使用了',
  `invest_ticket_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '如果使用了理财券，理财券的id',
  `scale_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财标id',
  `total_money` double DEFAULT '0' COMMENT '账面总金额(投资金额+收益+理财券)',
  `income` double DEFAULT NULL COMMENT '实际收益（不含理财券奖励）',
  `ticket_bonus` double DEFAULT NULL COMMENT '实际理财奖励发放',
  `status` int(11) DEFAULT '1' COMMENT '理财状态，1资金匹配中,2投资收益中,3完成投资，4到期回款中，5回款成功，6回款失败，7过期',
  `open_date` datetime DEFAULT NULL COMMENT '创建日期',
  `back_date` datetime DEFAULT NULL COMMENT '实际回款日期',
  `pact_number` char(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同编号',
  `invest_style` int(11) DEFAULT NULL COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  `poundage_turn` double DEFAULT NULL COMMENT '转让手续费',
  `poundage_privilege` double DEFAULT NULL COMMENT '转让优惠手续费',
  `is_turn` int(11) DEFAULT '0' COMMENT '是否已经转让，0没，1是',
  `turn_status` int(11) DEFAULT '0' COMMENT '转让状态，1转让中，2成功，3过期，4失败',
  `calc_beginDate` datetime DEFAULT NULL COMMENT '开始计息时间',
  `calc_endDate` datetime DEFAULT NULL COMMENT '截止计息时间',
  `days` int(11) DEFAULT NULL COMMENT '天数',
  PRIMARY KEY (`id`),
  KEY `FK_invest_list_scales` (`scale_id`),
  KEY `FK_invest_list_ticket` (`invest_ticket_id`),
  KEY `FK_invest_list_member` (`member_info`),
  CONSTRAINT `FK_invest_list_member` FOREIGN KEY (`member_info`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_invest_list_scales` FOREIGN KEY (`scale_id`) REFERENCES `scale` (`id`),
  CONSTRAINT `FK_invest_list_ticket` FOREIGN KEY (`invest_ticket_id`) REFERENCES `invest_ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='投资记录表';

/*Data for the table `invest_list` */

insert  into `invest_list`(`id`,`member_info`,`money`,`back_income`,`back_money`,`use_ticket`,`invest_ticket_id`,`scale_id`,`total_money`,`income`,`ticket_bonus`,`status`,`open_date`,`back_date`,`pact_number`,`invest_style`,`poundage_turn`,`poundage_privilege`,`is_turn`,`turn_status`,`calc_beginDate`,`calc_endDate`,`days`) values ('1','123',123,123,123,0,'123456','123',0,12,12,2,'2016-08-24 11:29:35','2016-08-24 11:29:41','23',2,23,23,0,0,'2016-08-24 11:29:50','2016-08-24 11:29:53',30),('2','c8eb824f8e2c4976b9e816a2b6c85d60',23,5622,522,0,'5bba590159e148dc8fea0d8dec3bf06c','d3f2efe1b4b24b4fa32927dd72859911',0,3600,23,2,'2016-09-13 13:58:15','2016-09-13 13:58:18','6000',3,6333,5600,0,0,'2016-09-13 13:59:46','2016-09-13 13:59:49',30),('3','lvbu',23,23,23,0,'c8308d5ac410400f91b245356ae0af20','d3f2efe1b4b24b4fa32927dd72859911',0,36300,23,3,'2016-09-13 14:06:27','2016-09-13 14:06:29','620',1,233,23,0,0,'2016-09-13 14:06:51','2016-09-13 14:06:53',20),('4','da5d2a3f259c4a2994ba22ab8b9d2d5f',5200,200,5200,0,'d86bbd26b09c422ea6f7cabfe3981bc4','d3f2efe1b4b24b4fa32927dd72859911',5450,0,0,3,'2016-10-12 16:28:13','2016-11-12 16:28:06','410000',3,0,0,0,0,'2016-10-12 16:27:21','2016-11-12 16:27:15',30),('5','da5d2a3f259c4a2994ba22ab8b9d2d5f',1000,100,1000,1,'c8308d5ac410400f91b245356ae0af20','c9db3455fff445f482b9eb48edfd343a',1200,0,0,3,'2016-09-12 16:35:33','2016-11-12 16:35:46','510000',3,0,0,0,0,'2016-09-12 16:36:08','2016-10-12 16:36:14',30);

/*Table structure for table `invest_member` */

DROP TABLE IF EXISTS `invest_member`;

CREATE TABLE `invest_member` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `memberinfo_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `is_contract` int(11) DEFAULT '0' COMMENT '契约用户,0不是，1是',
  `balance` double DEFAULT NULL COMMENT '余额',
  `investing` double DEFAULT NULL COMMENT '投资中金额',
  `total_income` double DEFAULT NULL COMMENT '总收益',
  `total_money` double DEFAULT NULL COMMENT '总资产（余额+投资中金额）',
  `current_income` double DEFAULT NULL COMMENT '当期收益',
  PRIMARY KEY (`id`),
  KEY `member_info_invest_id` (`memberinfo_id`),
  CONSTRAINT `FK_INVEST_MEMBER_INFO` FOREIGN KEY (`memberinfo_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财用户列表';

/*Data for the table `invest_member` */

insert  into `invest_member`(`id`,`memberinfo_id`,`is_contract`,`balance`,`investing`,`total_income`,`total_money`,`current_income`) values ('23123asdfwqwezxwer34zw44sdf34543','123',0,1200,0,600,0,0),('2652bfce54f64a14aad8dd3f3d18c911','d2eef99f254a4bd1b00d8609dfc86059',0,10000,0,0,0,0),('990c2469a7414325982c8745c2048baa','da5d2a3f259c4a2994ba22ab8b9d2d5f',0,0,0,0,0,0),('asdf3412345asdf34523asfd435345asd','124',0,1250,0,954,0,0),('asdfw434534asdfg4546gwe436asd232','lvbu',0,630,0,50,0,0),('c230c4ae23c9404aa21c3c2e34dc70a5','84986744114e4c3e82e2703e6eb9770e',0,0,0,0,0,0),('e00ead0b3f6d4288b3fcadb9bc04895a','85178b2a4737458c8d9aed8e4fdfcb06',0,20000,10,4547,7478,12),('fdefc7f826eb4f28beca1930bd3c8032','14e012115ccd41a2a067bded58a7c0f5',0,0,0,0,0,0);

/*Table structure for table `invest_ticket` */

DROP TABLE IF EXISTS `invest_ticket`;

CREATE TABLE `invest_ticket` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券名字',
  `remark` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券说明',
  `describes` mediumtext COLLATE utf8_unicode_ci COMMENT '理财券详细描述',
  `money` double DEFAULT NULL COMMENT '价值',
  `begin_time` datetime DEFAULT NULL COMMENT '有效期始',
  `end_time` datetime DEFAULT NULL COMMENT '有效期至',
  `create_time` datetime DEFAULT NULL COMMENT '派发日期',
  `min_money` double DEFAULT NULL COMMENT '使用最小的投资金额权限',
  `product_ids` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '可使用产品，多个用'';''隔开',
  `state` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财券';

/*Data for the table `invest_ticket` */

insert  into `invest_ticket`(`id`,`name`,`remark`,`describes`,`money`,`begin_time`,`end_time`,`create_time`,`min_money`,`product_ids`,`state`) values ('123456','理财券1','只是个卷而已','不要当真',200,'2016-08-15 11:21:23','2016-08-21 11:21:31','2016-08-15 11:21:34',6000,'03261c163fde477583d4ffad20a7209a;7868430bc64f48e299f38f9e22c8305e;d79a0f74392f4505ab1c050d4eb8eb26',0),('5bba590159e148dc8fea0d8dec3bf06c','王宝强1','离婚1','sadf1',100,'2016-08-16 05:15:17','2016-08-25 05:15:21',NULL,10,NULL,1),('c8308d5ac410400f91b245356ae0af20','是否','爱上',NULL,100,'2016-08-16 16:19:03','2016-08-16 16:19:07','2016-08-16 16:19:13',234,NULL,1),('d86bbd26b09c422ea6f7cabfe3981bc4','里约1','奥运会1','阿斯蒂芬1',50,'2016-08-11 04:25:35','2016-08-25 04:25:39','2016-08-16 04:25:48',5001,'03261c163fde477583d4ffad20a7209a;7868430bc64f48e299f38f9e22c8305e',0);

/*Table structure for table `invite_code` */

DROP TABLE IF EXISTS `invite_code`;

CREATE TABLE `invite_code` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `invite_code` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '邀请码',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `state` int(11) DEFAULT '1' COMMENT '状态 0过期  1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='邀请码';

/*Data for the table `invite_code` */

/*Table structure for table `loan_list` */

DROP TABLE IF EXISTS `loan_list`;

CREATE TABLE `loan_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '借款用户外键',
  `pact_number` char(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同编号',
  `is_sell` int(11) DEFAULT '1' COMMENT '是否起售,1表示未起标,2表示起标,3表示标满',
  `poundage_state` int(11) DEFAULT '1' COMMENT '手续费状态,1未扣手续费,2已扣手续费',
  `money` double DEFAULT '0' COMMENT '申请借款金额',
  `real_money` double DEFAULT NULL COMMENT '实际审核金额',
  `manage_cost` double DEFAULT NULL COMMENT '平台管理费',
  `poundage` double DEFAULT NULL COMMENT '手续费',
  `get_money` double DEFAULT NULL COMMENT '应该放款金额',
  `yet_money` double DEFAULT NULL COMMENT '已经放款金额',
  `return_money` double DEFAULT NULL COMMENT '实际应该还款金额',
  `ago_money` double DEFAULT NULL COMMENT '逾期费',
  `yet_return_money` double DEFAULT NULL COMMENT '实际已经还款金额',
  `return_status` int(11) DEFAULT '1' COMMENT '还款状态，1未还款，2未还尽，3产生逾期，4完成还款',
  `loan_state` int(11) DEFAULT '1' COMMENT '放款状态,1未开始放款2放款中3放款完成',
  `apply_state` int(11) DEFAULT '1' COMMENT '申请状态,1待审核,2审核通过,3审核驳回,4已取消',
  `loan_type` int(11) DEFAULT '1' COMMENT '借款类型,1普通模式,2产权模式,3信用模式,4急速模式',
  `loan_use` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款用途',
  `loan_interest` double DEFAULT NULL COMMENT '借款利息',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `sign_date` datetime DEFAULT NULL COMMENT '签约日',
  `begin_return_date` datetime DEFAULT NULL COMMENT '开始还款日',
  `end_return_date` datetime DEFAULT NULL COMMENT '截止还款日',
  `done_return_date` datetime DEFAULT NULL COMMENT '实际完成还款日',
  `loan_style` int(11) DEFAULT '0' COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  `back_style` int(11) DEFAULT '2' COMMENT '还款方式，1等额本息，2到期一次性还款',
  `customer_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户经理外键',
  `pass_time` datetime DEFAULT NULL COMMENT '审核通过时间',
  `apply_content` mediumtext COLLATE utf8_unicode_ci COMMENT '审核内容',
  `days` int(11) DEFAULT NULL COMMENT '天数',
  PRIMARY KEY (`id`),
  KEY `FK_loan_list_load_member` (`member_info`),
  KEY `FK_loan_lis_type` (`loan_type`),
  KEY `FK_loan_list` (`customer_id`),
  CONSTRAINT `FK_loan_list` FOREIGN KEY (`customer_id`) REFERENCES `customer_manager` (`id`),
  CONSTRAINT `FK_loan_list_member` FOREIGN KEY (`member_info`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='借款列表';

/*Data for the table `loan_list` */

insert  into `loan_list`(`id`,`member_info`,`pact_number`,`is_sell`,`poundage_state`,`money`,`real_money`,`manage_cost`,`poundage`,`get_money`,`yet_money`,`return_money`,`ago_money`,`yet_return_money`,`return_status`,`loan_state`,`apply_state`,`loan_type`,`loan_use`,`loan_interest`,`createTime`,`sign_date`,`begin_return_date`,`end_return_date`,`done_return_date`,`loan_style`,`back_style`,`customer_id`,`pass_time`,`apply_content`,`days`) values ('123456789','7c6cdd66bca3437c8384fae952930d2b','5467891231',2,1,1000,1,1,1,1,1,1,1,1,1,2,2,2,'1',0.12,'2016-08-26 14:37:33','2016-08-26 14:37:35','2016-08-26 14:37:37','2016-08-26 14:37:40','2016-08-26 14:37:42',0,2,'88d6eac7c86f41008b7efff656cce61b','2016-09-02 18:02:47','1454545放水电费是',15),('12asd3sddas456asdsdf789','da5d2a3f259c4a2994ba22ab8b9d2d5f','AJSH2016083111',2,1,23000,20000,2000,1000,23000,0,0,0,0,1,2,2,1,'娶老婆',0.12,'2016-07-01 15:53:58','2016-10-12 14:54:01','2016-07-01 14:54:17','2016-10-12 14:54:50','2016-10-12 14:54:56',0,2,'wangmazi','2016-10-12 14:55:14','买房子',104),('5467897','7c6cdd66bca3437c8384fae952930d2b','789456123',2,1,1500,1,1,1,1,1,1,1,1,1,2,2,1,'1',0.15,'2016-08-24 15:45:05','2016-08-24 15:45:08','2016-08-24 15:45:11','2016-08-24 15:45:13','2016-08-24 15:45:16',0,2,'95724ed7968744e1af90e1eb1342b219','2016-09-02 15:45:27','1',15),('jidai_songjiang','songjiang','AJSH2016083112',2,1,100000,90000,8000,2000,90000,0,0,0,0,1,2,1,1,'宋江借钱装逼于梁山',0.15,'2016-08-31 11:12:44','2016-08-31 11:12:47','2016-08-31 11:12:53','2016-08-31 11:12:56','2016-08-31 11:12:59',2,2,'wangmazi','2016-09-02 11:13:08','找吴用asdfads',60),('jiedai_lvbu','lvbu','AJSH2016083111',2,1,50000,45000,4000,1000,45000,0,0,0,0,1,2,2,1,'吕布借钱养马侯貂蝉',0.12,'2016-08-31 10:43:06','2016-08-31 10:43:12','2016-08-31 10:43:15','2016-08-31 10:43:18','2016-08-31 10:43:24',2,2,'wangmazi','2016-09-02 10:43:52','问董卓',15);

/*Table structure for table `loan_member` */

DROP TABLE IF EXISTS `loan_member`;

CREATE TABLE `loan_member` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT 'member_info外键',
  `lend_money` double DEFAULT '0' COMMENT '总借款',
  `back_money` double DEFAULT '0' COMMENT '总还款',
  `cur_money` double DEFAULT '0' COMMENT '当前借款',
  `residue_money` double DEFAULT '0' COMMENT '剩余应还',
  `expect_money` double DEFAULT '0' COMMENT '总逾期',
  PRIMARY KEY (`id`),
  KEY `FK_loan_member_info_id` (`member_info_id`),
  CONSTRAINT `FK_loan_member_info_id` FOREIGN KEY (`member_info_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='借款用户列表';

/*Data for the table `loan_member` */

insert  into `loan_member`(`id`,`member_info_id`,`lend_money`,`back_money`,`cur_money`,`residue_money`,`expect_money`) values ('1308413d2244457987fa60656f7690c9','da5d2a3f259c4a2994ba22ab8b9d2d5f',0,0,0,0,0),('2201d8e84f554911b5e5674b351669f6','85178b2a4737458c8d9aed8e4fdfcb06',0,0,0,0,0),('32622dbd58da46d1ac468d4d7d9555cc','d2eef99f254a4bd1b00d8609dfc86059',0,0,0,0,0),('7b55b7f095e2408cb8eef8c0b790ac10','84986744114e4c3e82e2703e6eb9770e',0,0,0,0,0),('bc3016be3ebc46398766379baf869e10','14e012115ccd41a2a067bded58a7c0f5',0,0,0,0,0);

/*Table structure for table `member_extra_info` */

DROP TABLE IF EXISTS `member_extra_info`;

CREATE TABLE `member_extra_info` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '住址',
  `degree` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学位  博士后，博士，研究生，本科，大专，中专，高中',
  `job_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作类型',
  `school` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '毕业院校',
  `salary_year` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '年薪',
  `hobby` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '爱好',
  PRIMARY KEY (`id`),
  KEY `FK_member_extra_info_member_info` (`member_info_id`),
  CONSTRAINT `FK_member_extra_info_member_info` FOREIGN KEY (`member_info_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户额外信息（住址，爱好，毕业院校，学位等）';

/*Data for the table `member_extra_info` */

/*Table structure for table `member_info` */

DROP TABLE IF EXISTS `member_info`;

CREATE TABLE `member_info` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `login_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录名',
  `real_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  `nickname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `age` char(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '年龄',
  `sex` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `id_card` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证号码',
  `id_card_img1` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证正面照',
  `id_card_img2` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证背面照',
  `mi_describe` mediumtext COLLATE utf8_unicode_ci COMMENT '描述',
  `isdelete` char(2) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '是否删除  1：已删除 0未删除',
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `createuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
  `is_elite_account` int(11) DEFAULT '0' COMMENT '是否为金账户 0否 1是',
  `level` char(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '级别',
  `price` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '财力值',
  `password` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `pay_password` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付密码',
  `hand_password` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手势密码',
  `is_freeze` int(11) DEFAULT '0' COMMENT '是否冻结 0解冻 1冻结',
  `my_qr` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '我的二维码',
  `register_style` int(11) DEFAULT NULL COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  `cusmemberid` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '推荐会员的id，外键自己',
  `cusmembername` varchar(355) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '推荐会员的名字，放这主要为了方便查询',
  `customer_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '如果是客户经理的话，客户经理的外键',
  `useable_score` int(11) DEFAULT '0' COMMENT '积分',
  PRIMARY KEY (`id`),
  KEY `FK_member_info_cus` (`cusmemberid`),
  KEY `FK_member_info_customer` (`customer_id`),
  CONSTRAINT `FK_member_info_cus` FOREIGN KEY (`cusmemberid`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_member_info_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer_manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='基本用户表 ';

/*Data for the table `member_info` */

insert  into `member_info`(`id`,`login_name`,`real_name`,`nickname`,`email`,`age`,`sex`,`phone`,`id_card`,`id_card_img1`,`id_card_img2`,`mi_describe`,`isdelete`,`createTime`,`modifyTime`,`createuser`,`modifyuser`,`user_img`,`is_elite_account`,`level`,`price`,`password`,`pay_password`,`hand_password`,`is_freeze`,`my_qr`,`register_style`,`cusmemberid`,`cusmembername`,`customer_id`,`useable_score`) values ('0712505779b9476e831e1880ccdb9382','asdf1','asdf1','panyalan','213212123@131.com','18','2','21312321','1231324564','f659fd9bba99477297eb6fb0d84daf68.jpg','cc2decde784e497ead5b8b9c147eaf82.jpg','sdfsd1','0','2016-08-03 16:56:46','2016-08-05 10:00:29','0','0',NULL,0,'5','5001','8888',NULL,'0',0,NULL,1,'123',NULL,'wangmazi',0),('123','18601920462','二蛋子','1235','18601920462@163.com','26','1','18601920462',NULL,'4c27fbd5f5e24cadbd8eed6432d28381.jpg','cb6b0dfcd4a444aaad1d8da0852c7d02.jsp',NULL,'0','2016-08-02 11:22:04',NULL,NULL,NULL,NULL,0,NULL,NULL,'1234566','1235',NULL,0,NULL,1,'lvbu',NULL,'wangmazi',0),('124','18901256863','An','宋江','songjiang@163.com','36','0','18901256863',NULL,NULL,NULL,NULL,'1','2016-08-02 11:22:08',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,1,NULL,2,'123',NULL,'wangmazi',0),('14e012115ccd41a2a067bded58a7c0f5','15311528762',NULL,'手机用户15311528762',NULL,NULL,NULL,'15311528762',NULL,NULL,NULL,NULL,'0','2016-10-11 17:13:07',NULL,NULL,NULL,NULL,0,NULL,NULL,'SiM83CpjqIc=',NULL,NULL,0,NULL,3,NULL,NULL,NULL,0),('7c6cdd66bca3437c8384fae952930d2b','asd','asdf1','asd','sdf@173.com','18','1','18601920463','123456789123456789','15cf76648a0d4ab5b2c08ac584790619.jsp','23af45ae9ab44867875e2e9b3e981ebc.gif','','0','2016-08-11 11:30:18','2016-08-11 11:30:18','0','0',NULL,0,'1','0','asdf',NULL,'0',0,NULL,3,'123',NULL,'dfsdf',0),('84986744114e4c3e82e2703e6eb9770e','15311527763',NULL,'手机用户15311527763',NULL,NULL,NULL,'15311527763',NULL,NULL,NULL,NULL,'0','2016-10-12 10:34:47',NULL,NULL,NULL,NULL,0,NULL,NULL,'j1in5uunAQc=',NULL,NULL,0,NULL,3,NULL,NULL,NULL,0),('85178b2a4737458c8d9aed8e4fdfcb06','13525742296',NULL,'手机用户13525742296',NULL,NULL,NULL,'13525742296',NULL,NULL,NULL,NULL,'0','2016-09-29 10:09:09',NULL,NULL,NULL,NULL,0,NULL,NULL,'gCrXlx/xwUvdHzLEQfhngA==',NULL,NULL,0,NULL,3,NULL,NULL,NULL,0),('b4961a25ab5948abba3d8852c9c6f5a5','asdfasdf','sdf','adf','','','1','87978',NULL,'af7b7ccedcd94758896257f33e0f0138.jpg','7a2b4de709414672b408996c181cdb9f.jpg','','0','2016-08-04 11:12:16','2016-08-04 11:12:16','0','0',NULL,0,'1','','sdf',NULL,NULL,0,NULL,3,'123',NULL,'wangmazi',0),('c8eb824f8e2c4976b9e816a2b6c85d60','Allen','王丹丹','','','18','1','','','c907d7bc8e0c48248be3070ea43aa4cf.jsp','af96323a3f3c4b7297fa43508124efa4.png','','0','2016-08-11 13:59:21','2016-08-11 13:59:21','0','0',NULL,0,'1','0','',NULL,NULL,0,NULL,4,'123',NULL,'wangmazi',0),('d2eef99f254a4bd1b00d8609dfc86059','15311527762','楣垮皬鏅�','手机用户15311527762','409779813@qq.com',NULL,NULL,'15311527762','130723199107153211',NULL,NULL,NULL,'0','2016-09-29 10:46:44',NULL,NULL,NULL,NULL,0,NULL,NULL,'SiM83CpjqIc=',NULL,NULL,0,NULL,3,NULL,NULL,NULL,0),('da5d2a3f259c4a2994ba22ab8b9d2d5f','15311527765','15311527765','手机用户15311527765',NULL,NULL,NULL,'15311527765',NULL,NULL,NULL,NULL,'0','2016-10-12 14:44:47',NULL,NULL,NULL,NULL,0,NULL,NULL,'SiM83CpjqIc=',NULL,NULL,0,NULL,3,NULL,NULL,NULL,0),('lvbu','13811294856','吕布','小白脸','lvbu@163.com','19','1','13811294856','352261945565622444','a3fe3c12cfd34ab8ba9ca1dcb0f2e6ff.jsp','bd81f81b656a4fa7b344a41615bdff97.png','','0','2016-08-05 11:01:32','2016-08-05 11:01:32','0','0',NULL,1,'3','100','j1in5uunAQc=',NULL,'0',0,NULL,2,'123',NULL,'wangmazi',0),('songjiang','15895594965','宋江','及时雨','songjiang@sina.com','42','1','15600465150','510821144495515151','78dbd842027b40ab81d95c2687dee078.jpg',NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,1,'123',NULL,'wangmazi',0);

/*Table structure for table `member_invest_ticket` */

DROP TABLE IF EXISTS `member_invest_ticket`;

CREATE TABLE `member_invest_ticket` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财用户id',
  `invest_ticket_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券id',
  `resource_style` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券来源',
  `is_use` int(11) DEFAULT NULL COMMENT '是否使用,1未使用,2已使用',
  PRIMARY KEY (`id`),
  KEY `FK_member_invest_ticket_ticket` (`invest_ticket_id`),
  KEY `FK_member_info` (`member_info`) USING BTREE,
  CONSTRAINT `FK_member_info` FOREIGN KEY (`member_info`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_member_invest_ticket_ticket` FOREIGN KEY (`invest_ticket_id`) REFERENCES `invest_ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='我的理财券';

/*Data for the table `member_invest_ticket` */

insert  into `member_invest_ticket`(`id`,`member_info`,`invest_ticket_id`,`resource_style`,`is_use`) values ('123','123','5bba590159e148dc8fea0d8dec3bf06c','爱上',0),('125','124','c8308d5ac410400f91b245356ae0af20','阿萨德',1);

/*Table structure for table `member_ticket_record` */

DROP TABLE IF EXISTS `member_ticket_record`;

CREATE TABLE `member_ticket_record` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `invest_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财列表外键',
  `invest_ticket_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券外键',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`id`),
  KEY `FK_ticket_user_record_member` (`invest_list_id`),
  KEY `FK_ticket_user_record_ticket` (`invest_ticket_id`),
  CONSTRAINT `FK_ticket_user_record_list` FOREIGN KEY (`invest_list_id`) REFERENCES `invest_list` (`id`),
  CONSTRAINT `FK_ticket_user_record_ticket` FOREIGN KEY (`invest_ticket_id`) REFERENCES `invest_ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财券使用记录';

/*Data for the table `member_ticket_record` */

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `receiver` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'member——info外键',
  `sender` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'member——info外键,null表示系统发送消息',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `title` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '内容',
  `readed` int(11) DEFAULT '0' COMMENT '是否已读，0未读，1已读',
  `state` int(11) DEFAULT '1' COMMENT '0删除 1正常',
  PRIMARY KEY (`id`),
  KEY `FK_message_member1` (`receiver`),
  KEY `FK_message_member2` (`sender`),
  CONSTRAINT `FK_message_member1` FOREIGN KEY (`receiver`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_message_member2` FOREIGN KEY (`sender`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='消息';

/*Data for the table `message` */

insert  into `message`(`id`,`receiver`,`sender`,`time`,`title`,`content`,`readed`,`state`) values ('123','123','0712505779b9476e831e1880ccdb9382','2016-08-18 15:37:44','你说我美不美','美的不要不要的',1,1),('124568','da5d2a3f259c4a2994ba22ab8b9d2d5f','123','2016-10-14 11:00:00','asd','asd',1,1),('13245','123','da5d2a3f259c4a2994ba22ab8b9d2d5f','2016-10-14 10:50:53','你说我美不美','美的不要不要的',0,1),('89565','da5d2a3f259c4a2994ba22ab8b9d2d5f','124','2016-10-14 15:23:53','sd','tttttttttt',1,0);

/*Table structure for table `news_information` */

DROP TABLE IF EXISTS `news_information`;

CREATE TABLE `news_information` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `content` mediumtext COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `img` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片路径',
  `createuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT '1' COMMENT '状态 1正常 0删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='新闻资讯';

/*Data for the table `news_information` */

insert  into `news_information`(`id`,`content`,`createtime`,`img`,`createuser`,`state`) values ('123456','你说今天发生了什么','2016-10-17 16:55:33','',NULL,1),('28b6524e180f48e9afe6a0f3ffb2ea28','asdfasdfasdf','2016-10-17 11:54:47',NULL,'0',1),('45687','王宝强','2016-10-13 16:55:52',NULL,NULL,0),('5c98506a7b714ca8a34ed2d443902107','asdfsdfsdf','2016-10-17 11:40:50','0f466af55fc5463a868ca7bc51ad121b.jpg','0',1);

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) DEFAULT '2' COMMENT '状态，1启动弹窗，2通告列表内，3都有',
  `publish_time` datetime DEFAULT NULL COMMENT '发布日期',
  `title` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` mediumtext COLLATE utf8_unicode_ci COMMENT '内容',
  `begin_date` datetime DEFAULT NULL COMMENT '有效期始',
  `end_date` datetime DEFAULT NULL COMMENT '有效期至',
  `readed` int(11) DEFAULT '0' COMMENT '已读1 未读0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='通告';

/*Data for the table `notification` */

insert  into `notification`(`id`,`status`,`publish_time`,`title`,`content`,`begin_date`,`end_date`,`readed`) values ('123456',2,'2016-08-19 11:27:52','asd','sdf','2016-08-19 11:27:58','2016-08-27 11:28:00',0),('258963',3,'2016-08-18 11:28:53','红楼梦','一场戏','2016-08-20 11:29:07','2016-08-21 11:29:13',0),('4834cbbf8332490cacf8f096ceebaa1e',3,'2016-08-19 02:09:20','asd士大夫','asdf','2016-08-19 14:10:53','2016-08-19 14:10:56',0),('55614',1,'2016-08-20 11:28:14','三国演义','分分合合','2016-08-20 11:28:30','2016-08-28 11:28:35',0),('c5b2fb6d5576426aaf533afa47b7764c',2,'2016-08-20 02:15:32','阿斯蒂芬1','阿斯蒂芬1','2016-08-22 02:15:35','2016-08-29 02:15:39',0),('cb51243a6f25124a29sba22ab8b9d2d5f',1,'2016-10-12 15:00:08','号外号外','2016年没有假期了','2016-10-12 15:00:41','2016-11-04 15:00:44',0),('da5d2a3f259c4a2344ba22ab8b9d2d53',2,'2016-10-12 15:01:37','雾霾','雾霾天气严重，怎么还要上班','2016-10-12 15:02:25','2016-11-05 15:02:27',0);

/*Table structure for table `notification_readed` */

DROP TABLE IF EXISTS `notification_readed`;

CREATE TABLE `notification_readed` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notification` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_notification_readed` (`member_info`),
  KEY `FK_notification_readed_notify` (`notification`),
  CONSTRAINT `FK_notification_readed` FOREIGN KEY (`member_info`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_notification_readed_notify` FOREIGN KEY (`notification`) REFERENCES `notification` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='通知阅读记录';

/*Data for the table `notification_readed` */

insert  into `notification_readed`(`id`,`member_info`,`notification`) values ('56rtd2a3f259c4a2994ba22a9tu9d98op','da5d2a3f259c4a2994ba22ab8b9d2d5f','da5d2a3f259c4a2344ba22ab8b9d2d53'),('da5d2a36959c4b3994ba22ab8b9d2d5f','da5d2a3f259c4a2994ba22ab8b9d2d5f','cb51243a6f25124a29sba22ab8b9d2d5f');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `category` int(11) NOT NULL COMMENT '产品类型，默认1信贷产品，2房标类产品，3车标',
  `name_zh` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '总名称  如：年年余',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称  6月期，12月期',
  `name_describe` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `yield_describe` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收益率描述',
  `yield` double DEFAULT NULL COMMENT '准确的收益率',
  `time_limit` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '时长',
  `title1` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题1',
  `title2` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题2',
  `is_sell` int(11) DEFAULT NULL COMMENT '是否起售 0：停售 1：起售',
  `is_new_product` int(11) DEFAULT NULL COMMENT '是否为新品  0:否  1：是',
  `is_recommend` int(11) DEFAULT NULL COMMENT '是否推荐 0否 1是推荐',
  `product_describe` mediumtext COLLATE utf8_unicode_ci COMMENT '介绍',
  `risk_control` mediumtext COLLATE utf8_unicode_ci COMMENT '风险控制',
  `details` mediumtext COLLATE utf8_unicode_ci COMMENT '更多详情',
  `isdelete` int(11) DEFAULT '0' COMMENT '是否删除  1：已删除 0未删除',
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `createuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyuser` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `days` int(11) DEFAULT NULL COMMENT '天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财产品表';

/*Data for the table `product` */

insert  into `product`(`id`,`category`,`name_zh`,`name`,`name_describe`,`yield_describe`,`yield`,`time_limit`,`title1`,`title2`,`is_sell`,`is_new_product`,`is_recommend`,`product_describe`,`risk_control`,`details`,`isdelete`,`createtime`,`modifytime`,`createuser`,`modifyuser`,`days`) values ('03261c163fde477583d4ffad20a7209a',1,'dfs','asd','sdf','sdf',132123,NULL,'asdf','asdf',0,0,0,'asdf','asdf','asdf',1,'2016-08-08 11:32:57','2016-08-08 11:32:57','0','0',15),('195add1830134f238c5d8c5f4a0ebc94',1,'tttt','ttt','sdf','12',12,NULL,'12','',1,1,1,'12','12','12',0,NULL,'2016-09-06 09:46:32',NULL,'0',15),('413c9f04152046418b880373ef081f03',2,'56','56','','',56,NULL,'','',1,1,1,'','','',1,NULL,NULL,NULL,NULL,15),('4548c7b992f04e67bbf202fcc0b7f6cc',1,'月月盈','月月盈','每月循环滚利','超高52%',0.052,NULL,'1','1',1,1,1,'1','1','1',0,NULL,'2016-08-31 15:17:20',NULL,'0',30),('4d11022b613847db92dc4c8a09340ce5',2,'676','67','','',67,NULL,'','',1,1,1,'','','',1,NULL,NULL,NULL,NULL,15),('71d59b80dbea4ce2b9163fa3dae5f9e9',1,'er','we','','',0.20000000298023224,NULL,'','',1,1,1,'','','',1,NULL,NULL,NULL,NULL,15),('7868430bc64f48e299f38f9e22c8305e',1,'af','sdf','','',123,NULL,'','',1,1,1,'','','',0,NULL,NULL,NULL,NULL,15),('d79a0f74392f4505ab1c050d4eb8eb26',2,'sdf1','sdf1','sdf1','asdf1',0.23000000417232513,NULL,'sdf1','sd1',1,0,1,'1','sdfsdf1','sdfsdfsdf1',0,NULL,NULL,NULL,NULL,15);

/*Table structure for table `scale` */

DROP TABLE IF EXISTS `scale`;

CREATE TABLE `scale` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '标id',
  `product_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品id',
  `name` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标名称',
  `begin_time` date DEFAULT NULL COMMENT '开标时间',
  `end_time` date DEFAULT NULL COMMENT '标最晚结束时间',
  `time_limit` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '投资时长描述',
  `calc_begin_time` date DEFAULT NULL COMMENT '计算收益开始日',
  `calc_end_time` date DEFAULT NULL COMMENT '计算收益截止日',
  `revenue` double DEFAULT '0' COMMENT '收益率',
  `revenue_add` double DEFAULT '0' COMMENT '增加收益率',
  `max_limit` double DEFAULT NULL COMMENT '单笔限额',
  `return_style` int(11) DEFAULT '1' COMMENT '还款方式,1到期一次还本付息',
  `tags` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标签，英文“;”隔开',
  `use_ticket` int(11) DEFAULT '1' COMMENT '是否可以使用理财券，0不可以，1可以',
  `transfer` int(11) DEFAULT '1' COMMENT '是否可以转让，0不可以，1可以',
  `turn_date` date DEFAULT NULL COMMENT '最早转让时间',
  `total_money` double DEFAULT NULL COMMENT '标的总金额',
  `residue_money` double DEFAULT NULL COMMENT '剩余可投金额',
  `yet_money` double DEFAULT NULL COMMENT '实际已投入金额',
  `score_bonus` int(11) DEFAULT '1' COMMENT '积分奖励，0没有，1有',
  `ones_score` int(11) DEFAULT NULL COMMENT '每份标（500块）所获得积分',
  `status` int(11) DEFAULT '0' COMMENT '状态，0新建标，1可投入，2还有机会，3已完成',
  `is_turn` int(11) DEFAULT '0' COMMENT '是否属于转让标，0不是，1是',
  `invest_list` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '转让标的原来的理财记录',
  PRIMARY KEY (`id`),
  KEY `FK_scale_product` (`product_id`),
  KEY `FK_scale` (`invest_list`),
  CONSTRAINT `FK_scale` FOREIGN KEY (`invest_list`) REFERENCES `invest_list` (`id`),
  CONSTRAINT `FK_scale_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='投资标列表表';

/*Data for the table `scale` */

insert  into `scale`(`id`,`product_id`,`name`,`begin_time`,`end_time`,`time_limit`,`calc_begin_time`,`calc_end_time`,`revenue`,`revenue_add`,`max_limit`,`return_style`,`tags`,`use_ticket`,`transfer`,`turn_date`,`total_money`,`residue_money`,`yet_money`,`score_bonus`,`ones_score`,`status`,`is_turn`,`invest_list`) values ('123','03261c163fde477583d4ffad20a7209a','年年1','2016-08-24','2016-08-24','5','2016-08-24','2016-08-24',0,0,2,1,'1',1,1,'2016-08-24',1,1,1,1,6,1,0,'1'),('456789','4d11022b613847db92dc4c8a09340ce5','asd','2016-08-26','2016-08-26','1','2016-08-26','2016-08-26',0,0,2,1,'1',1,1,'2016-08-26',1,1,1,1,6,1,0,'1'),('81efaabea83a4bd2b50f6d6bace51d88','4548c7b992f04e67bbf202fcc0b7f6cc','sdfretrrrrr','2016-09-08','2016-09-28','',NULL,NULL,12,0,12,1,'',1,1,NULL,0,0,0,1,0,0,0,NULL),('c9db3455fff445f482b9eb48edfd343a','4548c7b992f04e67bbf202fcc0b7f6cc','吉吉','2016-09-02','2016-09-09','',NULL,NULL,12,12,12,1,'',1,1,NULL,1000,0,0,1,10,1,0,NULL),('d3f2efe1b4b24b4fa32927dd72859911','4548c7b992f04e67bbf202fcc0b7f6cc','月月盈一号','2016-08-30','2016-09-05','单月快速产品',NULL,NULL,0.052,0.003,100000,1,'单月盈  月月盈',1,1,'2016-09-15',150000,0,0,1,1,1,0,NULL);

/*Table structure for table `scale_loan_list` */

DROP TABLE IF EXISTS `scale_loan_list`;

CREATE TABLE `scale_loan_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `scale` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标外键',
  `loan_list` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款外键',
  PRIMARY KEY (`id`),
  KEY `FK_scale_loan_list` (`scale`),
  KEY `FK_scale_loan_list2` (`loan_list`),
  CONSTRAINT `FK_scale_loan_list` FOREIGN KEY (`scale`) REFERENCES `scale` (`id`),
  CONSTRAINT `FK_scale_loan_list2` FOREIGN KEY (`loan_list`) REFERENCES `loan_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='标包含的借款记录';

/*Data for the table `scale_loan_list` */

insert  into `scale_loan_list`(`id`,`scale`,`loan_list`) values ('0123456','123','5467897'),('26b98d545b5c48d8a907c72ac5160092','c9db3455fff445f482b9eb48edfd343a','jidai_songjiang'),('48c7bca2e39c45229bf74aa4d20954fd','c9db3455fff445f482b9eb48edfd343a','5467897'),('5eef9c3bec9f4ec5a04f1a6515320407','c9db3455fff445f482b9eb48edfd343a','123456789'),('5sfdge3','123','123456789'),('7e3a9bde18c8434a825cce63f3cd92b0','456789','5467897'),('8c8988aee7da44a9b5f6c496b29ef413','d3f2efe1b4b24b4fa32927dd72859911','jiedai_lvbu'),('d09eb8d179ae4e539902e26b1193b873','d3f2efe1b4b24b4fa32927dd72859911','jidai_songjiang'),('d96715c41cc2418393928774d2a6e6ca','456789','123456789'),('dfebc7c812a24ae5858340f224db1ae3','c9db3455fff445f482b9eb48edfd343a','jiedai_lvbu');

/*Table structure for table `score_list` */

DROP TABLE IF EXISTS `score_list`;

CREATE TABLE `score_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户公共表id外键',
  `score` int(11) DEFAULT NULL COMMENT '获得积分，正数获得，负数花费',
  `resource_from` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '获得来源',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `FK_score_list` (`member_info_id`),
  CONSTRAINT `FK_score_list` FOREIGN KEY (`member_info_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='积分记录';

/*Data for the table `score_list` */

/*Table structure for table `sign` */

DROP TABLE IF EXISTS `sign`;

CREATE TABLE `sign` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `signDate` datetime NOT NULL COMMENT '签到日期',
  `signCount` int(11) NOT NULL DEFAULT '1' COMMENT '连续签到天数 默认为1,',
  `score` int(11) NOT NULL DEFAULT '1' COMMENT '签到获得的积分数',
  PRIMARY KEY (`id`),
  KEY `member_sign_fk` (`member_id`),
  CONSTRAINT `member_sign_fk` FOREIGN KEY (`member_id`) REFERENCES `member_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='签到表';

/*Data for the table `sign` */

/*Table structure for table `sms` */

DROP TABLE IF EXISTS `sms`;

CREATE TABLE `sms` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `phone` char(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '接收手机号码',
  `content` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `createTime` datetime NOT NULL COMMENT '发送时间',
  `platform` int(11) DEFAULT '4' COMMENT '1线下，2Android，3IOS，4平台系统',
  `state` int(11) DEFAULT '0' COMMENT '1已发送 0未发送',
  `mesNum` int(11) DEFAULT NULL COMMENT '任务需要的短信条数',
  `price` double(22,0) DEFAULT NULL COMMENT '单价',
  `money` double(22,0) DEFAULT NULL COMMENT '花费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='短信表';

/*Data for the table `sms` */

insert  into `sms`(`id`,`phone`,`content`,`createTime`,`platform`,`state`,`mesNum`,`price`,`money`) values ('123456','18601920463','你太帅了','2016-09-19 17:33:28',4,1,1,1,1);

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '库存id',
  `loan_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款记录id',
  `money` double DEFAULT '500' COMMENT '库存金额，默认500',
  `fetch_money` double DEFAULT NULL COMMENT '取走金额',
  `scale_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用于理财标',
  `invest_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财记录id',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  `fetch_time` datetime DEFAULT NULL COMMENT '库存分配出去时间',
  `difference` double DEFAULT '0' COMMENT '库存出入差',
  `status` int(11) DEFAULT '0' COMMENT '状态，0仅入库，1分配到理财标（待支付）,2成功',
  `is_turn` int(11) DEFAULT '0' COMMENT '是否属于库存转让,0是，1不是',
  `turn_invest_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '转让到的理财记录',
  PRIMARY KEY (`id`),
  KEY `FK_stock_invest_member` (`invest_list_id`),
  KEY `FK_stock_loan_member` (`loan_list_id`),
  KEY `FK_stock_scale` (`scale_id`),
  KEY `FK_stock` (`turn_invest_list_id`),
  CONSTRAINT `FK_stock` FOREIGN KEY (`turn_invest_list_id`) REFERENCES `invest_list` (`id`),
  CONSTRAINT `FK_stock_invest_member` FOREIGN KEY (`invest_list_id`) REFERENCES `invest_list` (`id`),
  CONSTRAINT `FK_stock_loan_member` FOREIGN KEY (`loan_list_id`) REFERENCES `loan_list` (`id`),
  CONSTRAINT `FK_stock_scale` FOREIGN KEY (`scale_id`) REFERENCES `scale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='库存';

/*Data for the table `stock` */

insert  into `stock`(`id`,`loan_list_id`,`money`,`fetch_money`,`scale_id`,`invest_list_id`,`create_time`,`fetch_time`,`difference`,`status`,`is_turn`,`turn_invest_list_id`) values ('011f133f47c84509a723e765e722be31','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911','1','2016-08-31 15:22:49','2016-09-02 15:00:00',0,0,0,NULL),('017c9e2b139f4f48a8f9a87c28d1d986','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('032c1580ff9d49ac9737402fe71fe4ce','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911','1','2016-08-31 15:22:49',NULL,0,0,0,NULL),('03e510275dc04f22aaa92416a2d88dab','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911','1','2016-08-31 15:22:49',NULL,0,0,0,NULL),('04a61da5c09142e392302c79da0972b2','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('04ab520706854750a317a112799f014e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('04c0b587020c41ff94cf4ba3b6a1f9d5','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('04cd3de640dd4439b1be81976248b81a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('059f9c19df13412abb743199f2a3c327','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('05ad14a6eb19405db75c86d2a2cb2ee1','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('05cce60ad7c743b8a3a24a08cc04c53f','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('060243eacac3442f925201ff0bcf3d7e','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('0626c1c3ab734a71a076b5b61d9a1ea2','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('0636cc3d2d91450ebc509f00eedda70f','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('07287b927ca54f439ce5cf7f33564a85','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('08ab30a7e0664caf9a91a3be52d9fce6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('08c26ffaf95a4d72b3e57a6503752f5b','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('08cc7f720ded41ffa4e579ea3379e225','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('098bd89ca7aa4eba98b32c81620189e2','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('098d5b0ef56d4b93b5755ec799cde3d8','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('09c9385d95bd44618081ab69e343829b','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('0a208cc1270845b88e1b1ea331724b2b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('0a6676bff5284186a4426ac55f974162','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('0a68766826c541d79a085c089b8d7336','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('0b81ecbd142342e68edfe0ad57e1bd96','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('0bbb3c1f9ebd4b519b0a46d97233aec6','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('0bd9f05954214ef7913a53bff6befca5','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('0bee99c8c51d4ff2be19589ee69700c1','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('0dc4a524dfea45f9a3e530c557bb3c9b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('0e6c77ea1de043698435315d8ba68635','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('0e815cf4f75b4a1999ca921aceb940a6','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('0ebfa195c5de445281bb5c4c9e8a9094','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('0ee5789314254943b3d2c85c0d476cfd','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('0fb9c970657a4e7b836eb8d96ee92c76','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('100eeadfc6b341c2a6fddaac26538512','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('10251c0c91e14125a8fcea8108c5e842','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('103550d311004f1b9a899bd6759eb9ff','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('1048da7d932c4eb897a5e352022f3d27','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('107276580fe94e75810749e2a5cfd112','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('108cc670827f49ef8658c11b4e0e1b0b','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1093acf0bf0a455088743b29155868e1','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('11758a01751c4c9c8b75fc09f94c162e','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('11cdb920c1ce494fa94c22dcc59025d9','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('1281d37046c0449baa44be3418eabd3e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('13275febcf324c07b0d08434552c7466','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('135b0e4bf41443fba2e1303012c1ea9f','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1362a1b6cab74ee0af572c4201ad2263','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('1378b64f4c774922b1db1e671605d548','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('14b650c9e16142a1b6b6ceec0478ee06','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1526c795be464979991d5bbe75a334f7','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('1583e76c746c439eab321b9861250141','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('15f0ad62a4654218909d1ab4e5f8b051','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('15f8b115cc5545249f92018ab2ad002d','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1658e72f51534dd78999565b37b922cd','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('16a00cb65aa74123acf729ef9875cd71','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('16cc4826ff0b46adb121c658623e137b','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('16d23333a4a843bf8e5680db41468896','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('16d551604e7441828bd1b28e411b89ac','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('171f2a3217dc421ca291630c2ae7dabe','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('1771e677b68e420dae21bd6d354959f0','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1886fc2a044e4c80aacabf52ea1ebb9a','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('18b4760bf5f94944bdbdc1e0b18df9f1','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('18b99797271545f2b88f225e26d136ed','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('191809255d0b494aa5912c08d3dc4383','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('1928c4b676a84b898bd1d1029f5fd6f7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('19584c92e86e440b9ddf50dc88ce06e3','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('198233acad334d6bbe312aff85aa796e','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1a4a2ce686c84ecc8bf885f03e3d4668','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('1a71161dff9d4fa79eb5806879165403','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1b75fda9fd5741829ad61bbc315aab75','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1ba73763b8d3430aa964a17f4d83b54c','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('1dd471501dd942eeb3562ba773fc0232','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('1df6f9d17af14222b020bfd2173bf265','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('1edb88afb28b4264a5f5e4aa3fd8aaa2','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1ee8485ebc694303b828989767b00d39','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('1f5493017fe048f290e5fe9f7fa1cacc','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('1f7a77272d0f44859842bdfe505ad483','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('1ffb5a9bd8d5492b92b533340dc8423a','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('210f103257964c8eb99625e4ad4b74a8','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('21a5b367d5a24a23b62cf3aa88e1a1e8','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('23a17ad388dc4f4a9e8e08b56206e3e5','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('23c8e418ac9d4c35b70c04adad7e85f4','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2472d97845a64ab3b191d1d5fbad25c0','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('24bed7e4e1354a6888512f7f21b15d15','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2521c2c3f27642f0870473660a6d185d','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('254b5c8574b64984ab83ce1bf8038e4f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('256c5036d7ed49c1a8085a49e6e85f75','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('25f75367ee574c9589190fdafc192f69','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('26cb25139c954d20ab1450e61e768860','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('26e31a317044469f81286364aaead174','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('26f1af0ffcee433990cb6c1e372e934c','123456789',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 18:22:19',NULL,0,0,0,NULL),('272890b1629e41e086a0acc364845aa5','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('2762c02c96f244d2a14a278d3fc2462a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('27e4cb6faf0640b19e3a976e4a2eeca9','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('2896318c90ba4772af746014385cbf01','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('28b8f99bbc14426fb95c495af65e5eef','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('28e07b3680dd4a1d8ab546021c1670bc','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('29a84adba5b241d78cb0b67d1032c59d','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('2a0141e020f4419c8059348b2864793b','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2a8bb95970244b799ef7ed05a09b6ff6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('2a9dd8083c924c16817f566cd8142dcb','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2afcec996f624de787a0a821728f2e37','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('2b1dd0455c3347ce8db75d6e05d6d5b7','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2b7822ed84e74daead324fa37c7b999f','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('2bd0fa84939345bd8597bb43b68114cb','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('2c766393524e4685bf11bd70f2398505','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2d8293211b974afaa6086a1c02f110b5','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2dde33c32169473e958a479f8ff77d66','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2e301c3097154075834105679d64226d','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('2e928584b5084b5a8e2a77094e9e057a','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('2f2d90b3f27e4bea964537427cebcf6f','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2f7a8969eec24397bf05cf97f7646e12','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('2f7d5c87bcd548b6a2632458c8801466','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('30a7fcaa03754e1bbfd1100c1b048afb','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('311c322cd173456b89f6cac556612353','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('314910b106094f348f6715b173c3e1fa','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('315cb043cba749e9b12585f2bf0aaae4','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('32fbf11ef5354c84ae0a8ae7a7ad9741','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('33401d19d1c440ed935bb50da2299d5b','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('345d600965b4427e9876467af3ef607c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('34f68f1c2c7a4c24a38d9a4dd11e4432','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('351b4b7362f6488e99969f979f8631c2','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3526390e42584052a186fcba2a3907a8','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3579fc091d704fd897d7f82fb0ba8f5f','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('35dc20c9937c4fca816132b2bf7a5a05','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('36b648d01481466dbe716a4e5c1a2846','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('36cf8425bb1d409fb1021cbc7c4c1a35','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('36d3c676835e40ef824d3c389d4039f3','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('36f9c5f910ae4be4958bd043d773958d','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('377309587d884abf87bb92f48172e7b6','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('377a4a53460c4edcbf2977fecee54929','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('37c25014a7ad43a6b53af4655ec5281b','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('38ae80d26f4d4b24b9e7c140224d5815','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3906e8331b0345e6b97fcfff6ebe575a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('3a0969559ddb4a20a926fec984adea99','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('3b305f580aa945f0bc632a02308ae9be','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('3b52948377044caa92db56e036477b9a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('3b671a0bc45149a1af027251fe690481','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('3c0c598ae7af4fd7a5a02f38fe3d4116','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3c3a2be0ba2d46729f36a4d564194a9d','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3d33310d8f244d669295fc9fc0d173d1','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3d6b948b054e49a5ae81182bc742d4f3','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('3de86fc8a935435485848f23e060abe0','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3e16daea00094bfa806db6e88cff12cb','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3e21cc300a974be8a3ae4797391a639c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('3e857c90ba2b49fc86d8e3d42302759c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('3ef81f4f4c624065a425cdb848981469','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4048332b3fe642e2a30a7b9b7de65b6f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('4063d110b5314daaa90578fc5f2137f8','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('40a250ee5f364c3b8cec60cfa10e90be','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('40e54911032842b9aeb2bdaee3e9be11','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('415339d1f7fe47c1be17659c04be0013','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('41c9fe16bcf34abc86ab562c376c9836','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('4218060bf2e54ab6be50de9d6b7cab16','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4236903660314ffebcdd2f97c98413ff','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('42786aaddfa541aa89a664ffc7272efc','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4284bd3d0de749549db5cd7aa0733d4b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('431496d4c3ea4c8080dd5b1b2f13c0af','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('44a317f8001a47018256cda99550ed0f','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('44f0b83fbf7d42279a1b354ccb536253','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('45498bbc3ca34e359773efcd9b7f4def','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('45567f29c2e14635ade0046cd698a2b9','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('45888442e02f4c10b8e213a3fbd2f914','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('461bff448d4547e9bb772dd7b9c4b72d','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('467f335140e84fb698f71e3c0e7f098f','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('4689bdf21289436ba5682d67ba474a95','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('47057489763f498493605264691580b9','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('477c7616afe743ab9da797265b07248b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('47b7c19fc67847cdb29bbdab2df4eab1','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('483d7d40edc14dff85bd32d36fca37be','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('488b8865434b4d5cb477d9eab89fec35','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('4903bf8a3c724896bc7634c8f2afcea2','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4a63e09354fc4ffd8f623c32e73bed37','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4a6b70048d5a4ab3b2f4a3f2b276bd28','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4af0bf8611644d31a7e007dbe4bacabc','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('4bd2b22d5c554d24a2158e34648b7aa5','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4c040cab99244441b36bf3f9b596fad8','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4c08884bf3534c5aa0fd53ed3080dbe2','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4c320fd147064f4c9638e354c2880a21','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('4c5fa3e67041430ab2a143508c456700','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4c9a5d0ab4284fcb8ba33a698cc4762c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4cee2e5ff0194d01bc241a719a276436','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4d0ae35ce9a84961a10f30bd6fca833d','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('4d23884cf57b45999de39bbb7c0bea89','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('4f240ff9e24243bdb9dc7ed920bbbc1b','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('4f50c49e688c4ffd82e2e264ef81571e','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('501b4cd8091c4bbea71af389d73d6fbf','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5073e82137cf48059d16bb7b8a7cc1cd','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('50efd07adc954993bf0f57a5a5c10b0b','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('51467964c8ba493faf33da77a591c336','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5235b3b0982f41089f697e89c27f132e','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('5267e3dd885e4b32a81ab4e530c5cf5c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('542c7e6360e048b09053e0218bb4cd25','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5463f5321acc4ff089190693443fcad7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5497b79b7bf3436abf25ddfd0a4b7476','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('54d48c6683184ddf8b062a207865bdaa','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('54e4f2b100fd4da6b1fb94af7e192719','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('54f3028f75c844bd99ef6caacffb5d5a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('557fe7fb15a3438d8a79a6d465c975ea','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('55b24bb17eea4293947701c7f892396d','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5617aabe20e645f8968fe454f2397400','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('56aeb124cfef41e28467bca46e414971','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('56e608ae88684f97a55b7f053c512581','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5710dad73c3849568c6be95473e079b2','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('571d992b08144a5b965c9fd6addaec49','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('57a35139645a4570ace03af0fc9b256c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('57c509dfda4047939b04305ad6d39e20','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5810adf8b842491d91d96f28c5c969c6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('58322c5b45c24721858e85a8cbf8d14f','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5859b77e9e7645cbbc4595bf969ad00a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5878fe7f887e4472a10df17f7e331030','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('591378628aea4a3ca0a1fe6ee679a37f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5983ccff09154802992d9bed4aac8ebe','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5a6703895e664c9f9ffad73f3d8ba6dd','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5ba8569bb88142b19da00c0b1d4b6925','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5c93821e46c24c62a80726ac0baf57b0','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5d20e34c8f6b4bd794312e9492ce2b42','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('5d232b220a2c446da59664606e0c4607','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5d861885030245ef9a96c52dbf3908c4','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5df23a769a0a4cdfa06f48a449615754','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5e420a434c534b6dac32273b5419d583','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5ed06009950e4ed28b5524e8f1b720bd','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5ed8e5c38fb847e9b0c3474bcb33a2c1','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('5facf9d1402b477d9d0016bf717b532a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5faf75e981f94d5e82198390d914ece7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('5fd841a4a89a4f899b031d4926f779be','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('60362c4fa21047169ab95567253712ce','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('60977e6342f348e8af6942790a09f1ca','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('60f7c157e41e40628986973e61771ef8','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('60ff32898f164478be4b0b747d0a92bd','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('6115b346826c490e90041e22aeaf20cd','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('6277f1f60a77498f829f481f5bbbcd8b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('634f4eba776144a886cc190839165b22','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('635cde9ad51942448a262624c7b6f36a','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('63a5bccb8de146fb9f59a20610bb959f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('64c68c0753ba41bda021ceb0812b905d','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('65371c13d12a43da8ddf26e70ea7a9d3','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('65607b8fbce64013b0012fe61a92fa13','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('66cc74f0201649629c78e694d0399740','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('67ad468961274702bccf60670cc7f8f7','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('67cf6d448db048e8840d91666903071d','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('67f824b236814b39ac361b02a2947940','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('68bb0f71f48545d5a7ec86f6947d833e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('6cbc8b71e9eb40618c5347be0aa030c5','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('6ceae4060c9349f8a235fe156e7a06b4','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('6d0a6eca3731466c8e190bb626814d31','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('6d7e883a6179484093ba607811969877','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('6de712fb9ef146fea2522065ee21e64f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('6e4841beb169495abe2be47513919b75','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('6e617aa9af924e27a7780f25070882f2','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('6ebbb24d21d547829da76cca045ed71c','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('6ebd1f1014dc4cc9b25529f738026bd3','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('6f1b7b1504e945c6a6a62ae513c3b15c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('6fc3d257701d464db0bf16476aaecc3b','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('70081ebccc374f65b3046b3feba85670','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('7073a14919bd4135a811b3a7cfdfa00c','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('71693dd618bc40fe99401ac971b8223e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('71f8513600ec46cdaac9fb9bda073814','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('729162707fb04e69a97da9a47c4a8dd1','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('72e40ee66de147568e7d39d275ce0560','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('736110e8437946e78384208500363484','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('74496f9e7a3149b496c66a6e8c2f2a4b','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('74f0976d2004420286d1db9e06b4ea16','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('7524307f97214d80bb19c9e07ae7b139','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('7534dc88da424a08ae89d4ff84752dc6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('753c2e371a5f4448909d0a0ff06801fe','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('754017de9f25437ca12ac5e5e7966044','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('75a1e0e84a9b41aeac421dbfec7c24f3','5467897',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:57:06',NULL,0,0,0,NULL),('761bc42390764a54b34358affdb97bd6','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('7751c1a609af4b99b2d00fc831e6b598','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('77a79097bd1b4c09867b420ca240af4f','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('77aef29c01554e67ade156c0320e25af','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('781e636a192a4219b88f5ce168e74888','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('78557d41b1274cdeb61f66b7e632f790','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('78bc5244080449efac69baa8e8b9f28d','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('7ab578a470434d209344b34abc810be8','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('7b45d6a12297401da1d236ddb6569d05','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('7b84a1b9383f47a68b833346ea4327dc','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('7bbb126e7fe046228e3fb1a508a63673','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('7c33b54d1d5544ddb6aa78dad5f113e0','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('7cdfb1875e2f4565882d1c693df59e05','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('7d3d364eaec847109fa0c556894220b3','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('7d3ea67ed5704f32b74494d75e5cf6dd','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('7def8531e2894bee895de9810c6c2f93','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('7df16973b3084c98b0e3d1d1a3ff4955','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('7e14ab30f8504f17a9efe3572257f67b','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('7e63e9369fc740f3ba2bb4e79f8bc08d','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('7ec7045e5a9748e4bc93cfd87c4a5fab','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('7ff932d8b9b8426bbf2be0024993290d','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('803a0e8a46b246ebabc149c1f3097eb6','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('805cc32e478f44f8adc5f172427965a3','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8061b5664b3548e283bf8efd3337d88b','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('80afffeb28b54524bedde429dea563fe','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('80b274485e7b426da48c4edf24046bf6','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('80b3f3bebad04886ac47c83806ddb932','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('80c2aee6e7af4c53add288ae8cbc9bfe','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('80cb4879ae8c4b99ae7e665418847202','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('80fb2d0224334980a6d837c6486b5b46','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('813dd315b3b04e39bc4a89eabc3d6c1d','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8239c360d8f74e1dbad1668d2d181f6c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('824c82f4d6e24fc6b1d108805dbff219','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('827aac1bb43e49b59381f4119a92e26b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('83203f8c6d2043a5bff799b3ed6c4f7e','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('8393ba886e774e2db80becb50e625e2b','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('83ea4e7c48ef461e9a2e387b29c677b5','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('840f9dc6f66b432c9ad8ebc6f29560a7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('842d7319b2e646f8be3c4c31328ad903','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('859076d29fea4eda82094941a7da69c5','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('86c329a50266407bb8d3142414e6684c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('86c5633f7f934558bf90ca6e38dfd87c','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('87032da54ec446adb4715968222cec18','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('884e1dcab6b8480384c78d01174260c6','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('88c6b51b379c42f68c22baf196e6ec59','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('88ef69a2d966430bbe6c76d2b3e53547','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('8921f8c8a98c46b7b4a10e995bdbc508','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('894d9fa4d79a44d985bb0ae959630cbe','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('89b1ccbade4544d49cd988278f673e4e','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('89ba2aa7d4d6432ab2377559ea0001d1','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('8a195d4bc1a648b691e605320b51e32c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8a6c3f42720b4ed4aa82c3976df87fd1','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8b10033e352a4e86a4add5ff8850cbda','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8bca612ce5f340b8a4b95c61660d4e4f','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('8bd204d1376c4be6a40fbcbad0ac258a','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8c8423fbf38544909da178f1ad475af0','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8c951efe896c4a4da0b7ad8b6d983e91','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8caf83963c604b84ab7884aa091211cd','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8ce86705735f4945ad600cb85c428ac8','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8da456fd07724fab9c1dcd8b437a611c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8e61187b72674f019a97568ebfe5cfa5','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8ee3c0c01083499dbe503a4688c77f01','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8fd0262f513b41a19631ced787ea8665','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('8fd7b611f7c148b7b621da64cce23c77','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9057fe649111430a907c4db38ec08f82','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('906fd0bee8254cd8b95453fbe80e7b67','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('90b07552705e407e8a9f10d0fd575a6b','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('90dbb50643fc4d82b879e5c2b0f3a890','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('913d2653a4e1429e8e7e0a56c9be306f','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('915505bd08cc466c90c987ca799686d7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('91c6b37e23994614a4072ab417f00857','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('91ff5e5788cb459e951f130c93da2e3e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('92462602690e4728a87d81fb86650edd','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9253025b40004c1c9aa2291738927720','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('936f47d9602541339b975b1a57f297cd','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('943e7f81f869485e8295d482024a63fe','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('945c3d106433448596843ff8dbc85848','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('94b80f9635a64c3095da5640be9d9062','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('952c73a975734933bfff78908522d805','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('9534d2d5d34a4a1bb37ba4f4f8a9857c','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('955e4d775318431a84b03a755810be0e','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('95f65ad2f1324b30838345ee4680c84c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('966bc1ee60074ac3a3d4423b09be847e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('97296cc56e704b46ac2e673d01ddfe71','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('975726c3bdaa422e9b93c57ac2a132d0','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('97b4062cad764d85b75fd39d96951be5','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('98c52b8f35714b32949e702d96445110','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('9924f453071749fc998c67b4b6108893','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9952d897ff444ef0b26e80333d5ffebf','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('99944177c7064267983524eae237307d','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9b22f6a7da00486aa2287c5d66623700','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9b635a289d814b42bbf1ae803488f4e6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('9bac1a8152524f3db15b1579cda79bf1','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9c1be034ff6443b391af8b307e9cf9b5','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9c46c68eb9ed459982d8d3709f082552','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('9c4744a9344442b093696069deaf89ba','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('9c8115140a2d4634a79d2564440dc8ae','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('9cd17ea596d646f78641100776e6718b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('9cd9b3d43a85405aa4332ebcd6b10526','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9cec93feb66e4863908cc6d85448bdf6','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('9db32f6c78a744b6b09586f469638a3b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('9e13eec1ef7248d59a9bd3bf85fe094c','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('9e7b1b316cad4ad8ab62698ff96686a7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('9e7efa8f55bc48e0bf94e1a2ed5a9b50','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('9fbc09c07ebc41fb958b6a8236d16173','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a054e226793142dba10f38c54d289a6a','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a0d425ec3fe74c868c3660da1b20aad9','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a18aea157e2a47e59f5c1005f44638a2','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a1ede099840242d28c11f2e510809067','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a2ec6195e8dd4443aae3bde66cf37e86','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('a3145c0f57b142ca8939227984370329','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a35d6a3fe8ef4bb691e6d91578db8ad2','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a3653f92869d45429e146d296f80cefc','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a3a773bc3bf9421495a772a080b922c4','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('a3cd67f9fd41483b8103d77438b4347f','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a517b4b05e1f47b6b8178788479121dc','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('a53b0caffd3a4333bb81a73e3913db23','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a56b72cd5ac64bdbb11bce9a73f7e55e','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a605b9e9e52f43398d1801d676d09952','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a637fc1b806545a6a5a46f8c86642b4e','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a6450ca18aa44736b7b24a7e32d5138a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a6500c6119c24a3d98a23d029534b6ba','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a71ed6272cf7455e93a82ed0b0318b23','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a7a0b1ea17d443e081d582be3d9acff9','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a7bf47b0ebf74b02a8fe597db1b92d46','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a87a0d3a8ded4fc48cdab66b6b37f634','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a8a54bdde79748b4b958425bfdde9a1e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('a90816ad551e432f80805dfb94fd56cf','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a9e38e814dc843ad85cf18b2402cc4d3','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('a9f81a569018427697648b7f4aeea744','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('aa39c7f118a243569ef9c48ab98ac6a7','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ab074f1059684fe894e676c27ba60773','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ab27c829c1104425912740a0f63c69aa','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('ac588ff1d0c6446b81268fe5379a8108','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('acd23af102654fb796a591c8080c807c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('acfa7ab2588d4c4295509a1fdcebf238','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ad0051a06b0a46a1a05087b0104ffc89','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('adad605e2f5e4834864ff658adb20039','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('ae2e98a6ac234e0dacb5217344d3207a','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ae84482d8238458da75b36fde8f17011','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('aead766fb4d6432e8d94ce1e69f5c349','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('af6d970c87da494fa9599d7819f29073','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('afd12713cdb4418190c95ddde2da444a','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('b0833205eaed43e9a499ca57bcfa1e9c','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b0d9c7af0a5e496f9c5d824537e1d111','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('b0e9006e5a68434a82d3665eb76f6a91','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('b225d39452a148628de4654342ea0337','5467897',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:57:06',NULL,0,0,0,NULL),('b2547832cbfc42fd95160cb06764356f','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b40e2ca84aea45d286c88f9a6414f8c9','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b43761ae36f34ee0b5b1e967f52868f3','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('b5429e28bb7d4d69affa1df92e7f97c5','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b5cb2d7599c248e99ac5ab81f412ad0c','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b780775d12a943b8b2f41d8336b6c921','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('b784954263e24d2487aff8ac4f9b3742','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('b7a7eb32962b483f8f7619c17c0fcb4a','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b7d741335ff14cb59e3c1f7e5b5ec724','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b7e15ee2e9d34b809cf1dcbfeded5a61','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b83ea6a7ce46448b904ab6af49773727','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('b88df9394c2e4ee4976dc8a1cc8d0dde','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('b98917c0b153438c8de8dfeaf7221d4c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('b98bb29ba0864b4ab0533afe27345e3d','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('b9e2dd34f672418bbdce48a7e012369c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('ba58171b945442e086da01697bfe7f08','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('baa98a64d56d4007b20c3fa2cdc0a5d4','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('baad7714dccd4bb99eadfb3a9e36eec3','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('bb0f150eb76b4701bfe520495fa01992','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('bb327fd857914e35af4b1df1f5b3e399','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('bc090aad61c144899bff424bc8a26ae7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('bcf235974e484dcc9492b14a440b9b10','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('bcf86573455847c49b766384c662a859','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('beff9aa7d2114d45be30262f69cde73f','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('bf10b68be116401f92285f0b21abdb43','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('bf6e1be0e83f4768ad1537fce86df4bc','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c0bbc5ebd51f4efb9ee7ca0961e9e3c0','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('c0c58614af084693aa9efe25fb03f195','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c2a307b39fdd4f8f8b16aa64c2bdc6a6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c3af5a4b4fef42a6adee7398798ad025','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c45f5a375be5411aaf0f022c85edadbc','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c4afa76652bf4f2f9ac794989018fbd7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c510c070318a46a689b3c233755cff23','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c5252b14b71948bbba2c99e7c4698eb3','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c5ab60e8ffa8426aab7e84ccd5b3fcb5','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c5cd21917ed94d47a1ef702be5ad2447','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('c644b6cb07cc4ae0bc47532b8ce8d594','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('c65070b6ca974698adc0d0b18c26e2ac','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('c6b2b8bbe8694127afc12b881e0cb9ef','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('c6d21ed2152947548cf838ec1eefba9e','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c6f4aff7533443359fc801efefa175d1','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c789aa1296f643588d5555004547a775','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c7a38ce5ccfa4eba904c6f1a68f378e9','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c7d3fe14f0324067b8230f5fa0006fe5','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c7d54aeaac3948cfb946f08fc8f32290','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c815093dcc0e4373b1df458f882d1084','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('c8a3cd2fa5964ea2854b478bd286eed1','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c9c8778b51694fc591ee7a7e7f194b3a','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c9cfe277ea524a4084558e19407d755f','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('c9f00df89e0f4ecf8a1e7b5ed876f2f6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('ca099cb66a274d909092de3ebfc18da8','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('ca6725cbe5d84445af993a96df9e43b7','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('ca71bd2a977342f6bc86f946b1ead983','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('caa909c7025e4ed79e95ddbfd98ef7bb','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('cae63a4d2b124b17a3619bc5c8fb6c43','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('cb9895b1250e48ccb92608c85b54fae9','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('cc05f4a074cc4596941201943b27fbab','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ccd2ad5ea36043c2b98df30421bbc226','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ce3e88a2a5d24f65a348d1f21d5f2683','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('cf0fbb79ac9e4298b9d989ab0f88155f','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('cf110124bcca462f85ab604045f46315','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('cf8ad47ef6884ed2888537bad68af578','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('d08ea79bdc654c62b9db824d6382a6c7','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d123b88f13f644a9966cfe27b27a5d88','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d129510b73f84830ab44969cbd3100b9','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('d15e878fc2ad4044a3c0369f226bdc0f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('d19150e627fd4dfbae5a3e0ea0907db9','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d1ec5ca1c66f49ddbdef90768af6470a','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d234c9248938498ab6c9b0a088b0dbe8','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('d26be90a0b8d45b8b9dc8815eebac496','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d28d33ca324b4412af120dbdbdb66d50','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d2a424c9084d4d579bd7ac16a7b169fe','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('d3892421a954457c8a3678a7f2157df6','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d40d13d54b144331a02d0dd44deccfa9','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('d43e0023418746d397e16764a52110dc','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('d45207ea9da242d387de05d21beb1926','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d4f36d3205ee400593a30a031a355e70','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d55abc87eb774fa4a4a3a263cb0ed0c3','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d569e52b0001408986a34902180af8b7','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d5fee594081f4fe7954d07bc0745544f','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d6b322d52d7848089fe36eda66cee535','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d738f7ef2bb940d78e58bd42e9920e6f','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('d8282c9bf63b4e7eb2b8d9630dd90885','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('d90b6564d45e4cc2bfc4ad24a93d2b42','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('da9e911a387a445a980d1e56b2b8148c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('dbab42c2beb54ed190044b0d81cfba32','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('dbc89d1cf39f43ccaa7e21aa4fa16c8c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('dc0c7351a5324055aae9abb5510803ba','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('ddb1308e1f97481da83af8542818b4df','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('de14e6e2690f4c4eaabae28a1eb5f726','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('df1a534597554d59bb51969a0b7bceae','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('df1acc2958974a2a8a21248996491c66','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('df245ed51a464f25878c503e80e02b72','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('df3db9961e7f44aab8878e153a61cf28','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e018cd04a96c4572aa32cbe3f4df084d','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e035073c74d64d81a1652a7b405c5d03','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e03e50f43a9c4a46b3aecb0d6ddf5122','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e0e0d6003e7d43ddaa0eb3445917ddc0','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e0f5ce279be24b20a37505fbaa71a97e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e1ed70d36d87401b973ab4a8ea9553ad','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e281ba2a946b44f4bc3955eb986ee449','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e2bd48718137405cb27d7983ffa1914a','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e2c907a301fb4b84b008c6d233cf8ff7','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e2e6aeac42a44be5a51aac4ca746dabd','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e34d19e9689e4154b5f0abfa1810c004','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e398a95d58f847988b0c3aadd140b882','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e39f5e136ac34cf3bbe73ad13810f8e8','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e496f9dec92a47d2b4d7d110a101e55a','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e5113add38494543861370a4f2de1026','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e55cdaab19ed4cc6a0e18e9c9681268e','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e5fc480a35814b55a253b736b119a262','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e6474f153b0d4d388d663557098deb6f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e666f0379cb242b283dd66ebdbad4099','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e69c6d45098d466583665ef9410425a6','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('e740269f02a747ffa4f67e56a9cf7066','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e755119c46ab4ba2b53250f245a2b280','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e780755db5b0402ea74050d0863d00b0','5467897',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:57:06',NULL,0,0,0,NULL),('e7e2778ee78f439aa42b95027e3da623','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e80229b2f4f64e578628ef17735b99c0','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e8152489027945feb3fb649083819f1b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e846100685bd406b8e2288096ac321c6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('e8efee255e024dd689417db4399e006d','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('e91c5e8754254b0b85cc7bfec0916b84','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('eab2d52cece74c22824b4e8a493f9c5f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('eac06db188164b17912014d3b2521a77','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('eae0ea4638ec43a8af5f49dd0f31f26e','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('eb9dab7e2595402a953dc8301d9b93a8','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ec0cf439e98b41468c8a30472e178b40','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('ec3a3bb5961c414a8c62522dfa16f4b3','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ec85d476d4464865a7fd92db94278b66','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ecac0ad3e5aa45219362ee1ebe13911b','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('ece0372a044d43c9a6cf158d17759365','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ed0eb0b10c3446f59138987ca7d2dc1d','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('ed68d82797c3446a9b5bed584a3dda14','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ee3ceab5c85c40ef83cd707d65f99f04','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('eeb3a6a30e234b24aa3e541cf0e1d9c9','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ef3decedd90a4d488cde7f87f1a45cdf','123456789',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 18:22:19',NULL,0,0,0,NULL),('ef4089dab29141d7922c6ac9897407a6','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ef6a1ebb0ccf4f3e95bca65dcf2ce85a','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('efcd8307834d4b848474f21431ebf05f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f00da6d88de143ea92febb3fba0c6767','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f03bf5bcc5be42cd9502a2eb44faff98','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f0b8499d213b42c0a60aae4656695014','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('f15f4faf5b40479a96747a053279e4fb','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f17ed57b145540cdbcd5b2bc00002f65','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f187bbfcd59d456f91b7abd4975f2656','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f189f5def1a844cb9d456090a5de0410','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f1fcde79a99447778dc426634b2befe3','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f27830df52d14f858f75abd0d52ab153','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f2f2e82091df45e4ba729d8ac894ef84','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f3aa80033ee84844b85ec645dae6f84f','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f4a3a53fa7d949a4aa58af8817d8ac10','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f4d51afa149a4943934df9cdfaf0ab3c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f55929154bb245e09ec0708de8edfc1b','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f57bd48cb61f4288aa10db08fb04bd8c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f58ef5e1e6064eb886d7a1fa94b8f017','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('f7293dffb92641d184bcef18ac2dfb10','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f7d43bf4fdf74d34927e6c53117e462c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f7e9621f415c4411a4f1fd8ff4d3a631','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f7ea71fea10041c89d4410ac0ec4b3f5','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('f805fdf6fbfa403e94ad7426304f58c0','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f824ddf88804434c9238f7cf8259a258','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('f91f1ee6c7a94e1eafdecc2fbf777ef6','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('f9585bfbd2cf43c490e690c338722caf','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('fa2c0bcf503d4164aa0419efbd31523c','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fa3ae527d2bd494e9fb7588f1a8ebc8b','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fa594e31a840473995ace36f803422ce','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fb2b740e774b41808620ac853c7b6d71','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fb5603c769784ee39114b45e6dadbdee','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fb7743f9751146fd8c2da294e528098e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fba139b5017a45e2996d78c6ea24253c','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('fbea5c1bf838480cb3522ff131ad46ab','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fc2780de1a1c479aa9c8108ee901367e','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fc9cb8fcb8c3401999c4df35a7983003','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('fcad7606fab54f598d3dc00a0e3c4273','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL),('fccc36d85c1b486aad3cc18877fca53f','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fcdd353759204e43a1e0c2c845a360e5','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fd0e6ffc20f043f3bad750dce49478ec','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('fd1ca784c7874f1586e14422c7c45807','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('fd271d78e0d1493fa9f23455267d1cd2','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fd7d11200a7a4268a04bae24002be8a0','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fe286068b05c480c8a4e5a58be13b7a8','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fe7ee59de64444ce9b9d19057877eba2','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('fea0d529e3404fe08a476702e66db133','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('febd617837ea426e9c70304f2d921388','jidai_songjiang',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fed83cfba4e54a2d89151c5d0db19423','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('ff3635a2b4bc414eaa2a3247e1b4e315','jiedai_lvbu',500,0,'d3f2efe1b4b24b4fa32927dd72859911',NULL,'2016-08-31 15:22:49',NULL,0,0,0,NULL),('fff38c068de343c9a315ecf96ed44c05','jidai_songjiang',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:52:20',NULL,0,0,0,NULL),('fffda67c061a4b3b9afcf1300418a7b2','jiedai_lvbu',500,0,'c9db3455fff445f482b9eb48edfd343a',NULL,'2016-09-02 17:56:13',NULL,0,0,0,NULL);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` char(36) NOT NULL COMMENT '日志ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `model_name` varchar(50) DEFAULT NULL COMMENT '操作模块',
  `log_content` mediumtext COMMENT '日志内容',
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
  `remark` mediumtext COLLATE utf8_unicode_ci COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统权限表';

/*Data for the table `sys_power` */

insert  into `sys_power`(`id`,`name`,`url`,`parent_id`,`sort_index`,`option_time`,`icon`,`remark`) values ('006fd11739ad4bff9e2adbf33234aff1','合同','contract!gotolist.do','099b882f3f5749079af62aac20400782',1,'2016-09-19 10:00:08','contract',''),('020511d9903d4e61800cb6accd21b4dd','好友列表','friends!gotolist.do','099b882f3f5749079af62aac20400782',1,'2016-08-23 17:22:08','icon-group_link',''),('06c2e4bc383949b584e7759a1e20f354','数据中心','/','syspowers',2,'2016-08-11 17:28:44','icon-databases','数据中心'),('07a80a029b1548d79ab5ad3544f39750','绑定银行卡','bindcard!gotoBindInfoList.do','099b882f3f5749079af62aac20400782',1,'2016-08-15 14:51:40','bank_card_16',''),('099b882f3f5749079af62aac20400782','业务中心','/','syspowers',1,'2016-07-29 14:45:39','icon-rosette',''),('0f5918b3fd894e3498dfd07b5834afbf','理财券使用记录','memberticketrecord!gotoMemeberTicketRecordList.do','099b882f3f5749079af62aac20400782',1,'2016-08-16 11:31:06','icon-bricks',''),('1e72241d8f5144299542af5cf0d173ac','用户理财券','memberinvestticket!gotoList.do','099b882f3f5749079af62aac20400782',1,'2016-08-18 17:28:40','ticket_add',''),('303bff33608c46478eafe0b2720d949e','用户管理','sysuser!showUser.do','7518b13cf6da4e5580aeaa7decfc71bb',1,'2016-07-29 14:41:58','icon-people','用户管理'),('354ce4e52bbb46559a6fe5ec2a5e9cf7','客户经理','customermanager!openDialog.do','099b882f3f5749079af62aac20400782',4,'2016-08-09 15:00:38','icon-folder_user','客户经理'),('4b7526ebcba3439680afac7c2ea40ea4','消息管理','message!gotoMessageList.do','099b882f3f5749079af62aac20400782',1,'2016-08-18 15:16:26','icon-comments',''),('4bda189386834431a3fbb7360e40d784','资金放款还款记录','billloan!gotoList.do','099b882f3f5749079af62aac20400782',1,'2016-08-24 15:38:33','icon-medal_bronze_3',''),('56a7ae4db858460ab5e0198bbe890806','我的客户','memberinfo!gotoMyMemberInfoList.do','e954e75f79504d6e81e4a3e0392872a9',3,'2016-09-21 14:52:38','icon-group',''),('5cb0b2b482ed43c9bfd831aaf2eb5977','通知','notification!gotoList.do','099b882f3f5749079af62aac20400782',1,'2016-08-19 11:12:33','icon-bell',''),('5cd915c40fa348dd97376b5d1fec9c5a','理财标','scale!openDialog.do','099b882f3f5749079af62aac20400782',8,'2016-08-16 16:23:48','icon-application_side_list',''),('69c4c017afa84aa5b36ea8fed457db8b','理财列表','investlist!openDialog.do','06c2e4bc383949b584e7759a1e20f354',2,'2016-08-15 16:58:25','icon-coins','理财列表'),('7518b13cf6da4e5580aeaa7decfc71bb','系统设置','/','syspowers',100,'2016-07-29 14:41:35','icon-2012080404391','系统用户权限角色的设置'),('76fd35f10c3d47c0aa8db7aa91311384','短信','sms!showSMS.do','099b882f3f5749079af62aac20400782',1,'2016-09-19 17:32:50','icon-user_comment','短信列表'),('8f24607739fe4b04a24618cd0742c585','理财资金记录','billinvest!gotoList.do','099b882f3f5749079af62aac20400782',1,'2016-08-24 11:18:14','icon-medal_gold_2',''),('a007affa6bca4c20adc45e2ca5550bf8','角色管理','sysrole!showRole.do','7518b13cf6da4e5580aeaa7decfc71bb',2,'2016-07-29 14:45:16','icon-status_be_right_back','角色管理'),('a59137b1774f4839b9bb89b40d51cf87','产品列表','investpro!investProList.do','099b882f3f5749079af62aac20400782',2,'2016-07-29 15:48:35','icon-tag_green','产品的列表'),('ab36a5f309464b9d8059f03dfa2c9750','客户管理','memberinfo!gotoMemberInfoList.do','099b882f3f5749079af62aac20400782',1,'2016-08-11 15:37:06','icon-group_gear',''),('ad8fddabfe2a4928bd2e9184e730b14d','会员理财列表','investlist!openDialog.do?isCustomer=1','e954e75f79504d6e81e4a3e0392872a9',1,'2016-09-20 17:37:13','icon-coins',''),('bd0ed272880d4e21b95edf6f318e6fed','会员借款列表','loanlist!openDialog.do?isCustomer=1','e954e75f79504d6e81e4a3e0392872a9',2,'2016-09-20 17:36:56','icon-award_star_bronze_2',''),('d40f992523ed4ff6a70ab2e05b1d4cde','库存对账单','stock!openDialog.do','099b882f3f5749079af62aac20400782',9,'2016-07-26 11:42:06','icon-201208041220',''),('db53f973105145fc91c6dc8ca761b6f5','借款列表','loanlist!openDialog.do','06c2e4bc383949b584e7759a1e20f354',1,'2016-08-11 17:31:08','icon-award_star_bronze_2','借款列表'),('de0b897358bb4e2f8e983093a20a889f','理财券管理','investticket!gotoInvestTicketList.do','099b882f3f5749079af62aac20400782',1,'2016-08-16 14:47:40','ticket',''),('e25ebca75b12481d9fb782b21ef842f0','权限管理','syspower!showPower.do','7518b13cf6da4e5580aeaa7decfc71bb',3,'2016-07-29 14:44:57','icon-user_key','权限管理'),('e2d46155babf4c549fd40f79c1d1db73','产品管理','investpro!investProManage.do','099b882f3f5749079af62aac20400782',2,'2016-07-29 16:37:49','icon-tag_green','产品的增删改查'),('e5a1e29e4c1d4e5daa8c0f7f276cdf8f','广告','ad!gotolist.do','099b882f3f5749079af62aac20400782',1,'2016-09-05 13:55:38','icon-shield_rainbow','广告管理'),('e954e75f79504d6e81e4a3e0392872a9','经理中心','/','syspowers',3,'2016-09-20 15:53:54','icon-client',''),('ec8f4769ac0a4bf9a9978ca15a996671','新闻资讯','news!gotolist.do','099b882f3f5749079af62aac20400782',1,'2016-10-17 11:27:45','icon-award_star_bronze_1',''),('fcf20301d76d41a7bb4d2ba8d876dd43','推荐关系','memberinfo!gotoLevelMebmer.do','099b882f3f5749079af62aac20400782',10,'2016-09-21 10:03:59','icon-20130410120031302_easyicon_net_16',''),('syspowers','系统权限','/','0',1,NULL,'icon-server_chart',NULL);

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

insert  into `sys_role_power`(`role_power_id`,`role_id`,`power_id`) values ('161497c54d2c4a8c8382c3afb4b2d07e','526b3b81287c4b4a897676ef194d04d4','5cb0b2b482ed43c9bfd831aaf2eb5977'),('1663c5bc4da046cf8981ec7ca2e6b7ed','526b3b81287c4b4a897676ef194d04d4','de0b897358bb4e2f8e983093a20a889f'),('1cc38dd06ebc4122a75bc4b8ea19fb02','e4fa3ab5c9304eafb326449012a063a2','bd0ed272880d4e21b95edf6f318e6fed'),('1cda4adac0fb495faa4c18f5c4fa7dab','526b3b81287c4b4a897676ef194d04d4','e25ebca75b12481d9fb782b21ef842f0'),('2241c04785f2470ba69d7cf92aafd7be','526b3b81287c4b4a897676ef194d04d4','06c2e4bc383949b584e7759a1e20f354'),('237a8a565ea74696b7ddbafaf0ca3b71','e4fa3ab5c9304eafb326449012a063a2','ad8fddabfe2a4928bd2e9184e730b14d'),('27ff505170cb4f97a8136e3dd20ec9ef','526b3b81287c4b4a897676ef194d04d4','db53f973105145fc91c6dc8ca761b6f5'),('29815e5f7b4d4a6380a7775f4c25a6e5','526b3b81287c4b4a897676ef194d04d4','020511d9903d4e61800cb6accd21b4dd'),('417f4319ee6640c5b0a0426e4164fb11','526b3b81287c4b4a897676ef194d04d4','07a80a029b1548d79ab5ad3544f39750'),('4f130b10714a4742b77c416c4c0a0448','526b3b81287c4b4a897676ef194d04d4','099b882f3f5749079af62aac20400782'),('50b38b329a7e4f23add0788299c5fc29','526b3b81287c4b4a897676ef194d04d4','69c4c017afa84aa5b36ea8fed457db8b'),('5133bfdf9f834c5487fb8b1eadfc6b30','e4fa3ab5c9304eafb326449012a063a2','e954e75f79504d6e81e4a3e0392872a9'),('545c81e5a4ed4166804d83ea06846eb0','526b3b81287c4b4a897676ef194d04d4','7518b13cf6da4e5580aeaa7decfc71bb'),('73d5fb9b305d480bbf64e8bba980a888','526b3b81287c4b4a897676ef194d04d4','ad8fddabfe2a4928bd2e9184e730b14d'),('76227c61c098422cad162d624e95953e','526b3b81287c4b4a897676ef194d04d4','4bda189386834431a3fbb7360e40d784'),('78aa06bb381a4b72a86ea552b9e4cf31','526b3b81287c4b4a897676ef194d04d4','a59137b1774f4839b9bb89b40d51cf87'),('7c0aac5296bb44c9be28061e8845fe2f','526b3b81287c4b4a897676ef194d04d4','a007affa6bca4c20adc45e2ca5550bf8'),('830b631369ab479f9d3cd2fd0422bb10','526b3b81287c4b4a897676ef194d04d4','76fd35f10c3d47c0aa8db7aa91311384'),('9002aa6f841c494ea78a9eef6975c88e','526b3b81287c4b4a897676ef194d04d4','syspowers'),('92425c1593cc46229f8a0cfe27b1e627','526b3b81287c4b4a897676ef194d04d4','8f24607739fe4b04a24618cd0742c585'),('9d85af0b9919435cba749b41a12a452e','526b3b81287c4b4a897676ef194d04d4','006fd11739ad4bff9e2adbf33234aff1'),('9e3d6e84f0414a0488a18a5ff218e516','526b3b81287c4b4a897676ef194d04d4','e954e75f79504d6e81e4a3e0392872a9'),('a5ed4f6bbcc343bca864618a890d3ade','526b3b81287c4b4a897676ef194d04d4','fcf20301d76d41a7bb4d2ba8d876dd43'),('ae179fa9faa244409080ea227fed95ac','526b3b81287c4b4a897676ef194d04d4','354ce4e52bbb46559a6fe5ec2a5e9cf7'),('b78f5cecd428434b8d352caab8d0515f','526b3b81287c4b4a897676ef194d04d4','ab36a5f309464b9d8059f03dfa2c9750'),('cdc1082e9b47458ab8a004fba8d758f2','526b3b81287c4b4a897676ef194d04d4','bd0ed272880d4e21b95edf6f318e6fed'),('cecdd9d8ea3e489283b60a39be605326','526b3b81287c4b4a897676ef194d04d4','56a7ae4db858460ab5e0198bbe890806'),('d016bc45236d4006b344fa2f7879c170','526b3b81287c4b4a897676ef194d04d4','d40f992523ed4ff6a70ab2e05b1d4cde'),('d23f532b97a3485b998552ef1082d197','526b3b81287c4b4a897676ef194d04d4','5cd915c40fa348dd97376b5d1fec9c5a'),('db4c5ee355364afe8ea5731d90249249','526b3b81287c4b4a897676ef194d04d4','0f5918b3fd894e3498dfd07b5834afbf'),('e5fa8649c4ed4c8ca227c2467ba758b7','526b3b81287c4b4a897676ef194d04d4','e2d46155babf4c549fd40f79c1d1db73'),('e7e07408a7a74602a676499c17f03ee3','e4fa3ab5c9304eafb326449012a063a2','56a7ae4db858460ab5e0198bbe890806'),('e856bbfa0a324073852a3052c0fbb3c6','526b3b81287c4b4a897676ef194d04d4','ec8f4769ac0a4bf9a9978ca15a996671'),('ed44b4bbe88e4d7f99d0953948ddab5c','526b3b81287c4b4a897676ef194d04d4','4b7526ebcba3439680afac7c2ea40ea4'),('f041b844e69f4511bd82cb662cb066b8','526b3b81287c4b4a897676ef194d04d4','e5a1e29e4c1d4e5daa8c0f7f276cdf8f'),('f38dd49f0abe43fea3f170f92052f943','526b3b81287c4b4a897676ef194d04d4','303bff33608c46478eafe0b2720d949e'),('f54782d79b1f4274995271314ab7e557','526b3b81287c4b4a897676ef194d04d4','1e72241d8f5144299542af5cf0d173ac');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '系统用户主键',
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '系统用户登录名',
  `password` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '系统用户登录密码',
  `role_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属角色',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` mediumtext COLLATE utf8_unicode_ci COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_ROLE` (`role_id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`name`,`password`,`role_id`,`create_time`,`update_time`,`remark`) values ('0','admin','c8qFgP5zhUU=','526b3b81287c4b4a897676ef194d04d4','2016-07-29 13:23:07','2016-07-29 13:25:11',''),('1212121212','liyonghui','21232f297a57a5a743894a0e4a801fc3','398f5389af8f49749977cf21dc846f2e','2016-08-08 11:47:38','2016-08-08 11:47:41',NULL),('4a04dc52ec9d4f449caddee28f0071d4','admins','c8qFgP5zhUU=',NULL,'2016-08-10 09:59:27',NULL,'客户经理账号'),('56f6f851a3fa452ca6364024c169d96b','wangsicong','Pydeq/rW/6m+QPoQ4LdzDA==',NULL,'2016-09-21 14:43:30',NULL,'客户经理账号'),('5cc6c63d1069403b8ab97cd1876724ef','ls','73882ab1fa529d7273da0db6b49cc4f3','398f5389af8f49749977cf21dc846f2e','2016-07-29 13:23:07','2016-07-29 13:23:07',''),('65afb5b2d9344855a546a4e318adf537','wangjianlin','zmQIc2Ydh6dvPkynWhuIxVJct75kX7H+',NULL,'2016-09-21 14:54:39',NULL,'客户经理账号'),('97d57f49db95473ba1f5127deffdccd0','zs','f63f4fbc9f8c85d409f2f59f2b9e12d5','e4fa3ab5c9304eafb326449012a063a2','2016-07-29 12:56:19','2016-07-29 13:22:43',''),('c883c60c396f4fa385cf66d93071facc','fds','8e57a0a08acf3285aae9566dc3d1d0db',NULL,'2016-08-10 15:19:37',NULL,'客户经理账号'),('e732795b6a1e490cafb700ba029943a3','wangmazi','c8qFgP5zhUU=','e4fa3ab5c9304eafb326449012a063a2','2016-07-29 12:56:19','2016-07-29 13:22:43',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
