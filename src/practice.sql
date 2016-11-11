/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50537
Source Host           : localhost:3307
Source Database       : practice

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-07-09 06:12:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `announcement`
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `people` varchar(20) NOT NULL,
  `pubdate` date NOT NULL,
  `announcement` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('2', '关于华软放假的通知', 'hjc', '2016-07-27', '华软将于7号放假，7号晚上10点男生宿舍区域将断网！请告知');
INSERT INTO `announcement` VALUES ('3', '关于开学时间的通知', 'hjc', '2016-07-21', '华软学院将于9.5号开学，请互相告知！');
INSERT INTO `announcement` VALUES ('4', '放假啦！放假啦！', 'hjc', '2016-07-09', '明天过后就放假啦！');

-- ----------------------------
-- Table structure for `buyproduct`
-- ----------------------------
DROP TABLE IF EXISTS `buyproduct`;
CREATE TABLE `buyproduct` (
  `username` varchar(50) NOT NULL,
  `id` int(11) NOT NULL,
  KEY `productid` (`id`),
  CONSTRAINT `productid` FOREIGN KEY (`id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyproduct
-- ----------------------------

-- ----------------------------
-- Table structure for `card`
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `CardID` int(4) NOT NULL AUTO_INCREMENT,
  `CardNumber` varchar(30) NOT NULL,
  `CardPassword` varchar(30) NOT NULL,
  `Point` int(4) NOT NULL,
  `TimeOutDate` datetime NOT NULL,
  `isUse` tinyint(1) NOT NULL DEFAULT '0',
  `username` varchar(20) DEFAULT NULL,
  `UserTime` datetime DEFAULT NULL,
  `AddTime` datetime NOT NULL,
  PRIMARY KEY (`CardID`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------

-- ----------------------------
-- Table structure for `card_name`
-- ----------------------------
DROP TABLE IF EXISTS `card_name`;
CREATE TABLE `card_name` (
  `username` varchar(20) NOT NULL,
  `CardID` int(4) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card_name
-- ----------------------------

-- ----------------------------
-- Table structure for `companyuserinfo`
-- ----------------------------
DROP TABLE IF EXISTS `companyuserinfo`;
CREATE TABLE `companyuserinfo` (
  `CorpID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `C_Name` varchar(50) NOT NULL,
  `C_PostCode` char(10) NOT NULL,
  `C_Province` varchar(50) NOT NULL,
  `C_City` varchar(50) NOT NULL,
  `C_Address` varchar(50) NOT NULL,
  `C_ConactName` varchar(50) NOT NULL,
  `C_Vocation` varchar(50) NOT NULL,
  `C_WebSite` varchar(50) NOT NULL,
  `C_size` varchar(50) NOT NULL,
  `C_Tel` char(10) NOT NULL,
  `C_Capital` varchar(50) NOT NULL,
  `C_BankName` varchar(50) NOT NULL,
  PRIMARY KEY (`CorpID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of companyuserinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('2', 'hjc', '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO `manager` VALUES ('3', 'cal', 'Qpf0SxOVUjUkWySXOZ16kw==');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` varchar(50) NOT NULL,
  `Version` varchar(50) NOT NULL,
  `PType` varchar(50) NOT NULL,
  `useNum` int(11) NOT NULL DEFAULT '0',
  `bound` int(11) unsigned NOT NULL DEFAULT '0',
  `Content` varchar(50) NOT NULL,
  `addTime` datetime NOT NULL,
  `URL_1` varchar(50) NOT NULL,
  `UserDel` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('9', 'DS34', 'ui1.01', '电子书', '25', '10', '数据结构', '2016-07-07 00:32:16', '1467822736481新建文本文档.txt', '0');
INSERT INTO `product` VALUES ('10', 'J8fg', 'vdui1.0', '文档', '9', '50', '这是一本讲述java的书', '2016-07-07 10:54:43', '1467860083454老师工号.txt', '0');
INSERT INTO `product` VALUES ('11', 'ui887', '5.0', 'jar包', '0', '80', '上传文件的jar包', '2016-07-08 17:33:01', '1467970381499commons-fileupload-1.2.2.jar', '0');
INSERT INTO `product` VALUES ('12', 'po8', '1.0', 'jar包', '3', '30', 'jstl jar包', '2016-07-08 17:33:42', '1467970422880jstl.jar', '0');
INSERT INTO `product` VALUES ('13', 'pl33', '2.1', '文档', '0', '60', '这是一个java源代码文件', '2016-07-08 17:34:57', '1467970497331AnnouncementDao.java', '0');
INSERT INTO `product` VALUES ('14', 'jo88', '2.0', '文档', '0', '20', '项目实训文档', '2016-07-09 01:01:06', '1467997266909项目总结.docx', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `usernumber` varchar(10) NOT NULL COMMENT '用户编号，自动增长',
  `username` varchar(20) NOT NULL COMMENT '用户名，主键',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `question` varchar(20) NOT NULL COMMENT '安全问题',
  `answer` varchar(20) NOT NULL COMMENT '问题答案',
  `safecode` varchar(20) NOT NULL COMMENT '安全码',
  `email` varchar(30) NOT NULL COMMENT '电子邮件',
  `jifen` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `img` varchar(50) NOT NULL COMMENT '头像',
  `GroupNumber` varchar(20) NOT NULL COMMENT '会员组编号',
  `shenfen` char(10) NOT NULL COMMENT '身份',
  `point` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('HY0038', 'aaaaaa', 'C056Dl/oStNftflbnO6seQ==', '你的生日是几号?', 'aaaaaa', 'aaaaaa', 'aaaaaa@qq.com', '0', '14679982771238.jpg', '2', '企业会员', '40');
INSERT INTO `user` VALUES ('HY0022', 'bbbbbb', 'h18m/bHOzyDOtMoCgmPexg==', '你的生日是几号?', 'bbbbbb', 'bbbbbb', 'bbbbbb@qq.com', '0', '1467993599946img07.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0023', 'cccccc', 'wfaOwGtJCz7LQGaxsTqe6Q==', '你的父亲是?', 'cccccc', 'cccccc', 'cccccc@qq.com', '0', '146799362589301.png', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0003', 'dddddd', 'mArCF8a1Hn3EEEC+we3+yA==', '你的父亲是？', 'dddddd', 'dddddd', 'dddddd@qq.com', '0', '14677938304444.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0024', 'eeeeee', 'zYfNXvdToG7nn8ddx8/mbA==', '你的父亲是?', 'eeeeee', 'eeeeee', 'eeeeee@qq.com', '0', '146799365079806.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0025', 'ffffff', '7tjNxADf1OyF3/cKFwBmtw==', '你的母亲是？', 'ffffff', 'ffffff', 'ffffff@qq.com', '0', '146799399845005.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0006', 'gggggg', 'nK/u8I2y3UdwmKApPnH5Cg==', '你的生日是什么时候', 'gggggg', 'gggggg', 'gggggg@qq.com', '0', '14677939238637.jpg', '2', '企业会员', '0');
INSERT INTO `user` VALUES ('HY0026', 'hhhhhh', '8UApIX/156UM3H5w9obPKQ==', '你的生日是几号?', 'hhhhhh', 'hhhhhh', 'hhhhhh@qq.com', '0', '14679940382432222.gif', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0027', 'iiiiii', 'bS/h1vCXgWlJovVO09mGqA==', '你的父亲是?', 'iiiiii', 'iiiiii', 'iiiiii@qq.com', '0', '1467994060868th.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0037', 'jjjjjj', 'Or8A+mG/ri//kTM3XhQkFg==', '你的父亲是?', 'jjjjjj', 'jjjjjj', 'jjjjjj@qq.com', '0', '14679982080355.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0008', 'kkkkkk', 'wIrFauEUVWbyzlTLvqNfow==', '你的父亲是？', 'kkkkkk', 'kkkkkk', 'kkkkkk@qq.com', '0', '14677939726489.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0009', 'llllll', 'pz+GrkCK9wtnFBhD5xMHIw==', '你的父亲是？', 'llllll', 'llllll', 'llllll@qq.com', '0', '146779399653610.jpg', '2', '企业会员', '0');
INSERT INTO `user` VALUES ('HY0010', 'mmmmmm', 'mu45Dxk0UCjwO7FsWIVQ4Q==', '你的父亲是？', 'mmmmmm', 'mmmmmm', 'mmmmmm@qq.com', '0', '146779401789411.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0029', 'nnnnnn', 'TItAAY+JPUOE/P5gMCy0ag==', '你的生日是几号?', 'nnnnnn', 'nnnnnn', 'nnnnnn@qq.com', '0', '146799423938704.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0018', 'oooooo', 'mYKyp/zqruLIREtQhq7gCA==', '你的母亲是？', 'oooooo', 'oooooo', 'oooooo@qq.com', '0', '1467965634231male.jpg', '2', '企业会员', '52');
INSERT INTO `user` VALUES ('HY0012', 'ppppp', '6IK3K8z8KtV4wnsNm0cqFA==', '你的母亲是？', 'pppppp', 'ppppppp', 'pppppp@qq.com', '0', '14677937162647.jpg', '2', '企业会员', '0');
INSERT INTO `user` VALUES ('HY0030', 'qqqqqq', 'NDscSj6nIbLWQPyHANsPNg==', '你的生日是几号?', 'qqqqqq', 'qqqqqq', 'qqqqqq@qq.com', '0', '146799501013902.jpg', '2', '企业会员', '0');
INSERT INTO `user` VALUES ('HY0014', 'rrrrrr', '/y8k+LbSU7tai8VXKMpzcg==', '你的父亲是？', 'rrrrrr', 'rrrrrr', 'rrrrrr@qq.com', '0', '146779408382214.jpg', '1', '个人会员', '0');
INSERT INTO `user` VALUES ('HY0031', 'ssssss', 'rxXV/azV/f6jAOiKjiU+gg==', '你的父亲是?', 'ssssss', 'ssssss', 'ssssss@qq.com', '0', '1467995029849female.jpg', '2', '企业会员', '0');
INSERT INTO `user` VALUES ('HY0032', 'tttttt', 'vMcg8pgdGmjb1m/9Z1YMNw==', '你的母亲是？', 'tttttt', 'tttttt', 'tttttt@qq.com', '0', '146799505226705.jpg', '2', '企业会员', '0');
INSERT INTO `user` VALUES ('HY0033', 'uuuuuu', 'jeHr5SIBltas20hvNG/hYg==', '你的母亲是？', 'uuuuuu', 'uuuuuu', 'uuuuuu@qq.com', '0', '146799507194806.jpg', '2', '企业会员', '0');
INSERT INTO `user` VALUES ('HY0034', 'vvvvvv', 'RpP7shW4yhWmkA8M+hZM3A==', '你的生日是几号?', 'vvvvvv', 'vvvvvv', 'vvvvvv@qq.com', '0', '14679951631982.jpg', '2', '企业会员', '2');
INSERT INTO `user` VALUES ('HY0035', 'wwwwww', '14XJnSmKTp5uE/6Z5gLvQg==', '你的生日是几号?', 'wwwwww', 'wwwwww', 'wwwwww@qq.com', '0', '14679951851277.jpg', '2', '企业会员', '0');
INSERT INTO `user` VALUES ('HY0036', 'xxxxxx', '2tOjeqnVBoi1FXaYrP167g==', '你的生日是几号?', 'xxxxxx', 'xxxxxx', 'xxxxxx@qq.com', '0', '146799520095815.jpg', '2', '企业会员', '16');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `uname` varchar(50) NOT NULL COMMENT '真实姓名',
  `sex` char(10) NOT NULL COMMENT '性别',
  `birthday` date NOT NULL,
  `zjlb` varchar(50) NOT NULL COMMENT '证件类别',
  `zjhm` char(20) NOT NULL COMMENT '证件号码',
  `szsf` varchar(50) NOT NULL COMMENT '所在省份',
  `city` varchar(50) NOT NULL COMMENT '城市',
  `addr` varchar(50) NOT NULL COMMENT '地址',
  `yzbm` varchar(50) NOT NULL COMMENT '邮政编码',
  `Tel` varchar(50) NOT NULL COMMENT '电话',
  `hompage` varchar(50) NOT NULL COMMENT '主页',
  `qq` varchar(50) NOT NULL COMMENT 'qq',
  KEY `username` (`username`),
  CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
DROP TRIGGER IF EXISTS `deleteuser`;
DELIMITER ;;
CREATE TRIGGER `deleteuser` BEFORE DELETE ON `user` FOR EACH ROW BEGIN  
set @count = (select count(*) from userinfo where username = old.username);  
if @count = 0 then  
set @Comp = 'keep it';  
else   
delete from userinfo where username =  old.username;  
set @Comp = 'drop company';  
end if;  
  
delete from companyuserinfo where username = old.username;  
  
END
;;
DELIMITER ;
