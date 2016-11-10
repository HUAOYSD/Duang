/*
Navicat MySQL Data Transfer

Source Server         : jstl
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : duang

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-11-10 19:09:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad
-- ----------------------------
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

-- ----------------------------
-- Records of ad
-- ----------------------------
INSERT INTO `ad` VALUES ('11234567895236', '胖大海1', '清热润肺1', '2016-09-05 14:00:09', 'ccb6c64843ad4c518aa5726498acd91f.jpg', 'http://localhost:8080/sdf1', '0');
INSERT INTO `ad` VALUES ('839a719bf9d244af8005133906018621', '跑步', 'asdfasdfasdf爱迪生', '2016-09-05 16:54:33', '6061fc2388db4ec3934b5f61aeb499db.jpg', 'http://localhost:8080/sdf', '1');
INSERT INTO `ad` VALUES ('9c593fca1e524d7785f0738aeb994562', '13212', '321', '2016-09-06 15:15:24', '491d502156dd4436958d7835d99be8fb.png', 'http://www.baidu.com', '1');

-- ----------------------------
-- Table structure for apply_loan_car
-- ----------------------------
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

-- ----------------------------
-- Records of apply_loan_car
-- ----------------------------
INSERT INTO `apply_loan_car` VALUES ('123121212', '123456789', 'Aleln', '123456', '13467897', '北京', '北京', '代售点', '20', '6', '6565', 'asdf', '24657876545', '/resources/file/basic/lvbu/datums/02.jpg', '/resources/file/basic/lvbu/datums/02.jpg');

-- ----------------------------
-- Table structure for apply_loan_house
-- ----------------------------
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

-- ----------------------------
-- Records of apply_loan_house
-- ----------------------------
INSERT INTO `apply_loan_house` VALUES ('123456', '5467897', 'fsd', '78945621', '123456', '北京', '北京', '12345678', '/resources/file/basic/lvbu/datums/03.jpg', '/resources/file/basic/lvbu/datums/03.jpg;/resources/file/basic/lvbu/datums/01.jpg', '20000');

-- ----------------------------
-- Table structure for apply_loan_info
-- ----------------------------
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

-- ----------------------------
-- Records of apply_loan_info
-- ----------------------------
INSERT INTO `apply_loan_info` VALUES ('123456', 'jiedai_lvbu', '12360000', '24', '1', '吕布', '15632569874', '123456789123456789', 'lvbu@173.com', '广东', '广东理财师', '北京花梨坎', '0', '3', '0', 'asd', '1', '789456258012345645', '金融', '1', '经理', '北京', '爱钱金融', '1234564655', '中国银行', '2400000', '公司周转', '20000', '张飞', '123456856', '/resources/file/basic/lvbu/datums/01.jpg;/resources/file/basic/lvbu/datums/02.jpg;/resources/file/basic/lvbu/datums/03.jpg', '/resources/file/basic/lvbu/datums/03.jpg');

-- ----------------------------
-- Table structure for bill_invest
-- ----------------------------
DROP TABLE IF EXISTS `bill_invest`;
CREATE TABLE `bill_invest` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'invest_member表外键',
  `invest_list_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '理财记录，只有消费、手续费、赎回、收益、手续费的时候用到这个字段',
  `card` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡，只有充值和提现使用到这个字段',
  `use_type` int(11) DEFAULT NULL COMMENT '资金类型，1充值，2提现，3消费（购买理财产品），4购买手续费，5赎回（仅本金），6收益，7转让手续费、8推荐奖励',
  `money` double DEFAULT NULL COMMENT '变动金额',
  `balance` double DEFAULT NULL COMMENT '变动后账户余额',
  `asset` double DEFAULT NULL COMMENT '变动后总资产',
  `status` int(11) DEFAULT '1' COMMENT '状态，1进行中，2成功，3失败',
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

-- ----------------------------
-- Records of bill_invest
-- ----------------------------

-- ----------------------------
-- Table structure for bill_loan
-- ----------------------------
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

-- ----------------------------
-- Records of bill_loan
-- ----------------------------
INSERT INTO `bill_loan` VALUES ('13456', '5467897', '1', '123', '1', '1', '1', '2016-08-24 15:45:50', 'we', '2', '7c6cdd66bca3437c8384fae952930d2b');
INSERT INTO `bill_loan` VALUES ('chenhao', 'chenhao', '1', null, '200000', null, '2', '2016-11-10 15:45:50', 'ok', '1', 'chenhao');
INSERT INTO `bill_loan` VALUES ('kangmiao', 'kangmiao', '1', null, '100000', null, '2', '2016-11-10 15:45:50', 'ok', '1', 'kangmiao');
INSERT INTO `bill_loan` VALUES ('qiaoran', 'qiaoran', '1', null, '260000', null, '2', '2016-11-10 15:45:50', 'ok', '1', 'qiaoran');
INSERT INTO `bill_loan` VALUES ('songjiehui', 'songjiehui', '1', null, '240000', null, '2', '2016-11-10 15:45:50', 'ok', '1', 'songjiehui');
INSERT INTO `bill_loan` VALUES ('yangjinlin', 'yangjinlin', '1', null, '300000', null, '2', '2016-11-10 15:45:50', 'ok', '1', 'yangjinlin');
INSERT INTO `bill_loan` VALUES ('yiliqun', 'yiliqun', '1', null, '100000', null, '2', '2016-11-10 15:45:50', 'ok', '1', 'yiliqun');
INSERT INTO `bill_loan` VALUES ('zhouhuirong', 'zhouhuirong', '1', null, '400000', null, '2', '2016-11-10 15:45:50', 'ok', '1', 'zhouhuirong');
INSERT INTO `bill_loan` VALUES ('zhouye', 'zhouye', '1', null, '200000', null, '2', '2016-11-10 15:45:50', 'ok', '1', 'zhouye');

-- ----------------------------
-- Table structure for bind_card
-- ----------------------------
DROP TABLE IF EXISTS `bind_card`;
CREATE TABLE `bind_card` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_info_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户公共表id外键',
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名字',
  `idcard` char(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证',
  `phone` char(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开户手机号',
  `bank_no` char(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡号',
  `open_bank` varchar(90) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开户行',
  `type` int(11) DEFAULT NULL COMMENT '类型，1储蓄卡，2借记卡，3信用卡',
  `photo_img1` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡正面照',
  `photo_img2` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡背面照',
  `opt_time` datetime DEFAULT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`),
  KEY `FK_bind_card` (`member_info_id`),
  CONSTRAINT `FK_bind_card` FOREIGN KEY (`member_info_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户银行卡';

-- ----------------------------
-- Records of bind_card
-- ----------------------------
INSERT INTO `bind_card` VALUES ('123', '123', 'Allen', '123456789123456789', '18601920463', '7894561230123456789', '中国银行', '1', '05c9d08607674e008552fb4a437c336c.jpg', null, '2016-08-15 11:38:08');
INSERT INTO `bind_card` VALUES ('17e1e2012d47405d810d5ebc0f56752a', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '', '', '', '', '', '1', null, null, '2016-10-25 17:18:33');
INSERT INTO `bind_card` VALUES ('4a3454174b3646a79bf419de94089bd6', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '李永辉', '4564578942415', '18601920463', '548785645123456787878', '邮政银行', '2', null, null, '2016-10-28 14:37:11');

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `conPath` varchar(2000) COLLATE utf8_unicode_ci NOT NULL COMMENT '路径',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `state` int(11) DEFAULT '1' COMMENT '状态 1正常 0删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='合同\r\n';

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('0c708459883346ecb0392730af5ee8aa', 'resources\\file\\basic\\contract7.pdf', '2016-10-28 18:03:37', '7.pdf', '1');
INSERT INTO `contract` VALUES ('13456789', 'resources\\POReceiveReport.pdf', '2016-09-19 10:03:08', 'POReceiveReport.pdf', '1');
INSERT INTO `contract` VALUES ('247fb51ee7ae4248baab83df600d382c', 'resources\\file\\basic\\contractBJ-DK-20161031171334-0016.pdf', '2016-10-31 17:13:58', 'BJ-DK-20161031171334-0016.pdf', '1');
INSERT INTO `contract` VALUES ('2c4058d403414e39a0bdc46570c742f8', 'resources\\file\\basic\\contract\\BJ-DK-20161031172427-0019.pdf', '2016-10-31 17:24:27', 'BJ-DK-20161031172427-0019.pdf', '1');
INSERT INTO `contract` VALUES ('3e1f759f02ff4ebca1a9ad43d4904296', 'resources\\file\\basic\\contract\\BJ-DK-20161031172414-0018.pdf', '2016-10-31 17:24:14', 'BJ-DK-20161031172414-0018.pdf', '1');
INSERT INTO `contract` VALUES ('69fea6a9363c4e19990ae4db8e835e0b', 'resources\\file\\basic\\contractBJ-DK-20161031170015-0010.pdf', '2016-10-31 17:00:15', 'BJ-DK-20161031170015-0010.pdf', '1');
INSERT INTO `contract` VALUES ('6a048e34894a4aa3802fab4990e223f0', 'resources\\file\\basic\\contractBJ-DK-20161031170604-0012.pdf', '2016-10-31 17:06:04', 'BJ-DK-20161031170604-0012.pdf', '1');
INSERT INTO `contract` VALUES ('7ccbb0609b794e1384117bc2bbde52b6', 'resources\\file\\basic\\contract5.pdf', '2016-10-28 17:51:39', '5.pdf', '1');
INSERT INTO `contract` VALUES ('7d3537b43edd462c85e83174572f6d8d', 'resources\\file\\basic\\contract4.pdf', '2016-10-28 17:48:30', '4.pdf', '1');
INSERT INTO `contract` VALUES ('82ddf47136ab4a0299e2f34f80a448a3', 'resources\\file\\basic\\contractBJ-DK-20161031170839-0014.pdf', '2016-10-31 17:08:39', 'BJ-DK-20161031170839-0014.pdf', '1');
INSERT INTO `contract` VALUES ('8d7f688c05e04bf0aca24d5a0c64a58f', 'resources\\file\\basic\\contract6.pdf', '2016-10-28 17:58:09', '6.pdf', '1');
INSERT INTO `contract` VALUES ('937aeecb8f72473aab73556a313d2b9b', 'resources\\file\\basic\\\\contract\\BJ-DK-20161027181337-0003.pdf', '2016-10-27 18:13:40', 'BJ-DK-20161027181337-0003.pdf', '1');
INSERT INTO `contract` VALUES ('a6cdcfa6943d468b88a9f40d537e6bb5', 'resources\\file\\basic\\contractBJ-DK-20161031170133-0011.pdf', '2016-10-31 17:01:33', 'BJ-DK-20161031170133-0011.pdf', '1');
INSERT INTO `contract` VALUES ('asdf456sdf', 'sdfsdf/sdf.pdf', '2016-09-19 10:03:32', 'POReceiveReport.pdf', '1');
INSERT INTO `contract` VALUES ('b6f19181f3ac4cf8a9a7c02a350768ba', 'resources\\file\\basic\\contractBJ-DK-20161028180841-0008.pdf', '2016-10-28 18:08:41', 'BJ-DK-20161028180841-0008.pdf', '1');
INSERT INTO `contract` VALUES ('c5540467ca65427ab06891ee6c46b08e', 'resources\\file\\basic\\contractBJ-DK-20161031165943-0009.pdf', '2016-10-31 16:59:47', 'BJ-DK-20161031165943-0009.pdf', '1');
INSERT INTO `contract` VALUES ('c8c3fefcfb664c6f949ca985304a7f9b', 'resources\\file\\basic\\contractBJ-DK-20161031170806-0013.pdf', '2016-10-31 17:08:06', 'BJ-DK-20161031170806-0013.pdf', '1');
INSERT INTO `contract` VALUES ('dcc0cd74e1304bc59d1c3469ccd14ffe', 'resources\\file\\basic\\contract\\BJ-DK-20161101163634-0020.pdf', '2016-11-01 16:36:36', 'BJ-DK-20161101163634-0020.pdf', '1');
INSERT INTO `contract` VALUES ('de07a356383e4ff48be936742a0396e7', 'resources\\file\\basic\\contract\\BJ-DK-20161031171548-0017.pdf', '2016-10-31 17:15:48', 'BJ-DK-20161031171548-0017.pdf', '1');
INSERT INTO `contract` VALUES ('e354711beecc4f36a6a3ffb0bf842690', 'resources\\file\\basic\\contractBJ-DK-20161031171222-0015.pdf', '2016-10-31 17:12:22', 'BJ-DK-20161031171222-0015.pdf', '1');

-- ----------------------------
-- Table structure for customer_manager
-- ----------------------------
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

-- ----------------------------
-- Records of customer_manager
-- ----------------------------
INSERT INTO `customer_manager` VALUES ('2fd42073b61d4d99a25aefdb10bd167a', '56f6f851a3fa452ca6364024c169d96b', '王思聪', 'WSC21308', '男', '130452197811053356', '15611146202@sina.com', '15611146202', null, null, '日了狗了', '2016-09-21 14:43:30', '0');
INSERT INTO `customer_manager` VALUES ('6cdd00e4ecf8430e8e1130d30c74c6e2', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `customer_manager` VALUES ('88d6eac7c86f41008b7efff656cce61b', '97d57f49db95473ba1f5127deffdccd0', '王麻子', 'W03', '女', '510811199502233333', '977943333@qq.com', '15600046133', '', '', '王麻子的账号儿sa33', '2016-08-10 09:59:27', '0');
INSERT INTO `customer_manager` VALUES ('95724ed7968744e1af90e1eb1342b219', 'c883c60c396f4fa385cf66d93071facc', '是个', '', '男', '', '', '', null, null, '', '2016-08-10 15:19:37', '0');
INSERT INTO `customer_manager` VALUES ('dcae8aebb8924186b3673da73a232e30', '65afb5b2d9344855a546a4e318adf537', '王健林', 'WJL1515', '男', '510822194505055588', '15611146201@sina.com', '15611146201', null, null, '王思聪他爹', '2016-09-21 14:54:39', '0');
INSERT INTO `customer_manager` VALUES ('dfsdf', '1212121212', '李永辉', '12', null, null, null, null, null, null, null, null, '0');
INSERT INTO `customer_manager` VALUES ('wangmazi', 'e732795b6a1e490cafb700ba029943a3', '王麻子', 'WMZ', '男', '132051199105254485', 'wangmazi@sina.com', '15894493596', null, null, null, '2016-08-31 10:45:41', '0');

-- ----------------------------
-- Table structure for friends
-- ----------------------------
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

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('123456', '0712505779b9476e831e1880ccdb9382', '124', '1', '2016-08-23 17:32:57');
INSERT INTO `friends` VALUES ('1236588', 'c8eb824f8e2c4976b9e816a2b6c85d60', 'lvbu', '2', '2016-08-23 17:33:44');
INSERT INTO `friends` VALUES ('123789', '123', 'c8eb824f8e2c4976b9e816a2b6c85d60', '2', '2016-08-23 17:33:15');
INSERT INTO `friends` VALUES ('5478asdfae45sd', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', 'f31d3a3b5ad2411aa6eeb5a22e9d3864', '1', '2016-10-12 14:57:12');
INSERT INTO `friends` VALUES ('567dgs', 'c8eb824f8e2c4976b9e816a2b6c85d60', '124', '1', '2016-10-10 16:23:47');
INSERT INTO `friends` VALUES ('as23dsdwqer2345as', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', 'songjiang', '2', '2016-10-12 14:57:52');
INSERT INTO `friends` VALUES ('as34566745dw234ere123', 'songjiang', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2', '2016-10-12 14:58:31');

-- ----------------------------
-- Table structure for friends_news
-- ----------------------------
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

-- ----------------------------
-- Records of friends_news
-- ----------------------------
INSERT INTO `friends_news` VALUES ('1231', 'songjiang', '今天的好吃的', '2016-10-13 17:25:38', '1');
INSERT INTO `friends_news` VALUES ('54678sd', 'lvbu', '玩的很开心', '2016-10-13 17:26:08', '1');

-- ----------------------------
-- Table structure for friends_news_img
-- ----------------------------
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

-- ----------------------------
-- Records of friends_news_img
-- ----------------------------
INSERT INTO `friends_news_img` VALUES ('123', 'asd/asdf/tt.jpg', '1231', '1');
INSERT INTO `friends_news_img` VALUES ('254', 'sdfa/sdf/tt.jpg', '54678sd', '1');
INSERT INTO `friends_news_img` VALUES ('561', 'asdf/jsdf/sdf.jpg', '1231', '2');

-- ----------------------------
-- Table structure for invest_list
-- ----------------------------
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
  `status` int(11) DEFAULT '1' COMMENT '理财状态，1资金匹配中,2投资收益中,3完成投资，4到期回款中，5回款成功，6回款失败，7过期、8提前赎回成功',
  `open_date` datetime DEFAULT NULL COMMENT '创建日期',
  `back_date` datetime DEFAULT NULL COMMENT '实际回款日期',
  `pact_number` char(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '合同编号',
  `invest_style` int(11) DEFAULT NULL COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  `poundage_turn` double DEFAULT NULL COMMENT '转让手续费',
  `poundage_privilege` double DEFAULT NULL COMMENT '转让/提前赎回 手续费',
  `is_turn` int(11) DEFAULT '0' COMMENT '是否已经转让，0没，1是',
  `turn_status` int(11) DEFAULT '0' COMMENT '转让状态，1转让中，2成功，3过期，4失败',
  `calc_beginDate` datetime DEFAULT NULL COMMENT '开始计息时间',
  `calc_endDate` datetime DEFAULT NULL COMMENT '截止计息时间',
  `days` int(11) DEFAULT NULL COMMENT '天数',
  `giftSum` double DEFAULT NULL COMMENT '红包',
  PRIMARY KEY (`id`),
  KEY `FK_invest_list_scales` (`scale_id`),
  KEY `FK_invest_list_ticket` (`invest_ticket_id`),
  KEY `FK_invest_list_member` (`member_info`),
  CONSTRAINT `FK_invest_list_member` FOREIGN KEY (`member_info`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_invest_list_scales` FOREIGN KEY (`scale_id`) REFERENCES `scale` (`id`),
  CONSTRAINT `FK_invest_list_ticket` FOREIGN KEY (`invest_ticket_id`) REFERENCES `invest_ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='投资记录表';

-- ----------------------------
-- Records of invest_list
-- ----------------------------

-- ----------------------------
-- Table structure for invest_member
-- ----------------------------
DROP TABLE IF EXISTS `invest_member`;
CREATE TABLE `invest_member` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `memberinfo_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `is_contract` int(11) DEFAULT '0' COMMENT '契约用户,0不是，1是',
  `balance` double DEFAULT '0' COMMENT '余额',
  `investing` double DEFAULT '0' COMMENT '投资中金额',
  `total_income` double DEFAULT '0' COMMENT '总收益',
  `total_money` double DEFAULT '0' COMMENT '总资产（余额+投资中金额）',
  `current_income` double DEFAULT '0' COMMENT '当期收益',
  `unsettledBalance` double DEFAULT '0' COMMENT '未结金额',
  PRIMARY KEY (`id`),
  KEY `member_info_invest_id` (`memberinfo_id`),
  CONSTRAINT `FK_INVEST_MEMBER_INFO` FOREIGN KEY (`memberinfo_id`) REFERENCES `member_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='理财用户列表';

-- ----------------------------
-- Records of invest_member
-- ----------------------------
INSERT INTO `invest_member` VALUES ('000dbdadfd1349d78ca7a88aa60ad3af', 'dba4129c95bb4b7b88a69be29f0a9cc4', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('01e32de2e4094f67801784e3cd6665b4', 'b9f0f8980b964743ad814954a013be3e', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('05e807d57c254682b4f36af9233c0b84', '1504fb290eb54d56871dc236835114ae', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('0ac6684a1b9a49c2995f7afc944e04ed', '22a1e25a565d4d0390a4d88293f30eab', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('0ef00ef3ad984ee2aef2506eccba48c9', '1243c583a96140eb85a01757acda5042', '0', '10', '0', '0', '10', '0', '0');
INSERT INTO `invest_member` VALUES ('13989bc7c9ae4d52b72350431fd6ac32', '0ddd89d9f32847fab1acc0924e0a2d65', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('19472ad6614541499789fc207b3fc656', 'bdb2d460baa64b26b309c9f9383d23d2', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('1afe71eb9a25488b93fffbae107597f6', 'facbf724a11b4f23a37f4c91585e3b96', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('1d9b1f5629a744ccbbded2f9438bb66c', '731e4714b43746e38a7b513b6bad7aee', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('1f3faa8139f94d4e8ddc05594c08c479', '7c8225b7f00448eeb10030aa37c29914', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('205e7c5596b4400f94aa7c48276883e6', 'dce420bff80945c18c1c3bf96db3f480', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('21ec5d7467eb49f0b342fa0baacfd33d', '4016883ea7a84090bf28bff14f6e1723', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('230fbc72c0714f04a9e1ee51982d6b47', '3f4891b346ca47ab96c5e7e19f33a632', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('23123asdfwqwezxwer34zw44sdf34543', '123', '0', '1200', '0', '600', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('2652bfce54f64a14aad8dd3f3d18c911', 'd2eef99f254a4bd1b00d8609dfc86059', '0', '10000', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('29e4b78ec3bf4de79a4b233ae2323c94', 'd46b080bffae4c88b5baf3877226c0a4', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('2c2fbca997ea47ceb3076eb9d6588e44', '8dc794425fda4c738cca7f9585da791d', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('2de106f2e7b74f0488a208e5ef6d7cb6', '3084a2a51126491f88ab9b33a6d87e0e', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('3052a006501e497284f8823cac245864', '93645998e65f48d98695fe5199b21df8', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('30683704a0b440d5886324cc5d3035f9', '188c6bb5157d4ff193e5946f95c2123c', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('3483e414192441f381f6982f0e63cb7e', 'c534c617d6964b76a1505f57de951d43', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('3860c40071e64876a9e208fe3fc3106d', 'db9994937f2744deb248bbdf488e339b', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('3d9727ef175e46619b608ab8b009ea3b', '10e35b13ae2f4630962fe9b19fb30fdb', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('3fb3ea4830084efcaceedf59818cc15c', '4a8a35dde23244b086b736d224080c50', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('53194af897734885a75c2d3162038ba3', 'c4c972c8ac164f6cbd47513db25337fc', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('59eb261e19894d77a315c5af3e7eaef3', '624bf13fad0440b0adfc67db693951b5', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('5bebf4d19d0245ab8d5c8db538d88283', '931ca70826584ff6bd83315c38557317', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('5cce48fb7ef2484ba2beb37e20be8f97', 'd9ad4cebbe5b4514b51e4083f4478fdf', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('633cd4d20e734f679eeea68858513e2f', '4015828c0b08485786cc10a46015558a', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('681f713045fa4662b8c76d2af635e80b', '2223f8db21cb42c7a113a3639dd2cc2e', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('72a98638129642c5995e22e8378d452b', 'd84d4b689ae64d3bb0607ef8ea51addb', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('74f58b2d30894e28ae52e6dbaf21d47e', '2a52fd5d501b4f72b2e8e1966099b516', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('799a67f16c294c448bb61364aa54307f', 'c6ef1e42350e4cf98e2dbe73cd7c0f91', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('7f4958b49627444b99d21414077d774e', '688f4311e9ef463e854c6d762632add6', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('8903edf5fffb4b5ca0c8131b5e92bfbc', '20c673e086ea48658999905b019aa44d', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('94c2d1ccce3f42acbd805435839c66a1', 'bcce5fe7a48a4e4ba3126bf30a9ac7c5', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('990c2469a7414325982c8745c2048baa', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '0', '9215', '4635', '0', '13850', '0', '0');
INSERT INTO `invest_member` VALUES ('a11d5bcad5ea4f0e972cc325893ace69', '67c2e90a96104653ae67513717a176a9', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('a5ac0b8e56954679ab538bd6ff261752', '9aada9a4175041b28f16ca68ec3a638e', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('asdf3412345asdf34523asfd435345asd', '124', '0', '1250', '0', '954', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('asdfw434534asdfg4546gwe436asd232', 'lvbu', '0', '630', '0', '50', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('b543fcece7574148aac6262c5b0f13dc', 'e84121690e6e4d648e5ef821fa5e8a8a', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('bc3d0959838d4e0aa717f91c24d0420f', 'c98652fc37ac41ab95aae44cac17e2d7', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('c0e51d4652644eef96fa15b4b218e252', '12b6856d2f214da08bb33e7ca6b29a02', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('c13d52a4e7594634b1475680290da1f2', '1eeb2458ed2745658bf4b9a322f57d7a', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('c19c2a048d6b467283a2cb91291b3bde', 'bac67b0fc2a0450a9d9c4c406427414c', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('c1b56478dc584e8bb902baebe104fd9e', '16c41343a1014716a82c6a97c42e00c0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('c230c4ae23c9404aa21c3c2e34dc70a5', '84986744114e4c3e82e2703e6eb9770e', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('c5e11d0be9ca4399a207e98c3cac2cb9', '8ebbb530091f4e3695bce8b1fef73426', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('c5e1bac38aba47649c1752bd31bac982', 'f394ca2c7ae948788f3ccda8a73e374b', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('c90de18bb0814bfaa6744ae8c20d11bc', 'ff8c9e1c54444f038634fec36e98ba0d', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('cc32f156e40748bbb7e47eb2074de842', '838aa77cfa0b4e229454e3a9e8784cc9', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('cde5f611104a4c3cb5e5c4ddcd0e3d84', 'dcf175c7bc8e433ab80ec85f06bae842', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('ce4b3cde12f8421281e08763524fdb13', 'f8768de73be3425b92c5478d1607684f', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('cef870bb5d7a4206827337a1cd3a662e', '598751c306b94a7c8e30e1896ce9772c', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('cf42256c2195457daa538fb9868cfd33', 'c4613d5d20184ac7a73d24321f97574f', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('chenhao', 'chenhao', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('d1fa8f25fb534a5bb395015fe06c42c1', '78abb76b89ec496c9de742413f44f566', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('d7450b257d4f42d98f219f3fbad0a861', '53e8cfc503ca47e2a6781c31461b6a7c', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('e00ead0b3f6d4288b3fcadb9bc04895a', '85178b2a4737458c8d9aed8e4fdfcb06', '0', '20000', '10', '4547', '7478', '12', '0');
INSERT INTO `invest_member` VALUES ('e2852a63db6940a582e9bf43e22ed4d4', '775d0b36715d4d7788ac17718ed78246', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('e47b28e56ac44f9d929c1665c1dae6a2', 'f31d3a3b5ad2411aa6eeb5a22e9d3864', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('f784c3bdea984bfc9f83b10c6af8ae1b', '1d69f5e9a5dd4840b5133c26b2ba0437', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('f8bedd95c9f04f5db4b477297df70a2d', '34cddb18c65b439c89005394baddb244', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('fc9b3ca9c41640d0a2f0fbb1d04792cd', '32bb6f2e9d5f42e4922fd54d9193c547', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('fdefc7f826eb4f28beca1930bd3c8032', '14e012115ccd41a2a067bded58a7c0f5', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('fe3772027c15496eaf737dcfb7c4bcf0', 'cb470be575744299bdfa9d7be2c54e5e', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('feb5a9c133564e4c8e9482b09f601f34', '4833d41037974a958537f2ad976a703f', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('feddfb2f08d940c39859c76ce27c1ae9', 'd383fc0e12e94174b45e663518493ff6', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('kangmiao', 'kangmiao', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('qiaoran', 'qiaoran', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('songjiehui', 'songjiehui', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('yangjinlin', 'yangjinlin', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('yiliqun', 'yiliqun', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('zhouhuirong', 'zhouhuirong', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `invest_member` VALUES ('zhouye', 'zhouye', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for invest_ticket
-- ----------------------------
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

-- ----------------------------
-- Records of invest_ticket
-- ----------------------------
INSERT INTO `invest_ticket` VALUES ('123456', '理财券1', '只是个卷而已', '不要当真', '200', '2016-08-15 11:21:23', '2016-08-21 11:21:31', '2016-08-15 11:21:34', '6000', '03261c163fde477583d4ffad20a7209a;7868430bc64f48e299f38f9e22c8305e;d79a0f74392f4505ab1c050d4eb8eb26', '0');
INSERT INTO `invest_ticket` VALUES ('5bba590159e148dc8fea0d8dec3bf06c', '王宝强1', '离婚1', 'sadf1', '100', '2016-08-16 05:15:17', '2016-08-25 05:15:21', null, '10', null, '1');
INSERT INTO `invest_ticket` VALUES ('c8308d5ac410400f91b245356ae0af20', '是否', '爱上', null, '100', '2016-08-16 16:19:03', '2016-08-16 16:19:07', '2016-08-16 16:19:13', '234', null, '1');
INSERT INTO `invest_ticket` VALUES ('d86bbd26b09c422ea6f7cabfe3981bc4', '里约1', '奥运会1', '阿斯蒂芬1', '50', '2016-08-11 04:25:35', '2016-08-25 04:25:39', '2016-08-16 04:25:48', '5001', '03261c163fde477583d4ffad20a7209a;7868430bc64f48e299f38f9e22c8305e', '0');

-- ----------------------------
-- Table structure for invite_code
-- ----------------------------
DROP TABLE IF EXISTS `invite_code`;
CREATE TABLE `invite_code` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `member_id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `invite_code` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '邀请码',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `state` int(11) DEFAULT '1' COMMENT '状态 0过期  1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='邀请码';

-- ----------------------------
-- Records of invite_code
-- ----------------------------

-- ----------------------------
-- Table structure for loan_list
-- ----------------------------
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

-- ----------------------------
-- Records of loan_list
-- ----------------------------
INSERT INTO `loan_list` VALUES ('123456789', '7c6cdd66bca3437c8384fae952930d2b', '5467891231', '2', '1', '1000', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '1', '0.12', '2016-08-26 14:37:33', '2016-08-26 14:37:35', '2016-08-26 14:37:37', '2016-08-26 14:37:40', '2016-08-26 14:37:42', '0', '2', '88d6eac7c86f41008b7efff656cce61b', '2016-09-02 18:02:47', '1454545放水电费是', '15');
INSERT INTO `loan_list` VALUES ('12asd3sddas456asdsdf789', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', 'AJSH2016083111', '2', '1', '23000', '20000', '2000', '1000', '23000', '0', '0', '0', '0', '1', '2', '2', '1', '娶老婆', '0.12', '2016-07-01 15:53:58', '2016-10-12 14:54:01', '2016-07-01 14:54:17', '2016-10-12 14:54:50', '2016-10-12 14:54:56', '0', '2', 'wangmazi', '2016-10-12 14:55:14', '买房子', '104');
INSERT INTO `loan_list` VALUES ('5467897', '7c6cdd66bca3437c8384fae952930d2b', '789456123', '2', '1', '1500', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '1', '1', '0.15', '2016-08-24 15:45:05', '2016-08-24 15:45:08', '2016-08-24 15:45:11', '2016-08-24 15:45:13', '2016-08-24 15:45:16', '0', '2', '95724ed7968744e1af90e1eb1342b219', '2016-09-02 15:45:27', '1', '15');
INSERT INTO `loan_list` VALUES ('chenhao', 'chenhao', 'AHSH20161111IIF', '1', '2', '200000', '195000', '4000', '1000', '195000', '0', '205000', '0', '205000', '1', '1', '2', '1', '企业资金周转', '0.16', '2016-11-10 10:43:06', '2016-11-10 10:43:06', null, null, null, '1', '2', null, '2016-11-10 10:43:06', '情况属实，通过。 ', '60');
INSERT INTO `loan_list` VALUES ('jidai_songjiang', 'songjiang', 'AJSH2016083112', '2', '1', '100000', '90000', '8000', '2000', '90000', '0', '0', '0', '0', '1', '2', '1', '1', '宋江借钱装逼于梁山', '0.15', '2016-08-31 11:12:44', '2016-08-31 11:12:47', '2016-08-31 11:12:53', '2016-08-31 11:12:56', '2016-08-31 11:12:59', '2', '2', 'wangmazi', '2016-09-02 11:13:08', '找吴用asdfads', '60');
INSERT INTO `loan_list` VALUES ('jiedai_lvbu', 'lvbu', 'AJSH2016083111', '1', '1', '1000', '1000', '10', '1000', '1000', '0', '0', '0', '0', '1', '2', '2', '1', '吕布借钱养马侯貂蝉', '0.12', '2016-08-31 10:43:06', '2016-08-31 10:43:12', '2016-08-31 10:43:15', '2016-08-31 10:43:18', '2016-08-31 10:43:24', '2', '2', 'wangmazi', '2016-09-02 10:43:52', '问董卓', '15');
INSERT INTO `loan_list` VALUES ('kangmiao', 'kangmiao', 'AHSH20161111IOS', '1', '2', '100000', '95000', '4000', '1000', '95000', '0', '105000', '0', '105000', '1', '1', '2', '1', '企业资金周转', '0.16', '2016-11-10 10:43:06', '2016-11-10 10:43:06', null, null, null, '1', '2', 'wangmazi', '2016-11-10 10:43:06', '情况属实，通过。 ', '30');
INSERT INTO `loan_list` VALUES ('qiaoran', 'qiaoran', 'AHSH20161111KNM', '1', '2', '260000', '255000', '4000', '1000', '255000', '0', '265000', '0', '265000', '1', '1', '2', '1', '企业资金周转', '0.16', '2016-11-10 10:43:06', '2016-11-10 10:43:06', null, null, null, '1', '2', 'wangmazi', '2016-11-10 10:43:06', '情况属实，通过。 ', '360');
INSERT INTO `loan_list` VALUES ('songjiehui', 'songjiehui', 'AHSH20161111JHG', '1', '2', '260000', '235000', '4000', '1000', '235000', '0', '265000', '0', '265000', '1', '1', '2', '1', '企业资金周转', '0.16', '2016-11-10 10:43:06', '2016-11-10 10:43:06', null, null, null, '1', '2', 'wangmazi', '2016-11-10 10:43:06', '情况属实，通过。 ', '360');
INSERT INTO `loan_list` VALUES ('yangjinlin', 'yangjinlin', 'AHSH20161111HGF', '1', '2', '300000', '295000', '4000', '1000', '295000', '0', '305000', '0', '305000', '1', '1', '2', '1', '企业资金周转', '0.16', '2016-11-10 10:43:06', '2016-11-10 10:43:06', null, null, null, '1', '2', 'wangmazi', '2016-11-10 10:43:06', '情况属实，通过。 ', '90');
INSERT INTO `loan_list` VALUES ('yiliqun', 'yiliqun', 'AHSH20161111POM', '1', '2', '100000', '95000', '4000', '1000', '95000', '0', '105000', '0', '105000', '1', '1', '2', '1', '企业资金周转', '0.16', '2016-11-10 10:43:06', '2016-11-10 10:43:06', null, null, null, '1', '2', 'wangmazi', '2016-11-10 10:43:06', '情况属实，通过。 ', '180');
INSERT INTO `loan_list` VALUES ('zhouhuirong', 'zhouhuirong', 'AHSH20161111UHB', '1', '2', '400000', '395000', '4000', '1000', '395000', '0', '405000', '0', '405000', '1', '1', '2', '1', '企业资金周转', '0.16', '2016-11-10 10:43:06', '2016-11-10 10:43:06', null, null, null, '1', '2', 'wangmazi', '2016-11-10 10:43:06', '情况属实，通过。 ', '180');
INSERT INTO `loan_list` VALUES ('zhouye', 'zhouye', 'AHSH20161111UYF', '1', '2', '200000', '195000', '4000', '1000', '195000', '0', '205000', '0', '205000', '1', '1', '2', '1', '企业资金周转', '0.16', '2016-11-10 10:43:06', '2016-11-10 10:43:06', null, null, null, '1', '2', 'wangmazi', '2016-11-10 10:43:06', '情况属实，通过。 ', '90');

-- ----------------------------
-- Table structure for loan_member
-- ----------------------------
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

-- ----------------------------
-- Records of loan_member
-- ----------------------------
INSERT INTO `loan_member` VALUES ('048db3e26e2547a0a9225d0d3abc1783', 'dcf175c7bc8e433ab80ec85f06bae842', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('07ee1ed43fae4dde98ec99cf225fe6bb', '20c673e086ea48658999905b019aa44d', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('0898c464344040e38cc4e569b6d7aa51', '731e4714b43746e38a7b513b6bad7aee', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('0b75da3a80b2418c8a7fd01f5a7d13bc', '3f4891b346ca47ab96c5e7e19f33a632', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('0bfbd4d02900427f979fab700fdf5ae5', '4016883ea7a84090bf28bff14f6e1723', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('1308413d2244457987fa60656f7690c9', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('152109b3ebb3415ba070c4141a80b3e0', '838aa77cfa0b4e229454e3a9e8784cc9', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('191fa81593eb4436a6e753b45ce6ffc4', '0ddd89d9f32847fab1acc0924e0a2d65', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('19a7885589b747b995a805513a8bd123', '22a1e25a565d4d0390a4d88293f30eab', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('2201d8e84f554911b5e5674b351669f6', '85178b2a4737458c8d9aed8e4fdfcb06', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('269afde68c4d4f7689e2f95432a4779e', '78abb76b89ec496c9de742413f44f566', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('2d803cc42b1347a7ba49f6b3b9a53fb1', '9aada9a4175041b28f16ca68ec3a638e', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('3125deb811c049baa55d74464293a205', 'c534c617d6964b76a1505f57de951d43', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('32622dbd58da46d1ac468d4d7d9555cc', 'd2eef99f254a4bd1b00d8609dfc86059', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('34991e4e776b4a6ba6e6bfe5799c7af9', '688f4311e9ef463e854c6d762632add6', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('38136c62c81348788b2643efb7daa0f2', 'b9f0f8980b964743ad814954a013be3e', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('3b936ed81ba042c98da1638f60c6ba67', '2223f8db21cb42c7a113a3639dd2cc2e', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('3feda8f26c6e44b8985b2a2e1a8913fc', 'd9ad4cebbe5b4514b51e4083f4478fdf', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('40aaa57682834295b758950d2946a1fb', '624bf13fad0440b0adfc67db693951b5', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('45dfc8ce51a8433dbb066545efa30469', 'f31d3a3b5ad2411aa6eeb5a22e9d3864', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('4be0c52709104f4bacfa9c32ef8fd142', 'bdb2d460baa64b26b309c9f9383d23d2', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('4d368e25d5c147afbab2feb0e2a58a2a', '12b6856d2f214da08bb33e7ca6b29a02', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('5720e31fc0364fcfa3e47c0bdb5fff0f', 'dba4129c95bb4b7b88a69be29f0a9cc4', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('5d1b340f557c4985a5a70d05d2cf6b26', '67c2e90a96104653ae67513717a176a9', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('5eccb7c4152440d7aeb50c15fc209fee', '2a52fd5d501b4f72b2e8e1966099b516', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('5fcf2c0e6be54b7d9b5df6c1af361ad0', 'bcce5fe7a48a4e4ba3126bf30a9ac7c5', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('622b1082bed84d52b77afe53ce446fc2', '8dc794425fda4c738cca7f9585da791d', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('65408e437af54d5989ed5394089a4833', 'dce420bff80945c18c1c3bf96db3f480', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('6ca6f3390ee242a19923b393bc52cde4', '1243c583a96140eb85a01757acda5042', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('70eab02f4fd74594a7c02a9f4e4b1f58', '34cddb18c65b439c89005394baddb244', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('71035e50ad1f4581a3b35ba18b4ae14b', 'cb470be575744299bdfa9d7be2c54e5e', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('7158c72c18ef4958b4a60b2882b846a2', 'f394ca2c7ae948788f3ccda8a73e374b', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('73d1339de8aa487cba53964602f121af', '188c6bb5157d4ff193e5946f95c2123c', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('7b41652ecd624110843e3bcee54a4085', 'ff8c9e1c54444f038634fec36e98ba0d', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('7b55b7f095e2408cb8eef8c0b790ac10', '84986744114e4c3e82e2703e6eb9770e', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('7c304ac8af2b46029852da261db2725e', '1d69f5e9a5dd4840b5133c26b2ba0437', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('7e07f93c69424f01825d4d080524a4b6', '53e8cfc503ca47e2a6781c31461b6a7c', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('7e4cfa5e5d0e45f4a7a3a08e9741e7b3', '4a8a35dde23244b086b736d224080c50', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('84b6f4b71ff548a6bc3219d99d517090', '8ebbb530091f4e3695bce8b1fef73426', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('8f38b18b00024a859e8656954c7467ac', '4833d41037974a958537f2ad976a703f', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('8f6bcb4add5742c297bde814805a8dd1', '4015828c0b08485786cc10a46015558a', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('9412ec170ebd4c5aa9f0af73e35b6d5d', '931ca70826584ff6bd83315c38557317', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('941f3c5fba2948ec9220f9f602ea23f2', '10e35b13ae2f4630962fe9b19fb30fdb', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('972c058402d14fa1812791db6491f0e9', '93645998e65f48d98695fe5199b21df8', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('978d45e6f4c24a99a1d32998c058fce2', 'd46b080bffae4c88b5baf3877226c0a4', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('9ad84df499b94e6b98e0ff2da6282da3', '3084a2a51126491f88ab9b33a6d87e0e', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('a064b2b038b9496d89c7bc85f7e36628', 'c4613d5d20184ac7a73d24321f97574f', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('a46e15f5afe14eaf826263d3421d87a0', '1504fb290eb54d56871dc236835114ae', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('a484fd452823407caba37ac75f25e0c8', '7c8225b7f00448eeb10030aa37c29914', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('asd87qwuqwer2j34j2hhk123u8qwer2', '7c6cdd66bca3437c8384fae952930d2b', '4000', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('b08043f4705c45099bc602b379f46660', '598751c306b94a7c8e30e1896ce9772c', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('b4e917d3bb72435ca710be9b84c983bd', 'f8768de73be3425b92c5478d1607684f', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('b9241bf7ee1c4377b19e00b5aec330ab', '32bb6f2e9d5f42e4922fd54d9193c547', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('bc3016be3ebc46398766379baf869e10', '14e012115ccd41a2a067bded58a7c0f5', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('c1d71959911d4f7899dc8cab4bf55908', 'c4c972c8ac164f6cbd47513db25337fc', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('c2e91b0594b34ca695b44afb448433b7', 'e84121690e6e4d648e5ef821fa5e8a8a', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('c520a5fe54ba4172a3ff2f2e51bf5fa1', 'facbf724a11b4f23a37f4c91585e3b96', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('chenhao', 'chenhao', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('dasdfasdfasdfa2314asdfasdfa432asdf', 'lvbu', '1000', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('ddfc93c32e734224b217c0babcd2ae14', '775d0b36715d4d7788ac17718ed78246', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('e3e27f76ee39433abac1ac45b0c5de07', 'd383fc0e12e94174b45e663518493ff6', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('e541dac2080944ba916832b1ef5bdd55', 'bac67b0fc2a0450a9d9c4c406427414c', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('e62f4952aa404d219aa465b77eb12145', 'db9994937f2744deb248bbdf488e339b', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('ea25addadba441e4b7e47ca332ecbe66', 'd84d4b689ae64d3bb0607ef8ea51addb', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('eb4c101f8f68405294c85ca5d69b3a17', 'c6ef1e42350e4cf98e2dbe73cd7c0f91', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('eb61de537d4f4bd380d3137bd281414b', '1eeb2458ed2745658bf4b9a322f57d7a', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('f1847eae428d4dd3a66fc310f9c89d3b', '16c41343a1014716a82c6a97c42e00c0', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('f6e553bd822f406eb706137fa71e6187', 'c98652fc37ac41ab95aae44cac17e2d7', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('kangmiao', 'kangmiao', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('qiaoran', 'qiaoran', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('songjiehui', 'songjiehui', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('yangjinlin', 'yangjinlin', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('yiliqun', 'yiliqun', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('zhouhuirong', 'zhouhuirong', '0', '0', '0', '0', '0');
INSERT INTO `loan_member` VALUES ('zhouye', 'zhouye', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for member_extra_info
-- ----------------------------
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

-- ----------------------------
-- Records of member_extra_info
-- ----------------------------

-- ----------------------------
-- Table structure for member_info
-- ----------------------------
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
  `register_style` int(11) DEFAULT '3' COMMENT '方式，1线下，2Android，3IOS，4平台系统',
  `cusmemberid` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '推荐会员的id，外键自己',
  `cusmembername` varchar(355) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '推荐会员的名字，放这主要为了方便查询',
  `customer_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '如果是客户经理的话，客户经理的外键',
  `useable_score` int(11) DEFAULT '0' COMMENT '积分',
  `is_auth` int(11) DEFAULT '0' COMMENT '是否认证，0：未认证； 1：已认证',
  `payType` char(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手续费收取方式  0：代表从可用账户收取手续费 1：代表从预付账户收取手续费',
  `token` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'token 每个用户唯一，用于验证',
  `requestId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '流水号，账户注册时的流水号，唯一',
  `entity_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '自己的邀请码',
  `userId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资金存管平台用户ID',
  PRIMARY KEY (`id`),
  KEY `FK_member_info_cus` (`cusmemberid`),
  KEY `FK_member_info_customer` (`customer_id`),
  CONSTRAINT `FK_member_info_cus` FOREIGN KEY (`cusmemberid`) REFERENCES `member_info` (`id`),
  CONSTRAINT `FK_member_info_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer_manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='基本用户表 ';

-- ----------------------------
-- Records of member_info
-- ----------------------------
INSERT INTO `member_info` VALUES ('0712505779b9476e831e1880ccdb9382', 'asdf1', 'asdf1', 'panyalan', '213212123@131.com', '18', '2', '21312321', '1231324564', 'f659fd9bba99477297eb6fb0d84daf68.jpg', 'cc2decde784e497ead5b8b9c147eaf82.jpg', 'sdfsd1', '0', '2016-08-03 16:56:46', '2016-08-05 10:00:29', '0', '0', null, '0', '5', '5001', '8888', null, '0', '0', null, '1', '123', null, 'wangmazi', '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('0ddd89d9f32847fab1acc0924e0a2d65', '13623969600', null, '手机用户13623969600', null, null, null, '13623969600', null, null, null, null, '0', '2016-11-10 11:09:09', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, 'fca0e9296feb4917bd6a6ff0dd07889f', 'a78010148de243ae9f80c7d5e112bb37', '081345', null);
INSERT INTO `member_info` VALUES ('10e35b13ae2f4630962fe9b19fb30fdb', '13255696658', null, '手机用户13255696658', null, null, null, '13255696658', null, null, null, null, '0', '2016-11-10 10:50:22', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '5888665061dd42678293c6c98258c62c', '9c0c88ea71be48259d5095e58104cc27', '724320', null);
INSERT INTO `member_info` VALUES ('123', '18601920462', '二蛋子', '1235', '18601920462@163.com', '26', '1', '18601920462', null, '4c27fbd5f5e24cadbd8eed6432d28381.jpg', 'cb6b0dfcd4a444aaad1d8da0852c7d02.jsp', null, '0', '2016-08-02 11:22:04', null, null, null, null, '0', null, null, '1234566', '1235', null, '0', null, '1', 'lvbu', null, 'wangmazi', '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('124', '18901256863', 'An', '宋江', 'songjiang@163.com', '36', '0', '18901256863', null, null, null, null, '1', '2016-08-02 11:22:08', null, null, null, null, '0', null, null, null, null, null, '1', null, '2', '123', null, 'wangmazi', '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('1243c583a96140eb85a01757acda5042', '15311527711', null, '手机用户15311527711', null, null, null, '15311527711', null, null, null, null, '0', '2016-11-07 21:52:52', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '1', '0', null, 'd64a71da19d24499b55e6d30497668bc', '52368191d15b4ade92859fdc88c4dfe9', '223727', null);
INSERT INTO `member_info` VALUES ('12b6856d2f214da08bb33e7ca6b29a02', '15311527703', null, '手机用户15311527703', null, null, null, '15311527703', null, null, null, null, '0', '2016-11-04 10:01:30', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '4313cd0ffdff4a83a5e20897728d3a4b', 'b9d330820bb54816abc52b6b31a7c8fe', null, null);
INSERT INTO `member_info` VALUES ('14e012115ccd41a2a067bded58a7c0f5', '15311528762', null, '手机用户15311528762', null, null, null, '15311528762', null, null, null, null, '0', '2016-10-11 17:13:07', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('1504fb290eb54d56871dc236835114ae', '15311527712', null, '手机用户15311527712', null, null, null, '15311527712', null, null, null, null, '0', '2016-11-07 23:01:37', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '13e5d74399ea478fba172d7670e76ca7', '6fb541f1cd7a411895b099d16fd06d92', '279646', null);
INSERT INTO `member_info` VALUES ('16c41343a1014716a82c6a97c42e00c0', '13546494512', null, '手机用户13546494512', null, null, null, '13546494512', null, null, null, null, '0', '2016-11-02 17:05:09', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('188c6bb5157d4ff193e5946f95c2123c', '13923568600', null, '手机用户13923568600', null, null, null, '13923568600', null, null, null, null, '0', '2016-11-09 19:29:11', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, 'd00132ae302c42f3b190a317faa18896', '93e64f0ca3d1488db4a1a4c55e0c6129', '445122', null);
INSERT INTO `member_info` VALUES ('1d69f5e9a5dd4840b5133c26b2ba0437', '18655425536', null, '手机用户18655425536', null, null, null, '18655425536', null, null, null, null, '0', '2016-11-07 15:27:33', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '61888d3b632144ec9fd7778e041b14c7', '3796aac9616e47f58a66193133273e52', '169377', null);
INSERT INTO `member_info` VALUES ('1eeb2458ed2745658bf4b9a322f57d7a', '15311537765', null, '手机用户15311537765', null, null, null, '15311537765', null, null, null, null, '0', '2016-11-08 11:01:14', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '53fab7fa66714f2798b6b9b2d03ff1c5', 'da9a2d8452bc41eeb1123d93bafc626d', '967967', null);
INSERT INTO `member_info` VALUES ('20c673e086ea48658999905b019aa44d', '13623568500', null, '手机用户13623568500', null, null, null, '13623568500', null, null, null, null, '0', '2016-11-08 15:11:03', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '41169b294b904cda827b12bf322b4e8c', '171e7d4239e14048a7457374f3432873', '230457', null);
INSERT INTO `member_info` VALUES ('2223f8db21cb42c7a113a3639dd2cc2e', '13520679283', null, '手机用户13520679283', null, null, null, '13520679283', null, null, null, null, '0', '2016-11-09 17:54:49', null, null, null, null, '0', null, null, 'pghGvLLdQIC5tYNfA4rHKg==', null, null, '0', null, '3', null, null, null, '0', '0', null, '251f61c2b183482e95a635512b504dea', 'b12361b6da9648989db0d074d91a7432', '312441', null);
INSERT INTO `member_info` VALUES ('22a1e25a565d4d0390a4d88293f30eab', '13623468600', null, '手机用户13623468600', null, null, null, '13623468600', null, null, null, null, '0', '2016-10-25 15:38:39', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('2a52fd5d501b4f72b2e8e1966099b516', '13936988758', null, '手机用户13936988758', null, null, null, '13936988758', null, null, null, null, '0', '2016-11-10 10:35:29', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '41b832cbed9741989d90abc93e5385e8', '6a275dc6c80e46be83ed6349d7f8b4f5', '338387', null);
INSERT INTO `member_info` VALUES ('3084a2a51126491f88ab9b33a6d87e0e', '15031079277', null, '手机用户15031079277', null, null, null, '15031079277', null, null, null, null, '0', '2016-11-08 17:46:02', null, null, null, 'd0c2633d018e465d868705a9ca6b39fd.jpg', '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '7646f6fe13e94a05adede988010c84bc', '5f72bb0f4696480d88184f9bed39e555', '100699', null);
INSERT INTO `member_info` VALUES ('32bb6f2e9d5f42e4922fd54d9193c547', '13466523368', null, '手机用户13466523368', null, null, null, '13466523368', null, null, null, null, '0', '2016-11-09 20:18:06', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '7eba5272ab0749319de437b898880e8e', '3cdaa6dc475d49f994b18b1f51d81832', '614005', null);
INSERT INTO `member_info` VALUES ('34cddb18c65b439c89005394baddb244', '15311527706', null, '手机用户15311527706', null, null, null, '15311527706', null, null, null, null, '0', '2016-11-04 14:35:56', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '088bb1ca43cb4293928c0ddffed1dbe5', 'fadc569d42554e7a8754a9d655f203b4', null, null);
INSERT INTO `member_info` VALUES ('3f4891b346ca47ab96c5e7e19f33a632', '13623468900', null, '手机用户13623468900', null, null, null, '13623468900', null, null, null, null, '0', '2016-11-02 16:29:24', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('4015828c0b08485786cc10a46015558a', '15311537790', null, '手机用户15311537790', null, null, null, '15311537790', null, null, null, null, '0', '2016-11-03 17:05:48', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '2e5ee5ef1ddb4ef99510146f785f51f1', '2e5ee5ef1ddb4ef99510146f785f51f1', null, null);
INSERT INTO `member_info` VALUES ('4016883ea7a84090bf28bff14f6e1723', '13265366965', null, '手机用户13265366965', null, null, null, '13265366965', null, null, null, null, '0', '2016-11-09 20:25:02', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '77350920e5a840e592d1df14ab940f4d', 'e45f916083124af0987dd0a72fd9002b', '585108', null);
INSERT INTO `member_info` VALUES ('4833d41037974a958537f2ad976a703f', '13718132104', null, '手机用户13718132104', null, null, null, '13718132104', null, null, null, null, '0', '2016-11-10 11:40:09', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '2f64a48f9fd345318c93c1fb48669498', '234285c01a4b4e65a851198619603814', '450160', null);
INSERT INTO `member_info` VALUES ('4a8a35dde23244b086b736d224080c50', '13623469600', null, '手机用户13623469600', null, null, null, '13623469600', null, null, null, null, '0', '2016-11-02 16:35:36', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('53e8cfc503ca47e2a6781c31461b6a7c', '15311527707', null, '手机用户15311527707', null, null, null, '15311527707', null, null, null, null, '0', '2016-11-04 16:10:01', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '07c45ee794ee41629f9732ac442a7fe0', '37061cd37f3844148d467e370efb9489', null, null);
INSERT INTO `member_info` VALUES ('598751c306b94a7c8e30e1896ce9772c', '15575171262', null, '手机用户15575171262', null, null, null, '15575171262', null, null, null, null, '0', '2016-11-10 10:18:39', null, null, null, null, '0', null, null, 'O8rcTzYHPfY46Au0nkwvEg==', null, null, '0', null, '3', null, null, null, '0', '0', null, '6721c2768910426eb5cf7bf2f78415dc', '1b564015b46b4a5f872e9bc8754aed65', '218089', null);
INSERT INTO `member_info` VALUES ('624bf13fad0440b0adfc67db693951b5', '15311527798', null, '手机用户15311527798', null, null, null, '15311527798', null, null, null, null, '0', '2016-11-08 15:24:50', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, 'b3974b9bb7774fb69fb0f447a5c59939', 'dfedcee0054748fd865f453703923dc9', '935828', null);
INSERT INTO `member_info` VALUES ('67c2e90a96104653ae67513717a176a9', '15031079278', null, '手机用户15031079278', null, null, null, '15031079278', null, null, null, null, '0', '2016-10-31 21:08:04', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('688f4311e9ef463e854c6d762632add6', '15031079275', null, '手机用户15031079275', null, null, null, '15031079275', null, null, null, null, '0', '2016-10-25 15:40:54', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('731e4714b43746e38a7b513b6bad7aee', '13623659600', null, '手机用户13623659600', null, null, null, '13623659600', null, null, null, null, '0', '2016-11-10 11:11:30', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, 'c6a4c9a448d342afa7375990423175a7', 'f17f3bedad3741cf860f17fb377e4850', '280438', null);
INSERT INTO `member_info` VALUES ('775d0b36715d4d7788ac17718ed78246', '15311527708', null, '手机用户15311527708', null, null, null, '15311527708', null, null, null, null, '0', '2016-11-04 17:10:40', null, null, null, null, '0', null, null, 'ncgql%2bTwuRE=', null, null, '0', null, '3', null, null, null, '0', '0', null, 'f665634b79ab4365a208608582b41894', '7a99ab33094d4ab180620dd2ba70ef79', null, null);
INSERT INTO `member_info` VALUES ('78abb76b89ec496c9de742413f44f566', '13623466800', null, '手机用户13623466800', null, null, null, '13623466800', null, null, null, null, '0', '2016-11-02 16:38:27', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('7c6cdd66bca3437c8384fae952930d2b', 'asd', 'asdf1', 'asd', 'sdf@173.com', '18', '1', '18601920463', '123456789123456789', '15cf76648a0d4ab5b2c08ac584790619.jsp', '23af45ae9ab44867875e2e9b3e981ebc.gif', '', '0', '2016-08-11 11:30:18', '2016-08-11 11:30:18', '0', '0', null, '0', '1', '0', 'asdf', null, '0', '0', null, '3', '123', null, 'dfsdf', '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('7c8225b7f00448eeb10030aa37c29914', '13520679283', null, '手机用户13520679283', null, null, null, '13520679283', null, null, null, null, '0', '2016-11-09 17:54:48', null, null, null, null, '0', null, null, 'pghGvLLdQIC5tYNfA4rHKg==', null, null, '0', null, '3', null, null, null, '0', '0', null, '35c1531cf9f34351a22081534ad3b826', 'e5c9d82dd1b2401489fade33d13cc889', '337436', null);
INSERT INTO `member_info` VALUES ('838aa77cfa0b4e229454e3a9e8784cc9', '13623568600', null, '手机用户13623568600', null, null, null, '13623568600', null, null, null, null, '0', '2016-11-02 16:21:36', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('84986744114e4c3e82e2703e6eb9770e', '15311527763', null, '手机用户15311527763', null, null, null, '15311527763', null, null, null, null, '0', '2016-10-12 10:34:47', null, null, null, null, '0', null, null, 'j1in5uunAQc=', null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('85178b2a4737458c8d9aed8e4fdfcb06', '13525742296', null, '手机用户13525742296', null, null, null, '13525742296', null, null, null, null, '0', '2016-09-29 10:09:09', null, null, null, null, '0', null, null, 'gCrXlx/xwUvdHzLEQfhngA==', null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('8dc794425fda4c738cca7f9585da791d', '15210241129', null, '手机用户15210241129', null, null, null, '15210241129', null, null, null, null, '0', '2016-11-10 15:52:11', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, 'f9df1d5af1524212b726d0392355f06f', '16d5371bdc144c12a186c0d658e98b34', '406328', null);
INSERT INTO `member_info` VALUES ('8ebbb530091f4e3695bce8b1fef73426', '18522125254', '鎷夎繘鏉�', '手机用户18522125254', '', null, null, '18522125254', '142727199', null, null, null, '0', '2016-11-03 18:43:05', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, 'e58a9acaa52c4636a99bce3be605d081', 'e58a9acaa52c4636a99bce3be605d081', null, null);
INSERT INTO `member_info` VALUES ('931ca70826584ff6bd83315c38557317', '13623968900', null, '手机用户13623968900', null, null, null, '13623968900', null, null, null, null, '0', '2016-11-02 16:39:45', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('93645998e65f48d98695fe5199b21df8', '15311527766', null, '手机用户15311527766', null, null, null, '15311527766', null, null, null, null, '0', '2016-10-24 16:43:18', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('9aada9a4175041b28f16ca68ec3a638e', '13936523256', null, '手机用户13936523256', null, null, null, '13936523256', null, null, null, null, '0', '2016-11-10 10:25:36', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '1cf26e32c7d64cdd98933c755161e44b', '2a5dbd3f2d904889b85c836d4512ae86', '175550', null);
INSERT INTO `member_info` VALUES ('b4961a25ab5948abba3d8852c9c6f5a5', 'asdfasdf', 'sdf', 'adf', '', '', '1', '87978', null, 'af7b7ccedcd94758896257f33e0f0138.jpg', '7a2b4de709414672b408996c181cdb9f.jpg', '', '0', '2016-08-04 11:12:16', '2016-08-04 11:12:16', '0', '0', null, '0', '1', '', 'sdf', null, null, '0', null, '3', '123', null, 'wangmazi', '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('b9f0f8980b964743ad814954a013be3e', '15311527769', null, '手机用户15311527769', null, null, null, '15311527769', null, null, null, null, '0', '2016-11-03 17:04:28', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '208657309f15462bae5821ce0050611c', '208657309f15462bae5821ce0050611c', null, null);
INSERT INTO `member_info` VALUES ('bac67b0fc2a0450a9d9c4c406427414c', '15311527711', null, '手机用户15311527711', null, null, null, '15311527711', null, null, null, null, '0', '2016-11-07 21:52:50', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, 'cbc749c040514174ab4a007dd94374bc', 'a034299eeb1b4844b35f31d4c8dd486d', '218606', null);
INSERT INTO `member_info` VALUES ('bcce5fe7a48a4e4ba3126bf30a9ac7c5', '13923568603', null, '手机用户13923568603', null, null, null, '13923568603', null, null, null, null, '0', '2016-11-09 19:51:58', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, 'acd41a8bd59d4b4a9425166b9898ca1e', 'aee476331d3d421a86dc593568e2b6aa', '533396', null);
INSERT INTO `member_info` VALUES ('bdb2d460baa64b26b309c9f9383d23d2', '18611222236', null, '手机用户18611222236', null, null, null, '18611222236', null, null, null, null, '0', '2016-11-02 16:15:57', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('c4613d5d20184ac7a73d24321f97574f', '15311527702', null, '手机用户15311527702', null, null, null, '15311527702', null, null, null, null, '0', '2016-11-03 21:19:28', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '2', '0', null, '62ea51c086cf4e52a25343568cb8785d', '62ea51c086cf4e52a25343568cb8785d', null, null);
INSERT INTO `member_info` VALUES ('c4c972c8ac164f6cbd47513db25337fc', '18655422223', null, '手机用户18655422223', null, null, null, '18655422223', null, null, null, null, '0', '2016-11-09 19:21:01', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '529843a5f3f647bc984de9ceeb593e71', '973c2639fc35473f9599944081918c39', '539167', null);
INSERT INTO `member_info` VALUES ('c534c617d6964b76a1505f57de951d43', '15311527768', null, '手机用户15311527768', null, null, null, '15311527768', null, null, null, null, '0', '2016-11-03 16:56:14', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, 'c40f5b19911c49c484497e255ae38049', 'c40f5b19911c49c484497e255ae38049', null, null);
INSERT INTO `member_info` VALUES ('c6ef1e42350e4cf98e2dbe73cd7c0f91', '15311527409', null, '手机用户15311527409', null, null, null, '15311527409', null, null, null, null, '0', '2016-11-07 14:42:35', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, 'dbb30028f87e446d86ebe291b95dd4a3', 'd3563d92a61e4c5d9feddeac7285027f', null, null);
INSERT INTO `member_info` VALUES ('c8eb824f8e2c4976b9e816a2b6c85d60', 'Allen', '王丹丹', '', '', '18', '1', '', '', 'c907d7bc8e0c48248be3070ea43aa4cf.jsp', 'af96323a3f3c4b7297fa43508124efa4.png', '', '0', '2016-08-11 13:59:21', '2016-08-11 13:59:21', '0', '0', null, '0', '1', '0', '', null, null, '0', null, '4', '123', null, 'wangmazi', '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('c98652fc37ac41ab95aae44cac17e2d7', '15311527713', null, '手机用户15311527713', null, null, null, '15311527713', null, null, null, null, '0', '2016-11-08 09:57:36', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, 'fe8035442b7145b3849d8f1a3d272b9f', '354a087d06984bfc8e35f10c0ab1aa12', '071020', null);
INSERT INTO `member_info` VALUES ('cb470be575744299bdfa9d7be2c54e5e', '18612762588', null, '手机用户18612762588', null, null, null, '18612762588', null, null, null, null, '0', '2016-11-10 16:00:36', null, null, null, null, '0', null, null, 'OZFjLB4s07KTZhDhXnQ1yA==', null, null, '0', null, '3', null, null, null, '0', '0', null, 'e1e94726469845d591d6cfa57a05a729', 'c8cb19ecc2fe4b2aba8ed8f788ada0f0', '757195', null);
INSERT INTO `member_info` VALUES ('chenhao', '13655654123', '陈浩', '小陈', '13655654123@qq.com', '32', '1', '13655654123', '120541198505064422', null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('d2eef99f254a4bd1b00d8609dfc86059', '15311527762', '闃挎瘺', '手机用户15311527762', '409779813@qq.com', null, null, '15311527762', '130723199107153211', null, null, null, '0', '2016-09-29 10:46:44', null, null, null, '8efef0bb88b048ba996340f5e5ee79f7.jpg', '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('d383fc0e12e94174b45e663518493ff6', '13525742285', null, '手机用户13525742285', null, null, null, '13525742285', null, null, null, null, '0', '2016-11-07 14:54:47', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', 'songjiang', null, null, '0', '0', null, 'a2f2fbbdde3b468ca79117c9be996391', '4aa845e4fcec41cda5074ef64e1d80f3', '710726', null);
INSERT INTO `member_info` VALUES ('d46b080bffae4c88b5baf3877226c0a4', '13996523694', null, '手机用户13996523694', null, null, null, '13996523694', null, null, null, null, '0', '2016-11-10 10:37:36', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, 'd08a6beaf6e74f6db0abeb4f3b1556e0', '723ff3e951ec425c9809f94ab838905f', '353849', null);
INSERT INTO `member_info` VALUES ('d84d4b689ae64d3bb0607ef8ea51addb', '13653256569', null, '手机用户13653256569', null, null, null, '13653256569', null, null, null, null, '0', '2016-11-10 10:31:12', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '948d9e3deeed44f9ae8baca9f98f759d', '62861a643d694c2495a6de25e5187f10', '885614', null);
INSERT INTO `member_info` VALUES ('d9ad4cebbe5b4514b51e4083f4478fdf', '15311527704', null, '手机用户15311527704', null, null, null, '15311527704', null, null, null, null, '0', '2016-11-04 11:33:43', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '0cea0a547ecb4b13a7c4a57bba0a1f58', '2bd0e703159d47eb8ae32a41fd80554c', null, null);
INSERT INTO `member_info` VALUES ('da5d2a3f259c4a2994ba22ab8b9d2d5f', '15311527765', '韩玉昆', '好想', null, null, null, '15311527765', '510709198408248248', null, null, null, '0', '2016-10-12 14:44:47', null, null, null, '2d54e643ea8c4e1c871da6826aaded86.jpg', '0', null, null, 'SiM83CpjqIc=', 'SiM83CpjqIc=', null, '0', null, '3', null, null, null, '36', '0', null, 'c40f5b19911c49c484497e255ae3806g', 'c40f5b19911c49c484497e255ae3806g', '239805', null);
INSERT INTO `member_info` VALUES ('db9994937f2744deb248bbdf488e339b', '13625366565', null, '手机用户13625366565', null, null, null, '13625366565', null, null, null, null, '0', '2016-11-09 20:22:56', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '71c0c565561a43a7ba905862a732c1ac', '8f93f1292d6a4369aa9e77b038563a92', '489160', null);
INSERT INTO `member_info` VALUES ('dba4129c95bb4b7b88a69be29f0a9cc4', '13646328600', null, '手机用户13646328600', null, null, null, '13646328600', null, null, null, null, '0', '2016-11-10 11:18:53', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '4db5f2c1af1042a597716c80a4ec0ee4', '0f7306f158964d4a864bcc40866b4a51', '799893', null);
INSERT INTO `member_info` VALUES ('dce420bff80945c18c1c3bf96db3f480', '18699872236', null, '手机用户18699872236', null, null, null, '18699872236', null, null, null, null, '0', '2016-11-09 20:08:37', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '021e65bb3b8547f8aa60fcfbfd4e6892', 'f6ab936a6d694a378fe2897c195ed640', '280733', null);
INSERT INTO `member_info` VALUES ('dcf175c7bc8e433ab80ec85f06bae842', '15311527712', null, '手机用户15311527712', null, null, null, '15311527712', null, null, null, null, '0', '2016-11-07 23:01:38', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '9890a24e75c247e8b3dfa3471d892768', 'd2c99e14a191417ca8b0f1aecab6323e', '876372', null);
INSERT INTO `member_info` VALUES ('e84121690e6e4d648e5ef821fa5e8a8a', '15311177100', null, '手机用户15311177100', null, null, null, '15311177100', null, null, null, null, '0', '2016-11-10 15:13:35', null, null, null, null, '0', null, null, 'OZFjLB4s07KTZhDhXnQ1yA==', null, null, '0', null, '3', null, null, null, '0', '0', null, '41e2f915e9c34e398953e01d094ecfed', 'f3fc1f912f604a85a6f06238bfa3029e', '398706', null);
INSERT INTO `member_info` VALUES ('f31d3a3b5ad2411aa6eeb5a22e9d3864', '18923468600', '嵩岩', '手机用户18923468600', '275855510@qq.com', null, null, '18923468600', '142727199221214521', null, null, null, '0', '2016-11-02 17:13:09', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('f394ca2c7ae948788f3ccda8a73e374b', '18611222254', null, '手机用户18611222254', null, null, null, '18611222254', null, null, null, null, '0', '2016-10-25 15:42:07', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('f394ca2c7ae948788f4yuda8a73e374b', '18601920463', '18601920463', '18601920463', '18601920463@163.com', null, null, '18601920463', null, null, null, null, '0', '2016-11-04 16:50:27', '2016-11-04 16:50:30', null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '50eaf9662fd9bc868bb315954462f178', '50eaf9662fd9bc868bb315954462f178', null, null);
INSERT INTO `member_info` VALUES ('f8768de73be3425b92c5478d1607684f', '18655363966', null, '手机用户18655363966', null, null, null, '18655363966', null, null, null, null, '0', '2016-11-10 10:55:57', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, 'cca3130c31e04fc3bacbf6fac510a363', 'd5d6bcac67984283acfdaf67f6ab385f', '153672', null);
INSERT INTO `member_info` VALUES ('facbf724a11b4f23a37f4c91585e3b96', '15311527701', null, '手机用户15311527701', null, null, null, '15311527701', null, null, null, null, '0', '2016-11-03 17:34:23', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '3', null, null, null, '0', '0', null, '50eaf9662fd946868bb3154d4462f178', '50eaf9662fd946868bb3154d4462f178', null, null);
INSERT INTO `member_info` VALUES ('ff8c9e1c54444f038634fec36e98ba0d', '15636326585', null, '手机用户15636326585', null, null, null, '15636326585', null, null, null, null, '0', '2016-11-09 23:57:22', null, null, null, null, '0', null, null, 'SiM83CpjqIc=', null, null, '0', null, '2', null, null, null, '0', '0', null, '2826c5f2a91b40df82f76c07d6ed208c', 'ec89e2b991e44cd0b9daf05cebd8b7d2', '600871', null);
INSERT INTO `member_info` VALUES ('kangmiao', '15600458856', '康淼', '康康', '15600458856@qq.com', '30', '1', '15600458856', '510153198805064426', null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('lvbu', '13811294856', '吕布', '小白脸', 'lvbu@163.com', '19', '1', '13811294856', '352261945565622444', 'a3fe3c12cfd34ab8ba9ca1dcb0f2e6ff.jsp', 'bd81f81b656a4fa7b344a41615bdff97.png', '', '0', '2016-08-05 11:01:32', '2016-08-05 11:01:32', '0', '0', null, '1', '3', '100', 'j1in5uunAQc=', null, '0', '0', null, '2', '123', null, 'wangmazi', '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('qiaoran', '13812201561', '瞧冉', '然而再生', '13812201561@qq.com', '25', '1', '13812201561', '451521198905304561', null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('songjiang', '15895594965', '宋江', '及时雨', 'songjiang@sina.com', '42', '1', '15600465150', '510821144495515151', '78dbd842027b40ab81d95c2687dee078.jpg', null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '1', '123', null, 'wangmazi', '0', '0', null, null, null, '123456', null);
INSERT INTO `member_info` VALUES ('songjiehui', '15892657856', '宋杰辉', '辉', '15892657856@qq.com', '27', '1', '15892657856', '441561197808241156', null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('yangjinlin', '18500451235', '杨锦林', '小林子', '18500451235@qq.com', '29', '1', '18500451235', '504571198809154562', null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('yiliqun', '13508264451', '易莉群', '莉花容月貌', '13508264451@qq.com', '56', '1', '13508214451', '320451195304254456', null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('zhouhuirong', '15756442356', '周惠荣', '阿慧', '15756442356@qq.com', '52', '1', '15756442356', '210874195504159845', null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `member_info` VALUES ('zhouye', '15704541156', '周业', '周全福', '15704541156@qq.com', '28', '1', '15704541156', '201564198604158456', null, null, null, '0', null, null, null, null, null, '0', null, null, null, null, null, '0', null, '3', null, null, null, '0', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for member_invest_ticket
-- ----------------------------
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

-- ----------------------------
-- Records of member_invest_ticket
-- ----------------------------
INSERT INTO `member_invest_ticket` VALUES ('123', '123', '5bba590159e148dc8fea0d8dec3bf06c', '爱上', '0');
INSERT INTO `member_invest_ticket` VALUES ('125', '124', 'c8308d5ac410400f91b245356ae0af20', '阿萨德', '1');

-- ----------------------------
-- Table structure for member_ticket_record
-- ----------------------------
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

-- ----------------------------
-- Records of member_ticket_record
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
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

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('123', '123', '0712505779b9476e831e1880ccdb9382', '2016-08-18 15:37:44', '你说我美不美', '美的不要不要的', '1', '1');
INSERT INTO `message` VALUES ('124568', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '123', '2016-10-14 11:00:00', 'asd', 'asd', '1', '0');
INSERT INTO `message` VALUES ('13245', '123', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-10-14 10:50:53', '你说我美不美', '美的不要不要的', '0', '1');
INSERT INTO `message` VALUES ('89565', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '124', '2016-10-14 15:23:53', 'sd', 'tttttttttt', '1', '0');

-- ----------------------------
-- Table structure for news_information
-- ----------------------------
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

-- ----------------------------
-- Records of news_information
-- ----------------------------
INSERT INTO `news_information` VALUES ('123456', '你说今天发生了什么', '2016-10-17 16:55:33', '', null, '1');
INSERT INTO `news_information` VALUES ('28b6524e180f48e9afe6a0f3ffb2ea28', 'asdfasdfasdf', '2016-10-17 11:54:47', null, '0', '1');
INSERT INTO `news_information` VALUES ('45687', '王宝强', '2016-10-13 16:55:52', null, null, '0');
INSERT INTO `news_information` VALUES ('5c98506a7b714ca8a34ed2d443902107', 'asdfsdfsdf', '2016-10-17 11:40:50', '0f466af55fc5463a868ca7bc51ad121b.jpg', '0', '1');
INSERT INTO `news_information` VALUES ('96e7efa4739641d38460ff16717aa26c', '甯屾媺閲屽彂琛ㄨ触閫夋紨璇�鍒嗘瀽锛氬ス涓轰綍杈撶粰浜嗙壒鏈楁櫘', '2016-11-10 14:53:15', null, '0', '0');
INSERT INTO `news_information` VALUES ('aac9a646b9ba41be86daa53206a2cdb5', '阿什福', '2016-11-10 14:56:23', null, '0', '1');

-- ----------------------------
-- Table structure for notification
-- ----------------------------
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

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES ('b181f00986794754921197e7a40969d5', '2', '2016-11-10 01:59:35', '牛奔贷送福利', '喜迎双十一，牛奔贷福利多，充多少送多少', '2016-11-10 01:59:38', '2016-11-14 01:59:41', '0');

-- ----------------------------
-- Table structure for notification_readed
-- ----------------------------
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

-- ----------------------------
-- Records of notification_readed
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
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

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('107188fd6a1b48ea9c979d3ec7335e8b', '1', '荣春宝', '荣春宝', '', '', '0.08', null, '', '', '1', '1', '1', '', '', '', '0', null, '2016-11-09 17:56:35', null, '0', '90');
INSERT INTO `product` VALUES ('13bdcf0612844656addbd87d22e2c233', '1', '金秋宝', '金秋宝', '', '', '0.1', null, '', '', '1', '1', '1', '', '', '', '0', '2016-11-09 17:51:36', '2016-11-09 17:51:36', '0', '0', '180');
INSERT INTO `product` VALUES ('19326a95b16441fe838aa6d4d5fcf87a', '1', '天天宝', '天天宝', '', '', '0.12', null, '', '', '1', '1', '1', '', '', '', '1', '2016-11-10 15:15:34', '2016-11-10 15:15:34', '0', '0', '180');
INSERT INTO `product` VALUES ('7b4536a78aee47cb859d7a999a1462b7', '1', '盛夏宝', '盛夏宝', '', '', '0.09', null, '', '', '1', '1', '1', '', '', '', '0', null, '2016-11-09 17:56:45', null, '0', '180');
INSERT INTO `product` VALUES ('832cc648169f44ee80cc86a743a35796', '1', '双月宝', '双月宝', '', '', '0.08', null, '', '', '1', '1', '1', '', '', '', '0', '2016-11-09 17:54:24', '2016-11-09 17:54:24', '0', '0', '60');
INSERT INTO `product` VALUES ('a0fe5c3356fa4b6cb09ec03241ebd6a0', '1', '元月宝', '元月宝', '', '短期高收益产品', '0.05', null, '', '', '1', '1', '1', '', '', '', '0', null, '2016-11-09 17:52:36', null, '0', '30');
INSERT INTO `product` VALUES ('c519dc61791746ef92ed9618b7a9adee', '1', '隆冬宝', '隆冬宝', '', '', '0.12', null, '', '', '1', '1', '1', '', '', '', '0', '2016-11-09 17:52:08', '2016-11-09 17:52:08', '0', '0', '360');

-- ----------------------------
-- Table structure for scale
-- ----------------------------
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
  `giftFlag` char(10) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '是否使用红包 0不使用红包，1使用红包',
  PRIMARY KEY (`id`),
  KEY `FK_scale_product` (`product_id`),
  KEY `FK_scale` (`invest_list`),
  CONSTRAINT `FK_scale` FOREIGN KEY (`invest_list`) REFERENCES `invest_list` (`id`),
  CONSTRAINT `FK_scale_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='投资标列表表';

-- ----------------------------
-- Records of scale
-- ----------------------------
INSERT INTO `scale` VALUES ('02a655d953394d94a711facc3d3bb98f', 'c519dc61791746ef92ed9618b7a9adee', '隆冬宝', '2016-11-10', '2016-11-30', '长期稳定产品', null, null, '0.1', '0.02', '10000000', '1', '', '1', '0', null, '0', '0', '0', '0', '0', '0', '0', null, '0');
INSERT INTO `scale` VALUES ('12ee0ed7321b4938ab02b5630cc305e0', '7b4536a78aee47cb859d7a999a1462b7', '盛夏宝', '2016-11-10', '2016-11-30', '中长期产品', null, null, '0.08', '0.01', '10000000', '1', '', '1', '0', null, '0', '0', '0', '0', '0', '0', '0', null, '0');
INSERT INTO `scale` VALUES ('75ec5a9615bb409585c36c2568ab410b', 'a0fe5c3356fa4b6cb09ec03241ebd6a0', '元月宝', '2016-11-10', '2016-11-30', '单月短期标', null, null, '0.05', '0.05', '10000', '1', '', '1', '0', null, '0', '0', '0', '0', '0', '0', '0', null, '0');
INSERT INTO `scale` VALUES ('9c33ab2a1b10455b80074f464783b7ec', '832cc648169f44ee80cc86a743a35796', '双月宝', '2016-11-10', '2016-11-30', '中期产品', null, null, '0.05', '0.05', '100000', '1', '', '1', '0', null, '0', '0', '0', '0', '0', '0', '0', null, '0');
INSERT INTO `scale` VALUES ('f5ab54b682a74282856a494f78d6ffd1', '107188fd6a1b48ea9c979d3ec7335e8b', '荣春宝', '2016-11-10', '2016-11-30', '中期产品', null, null, '0.06', '0.02', '1000000', '1', '', '1', '0', null, '0', '0', '0', '0', '0', '0', '0', null, '0');

-- ----------------------------
-- Table structure for scale_loan_list
-- ----------------------------
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

-- ----------------------------
-- Records of scale_loan_list
-- ----------------------------

-- ----------------------------
-- Table structure for score_list
-- ----------------------------
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

-- ----------------------------
-- Records of score_list
-- ----------------------------

-- ----------------------------
-- Table structure for sign
-- ----------------------------
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

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('16efba944f004c8cb968aa34d39dbdc9', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-11-08 00:16:47', '3', '5');
INSERT INTO `sign` VALUES ('34b29806e90b4096a63cdfe2eeee350b', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-11-07 23:24:30', '2', '5');
INSERT INTO `sign` VALUES ('46a0a88229404b79b98ba3498b6e2679', '1243c583a96140eb85a01757acda5042', '2016-11-08 09:53:27', '1', '1');
INSERT INTO `sign` VALUES ('564a46e355e84e848008874351f1ce36', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-11-06 21:44:17', '1', '1');
INSERT INTO `sign` VALUES ('5b7fac2455a64ab99f3eee1558988284', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-11-10 11:39:29', '5', '5');
INSERT INTO `sign` VALUES ('6472619a36e94dc0838ad8b8ba2b2059', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-11-02 18:09:15', '1', '1');
INSERT INTO `sign` VALUES ('665f32e806f6437c83a46ed3a45db83c', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-10-30 13:30:56', '1', '1');
INSERT INTO `sign` VALUES ('6df7733c086f496eaafb5a19f31f9214', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-10-24 23:40:09', '1', '1');
INSERT INTO `sign` VALUES ('900c1d839578466189e48ce19f078d00', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-10-31 18:29:25', '2', '5');
INSERT INTO `sign` VALUES ('9b4eede923994d839e1e0e7aa94a7de2', 'c4613d5d20184ac7a73d24321f97574f', '2016-11-03 22:11:03', '1', '1');
INSERT INTO `sign` VALUES ('b55824c396584f469074f9717f84fb17', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-11-10 11:39:29', '5', '5');
INSERT INTO `sign` VALUES ('b896d8f14d2a42afb2affc930cab1636', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-11-06 21:44:18', '1', '1');
INSERT INTO `sign` VALUES ('c4dcb3ecb2b544f4a8526251a8180f9d', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-11-09 07:57:01', '4', '5');
INSERT INTO `sign` VALUES ('c9c3fbb9188d499eb85724078c92f2a5', 'c4613d5d20184ac7a73d24321f97574f', '2016-11-03 22:11:03', '1', '1');
INSERT INTO `sign` VALUES ('fcd291d9d8854ae48b9fce0ae05e2308', 'da5d2a3f259c4a2994ba22ab8b9d2d5f', '2016-10-27 22:56:53', '1', '1');

-- ----------------------------
-- Table structure for sms
-- ----------------------------
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

-- ----------------------------
-- Records of sms
-- ----------------------------
INSERT INTO `sms` VALUES ('123456', '18601920463', '你太帅了', '2016-09-19 17:33:28', '4', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for sms_verification_code
-- ----------------------------
DROP TABLE IF EXISTS `sms_verification_code`;
CREATE TABLE `sms_verification_code` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号',
  `v_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '短信验证码',
  `startTime` datetime NOT NULL COMMENT '开始时间',
  `endTime` datetime NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sms_verification_code
-- ----------------------------
INSERT INTO `sms_verification_code` VALUES ('013ca040b306499bade486d65c875ef5', 'bdSOrsTIkKxaoGcg27bBqQ==', '362275', '2016-11-07 21:51:47', '2016-11-07 22:01:47');
INSERT INTO `sms_verification_code` VALUES ('07ead38c776f45d5a960ed79cf5c21ed', 'bdSOrsTIkKw4wLyRW6az0A==', '389570', '2016-11-08 14:51:05', '2016-11-08 15:01:05');
INSERT INTO `sms_verification_code` VALUES ('12b91a2ad6d342099e5acf0224b170b3', '13811294832', '563473', '2016-11-07 22:53:54', '2016-11-07 23:03:54');
INSERT INTO `sms_verification_code` VALUES ('1487ab95fcd543a585608216ea968f8a', 'hwpdA6X+6m6VdWNmHIsFrw==', '535208', '2016-11-08 15:10:59', '2016-11-08 15:20:59');
INSERT INTO `sms_verification_code` VALUES ('29c53e768f794caa8b2bbc56b57787d6', '2kj0QBgUd9XZkn7DvQz9xg==', '563576', '2016-11-10 15:13:04', '2016-11-10 15:23:04');
INSERT INTO `sms_verification_code` VALUES ('2ea89ec0bd2243d5afd7e01093fc77fb', 't0xNHAvZT+GvP1R3SZEskg==', '675352', '2016-11-09 19:19:36', '2016-11-09 19:29:36');
INSERT INTO `sms_verification_code` VALUES ('35d1252bb65a4c83aa7c793bc07a2ec5', 'IGUgW5WXFU2zbzc6+3xF+g==', '150569', '2016-11-09 19:13:42', '2016-11-09 19:23:42');
INSERT INTO `sms_verification_code` VALUES ('3731b2c8085244b29ac6d66ac22c0c74', 'wB4fs5lGBDlno4bFBz1nKg==', '209978', '2016-11-10 10:18:13', '2016-11-10 10:28:13');
INSERT INTO `sms_verification_code` VALUES ('3f34c5b379ca43a0bbef0fcbbeab2b09', 'bdSOrsTIkKxUqe9XlNv7xw==', '706560', '2016-11-08 15:24:40', '2016-11-08 15:34:40');
INSERT INTO `sms_verification_code` VALUES ('4cd7eba1a5534fc9a0449671ee326490', 'bdSOrsTIkKwlolePHQMkDA==', '981650', '2016-11-08 10:23:13', '2016-11-08 10:33:13');
INSERT INTO `sms_verification_code` VALUES ('4ed6bcb848134be18007be3ef58399cc', 'VD22Ugt7Jf%2bNc5ugdQMOKw==', '480637', '2016-11-09 17:54:28', '2016-11-09 18:04:28');
INSERT INTO `sms_verification_code` VALUES ('5df0a631643a4f60ae0afce02154ae68', 'f/m26YDiELYoQINLU0kqhg==', '631389', '2016-11-09 17:54:46', '2016-11-09 18:04:46');
INSERT INTO `sms_verification_code` VALUES ('5f2e61f4e3a34ea0a6b8086113f1c617', 'ZDjLPiJmTCfZq54nuzmSGA==', '305959', '2016-11-10 15:51:53', '2016-11-10 16:01:53');
INSERT INTO `sms_verification_code` VALUES ('657b3c624bb94dfa97bfd07d1ca50b3a', 'f/m26YDiELYIR4LvLz4Akw==', '089489', '2016-11-10 14:51:07', '2016-11-10 15:01:07');
INSERT INTO `sms_verification_code` VALUES ('6885fb763e914b289e12630cc7583a66', 'bdSOrsTIkKz7eDX3Bxnb5A==', '553950', '2016-11-07 23:01:08', '2016-11-07 23:11:08');
INSERT INTO `sms_verification_code` VALUES ('7486102fd13a42c0a604e1354991327a', 'mKrodR6BYPdXBP167rR7GA==', '285581', '2016-11-09 17:50:44', '2016-11-09 18:00:44');
INSERT INTO `sms_verification_code` VALUES ('84274877f77f44418105aa92cc2908c8', 'bdSOrsTIkKzA4IWhC5siBQ==', '300599', '2016-11-07 14:35:06', '2016-11-07 14:45:06');
INSERT INTO `sms_verification_code` VALUES ('8948f36ca8564040ab1a6f0c181ec2a4', 'bdSOrsTIkKy4NetA%2bLY1Ow==', '792369', '2016-11-07 14:38:37', '2016-11-07 14:38:47');
INSERT INTO `sms_verification_code` VALUES ('8fe0e7ed695f4840b6d3d32cc0084df3', '15311177100==', '694647', '2016-11-09 17:38:47', '2016-11-09 17:48:47');
INSERT INTO `sms_verification_code` VALUES ('956dbd3d414e4c10895f240f99557a0c', 'xoUo 1np5pOwa39ElhecHg==', '204273', '2016-11-07 22:58:33', '2016-11-07 22:58:43');
INSERT INTO `sms_verification_code` VALUES ('b5668528a76642abaa49d3b3ade90413', 'qAJU8nkHydXUWu4IKkjNCA==', '508524', '2016-11-10 15:59:38', '2016-11-10 16:09:38');
INSERT INTO `sms_verification_code` VALUES ('c02b2ba0f86244cc82d784eee0a7c587', 'xoUo+1np5pOwa39ElhecHg==', '382454', '2016-11-07 23:04:22', '2016-11-07 23:04:32');
INSERT INTO `sms_verification_code` VALUES ('d95a82a7fbd049aa85145da6db43841b', '18601920463', '008822', '2016-10-25 09:13:58', '2016-10-25 09:14:08');
INSERT INTO `sms_verification_code` VALUES ('e38d3c056a3b4dfbb484499443c59fe7', 'IGUgW5WXFU2zbzc6%2b3xF%2bg==', '072495', '2016-11-10 15:06:20', '2016-11-10 15:16:20');
INSERT INTO `sms_verification_code` VALUES ('e6e434edff5342bb957d763ad38232c8', 'fKd7bnlvQDmNiXsye8sX9Q==', '213514', '2016-10-31 21:07:33', '2016-10-31 21:07:43');
INSERT INTO `sms_verification_code` VALUES ('ec0dfb9164694af7b074124d0961d214', 'fKd7bnlvQDkheG1r8sJITQ==', '599860', '2016-11-09 19:31:05', '2016-11-09 19:41:05');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
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

-- ----------------------------
-- Records of stock
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
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

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
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

-- ----------------------------
-- Records of sys_power
-- ----------------------------
INSERT INTO `sys_power` VALUES ('006fd11739ad4bff9e2adbf33234aff1', '合同', 'contract!gotolist.do', '099b882f3f5749079af62aac20400782', '1', '2016-09-19 10:00:08', 'contract', '');
INSERT INTO `sys_power` VALUES ('020511d9903d4e61800cb6accd21b4dd', '好友列表', 'friends!gotolist.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-23 17:22:08', 'icon-group_link', '');
INSERT INTO `sys_power` VALUES ('06c2e4bc383949b584e7759a1e20f354', '数据中心', '/', 'syspowers', '2', '2016-08-11 17:28:44', 'icon-databases', '数据中心');
INSERT INTO `sys_power` VALUES ('07a80a029b1548d79ab5ad3544f39750', '绑定银行卡', 'bindcard!gotoBindInfoList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-15 14:51:40', 'bank_card_16', '');
INSERT INTO `sys_power` VALUES ('099b882f3f5749079af62aac20400782', '业务中心', '/', 'syspowers', '1', '2016-07-29 14:45:39', 'icon-rosette', '');
INSERT INTO `sys_power` VALUES ('0f5918b3fd894e3498dfd07b5834afbf', '理财券使用记录', 'memberticketrecord!gotoMemeberTicketRecordList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-16 11:31:06', 'icon-bricks', '');
INSERT INTO `sys_power` VALUES ('1e72241d8f5144299542af5cf0d173ac', '用户理财券', 'memberinvestticket!gotoList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-18 17:28:40', 'ticket_add', '');
INSERT INTO `sys_power` VALUES ('303bff33608c46478eafe0b2720d949e', '用户管理', 'sysuser!showUser.do', '7518b13cf6da4e5580aeaa7decfc71bb', '1', '2016-07-29 14:41:58', 'icon-people', '用户管理');
INSERT INTO `sys_power` VALUES ('354ce4e52bbb46559a6fe5ec2a5e9cf7', '客户经理', 'customermanager!openDialog.do', '099b882f3f5749079af62aac20400782', '4', '2016-08-09 15:00:38', 'icon-folder_user', '客户经理');
INSERT INTO `sys_power` VALUES ('4b7526ebcba3439680afac7c2ea40ea4', '消息管理', 'message!gotoMessageList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-18 15:16:26', 'icon-comments', '');
INSERT INTO `sys_power` VALUES ('4bda189386834431a3fbb7360e40d784', '资金放款还款记录', 'billloan!gotoList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-24 15:38:33', 'icon-medal_bronze_3', '');
INSERT INTO `sys_power` VALUES ('4c4e61b29d8946308e1eb29d993a5337', '测试', 'test!gotolist.do', '06c2e4bc383949b584e7759a1e20f354', '1', '2016-10-25 09:49:23', 'ticket_add', '');
INSERT INTO `sys_power` VALUES ('56a7ae4db858460ab5e0198bbe890806', '我的客户', 'memberinfo!gotoMyMemberInfoList.do', 'e954e75f79504d6e81e4a3e0392872a9', '3', '2016-09-21 14:52:38', 'icon-group', '');
INSERT INTO `sys_power` VALUES ('5cb0b2b482ed43c9bfd831aaf2eb5977', '通知', 'notification!gotoList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-19 11:12:33', 'icon-bell', '');
INSERT INTO `sys_power` VALUES ('5cd915c40fa348dd97376b5d1fec9c5a', '理财标', 'scale!openDialog.do', '099b882f3f5749079af62aac20400782', '8', '2016-08-16 16:23:48', 'icon-application_side_list', '');
INSERT INTO `sys_power` VALUES ('69c4c017afa84aa5b36ea8fed457db8b', '理财列表', 'investlist!openDialog.do', '06c2e4bc383949b584e7759a1e20f354', '2', '2016-08-15 16:58:25', 'icon-coins', '理财列表');
INSERT INTO `sys_power` VALUES ('7518b13cf6da4e5580aeaa7decfc71bb', '系统设置', '/', 'syspowers', '100', '2016-07-29 14:41:35', 'icon-2012080404391', '系统用户权限角色的设置');
INSERT INTO `sys_power` VALUES ('76fd35f10c3d47c0aa8db7aa91311384', '短信', 'sms!showSMS.do', '099b882f3f5749079af62aac20400782', '1', '2016-09-19 17:32:50', 'icon-user_comment', '短信列表');
INSERT INTO `sys_power` VALUES ('8f24607739fe4b04a24618cd0742c585', '理财资金记录', 'billinvest!gotoList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-24 11:18:14', 'icon-medal_gold_2', '');
INSERT INTO `sys_power` VALUES ('a007affa6bca4c20adc45e2ca5550bf8', '角色管理', 'sysrole!showRole.do', '7518b13cf6da4e5580aeaa7decfc71bb', '2', '2016-07-29 14:45:16', 'icon-status_be_right_back', '角色管理');
INSERT INTO `sys_power` VALUES ('a59137b1774f4839b9bb89b40d51cf87', '产品列表', 'investpro!investProList.do', '099b882f3f5749079af62aac20400782', '2', '2016-07-29 15:48:35', 'icon-tag_green', '产品的列表');
INSERT INTO `sys_power` VALUES ('ab36a5f309464b9d8059f03dfa2c9750', '客户管理', 'memberinfo!gotoMemberInfoList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-11 15:37:06', 'icon-group_gear', '');
INSERT INTO `sys_power` VALUES ('ad8fddabfe2a4928bd2e9184e730b14d', '会员理财列表', 'investlist!openDialog.do?isCustomer=1', 'e954e75f79504d6e81e4a3e0392872a9', '1', '2016-09-20 17:37:13', 'icon-coins', '');
INSERT INTO `sys_power` VALUES ('bd0ed272880d4e21b95edf6f318e6fed', '会员借款列表', 'loanlist!openDialog.do?isCustomer=1', 'e954e75f79504d6e81e4a3e0392872a9', '2', '2016-09-20 17:36:56', 'icon-award_star_bronze_2', '');
INSERT INTO `sys_power` VALUES ('d40f992523ed4ff6a70ab2e05b1d4cde', '库存对账单', 'stock!openDialog.do', '099b882f3f5749079af62aac20400782', '9', '2016-07-26 11:42:06', 'icon-201208041220', '');
INSERT INTO `sys_power` VALUES ('db53f973105145fc91c6dc8ca761b6f5', '借款列表', 'loanlist!openDialog.do', '06c2e4bc383949b584e7759a1e20f354', '1', '2016-08-11 17:31:08', 'icon-award_star_bronze_2', '借款列表');
INSERT INTO `sys_power` VALUES ('de0b897358bb4e2f8e983093a20a889f', '理财券管理', 'investticket!gotoInvestTicketList.do', '099b882f3f5749079af62aac20400782', '1', '2016-08-16 14:47:40', 'ticket', '');
INSERT INTO `sys_power` VALUES ('e25ebca75b12481d9fb782b21ef842f0', '权限管理', 'syspower!showPower.do', '7518b13cf6da4e5580aeaa7decfc71bb', '3', '2016-07-29 14:44:57', 'icon-user_key', '权限管理');
INSERT INTO `sys_power` VALUES ('e2d46155babf4c549fd40f79c1d1db73', '产品管理', 'investpro!investProManage.do', '099b882f3f5749079af62aac20400782', '2', '2016-07-29 16:37:49', 'icon-tag_green', '产品的增删改查');
INSERT INTO `sys_power` VALUES ('e5a1e29e4c1d4e5daa8c0f7f276cdf8f', '广告', 'ad!gotolist.do', '099b882f3f5749079af62aac20400782', '1', '2016-09-05 13:55:38', 'icon-shield_rainbow', '广告管理');
INSERT INTO `sys_power` VALUES ('e954e75f79504d6e81e4a3e0392872a9', '经理中心', '/', 'syspowers', '3', '2016-09-20 15:53:54', 'icon-client', '');
INSERT INTO `sys_power` VALUES ('ec8f4769ac0a4bf9a9978ca15a996671', '新闻资讯', 'news!gotolist.do', '099b882f3f5749079af62aac20400782', '1', '2016-10-17 11:27:45', 'icon-award_star_bronze_1', '');
INSERT INTO `sys_power` VALUES ('fcf20301d76d41a7bb4d2ba8d876dd43', '推荐关系', 'memberinfo!gotoLevelMebmer.do', '099b882f3f5749079af62aac20400782', '10', '2016-09-21 10:03:59', 'icon-20130410120031302_easyicon_net_16', '');
INSERT INTO `sys_power` VALUES ('syspowers', '系统权限', '/', '0', '1', null, 'icon-server_chart', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色id',
  `role_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `option_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('398f5389af8f49749977cf21dc846f2e', '李四权限', '李四权限测试', '2016-07-29 13:19:35');
INSERT INTO `sys_role` VALUES ('526b3b81287c4b4a897676ef194d04d4', '超级用户', '管理所有后台1省道', '2016-11-10 15:12:44');
INSERT INTO `sys_role` VALUES ('9f22a1c341234e99b5099c86cc43134b', '开发', '最爱写代码的猿人们', '2016-11-10 15:12:24');
INSERT INTO `sys_role` VALUES ('e4fa3ab5c9304eafb326449012a063a2', '张三角色', '张三角色测试', '2016-07-29 13:19:03');

-- ----------------------------
-- Table structure for sys_role_power
-- ----------------------------
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

-- ----------------------------
-- Records of sys_role_power
-- ----------------------------
INSERT INTO `sys_role_power` VALUES ('06891e6b397f4fd69f7c35adfa711fa5', '526b3b81287c4b4a897676ef194d04d4', 'bd0ed272880d4e21b95edf6f318e6fed');
INSERT INTO `sys_role_power` VALUES ('09ac8d846ad04b9c87703436d9fd03ea', '526b3b81287c4b4a897676ef194d04d4', '07a80a029b1548d79ab5ad3544f39750');
INSERT INTO `sys_role_power` VALUES ('0c486a7ebf5943f094e65f0009ca05a0', '526b3b81287c4b4a897676ef194d04d4', 'syspowers');
INSERT INTO `sys_role_power` VALUES ('0e92cedd87f34f868086a1f85c1f8551', '9f22a1c341234e99b5099c86cc43134b', '4b7526ebcba3439680afac7c2ea40ea4');
INSERT INTO `sys_role_power` VALUES ('106af7169943479fa4d251477cebee9d', '526b3b81287c4b4a897676ef194d04d4', '0f5918b3fd894e3498dfd07b5834afbf');
INSERT INTO `sys_role_power` VALUES ('17142743e29f4f09928eb130ab3f988d', '526b3b81287c4b4a897676ef194d04d4', '4b7526ebcba3439680afac7c2ea40ea4');
INSERT INTO `sys_role_power` VALUES ('1c64c7c871dd4b8d95175be47814cac4', '526b3b81287c4b4a897676ef194d04d4', 'de0b897358bb4e2f8e983093a20a889f');
INSERT INTO `sys_role_power` VALUES ('1cc38dd06ebc4122a75bc4b8ea19fb02', 'e4fa3ab5c9304eafb326449012a063a2', 'bd0ed272880d4e21b95edf6f318e6fed');
INSERT INTO `sys_role_power` VALUES ('1e11e723dc4b44c3bfa51d6be777f42d', '526b3b81287c4b4a897676ef194d04d4', 'e954e75f79504d6e81e4a3e0392872a9');
INSERT INTO `sys_role_power` VALUES ('237a8a565ea74696b7ddbafaf0ca3b71', 'e4fa3ab5c9304eafb326449012a063a2', 'ad8fddabfe2a4928bd2e9184e730b14d');
INSERT INTO `sys_role_power` VALUES ('2762080d3cda43c5b2bde00e222e696b', '526b3b81287c4b4a897676ef194d04d4', '020511d9903d4e61800cb6accd21b4dd');
INSERT INTO `sys_role_power` VALUES ('2be169861c3b42b399a2ec646d3b2a97', '526b3b81287c4b4a897676ef194d04d4', 'ab36a5f309464b9d8059f03dfa2c9750');
INSERT INTO `sys_role_power` VALUES ('2cd17137c39b4ee4918886e7acbb3b61', '526b3b81287c4b4a897676ef194d04d4', 'e5a1e29e4c1d4e5daa8c0f7f276cdf8f');
INSERT INTO `sys_role_power` VALUES ('2e6f66163729495e87e65f8e08057b97', '526b3b81287c4b4a897676ef194d04d4', 'e2d46155babf4c549fd40f79c1d1db73');
INSERT INTO `sys_role_power` VALUES ('2ea06578a35c4043bb26d5dc7d028f33', '526b3b81287c4b4a897676ef194d04d4', '7518b13cf6da4e5580aeaa7decfc71bb');
INSERT INTO `sys_role_power` VALUES ('30082cf94a0549918f18096629bec18c', '9f22a1c341234e99b5099c86cc43134b', '5cb0b2b482ed43c9bfd831aaf2eb5977');
INSERT INTO `sys_role_power` VALUES ('30cf97dbccd7483092a4eb3f13fed9c0', '9f22a1c341234e99b5099c86cc43134b', 'ab36a5f309464b9d8059f03dfa2c9750');
INSERT INTO `sys_role_power` VALUES ('31443b0018ff4f46b7cdae0538360e42', '9f22a1c341234e99b5099c86cc43134b', '76fd35f10c3d47c0aa8db7aa91311384');
INSERT INTO `sys_role_power` VALUES ('38572ded9c974f90baef912c012330fc', '526b3b81287c4b4a897676ef194d04d4', 'db53f973105145fc91c6dc8ca761b6f5');
INSERT INTO `sys_role_power` VALUES ('3bf4f16d7ef34a5aa8c57b8692215a75', '9f22a1c341234e99b5099c86cc43134b', '020511d9903d4e61800cb6accd21b4dd');
INSERT INTO `sys_role_power` VALUES ('3c006e1381644aaeb78c08639d68bc30', '526b3b81287c4b4a897676ef194d04d4', '76fd35f10c3d47c0aa8db7aa91311384');
INSERT INTO `sys_role_power` VALUES ('46463b1e3e9e4fc0a21dd02c29e2ea1a', '526b3b81287c4b4a897676ef194d04d4', '303bff33608c46478eafe0b2720d949e');
INSERT INTO `sys_role_power` VALUES ('4e6b54ffdabd44e3a9f4dc7915ad964e', '526b3b81287c4b4a897676ef194d04d4', '4bda189386834431a3fbb7360e40d784');
INSERT INTO `sys_role_power` VALUES ('50447fb18f344b978b6396b321fd7ac2', '526b3b81287c4b4a897676ef194d04d4', '354ce4e52bbb46559a6fe5ec2a5e9cf7');
INSERT INTO `sys_role_power` VALUES ('5133bfdf9f834c5487fb8b1eadfc6b30', 'e4fa3ab5c9304eafb326449012a063a2', 'e954e75f79504d6e81e4a3e0392872a9');
INSERT INTO `sys_role_power` VALUES ('5465faa2f9c64ec3b1a36522916312f7', '526b3b81287c4b4a897676ef194d04d4', '06c2e4bc383949b584e7759a1e20f354');
INSERT INTO `sys_role_power` VALUES ('558c2bcb259f40ecbb23f4d4ba3638ba', '526b3b81287c4b4a897676ef194d04d4', 'a007affa6bca4c20adc45e2ca5550bf8');
INSERT INTO `sys_role_power` VALUES ('59a1c4c956f84b94a79508d12517df8e', '9f22a1c341234e99b5099c86cc43134b', '4bda189386834431a3fbb7360e40d784');
INSERT INTO `sys_role_power` VALUES ('5a6951d4772c43a9b6b8c6b0db6d68d9', '526b3b81287c4b4a897676ef194d04d4', '5cd915c40fa348dd97376b5d1fec9c5a');
INSERT INTO `sys_role_power` VALUES ('5f71fcbff29b4ebaac860e4f2053693c', '526b3b81287c4b4a897676ef194d04d4', '5cb0b2b482ed43c9bfd831aaf2eb5977');
INSERT INTO `sys_role_power` VALUES ('60224c0715d5451483c56f9d518e8317', '526b3b81287c4b4a897676ef194d04d4', '69c4c017afa84aa5b36ea8fed457db8b');
INSERT INTO `sys_role_power` VALUES ('666d12aa5ffb441f83f94ccf3ab9b1dc', '9f22a1c341234e99b5099c86cc43134b', 'e2d46155babf4c549fd40f79c1d1db73');
INSERT INTO `sys_role_power` VALUES ('6ba9f32e56ec4eaba1ebb6467f266894', '526b3b81287c4b4a897676ef194d04d4', '8f24607739fe4b04a24618cd0742c585');
INSERT INTO `sys_role_power` VALUES ('733734a17e6f498e9347e5cba2361270', '526b3b81287c4b4a897676ef194d04d4', '099b882f3f5749079af62aac20400782');
INSERT INTO `sys_role_power` VALUES ('75bcca14a8354009acc0e1e4f928f4be', '526b3b81287c4b4a897676ef194d04d4', '56a7ae4db858460ab5e0198bbe890806');
INSERT INTO `sys_role_power` VALUES ('81d1d34cc04e47d5bd43188447c49fd3', '526b3b81287c4b4a897676ef194d04d4', '006fd11739ad4bff9e2adbf33234aff1');
INSERT INTO `sys_role_power` VALUES ('84ee3dfbdb6b4fc3b15be14b61492736', '9f22a1c341234e99b5099c86cc43134b', '006fd11739ad4bff9e2adbf33234aff1');
INSERT INTO `sys_role_power` VALUES ('8554468328b14d5eb760c3c618f3feba', '526b3b81287c4b4a897676ef194d04d4', 'e25ebca75b12481d9fb782b21ef842f0');
INSERT INTO `sys_role_power` VALUES ('86140e04e0b344639d837cbe40fc8ede', '9f22a1c341234e99b5099c86cc43134b', 'ec8f4769ac0a4bf9a9978ca15a996671');
INSERT INTO `sys_role_power` VALUES ('9606d3cbc82a4c89884dbd25a216234d', '9f22a1c341234e99b5099c86cc43134b', 'a59137b1774f4839b9bb89b40d51cf87');
INSERT INTO `sys_role_power` VALUES ('9b9d9d7bc197408994a7756323c5a431', '9f22a1c341234e99b5099c86cc43134b', '5cd915c40fa348dd97376b5d1fec9c5a');
INSERT INTO `sys_role_power` VALUES ('9f687af1f2234eaf9d8a378de5fb71f9', '9f22a1c341234e99b5099c86cc43134b', '0f5918b3fd894e3498dfd07b5834afbf');
INSERT INTO `sys_role_power` VALUES ('a3d491f35bce4cb3bb0c344a216ce062', '9f22a1c341234e99b5099c86cc43134b', 'd40f992523ed4ff6a70ab2e05b1d4cde');
INSERT INTO `sys_role_power` VALUES ('b14688c3366e46b1ac851f3b46c45dd0', '526b3b81287c4b4a897676ef194d04d4', '1e72241d8f5144299542af5cf0d173ac');
INSERT INTO `sys_role_power` VALUES ('b8c03c5890c947fc8ca661bc5db97e66', '526b3b81287c4b4a897676ef194d04d4', '4c4e61b29d8946308e1eb29d993a5337');
INSERT INTO `sys_role_power` VALUES ('baae93ad8cc54e408ea621cda033c3b3', '9f22a1c341234e99b5099c86cc43134b', '07a80a029b1548d79ab5ad3544f39750');
INSERT INTO `sys_role_power` VALUES ('c4d5d65ffe574051a25a968c010e78d8', '9f22a1c341234e99b5099c86cc43134b', '1e72241d8f5144299542af5cf0d173ac');
INSERT INTO `sys_role_power` VALUES ('c57771b53ac04e65bf1e88bb5dc3545f', '526b3b81287c4b4a897676ef194d04d4', 'a59137b1774f4839b9bb89b40d51cf87');
INSERT INTO `sys_role_power` VALUES ('c5a9e8a84ce546d19881c343273ba81a', '526b3b81287c4b4a897676ef194d04d4', 'd40f992523ed4ff6a70ab2e05b1d4cde');
INSERT INTO `sys_role_power` VALUES ('c6f4aa7a6b154285ae12b138403a6d8e', '9f22a1c341234e99b5099c86cc43134b', 'fcf20301d76d41a7bb4d2ba8d876dd43');
INSERT INTO `sys_role_power` VALUES ('ca66cc180a704ae4bcb7ba6e17cd7b65', '526b3b81287c4b4a897676ef194d04d4', 'fcf20301d76d41a7bb4d2ba8d876dd43');
INSERT INTO `sys_role_power` VALUES ('d23b1fbe6b30439c81279f900f247d1a', '9f22a1c341234e99b5099c86cc43134b', '8f24607739fe4b04a24618cd0742c585');
INSERT INTO `sys_role_power` VALUES ('d3e16cac43d04377a05f6ec571aedbc0', '9f22a1c341234e99b5099c86cc43134b', 'de0b897358bb4e2f8e983093a20a889f');
INSERT INTO `sys_role_power` VALUES ('d615610e86214742bfe988950730d5e5', '526b3b81287c4b4a897676ef194d04d4', 'ad8fddabfe2a4928bd2e9184e730b14d');
INSERT INTO `sys_role_power` VALUES ('de2af43205244ebd855d3588c1a06cf7', '9f22a1c341234e99b5099c86cc43134b', '354ce4e52bbb46559a6fe5ec2a5e9cf7');
INSERT INTO `sys_role_power` VALUES ('e32e5002b66f4d16becd44ecc4968831', '526b3b81287c4b4a897676ef194d04d4', 'ec8f4769ac0a4bf9a9978ca15a996671');
INSERT INTO `sys_role_power` VALUES ('e7e07408a7a74602a676499c17f03ee3', 'e4fa3ab5c9304eafb326449012a063a2', '56a7ae4db858460ab5e0198bbe890806');
INSERT INTO `sys_role_power` VALUES ('e8e7a047fe834dfc8707ac4595ed60d7', '9f22a1c341234e99b5099c86cc43134b', '099b882f3f5749079af62aac20400782');
INSERT INTO `sys_role_power` VALUES ('f31f2a465bfe4b089bc67deeee8b03dd', '9f22a1c341234e99b5099c86cc43134b', 'e5a1e29e4c1d4e5daa8c0f7f276cdf8f');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
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

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0', 'admin', 'c8qFgP5zhUU=', '526b3b81287c4b4a897676ef194d04d4', '2016-07-29 13:23:07', '2016-07-29 13:25:11', '');
INSERT INTO `sys_user` VALUES ('1212121212', 'liyonghui', '21232f297a57a5a743894a0e4a801fc3', '398f5389af8f49749977cf21dc846f2e', '2016-08-08 11:47:38', '2016-08-08 11:47:41', null);
INSERT INTO `sys_user` VALUES ('4a04dc52ec9d4f449caddee28f0071d4', 'admins', 'c8qFgP5zhUU=', null, '2016-08-10 09:59:27', null, '客户经理账号');
INSERT INTO `sys_user` VALUES ('56f6f851a3fa452ca6364024c169d96b', 'wangsicong', 'Pydeq/rW/6m+QPoQ4LdzDA==', null, '2016-09-21 14:43:30', null, '客户经理账号');
INSERT INTO `sys_user` VALUES ('5cc6c63d1069403b8ab97cd1876724ef', 'ls', '73882ab1fa529d7273da0db6b49cc4f3', '398f5389af8f49749977cf21dc846f2e', '2016-07-29 13:23:07', '2016-07-29 13:23:07', '');
INSERT INTO `sys_user` VALUES ('65afb5b2d9344855a546a4e318adf537', 'wangjianlin', 'zmQIc2Ydh6dvPkynWhuIxVJct75kX7H+', null, '2016-09-21 14:54:39', null, '客户经理账号');
INSERT INTO `sys_user` VALUES ('87a99bd3b0e040e49b059312aef038b3', '李永辉', 'YmFGmNGfYqdpDX+CZ4Oucw==', '526b3b81287c4b4a897676ef194d04d4', '2016-11-10 15:13:39', '2016-11-10 15:13:39', '士大夫');
INSERT INTO `sys_user` VALUES ('97d57f49db95473ba1f5127deffdccd0', 'zs', 'f63f4fbc9f8c85d409f2f59f2b9e12d5', 'e4fa3ab5c9304eafb326449012a063a2', '2016-07-29 12:56:19', '2016-07-29 13:22:43', '');
INSERT INTO `sys_user` VALUES ('c883c60c396f4fa385cf66d93071facc', 'fds', '8e57a0a08acf3285aae9566dc3d1d0db', null, '2016-08-10 15:19:37', null, '客户经理账号');
INSERT INTO `sys_user` VALUES ('e732795b6a1e490cafb700ba029943a3', 'wangmazi', 'c8qFgP5zhUU=', 'e4fa3ab5c9304eafb326449012a063a2', '2016-07-29 12:56:19', '2016-07-29 13:22:43', null);
