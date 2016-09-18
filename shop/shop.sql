/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50511
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50511
File Encoding         : 65001

Date: 2016-07-07 10:53:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contact` varchar(32) NOT NULL COMMENT '收货人',
  `mobile` varchar(32) NOT NULL COMMENT '联系电话',
  `street` varchar(255) NOT NULL COMMENT '详细地址',
  `zipcode` varchar(32) DEFAULT NULL COMMENT '邮编',
  `mbr_id` int(11) NOT NULL COMMENT '所属会员',
  `default_value` tinyint(1) DEFAULT '0' COMMENT '是否为所属会员的默认收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '张三', '13902025678', '天津·西青区·津静路26号', '300384', '1', '0');
INSERT INTO `address` VALUES ('2', '李四', '13702204321', '天津市滨海高新区华苑产业区海泰华科三路1号华鼎智地3号楼4层', '300384', '1', '0');
INSERT INTO `address` VALUES ('3', '王5', '022-87188666', '天津市南开区华苑路26号dddddd', '300384', '1', '1');
INSERT INTO `address` VALUES ('5', '钱七', '777', '广东东莞', '200020', '2', '1');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '类目名称',
  `alias` varchar(64) DEFAULT NULL COMMENT '类目别名',
  `p_id` int(11) DEFAULT NULL COMMENT '父类目编号',
  `order_weight` int(11) DEFAULT NULL COMMENT '排序权重',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `alias` (`alias`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '手      机', 'shouji', null, '10');
INSERT INTO `category` VALUES ('2', '电脑办公', 'diannaobangong', null, '10');
INSERT INTO `category` VALUES ('3', '影音娱乐', 'yingyinyule', null, '10');
INSERT INTO `category` VALUES ('4', '小  家 电', 'xiaojiadian', null, '10');
INSERT INTO `category` VALUES ('5', '大  家 电', 'dajiadian', null, '10');
INSERT INTO `category` VALUES ('6', '摄影器材', 'sheyingqicai', null, '10');
INSERT INTO `category` VALUES ('7', '酷玩潮品', 'yidong', null, '10');
INSERT INTO `category` VALUES ('8', '移动手机', 'zhinengji', '1', '10');
INSERT INTO `category` VALUES ('9', '联通手机', 'liantong', '1', '10');
INSERT INTO `category` VALUES ('10', '电信手机', 'dianxin', '1', '10');
INSERT INTO `category` VALUES ('11', '笔记本', 'bijiben', '2', '10');
INSERT INTO `category` VALUES ('12', '台式机', 'taishiji', '2', '10');
INSERT INTO `category` VALUES ('13', '数码影音', 'shumayingyin', '3', '10');
INSERT INTO `category` VALUES ('14', '家庭影院', 'jiatingyingyuan', '3', '10');
INSERT INTO `category` VALUES ('15', '个护电器', 'gehudianqi', '4', '10');
INSERT INTO `category` VALUES ('16', '健康电器', 'jiankangdianqi', '4', '10');
INSERT INTO `category` VALUES ('17', '冰箱', 'bingxiang', '5', '10');
INSERT INTO `category` VALUES ('18', '空调', 'kongtiao', '5', '10');
INSERT INTO `category` VALUES ('19', '洗衣机', 'xiyiji', '5', '10');
INSERT INTO `category` VALUES ('20', '摄影摄像', 'sheyingshexiang', '6', '10');
INSERT INTO `category` VALUES ('21', '摄影配件', 'sheyingpeijian', '6', '10');
INSERT INTO `category` VALUES ('22', '潮品', 'chaopin', '7', '10');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '所属订单编号',
  `product_id` int(11) NOT NULL COMMENT '商品编号',
  `amount` int(11) DEFAULT NULL COMMENT '单品数量',
  `total_price` decimal(9,2) DEFAULT NULL COMMENT '单品总价',
  `payment_price` decimal(9,2) DEFAULT NULL COMMENT '单品实付的总价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '1', '3', '2', '1598.00', '1398.00');
INSERT INTO `item` VALUES ('2', '1', '14', '1', '999.00', '999.00');
INSERT INTO `item` VALUES ('3', '2', '12', '2', '6798.00', '6198.00');
INSERT INTO `item` VALUES ('4', '2', '15', '2', '1998.00', '1998.00');
INSERT INTO `item` VALUES ('5', '3', '6', '4', '800.00', '672.00');
INSERT INTO `item` VALUES ('6', '4', '16', '3', '8100.00', '6000.00');
INSERT INTO `item` VALUES ('7', '5', '22', '1', '6999.00', '6530.00');
INSERT INTO `item` VALUES ('8', '6', '17', '1', '1998.00', '1500.00');
INSERT INTO `item` VALUES ('9', '7', '24', '1', '1999.00', '1500.00');

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(32) NOT NULL COMMENT '手机号',
  `pwd` varchar(32) NOT NULL COMMENT '密码',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实名',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱号',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别，默认值false',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '119', '2016', 'HYC', '黄运驰', '119@sina.com', '1', '2016-07-01 08:50:22');
INSERT INTO `member` VALUES ('2', '122', '122', '通哥', '交通警察', '122@126.com', '0', '2016-07-01 15:12:08');
INSERT INTO `member` VALUES ('3', '114', '114', '查查查', '老查', '411@126.com', '1', '2016-07-01 16:53:24');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `thumbnail` varchar(128) DEFAULT NULL COMMENT '主配图片',
  `content` longtext COMMENT '内容',
  `top` tinyint(1) DEFAULT '0' COMMENT '是否轮播图，默认值false',
  `hits` int(11) DEFAULT '0' COMMENT '阅读次数',
  `pub_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '玫瑰金Beats Solo2无线耳机和urBeats耳机开卖', 'sliding-3.jpg', '苹果今天发布了玫瑰金Beats Solo2无线头戴式耳机和urBeats耳塞式耳机，来搭配iPhone6S阵容和部分Apple Watch机型。这两款耳机在今年早些时候发布的时候，只有金，银，太空灰三种颜色。作为被苹果收购之后第一款产品，Beats Solo2头戴式无线耳机2014年十一月发布，零售价格$299.95。<br><img src=\"/shop-day09/img/yingyinyule01.jpg\" border=\"0\" alt=\"\" />', '1', '1', '2016-06-02 21:38:00');
