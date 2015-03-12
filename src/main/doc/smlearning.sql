/*
MySQL Data Transfer
Source Host: localhost
Source Database: smlearning
Target Host: localhost
Target Database: smlearning
Date: 2014/6/24 8:35:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for class_book
-- ----------------------------
DROP TABLE IF EXISTS `class_book`;
CREATE TABLE `class_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课堂编号',
  `name` varchar(200) NOT NULL,
  `image_url` varchar(100) NOT NULL COMMENT '课堂板书',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(300) NOT NULL COMMENT '课程名称',
  `courseware_category_id` bigint(20) NOT NULL COMMENT '课程分类编号',
  `lectuer` varchar(200) NOT NULL COMMENT '主讲人',
  `hour` int(10) NOT NULL COMMENT '学时(分钟)',
  `description` text COMMENT '课程简介',
  `teacher_description` text COMMENT '老师简介',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '态状:0启用;1停用',
  `pic` varchar(100) NOT NULL DEFAULT '0' COMMENT '是否上传',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `url` varchar(300) NOT NULL COMMENT '课件网址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='课程';

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(300) NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级课程分类编号',
  `sort_flag` int(3) NOT NULL COMMENT '课程分类排序',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_courseware
-- ----------------------------
DROP TABLE IF EXISTS `course_courseware`;
CREATE TABLE `course_courseware` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '编号',
  `course_id` bigint(20) NOT NULL COMMENT '课程编号',
  `courseware_id` bigint(20) NOT NULL COMMENT '课件编号',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `created_time` date NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_plan
-- ----------------------------
DROP TABLE IF EXISTS `course_plan`;
CREATE TABLE `course_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '教学计划',
  `name` varchar(200) NOT NULL,
  `image_url` varchar(100) NOT NULL COMMENT '教学计划资源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_schedule
-- ----------------------------
DROP TABLE IF EXISTS `course_schedule`;
CREATE TABLE `course_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `week_name` varchar(100) NOT NULL COMMENT '星期名称',
  `level_one` varchar(10) NOT NULL COMMENT '一级',
  `level_two` varchar(10) NOT NULL COMMENT '二级',
  `level_three` varchar(10) NOT NULL COMMENT '三级',
  `level_four` varchar(10) NOT NULL COMMENT '四级',
  `level_five` varchar(10) NOT NULL COMMENT '五级',
  `level_six` varchar(10) NOT NULL COMMENT '六级',
  `level_seven` varchar(10) NOT NULL COMMENT '7级',
  `level_eight` varchar(10) NOT NULL COMMENT '八级',
  `super_grade` varchar(100) DEFAULT NULL COMMENT '年级',
  `super_class` varchar(100) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_table
-- ----------------------------
DROP TABLE IF EXISTS `course_table`;
CREATE TABLE `course_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程表编号',
  `name` varchar(200) NOT NULL,
  `image_url` varchar(100) NOT NULL COMMENT '课程表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for courseware
-- ----------------------------
DROP TABLE IF EXISTS `courseware`;
CREATE TABLE `courseware` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(300) NOT NULL COMMENT '课件名称',
  `courseware_category_id` bigint(20) NOT NULL COMMENT '课件分类编号',
  `url` varchar(300) NOT NULL COMMENT '课件网址',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `pic` varchar(100) DEFAULT NULL COMMENT '电子书图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='课件';

-- ----------------------------
-- Table structure for courseware_category
-- ----------------------------
DROP TABLE IF EXISTS `courseware_category`;
CREATE TABLE `courseware_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(300) NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级课件分类编号',
  `sort_flag` int(3) NOT NULL DEFAULT '0' COMMENT '课件分类排序',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `use_type` int(1) DEFAULT NULL COMMENT '类型编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='课件分类';

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程进度编号',
  `class_id` bigint(20) NOT NULL COMMENT '班级编号',
  `subject_id` bigint(20) NOT NULL COMMENT '科目编号',
  `day` int(2) NOT NULL COMMENT '星期',
  `thetime` int(2) NOT NULL COMMENT '课程节数',
  `year` int(10) NOT NULL COMMENT '年数',
  `term` int(2) NOT NULL COMMENT '学期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lesson_plan
-- ----------------------------
DROP TABLE IF EXISTS `lesson_plan`;
CREATE TABLE `lesson_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程计划编号',
  `class_id` bigint(20) NOT NULL COMMENT '班级编号',
  `subject_id` bigint(20) NOT NULL COMMENT '科目编号',
  `day` int(2) NOT NULL COMMENT '星期',
  `year` int(10) NOT NULL COMMENT '年数',
  `content` varchar(3000) NOT NULL COMMENT '教学内容',
  `homework` varchar(3000) NOT NULL COMMENT '课后作业',
  `c_date` varchar(50) NOT NULL COMMENT '日期',
  `thetime` int(2) DEFAULT NULL COMMENT '节数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '登录帐号',
  `password` varchar(100) NOT NULL DEFAULT '999999' COMMENT '登录密码',
  `actual_name` varchar(100) NOT NULL COMMENT '姓名',
  `department` varchar(100) DEFAULT NULL COMMENT '部门',
  `post` varchar(100) DEFAULT NULL COMMENT '职务',
  `degree` int(1) NOT NULL DEFAULT '1' COMMENT '身份（单位管理员,教师）',
  `use_type` int(1) NOT NULL DEFAULT '0' COMMENT '教师类型（老师、助教）',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态:0启用1停用',
  `class_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for online_forum
-- ----------------------------
DROP TABLE IF EXISTS `online_forum`;
CREATE TABLE `online_forum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '在线交流编号',
  `name` varchar(1000) DEFAULT NULL COMMENT '主题',
  `question` varchar(3000) DEFAULT NULL COMMENT '问题',
  `root_id` bigint(20) DEFAULT NULL COMMENT '根贴编号',
  `child_num` int(10) DEFAULT NULL COMMENT '跟贴数量',
  `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(200) DEFAULT NULL COMMENT '创建人',
  `class_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for organ
-- ----------------------------
DROP TABLE IF EXISTS `organ`;
CREATE TABLE `organ` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '联系人',
  `name` varchar(300) NOT NULL COMMENT '单位名称',
  `grade` varchar(200) DEFAULT NULL COMMENT '单位级别',
  `degree` int(1) DEFAULT '1' COMMENT '身份',
  `linkman` varchar(100) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `server_ip` varchar(100) DEFAULT NULL COMMENT '服务器IP地址',
  `server_port` varchar(10) NOT NULL DEFAULT '80' COMMENT '服务器端口',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_class
-- ----------------------------
DROP TABLE IF EXISTS `sys_class`;
CREATE TABLE `sys_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_key
-- ----------------------------
DROP TABLE IF EXISTS `sys_key`;
CREATE TABLE `sys_key` (
  `id` bigint(20) NOT NULL,
  `key_name` varchar(100) NOT NULL COMMENT '术语名称',
  `key_value` varchar(1000) DEFAULT NULL COMMENT '术语值',
  `can_modify` int(1) NOT NULL DEFAULT '1' COMMENT '是否可维护',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `organ_name` varchar(300) NOT NULL COMMENT '单位名称',
  `organ_grade` varchar(200) NOT NULL COMMENT '单位级别',
  `organ_degree` int(1) NOT NULL DEFAULT '1' COMMENT '单位身份（上级，本级，下级）',
  `sent_time` datetime DEFAULT NULL COMMENT '送达时间',
  `can_send_all` int(1) DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_subject
-- ----------------------------
DROP TABLE IF EXISTS `sys_subject`;
CREATE TABLE `sys_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '科目编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_course_note
-- ----------------------------
DROP TABLE IF EXISTS `user_course_note`;
CREATE TABLE `user_course_note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) NOT NULL COMMENT '学员编号',
  `courseware_id` bigint(20) DEFAULT NULL COMMENT '课件编号',
  `note` text COMMENT '文本内容',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '龄年',
  `name` varchar(100) NOT NULL COMMENT '学号',
  `password` varchar(100) NOT NULL DEFAULT '888888' COMMENT '密码',
  `actual_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `age` int(3) DEFAULT NULL COMMENT '龄年',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态0启用1停用',
  `nation` varchar(100) DEFAULT NULL COMMENT '民族',
  `birthdate` date DEFAULT NULL COMMENT '出生日期',
  `address` varchar(2000) DEFAULT NULL COMMENT '通信地址',
  `class_id` bigint(20) DEFAULT NULL COMMENT ' 班级编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=420 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `class_book` VALUES ('1', '课堂板书', '/uploadFile/file/kechengbiao.pdf');
INSERT INTO `course` VALUES ('2', '英语课程', '3', '周老师', '2', '&bull;	进行课程的总结和答疑，讲解实际项目中如何分析需求、针对需求进行设计，对选择大数据各种处理框架。\r\n&bull;	此外，讲解任务调度的使用，目前暂定讲解Azkaban，可能后期会有变动，会讲解Oozie，依据具体情况而定。', '主要从事对Hadoop技术的实际应用开发，以及其性能优化工作。熟悉HDFS架构以及Mapreduce原理和相关编程；在数据分析、数据挖掘、云计算，云存储有多年开发经验，在Hadoop处理大数据方面有自己独到的见解，致力于让Hadoop大数据应用更简单。参加过众多大型项目，包括《Xxx省公安厅车辆轨迹大数据研判应用系统》建设、广东肇庆市公安局稽查布控系统、山东枣庄市公安局卡口系统、奇瑞异地工厂EAI系统项目、服务管理系统(SMS)、信息管理与整合系统等组织开发工作。', '0', '/uploadFile/pic/res6.jpg', '2014-01-30', '1', '/uploadFile/file/video_7.3gp');
INSERT INTO `course` VALUES ('3', '数学课程', '1', '沈老师', '20', '据统计，1月全国100个城市新建住宅均价环比上涨0.63%，低于前一个月的0.70%。其中，上海环比上涨1.31%。同比连续14个月上涨，涨幅较之前缩小0.41个百分点至11.10%，是2013年1月以来首次缩小。', '中指院预计，2014年，少数热点城市供不应求矛盾依然明显，房价上涨压力继续存在，武汉、重庆等二、三线城市供需基本均衡，房价总体平稳；少数三、四线供应持续高于需求，若货币政策进一步趋紧，需求不足和房价下跌风险可能显现。', '0', '/uploadFile/pic/res5.jpg', '2014-02-06', '1', '/uploadFile/file/123.3gp');
INSERT INTO `course` VALUES ('9', '物理课', '14', '许老师', '10', '本节是对hadoop核心之一&mdash;&mdash;HDFS的讲解。HDFS是所有hadoop操作的基础，属于基本的内容。对本节内容的理解直接影响以后所有课程的学习。在本节学习中，我们会讲述hdfs的体系结构，以及使用shell、java不同方式对hdfs的操作。在工作中，这两种方式都非常常用。学会了本节内容，就可以自己开发网盘应用了。在本节学习中，我们不仅对理论和操作进行讲解，也会讲解hdfs的源代码，方便部分学员以后对hadoop源码进行修改。', '&bull;	该项目是为本课程量身定做的，非常适合我们本阶段课程学习。有的同学觉得应该多介绍项目，其实如果做过项目后，就会发现项目的思路是相同的，只是业务不同而已。大家写过这个项目后，就对hadoop的各个框架在项目中是如何使用的，有个比较清晰的认识，对hadoop与javaEE结合有个比较清晰的认识了。', '0', '/uploadFile/pic/res7.jpg', '2014-01-30', '1', '/uploadFile/file/video_7.3gp');
INSERT INTO `course` VALUES ('10', '公司战略课程', '3', '王老师', '12', '中国移动推出4G套餐，40元包300兆流量，按照每秒百兆的速率，这个套餐3秒就用完了，3秒40元，一个小时就是48000元。如果晚上忘了关闭4G连接，一觉醒来，你的房子都快成移动公司的了。&rdquo;当当网CEO李国庆前几日发出的这则微博段子，24小时内便得到近3万次转载，1万次点赞。', '按照现有4G资费套餐，网友算了一笔账，如果使用4G网络观看一部大小1GB左右的电影，即便算上当前赠送的每月1GB流量，仍需要办理价格138元的4G飞享套餐，或50元的流量可选包。有网友表示，这资费看视频还不如直接去电影院看电影呢！\r\n\r\n　　北京移动金融街营业厅工作人员介绍，尽管4G流量价格超出套餐部分按照0.29元/兆计算，比2G、3G时代1元/兆降低不少，但也有用户反映，流量消耗速度加快，甚至一天能耗掉整月流量。', '0', '/uploadFile/pic/res12.jpg', '2014-02-07', '1', '/uploadFile/file/video_9.3gp');
INSERT INTO `course` VALUES ('11', '化学课程', '3', '王老师', '23', '国务院总理李克强2月7日主持召开国务院常务会议，听取关于2013年全国人大代表建议和全国政协委员提案办理工作汇报，决定合并新型农村社会养老保险和城镇居民社会养老保险，建立全国统一的城乡居民基本养老保险制度', '会议认为，政府的权力来自人民，各项工作和决策必须体现人民意志。国务院各部门办理全国人大代表、全国政协委员提出的相关建议、提案，是接受全国人大及其常委会依法监督、全国政协民主监督的重要形式，也是政府法治建设的重要内容。2013年，由国务院部门牵头办理的建议', '0', '/uploadFile/pic/res10.jpg', '2014-02-07', '1', '/uploadFile/file/video_9.3gp');
INSERT INTO `course` VALUES ('12', '解题大全', '3', '周老师', '20', '会议强调，今年两会在即，各部门负责人届时要到会认真听取代表委员的意见建议。要以改革创新精神和法治理念，进一步做好建议和提案办理工作，把受领、办理建议和提案作为接受人民监督、回应人民呼声的重要渠道，完善办理工作制度，深入调研，', '明确按时办复、与代表委员直接沟通等&ldquo;硬要求&rdquo;，提高办理工作效率和质量。要建立和完善台账制度，将建议和提案办理纳入国务院及各部门年度督查工作计划，采取抽查、重点督办等形式，督促检查落实，探索逐步向社会公开办理结果，让办理工作成为政府转作风、办实事、解难题的过程，使政府更好服务人民群众。', '0', '/uploadFile/pic/res9.jpg', '2014-02-07', '1', '/uploadFile/file/video_9.3gp');
INSERT INTO `course_plan` VALUES ('1', '第一周', '/uploadFile/file/kechengbiao.pdf');
INSERT INTO `course_plan` VALUES ('2', '第二周', '/uploadFile/file/kechengbiao.pdf');
INSERT INTO `course_plan` VALUES ('4', '第三周', '/uploadFile/file/kechengbiao.pdf');
INSERT INTO `course_schedule` VALUES ('1', '星期一', ' 物理', '数学', '体育', '语文', ' 英语', '计算机', '物理', '生物', '高三', '九班');
INSERT INTO `course_schedule` VALUES ('2', '星期二', ' 数学', '语文', '体育', '计算机', ' 英语', '生物', '体育', ' 物理', '高三', '九班');
INSERT INTO `course_schedule` VALUES ('3', '星期三', '语文', '数学', '地理', '体育', '英语', '物理', '生物', '化学', '高三', '九班');
INSERT INTO `course_schedule` VALUES ('4', '星期四', '英语', '语文', '数学', '生物', '体育', '计算机', '物理', '化学', '高三', '九班');
INSERT INTO `course_schedule` VALUES ('5', '星期五', '语文', '数学', '物理', '生物', '英语', '体育', '化学', '计算机', '高三', '九班');
INSERT INTO `course_table` VALUES ('1', '课程表', '/uploadFile/file/kechengbiao.pdf');
INSERT INTO `course_table` VALUES ('5', '课程2', '/uploadFile/file/kechengbiao.pdf');
INSERT INTO `courseware` VALUES ('7', '语文件视频1', '2', '/uploadFile/file/高等数学.flv', '2014-01-10 01:12:00', '1', '/uploadFile/pic/1.jpg');
INSERT INTO `courseware` VALUES ('8', '语文课的心得', '53', '/uploadFile/file/化学必修.pdf', '2014-01-25 02:00:00', '1', '/uploadFile/pic/res2.jpg');
INSERT INTO `courseware` VALUES ('9', '数学课本1', '60', '/uploadFile/file/huaxue.pdf', '2014-01-25 03:00:00', '1', '/uploadFile/pic/res1.jpg');
INSERT INTO `courseware` VALUES ('29', '语文件视频2', '2', '/uploadFile/file/小学数学.flv', '2014-01-25 04:00:00', '1', '/uploadFile/pic/2.jpg');
INSERT INTO `courseware` VALUES ('30', '语文件视频3', '2', '/uploadFile/file/高等数学.flv', '2014-01-25 05:00:00', '1', '/uploadFile/pic/3.jpg');
INSERT INTO `courseware` VALUES ('31', '语文件视频4', '2', '/uploadFile/file/小学数学.flv', '2014-01-25 06:00:00', '1', '/uploadFile/pic/4.jpg');
INSERT INTO `courseware` VALUES ('33', '语文件视频5', '2', '/uploadFile/file/高等数学.flv', '2014-01-25 07:00:00', '1', '/uploadFile/pic/1.jpg');
INSERT INTO `courseware` VALUES ('35', 'java语言设计', '60', '/uploadFile/file/huaxue.pdf', '2014-02-07 08:00:00', '1', '/uploadFile/pic/res11.jpg');
INSERT INTO `courseware` VALUES ('36', '化学课题', '61', '/uploadFile/file/huaxue.pdf', '2014-02-07 09:00:00', '1', '/uploadFile/pic/res10.jpg');
INSERT INTO `courseware` VALUES ('37', '法律课业', '61', '/uploadFile/file/huaxue.pdf', '2014-02-07 01:00:00', '1', '/uploadFile/pic/res3.jpg');
INSERT INTO `courseware` VALUES ('38', 'c++作业', '60', '/uploadFile/file/huaxue.pdf', '2014-02-07 02:00:00', '1', '/uploadFile/pic/res8.jpg');
INSERT INTO `courseware` VALUES ('39', '化学作业', '63', '/uploadFile/file/huaxue1.pdf', '2014-02-13 03:00:00', '1', '/uploadFile/pic/kechejidu.jpg');
INSERT INTO `courseware_category` VALUES ('1', '数学', '0', '1', '2014-01-01', '1', '0');
INSERT INTO `courseware_category` VALUES ('2', '语文', '0', '2', '2014-01-01', '1', '0');
INSERT INTO `courseware_category` VALUES ('3', '英语', '0', '3', '2014-01-01', '1', '0');
INSERT INTO `courseware_category` VALUES ('14', '政台', '0', '6', '2014-01-01', '1', '0');
INSERT INTO `courseware_category` VALUES ('53', '语文', '0', '1', '2014-01-06', '1', '1');
INSERT INTO `courseware_category` VALUES ('60', '数学', '0', '2', '2014-01-06', '1', '1');
INSERT INTO `courseware_category` VALUES ('61', '地理', '0', '3', '2014-01-06', '1', '1');
INSERT INTO `courseware_category` VALUES ('62', '英语', '0', '4', '2014-01-06', '1', '1');
INSERT INTO `courseware_category` VALUES ('63', '作业', '0', '1', '2014-02-13', '1', '1');
INSERT INTO `courseware_category` VALUES ('64', 'test', '0', '6', '2014-02-15', '1', '1');
INSERT INTO `courseware_category` VALUES ('65', '33', '0', '3', '2014-05-21', '1', '1');
INSERT INTO `lesson` VALUES ('1', '1', '1', '1', '1', '2014', '1');
INSERT INTO `lesson` VALUES ('3', '1', '2', '2', '1', '2014', '1');
INSERT INTO `lesson` VALUES ('10', '1', '3', '3', '1', '2014', '1');
INSERT INTO `lesson` VALUES ('11', '1', '5', '4', '1', '2014', '1');
INSERT INTO `lesson` VALUES ('12', '1', '6', '5', '1', '2014', '1');
INSERT INTO `lesson` VALUES ('13', '1', '10', '6', '1', '2014', '1');
INSERT INTO `lesson` VALUES ('14', '1', '8', '1', '2', '2014', '1');
INSERT INTO `lesson` VALUES ('15', '1', '3', '2', '2', '2014', '1');
INSERT INTO `lesson` VALUES ('16', '1', '10', '3', '2', '2014', '1');
INSERT INTO `lesson` VALUES ('17', '1', '1', '4', '2', '2014', '1');
INSERT INTO `lesson` VALUES ('18', '1', '6', '5', '2', '2014', '1');
INSERT INTO `lesson` VALUES ('19', '1', '8', '6', '2', '2014', '1');
INSERT INTO `lesson` VALUES ('20', '1', '10', '1', '3', '2014', '1');
INSERT INTO `lesson` VALUES ('21', '1', '1', '2', '3', '2014', '1');
INSERT INTO `lesson` VALUES ('22', '1', '9', '3', '3', '2014', '1');
INSERT INTO `lesson` VALUES ('23', '1', '7', '4', '3', '2014', '1');
INSERT INTO `lesson` VALUES ('24', '1', '9', '5', '3', '2014', '1');
INSERT INTO `lesson` VALUES ('25', '1', '3', '6', '3', '2014', '1');
INSERT INTO `lesson` VALUES ('26', '1', '8', '1', '4', '2014', '1');
INSERT INTO `lesson` VALUES ('27', '1', '9', '2', '4', '2014', '1');
INSERT INTO `lesson` VALUES ('28', '1', '1', '3', '4', '2014', '1');
INSERT INTO `lesson` VALUES ('29', '1', '2', '4', '4', '2014', '1');
INSERT INTO `lesson` VALUES ('30', '1', '7', '5', '4', '2014', '1');
INSERT INTO `lesson` VALUES ('31', '1', '7', '6', '4', '2014', '1');
INSERT INTO `lesson` VALUES ('32', '1', '2', '1', '5', '2014', '1');
INSERT INTO `lesson` VALUES ('33', '1', '3', '2', '5', '2014', '1');
INSERT INTO `lesson` VALUES ('34', '1', '1', '3', '5', '2014', '1');
INSERT INTO `lesson` VALUES ('35', '1', '9', '4', '5', '2014', '1');
INSERT INTO `lesson` VALUES ('36', '1', '6', '5', '5', '2014', '1');
INSERT INTO `lesson` VALUES ('37', '1', '10', '6', '5', '2014', '1');
INSERT INTO `lesson` VALUES ('38', '1', '8', '1', '6', '2014', '1');
INSERT INTO `lesson` VALUES ('39', '1', '1', '2', '6', '2014', '1');
INSERT INTO `lesson` VALUES ('40', '1', '3', '3', '6', '2014', '1');
INSERT INTO `lesson` VALUES ('41', '1', '5', '4', '6', '2014', '1');
INSERT INTO `lesson` VALUES ('42', '1', '9', '5', '6', '2014', '1');
INSERT INTO `lesson` VALUES ('43', '1', '2', '6', '6', '2014', '1');
INSERT INTO `lesson` VALUES ('44', '1', '2', '1', '7', '2014', '1');
INSERT INTO `lesson` VALUES ('45', '1', '3', '2', '7', '2014', '1');
INSERT INTO `lesson` VALUES ('46', '1', '5', '3', '7', '2014', '1');
INSERT INTO `lesson` VALUES ('47', '1', '1', '4', '7', '2014', '1');
INSERT INTO `lesson` VALUES ('48', '1', '6', '5', '7', '2014', '1');
INSERT INTO `lesson` VALUES ('49', '1', '9', '6', '7', '2014', '1');
INSERT INTO `lesson` VALUES ('50', '1', '1', '1', '8', '2014', '1');
INSERT INTO `lesson` VALUES ('51', '1', '2', '2', '8', '2014', '1');
INSERT INTO `lesson` VALUES ('52', '1', '3', '3', '8', '2014', '1');
INSERT INTO `lesson` VALUES ('53', '1', '6', '4', '8', '2014', '1');
INSERT INTO `lesson` VALUES ('54', '1', '8', '5', '8', '2014', '1');
INSERT INTO `lesson` VALUES ('55', '1', '10', '6', '8', '2014', '1');
INSERT INTO `lesson_plan` VALUES ('1', '1', '1', '1', '2014', 'oooooooooooooooo', 'p47', '01.06', '1');
INSERT INTO `lesson_plan` VALUES ('2', '1', '1', '2', '2014', '函数复习学案之函数及其基本性质（12月23日已发邮箱）', 'p45', '01.06', '1');
INSERT INTO `lesson_plan` VALUES ('3', '1', '1', '3', '2014', 'jjjoookkkkkkkkkkkkk', 'p98', '01.06', '1');
INSERT INTO `lesson_plan` VALUES ('4', '1', '1', '4', '2014', '综合复习：第一章复习', 'p66', '01.06', '1');
INSERT INTO `lesson_plan` VALUES ('5', '1', '1', '5', '2014', '习题课：', 'p76', '01.06', '1');
INSERT INTO `lesson_plan` VALUES ('6', '1', '1', '6', '2014', '评讲冲刺（一）', 'p44', '01.06', '1');
INSERT INTO `lesson_plan` VALUES ('7', '1', '2', '1', '2014', 'ccccc', 'p90', '01.07', '2');
INSERT INTO `lesson_plan` VALUES ('8', '1', '2', '2', '2014', 'aaaa', 'p67', '01.07', '2');
INSERT INTO `lesson_plan` VALUES ('9', '1', '2', '3', '2014', 'ssss', 'p90', '01.07', '2');
INSERT INTO `lesson_plan` VALUES ('10', '1', '2', '4', '2014', 'ggg', 'p34', '01.07', '2');
INSERT INTO `lesson_plan` VALUES ('11', '1', '2', '5', '2014', 'bbbb', 'p09', '01.07', '2');
INSERT INTO `lesson_plan` VALUES ('12', '1', '2', '6', '2014', 'zzzz', 'p67', '01.07', '2');
INSERT INTO `manager` VALUES ('1', 'admin', '999999', 'dd', 'e', '33', '0', '0', '0', null);
INSERT INTO `manager` VALUES ('2', 'test', '999999', 'testname', '技术部', '主任', '1', '0', '0', null);
INSERT INTO `manager` VALUES ('3', 'test1', '999999', 'test1', '教育部', '老师', '1', '0', '0', '1');
INSERT INTO `online_forum` VALUES ('39', '中国教育问题', '解决1+1=3问题？', '0', '0', '2014-06-12 15:02:22', 'dd', '1');
INSERT INTO `online_forum` VALUES ('40', '中国教育问题', '不会', '39', '1', '2014-06-12 15:02:57', 'test1', '1');
INSERT INTO `online_forum` VALUES ('41', '中国教育问题', '无解', '39', '0', '2014-06-12 15:04:12', '邢荣', null);
INSERT INTO `online_forum` VALUES ('42', '中国教育问题', 'gg', '39', '0', '2014-06-12 15:20:35', '邢荣', null);
INSERT INTO `online_forum` VALUES ('43', 'xfx', 'xfcggh', '0', '0', '2014-06-12 17:41:13', 'test1', '1');
INSERT INTO `online_forum` VALUES ('44', '的地方方法广告', '消费习惯广告根本不能健康', '0', '0', '2014-06-12 17:42:13', 'test1', '1');
INSERT INTO `organ` VALUES ('1', '上级单位', '1', '0', '2', '4444', '127.0.0.1', '80');
INSERT INTO `organ` VALUES ('3', '中国ooooo', 't1', '0', 'ddd', '010551221', '192.168.1.1', '80');
INSERT INTO `organ` VALUES ('4', '下级单位test1', 't11', '2', 'ddd1', '010551221', '192.168.1.11', '801');
INSERT INTO `organ` VALUES ('5', '育恒教育', '育恒教育', '1', '王老师', '010-12569852', null, '80');
INSERT INTO `sys_class` VALUES ('1', '高三一班', '2014-01-13', '1');
INSERT INTO `sys_class` VALUES ('2', '高三二班', '2014-01-13', '1');
INSERT INTO `sys_class` VALUES ('4', '高三三班', '2014-01-13', '1');
INSERT INTO `sys_key` VALUES ('0', '性别', '男;女;', '1');
INSERT INTO `sys_key` VALUES ('1', '部门', '研发部;开发部;事业部', '1');
INSERT INTO `sys_key` VALUES ('2', '职务', '工程师;业务员', '1');
INSERT INTO `sys_key` VALUES ('3', '民族', '汉;满;', '1');
INSERT INTO `sys_key` VALUES ('4', '政治面貌', '党员;非党员;', '1');
INSERT INTO `sys_key` VALUES ('5', '职务级别', '科级;处级;', '1');
INSERT INTO `sys_key` VALUES ('6', '证件类型', '身份证;军人证;', '1');
INSERT INTO `sys_key` VALUES ('13', 'serverIP', '192.168.109.10', '0');
INSERT INTO `sys_message` VALUES ('9', '考试3', '我我要是了不起诉', '0', '2014-06-04 10:21:26', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('10', 'ry', 'cyhvuh', '0', '2014-06-04 10:25:37', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('11', 'fgvhvhvvh', 'vhvhvhvhvh', '0', '2014-06-04 14:42:42', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('12', 'cgvghhhhb', 'gvvvb', '0', '2014-06-04 14:46:05', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('13', 'ghhhjjjk', 'vbhjjj', '0', '2014-06-04 14:46:23', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('14', 'aaaaaaaa', 'xxxx', '0', '2014-06-04 14:46:36', '3', '育恒教育', '育恒教育', '1', null, '0', '1');
INSERT INTO `sys_message` VALUES ('15', 'zzzzzzhjbl', 'cgvh', '0', '2014-06-04 14:48:02', '3', '育恒教育', '育恒教育', '1', null, '0', '1');
INSERT INTO `sys_message` VALUES ('16', '美女发个通知', '大家下午快乐', '0', '2014-06-11 15:19:11', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('17', '薛娜', 'hello', '0', '2014-06-11 15:19:24', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('18', 'cbvcx&#39;--', 'bvc  bc vj', '0', '2014-06-11 15:20:44', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('19', 'today', 'cloudy', '0', '2014-06-11 15:21:21', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('20', '10', '你好', '0', '2014-06-11 15:22:26', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('21', 'eeee', 'aaaaaaaaaaaaaaaaa', '0', '2014-06-11 15:22:36', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('22', '张艺谋', '大家好', '0', '2014-06-11 15:22:42', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('23', '小学', '大家好吗', '0', '2014-06-11 15:23:45', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('24', '12456', '薛娜娜娜娜娜', '0', '2014-06-11 15:24:07', '3', '育恒教育', '育恒教育', '1', null, '0', '1');
INSERT INTO `sys_message` VALUES ('25', '放学', '下课啦', '0', '2014-06-11 15:24:25', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('26', 'jiauxue', 'jiaoyu', '0', '2014-06-11 15:24:56', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('27', '何真真', '哈哈哈', '0', '2014-06-11 15:25:51', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('28', '询问', '期末考试时间？', '0', '2014-06-11 15:26:56', '3', '育恒教育', '育恒教育', '1', null, '0', '1');
INSERT INTO `sys_message` VALUES ('29', '通知', '王晓婷晚上大请客', '0', '2014-06-11 15:27:17', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('30', '李红艳', '呵呵呵呵', '0', '2014-06-11 15:27:40', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('31', '询问', '本学期期末考试时间？', '0', '2014-06-11 15:28:36', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('32', '询问', '本学期期末考试时间？', '0', '2014-06-11 15:30:37', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('33', '人顶顶顶顶', '综上所述的', '0', '2014-06-11 15:31:18', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('34', '虽然的新你', '敌人行程就说和解', '0', '2014-06-11 15:32:09', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('35', '然后呵呵', '系统天天好像', '0', '2014-06-11 15:33:05', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('36', 'jiaoxue', 'gaoyi', '0', '2014-06-11 15:34:07', '3', '育恒教育', '育恒教育', '1', null, '0', '1');
INSERT INTO `sys_message` VALUES ('37', '请大家分享乐观点播。', '大家好', '0', '2014-06-11 15:34:12', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('38', '问题', '期末考试时间？', '0', '2014-06-11 15:34:14', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('39', 'gaoyi', 'jiaoxue', '0', '2014-06-11 15:34:34', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('40', '培训', '现在培训', '0', '2014-06-11 15:36:14', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('41', 'yhedu，', '啦啦啦啦啦啦啦啦啦', '0', '2014-06-11 15:36:42', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_message` VALUES ('42', '风格规划', '国际化', '0', '2014-06-11 15:36:59', '3', '育恒教育', '育恒教育', '1', null, '0', '1');
INSERT INTO `sys_message` VALUES ('43', '新加坡', '你去不？', '0', '2014-06-11 15:37:34', '3', '育恒教育', '育恒教育', '1', null, '0', '1');
INSERT INTO `sys_message` VALUES ('44', '新加坡', '你去不？', '0', '2014-06-11 15:38:36', '3', '育恒教育', '育恒教育', '1', null, '1', '1');
INSERT INTO `sys_subject` VALUES ('1', '语文', '2014-01-13', '1');
INSERT INTO `sys_subject` VALUES ('2', '数学', '2014-01-13', '1');
INSERT INTO `sys_subject` VALUES ('3', '英语', '2014-01-13', '1');
INSERT INTO `sys_subject` VALUES ('5', '物理', '2014-01-14', '1');
INSERT INTO `sys_subject` VALUES ('6', '体育', '2014-01-14', '1');
INSERT INTO `sys_subject` VALUES ('7', '计算机', '2014-01-14', '1');
INSERT INTO `sys_subject` VALUES ('8', '物理', '2014-01-14', '1');
INSERT INTO `sys_subject` VALUES ('9', '生物', '2014-01-14', '1');
INSERT INTO `sys_subject` VALUES ('10', '化学', '2014-01-14', '1');
INSERT INTO `user_course_note` VALUES ('3', '4', '1', 'wwwwwwwddddd', '2014-02-08 18:21:28');
INSERT INTO `user_course_note` VALUES ('4', '4', '1', 'wwwwwwwddddd', '2014-02-08 18:24:21');
INSERT INTO `user_course_note` VALUES ('5', '4', '1', 'wwwwwwwddddd', '2014-02-08 18:30:15');
INSERT INTO `user_course_note` VALUES ('6', '4', '1', 'wwwwwwwddddd', '2014-02-08 18:31:26');
INSERT INTO `user_course_note` VALUES ('7', '4', '1', 'wwwwwwwddddd', '2014-02-08 18:33:04');
INSERT INTO `user_course_note` VALUES ('8', '4', '1', 'wwwwwwwddddd', '2014-02-08 18:33:06');
INSERT INTO `user_course_note` VALUES ('9', '4', '1', 'jjhggfddetyyhh', '2014-02-08 18:36:29');
INSERT INTO `user_course_note` VALUES ('10', '4', '1', 'fee yyyy', '2014-02-08 18:57:24');
INSERT INTO `user_course_note` VALUES ('11', '4', '1', 'seeresses', '2014-02-08 18:59:52');
INSERT INTO `user_course_note` VALUES ('12', '4', '1', 'eeeeeee', '2014-02-08 19:47:23');
INSERT INTO `user_course_note` VALUES ('13', '5', '1', 'qqqqqqqq', '2014-02-08 20:05:20');
INSERT INTO `user_course_note` VALUES ('14', '4', '1', '123456789', '2014-02-10 14:09:36');
INSERT INTO `user_course_note` VALUES ('15', '4', '1', '饮料', '2014-02-10 14:12:25');
INSERT INTO `user_course_note` VALUES ('16', '4', '1', 'rrrr', '2014-02-10 17:17:17');
INSERT INTO `user_course_note` VALUES ('17', '4', '1', '', '2014-02-11 12:03:57');
INSERT INTO `user_course_note` VALUES ('18', '4', '1', 'yyyyyyyyyy', '2014-02-11 12:17:20');
INSERT INTO `user_course_note` VALUES ('19', '4', '1', 'sssssss', '2014-02-11 13:22:52');
INSERT INTO `user_info` VALUES ('14', '412721199710165010', '888888', '高康', '男', '20', '123QQ.com', '0', '汉', null, '555', '2');
INSERT INTO `user_info` VALUES ('15', '41272119960702341X', '888888', '齐胜飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('16', '412721199609103413', '888888', '赵迎磊', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('17', '412724199704293314', '888888', '贾凯博', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('18', '412721199809202248', '888888', '李金阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('19', '41272119970316479X', '888888', '娄帅威', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('20', '412721199703050645', '888888', '王佩', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('21', '411621199808070339', '888888', '邹明康', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('22', '412721199802063820', '888888', '葛盼盼', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('23', '412721199910134235', '888888', '李帅旗', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('24', '41272119961215502X', '888888', '廉雪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('25', '412721199803044250', '888888', '于畅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('26', '412721199902130621', '888888', '高文静', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('27', '412721199704243019', '888888', '吴少翔', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('28', '412721199809151815', '888888', '张威', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('29', '41272119970512381X', '888888', '李克强', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('30', '412721199704125055', '888888', '李华阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('31', '412721199705014218', '888888', '张家康', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('32', '412721199805251843', '888888', '李虹静', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('33', '412722199803308742', '888888', '陈曼瑜', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('34', '412721199801084216', '888888', '刘雅各', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('35', '41272119970606185X', '888888', '魏冰柯', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('36', '412721199511042333', '888888', '闫晓兵', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('37', '412721199708142258', '888888', '郭艳兵', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('38', '412721199610253830', '888888', '朱广生', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('39', '412721199609123414', '888888', '张志宇', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('40', '412721199802124240', '888888', '刘欣欣', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('41', '412721199710293813', '888888', '尹林威', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('42', '412721199602126217', '888888', '聂金鸽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('43', '412721199801142623', '888888', '周瑞娜', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('44', '412721199805073047', '888888', '吴静文', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('45', '412721199710282612', '888888', '谷科友', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('46', '412721199907045039', '888888', '张少明', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('47', '412721199805273831', '888888', '周寰宇', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('48', '412721199509274258', '888888', '孙云峰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('49', '412721199810063054', '888888', '李昌钰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('50', '412721199704163836', '888888', '李燕涛', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('51', '412721199708152632', '888888', '刘阳光', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('52', '412721199802072217', '888888', '郭港华', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('53', '41162119980528311X', '888888', '姜宁毫', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('54', '411621199901123079', '888888', '姜培亮', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('55', '41272119970523224X', '888888', '李爱芳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('56', '412721199805154613', '888888', '王壮', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('57', '412721199805054284', '888888', '常刘杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('58', '412721199712031509', '888888', '贾旭阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('59', '412721199802113429', '888888', '鲁淼', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('60', '412721199707061851', '888888', '陈永港', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('61', '412721199703205475', '888888', '何鹏宇', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('62', '412721199410101509', '888888', '李露', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('63', '412721199804125028', '888888', '郝笑会', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('64', '412721199806183862', '888888', '尹文瑜', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('65', '412721199902092629', '888888', '翟梦娜', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('66', '412721199901181013', '888888', '杨涛', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('67', '412721199708295051', '888888', '杨山泉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('68', '412721199810110324', '888888', '秦琼琼', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('69', '412721199901270315', '888888', '郭兆岑', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('70', '412721199612202252', '888888', '张帅飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('71', '412721199810263822', '888888', '杜倩文', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('72', '41272119980304471X', '888888', '任留顺', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('73', '41272119960909385X', '888888', '王重阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('74', '412721199707101024', '888888', '贾可可', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('75', '412721199810161068', '888888', '郑彩霞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('76', '412722199804174010', '888888', '郭星晨', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('77', '412721199805181830', '888888', '陈名阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('78', '412721199712231894', '888888', '胡春辉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('79', '412721199705154210', '888888', '常海坤', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('80', '412722199805282532', '888888', '刘霄杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('81', '41272119970103510X', '888888', '宋文君', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('82', '412721199801015026', '888888', '刘丹阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('83', '412721199703120332', '888888', '李宏阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('84', '412721199806063043', '888888', '史湘威', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('85', '412721199804054231', '888888', '孙俊超', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('86', '412721199710284212', '888888', '赵春雷', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('87', '412721199705101856', '888888', '尹献坡', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('88', '412721199912022675', '888888', '葛嘉宝', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('89', '412721199702033380', '888888', '冯旭冉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('90', '412721199703093864', '888888', '王婧颐', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('91', '412724199701144428', '888888', '张文文', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('92', '412721199710253029', '888888', '张梦鸽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('93', '412721199903205025', '888888', '任发启', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('94', '412721199901300318', '888888', '邹志翔', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('95', '41272119970924423X', '888888', '杜海放', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('96', '412721199711104244', '888888', '常庆悦', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('97', '412721199704164652', '888888', '娄元帅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('98', '411082199510234839', '888888', '王静宇', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('99', '412724199603224037', '888888', '李留原', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('100', '412721199806078831', '888888', '潘铭赫', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('101', '412721199706044232', '888888', '董澎飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('102', '412721199804251411', '888888', '张小兵', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('103', '412721199804064210', '888888', '侯肖磊', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('104', '411621199510283040', '888888', '聂梦雅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('105', '412721199610205417', '888888', '安慧民', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('106', '412721199703031022', '888888', '郭晶', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('107', '412721199712153814', '888888', '张亚杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('108', '41162119971202467X', '888888', '王凯迪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('109', '412721199609302615', '888888', '牛帅豪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('110', '412721199701202633', '888888', '尹腾耀', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('111', '412724199705042533', '888888', '刘华楠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('112', '412721199804275448', '888888', '吕萌萌', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('113', '412721199810041883', '888888', '杜苗苗', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('114', '412721199804195448', '888888', '马方方', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('115', '412721199710261811', '888888', '张松伟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('116', '41162119981027033X', '888888', '李帅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('117', '411621199806022616', '888888', '张东豪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('118', '412724199807142957', '888888', '郭一凡', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('119', '412721199711045010', '888888', '刘登科', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('120', '41272119981127067X', '888888', '李凯旋', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('121', '412721199910015017', '888888', '郝江潮', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('122', '412721199806163458', '888888', '马彦轲', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('123', '412721199710181918', '888888', '何培坤', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('124', '412721199503012636', '888888', '王腾飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('125', '412721199712305013', '888888', '刘相阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('126', '412721199710161415', '888888', '杨云召', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('127', '412721199805203833', '888888', '卢锦涛', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('128', '412721199805070639', '888888', '穆晓枫', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('129', '412721199705262633', '888888', '尹玉迪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('130', '412721199604101013', '888888', '石贝', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('131', '412721199707154214', '888888', '何汪洋', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('132', '412721199701124217', '888888', '李博文', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('133', '412721199610094657', '888888', '王九龙', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('134', '412724199811272922', '888888', '王心秀', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('135', '412721199805163835', '888888', '陈函志', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('136', '412721199803161828', '888888', '杜香香', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('137', '412721199610085048', '888888', '李晨茜', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('138', '412721199706275014', '888888', '李华康', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('139', '412721199809074733', '888888', '孙天文', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('140', '412721199711153847', '888888', '王云', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('141', '412721199812011856', '888888', '李俊广', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('142', '412721199809282671', '888888', '施家康', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('143', '412721199808282610', '888888', '翟文凯', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('144', '412721199803233414', '888888', '宁登科', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('145', '412721199709223834', '888888', '边金龙', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('146', '412725199611077416', '888888', '薛雷', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('147', '410222199807303018', '888888', '张振源', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('148', '412721199807280015', '888888', '田壹名', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('149', '412721199709100025', '888888', '曹丹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('150', '412721199603082623', '888888', '崔冰杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('151', '412721199802230425', '888888', '黄梦珠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('152', '412721199901265049', '888888', '郁闯', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('153', '412721199608161427', '888888', '宋贝贝', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('154', '412722199803194925', '888888', '苏钰杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('155', '411621199709270044', '888888', '张莉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('156', '412721199702042651', '888888', '路跃力', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('157', '412721199707095824', '888888', '侯文雅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('158', '412721199604233825', '888888', '杨二苹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('159', '412721199805065071', '888888', '刘慧斌', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('160', '412721199510202331', '888888', '谢波涛', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('161', '412721199804141810', '888888', '王向杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('162', '412721199602273452', '888888', '宁东辉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('163', '412721199711053838', '888888', '孙乐', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('164', '412721199612243476', '888888', '顾博伦', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('165', '412721199501182631', '888888', '邢兵', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('166', '412721199906175480', '888888', '齐静静', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('167', '412721199704064213', '888888', '王雷振', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('168', '412721199612251433', '888888', '张一凡', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('169', '412721199807124215', '888888', '高速贺', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('170', '412721199811295017', '888888', '董飞虎', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('171', '41272419980529253X', '888888', '赵晨阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('172', '412721199708113035', '888888', '谢林阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('173', '411621199705013040', '888888', '史笑丽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('174', '412721199801093067', '888888', '王孟雪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('175', '412721199806265446', '888888', '栾园园', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('176', '41272119990806264X', '888888', '杨姝', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('177', '412721199709060617', '888888', '李腾飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('178', '412721199703063454', '888888', '樊丰毅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('179', '412721199810091864', '888888', '王亚娟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('180', '412721199601232616', '888888', '任文康', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('181', '412721199903211829', '888888', '周娟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('182', '412721199709024237', '888888', '赵梦阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('183', '412721199709202611', '888888', '李磊', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('184', '412721199907125012', '888888', '郭家辉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('185', '412721199705284218', '888888', '井美绅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('186', '412721199706181878', '888888', '李意威', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('187', '412721199812101835', '888888', '周兴超', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('188', '412721199708195835', '888888', '梁孟君', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('189', '41272119980826181X', '888888', '金刘阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('190', '411621199701173063', '888888', '高俊芳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('191', '41272419980514254X', '888888', '陈晨', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('192', '412724199911082544', '888888', '孔格格', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('193', '412721199901282615', '888888', '刘静纯', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('194', '412721199711161871', '888888', '郭程龙', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('195', '412721199701115417', '888888', '付学言', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('196', '412721199304132635', '888888', '李文基', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('197', '412721199505052236', '888888', '陈云功', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('198', '412721199912081513', '888888', '赫志强', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('199', '412721199904204719', '888888', '刘禹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('200', '412724199604262924', '888888', '陈银慧', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('201', '412721199707172236', '888888', '姜琳琅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('202', '412721199504022617', '888888', '梅海洋', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('203', '412721199801016213', '888888', '万笑虎', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('204', '41272119970628501X', '888888', '李家乐', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('205', '412721199701134618', '888888', '王鹏飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('206', '412721199703013414', '888888', '张威威', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('207', '412721199801234616', '888888', '韩宇', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('208', '411621199707183035', '888888', '何争港', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('209', '41272119980507543X', '888888', '王光耀', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('210', '412721199801205444', '888888', '杜倩杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('211', '412721199811275040', '888888', '王四琼', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('212', '412721199901081020', '888888', '祝赢娟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('213', '412721199902245074', '888888', '郁海洲', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('214', '41272119951112462X', '888888', '苏瑞姣', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('215', '412721199803203864', '888888', '杜俊娅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('216', '412721199810280016', '888888', '赵宇飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('217', '412721199510203436', '888888', '李亚军', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('218', '412721199802185430', '888888', '何汶举', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('219', '412721199705190019', '888888', '李嘉龙', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('220', '412721199704265410', '888888', '朱会杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('221', '41272119931120262X', '888888', '张婧霞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('222', '412721199602171827', '888888', '何梦贝', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('223', '412721199903182650', '888888', '陈飞宇', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('224', '412721199612164305', '888888', '刘雪雅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('225', '41272119990806184X', '888888', '丁之琳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('226', '411621199801143048', '888888', '吴明珠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('227', '412721199510025021', '888888', '郝紫琦', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('228', '412721199707192632', '888888', '朱振方', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('229', '412721199804150610', '888888', '严奇光', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('230', '41272119990109143X', '888888', '杜向阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('231', '412721199702173811', '888888', '张龙飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('232', '411621199705163030', '888888', '朱金松', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('233', '412721199805123825', '888888', '常乐园', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('234', '412721199807301429', '888888', '张华丽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('235', '412721199805171878', '888888', '何景山', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('236', '412721199712222621', '888888', '张亚萌', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('237', '41272219960612256X', '888888', '张琳霞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('238', '412721199802190387', '888888', '侯时雨', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('239', '412721199801061022', '888888', '郭思雨', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('240', '412721199708184765', '888888', '王皎', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('241', '412721199503161834', '888888', '严江北', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('242', '411621199605161062', '888888', '李豆', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('243', '41272119960317224X', '888888', '时秋珂', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('244', '412721199811111863', '888888', '李静', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('245', '412721199809293063', '888888', '吴银玲', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('246', '412721199406202649', '888888', '李静银', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('247', '412721199811023441', '888888', '王璞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('248', '412721199706114288', '888888', '张文雅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('249', '412721199612294767', '888888', '李亚田', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('250', '412721199706210616', '888888', '刘骁健', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('251', '412724199811172534', '888888', '王加辉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('252', '411621199710103067', '888888', '张梦姣', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('253', '411627199912302126', '888888', '王银慧', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('254', '412721199608281023', '888888', '张燕杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('255', '41272119980117182X', '888888', '杜倩', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('256', '412721199704062648', '888888', '路璐', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('257', '41162119960515308X', '888888', '赵梦娟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('258', '412721199805264222', '888888', '祁肖茜', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('259', '411621199805204645', '888888', '薄彩云', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('260', '412721199509234221', '888888', '孙浩', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('261', '412721199611203448', '888888', '韩瑞苹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('262', '412721199809094224', '888888', '井美华', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('263', '41272119951210265X', '888888', '朱鹏飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('264', '412721199701014624', '888888', '王艳伟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('265', '412721199804031822', '888888', '刘畅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('266', '412721199710032226', '888888', '邵玉琪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('267', '412721199509075435', '888888', '何向阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('268', '412721199809035056', '888888', '郝帅威', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('269', '412721199708073467', '888888', '张卉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('270', '412721199812074224', '888888', '张丽娜', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('271', '412721199707273811', '888888', '王奇', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('272', '412721199810091020', '888888', '魏若冰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('273', '412721199611102284', '888888', '闫亚如', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('274', '412721199803113025', '888888', '张文雅', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('275', '412721199810180023', '888888', '刘梦瑶', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('276', '41272119960519061X', '888888', '赵豪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('277', '412721199810264614', '888888', '刘晨明', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('278', '41272119960201424X', '888888', '刘晓静', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('279', '412721199701202238', '888888', '范云飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('280', '411621199701243036', '888888', '闫慧林', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('281', '412721199812083044', '888888', '赵宇鸽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('282', '412721199707202247', '888888', '宋留画', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('283', '412721199808200312', '888888', '何琤翔', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('284', '412721199711272256', '888888', '李鑫龙', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('285', '412721199805201440', '888888', '张雅琼', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('286', '412721199810095435', '888888', '齐指辉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('287', '412721199712133418', '888888', '鲁培垒', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('288', '412721199709063412', '888888', '齐光耀', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('289', '412721199807143846', '888888', '张梦琳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('290', '412721199703283422', '888888', '赵静文', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('291', '412721199910023842', '888888', '杜玉珠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('292', '412721199801140301', '888888', '吴瑞晗', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('293', '412721199709103410', '888888', '侯彦康', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('294', '412721199711251033', '888888', '张富申', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('295', '412721199508254634', '888888', '刘萌科', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('296', '41272119980916267X', '888888', '施晨凯', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('297', '41272119980513302X', '888888', '朱开琴', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('298', '412721199806110321', '888888', '刘晓易', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('299', '412724199611042938', '888888', '王可飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('300', '412721199701084227', '888888', '陈利莎', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('301', '412721199711084220', '888888', '常梦娟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('302', '412721199709143842', '888888', '张家瑞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('303', '412721199808070028', '888888', '曹涵冲', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('304', '412721199801123828', '888888', '刘丹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('305', '411621199804233831', '888888', '杜东孝', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('306', '412721199808093836', '888888', '王亚涛', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('307', '412721199509231821', '888888', '郭莉杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('308', '41272119961220264X', '888888', '何思佳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('309', '411621199403121847', '888888', '陈星星', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('310', '412721199710302265', '888888', '谢梦月', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('311', '412721199805181857', '888888', '丁子昂', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('312', '41272119961230032X', '888888', '梁玉婷', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('313', '410526199803051169', '888888', '牛霄雯', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('314', '412721199711161839', '888888', '严豪强', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('315', '412721199810121031', '888888', '刘庆磊', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('316', '412721199504294217', '888888', '常天其', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('317', '412721199908061428', '888888', '何静思', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('318', '412721199703023022', '888888', '李莹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('319', '412721199606212622', '888888', '唐京婷', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('320', '412721199812120323', '888888', '杜涵', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('321', '412721199712085014', '888888', '宋亚涛', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('322', '412721200005205086', '888888', '张梦园', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('323', '412721199411093424', '888888', '张梦爽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('324', '41272119980120103X', '888888', '张亚飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('325', '412721199712263829', '888888', '张柯', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('326', '41272119981013342X', '888888', '李文艺', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('327', '412721199812063824', '888888', '孙盈盈', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('328', '412721199812065037', '888888', '长进才', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('329', '412721199810193043', '888888', '张文丹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('330', '412721199611224767', '888888', '陈瑞杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('331', '412721199706165448', '888888', '张慧如', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('332', '41272119970608463X', '888888', '王博奇', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('333', '41272119970506424X', '888888', '赵乐珊', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('334', '412721199709240626', '888888', '江苗苗', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('335', '412721199707202626', '888888', '任云玲', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('336', '412721199804161467', '888888', '曹肖楠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('337', '412721199811173829', '888888', '陈婷婷', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('338', '412721199610281866', '888888', '彭梦楠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('339', '412721199702044729', '888888', '薄玲玲', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('340', '41272119980113421X', '888888', '魏书豪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('341', '412721199805181194', '888888', '马驰涵', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('342', '41272119990511503X', '888888', '刘颖飞', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('343', '412721199801294221', '888888', '李蓉蓉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('344', '412721199601172625', '888888', '庞璐爽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('345', '412721199802064305', '888888', '刘梦晴', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('346', '412721199706114237', '888888', '孙云龙', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('347', '412721199612254220', '888888', '郭文婷', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('348', '412721199604133445', '888888', '骆莹莹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('349', '412721199802191048', '888888', '刘梦莹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('350', '412721199702272617', '888888', '梅中旺', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('351', '412721199701212620', '888888', '张晶晶', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('352', '412721199609131035', '888888', '张晓龙', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('353', '412721199812024614', '888888', '刘飞凡', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('354', '412721199806221419', '888888', '焦立博', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('355', '412721199710175462', '888888', '刘朵', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('356', '411621199802203102', '888888', '吴雨珠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('357', '412721199803031417', '888888', '李志丹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('358', '411621199510133026', '888888', '史玲莉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('359', '412721199702233044', '888888', '李广姣', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('360', '412721199706143433', '888888', '张闯', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('361', '412721199411204657', '888888', '贺义冲', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('362', '412721199612013427', '888888', '高嘉琳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('363', '412721199703063446', '888888', '顾晓婉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('364', '412721199710071831', '888888', '薄保罗', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('365', '412721199701133053', '888888', '吴楠楠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('366', '41272119960829502X', '888888', '李梦华', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('367', '412721199810263849', '888888', '豆惠迪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('368', '412721199712034225', '888888', '赵婷婷', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('369', '412721199704045063', '888888', '刘亚芳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('370', '412721199706074220', '888888', '卢士旗', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('371', '412721199709094681', '888888', '王欣欣', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('372', '412721199707015011', '888888', '宋俊龙', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('373', '412721199607262613', '888888', '王豪威', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('374', '412721199801084224', '888888', '解春鸣', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('375', '412721199704012624', '888888', '杨立原', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('376', '412721199609173825', '888888', '白亚丹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('377', '412721199710164659', '888888', '牛梦祥', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('378', '412721199711293428', '888888', '施亚楠', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('379', '412721199709112245', '888888', '任梦兵', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('380', '412721199806060336', '888888', '张锦泽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('381', '412721199609021426', '888888', '聂梦鸽', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('382', '41272119970920502X', '888888', '李慧娜', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('383', '412721199804120331', '888888', '班昊阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('384', '412721199709062620', '888888', '乔芳芳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('385', '412721199704163449', '888888', '顾静怡', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('386', '412721199511231441', '888888', '张晨佳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('387', '412721199802192219', '888888', '张东方', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('388', '412721199505100613', '888888', '田友', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('389', '412721199804243446', '888888', '李玉莹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('390', '412721199702061844', '888888', '代萌', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('391', 'xushisong', '888888', '邢荣', '男', '20', '123QQ.com', '0', '汉', null, '555', '1');
INSERT INTO `user_info` VALUES ('392', '412721199807241454', '888888', '唐壮壮', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('393', '412721199707020312', '888888', '齐凌霄', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('394', '412721199903125031', '888888', '郎佳成', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('395', '412721199810065017', '888888', '潘万波', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('396', '412721199801013063', '888888', '赵阡莉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('397', '412721199807052235', '888888', '包靖阳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('398', '412721199502245462', '888888', '刘怡萌', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('399', '411621199808280045', '888888', '周俞含', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('400', '41272120001117222', '888888', '肖丽娟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('401', '412721199601083841', '888888', '庄婷茹', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('402', '412721199704033839', '888888', '孙彦伟', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('403', '412721199708113836', '888888', '卢瑞成', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('404', '412721199711042311', '888888', '王世兴', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('405', '412721199802233041', '888888', '霍施汝', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('406', '412721199807052223', '888888', '吕博佳', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('407', '412721199805213476', '888888', '张笑磊', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('408', '412721199611165824', '888888', '刘贝贝', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('409', '412721199806102612', '888888', '柴明辉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('410', '412721199806211413', '888888', '李亚行', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('411', '412721199612225411', '888888', '卢晨辉', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('412', '412721199705095150', '888888', '刘森', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('413', '412721199509032662', '888888', '路紫琦', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('414', '412721199507192646', '888888', '左曼迪', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('415', '412721199706233463', '888888', '韩敏杰', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('416', '412721199801071810', '888888', '张金鹏', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('417', '41272119980527501X', '888888', '关万亮', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('418', '412721199805035817', '888888', '李梦奎', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
INSERT INTO `user_info` VALUES ('419', '412721199703044237', '888888', '孙保建', '男', '20', '123QQ.com', '0', '汉', null, '', '1');
