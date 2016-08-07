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
  PRIMARY KEY (`id`),
  KEY `FK_apply_loan_car` (`loan_list_id`),
  CONSTRAINT `FK_apply_loan_car` FOREIGN KEY (`loan_list_id`) REFERENCES `loan_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车产贷款信息';

/*Data for the table `apply_loan_car` */

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

/*Table structure for table `apply_loan_info` */

DROP TABLE IF EXISTS `apply_loan_info`;

CREATE TABLE `apply_loan_info` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `loan_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '贷款列表表外键',
  `money` double DEFAULT NULL COMMENT '贷款金额',
  `time_limit` double DEFAULT NULL COMMENT '贷款期限(单位月)',
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

/*Table structure for table `apply_loan_result` */

DROP TABLE IF EXISTS `apply_loan_result`;

CREATE TABLE `apply_loan_result` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `loan_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款信息表id',
  `state` int(11) DEFAULT NULL COMMENT '申请状态,1待审核,2审核通过,3审核驳回',
  `content` mediumtext COLLATE utf8_unicode_ci COMMENT '审核内容',
  `opt_time` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`),
  KEY `FK_loan_apply_load_member` (`loan_list_id`),
  CONSTRAINT `FK_loan_apply_load_list` FOREIGN KEY (`loan_list_id`) REFERENCES `loan_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='借款审核结果';

/*Data for the table `apply_loan_result` */

/*Table structure for table `bill_invest` */

DROP TABLE IF EXISTS `bill_invest`;

CREATE TABLE `bill_invest` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `invest_member_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'invest_member表外键',
  `invest_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财记录，只有消费、手续费、赎回、收益、手续费的时候用到这个字段',
  `card` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡，只有充值和提现使用到这个字段',
  `use_type` int(11) DEFAULT NULL COMMENT '资金类型，1充值，2提现，3消费（购买理财产品），4手续费，5赎回（仅本金），6收益，7投资手续费',
  `money` double DEFAULT NULL COMMENT '变动金额',
  `balance` double DEFAULT NULL COMMENT '变动后账户余额',
  `asset` double DEFAULT NULL COMMENT '变动后总资产',
  `status` int(11) DEFAULT NULL COMMENT '状态，1进行中，2成功，3失败',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作说明',
  `style` int(11) DEFAULT NULL COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  PRIMARY KEY (`id`),
  KEY `FK_bill_invest_member` (`invest_member_id`),
  KEY `FK_bill_invest` (`card`),
  KEY `FK_bill_invest_list` (`invest_list_id`),
  CONSTRAINT `FK_bill_invest` FOREIGN KEY (`card`) REFERENCES `bind_card` (`id`),
  CONSTRAINT `FK_bill_invest_list` FOREIGN KEY (`invest_list_id`) REFERENCES `invest_list` (`id`),
  CONSTRAINT `FK_bill_invest_member` FOREIGN KEY (`invest_member_id`) REFERENCES `invest_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财资金记录';

/*Data for the table `bill_invest` */

/*Table structure for table `bill_loan` */

DROP TABLE IF EXISTS `bill_loan`;

CREATE TABLE `bill_loan` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `loan_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '放款还款对象---借款表loan_list外键',
  `status` int(11) DEFAULT NULL COMMENT '状态，1放款，2还款',
  `card` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡，还款所使用的银行卡',
  `money` double DEFAULT NULL COMMENT '金额，放款+，还款-',
  `done_money` double DEFAULT NULL COMMENT '距离完成还款还需要还款金额',
  `bill_status` int(11) DEFAULT NULL COMMENT '状态，1操作中，2成功，3失败',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作说明',
  `style` int(11) DEFAULT NULL COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  PRIMARY KEY (`id`),
  KEY `FK_bill_loan_liar` (`loan_list_id`),
  KEY `FK_bill_loan` (`card`),
  CONSTRAINT `FK_bill_loan` FOREIGN KEY (`card`) REFERENCES `bind_card` (`id`),
  CONSTRAINT `FK_bill_loan_liar` FOREIGN KEY (`loan_list_id`) REFERENCES `loan_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='投资者资金放款还款记录';

