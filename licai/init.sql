/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : licai

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-11-08 10:17:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cash_flow_statistics
-- ----------------------------
DROP TABLE IF EXISTS `cash_flow_statistics`;
CREATE TABLE `cash_flow_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '公司详情表主键',
  `year` int(4) NOT NULL COMMENT '年份',
  `report_type` int(1) NOT NULL DEFAULT '1' COMMENT '参考标准 1-年报 2-第三季度报 3-半年报 4-第一季度报',
  `business_to_profit` bigint(20) NOT NULL COMMENT '经营活动产生的现金流量净额',
  `bonus_cash` bigint(20) NOT NULL COMMENT '分红金额',
  `profit_substract_bonus` bigint(20) NOT NULL COMMENT '现金净增额  经营活动产生的现金流量净额-分红',
  `cash_in_incoming` int(8) NOT NULL COMMENT '销售商品提供劳务收到的现金/营业收入',
  `expand_in_profit_rate` int(8) NOT NULL COMMENT '购建资产/经营活动产生的现金流量净额',
  `sell_in_expand_rate` int(8) NOT NULL COMMENT '处置资产/购建资产',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_0` (`company_id`,`year`,`report_type`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='合并现金流量表指标';

-- ----------------------------
-- Records of cash_flow_statistics
-- ----------------------------
INSERT INTO `cash_flow_statistics` VALUES ('1', '1', '2019', '1', '14741007000', '4388702000', '10352305000', '8389', '13993', '280', null, '2020-11-04 13:02:17', '2020-11-04 16:23:51');
INSERT INTO `cash_flow_statistics` VALUES ('2', '1', '2015', '1', '3842094000', '1762667000', '2079427000', '10112', '31988', '4707', null, '2020-11-04 16:24:31', null);
INSERT INTO `cash_flow_statistics` VALUES ('3', '1', '2016', '1', '-1845571000', '3208633000', '-5054204000', '8464', '-70729', '-1065', null, '2020-11-04 16:41:05', null);
INSERT INTO `cash_flow_statistics` VALUES ('4', '1', '2017', '1', '6367887000', '26691182000', '-20323295000', '8907', '23205', '336', null, '2020-11-04 16:41:46', null);
INSERT INTO `cash_flow_statistics` VALUES ('5', '1', '2018', '1', '12522909000', '4120193000', '8402716000', '7982', '14248', '367', null, '2020-11-04 16:42:06', null);
INSERT INTO `cash_flow_statistics` VALUES ('6', '2', '2015', '1', '44378381827', '9525010447', '34853371380', '11348', '650', '0', null, '2020-11-05 14:22:07', '2020-11-05 15:30:39');
INSERT INTO `cash_flow_statistics` VALUES ('7', '2', '2016', '1', '14859952106', '9180067571', '5679884535', '6454', '2205', '18', null, '2020-11-05 14:48:26', '2020-11-05 15:30:45');
INSERT INTO `cash_flow_statistics` VALUES ('8', '2', '2017', '1', '16358538247', '11121283724', '5237254523', '7256', '1482', '2', null, '2020-11-05 15:43:48', null);
INSERT INTO `cash_flow_statistics` VALUES ('9', '2', '2018', '1', '26940791542', '862910396', '26077881146', '6815', '1424', '2', null, '2020-11-05 15:47:25', null);
INSERT INTO `cash_flow_statistics` VALUES ('10', '2', '2019', '1', '27893714093', '13159380388', '14734333705', '8397', '1690', '3', null, '2020-11-05 15:50:10', null);
INSERT INTO `cash_flow_statistics` VALUES ('11', '2', '2020', '3', '-4517675737', '7889510618', '-12407186355', '8015', '-5087', '-10', null, '2020-11-05 17:10:48', '2020-11-05 17:22:15');
INSERT INTO `cash_flow_statistics` VALUES ('12', '2', '2020', '2', '3032000101', '8142036369', '-5110036268', '17444', '11304', '19', null, '2020-11-05 17:16:37', null);

-- ----------------------------
-- Table structure for combine_cash_flow
-- ----------------------------
DROP TABLE IF EXISTS `combine_cash_flow`;
CREATE TABLE `combine_cash_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '公司详情表主键',
  `year` int(4) NOT NULL COMMENT '年份',
  `report_type` int(1) NOT NULL DEFAULT '1' COMMENT '1-年报 2-第三季度报 3-半年报 4-第一季度报',
  `business_to_profit` bigint(20) NOT NULL COMMENT '经营活动产生的现金流量净额',
  `investment_to_profit` bigint(20) NOT NULL COMMENT '投资活动产生的现金流量净额',
  `financing_to_profite` bigint(20) NOT NULL COMMENT '筹资活动产生的现金流量净额',
  `sale_to_cash` bigint(20) NOT NULL COMMENT '销售商品提供劳务收到的现金(含增值税)',
  `salary` bigint(20) NOT NULL COMMENT '支付给职工以及为职工支付的现金',
  `investment_inside_out` bigint(20) NOT NULL DEFAULT '0' COMMENT '购建固定资产、无形资产和其他长期资产支付的现金',
  `investment_inside_in` bigint(20) NOT NULL DEFAULT '0' COMMENT '处置固定资产、无形资产和其他长期资产收回的现金净额',
  `investment_outside_sub_out` bigint(20) NOT NULL DEFAULT '0' COMMENT '收购或投资子公司，对外扩大经营',
  `investment_outside_money_out` bigint(20) NOT NULL DEFAULT '0' COMMENT '投资支付的现金',
  `cash_and_cash_equivalents_add` bigint(20) NOT NULL COMMENT '现金及现金等价物净增加额',
  `cash_and_cash_equivalents_begin` bigint(20) NOT NULL COMMENT '期初现金及现金等价物余额',
  `cash_and_cash_equivalents_end` bigint(20) NOT NULL COMMENT '期末现金及现金等价物余额',
  `bonus_cash` bigint(20) NOT NULL DEFAULT '0' COMMENT '分红金额',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_0` (`company_id`,`year`,`report_type`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='合并现金流量表';

-- ----------------------------
-- Records of combine_cash_flow
-- ----------------------------
INSERT INTO `combine_cash_flow` VALUES ('2', '1', '2019', '1', '14741007000', '-20881446000', '6610345000', '107166119000', '20316157000', '20627160000', '413119000', '0', '1088939000', '523240000', '11151057000', '11674297000', '4388702000', '', '2020-11-04 13:02:17', '2020-11-04 16:23:51');
INSERT INTO `combine_cash_flow` VALUES ('3', '1', '2018', '1', '12522909000', '-14230760000', '3916511000', '103812991000', '18824518000', '17842390000', '459784000', '26872000', '829313000', '11151057000', '8935954000', '11151057000', '4120193000', '', '2020-11-04 13:06:33', '2020-11-04 16:42:06');
INSERT INTO `combine_cash_flow` VALUES ('4', '1', '2017', '1', '6367887000', '-15964084000', '11167824000', '94340585000', '15482853000', '14776561000', '213815000', '0', '1606622000', '1577360000', '7358594000', '8935954000', '26691182000', '', '2020-11-04 13:14:08', '2020-11-04 16:41:46');
INSERT INTO `combine_cash_flow` VALUES ('5', '1', '2016', '1', '-1845571000', '-13442642000', '16270217000', '87581417000', '14065652000', '13053450000', '196641000', '0', '926905000', '1079063000', '6279531000', '7358594000', '3208633000', '', '2020-11-04 13:14:35', '2020-11-04 16:41:05');
INSERT INTO `combine_cash_flow` VALUES ('6', '1', '2015', '1', '3842094000', '-10606440000', '8749785000', '80908941000', '12586188000', '12290161000', '1808561000', '0', '1070592000', '2190065000', '4089466000', '6279531000', '1762667000', '', '2020-11-04 13:19:09', '2020-11-04 16:24:31');
INSERT INTO `combine_cash_flow` VALUES ('7', '2', '2015', '1', '44378381827', '-4713154864', '-7683022005', '110918320884', '5590514442', '2884513074', '1228803', '0', '2832663335', '33858545732', '43506471113', '77365016845', '9525010447', '', '2020-11-05 14:22:07', '2020-11-05 15:30:39');
INSERT INTO `combine_cash_flow` VALUES ('12', '2', '2016', '1', '14859952106', '-19246552694', '-5751560185', '69896621293', '5657046182', '3276936026', '27196374', '0', '1496403698', '-6043656822', '77365016845', '71321360022', '9180067571', '', '2020-11-05 14:48:26', '2020-11-05 15:30:45');
INSERT INTO `combine_cash_flow` VALUES ('13', '2', '2017', '1', '16358538247', '-62253457968', '-2269341168', '107599120105', '7684869151', '2424806990', '3549493', '0', '12419732249', '-49962288325', '71321360022', '21359071697', '11121283724', '', '2020-11-05 15:43:48', null);
INSERT INTO `combine_cash_flow` VALUES ('14', '2', '2018', '1', '26940791542', '-21845765275', '2513846482', '135029126382', '8575412582', '3837549166', '6302072', '0', '15477712506', '7412504600', '21359616223', '28772120824', '862910396', '', '2020-11-05 15:47:25', null);
INSERT INTO `combine_cash_flow` VALUES ('15', '2', '2019', '1', '27893714093', '-11275048600', '-19221976120', '166387697953', '8831213736', '4713187965', '9614513', '0', '7192756039', '-2399549002', '28772120824', '26372571821', '13159380388', '', '2020-11-05 15:50:10', null);
INSERT INTO `combine_cash_flow` VALUES ('16', '2', '2020', '3', '-4517675737', '7789731713', '-6221151384', '55709211097', '4223083761', '2297960481', '4621711', '0', '3241418162', '-2944940392', '26372571821', '23427631429', '7889510618', '', '2020-11-05 17:10:48', '2020-11-05 17:22:08');
INSERT INTO `combine_cash_flow` VALUES ('17', '2', '2020', '2', '3032000101', '13046924740', '-16470465636', '98362600415', '0', '3427311410', '5803632', '346312249', '3511418162', '-711134818', '26372571821', '25661437003', '8142036369', '', '2020-11-05 17:16:37', null);

-- ----------------------------
-- Table structure for combine_profit
-- ----------------------------
DROP TABLE IF EXISTS `combine_profit`;
CREATE TABLE `combine_profit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '公司详情表主键',
  `year` int(4) NOT NULL COMMENT '年份',
  `report_type` int(1) NOT NULL DEFAULT '1' COMMENT '1-年报 2-第三季度报 3-半年报 4-第一季度报',
  `business_income` bigint(20) NOT NULL COMMENT '营业收入(不含增值税)',
  `business_costs` bigint(20) NOT NULL COMMENT '营业成本',
  `tax_revenue_total` bigint(20) NOT NULL DEFAULT '0' COMMENT '税金及附加',
  `selling_expenses` bigint(20) NOT NULL DEFAULT '0' COMMENT '销售费用',
  `manage_expenses` bigint(20) NOT NULL DEFAULT '0' COMMENT '管理费用',
  `financial_expenses` bigint(20) NOT NULL COMMENT '财务费用',
  `assets_impairment_loss` bigint(20) NOT NULL DEFAULT '0' COMMENT '资产减值损失',
  `income_from_changes_in_fair_value` bigint(20) NOT NULL COMMENT '公允价值变动收益（损失以“－”号填列）',
  `income_from_investment` bigint(20) NOT NULL DEFAULT '0' COMMENT '投资收益（损失以“－”号填列）',
  `business_profit` bigint(20) NOT NULL COMMENT '营业利润（主业和投资）',
  `non_business_income` bigint(20) NOT NULL DEFAULT '0' COMMENT '营业外收入',
  `non_business_costs` bigint(20) NOT NULL DEFAULT '0' COMMENT '营业外支出',
  `total_profit` bigint(20) NOT NULL COMMENT '利润总额',
  `income_tax_expense` bigint(20) NOT NULL COMMENT '所得税费用',
  `net_profit` bigint(20) NOT NULL COMMENT '净利润',
  `belong_mother_net_profit` bigint(20) NOT NULL COMMENT '归属于母公司所有者的净利润',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_0` (`company_id`,`year`,`report_type`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='合并利润表';

-- ----------------------------
-- Records of combine_profit
-- ----------------------------
INSERT INTO `combine_profit` VALUES ('1', '1', '2019', '1', '127738523000', '106924288000', '1560596000', '4345897000', '4140997000', '3014032000', '-159000000', '9749000', '-808695000', '2312288000', '226322000', '107479000', '2431131000', '312274000', '2118857000', '1614450000', '', '2020-11-03 17:52:56', '2020-11-04 14:39:58');
INSERT INTO `combine_profit` VALUES ('2', '1', '2018', '1', '130054707000', '108725343000', '2145629000', '4729481000', '3760412000', '2635336000', '-686416000', '-5470000', '-113353000', '4241760000', '229930000', '86050000', '4385640000', '829447000', '3556193000', '2780194000', '', '2020-11-03 19:03:34', '2020-11-04 14:41:39');
INSERT INTO `combine_profit` VALUES ('3', '1', '2017', '1', '105914702000', '85775482000', '1329477000', '4925288000', '6786083000', '2314401000', '242586000', '-118166000', '-206053000', '5410551000', '279030000', '68940000', '5620641000', '703705000', '4916936000', '4066478000', '', '2020-11-03 20:22:00', '2020-11-04 14:41:42');
INSERT INTO `combine_profit` VALUES ('4', '1', '2016', '1', '103469997000', '82400900000', '1511717000', '4196339000', '6842635000', '1222190000', '565731000', '-18207000', '-726027000', '5849534000', '844328000', '125452000', '6568410000', '1088398000', '5480012000', '5052154000', '', '2020-11-03 21:02:11', '2020-11-04 14:41:46');
INSERT INTO `combine_profit` VALUES ('5', '1', '2015', '1', '80008968000', '66513559000', '1267326000', '2867992000', '5415060000', '1445995000', '551648000', '18207000', '1210370000', '3175965000', '703235000', '84214000', '3794986000', '656790000', '3138196000', '2823441000', '', '2020-11-03 21:06:09', '2020-11-04 14:41:49');
INSERT INTO `combine_profit` VALUES ('6', '2', '2015', '1', '97745137194', '66017353745', '751894199', '15506341694', '5048746635', '-1928797250', '86317962', '-1010322499', '96654919', '13516176980', '1404291659', '11049178', '14909419462', '2285686841', '12623732620', '12532442817', '', '2020-11-05 14:12:54', '2020-11-05 14:23:59');
INSERT INTO `combine_profit` VALUES ('7', '2', '2016', '1', '108302565293', '72885641217', '1430404246', '16477265963', '5488955551', '-4845546598', '-991560', '1093332134', '-2221356324', '17455697835', '1096234774', '20742533', '18531190076', '3006555172', '15524634903', '15420964990', '', '2020-11-05 15:06:58', null);
INSERT INTO `combine_profit` VALUES ('8', '2', '2017', '1', '148286450009', '99562912753', '1513035444', '16660268494', '6071143700', '431284686', '263787001', '9212503', '396648138', '26126666010', '511059113', '20540169', '26617184953', '4108585909', '22508599044', '22401576204', '', '2020-11-05 15:11:45', '2020-11-05 15:23:29');
INSERT INTO `combine_profit` VALUES ('9', '2', '2018', '1', '198123177056', '138234167710', '1741892704', '18899578046', '4365850083', '-948201396', '261674177', '46257424', '106768935', '30996884691', '317857733', '41234701', '31273507724', '4894477907', '26379029817', '26202787681', '', '2020-11-05 15:22:52', null);
INSERT INTO `combine_profit` VALUES ('10', '2', '2019', '1', '198153027540', '143499372581', '1542983748', '18309812188', '3795645600', '-2426643429', '-842893299', '228264067', '-226634780', '29605107122', '345706663', '598106556', '29352707228', '4525463624', '24827243603', '24696641368', '', '2020-11-05 15:28:37', null);
INSERT INTO `combine_profit` VALUES ('11', '2', '2020', '3', '69502322369', '54827483782', '336366102', '5251499766', '1522385044', '-1180757815', '-105508628', '-99171782', '103967175', '7581755754', '126808580', '12822142', '7695742192', '1270470387', '6425271805', '6362137377', '', '2020-11-05 17:03:58', '2020-11-05 17:21:51');
INSERT INTO `combine_profit` VALUES ('12', '2', '2020', '2', '56387073628', '41647709495', '268743061', '4881384137', '986755044', '-325044368', '-22265517', '307402887', '56969442', '8595600214', '155664009', '3769209', '8747495013', '1380999163', '7366495850', '7336584665', '', '2020-11-05 17:06:03', null);

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '股票代码',
  `name` varchar(20) NOT NULL COMMENT '公司名称',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '行业类型 0-默认 1-汽车制造 2-白色家电',
  `location` int(2) NOT NULL COMMENT '归属市场 1-沪  2-深 3-港股 4-美股',
  `category` int(2) NOT NULL DEFAULT '1' COMMENT '类型 1-股票 2-基金 3-Reits 4-可转债 5-逆回购',
  `total_equity` bigint(20) NOT NULL COMMENT '总股本',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='公司详情表';

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '002594', '比亚迪', '1', '2', '1', '2728142855', '', '2020-11-02 14:07:14', '2020-11-05 16:08:03');
INSERT INTO `company` VALUES ('2', '000651', '格力电器', '2', '2', '1', '6016000000', '', '2020-11-02 18:01:54', '2020-11-05 16:07:56');
INSERT INTO `company` VALUES ('4', '600987', '航民股份', '0', '2', '1', '1080800000', '', '2020-11-05 16:03:10', null);
INSERT INTO `company` VALUES ('5', '002157', '正邦科技', '0', '2', '1', '2518700000', '', '2020-11-05 16:09:22', null);
INSERT INTO `company` VALUES ('6', '300181', '佐力药业', '0', '2', '1', '608600000', '', '2020-11-05 16:10:49', null);
INSERT INTO `company` VALUES ('7', '300003', '乐普医疗', '0', '2', '1', '1804600000', '', '2020-11-05 18:40:16', null);
INSERT INTO `company` VALUES ('8', '600036', '招商银行', '0', '2', '1', '25219800000', '港股为03968.HK', '2020-11-06 10:38:57', null);
INSERT INTO `company` VALUES ('9', '01928', '金沙中国有限公司', '0', '3', '1', '8089900000', '', '2020-11-06 10:41:45', null);
INSERT INTO `company` VALUES ('10', '600519', '贵州茅台', '0', '2', '1', '1256200000', '茅台的企业文化https://www.moutaichina.com/maotaigf/qygk32/qywh/index.html', '2020-11-06 11:21:58', null);
INSERT INTO `company` VALUES ('11', '300537', '广信材料', '0', '2', '1', '193000000', 'wendenl', '2020-11-07 07:38:39', null);

-- ----------------------------
-- Table structure for consolidated_assets_liabilities
-- ----------------------------
DROP TABLE IF EXISTS `consolidated_assets_liabilities`;
CREATE TABLE `consolidated_assets_liabilities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '公司详情表主键',
  `year` int(4) NOT NULL COMMENT '年份',
  `report_type` int(1) NOT NULL DEFAULT '1' COMMENT '1-年报 2-第三季度报 3-半年报 4-第一季度报',
  `total_assets` bigint(20) NOT NULL COMMENT '总资产',
  `total_liabilities` bigint(20) NOT NULL COMMENT '总负债',
  `share_holder_equity` bigint(20) NOT NULL COMMENT '股东权益合计',
  `short_term_liabilities` bigint(20) NOT NULL DEFAULT '0' COMMENT '短期负债',
  `year_arrive_no_current_liabilities` bigint(20) NOT NULL DEFAULT '0' COMMENT '一年内到期的非流动负债',
  `long_term_liabilities` bigint(20) NOT NULL DEFAULT '0' COMMENT '长期借款',
  `payable_bonds` bigint(20) NOT NULL DEFAULT '0' COMMENT '应付债券',
  `long_payable_money` bigint(20) NOT NULL DEFAULT '0' COMMENT '长期应付款',
  `payable_interest` bigint(20) NOT NULL DEFAULT '0' COMMENT '应付利息',
  `monetary_capital` bigint(20) NOT NULL COMMENT '货币资金',
  `payable_bill` bigint(20) NOT NULL DEFAULT '0' COMMENT '应付票据',
  `payable_money` bigint(20) NOT NULL DEFAULT '0' COMMENT '应付账款',
  `advance_receivable_money` bigint(20) NOT NULL DEFAULT '0' COMMENT '预收款项',
  `receivable_bill` bigint(20) NOT NULL DEFAULT '0' COMMENT '应收票据',
  `receivable_money` bigint(20) NOT NULL COMMENT '应收账款',
  `advance_payable_money` bigint(20) NOT NULL DEFAULT '0' COMMENT '预付款项',
  `fixed_assets` bigint(20) NOT NULL COMMENT '固定资产',
  `reconstruction_project` bigint(20) NOT NULL DEFAULT '0' COMMENT '在建工程',
  `engineering_materials` bigint(20) NOT NULL DEFAULT '0' COMMENT '工程物资',
  `fair_value_project` bigint(20) NOT NULL DEFAULT '0' COMMENT '以公允价值计量且变动计入当期损益的金融资产',
  `prepare_sale_finance_project` bigint(20) NOT NULL DEFAULT '0' COMMENT '可供出售金融资产',
  `held_to_maturity_investment` bigint(20) NOT NULL DEFAULT '0' COMMENT '持有至到期投资',
  `Investin_in_real_estate` bigint(20) NOT NULL DEFAULT '0' COMMENT '投资房地产',
  `long_term_equity_investment` bigint(20) NOT NULL DEFAULT '0' COMMENT '长期股权投资（与主业无关）',
  `belong_mother_equity` bigint(20) NOT NULL COMMENT '归属于母公司所有者权益合计',
  `payable_salary` bigint(20) NOT NULL DEFAULT '0' COMMENT '应付职工薪酬',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_0` (`company_id`,`year`,`report_type`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='合并资产负债表';

-- ----------------------------
-- Records of consolidated_assets_liabilities
-- ----------------------------
INSERT INTO `consolidated_assets_liabilities` VALUES ('1', '1', '2019', '1', '195641593000', '133040173000', '62601420000', '40332365000', '8747448000', '11947932000', '9968555000', '0', '560198000', '12650083000', '13647638000', '22520530000', '2000000', '43933795000', '7009379000', '362761000', '49443360000', '10674847000', '0', '0', '0', '0', '96902000', '0', '56762289000', '3782780000', '', '2020-11-02 20:34:06', '2020-11-04 13:27:36');
INSERT INTO `consolidated_assets_liabilities` VALUES ('2', '1', '2018', '1', '194571077000', '133877098000', '60693979000', '37788977000', '7482634000', '6847603000', '7076777000', '0', '389851000', '13052095000', '21140760000', '25142127000', '2300000', '49283534000', '7773025000', '358822000', '43678630000', '9683773000', '0', '0', '0', '0', '90066000', '0', '55198289000', '3855654000', '', '2020-11-02 23:00:56', '2020-11-04 13:29:26');
INSERT INTO `consolidated_assets_liabilities` VALUES ('3', '1', '2017', '1', '178099430000', '118141943000', '59957487000', '35774916000', '9873754000', '6369237000', '4493109000', '0', '174543000', '9902690000', '16954070000', '23319529000', '4700280000', '6973003000', '51880681000', '848811000', '43244815000', '4512856000', '3223053000', '1095000', '4185460000', '0', '66707000', '0', '55004194000', '3179937000', '', '2020-11-02 23:17:34', '2020-11-04 13:33:19');
INSERT INTO `consolidated_assets_liabilities` VALUES ('4', '1', '2016', '1', '145070778000', '89661415000', '55409363000', '25009611000', '7918830000', '4847936000', '4490584000', '0', '193528000', '7693666000', '15742125000', '19501485000', '1850792000', '6362378000', '41768002000', '205939000', '37483211000', '4565424000', '4391521000', '0', '3225238000', '0', '0', '0', '51255929000', '2978565000', '', '2020-11-02 23:20:27', '2020-11-04 13:33:44');
INSERT INTO `consolidated_assets_liabilities` VALUES ('5', '1', '2015', '1', '115485755000', '79456514000', '36029241000', '19943800000', '6469060000', '6745955000', '4483946000', '0', '192693000', '6596426000', '12897076000', '18581611000', '2438009000', '6798810000', '21519093000', '226962000', '32368535000', '5757798000', '3729764000', '0', '3071357000', '0', '0', '0', '32294404000', '2118495000', '', '2020-11-02 23:23:02', '2020-11-04 13:34:15');
INSERT INTO `consolidated_assets_liabilities` VALUES ('7', '1', '2020', '3', '197636405000', '130423547000', '67212858000', '32091445000', '1133880000', '15822055000', '9874753000', '0', '0', '13241316000', '11575368000', '43909067000', '1300000', '0', '43909067000', '1192801000', '48539242000', '11723781000', '0', '0', '0', '0', '0', '0', '59928232000', '0', '长期股权投资有疑问', '2020-11-03 00:36:50', '2020-11-03 01:49:02');
INSERT INTO `consolidated_assets_liabilities` VALUES ('9', '1', '2019', '2', '195641593000', '133040173000', '62601420000', '40332365000', '8747448000', '11947932000', '9968555000', '0', '560198000', '12650083000', '13647638000', '22520530000', '2000000', '0', '43933795000', '362761000', '49443360000', '10674847000', '0', '0', '0', '0', '0', '0', '56762289000', '0', '', '2020-11-03 01:43:02', null);
INSERT INTO `consolidated_assets_liabilities` VALUES ('10', '1', '2020', '2', '196799017000', '128939418000', '67859599000', '24789523000', '9201306000', '15654973000', '9876424000', '0', '333879000', '13181897000', '9332481000', '32000797000', '1400000', '0', '38907717000', '1106009000', '53190974000', '7527123000', '0', '0', '0', '0', '0', '0', '59764561000', '0', '', '2020-11-03 01:43:05', null);
INSERT INTO `consolidated_assets_liabilities` VALUES ('11', '1', '2019', '3', '195641593000', '133040173000', '62601420000', '40332365000', '8747448000', '11947932000', '9968555000', '0', '0', '12650083000', '13647638000', '22520530000', '2000000', '0', '43933795000', '362761000', '49443360000', '10674847000', '0', '0', '0', '0', '96902000', '0', '56762289000', '0', '', '2020-11-03 01:48:47', null);
INSERT INTO `consolidated_assets_liabilities` VALUES ('12', '2', '2019', '1', '282972157415', '170924500892', '112047656523', '15944176463', '0', '46885882', '0', '0', '0', '125400715267', '25285207843', '41656815752', '8225707662', '0', '8513334545', '2395610555', '19121930757', '2431051409', '0', '0', '0', '0', '498648691', '7064186161', '110153573282', '3430968964', '长期股权投资？？？', '2020-11-05 13:30:36', '2020-11-05 13:33:48');
INSERT INTO `consolidated_assets_liabilities` VALUES ('13', '2', '2018', '1', '251234157276', '158519445549', '92714711727', '22067750002', '0', '0', '0', '0', '0', '113079030368', '10835428282', '38987371471', '9792041417', '35911567876', '7699658990', '2161876009', '18385761475', '1663938988', '0', '1012470387', '2216195036', '0', '498648691', '2250732461', '91327095069', '2473204451', '', '2020-11-05 13:31:58', '2020-11-05 14:04:22');
INSERT INTO `consolidated_assets_liabilities` VALUES ('14', '2', '2015', '1', '161698016315', '113131407737', '48566608577', '6276660136', '0', '0', '0', '0', '48386709', '88819798560', '7427635753', '24794268372', '7619598042', '14879805537', '2879212111', '847929149', '15431813077', '2044837830', '0', '0', '2704719177', '0', '491540849', '95459187', '47521376091', '1697282605', '', '2020-11-05 13:43:56', null);
INSERT INTO `consolidated_assets_liabilities` VALUES ('15', '2', '2017', '1', '214967999328', '148133201565', '66834797763', '18646095044', '0', '0', '0', '0', '196103905', '99610431730', '9766929541', '34552886331', '14143038242', '32256413538', '5814491641', '3717874635', '17467371455', '1020709311', '0', '602045597', '2174941527', '0', '516630135', '110391368', '65595006071', '1876728937', '', '2020-11-05 13:57:09', '2020-11-05 14:04:26');
INSERT INTO `consolidated_assets_liabilities` VALUES ('16', '2', '2016', '1', '182369705049', '127446102258', '54923602790', '10701081645', '0', '0', '0', '0', '41781977', '95613130731', '9127336849', '29541466861', '10021885515', '29963355478', '2960534651', '1814945790', '17681655478', '581543756', '0', '0', '1384303560', '0', '597736633', '103913171', '53863951278', '3126302754', '', '2020-11-05 14:03:58', null);
INSERT INTO `consolidated_assets_liabilities` VALUES ('17', '2', '2020', '3', '282701982872', '167543374813', '115158608059', '20437330880', '0', '1233576700', '0', '0', '0', '129557451439', '18847532103', '42541732032', '0', '0', '10547952005', '2551681224', '19177411773', '2829431660', '0', '0', '0', '0', '447233617', '10467387264', '113612599659', '3009179950', '长期股权投资？？？', '2020-11-05 16:56:16', null);
INSERT INTO `consolidated_assets_liabilities` VALUES ('18', '2', '2020', '2', '274221770061', '157881976931', '116339793129', '18086420849', '0', '1579313619', '0', '0', '0', '130986600610', '18944531204', '37810786407', '0', '0', '13056612782', '2939464111', '18734023421', '3419787075', '0', '0', '0', '0', '448618082', '9905305812', '114752046055', '141021228', '', '2020-11-05 17:01:46', null);
INSERT INTO `consolidated_assets_liabilities` VALUES ('19', '6', '2015', '1', '1850546582', '414942602', '1435603980', '184340000', '1590000', '68000000', '0', '0', '326699', '450215405', '0', '51850464', '595027', '39425724', '183747591', '5319878', '483335407', '3626554', '0', '0', '20000000', '0', '0', '3859674', '1291690200', '20732564', '', '2020-11-05 20:40:40', null);

-- ----------------------------
-- Table structure for liabilities_statistics
-- ----------------------------
DROP TABLE IF EXISTS `liabilities_statistics`;
CREATE TABLE `liabilities_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '公司详情表主键',
  `year` int(4) NOT NULL COMMENT '年份',
  `report_type` int(1) NOT NULL COMMENT '参考标准 1-年报 2-第三季度报 3-半年报 4-第一季度报',
  `total_assets` bigint(20) NOT NULL COMMENT '总资产',
  `total_assets_growth_rate` int(8) NOT NULL COMMENT '总资产增速',
  `total_liabilities_in_report_type` int(8) NOT NULL COMMENT '资产负债率-负债合计占总资产比',
  `total_liabilities_growth_rate` int(8) NOT NULL COMMENT '负债合计增速',
  `share_holder_equity_growth_rate` int(8) NOT NULL COMMENT '股东权益合计增速',
  `interest_bearing_liabilities` bigint(20) NOT NULL DEFAULT '0' COMMENT '有息负债总额 = 短期负债 + 一年内到期的非流动负债 + 长期借款 + 应付债券 + 长期应付款 + 应付利息',
  `Industry_status_one` bigint(20) NOT NULL COMMENT '行业地位指标1 应付票据 + 应付账款 + 预收款项 - 应收票据 - 应收账款 - 预付款项 ',
  `monetary_capital` bigint(20) NOT NULL COMMENT '货币资金',
  `receivable_money_in_report_type` int(8) NOT NULL COMMENT '应收账款占总资产比',
  `fixed_assets_total_in_report_type` int(8) NOT NULL COMMENT '固定资产总和(固定资产+在建工程+工程物资)占总资产比',
  `investment_in_report_type` int(8) NOT NULL COMMENT '投资资产占总资产比',
  `shares_value` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '每股净资产',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_0` (`company_id`,`year`,`report_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='资产负债表统计指标';

-- ----------------------------
-- Records of liabilities_statistics
-- ----------------------------
INSERT INTO `liabilities_statistics` VALUES ('1', '1', '2016', '1', '145070778000', '2562', '6181', '1284', '5379', '42460489000', '-11241917000', '7693666000', '2879', '3201', '222', '18.79', null, '2020-11-02 23:20:27', '2020-11-04 13:33:44');
INSERT INTO `liabilities_statistics` VALUES ('2', '1', '2017', '1', '178099430000', '2277', '6633', '3176', '821', '56685559000', '-14728616000', '9902690000', '2913', '2862', '239', '20.16', null, '2020-11-02 23:17:34', '2020-11-04 13:33:19');
INSERT INTO `liabilities_statistics` VALUES ('3', '1', '2018', '1', '194571077000', '925', '6881', '1332', '123', '59585842000', '-11130194000', '13052095000', '399', '2743', '5', '20.23', null, '2020-11-02 23:00:56', '2020-11-03 12:31:02');
INSERT INTO `liabilities_statistics` VALUES ('4', '1', '2019', '1', '195641593000', '55', '6800', '-63', '314', '71556498000', '-15135767000', '12650083000', '358', '3073', '5', '20.81', null, '2020-11-02 20:34:06', '2020-11-03 12:31:00');
INSERT INTO `liabilities_statistics` VALUES ('5', '1', '2015', '1', '115485755000', '0', '6880', '0', '0', '37835454000', '5371831000', '6596426000', '1863', '3624', '266', '11.84', null, '2020-11-02 23:23:02', '2020-11-04 13:34:15');
INSERT INTO `liabilities_statistics` VALUES ('6', '1', '2020', '2', '196799017000', '59', '6552', '-308', '840', '59856105000', '1320952000', '13181897000', '1977', '3085', '0', '21.91', null, '2020-11-03 12:39:46', null);
INSERT INTO `liabilities_statistics` VALUES ('7', '1', '2019', '2', '195641593000', '0', '6800', '0', '0', '71556498000', '-8126388000', '12650083000', '2246', '3073', '0', '20.81', null, '2020-11-03 12:39:48', null);
INSERT INTO `liabilities_statistics` VALUES ('8', '1', '2019', '3', '195641593000', '0', '6800', '0', '0', '70996300000', '-8126388000', '12650083000', '2246', '3073', '5', '20.81', null, '2020-11-03 12:39:54', null);
INSERT INTO `liabilities_statistics` VALUES ('9', '1', '2020', '3', '197636405000', '102', '6599', '-197', '737', '58922133000', '10383867000', '13241316000', '2222', '3049', '0', '21.97', null, '2020-11-03 12:39:56', null);
INSERT INTO `liabilities_statistics` VALUES ('10', '2', '2019', '1', '282972157415', '1263', '6040', '783', '2085', '15991062345', '64258786157', '125400715267', '301', '762', '267', '18.31', null, '2020-11-05 13:30:36', '2020-11-05 13:33:48');
INSERT INTO `liabilities_statistics` VALUES ('11', '2', '2018', '1', '251234157276', '1687', '6310', '701', '3872', '22067750002', '13841738295', '113079030368', '306', '798', '238', '15.18', null, '2020-11-05 13:31:58', '2020-11-05 14:04:22');
INSERT INTO `liabilities_statistics` VALUES ('12', '2', '2015', '1', '161698016315', '0', '6996', '0', '0', '6325046845', '21234555370', '88819798560', '178', '1081', '204', '7.90', null, '2020-11-05 13:43:56', null);
INSERT INTO `liabilities_statistics` VALUES ('13', '2', '2017', '1', '214967999328', '1787', '6891', '1623', '2169', '18842198949', '16674074300', '99610431730', '270', '860', '158', '10.90', null, '2020-11-05 13:57:09', '2020-11-05 14:04:26');
INSERT INTO `liabilities_statistics` VALUES ('14', '2', '2016', '1', '182369705049', '1278', '6988', '1265', '1309', '10742863622', '13951853306', '95613130731', '162', '1001', '114', '8.95', null, '2020-11-05 14:03:58', null);
INSERT INTO `liabilities_statistics` VALUES ('15', '2', '2020', '3', '282701982872', '0', '5927', '0', '0', '21670907580', '48289630906', '129557451439', '373', '778', '386', '18.89', null, '2020-11-05 16:56:16', null);
INSERT INTO `liabilities_statistics` VALUES ('16', '2', '2020', '2', '274221770061', '0', '5757', '0', '0', '19665734468', '40759240718', '130986600610', '476', '808', '378', '19.07', null, '2020-11-05 17:01:46', null);
INSERT INTO `liabilities_statistics` VALUES ('17', '6', '2015', '1', '1850546582', '0', '2242', '0', '0', '254256699', '-176047702', '450215405', '993', '2631', '129', '2.12', null, '2020-11-05 20:40:40', null);

-- ----------------------------
-- Table structure for profit_statistics
-- ----------------------------
DROP TABLE IF EXISTS `profit_statistics`;
CREATE TABLE `profit_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '公司详情表主键',
  `year` int(4) NOT NULL COMMENT '年份',
  `report_type` int(1) NOT NULL DEFAULT '1' COMMENT '参考标准 1-年报 2-第三季度报 3-半年报 4-第一季度报',
  `business_income_growth_rate` int(8) NOT NULL DEFAULT '0' COMMENT '营业收入增速',
  `gross_profit_margin` int(8) NOT NULL COMMENT '毛利率',
  `cost_rate` int(8) NOT NULL COMMENT '费用率',
  `cost_in_profit` int(8) NOT NULL COMMENT '费用率/毛利率',
  `main_profit` bigint(20) NOT NULL COMMENT '主营利润',
  `business_to_profit` bigint(20) NOT NULL DEFAULT '0' COMMENT '经营活动产生的现金流量净额(现金流量表)',
  `profit_quality` int(8) NOT NULL COMMENT '利润质量 经营活动产生的现金流量净额/主营利润',
  `profit_quality_one` int(8) NOT NULL DEFAULT '0' COMMENT '利润质量 经营活动产生的现金流量净额/净利润',
  `main_profit_in_profit_total` int(8) NOT NULL COMMENT '主营利润/利润总额',
  `main_profit_in_income_total` int(8) NOT NULL COMMENT '主营利润率 主营利润/营业收入',
  `belong_mother_net_profit` bigint(20) NOT NULL COMMENT '归属于母公司所有者的净利润',
  `belong_mother_net_profit_growth_rate` int(8) NOT NULL COMMENT '归属于母公司所有者的净利润增速',
  `total_equity` bigint(20) NOT NULL COMMENT '总股本',
  `shares_profit` decimal(8,2) NOT NULL COMMENT '每股收益',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_0` (`company_id`,`year`,`report_type`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='合并利润表统计';

-- ----------------------------
-- Records of profit_statistics
-- ----------------------------
INSERT INTO `profit_statistics` VALUES ('1', '1', '2019', '1', '-178', '1947', '900', '4622', '7752713000', '14741007000', '19014', '0', '31889', '607', '1614450000', '-4193', '2728142855', '0.59', null, '2020-11-03 17:52:56', '2020-11-04 14:40:06');
INSERT INTO `profit_statistics` VALUES ('2', '1', '2018', '1', '2279', '1962', '855', '4358', '8058506000', '12522909000', '15540', '0', '18375', '620', '2780194000', '-3163', '2728142855', '1.02', null, '2020-11-04 14:41:39', null);
INSERT INTO `profit_statistics` VALUES ('3', '1', '2017', '1', '236', '2348', '1324', '5639', '4783971000', '6367887000', '13311', '0', '8511', '452', '4066478000', '-1951', '2728142855', '1.49', null, '2020-11-04 14:41:42', null);
INSERT INTO `profit_statistics` VALUES ('4', '1', '2016', '1', '2932', '2557', '1185', '4634', '7296216000', '-1845571000', '-2529', '0', '11108', '705', '5052154000', '7894', '2728142855', '1.85', null, '2020-11-04 14:41:46', null);
INSERT INTO `profit_statistics` VALUES ('5', '1', '2015', '1', '0', '2029', '1216', '5993', '2499036000', '3842094000', '15374', '0', '6585', '312', '2823441000', '0', '2728142855', '1.03', null, '2020-11-04 14:41:49', null);
INSERT INTO `profit_statistics` VALUES ('6', '2', '2015', '1', '0', '4806', '2103', '4376', '10420800921', '44378381827', '42586', '35155', '6989', '1066', '12532442817', '0', '6016000000', '2.08', null, '2020-11-05 14:12:54', '2020-11-05 15:30:39');
INSERT INTO `profit_statistics` VALUES ('7', '2', '2016', '1', '1080', '4859', '2028', '4174', '12020298316', '14859952106', '12362', '9572', '6487', '1110', '15420964990', '2305', '6016000000', '2.56', null, '2020-11-05 15:30:45', null);
INSERT INTO `profit_statistics` VALUES ('8', '2', '2017', '1', '3692', '4894', '1562', '3192', '24047804932', '16358538247', '6803', '7268', '9035', '1622', '22401576204', '4527', '6016000000', '3.72', null, '2020-11-05 15:43:48', null);
INSERT INTO `profit_statistics` VALUES ('9', '2', '2018', '1', '3361', '4332', '1174', '2710', '34881688513', '26940791542', '7723', '10213', '11154', '1761', '26202787681', '1697', '6016000000', '4.36', null, '2020-11-05 15:47:25', null);
INSERT INTO `profit_statistics` VALUES ('10', '2', '2019', '1', '2', '3809', '1116', '2930', '31005213423', '27893714093', '8996', '11235', '10563', '1565', '24696641368', '-575', '6016000000', '4.11', null, '2020-11-05 15:50:10', null);
INSERT INTO `profit_statistics` VALUES ('11', '2', '2020', '2', '0', '3539', '1041', '2942', '8602481891', '3032000101', '3525', '4116', '9834', '1526', '7336584665', '0', '6016000000', '1.22', null, '2020-11-05 17:16:37', null);
INSERT INTO `profit_statistics` VALUES ('12', '2', '2020', '3', '0', '2677', '975', '3642', '7564587675', '-4517675737', '-5972', '-7031', '9830', '1088', '6362137377', '0', '6016000000', '1.06', null, '2020-11-05 17:22:15', null);