INSERT INTO `news` VALUES ('2', '大疆精灵4的这十个黑科技，让新手也能轻松驾驶', 'sliding-1.jpg', '<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	一个月前的 CES 上，深圳零度和 Yuneec 分别与高通和英特尔合作，完成了 2016 年中国无人机的国际首秀，不过大疆新品的缺席却让 CES 上无人机的争斗还留有可升华的余地。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	今天凌晨，大疆在美国纽约发布了最新款消费级航拍无人机 Phantom 4，标志着中国消费级航拍无人机的“三国演义”正式进入了一个新的阶段，三家公司终于都拥有了属于 2016 年的消费级新品\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	发布于 2015 年的 Phantom 3 系列 4 款无人机，在接下来的很长一段时间里仍然会凭借其低廉的售价和优异的综合素质在保有量上领跑市场。\r\n\r\n如果说一年前 Phantom 3 系列彻底地把 Phantom 2 从无人机战场上替换下来，那么 Phantom 4 应该是在 Phantom 3 系列的基础上，通过智能避障，冗余设计、并联电池等更加自动化、安全的技术升级进一步满足中高端消费级用户的需求，在未来的一段时间内与 Phantom 3 一起持续扩展市场，填补 Phantom 3 Professional 6499 元至 Inspire 1 V2.0 19499 元的部分空间。\r\n\r\n作为消费级绝对旗舰的 Inspire 1 和 Inspire Pro 也会继续维持现有优势，借助可变形机架、360°云台、微型 3/4 航拍相机、双遥控器操作等高端特性紧跟专业级市场。由于中国民航总局《轻小无人机运行规定（试行）》的出台，重量更轻，被划分为 II 类无人机的 Inspire 系列将会被部分摄影师用于替代专业级八轴、六轴无人机，因为相比 Inspire 系列，两者受到法规的管理更加严格\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	这样一款科技感满满的无人机能否成功地在你心里种下一颗小草？不妨等待爱范儿接下来对大疆 Phantom 4 无人机进行的全方位测评后，再做最终的决定。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<span style=\"color:#666666;font-family:微软雅黑;font-size:14px;line-height:28px;background-color:#FFFFFF;\"></span>', '1', '0', '2016-06-02 21:58:00');
INSERT INTO `news` VALUES ('3', '时尚云笔记本 联想小新Air 12低至2999', 'sliding-2.jpg', '<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	尊敬的各位网友：\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	联想小新系列笔记本一直以高性价比著称，在不久前问世的联想小新Air 12云笔记本近日正式登陆京东商城以及联想官方商城，即日起至7月7日9点59分，消费者可前往预约，该机售价2999元起，7月7日10点至7月9日10点正式开启抢购。截止7月17日前，购买小新Air 12笔记本并按要求晒单，最高可获得1年免费延保及领取潮流帆布电脑包，感兴趣的用户千万不要错过。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<span style=\"color:#666666;font-family:微软雅黑;font-size:14px;line-height:21px;background-color:#FFFFFF;\">卓讯</span>3C购物节也在同期正式开启。7月6日起，在配置方面，该机采用最新的英特尔第六代Skylake酷睿M处理器，相比上一代，CPU性能提升17%，图形处理能力提升35%。联想小新Air 12配备了SSD固态硬盘，迅猛的读写速度，轻松实现快速开机。此外，联想小新Air 12采用了无风扇设计，使机身几乎静音运行，加之更强的续航能力，日常使用更加轻松\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<span style=\"color:#666666;font-family:微软雅黑;font-size:14px;line-height:28px;background-color:#FFFFFF;\">祝您购物愉快！</span>', '1', '0', '2016-06-02 22:00:00');
INSERT INTO `news` VALUES ('4', '一篇很穷的摄影器材攻略 没钱也能玩摄影', null, '<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	导语：无论你对摄影有着怎样的理解，总的来说摄影终究是一门(把钱)用光的艺术，所以从理论上来说你的资金预算越是充足当然所能选择的器材也越要出色。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	首先作为一名理智的摄影爱好者，要明确一点就是切勿迷恋器材并攀比，虽然是有多少钱办多少事但是合理使用手头的设备一样让你事半功倍。\r\n\r\n　　1.到底是单反还是微单?\r\n\r\n　　这个问题在微单相机崛起发展后就已经变成了一个老生常谈的话题了，关于到底是微单还是单反?\r\n\r\n　　从价格角度去看的话其实入门级的机型无论单反还是微单已经差别不大，比如入门级的佳能1300D套机已经不足3000元，同样价位的还有尼康D3300套机，都绝对是入门学习摄影的优先选择。\r\n\r\n一篇很穷的器材搭配攻略 告诉你没钱也能玩摄影\r\n　　微单相机在这个价位内则有更为丰富的选择，首先可以参考索尼的微单，因为但从传感器角度考虑的话这个价位的微单相机只有索尼提供了和单反相机同样尺寸的APS-C画幅。\r\n\r\n一篇很穷的器材搭配攻略 告诉你没钱也能玩摄影\r\n　　比如索尼ILCE-5000L，虽然是上一代机型但是套机仅2000元出头已经相当实惠。其他品牌的机型，功能上来看区别已经并不大了。\r\n\r\n加载中...\r\n　　这里要单独提一下如果是以学习为目的的话不建议选择Pentax的Q系列，它的体积真的非常小，但是对于这个机型来说更适合作为“玩物”来看待。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<span style=\"color:#666666;font-family:微软雅黑;font-size:14px;line-height:28px;background-color:#FFFFFF;\">　想买全画幅相机没钱怎么办?看看现在的索尼A7?第一代A7虽然推出距今已经有3年时间，但是目前全新机身已经不足6000元，二手机身已经只有4000~5000元的价格，想想几年前全画幅相机万元级的时代是不是非常不可思议?！</span>', '0', '0', '2016-06-03 12:11:00');
INSERT INTO `news` VALUES ('6', '小米欲发布6.4英寸平板手机抗衡iPhone7', null, '<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	据外媒7月5日报道，小米正在远离低端市场，把注意力转移到设备升级上，今年将发布6.4英寸平板手机，以期赶超苹果公司的iphone7。\r\n\r\n小米公司副总裁雨果·巴拉表示，小米将致力把自身打造成优质品牌。6月份，联想宣布将要发布第一部应用谷歌Tango 技术的智能手机，这款手机支持3D深度相机，可以增强实感，采用6.4英寸大屏，使其成为市场上最大的平板手机。雨果·巴拉当日也宣布小米Max平板会采用6.44英寸屏幕。这样一来，小米和联想将远远超过坚持5.5英寸显示屏的iphone plus，而此前有谣言称2017年发布的iphone plus 屏幕为5.7英寸，这也大大落后于其竞争对手的新标准。\r\n\r\n当苹果发布iphone 6plus 之后，其销量一发不可收拾，打破了所有智能手机销量的最高纪录。很明显，消费者想要的是大显示屏，联想和小米都想在苹果之前通过大屏幕手机在暑假期间赢得销量。另外，新的小米Max 定价是1799港元(约人民币1552元)，是iPhone 6s Plus售价的1/3。\r\n\r\n巴拉在香港的一家小米之家体验中心表示，“我们产品的价格有所提升，是因为客户期待的是优质产品，因此我们也正在努力打造优质配件的高质量手机”。除了打造类似于苹果ios的智能手机操作界面以外，小米还想复制苹果专卖店零售模式。一年前开放的体验中心是小米在香港的第一个实体专营店，客户可以在这里试用各种不同的产品。巴拉认为，小米之家是一种很成功的销售模式，并且表示将要在香港开更多的小米之家。(实习编译：李姣姣审稿：李宗泽)\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	选时尚潮流的iPad平板电脑，单反/无人机，智能穿戴产品，各种新奇特产品，特惠疯抢的价格\r\n</p>', '0', '0', '2016-06-03 12:20:00');
INSERT INTO `news` VALUES ('7', '从产品到生态，乐视手机为何能够抢眼赛诺数据?', null, '<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	智能手机行业的竞争可谓一刻不停歇，各个厂商都竭尽所能，力图有惊艳表现。在整体手机市场高度饱和、增长乏力的情况下，乐视手机却以迅猛的增长态势在赛诺的数据排行中异常抢眼。最新出炉的5月份赛诺整体(线上+线下)智能手机市场品牌份额排行显示，乐视手机以142万台的销量占据EBP市场14.8%的市场份额，位居手机行业线上份额第三，仅次于小米、荣耀，超越华为、苹果、魅族、三星、OPPO等老牌劲旅。线上线下整体市场，乐视手机5月销量超过200万台，份额4.7%排名第八，单月销量超过魅族20多万台。此外，在月度增长最快的手机公司中，乐视也表现抢眼，超过行业第二名两倍之多。\r\n\r\n6月底，乐视生态的展台亮相上海MWC大会，其间乐视手机更是召开了以“英雄本色”为名的新品发布会。那么，从产品到渠道，再到品牌生态，乐视手机频繁传出捷报，抢眼赛诺数据的背后原因是什么?答案除了乐视拥有不断颠覆和挑战已有行业格局的全新商业模式，更重要的是，乐视生态系统有着强大的生态力以及深入每一个环节的全流程创新。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	在今年【城宇】自媒体的评论分析文章中，多次提到了“供给侧改革”这一理论。不仅因为这是目前我国重要的改革方法论，更因为这样的理论背后实际上是思想意识的转变。随着时代的变化，用户的需求和思考方式也在改变，手机行业需要改革。简单来说，就是从野蛮粗放的设计生产方式，向追求质量的精细化生产方式转变。\r\n\r\n在乐视移动总裁冯幸看来，手机行业需要进行“供给侧改革”，手机厂商需要不断依靠提供高价值的“供给”，以用户价值为导向，提供优质内容和服务，才能创造更高的利润空间，为行业发展注入新的活力。乐视超级手机以“平台+内容+终端+应用”的生态模式重新定义手机，以生态创新和颠覆性的商业模式正在使手机行业悄然生变。现在，手机行业正走向精细化和生态化，这不仅验证着“供给侧改革”理论在手机行业的正确性和可行性，更预示着品牌与生态建设才是未来手机行业的大势所趋。\r\n\r\n冯幸说过：“虽然现在风头刚开始但风头正劲，这和乐视的风格相关，一切都是主动，希望把命运放在自己手里。我想现在这个时代，只要真正抓住用户需求，为他们创造价值，你就是造风者。”只有将产品力、渠道力和生态力有机结合起来，才能在激烈的红海竞争中驶出远航的风帆，才能永葆行业竞争力并在日后的发展中迎来再次大幅增长的契机。<br />\r\n</p>\r\n\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	祝您购物愉快！\r\n</p>', '0', '0', '2016-06-03 12:21:00');
INSERT INTO `news` VALUES ('8', '徕卡双镜头 华为P9临汾促销价仅2599元', null, '<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n华为P9是华为今年最新的旗舰机，3GB运行内存，搭载高端八核的海思 Kirin 955处理器，性能强悍，同时使采用了徕卡SUMMARIT双镜头，更好的亮度，清晰度，为我们带来了更好的体验。现在临汾经销商“临汾爱淘手机网”处参考2599元，有需要的欢迎来电咨询吧。\r\n</p>\r\n<img src=\"/shop-day09/img/huawei.jpg\" border=\"0\" alt=\"\" />\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	华为P9配备了5.2英寸2.5D弧面屏幕，分辨率为1920*1080，像素密度为424ppi，3.08mm窄边框设计，金属化的机身，支持指纹识别，支持双卡。背部边缘添加了曲线造型，为用户带来了更为贴合的握持感。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	性能上，华为P9搭载了海思 Kirin 955 处理器，2.5GHz（大四核），1.8GHz（小四核），3GB运行内存，32GB的存储容量，运行Android OS 6.0系统，3000毫安的电池容量，支持快速充电，采用徕卡认证的1200万像素双镜头，包含双色温补光灯，在拍照时黑白的更显细节，彩色的色彩更加的饱满，及其对应的800万像素前置镜头。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	编辑点评：华为P9引入了独立的双摄像头深度计算服务的双核图像处理芯片，可专门针对深度信息进行计算，让我们的拍照变的更加的专业，搭载的高端八核海思麒麟955，速度也是非常的快，如果您有需要，联系文末商家吧。\r\n</p>\r\n<p style=\"color:#666666;font-family:微软雅黑;font-size:14px;background-color:#FFFFFF;\">\r\n	<br />\r\n</p>\r\n<span style=\"color:#666666;font-family:微软雅黑;font-size:14px;line-height:28px;background-color:#FFFFFF;\">祝您购物愉快！</span>', '0', '0', '2016-06-03 12:23:00');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(64) DEFAULT NULL COMMENT '订单号',
  `buyer_id` int(11) NOT NULL COMMENT '所属买家会员编号',
  `total_amount` int(11) DEFAULT NULL COMMENT '商品总数量',
  `total_price` decimal(9,2) DEFAULT NULL COMMENT '总的费用',
  `payment_price` decimal(9,2) DEFAULT NULL COMMENT '实付的费用',
  `remark` varchar(255) DEFAULT NULL COMMENT '买家留言',
  `contact` varchar(32) DEFAULT NULL COMMENT '收货人',
  `mobile` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `street` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `zipcode` varchar(32) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '完成时间',
  `status` int(11) NOT NULL COMMENT '订单状态：0下单,1待付款,2已付款,3待发货,4已发货,5己收货,6已完成,-1已取消',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '20160604172439581', '1', '3', '2397.00', '2397.00', '送货要快啊', '李四', '13702204321', '天津市滨海高新区华苑产业区海泰华科三路1号华鼎智地3号楼4层', '300384', '2016-06-04 17:24:39', null, null, null, '-1');