/*Data for the table `bill_loan` */

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
  PRIMARY KEY (`id`),
  KEY `FK_custome_sys_user` (`user_id`),
  CONSTRAINT `FK_custome_sys_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='客户经理表';

/*Data for the table `customer_manager` */

/*Table structure for table `friends` */

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `self` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'member_info_id外键',
  `target` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'member_info_id外键',
  `together` int(11) DEFAULT '1' COMMENT '相互关注，1非,2是',
  `opt_time` datetime DEFAULT NULL COMMENT '关注时间',
  KEY `FK_friends_self` (`self`),
  KEY `FK_friends_target` (`target`),
  CONSTRAINT `FK_friends_target` FOREIGN KEY (`target`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_friends_self` FOREIGN KEY (`self`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='好友表';

/*Data for the table `friends` */

/*Table structure for table `invest_list` */

DROP TABLE IF EXISTS `invest_list`;

CREATE TABLE `invest_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `invest_member_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '投资用户id',
  `money` double DEFAULT '0' COMMENT '投资总金额',
  `yet_money` double DEFAULT '0' COMMENT '投资中金额',
  `space_money` double DEFAULT '0' COMMENT '剩余未投金额',
  `back_income` double DEFAULT NULL COMMENT '赎回收益',
  `back_money` double DEFAULT NULL COMMENT '赎回(本金)',
  `use_ticket` int(11) DEFAULT '0' COMMENT '是否使用了理财券，1没有，2使用了',
  `invest_ticket_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '如果使用了理财券，理财券的id',
  `scale_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财标id',
  `expect_income` double DEFAULT NULL COMMENT '理财标期收益（不含理财券奖励）',
  `total_money` double DEFAULT '0' COMMENT '账面总金额(投资金额+收益+理财券)',
  `income` double DEFAULT NULL COMMENT '实际收益（不含理财券奖励）',
  `ticket_bonus` double DEFAULT NULL COMMENT '实际理财奖励发放',
  `status` int(11) DEFAULT '0' COMMENT '理财状态，1资金匹配中,2投资收益中,3完成投资，4转让中，5转让成功，6到期回款中，7回款成功，8回款失败，9过期',
  `open_date` datetime DEFAULT NULL COMMENT '创建日期',
  `back_date` datetime DEFAULT NULL COMMENT '实际回款日期',
  `pact_number` char(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同编号',
  `invest_style` int(11) DEFAULT NULL COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  `end_pay_time` datetime DEFAULT NULL COMMENT '最后支付时间',
  PRIMARY KEY (`id`),
  KEY `FK_invest_list_scales` (`scale_id`),
  KEY `FK_invest_list_ticket` (`invest_ticket_id`),
  KEY `FK_invest_list_member` (`invest_member_id`),
  CONSTRAINT `FK_invest_list_member` FOREIGN KEY (`invest_member_id`) REFERENCES `invest_member` (`id`),
  CONSTRAINT `FK_invest_list_scales` FOREIGN KEY (`scale_id`) REFERENCES `scale` (`id`),
  CONSTRAINT `FK_invest_list_ticket` FOREIGN KEY (`invest_ticket_id`) REFERENCES `invest_ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='投资记录表';

/*Data for the table `invest_list` */

/*Table structure for table `invest_member` */

DROP TABLE IF EXISTS `invest_member`;

CREATE TABLE `invest_member` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `memberinfo_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `cust_manager_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户经理外键',
  `manager_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户经理姓名',
  `is_contract` int(11) DEFAULT '0' COMMENT '契约用户,0不是，1是',
  `balance` double DEFAULT NULL COMMENT '余额',
  `investing` double DEFAULT NULL COMMENT '投资中金额',
  `total_income` double DEFAULT NULL COMMENT '总收益',
  `total_money` double DEFAULT NULL COMMENT '总资产（余额+投资中金额）',
  `useable_score` int(11) DEFAULT '0' COMMENT '可用积分',
  `register_style` int(11) DEFAULT NULL COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  PRIMARY KEY (`id`),
  KEY `member_info_invest_id` (`memberinfo_id`),
  KEY `FK_invest_member_cust` (`cust_manager_id`),
  CONSTRAINT `FK_invest_member_cust` FOREIGN KEY (`cust_manager_id`) REFERENCES `customer_manager` (`id`),
  CONSTRAINT `member_info_invest_id` FOREIGN KEY (`memberinfo_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财用户列表';

/*Data for the table `invest_member` */

/*Table structure for table `invest_ticket` */

DROP TABLE IF EXISTS `invest_ticket`;

CREATE TABLE `invest_ticket` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券名字',
  `remark` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券说明',
  `describe` mediumtext COLLATE utf8_unicode_ci COMMENT '理财券详细描述',
  `money` double DEFAULT NULL COMMENT '价值',
  `begin_time` datetime DEFAULT NULL COMMENT '有效期始',
  `end_time` datetime DEFAULT NULL COMMENT '有效期至',
  `create_time` datetime DEFAULT NULL COMMENT '派发日期',
  `min_money` double DEFAULT NULL COMMENT '使用最小的投资金额权限',
  `product_ids` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '可使用产品，多个用'';''隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财券';

/*Data for the table `invest_ticket` */

/*Table structure for table `loan_list` */

DROP TABLE IF EXISTS `loan_list`;

CREATE TABLE `loan_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `loan_member_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '借款用户外键',
  `pact_number` char(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同编号',
  `is_sell` int(11) DEFAULT NULL COMMENT '是否起售,1表示起标,2表示起售,3表示停售',
  `poundage_state` int(11) DEFAULT NULL COMMENT '手续费状态,1未扣手续费,2待扣手续费,3已扣手续费',
  `money` double DEFAULT NULL COMMENT '申请借款金额',
  `real_money` double DEFAULT NULL COMMENT '实际审核金额',
  `manage_cost` double DEFAULT NULL COMMENT '平台管理费',
  `poundage` double DEFAULT NULL COMMENT '手续费',
  `get_money` double DEFAULT NULL COMMENT '应该放款金额',
  `yet_money` double DEFAULT NULL COMMENT '已经放款金额',
  `return_money` double DEFAULT NULL COMMENT '实际应该还款金额',
  `yet_return_money` double DEFAULT NULL COMMENT '实际已经还款金额',
  `return_status` int(11) DEFAULT NULL COMMENT '还款状态，1未还款，2未还尽，3完成还款',
  `loan_state` int(11) DEFAULT NULL COMMENT '放款状态,1未开始放款2放款中3放款完成',
  `apply_state` int(11) DEFAULT NULL COMMENT '申请状态,1待审核,2审核通过,3审核驳回',
  `loan_type` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款类型,loan_type表外键',
  `loan_use` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款用途',
  `loan_interest` double DEFAULT NULL COMMENT '借款利息',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `sign_date` datetime DEFAULT NULL COMMENT '签约日',
  `begin_return_date` datetime DEFAULT NULL COMMENT '开始还款日',
  `end_return_date` datetime DEFAULT NULL COMMENT '截止还款日',
  `done_return_date` datetime DEFAULT NULL COMMENT '实际完成还款日',
  `loan_style` int(11) DEFAULT '0' COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  PRIMARY KEY (`id`),
  KEY `FK_loan_list_load_member` (`loan_member_id`),
  KEY `FK_loan_lis_type` (`loan_type`),
  CONSTRAINT `FK_loan_list_load_member` FOREIGN KEY (`loan_member_id`) REFERENCES `loan_member` (`id`),
  CONSTRAINT `FK_loan_lis_type` FOREIGN KEY (`loan_type`) REFERENCES `loan_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='借款列表';

/*Data for the table `loan_list` */

/*Table structure for table `loan_member` */

DROP TABLE IF EXISTS `loan_member`;

CREATE TABLE `loan_member` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT 'member_info外键',
  `customer_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户经理外键',
  `lend_money` double DEFAULT NULL COMMENT '总借款',
  `back_money` double DEFAULT NULL COMMENT '总还款',
  `residue_money` double DEFAULT NULL COMMENT '剩余应还',
  `expect_money` double DEFAULT NULL COMMENT '总逾期',
  PRIMARY KEY (`id`),
  KEY `FK_loan_member_info_id` (`member_info_id`),
  KEY `FK_loan_member_id` (`customer_id`),
  CONSTRAINT `FK_loan_member_id` FOREIGN KEY (`customer_id`) REFERENCES `customer_manager` (`id`),
  CONSTRAINT `FK_loan_member_info_id` FOREIGN KEY (`member_info_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='借款用户列表';

/*Data for the table `loan_member` */

/*Table structure for table `loan_type` */

DROP TABLE IF EXISTS `loan_type`;

CREATE TABLE `loan_type` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '贷款类型表id',
  `name` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `remark` mediumtext COLLATE utf8_unicode_ci COMMENT '描述',
  `module` int(11) DEFAULT '1' COMMENT '借款模式，1普通模式,2产权模式,3信用模式,4急速模式',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='贷款类型表';

/*Data for the table `loan_type` */

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
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
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
  `type` char(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户类型',
  `level` char(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '级别',
  `price` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '财力值',
  `password` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `pay_password` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付密码',
  `hand_password` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手势密码',
  `is_freeze` int(11) DEFAULT '0' COMMENT '是否冻结 0解冻 1冻结',
  `my_qr` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '我的二维码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='基本用户表 ';

/*Data for the table `member_info` */

insert  into `member_info`(`id`,`login_name`,`real_name`,`nickname`,`email`,`age`,`sex`,`phone`,`id_card`,`id_card_img1`,`id_card_img2`,`mi_describe`,`isdelete`,`createTime`,`modifyTime`,`createuser`,`modifyuser`,`user_img`,`is_elite_account`,`type`,`level`,`price`,`password`,`pay_password`,`hand_password`,`is_freeze`,`my_qr`) values ('0712505779b9476e831e1880ccdb9382','asdf1','asdf1','panyalan','213212123@131.com','18','2','21312321',NULL,NULL,NULL,'sdfsd1','0','2016-08-03 16:56:46','2016-08-05 10:00:29','0','0',NULL,0,'0','5','5001','8888',NULL,'0',0,NULL),('123','18601920462','Allen','Lee','18601920462@163.com','26','1','18601920462',NULL,NULL,NULL,NULL,'0','2016-08-02 11:22:04',NULL,NULL,NULL,NULL,0,'',NULL,NULL,NULL,NULL,NULL,0,NULL),('124','18901256863','An','宋江','songjiang@163.com','36','0','18901256863',NULL,NULL,NULL,NULL,'1','2016-08-02 11:22:08',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),('4a5c643f94ef44b4a7f82f52ccae0870','13811294856','吕布','小白脸','lvbu@163.com','19','1','13811294856',NULL,NULL,NULL,'','0','2016-08-05 11:01:32','2016-08-05 11:01:32','0','0',NULL,1,'1','3','100','lvbu',NULL,'0',0,NULL),('b4961a25ab5948abba3d8852c9c6f5a5','asdfasdf','sdf','adf','','','1','87978',NULL,NULL,NULL,'','0','2016-08-04 11:12:16','2016-08-04 11:12:16','0','0',NULL,0,'0','1','','sdf',NULL,NULL,NULL,NULL);

/*Table structure for table `member_invest_ticket` */

DROP TABLE IF EXISTS `member_invest_ticket`;

CREATE TABLE `member_invest_ticket` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `invest_member_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财用户id',
  `invest_ticket_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券id',
  `resource_style` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财券来源',
  `is_use` int(11) DEFAULT NULL COMMENT '是否使用,1未使用,2已使用',
  PRIMARY KEY (`id`),
  KEY `FK_member_invest_ticket_member` (`invest_member_id`),
  KEY `FK_member_invest_ticket_ticket` (`invest_ticket_id`),
  CONSTRAINT `FK_member_invest_ticket_member` FOREIGN KEY (`invest_member_id`) REFERENCES `invest_member` (`id`),
  CONSTRAINT `FK_member_invest_ticket_ticket` FOREIGN KEY (`invest_ticket_id`) REFERENCES `invest_ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='我的理财券';

/*Data for the table `member_invest_ticket` */

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
  PRIMARY KEY (`id`),
  KEY `FK_message_member1` (`receiver`),
  KEY `FK_message_member2` (`sender`),
  CONSTRAINT `FK_message_member2` FOREIGN KEY (`sender`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_message_member1` FOREIGN KEY (`receiver`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='消息';

/*Data for the table `message` */

/*Table structure for table `notificatin` */

DROP TABLE IF EXISTS `notificatin`;

CREATE TABLE `notificatin` (
  `id` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) DEFAULT '2' COMMENT '状态，1启动弹窗，2通告列表内，3都有',
  `publish_time` datetime DEFAULT NULL COMMENT '发布日期',
  `title` varchar(3000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` mediumtext COLLATE utf8_unicode_ci COMMENT '内容',
  `begin_date` datetime DEFAULT NULL COMMENT '有效期始',
  `end_date` datetime DEFAULT NULL COMMENT '有效期至',
  `readed` int(11) DEFAULT '0' COMMENT '是否已读，0未读，1已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='通告';

/*Data for the table `notificatin` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `category` int(11) NOT NULL COMMENT '产品类型，默认1信贷产品，2标类产品',
  `name_zh` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '总名称  如：年年余',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称  6月期，12月期',
  `name_describe` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `yield_describe` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收益率描述',
  `yield` double DEFAULT NULL COMMENT '准确的收益率',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财产品表';

/*Data for the table `product` */

insert  into `product`(`id`,`category`,`name_zh`,`name`,`name_describe`,`yield_describe`,`yield`,`title1`,`title2`,`is_sell`,`is_new_product`,`is_recommend`,`product_describe`,`risk_control`,`details`,`isdelete`,`createtime`,`modifytime`,`createuser`,`modifyuser`) values ('413c9f04152046418b880373ef081f03',0,'56','56','','',56,'','',1,1,1,'','','',1,NULL,NULL,NULL,NULL),('4d11022b613847db92dc4c8a09340ce5',0,'676','67','','',67,'','',1,1,1,'','','',1,NULL,NULL,NULL,NULL),('71d59b80dbea4ce2b9163fa3dae5f9e9',0,'er','we','','',0.20000000298023224,'','',1,1,1,'','','',1,NULL,NULL,NULL,NULL),('7868430bc64f48e299f38f9e22c8305e',0,'af','sdf','','',123,'','',1,1,1,'','','',0,NULL,NULL,NULL,NULL),('d79a0f74392f4505ab1c050d4eb8eb26',0,'sdf1','sdf1','sdf1','asdf1',0.23000000417232513,'sdf1','sd1',1,0,1,'1','sdfsdf1','sdfsdfsdf1',0,NULL,NULL,NULL,NULL);

/*Table structure for table `scale` */

DROP TABLE IF EXISTS `scale`;

CREATE TABLE `scale` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '标id',
  `product_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品id',
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
  `total_money` double DEFAULT NULL COMMENT '标的总金额',
  `residue_money` double DEFAULT NULL COMMENT '剩余可投金额',
  `yet_money` double DEFAULT NULL COMMENT '实际已投入金额',
  `score_bonus` int(11) DEFAULT '1' COMMENT '积分奖励，0没有，1有',
  `ones_score` int(11) DEFAULT NULL COMMENT '每份标（500块）所获得积分',
  `status` int(11) DEFAULT NULL COMMENT '状态，1可投入，2还有机会，3已完成',
  `is_turn` int(11) DEFAULT '0' COMMENT '是否属于转让标，0不是，1是',
  `invest_list` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '转让标的原来的理财记录',
  PRIMARY KEY (`id`),
  KEY `FK_scale_product` (`product_id`),
  KEY `FK_scale` (`invest_list`),
  CONSTRAINT `FK_scale` FOREIGN KEY (`invest_list`) REFERENCES `invest_list` (`id`),
  CONSTRAINT `FK_scale_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='投资标列表表';

/*Data for the table `scale` */

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

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '库存id',
  `loan_member_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '借款用户id',
  `money` double DEFAULT '500' COMMENT '库存金额，默认500',
  `fetch` double DEFAULT NULL COMMENT '取走金额',
  `scale_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用于理财标',
  `fetch_member` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '库存分配对象————理财用户外键',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  `fetch_time` datetime DEFAULT NULL COMMENT '库存分配出去时间',
  `difference` double DEFAULT '0' COMMENT '库存出入差',
  `status` int(11) DEFAULT '0' COMMENT '状态，0仅入库，1分配到理财标',
  PRIMARY KEY (`id`),
  KEY `FK_stock_invest_member` (`fetch_member`),
  KEY `FK_stock_loan_member` (`loan_member_id`),
  KEY `FK_stock_scale` (`scale_id`),
  CONSTRAINT `FK_stock_invest_member` FOREIGN KEY (`fetch_member`) REFERENCES `invest_member` (`id`),
  CONSTRAINT `FK_stock_loan_member` FOREIGN KEY (`loan_member_id`) REFERENCES `loan_member` (`id`),
  CONSTRAINT `FK_stock_scale` FOREIGN KEY (`scale_id`) REFERENCES `scale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='库存';

/*Data for the table `stock` */

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

insert  into `sys_power`(`id`,`name`,`url`,`parent_id`,`sort_index`,`option_time`,`icon`,`remark`) values ('099b882f3f5749079af62aac20400782','业务中心','/','syspowers',1,'2016-07-29 14:45:39','icon-rosette',''),('1553ee3cc9874033a1cf3d07dd6bd791','张三1','1','e8ba0702782b49ef9bffeaba7021329f',1,'2016-07-29 13:17:20','',''),('303bff33608c46478eafe0b2720d949e','用户管理','sysuser!showUser.do','7518b13cf6da4e5580aeaa7decfc71bb',1,'2016-07-29 14:41:58','icon-people','用户管理'),('665cb2abea514a35b1528bc195af4175','李四2','3','d35cf70a67b143bcacc0bfae1b1c94e0',9,'2016-08-01 14:13:51','',''),('7518b13cf6da4e5580aeaa7decfc71bb','系统设置','/','syspowers',100,'2016-07-29 14:41:35','icon-2012080404391','系统用户权限角色的设置'),('924503c693bd4a569fece470c23d1c96','理财用户','investmember!investMemberList.do','099b882f3f5749079af62aac20400782',3,'2016-08-01 10:14:18','icon-group',''),('a007affa6bca4c20adc45e2ca5550bf8','角色管理','sysrole!showRole.do','7518b13cf6da4e5580aeaa7decfc71bb',2,'2016-07-29 14:45:16','icon-status_be_right_back','角色管理'),('a59137b1774f4839b9bb89b40d51cf87','产品列表','investpro!investProList.do','099b882f3f5749079af62aac20400782',2,'2016-07-29 15:48:35','icon-tag_green','产品的列表'),('bcc2df5e881c4af68f4743dbda55ec1f','张三2','3','e8ba0702782b49ef9bffeaba7021329f',3,'2016-07-29 13:17:54','',''),('d35cf70a67b143bcacc0bfae1b1c94e0','李四的权限','321','syspowers',5,'2016-07-29 14:40:54','',''),('e25ebca75b12481d9fb782b21ef842f0','权限管理','syspower!showPower.do','7518b13cf6da4e5580aeaa7decfc71bb',3,'2016-07-29 14:44:57','icon-user_key','权限管理'),('e2d46155babf4c549fd40f79c1d1db73','产品管理','investpro!investProManage.do','099b882f3f5749079af62aac20400782',2,'2016-07-29 16:37:49','icon-tag_green','产品的增删改查'),('e8ba0702782b49ef9bffeaba7021329f','张三的权限','1','syspowers',2,'2016-07-29 14:40:37','',''),('eea9712d2051499c9e38e314fa6617f2','李四1','3','d35cf70a67b143bcacc0bfae1b1c94e0',3,'2016-07-29 13:17:45','',''),('syspowers','系统权限','/','0',1,NULL,'icon-server_chart',NULL);

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

insert  into `sys_role_power`(`role_power_id`,`role_id`,`power_id`) values ('0d2965a7f43f419e8593103f9743b0e4','398f5389af8f49749977cf21dc846f2e','d35cf70a67b143bcacc0bfae1b1c94e0'),('36531fbbeec445b79dcc69047f7016b1','526b3b81287c4b4a897676ef194d04d4','099b882f3f5749079af62aac20400782'),('3956623b7875414a8f27fb49194ffba3','526b3b81287c4b4a897676ef194d04d4','e2d46155babf4c549fd40f79c1d1db73'),('4125f05610a84db7a8ff18f431588752','526b3b81287c4b4a897676ef194d04d4','a007affa6bca4c20adc45e2ca5550bf8'),('4cc04ab7ee90479f9c950b315582ab86','526b3b81287c4b4a897676ef194d04d4','d35cf70a67b143bcacc0bfae1b1c94e0'),('55bdcf3d28f74f2e9fb87daf359ba4a8','398f5389af8f49749977cf21dc846f2e','bcc2df5e881c4af68f4743dbda55ec1f'),('62ea121782de4358b0c6fee4c22a2d98','526b3b81287c4b4a897676ef194d04d4','7518b13cf6da4e5580aeaa7decfc71bb'),('63103d4edb394622bec79ce6b3a2e3c6','526b3b81287c4b4a897676ef194d04d4','syspowers'),('68672e14f6544f3086eaf425e249ae81','526b3b81287c4b4a897676ef194d04d4','a59137b1774f4839b9bb89b40d51cf87'),('6c2f652ca94f4bd2987427c3a716627e','398f5389af8f49749977cf21dc846f2e','eea9712d2051499c9e38e314fa6617f2'),('6ebb2ef4e07d4a07a4fc8e8fa7bf069a','e4fa3ab5c9304eafb326449012a063a2','e8ba0702782b49ef9bffeaba7021329f'),('8b1f23525cc5488289f666d722a20bfc','526b3b81287c4b4a897676ef194d04d4','bcc2df5e881c4af68f4743dbda55ec1f'),('8c1b4de5d60e4e40a06a4ab3febcee72','e4fa3ab5c9304eafb326449012a063a2','bcc2df5e881c4af68f4743dbda55ec1f'),('9055ff952bdb46d38f21faf6c8115983','526b3b81287c4b4a897676ef194d04d4','1553ee3cc9874033a1cf3d07dd6bd791'),('935e2fa7205b4d31a0b76c19d93b112c','526b3b81287c4b4a897676ef194d04d4','303bff33608c46478eafe0b2720d949e'),('a601b0a2cf674e8a8e96ee6fd9a9cc66','526b3b81287c4b4a897676ef194d04d4','e8ba0702782b49ef9bffeaba7021329f'),('ab6d9e5b04b9475195ecd3cc60293aea','398f5389af8f49749977cf21dc846f2e','665cb2abea514a35b1528bc195af4175'),('cc9161147cd64acca0ca3b08c5b64760','e4fa3ab5c9304eafb326449012a063a2','1553ee3cc9874033a1cf3d07dd6bd791'),('e10cb6346d0544519383c9f0915d2b29','526b3b81287c4b4a897676ef194d04d4','924503c693bd4a569fece470c23d1c96'),('ef765a6417c54a489b99ef77a93a4d8c','526b3b81287c4b4a897676ef194d04d4','e25ebca75b12481d9fb782b21ef842f0'),('f6fa80b205b34edf9009ee853847cbb1','526b3b81287c4b4a897676ef194d04d4','eea9712d2051499c9e38e314fa6617f2'),('fdaaf0c379034bf59c902078ae079487','526b3b81287c4b4a897676ef194d04d4','665cb2abea514a35b1528bc195af4175');

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
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idcard` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ROLE` (`role_id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`name`,`password`,`role_id`,`create_time`,`update_time`,`remark`,`phone`,`email`,`idcard`) values ('0','admin','21232f297a57a5a743894a0e4a801fc3','526b3b81287c4b4a897676ef194d04d4','2016-07-29 13:23:07','2016-07-29 13:25:11','','','',''),('1','1111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('11',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1312312312',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2222',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('3','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('31231123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('3123123123123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('312321312312312312',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('321',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('3213',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('3213111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('32144',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('333',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('3333',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('33333',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('333333333333',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('4','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('44',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('4444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('444444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('5','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('5cc6c63d1069403b8ab97cd1876724ef','ls','73882ab1fa529d7273da0db6b49cc4f3','398f5389af8f49749977cf21dc846f2e','2016-07-29 13:23:07','2016-07-29 13:23:07','','','',''),('6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('7',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('9',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('97d57f49db95473ba1f5127deffdccd0','zs','f63f4fbc9f8c85d409f2f59f2b9e12d5','e4fa3ab5c9304eafb326449012a063a2','2016-07-29 12:56:19','2016-07-29 13:22:43','','','','510821199401108853'),('99',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('999',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('9999',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `turn_list` */

DROP TABLE IF EXISTS `turn_list`;

CREATE TABLE `turn_list` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `invest_list` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财记录外键',
  `type` int(11) DEFAULT NULL COMMENT '类型，1转出,2购买转让',
  `poundage` double DEFAULT NULL COMMENT '手续费',
  `coupon_poundage` double DEFAULT NULL COMMENT '优惠手续费',
  `createtime` datetime DEFAULT NULL,
  `donetime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态：1待支付，2完成',
  PRIMARY KEY (`id`),
  KEY `FK_turn_list` (`invest_list`),
  CONSTRAINT `FK_turn_list` FOREIGN KEY (`invest_list`) REFERENCES `invest_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='转让标记录';

/*Data for the table `turn_list` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