INSERT INTO `orders` VALUES ('2', '20160604184357731', '1', '4', '8196.00', '8196.00', '货好', '张三', '13902025678', '天津·西青区·津静路26号', '300384', '2016-06-04 18:43:57', null, null, null, '3');
INSERT INTO `orders` VALUES ('3', '20160705153804703', '1', '4', '800.00', '672.00', '', '王5', '022-87188666', '天津市南开区华苑路26号dddddd', '300384', '2016-07-05 15:37:55', null, null, null, '2');
INSERT INTO `orders` VALUES ('4', '20160705201949889', '1', '3', '8100.00', '6000.00', '送上楼', '王5', '022-87188666', '天津市南开区华苑路26号dddddd', '300384', '2016-07-05 20:19:08', null, null, null, '2');
INSERT INTO `orders` VALUES ('5', '20160706141246119', '1', '1', '6999.00', '6530.00', '', '王5', '022-87188666', '天津市南开区华苑路26号dddddd', '300384', '2016-07-06 14:12:37', null, null, null, '2');
INSERT INTO `orders` VALUES ('6', '20160707103144693', '1', '1', '1998.00', '1500.00', '', '王5', '022-87188666', '天津市南开区华苑路26号dddddd', '300384', '2016-07-07 10:31:42', null, null, null, '2');
INSERT INTO `orders` VALUES ('7', '20160707103232554', '1', '1', '1999.00', '1500.00', '', '王5', '022-87188666', '天津市南开区华苑路26号dddddd', '300384', '2016-07-07 10:32:30', null, null, null, '2');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `cate_id` int(11) NOT NULL COMMENT '所属类目编号',
  `thumbnail` varchar(128) DEFAULT NULL COMMENT '主配图片',
  `inventory` int(11) DEFAULT '0' COMMENT '库存数量',
  `sales_volume` int(11) DEFAULT '0' COMMENT '售出数量',
  `price` decimal(9,2) DEFAULT '0.00' COMMENT '定价',
  `sale_price` decimal(9,2) DEFAULT '0.00' COMMENT '售价',
  `detail_description` mediumtext COMMENT '详情富文本描述',
  `selling_description` varchar(255) DEFAULT NULL COMMENT '卖点富文本描述',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `sale_time` datetime DEFAULT NULL COMMENT '开售时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'Nokia 经典通话机（第七代）', '9', 'nokia.jpg', '200', '0', '280.00', '120.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/n1.jpg\" alt=\"\" />\r\n<img src=\"./img/n2.jpg\" alt=\"\" /> \r\n<img src=\"./img/n3.jpg\" alt=\"\" /> \r\n<img src=\"./img/n4.jpg\" alt=\"\" /> \r\n</div>', '手机中的战斗机。待机时间15天。', '2016-06-01 17:37:59', '2016-06-03 18:50:00');
INSERT INTO `product` VALUES ('2', 'iphone 6s plus (64G)', '8', 'iphone6s.jpg', '123', '0', '5288.00', '4880.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/detail-1.jpg\" alt=\"\" />\n<img src=\"./img/detail-2.jpg\" alt=\"\" />\n<img src=\"./img/detail-3.jpg\" alt=\"\" />\n<img src=\"./img/detail-4.jpg\" alt=\"\" />\r\n</div>', '', '2016-06-01 22:38:11', '2016-06-02 18:28:00');
INSERT INTO `product` VALUES ('3', '魅蓝3 礼盒版', '9', 'meinote3.jpg', '100', '0', '799.00', '699.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/me1.jpg\" alt=\"\" />\n<img src=\"./img/me2.jpg\" alt=\"\" />\n<img src=\"./img/me3.jpg\" alt=\"\" />\n<img src=\"./img/me4.jpg\" alt=\"\" />\r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	全网通 4G手机 银白色 标配版(2G RAM+16G ROM)标配\r\n</h1>', '2016-06-03 09:44:28', '2016-06-05 09:40:00');
INSERT INTO `product` VALUES ('4', '小米Max', '9', 'xiaomi.jpg', '1000', '0', '1699.00', '1699.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/mi-1.jpg\" alt=\"\" />\n<img src=\"./img/mi-2.jpg\" alt=\"\" />\n<img src=\"./img/mi-3.jpg\" alt=\"\" />\n<img src=\"./img/mi-4.jpg\" alt=\"\" />\n<img src=\"./img/mi-5.jpg\" alt=\"\" />\n<img src=\"./img/mi-6.jpg\" alt=\"\" /> \r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	4G手机 双卡双待 金色 全网通(3G RAM+32G ROM)标配\r\n</h1>', '2016-06-03 09:50:25', '2016-06-10 09:00:00');
INSERT INTO `product` VALUES ('5', '华为 P9 全网通 3GB+32GB版 流光金 移动联通电信4G手机 双卡双待', '10', 'huawei.jpg', '2000', '0', '3188.00', '3188.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/hw-1.jpg\" alt=\"\" /> \r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	全网通 3GB+32GB版 流光金 移动联通电信4G手机 双卡双待\r\n</h1>', '2016-06-03 09:54:00', '2016-06-10 09:50:00');
INSERT INTO `product` VALUES ('6', '优思 US1 移动 联通 三防 老人手机 迷彩', '8', 'uniscope.jpg', '500', '0', '200.00', '168.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/us-1.jpg\" alt=\"\" /> \n<img src=\"./img/us-2.jpg\" alt=\"\" />\n <img src=\"./img/us-3.jpg\" alt=\"\" /> \n<img src=\"./img/us-4.jpg\" alt=\"\" /> \n<img src=\"./img/us-5.jpg\" alt=\"\" /> \r\n</div>', '<span style=\"color:#E3393C;font-family:arial, \'microsoft yahei\';font-size:14px;line-height:20px;background-color:#FFFFFF;\">耐磨防摔 户外三防 耐磨防摔 一键手电 充电宝功能</span>', '2016-06-03 09:58:58', '2016-06-03 09:57:00');
INSERT INTO `product` VALUES ('7', 'Apple MacBook Pro 13.3英寸笔记本电脑', '11', 'mbp.jpg', '300', '0', '9288.00', '9200.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/mbp-1.jpg\" alt=\"\" />\n<img src=\"./img/mbp-2.jpg\" alt=\"\" />\r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	银色(Core i5 处理器/8GB内存/128GB SSD闪存/Retina屏 MF839CH/A)\r\n</h1>', '2016-06-03 10:05:00', '2016-06-03 10:03:00');
INSERT INTO `product` VALUES ('8', 'ThinkPad X1 Carbon (20FBA00XCD) 14英寸超极笔记本电脑', '11', 'thinkpad-x1.jpg', '300', '0', '9999.00', '8999.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/thinkpad-x1-1.jpg\" alt=\"\" />\r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	i5-6200U 8G 192GB SSD FHD IPS Win10 64位\r\n</h1>', '2016-06-03 10:10:45', '2016-06-03 10:09:00');
INSERT INTO `product` VALUES ('9', '外星人（Alienware）ALW13E-4728S 13.3英寸笔记本电脑', '11', 'alienware.jpg', '400', '0', '12999.00', '12999.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/alienware-1.jpg\" alt=\"\" /> <img src=\"./img/alienware-2.jpg\" alt=\"\" /> <img src=\"./img/alienware-3.jpg\" alt=\"\" /> \r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	（I7-6500U 8G 256GB SSD GTX 960M 4G独显 Win10）银\r\n</h1>', '2016-06-03 10:31:11', '2016-06-03 10:28:00');
INSERT INTO `product` VALUES ('10', '戴尔（DELL）XPS 13-9350-R1708 13.3英寸微边框笔记本电脑', '11', 'dell.jpg', '125', '0', '8999.00', '8999.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/dell-1.jpg\" alt=\"\" />\n<img src=\"./img/dell-2.jpg\" alt=\"\" />\n<img src=\"./img/dell-3.jpg\" alt=\"\" />\r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	（ i7-6500U 8G 256GSSD Win10）银\r\n</h1>', '2016-06-03 10:34:08', '2016-06-03 10:33:00');
INSERT INTO `product` VALUES ('11', '戴尔（DELL）Vostro 3800-R6308 台式电脑', '12', 'dell-desktop.jpg', '1000', '0', '3699.00', '3199.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/dell-desktop-1.jpg\" alt=\"\" />\n<img src=\"./img/dell-desktop-2.jpg\" alt=\"\" />\r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	（i3-4170 4G 500G DVD 三年上门 三年硬盘保留Win7）黑\r\n</h1>', '2016-06-03 10:39:46', '2016-06-03 10:38:00');
INSERT INTO `product` VALUES ('12', '联想（Lenovo）H3050台式电脑', '12', 'lenovo.jpg', '1000', '0', '3399.00', '3099.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/lenovo-1.jpg\" alt=\"\" />\n<img src=\"./img/lenovo-2.jpg\" alt=\"\" />\r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	（i3-4170 4G 500G 集显 DVD 千兆网卡 Win10）20英寸\r\n</h1>', '2016-06-03 10:43:17', '2016-06-03 10:42:00');
INSERT INTO `product` VALUES ('13', 'Apple iPad mini 4 平板电脑 7.9英寸', '11', 'ipad.png', '600', '0', '3288.00', '3288.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/ipad-1.jpg\" alt=\"\" />\n<img src=\"./img/ipad-2.jpg\" alt=\"\" />\n<img src=\"./img/ipad-3.jpg\" alt=\"\" />\r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	（64G WLAN版/A8芯片/Retina显示屏/Touch ID技术 MK9J2CH）金色\r\n</h1>', '2016-06-03 10:46:54', '2016-06-03 10:45:00');
INSERT INTO `product` VALUES ('14', '小米（XIAOMI）平板电脑2 WIFI版 7.9英寸', '11', 'mipad.jpg', '400', '0', '999.00', '999.00', '<div style=\"text-align:center;\">\r\n	<img src=\"./img/mipad-1.jpg\" alt=\"\" />\n<img src=\"./img/mipad-2.jpg\" alt=\"\" />\n<img src=\"./img/mipad-3.jpg\" alt=\"\" />\n<img src=\"./img/mipad-4.jpg\" alt=\"\" />\n<img src=\"./img/mipad-5.jpg\" alt=\"\" /><br />\r\n</div>', '<h1 style=\"font-size:16px;font-family:arial, \'microsoft yahei\';color:#666666;background-color:#FFFFFF;\">\r\n	（Intel Z8500 2G/16G 500W/800W）香槟金\r\n</h1>', '2016-06-03 10:52:14', '2016-06-03 10:50:00');
INSERT INTO `product` VALUES ('15', '乐视（Letv）乐1S 太子妃版 32GB 银色 移动联通4G手机 双卡双待', '9', 'letv.jpg', '666', '0', '999.00', '999.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/letv-1.jpg\" alt=\"\" />\r\n<img src=\"./img/letv-2.jpg\" alt=\"\" />\r\n<img src=\"./img/letv-3.jpg\" alt=\"\" />\r\n</div>', '<span style=\"color:#E3393C;font-family:arial, \'microsoft yahei\';font-size:14px;line-height:20px;background-color:#FFFFFF;\">镜面指纹识别+快速充电+八核处理器+3GB+32GB内存！</span>', '2016-06-03 11:04:39', '2016-06-03 11:03:00');
INSERT INTO `product` VALUES ('16', '创维（Skyworth）55X5 55英寸 六核智能酷开网络平板液晶电视(黑色)', '14', 'dajiadian01.jpg', '100', '100', '2700.00', '2000.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/dajiadian0101.jpg\" alt=\"\" />\r\n<img src=\"./img/dajiadian0102.jpg\" alt=\"\" />\r\n<img src=\"./img/dajiadian0103.jpg\" alt=\"\" />\r\n<img src=\"./img/dajiadian0104.jpg\" alt=\"\" />\r\n<img src=\"./img/dajiadian0105.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-05 15:59:00', '2016-07-05 15:59:05');
INSERT INTO `product` VALUES ('17', 'Beats Solo2 Wireless蓝牙耳机头戴式 无线耳麦手机耳机', '13', 'yingyinyule01.jpg', '100', '100', '1998.00', '1500.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/yingyinyule0101.jpg\" alt=\"\" />\r\n<img src=\"./img/yingyinyule0102.jpg\" alt=\"\" />\r\n<img src=\"./img/yingyinyule0103.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 11:21:38', '2016-07-06 11:21:41');
INSERT INTO `product` VALUES ('18', 'Edifier/漫步者 R101V笔记本电脑音响 多媒体台式小音箱2.1低音炮', '13', 'yingyinyule02.jpg', '100', '100', '150.00', '130.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/yingyinyule0201.jpg\" alt=\"\" />\r\n<img src=\"./img/yingyinyule0202.jpg\" alt=\"\" />\r\n<img src=\"./img/yingyinyule0203.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 11:23:25', '2016-07-06 11:23:28');
INSERT INTO `product` VALUES ('19', 'inphic/英菲克 I9 8核网络机顶盒无线高清硬盘播放器八核电视盒子', '14', 'yingyinyule03.jpg', '100', '50', '399.00', '350.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/yingyinyule0301.jpg\" alt=\"\" />\r\n<img src=\"./img/yingyinyule0302.jpg\" alt=\"\" />\r\n<img src=\"./img/yingyinyule0303.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 13:47:05', '2016-07-06 13:47:09');
INSERT INTO `product` VALUES ('20', '飞利浦电动剃须刀S5080充电式全身水洗多功能三刀头S5000系列', '15', 'xiaojiadian01.jpg', '500', '50', '699.00', '599.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/xiaojiadian0101.jpg\" alt=\"\" />\r\n<img src=\"./img/xiaojiadian0102.jpg\" alt=\"\" />\r\n<img src=\"./img/xiaojiadian0103.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 13:56:17', '2016-07-06 13:56:20');
INSERT INTO `product` VALUES ('21', '格力加湿器 家用静音 卧室迷你小型 办公室空气净化大雾量增湿器', '16', 'xiaojiadian02.jpg', '500', '50', '799.00', '599.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/xiaojiadian0201.jpg\" alt=\"\" />\r\n<img src=\"./img/xiaojiadian0202.jpg\" alt=\"\" />\r\n<img src=\"./img/xiaojiadian0203.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 13:59:09', '2016-07-06 13:59:11');
INSERT INTO `product` VALUES ('22', 'SIEMENS/西门子 BCD-610W(KA82NV06TI)双开门双门对开门电冰箱', '17', 'bingxiang01.jpg', '500', '50', '6999.00', '6530.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/bingxiang0101.jpg\" alt=\"\" />\r\n<img src=\"./img/bingxiang0102.jpg\" alt=\"\" />\r\n<img src=\"./img/bingxiang0103.jpg\" alt=\"\" />\r\n<img src=\"./img/bingxiang0104.jpg\" alt=\"\" />\r\n<img src=\"./img/bingxiang0105.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 14:09:33', '2016-07-06 14:09:36');
INSERT INTO `product` VALUES ('23', '省电Kelon/科龙 KFR-35GW/ERQBN3(1L04) 大1.5匹智能冷暖空调挂机', '18', 'kongtiao01.jpg', '500', '100', '2600.00', '2300.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/kongtiao0101.jpg\" alt=\"\" />\r\n<img src=\"./img/kongtiao0102.jpg\" alt=\"\" />\r\n<img src=\"./img/kongtiao0103.jpg\" alt=\"\" />\r\n<img src=\"./img/kongtiao0104.jpg\" alt=\"\" />\r\n<img src=\"./img/kongtiao0105.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 14:33:15', '2016-07-06 14:33:18');
INSERT INTO `product` VALUES ('24', 'SIEMENS/西门子 XQG80-WM12P1681W 8KG变频滚筒 银色全自动洗衣机', '19', 'xiyiji01.jpg', '500', '500', '1999.00', '1500.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/xiyiji0101.jpg\" alt=\"\" />\r\n<img src=\"./img/xiyiji0102.jpg\" alt=\"\" /> \r\n<img src=\"./img/xiyiji0103.jpg\" alt=\"\" /> \r\n\r\n</div>', null, '2016-07-06 14:34:57', '2016-07-06 14:35:00');
INSERT INTO `product` VALUES ('25', '单反外观Sony/索尼 DSC-H400高清长焦射月数码照相机', '20', 'sheyingshexiang01.jpg', '500', '500', '6999.00', '6850.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/sheyingshexiang0101.jpg\" alt=\"\" />\r\n<img src=\"./img/sheyingshexiang0102.jpg\" alt=\"\" />\r\n<img src=\"./img/sheyingshexiang0103.jpg\" alt=\"\" />\r\n<img src=\"./img/sheyingshexiang0104.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 14:42:39', '2016-07-06 14:42:41');
INSERT INTO `product` VALUES ('26', '【2016新品】DJI大疆精灵Phantom 4新一代一体式智能无人机', '22', 'chaopin01.jpg', '500', '500', '8999.00', '8700.00', '<div style=\"text-align:center;\">\r\n<img src=\"./img/chapin0101.jpg\" alt=\"\" />\r\n<img src=\"./img/chapin0101.jpg\" alt=\"\" />\r\n<img src=\"./img/chapin0101.jpg\" alt=\"\" />\r\n<img src=\"./img/chapin0102.jpg\" alt=\"\" />\r\n<img src=\"./img/chapin0103.jpg\" alt=\"\" />\r\n<img src=\"./img/chapin0104.jpg\" alt=\"\" />\r\n<img src=\"./img/chapin0105.jpg\" alt=\"\" />\r\n<img src=\"./img/chapin0106.jpg\" alt=\"\" />\r\n</div>', null, '2016-07-06 15:24:18', '2016-07-06 15:24:20');
