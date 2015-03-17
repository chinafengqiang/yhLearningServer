-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.5-10.0.11-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 smlearning_new.class_book 结构
CREATE TABLE IF NOT EXISTS `class_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课堂编号',
  `name` varchar(200) NOT NULL,
  `image_url` varchar(100) NOT NULL COMMENT '课堂板书',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.class_book 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `class_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_book` ENABLE KEYS */;


-- 导出  表 smlearning_new.course 结构
CREATE TABLE IF NOT EXISTS `course` (
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
  `grade_id` bigint(20) DEFAULT '0',
  `ispublic` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='课程';

-- 正在导出表  smlearning_new.course 的数据：~18 rows (大约)
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
REPLACE INTO `course` (`id`, `name`, `courseware_category_id`, `lectuer`, `hour`, `description`, `teacher_description`, `status`, `pic`, `created_time`, `creator`, `url`, `grade_id`, `ispublic`) VALUES
	(18, '高一英语视频黑洞', 3, '纪录片', 10, '', '', 2, '/uploadFile/pic/yy.jpg', '2014-07-03', 1, '/uploadFile/file/高一英语视频黑洞.3gp', 0, 0),
	(19, '0307历史视频资料荷兰', 71, '纪录片', 10, '', '', 0, '/uploadFile/pic/lsbx2.jpg', '2014-07-03', 1, '/uploadFile/file/0307历史视频资料荷兰.3gp', 0, 0),
	(20, '0531高1英语环球影城1', 3, '纪录片', 10, '', '', 0, '/uploadFile/pic/yy.jpg', '2014-07-03', 1, '/uploadFile/file/0531高1英语环球影城1.3gp', 0, 0),
	(21, '0519高1英语课堂笔记Charlie', 3, '纪录片', 10, '', '', 0, '/uploadFile/pic/yy.jpg', '2014-07-04', 1, '/uploadFile/file/0519高1英语课堂笔记Charlie.3gp', 0, 0),
	(22, '0531高1英语课堂笔记', 3, '纪录片', 10, '', '', 0, '/uploadFile/pic/yy.jpg', '2014-07-04', 1, '/uploadFile/file/0531高1英语课堂笔记Amusement park.3gp', 0, 0),
	(23, '0622高1英语奥巴马胜选演说', 3, '演讲', 10, '', '', 0, '/uploadFile/pic/yy.jpg', '2014-07-04', 1, '/uploadFile/file//uploadFile/file/0622高1英语奥巴马胜选演说.3gp', 0, 0),
	(24, '0307历史视频资料课前', 71, '记录', 10, '', '', 0, '/uploadFile/pic/lsbx2.jpg', '2014-07-04', 1, '/uploadFile/file/0307历史视频资料课前.3gp', 0, 0),
	(25, '20140507高一物理实验探究功与速度的变化关系2', 73, '纪录片', 10, '', '', 0, '/uploadFile/pic/wlbx2.jpg', '2014-07-04', 1, '/uploadFile/file/20140507高一物理实验探究功与速度的变化关系2.3gp', 0, 0),
	(26, '20140507高一物理实验探究功与速度的变化关系1', 73, '实验', 10, '', '', 0, '/uploadFile/pic/wlbx2.jpg', '2014-07-04', 1, '/uploadFile/file/20140507高一物理实验探究功与速度的变化关系1.3gp', 0, 0),
	(27, '20140318高1生物(实验观察根尖分生组织细胞的有丝分裂)1', 75, '实验', 10, '', '', 0, '/uploadFile/pic/sw3.jpg', '2014-07-04', 1, '/uploadFile/file/20140318高1生物(实验观察根尖分生组织细胞的有丝分裂)1.3gp', 0, 0),
	(28, '20140318高1生物(实验观察根尖分生组织细胞的有丝分裂)2', 75, '实验', 10, '', '', 0, '/uploadFile/pic/sw3.jpg', '2014-07-04', 1, '/uploadFile/file/20140318高1生物(实验观察根尖分生组织细胞的有丝分裂)2.3gp', 0, 0),
	(29, '20140318高1生物(实验观察根尖分生组织细胞的有丝分裂)3', 75, '实验', 10, '', '', 0, '/uploadFile/pic/sw3.jpg', '2014-07-04', 1, '/uploadFile/file/20140318高1生物(实验观察根尖分生组织细胞的有丝分裂)3.3gp', 0, 0),
	(30, '高一地理参观地理器材室', 72, '实验', 10, '', '', 0, '/uploadFile/pic/dlbx2.jpg', '2014-07-04', 1, '/uploadFile/file/高一地理参观地理器材室.3gp', 0, 0),
	(31, '1161', 1, '视频资源', 20, '', '', 2, '/uploadFile/pic/sx4.jpg', '2014-07-04', 1, '/uploadFile/file/1161.mp4', 0, 0),
	(32, '1162', 1, '视频资料', 20, '', '', 0, '/uploadFile/pic/sx4.jpg', '2014-07-04', 1, '/uploadFile/file/1162.mp4', 0, 0),
	(33, '22222', 3, '', 0, '', '', 0, '', '2014-12-18', 1, '/uploadFile/file/record-20141213231331.iac', 4, 1),
	(34, 'cccsxss', 3, '', 0, '', '', 0, '/uploadFile/pic/QQ图片20141219154725.png', '2014-12-22', 1, '/uploadFile/file/01_什么是3g.avi', 1, 0),
	(35, 'ddddd', 14, '', 0, '', '', 0, '/uploadFile/pic/bar_icon_joinroom_normal1.png', '2014-12-22', 1, '/uploadFile/file/comment.png', 1, 0),
	(36, 'eeeee', 73, '', 0, '', '', 0, '/uploadFile/pic/1.jpg', '2014-12-22', 1, '/uploadFile/file/20141013物理（测电阻率实验）.wmv', 1, 1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;


-- 导出  表 smlearning_new.courseware 结构
CREATE TABLE IF NOT EXISTS `courseware` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(300) NOT NULL COMMENT '课件名称',
  `courseware_category_id` bigint(20) NOT NULL COMMENT '课件分类编号',
  `url` varchar(300) NOT NULL COMMENT '课件网址',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `pic` varchar(100) DEFAULT NULL COMMENT '电子书图片',
  `status` int(1) DEFAULT '0',
  `class_id` bigint(20) DEFAULT '1',
  `grade_id` bigint(20) DEFAULT NULL,
  `ispublic` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1473 DEFAULT CHARSET=utf8 COMMENT='课件';

-- 正在导出表  smlearning_new.courseware 的数据：~83 rows (大约)
/*!40000 ALTER TABLE `courseware` DISABLE KEYS */;
REPLACE INTO `courseware` (`id`, `name`, `courseware_category_id`, `url`, `created_time`, `creator`, `pic`, `status`, `class_id`, `grade_id`, `ispublic`) VALUES
	(1390, '0106高一英语课堂笔记1', 77, '/uploadFile/file/0106高一英语课堂笔记1.pdf', '2015-01-06 09:21:51', 1, '/uploadFile/pic/English.jpg', 1, 1, 1, NULL),
	(1391, '0106高一英语课堂笔记2', 77, '/uploadFile/file/0106高一英语课堂笔记2.pdf', '2015-01-06 09:22:09', 1, '/uploadFile/pic/English.jpg', 1, 1, 1, NULL),
	(1392, '0106高一数学课堂笔记', 77, '/uploadFile/file/0106高一数学课堂笔记.pdf', '2015-01-06 09:22:31', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 1, NULL),
	(1393, '0106高二文科数学课堂笔记', 77, '/uploadFile/file/0106高二文科数学课堂笔记.pdf', '2015-01-06 09:29:54', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 2, NULL),
	(1394, '0106高二文科数学课堂笔记2', 77, '/uploadFile/file/0106高二文科数学课堂笔记2.pdf', '2015-01-06 09:30:15', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 2, NULL),
	(1395, '0106高二理科语文课堂笔记', 77, '/uploadFile/file/0106高二理科语文课堂笔记.pdf', '2015-01-06 09:36:44', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 3, NULL),
	(1396, '0106高二理科英语课堂笔记', 77, '/uploadFile/file/0106高二理科英语课堂笔记.pdf', '2015-01-06 09:37:02', 1, '/uploadFile/pic/English.jpg', 1, 1, 3, NULL),
	(1397, '0106高二文数4-4高考题集锦及答案0108讲', 60, '/uploadFile/file/0106高二文数4-4高考题集锦及答案0108讲.pdf', '2015-01-06 09:42:19', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 2, NULL),
	(1398, '0106高一语文课堂笔记', 77, '/uploadFile/file/0106高一语文课堂笔记.pdf', '2015-01-06 10:03:08', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 1, NULL),
	(1399, '0106高二文科英语课堂笔记', 77, '/uploadFile/file/0106高二文科英语课堂笔记.pdf', '2015-01-06 10:07:16', 1, '/uploadFile/pic/English.jpg', 1, 1, 2, NULL),
	(1400, '0106高一物理课堂笔记', 77, '/uploadFile/file/0106高一物理课堂笔记.pdf', '2015-01-06 14:22:17', 1, '/uploadFile/pic/wuli.jpg', 1, 1, 1, NULL),
	(1401, '0106高二文科语文课堂笔记', 77, '/uploadFile/file/0106高二文科语文课堂笔记.pdf', '2015-01-06 14:24:47', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1402, '0106高二化学课堂笔记', 77, '/uploadFile/file/0106高二化学课堂笔记.pdf', '2015-01-06 14:27:24', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 3, NULL),
	(1403, '0106高二化学课堂笔记2', 77, '/uploadFile/file/0106高二化学课堂笔记2.pdf', '2015-01-06 14:27:43', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 3, NULL),
	(1404, '0106高一政治课堂笔记', 77, '/uploadFile/file/0106高一政治课堂笔记.pdf', '2015-01-06 16:36:32', 1, '/uploadFile/pic/zhengzhi.jpg', 1, 1, 1, NULL),
	(1405, '0106高二历史课堂笔记', 77, '/uploadFile/file/0106高二历史课堂笔记.pdf', '2015-01-06 16:38:04', 1, '/uploadFile/pic/lishi.jpg', 1, 1, 2, NULL),
	(1406, '0106高二政治课堂笔记', 77, '/uploadFile/file/0106高二政治课堂笔记.pdf', '2015-01-06 16:38:21', 1, '/uploadFile/pic/zhengzhi.jpg', 1, 1, 2, NULL),
	(1407, '0106高二理科数学课堂笔记1', 77, '/uploadFile/file/0106高二理科数学课堂笔记1.pdf', '2015-01-06 16:41:56', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 3, NULL),
	(1408, '0106高二理科数学课堂笔记2', 77, '/uploadFile/file/0106高二理科数学课堂笔记2.pdf', '2015-01-06 16:42:12', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 3, NULL),
	(1409, '0106高一各科作业', 63, '/uploadFile/file/0106高一各科作业.pdf', '2015-01-06 16:43:29', 1, '/uploadFile/pic/zuoye.jpg', 1, 1, 1, NULL),
	(1410, '0106高二化学会考复习练习一和二答案0109用', 68, '/uploadFile/file/0106高二化学会考复习练习一和二答案0109用.pdf', '2015-01-06 16:49:55', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 3, NULL),
	(1411, '0106高二地理课堂笔记', 77, '/uploadFile/file/0106高二地理课堂笔记.pdf', '2015-01-06 16:54:11', 1, '/uploadFile/pic/dili.jpg', 1, 1, 2, NULL),
	(1412, '0106高二物理课堂笔记', 77, '/uploadFile/file/0106高二物理课堂笔记.pdf', '2015-01-06 17:03:51', 1, '/uploadFile/pic/wuli.jpg', 1, 1, 3, NULL),
	(1413, '0106高二物理课堂笔记2', 77, '/uploadFile/file/0106高二物理课堂笔记2.pdf', '2015-01-06 17:04:12', 1, '/uploadFile/pic/wuli.jpg', 1, 1, 3, NULL),
	(1414, '0106高二文科各科作业', 63, '/uploadFile/file/0106高二文科各科作业.pdf', '2015-01-06 17:06:55', 1, '/uploadFile/pic/zuoye.jpg', 1, 1, 2, NULL),
	(1415, '0106高二理科各科作业', 63, '/uploadFile/file/0106高二理科各科作业.pdf', '2015-01-06 17:27:20', 1, '/uploadFile/pic/zuoye.jpg', 1, 1, 3, NULL),
	(1416, '0107高一英语课堂笔记1', 77, '/uploadFile/file/0107高一英语课堂笔记1.pdf', '2015-01-07 10:10:02', 1, '/uploadFile/pic/English.jpg', 1, 1, 1, NULL),
	(1417, '0107高一英语课堂笔记2', 77, '/uploadFile/file/0107高一英语课堂笔记2.pdf', '2015-01-07 10:10:19', 1, '/uploadFile/pic/English.jpg', 1, 1, 1, NULL),
	(1418, '0107高一英语课堂笔记3', 77, '/uploadFile/file/0107高一英语课堂笔记3.pdf', '2015-01-07 10:10:37', 1, '/uploadFile/pic/English.jpg', 1, 1, 1, NULL),
	(1419, '0107高一语文课堂笔记1', 77, '/uploadFile/file/0107高一语文课堂笔记1.pdf', '2015-01-07 10:11:00', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 1, NULL),
	(1420, '0107高一语文课堂笔记2', 77, '/uploadFile/file/0107高一语文课堂笔记2.pdf', '2015-01-07 10:11:18', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 1, NULL),
	(1421, '0107高二文科数学课堂笔记', 77, '/uploadFile/file/0107高二文科数学课堂笔记.pdf', '2015-01-07 10:14:32', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 2, NULL),
	(1422, '0107高二文科语文课堂笔记1', 77, '/uploadFile/file/0107高二文科语文课堂笔记1.pdf', '2015-01-07 10:14:50', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1423, '0107高二文科语文课堂笔记2', 77, '/uploadFile/file/0107高二文科语文课堂笔记2.pdf', '2015-01-07 10:15:08', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1424, '0107高二理科数学课堂笔记', 77, '/uploadFile/file/0107高二理科数学课堂笔记.pdf', '2015-01-07 10:17:45', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 3, NULL),
	(1425, '0107高二理科语文课堂笔记1', 77, '/uploadFile/file/0107高二理科语文课堂笔记1.pdf', '2015-01-07 10:18:03', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 3, NULL),
	(1426, '0107高二理科语文课堂笔记2', 77, '/uploadFile/file/0107高二理科语文课堂笔记2.pdf', '2015-01-07 10:18:22', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 3, NULL),
	(1427, '0107高二地理地图册P26世界气候类型和表层洋流分布图0106使用', 61, '/uploadFile/file/0107高二地理地图册P26世界气候类型和表层洋流分布图0106使用.pdf', '2015-01-07 10:27:22', 1, '/uploadFile/pic/dili.jpg', 1, 1, 2, NULL),
	(1428, '0107高二化学会考复习练习一和二答案0109用', 68, '/uploadFile/file/0107高二化学会考复习练习一和二答案0109用.pdf', '2015-01-07 10:27:51', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 3, NULL),
	(1429, '0107高二语文习题集第一和二单元习题及答案下周用', 53, '/uploadFile/file/0107高二语文习题集第一和二单元习题及答案下周用.pdf', '2015-01-07 10:28:24', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1430, '0107高二语文状元之路第一单元习题及答案下周用', 53, '/uploadFile/file/0107高二语文状元之路第一单元习题及答案下周用.pdf', '2015-01-07 10:28:51', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1431, '0107高二语文状元之路第二单元习题及答案下周用', 53, '/uploadFile/file/0107高二语文状元之路第二单元习题及答案下周用.pdf', '2015-01-07 10:29:16', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1432, '0107高一数学课堂笔记', 77, '/uploadFile/file/0107高一数学课堂笔记.pdf', '2015-01-07 14:31:04', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 1, NULL),
	(1433, '0107高一化学课堂笔记', 61, '/uploadFile/file/0107高一化学课堂笔记.pdf', '2015-01-07 14:32:47', 1, '/uploadFile/pic/huaxue.jpg', 0, 1, 1, 0),
	(1434, '0107高二文科英语课堂笔记', 77, '/uploadFile/file/0107高二文科英语课堂笔记.pdf', '2015-01-07 14:46:39', 1, '/uploadFile/pic/English.jpg', 1, 1, 2, NULL),
	(1435, '0107高二历史课堂笔记', 77, '/uploadFile/file/0107高二历史课堂笔记.pdf', '2015-01-07 14:47:40', 1, '/uploadFile/pic/lishi.jpg', 1, 1, 2, NULL),
	(1436, '0107高二生物课堂笔记', 77, '/uploadFile/file/0107高二生物课堂笔记.pdf', '2015-01-07 14:48:58', 1, '/uploadFile/pic/shengwu.jpg', 1, 1, 3, NULL),
	(1437, '0107高二物理课堂笔记', 77, '/uploadFile/file/0107高二物理课堂笔记.pdf', '2015-01-07 14:50:16', 1, '/uploadFile/pic/wuli.jpg', 1, 1, 3, NULL),
	(1438, '0107高二语文名师一号第八单元及答案0109用', 53, '/uploadFile/file/0107高二语文名师一号第八单元及答案0109用.pdf', '2015-01-07 15:05:04', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1439, '0107高二语文名师一号第九单元及答案0109使用', 53, '/uploadFile/file/0107高二语文名师一号第九单元及答案0109使用.pdf', '2015-01-07 15:05:30', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1440, '0107高一各科作业', 63, '/uploadFile/file/0107高一各科作业.pdf', '2015-01-07 15:24:38', 1, '/uploadFile/pic/zuoye.jpg', 1, 1, 1, NULL),
	(1441, '0107高一地理课堂笔记', 77, '/uploadFile/file/0107高一地理课堂笔记.pdf', '2015-01-07 16:03:02', 1, '/uploadFile/pic/dili.jpg', 1, 1, 1, NULL),
	(1442, '0107高二理科英语课堂笔记', 77, '/uploadFile/file/0107高二理科英语课堂笔记.pdf', '2015-01-07 16:08:34', 1, '/uploadFile/pic/English.jpg', 1, 1, 3, NULL),
	(1443, '0107高二地理课堂笔记', 77, '/uploadFile/file/0107高二地理课堂笔记.pdf', '2015-01-07 16:15:07', 1, '/uploadFile/pic/dili.jpg', 1, 1, 2, NULL),
	(1444, '0107高二政治课堂笔记', 77, '/uploadFile/file/0107高二政治课堂笔记.pdf', '2015-01-07 16:54:38', 1, '/uploadFile/pic/zhengzhi.jpg', 1, 1, 2, NULL),
	(1445, '0107高二历史河南12年12月普通高中学业水平考试历史试题及答案0113用', 66, '/uploadFile/file/0107高二历史河南12年12月普通高中学业水平考试历史试题及答案0113用.pdf', '2015-01-07 16:57:01', 1, '/uploadFile/pic/lishi.jpg', 1, 1, 2, NULL),
	(1446, '0107高二理科各科作业', 63, '/uploadFile/file/0107高二理科各科作业.pdf', '2015-01-07 16:58:41', 1, '/uploadFile/pic/zuoye.jpg', 1, 1, 3, NULL),
	(1447, '0107高二文科各科作业', 63, '/uploadFile/file/0107高二文科各科作业.pdf', '2015-01-07 16:59:05', 1, '/uploadFile/pic/zuoye.jpg', 1, 1, 2, NULL),
	(1448, '0108高一英语课堂笔记', 77, '/uploadFile/file/0108高一英语课堂笔记.pdf', '2015-01-08 09:15:41', 1, '/uploadFile/pic/English.jpg', 1, 1, 1, NULL),
	(1449, '0108高一物理课堂笔记', 77, '/uploadFile/file/0108高一物理课堂笔记.pdf', '2015-01-08 09:15:59', 1, '/uploadFile/pic/wuli.jpg', 1, 1, 1, NULL),
	(1450, '0108高二文科语文课堂笔记', 77, '/uploadFile/file/0108高二文科语文课堂笔记.pdf', '2015-01-08 09:20:10', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1451, '0108高二文科语文课堂笔记2', 77, '/uploadFile/file/0108高二文科语文课堂笔记2.pdf', '2015-01-08 09:20:27', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 2, NULL),
	(1452, '0108高二政治课堂笔记', 77, '/uploadFile/file/0108高二政治课堂笔记.pdf', '2015-01-08 09:20:44', 1, '/uploadFile/pic/zhengzhi.jpg', 1, 1, 2, NULL),
	(1453, '0108高二理科数学课堂笔记', 77, '/uploadFile/file/0108高二理科数学课堂笔记.pdf', '2015-01-08 09:27:00', 1, '/uploadFile/pic/shuxue.jpg', 1, 1, 3, NULL),
	(1454, '0108高二理科语文课堂笔记', 77, '/uploadFile/file/0108高二理科语文课堂笔记.pdf', '2015-01-08 09:27:19', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 3, NULL),
	(1455, '0108高二理科语文课堂笔记2', 77, '/uploadFile/file/0108高二理科语文课堂笔记2.pdf', '2015-01-08 09:27:37', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 3, NULL),
	(1456, '0108高一数学课堂笔记', 60, '/uploadFile/file/0108高一数学课堂笔记.pdf', '2015-01-08 10:11:34', 1, '/uploadFile/pic/shuxue.jpg', 0, 1, 1, 0),
	(1457, '0108高二地理课堂笔记', 77, '/uploadFile/file/0108高二地理课堂笔记.pdf', '2015-01-08 13:55:36', 1, '/uploadFile/pic/dili.jpg', 1, 1, 2, NULL),
	(1458, '0108高二化学课堂笔记', 77, '/uploadFile/file/0108高二化学课堂笔记.pdf', '2015-01-08 13:57:02', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 3, NULL),
	(1459, '0108高一语文必修3精讲集第二单元0114用', 53, '/uploadFile/file/0108高一语文必修3精讲集第二单元0114用.pdf', '2015-01-08 14:07:42', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 1, NULL),
	(1460, '0108高一语文必修3指导书第二单元0114用', 53, '/uploadFile/file/0108高一语文必修3指导书第二单元0114用.pdf', '2015-01-08 14:08:28', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 1, NULL),
	(1461, '0108高一语文必修三习题集第二单元及答案0114用', 53, '/uploadFile/file/0108高一语文必修三习题集第二单元及答案0114用.pdf', '2015-01-08 14:08:59', 1, '/uploadFile/pic/yuwen.jpg', 1, 1, 1, NULL),
	(1462, '0108高一历史课堂笔记', 77, '/uploadFile/file/0108高一历史课堂笔记.pdf', '2015-01-08 15:26:46', 1, '/uploadFile/pic/lishi.jpg', 1, 1, 1, NULL),
	(1463, '0108高二理科英语课堂笔记', 77, '/uploadFile/file/0108高二理科英语课堂笔记.pdf', '2015-01-08 15:55:54', 1, '/uploadFile/pic/English.jpg', 1, 1, 3, NULL),
	(1464, '0108高二英语金考卷高考水平测试卷及答案0109使用', 62, '/uploadFile/file/0108高二英语金考卷高考水平测试卷及答案0109使用.pdf', '2015-01-08 15:58:00', 1, '/uploadFile/pic/English.jpg', 1, 1, 3, NULL),
	(1465, '0108高一英语报纸第15期', 62, '/uploadFile/file/0108高一英语报纸第15期.pdf', '2015-01-08 16:01:07', 1, '/uploadFile/pic/English.jpg', 1, 1, 1, NULL),
	(1466, '0108高一英语辅导报13至16期答案', 62, '/uploadFile/file/0108高一英语辅导报13至16期答案.pdf', '2015-01-08 16:01:44', 1, '/uploadFile/pic/English.jpg', 1, 1, 1, NULL),
	(1467, '0108高一化学必修2全品第一章及单元测评一A习题', 64, '/uploadFile/file/0108高一化学必修2全品第一章及单元测评一A习题.pdf', '2015-01-08 16:05:22', 1, '/uploadFile/pic/huaxue.jpg', 0, 1, 1, 0),
	(1468, '0108高一化学必修2全品第一章及单元测评一A习题答案', 68, '/uploadFile/file/0108高一化学必修2全品第一章及单元测评一A习题答案.pdf', '2015-01-08 16:05:52', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 1, NULL),
	(1469, '0108高一化学习题集必修2第一章', 68, '/uploadFile/file/0108高一化学习题集必修2第一章.pdf', '2015-01-08 16:09:25', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 1, NULL),
	(1470, '0108高一化学必修二第一章习题集答案', 68, '/uploadFile/file/0108高一化学必修二第一章习题集答案.pdf', '2015-01-08 16:09:53', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 1, NULL),
	(1471, '0108高一化学必修二指导书第一章习题', 68, '/uploadFile/file/0108高一化学必修二指导书第一章习题.pdf', '2015-01-08 16:10:35', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 1, NULL),
	(1472, '0108高一化学必修二指导书第一章答案', 68, '/uploadFile/file/0108高一化学必修二指导书第一章答案.pdf', '2015-01-08 16:11:02', 1, '/uploadFile/pic/huaxue.jpg', 1, 1, 1, NULL);
/*!40000 ALTER TABLE `courseware` ENABLE KEYS */;


-- 导出  表 smlearning_new.courseware_category 结构
CREATE TABLE IF NOT EXISTS `courseware_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(300) NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级课件分类编号',
  `sort_flag` int(3) NOT NULL DEFAULT '0' COMMENT '课件分类排序',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `use_type` int(1) DEFAULT NULL COMMENT '类型编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='课件分类';

-- 正在导出表  smlearning_new.courseware_category 的数据：~21 rows (大约)
/*!40000 ALTER TABLE `courseware_category` DISABLE KEYS */;
REPLACE INTO `courseware_category` (`id`, `name`, `parent_id`, `sort_flag`, `created_time`, `creator`, `use_type`) VALUES
	(1, '数学', 0, 1, '2014-01-01', 1, 0),
	(2, '语文', 0, 2, '2014-01-01', 1, 0),
	(3, '英语', 0, 3, '2014-01-01', 1, 0),
	(14, '政治', 0, 4, '2014-01-01', 1, 0),
	(53, '语文', 0, 1, '2014-01-06', 1, 1),
	(60, '数学', 0, 2, '2014-01-06', 1, 1),
	(61, '地理', 0, 3, '2014-01-06', 1, 1),
	(62, '英语', 0, 4, '2014-01-06', 1, 1),
	(63, '作业', 0, 1, '2014-02-13', 1, 1),
	(64, '物理', 0, 6, '2014-02-15', 1, 1),
	(66, '历史', 0, 6, '2014-06-28', 1, 1),
	(67, '政治', 0, 7, '2014-06-28', 1, 1),
	(68, '化学', 0, 8, '2014-06-28', 1, 1),
	(69, '生物', 0, 9, '2014-06-28', 1, 1),
	(71, '历史', 0, 5, '2014-06-28', 1, 0),
	(72, '地理', 0, 6, '2014-06-28', 1, 0),
	(73, '物理', 0, 7, '2014-06-28', 1, 0),
	(74, '化学', 0, 8, '2014-06-28', 1, 0),
	(75, '生物', 0, 9, '2014-06-28', 1, 0),
	(77, '电子白板', 0, 13, '2014-09-22', 1, 1),
	(78, '补充', 0, 10, '2014-09-24', 1, 1);
/*!40000 ALTER TABLE `courseware_category` ENABLE KEYS */;


-- 导出  表 smlearning_new.course_category 结构
CREATE TABLE IF NOT EXISTS `course_category` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(300) NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级课程分类编号',
  `sort_flag` int(3) NOT NULL COMMENT '课程分类排序',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.course_category 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `course_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_category` ENABLE KEYS */;


-- 导出  表 smlearning_new.course_courseware 结构
CREATE TABLE IF NOT EXISTS `course_courseware` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '编号',
  `course_id` bigint(20) NOT NULL COMMENT '课程编号',
  `courseware_id` bigint(20) NOT NULL COMMENT '课件编号',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `created_time` date NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.course_courseware 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `course_courseware` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_courseware` ENABLE KEYS */;


-- 导出  表 smlearning_new.course_plan 结构
CREATE TABLE IF NOT EXISTS `course_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '教学计划',
  `name` varchar(200) NOT NULL,
  `image_url` varchar(100) NOT NULL COMMENT '教学计划资源',
  `grade_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.course_plan 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `course_plan` DISABLE KEYS */;
REPLACE INTO `course_plan` (`id`, `name`, `image_url`, `grade_id`) VALUES
	(11, '16届高二第二周', '/uploadFile/file/跟我学spring3(1-7).pdf', 4),
	(12, '123', '/uploadFile/file/Netty5.0架构剖析和源码解读.pdf', 2),
	(13, 'aaaaa', '/uploadFile/file/Go 学习笔记 第三版.pdf', 4);
/*!40000 ALTER TABLE `course_plan` ENABLE KEYS */;


-- 导出  表 smlearning_new.course_schedule 结构
CREATE TABLE IF NOT EXISTS `course_schedule` (
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

-- 正在导出表  smlearning_new.course_schedule 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `course_schedule` DISABLE KEYS */;
REPLACE INTO `course_schedule` (`id`, `week_name`, `level_one`, `level_two`, `level_three`, `level_four`, `level_five`, `level_six`, `level_seven`, `level_eight`, `super_grade`, `super_class`) VALUES
	(1, '星期一', ' 物理', '数学', '体育', '语文', ' 英语', '计算机', '物理', '生物', '高三', '九班'),
	(2, '星期二', ' 数学', '语文', '体育', '计算机', ' 英语', '生物', '体育', ' 物理', '高三', '九班'),
	(3, '星期三', '语文', '数学', '地理', '体育', '英语', '物理', '生物', '化学', '高三', '九班'),
	(4, '星期四', '英语', '语文', '数学', '生物', '体育', '计算机', '物理', '化学', '高三', '九班'),
	(5, '星期五', '语文', '数学', '物理', '生物', '英语', '体育', '化学', '计算机', '高三', '九班');
/*!40000 ALTER TABLE `course_schedule` ENABLE KEYS */;


-- 导出  表 smlearning_new.course_table 结构
CREATE TABLE IF NOT EXISTS `course_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程表编号',
  `name` varchar(200) NOT NULL,
  `image_url` varchar(100) NOT NULL COMMENT '课程表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.course_table 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `course_table` DISABLE KEYS */;
REPLACE INTO `course_table` (`id`, `name`, `image_url`) VALUES
	(6, '16届高一期末考试前后安排', '/uploadFile/file/16届高一期末考试前后安排.pdf');
/*!40000 ALTER TABLE `course_table` ENABLE KEYS */;


-- 导出  表 smlearning_new.lesson 结构
CREATE TABLE IF NOT EXISTS `lesson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程进度编号',
  `class_id` bigint(20) NOT NULL COMMENT '班级编号',
  `subject_id` bigint(20) NOT NULL COMMENT '科目编号',
  `day` int(2) NOT NULL COMMENT '星期',
  `thetime` int(2) NOT NULL COMMENT '课程节数',
  `year` int(10) NOT NULL COMMENT '年数',
  `term` int(2) NOT NULL COMMENT '学期',
  `time_space` varchar(50) NOT NULL DEFAULT '' COMMENT '课程时间段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.lesson 的数据：~48 rows (大约)
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
REPLACE INTO `lesson` (`id`, `class_id`, `subject_id`, `day`, `thetime`, `year`, `term`, `time_space`) VALUES
	(1, 1, 1, 1, 1, 2014, 2, ''),
	(3, 1, 2, 2, 1, 2014, 2, ''),
	(10, 1, 3, 3, 1, 2014, 2, ''),
	(11, 1, 5, 4, 1, 2014, 2, ''),
	(12, 1, 6, 5, 1, 2014, 2, ''),
	(13, 1, 10, 6, 1, 2014, 2, ''),
	(14, 1, 8, 1, 2, 2014, 2, ''),
	(15, 1, 3, 2, 2, 2014, 2, ''),
	(16, 1, 10, 3, 2, 2014, 2, ''),
	(17, 1, 1, 4, 2, 2014, 2, ''),
	(18, 1, 6, 5, 2, 2014, 2, ''),
	(19, 1, 8, 6, 2, 2014, 2, ''),
	(20, 1, 10, 1, 3, 2014, 2, ''),
	(21, 1, 1, 2, 3, 2014, 2, ''),
	(22, 1, 9, 3, 3, 2014, 2, ''),
	(23, 1, 7, 4, 3, 2014, 2, ''),
	(24, 1, 9, 5, 3, 2014, 2, ''),
	(25, 1, 3, 6, 3, 2014, 2, ''),
	(26, 1, 8, 1, 4, 2014, 2, ''),
	(27, 1, 9, 2, 4, 2014, 2, ''),
	(28, 1, 1, 3, 4, 2014, 2, ''),
	(29, 1, 2, 4, 4, 2014, 2, ''),
	(30, 1, 7, 5, 4, 2014, 2, ''),
	(31, 1, 7, 6, 4, 2014, 2, ''),
	(32, 1, 2, 1, 5, 2014, 2, ''),
	(33, 1, 3, 2, 5, 2014, 2, ''),
	(34, 1, 1, 3, 5, 2014, 2, ''),
	(35, 1, 9, 4, 5, 2014, 2, ''),
	(36, 1, 6, 5, 5, 2014, 2, ''),
	(37, 1, 10, 6, 5, 2014, 2, ''),
	(38, 1, 8, 1, 6, 2014, 2, ''),
	(39, 1, 1, 2, 6, 2014, 2, ''),
	(40, 1, 3, 3, 6, 2014, 2, ''),
	(41, 1, 5, 4, 6, 2014, 2, ''),
	(42, 1, 9, 5, 6, 2014, 2, ''),
	(43, 1, 2, 6, 6, 2014, 2, ''),
	(44, 1, 2, 1, 7, 2014, 2, ''),
	(45, 1, 3, 2, 7, 2014, 2, ''),
	(46, 1, 5, 3, 7, 2014, 2, ''),
	(47, 1, 1, 4, 7, 2014, 2, ''),
	(48, 1, 6, 5, 7, 2014, 2, ''),
	(49, 1, 9, 6, 7, 2014, 2, ''),
	(50, 1, 1, 1, 8, 2014, 2, ''),
	(51, 1, 2, 2, 8, 2014, 2, ''),
	(52, 1, 3, 3, 8, 2014, 2, ''),
	(53, 1, 6, 4, 8, 2014, 2, ''),
	(54, 1, 8, 5, 8, 2014, 2, ''),
	(55, 1, 10, 6, 8, 2014, 2, '');
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;


-- 导出  表 smlearning_new.lesson_define 结构
CREATE TABLE IF NOT EXISTS `lesson_define` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(256) DEFAULT '',
  `YEAR` int(10) DEFAULT '0',
  `TERM` int(10) DEFAULT '1',
  `GRADE_ID` bigint(20) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.lesson_define 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `lesson_define` DISABLE KEYS */;
/*!40000 ALTER TABLE `lesson_define` ENABLE KEYS */;


-- 导出  表 smlearning_new.lesson_define_detail 结构
CREATE TABLE IF NOT EXISTS `lesson_define_detail` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `LESSON_ID` int(10) NOT NULL DEFAULT '0',
  `LESSON_NUM` int(10) NOT NULL DEFAULT '0',
  `LESSON_TIME` varchar(256) NOT NULL DEFAULT '',
  `WEEK_ONE_LESSON` varchar(256) NOT NULL DEFAULT '',
  `WEEK_TWO_LESSON` varchar(256) NOT NULL DEFAULT '',
  `WEEK_THREE_LESSON` varchar(256) NOT NULL DEFAULT '',
  `WEEK_FOUR_LESSON` varchar(256) NOT NULL DEFAULT '',
  `WEEK_FIVE_LESSON` varchar(256) NOT NULL DEFAULT '',
  `WEEK_SIX_LESSON` varchar(256) NOT NULL DEFAULT '',
  `WEEK_SEVEN_LESSON` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.lesson_define_detail 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `lesson_define_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `lesson_define_detail` ENABLE KEYS */;


-- 导出  表 smlearning_new.lesson_plan 结构
CREATE TABLE IF NOT EXISTS `lesson_plan` (
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

-- 正在导出表  smlearning_new.lesson_plan 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `lesson_plan` DISABLE KEYS */;
REPLACE INTO `lesson_plan` (`id`, `class_id`, `subject_id`, `day`, `year`, `content`, `homework`, `c_date`, `thetime`) VALUES
	(1, 1, 1, 1, 2014, 'oooooooooooooooo', 'p47', '01.06', 1),
	(2, 1, 1, 2, 2014, '函数复习学案之函数及其基本性质（12月23日已发邮箱）', 'p45', '01.06', 1),
	(3, 1, 1, 3, 2014, 'jjjoookkkkkkkkkkkkk', 'p98', '01.06', 1),
	(4, 1, 1, 4, 2014, '综合复习：第一章复习', 'p66', '01.06', 1),
	(5, 1, 1, 5, 2014, '习题课：', 'p76', '01.06', 1),
	(6, 1, 1, 6, 2014, '评讲冲刺（一）', 'p44', '01.06', 1),
	(7, 1, 2, 1, 2014, 'ccccc', 'p90', '01.07', 2),
	(8, 1, 2, 2, 2014, 'aaaa', 'p67', '01.07', 2),
	(9, 1, 2, 3, 2014, 'ssss', 'p90', '01.07', 2),
	(10, 1, 2, 4, 2014, 'ggg', 'p34', '01.07', 2),
	(11, 1, 2, 5, 2014, 'bbbb', 'p09', '01.07', 2),
	(12, 1, 2, 6, 2014, 'zzzz', 'p67', '01.07', 2);
/*!40000 ALTER TABLE `lesson_plan` ENABLE KEYS */;


-- 导出  表 smlearning_new.manager 结构
CREATE TABLE IF NOT EXISTS `manager` (
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.manager 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
REPLACE INTO `manager` (`id`, `name`, `password`, `actual_name`, `department`, `post`, `degree`, `use_type`, `status`, `class_id`) VALUES
	(1, 'admin', '999999', 'dd', 'e', '33', 0, 0, 0, NULL),
	(3, 'test', '999999', 'test', '教育部', '老师', 1, 0, 0, 1),
	(4, 'test2', '999999', 'test2', '', '', 1, 0, 0, 1),
	(5, 't1', '999999', 'li', '技术', '老师', 1, 1, 0, 1),
	(6, 't2', '999999', '张', '教育', '老师', 1, 1, 0, 1),
	(7, 't3', '999999', '李', '教育', '老师', 1, 1, 0, 1),
	(8, 't4', '999999', '刘', '教育', '老师', 1, 1, 0, 1),
	(9, 't5', '999999', '王', '教育', '教师', 1, 1, 0, 1),
	(10, 'csgl', '123456', '北京测试', NULL, NULL, 0, 0, 0, 0),
	(11, 'bjcsjs1', '999999', '北京测试教师一', '1,4', '2', 1, 0, 0, 0);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;


-- 导出  表 smlearning_new.online_forum 结构
CREATE TABLE IF NOT EXISTS `online_forum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '在线交流编号',
  `name` varchar(1000) DEFAULT NULL COMMENT '主题',
  `question` varchar(3000) DEFAULT NULL COMMENT '问题',
  `root_id` bigint(20) DEFAULT NULL COMMENT '根贴编号',
  `child_num` int(10) DEFAULT NULL COMMENT '跟贴数量',
  `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(200) DEFAULT NULL COMMENT '创建人',
  `class_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.online_forum 的数据：~35 rows (大约)
/*!40000 ALTER TABLE `online_forum` DISABLE KEYS */;
REPLACE INTO `online_forum` (`id`, `name`, `question`, `root_id`, `child_num`, `created_time`, `creator`, `class_id`) VALUES
	(39, '中国教育问题', '解决1+1=3问题？', 0, 0, '2014-06-12 15:02:22', 'dd', 1),
	(40, '中国教育问题', '不会', 39, 1, '2014-06-12 15:02:57', 'test1', 1),
	(41, '中国教育问题', '无解', 39, 0, '2014-06-12 15:04:12', '邢荣', NULL),
	(42, '中国教育问题', 'gg', 39, 0, '2014-06-12 15:20:35', '邢荣', NULL),
	(45, '中国教育问题', 'ytxhgdugdutdytdtyd', 39, 0, '2014-06-23 20:08:41', 'aaa', NULL),
	(47, '关注', '关于问题的解答与回复', 0, 0, '2014-06-28 11:40:11', 'dd', 1),
	(48, '关注', 'weds', 47, 1, '2014-06-28 12:17:02', 'test1', 1),
	(49, '中国教育问题', '18137887983', 39, 1, '2014-07-04 16:59:44', 'test2', 1),
	(50, '关注', '你好，请问物理课本32页28题怎么解？', 47, 0, '2014-07-07 17:40:46', 'aaa', NULL),
	(51, '关注', '你说哪个题？', 47, 0, '2014-07-07 17:42:29', 'aaa', NULL),
	(52, '中国教育问题', '哪位？', 39, 0, '2014-07-15 17:01:26', 'aaa', NULL),
	(53, '中国教育问题', '知道', 39, 0, '2014-07-16 17:34:21', 'aaa', NULL),
	(54, '关注', '就是那个28题', 47, 0, '2014-09-16 16:34:01', 'aaa', NULL),
	(55, '关注', '', 47, 0, '2014-09-21 10:34:47', 'aaa', NULL),
	(56, '关注', '我的朋友', 47, 0, '2014-09-22 18:02:51', 'w', NULL),
	(57, '关注', '郭德纲东方红方法恢复', 47, 0, '2014-09-23 08:03:59', 's', NULL),
	(58, '关注', '育恒推广', 47, 0, '2014-09-23 13:41:24', 'q', NULL),
	(59, '关注', 'rr', 47, 0, '2014-09-23 14:52:17', 'aaa', NULL),
	(60, '关注', 'fghjkk', 47, 0, '2014-09-23 14:52:32', 'aaa', NULL),
	(61, '关注', '', 47, 0, '2014-09-23 14:52:34', 'aaa', NULL),
	(62, '关注', 'bl', 47, 0, '2014-09-23 14:52:45', 'aaa', NULL),
	(63, '关注', 'hchkk', 47, 0, '2014-09-23 14:53:02', 'aaa', NULL),
	(64, '关注', 'qqqqqqq', 47, 0, '2014-09-23 14:53:42', 'aaa', NULL),
	(65, '中国教育问题', 'rrrrrr', 39, 0, '2014-09-23 14:54:30', 'aaa', NULL),
	(66, '中国教育问题', 'ooooopp', 39, 0, '2014-09-23 14:54:54', 'aaa', NULL),
	(67, '关注', 'rrrrr', 47, 0, '2014-09-23 15:09:25', 'aaa', NULL),
	(68, '中国教育问题', 'rty', 39, 1, '2014-09-24 10:38:39', 'li', 1),
	(69, '中国教育问题', '', 39, 1, '2014-09-24 10:38:50', 'li', 1),
	(70, '关注', '干活哈哈', 47, 1, '2014-09-24 17:14:59', 'li', 1),
	(71, '关注', '', 47, 1, '2014-09-24 17:14:59', 'li', 1),
	(72, '关注', '', 47, 1, '2014-09-24 17:15:01', 'li', 1),
	(73, '关注', '', 47, 1, '2014-09-24 17:15:04', 'li', 1),
	(74, '关注', '啊啊啊啊', 47, 1, '2014-09-24 17:15:14', 'li', 1),
	(75, '中国教育问题', '我要好好学习', 39, 1, '2014-09-24 17:17:20', 'li', 1),
	(76, '关注', '', 47, 1, '2014-09-24 17:17:39', 'li', 1);
/*!40000 ALTER TABLE `online_forum` ENABLE KEYS */;


-- 导出  表 smlearning_new.organ 结构
CREATE TABLE IF NOT EXISTS `organ` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '联系人',
  `name` varchar(300) NOT NULL COMMENT '单位名称',
  `grade` varchar(200) DEFAULT NULL COMMENT '单位级别',
  `degree` int(1) DEFAULT '1' COMMENT '身份',
  `linkman` varchar(100) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `server_ip` varchar(100) DEFAULT NULL COMMENT '服务器IP地址',
  `server_port` varchar(10) NOT NULL DEFAULT '80' COMMENT '服务器端口',
  `pid` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.organ 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `organ` DISABLE KEYS */;
REPLACE INTO `organ` (`id`, `name`, `grade`, `degree`, `linkman`, `tel`, `server_ip`, `server_port`, `pid`) VALUES
	(1, '育恒教育', '育恒教育', 1, NULL, NULL, NULL, '80', 0);
/*!40000 ALTER TABLE `organ` ENABLE KEYS */;


-- 导出  表 smlearning_new.resources_move_path 结构
CREATE TABLE IF NOT EXISTS `resources_move_path` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` int(10) NOT NULL DEFAULT '1',
  `name` varchar(256) NOT NULL DEFAULT '',
  `m_path` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.resources_move_path 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `resources_move_path` DISABLE KEYS */;
REPLACE INTO `resources_move_path` (`id`, `type`, `name`, `m_path`) VALUES
	(1, 1, '资料', 'D:\\smlearning-tomcat\\SmartLearningServer\\uploadFile'),
	(2, 1, '作业', 'D:\\testpaper-tomcat\\Study\\upload\\testpaper'),
	(3, 1, '临时文件', 'D:\\smlearning-tomcat\\SmartLearningServer\\uploadFile\\temp');
/*!40000 ALTER TABLE `resources_move_path` ENABLE KEYS */;


-- 导出  表 smlearning_new.sys_class 结构
CREATE TABLE IF NOT EXISTS `sys_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `grade_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.sys_class 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_class` DISABLE KEYS */;
REPLACE INTO `sys_class` (`id`, `name`, `created_time`, `creator`, `grade_id`) VALUES
	(1, '高三一班', '2014-01-13', 1, 4),
	(2, '高三二班', '2014-01-13', 1, 4),
	(4, '高三三班', '2014-01-13', 1, 4),
	(5, '高一一班', '2015-01-10', 1, 1);
/*!40000 ALTER TABLE `sys_class` ENABLE KEYS */;


-- 导出  表 smlearning_new.sys_grade 结构
CREATE TABLE IF NOT EXISTS `sys_grade` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT '',
  `org_id` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `creator` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.sys_grade 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `sys_grade` DISABLE KEYS */;
REPLACE INTO `sys_grade` (`id`, `name`, `org_id`, `create_time`, `creator`) VALUES
	(1, '高中一年级', 1, '2014-12-18 00:00:00', 1),
	(2, '高中二年级文科', 1, '2014-12-18 00:00:00', 1),
	(3, '高中二年级理科', 1, '2014-12-18 00:00:00', 1),
	(4, '高中三年级文科', 1, '2014-12-18 00:00:00', 1),
	(5, '高中三年级理科', 1, '2014-12-18 00:00:00', 1);
/*!40000 ALTER TABLE `sys_grade` ENABLE KEYS */;


-- 导出  表 smlearning_new.sys_key 结构
CREATE TABLE IF NOT EXISTS `sys_key` (
  `id` bigint(20) NOT NULL,
  `key_name` varchar(100) NOT NULL COMMENT '术语名称',
  `key_value` varchar(1000) DEFAULT NULL COMMENT '术语值',
  `can_modify` int(1) NOT NULL DEFAULT '1' COMMENT '是否可维护',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.sys_key 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `sys_key` DISABLE KEYS */;
REPLACE INTO `sys_key` (`id`, `key_name`, `key_value`, `can_modify`) VALUES
	(0, '性别', '男;女;', 1),
	(1, '部门', '研发部;开发部;事业部', 1),
	(2, '职务', '工程师;业务员', 1),
	(3, '民族', '汉;满;', 1),
	(4, '政治面貌', '党员;非党员;', 1),
	(5, '职务级别', '科级;处级;', 1),
	(6, '证件类型', '身份证;军人证;', 1),
	(13, 'serverIP', '192.168.109.10', 0);
/*!40000 ALTER TABLE `sys_key` ENABLE KEYS */;


-- 导出  表 smlearning_new.sys_message 结构
CREATE TABLE IF NOT EXISTS `sys_message` (
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.sys_message 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_message` DISABLE KEYS */;
REPLACE INTO `sys_message` (`id`, `name`, `content`, `status`, `created_time`, `creator`, `organ_name`, `organ_grade`, `organ_degree`, `sent_time`, `can_send_all`, `class_id`) VALUES
	(9, '会议通知', '今天下午3点开会', 0, '2014-06-04 10:21:26', 3, '育恒教育', '1', 1, NULL, 1, 1);
/*!40000 ALTER TABLE `sys_message` ENABLE KEYS */;


-- 导出  表 smlearning_new.sys_subject 结构
CREATE TABLE IF NOT EXISTS `sys_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '科目编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `created_time` date NOT NULL COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.sys_subject 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `sys_subject` DISABLE KEYS */;
REPLACE INTO `sys_subject` (`id`, `name`, `created_time`, `creator`) VALUES
	(1, '语文', '2014-01-13', 1),
	(2, '数学', '2014-01-13', 1),
	(3, '英语', '2014-01-13', 1),
	(5, '物理', '2014-01-14', 1),
	(6, '体育', '2014-01-14', 1),
	(8, '物理', '2014-01-14', 1),
	(9, '生物', '2014-01-14', 1),
	(10, '化学', '2014-01-14', 1);
/*!40000 ALTER TABLE `sys_subject` ENABLE KEYS */;


-- 导出  表 smlearning_new.user_course_note 结构
CREATE TABLE IF NOT EXISTS `user_course_note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) NOT NULL COMMENT '学员编号',
  `courseware_id` bigint(20) DEFAULT NULL COMMENT '课件编号',
  `note` text COMMENT '文本内容',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.user_course_note 的数据：~17 rows (大约)
/*!40000 ALTER TABLE `user_course_note` DISABLE KEYS */;
REPLACE INTO `user_course_note` (`id`, `user_id`, `courseware_id`, `note`, `created_time`) VALUES
	(3, 4, 1, 'wwwwwwwddddd', '2014-02-08 18:21:28'),
	(4, 4, 1, 'wwwwwwwddddd', '2014-02-08 18:24:21'),
	(5, 4, 1, 'wwwwwwwddddd', '2014-02-08 18:30:15'),
	(6, 4, 1, 'wwwwwwwddddd', '2014-02-08 18:31:26'),
	(7, 4, 1, 'wwwwwwwddddd', '2014-02-08 18:33:04'),
	(8, 4, 1, 'wwwwwwwddddd', '2014-02-08 18:33:06'),
	(9, 4, 1, 'jjhggfddetyyhh', '2014-02-08 18:36:29'),
	(10, 4, 1, 'fee yyyy', '2014-02-08 18:57:24'),
	(11, 4, 1, 'seeresses', '2014-02-08 18:59:52'),
	(12, 4, 1, 'eeeeeee', '2014-02-08 19:47:23'),
	(13, 5, 1, 'qqqqqqqq', '2014-02-08 20:05:20'),
	(14, 4, 1, '123456789', '2014-02-10 14:09:36'),
	(15, 4, 1, '饮料', '2014-02-10 14:12:25'),
	(16, 4, 1, 'rrrr', '2014-02-10 17:17:17'),
	(17, 4, 1, '', '2014-02-11 12:03:57'),
	(18, 4, 1, 'yyyyyyyyyy', '2014-02-11 12:17:20'),
	(19, 4, 1, 'sssssss', '2014-02-11 13:22:52');
/*!40000 ALTER TABLE `user_course_note` ENABLE KEYS */;


-- 导出  表 smlearning_new.user_info 结构
CREATE TABLE IF NOT EXISTS `user_info` (
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
) ENGINE=InnoDB AUTO_INCREMENT=436 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.user_info 的数据：~414 rows (大约)
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
REPLACE INTO `user_info` (`id`, `name`, `password`, `actual_name`, `sex`, `age`, `email`, `status`, `nation`, `birthdate`, `address`, `class_id`) VALUES
	(14, '412721199710165010', '888888', '高康', '男', 20, '123QQ.com', 0, '汉', NULL, '555', 2),
	(15, '41272119960702341X', '888888', '齐胜飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(16, '412721199609103413', '888888', '赵迎磊', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(17, '412724199704293314', '888888', '贾凯博', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(18, '412721199809202248', '888888', '李金阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(19, '41272119970316479X', '888888', '娄帅威', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(20, '412721199703050645', '888888', '王佩', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(21, '411621199808070339', '888888', '邹明康', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(22, '412721199802063820', '888888', '葛盼盼', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(23, '412721199910134235', '888888', '李帅旗', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(24, '41272119961215502X', '888888', '廉雪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(25, '412721199803044250', '888888', '于畅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(26, '412721199902130621', '888888', '高文静', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(27, '412721199704243019', '888888', '吴少翔', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(28, '412721199809151815', '888888', '张威', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(29, '41272119970512381X', '888888', '李克强', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(30, '412721199704125055', '888888', '李华阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(31, '412721199705014218', '888888', '张家康', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(32, '412721199805251843', '888888', '李虹静', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(33, '412722199803308742', '888888', '陈曼瑜', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(34, '412721199801084216', '888888', '刘雅各', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(35, '41272119970606185X', '888888', '魏冰柯', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(36, '412721199511042333', '888888', '闫晓兵', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(37, '412721199708142258', '888888', '郭艳兵', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(38, '412721199610253830', '888888', '朱广生', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(39, '412721199609123414', '888888', '张志宇', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(40, '412721199802124240', '888888', '刘欣欣', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(41, '412721199710293813', '888888', '尹林威', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(42, '412721199602126217', '888888', '聂金鸽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(43, '412721199801142623', '888888', '周瑞娜', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(44, '412721199805073047', '888888', '吴静文', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(45, '412721199710282612', '888888', '谷科友', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(46, '412721199907045039', '888888', '张少明', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(47, '412721199805273831', '888888', '周寰宇', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(48, '412721199509274258', '888888', '孙云峰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(49, '412721199810063054', '888888', '李昌钰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(50, '412721199704163836', '888888', '李燕涛', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(51, '412721199708152632', '888888', '刘阳光', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(52, '412721199802072217', '888888', '郭港华', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(53, '41162119980528311X', '888888', '姜宁毫', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(54, '411621199901123079', '888888', '姜培亮', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(55, '41272119970523224X', '888888', '李爱芳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(56, '412721199805154613', '888888', '王壮', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(57, '412721199805054284', '888888', '常刘杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(58, '412721199712031509', '888888', '贾旭阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(59, '412721199802113429', '888888', '鲁淼', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(60, '412721199707061851', '888888', '陈永港', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(61, '412721199703205475', '888888', '何鹏宇', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(62, '412721199410101509', '888888', '李露', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(63, '412721199804125028', '888888', '郝笑会', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(64, '412721199806183862', '888888', '尹文瑜', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(65, '412721199902092629', '888888', '翟梦娜', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(66, '412721199901181013', '888888', '杨涛', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(67, '412721199708295051', '888888', '杨山泉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(68, '412721199810110324', '888888', '秦琼琼', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(69, '412721199901270315', '888888', '郭兆岑', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(70, '412721199612202252', '888888', '张帅飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(71, '412721199810263822', '888888', '杜倩文', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(72, '41272119980304471X', '888888', '任留顺', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(73, '41272119960909385X', '888888', '王重阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(74, '412721199707101024', '888888', '贾可可', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(75, '412721199810161068', '888888', '郑彩霞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(76, '412722199804174010', '888888', '郭星晨', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(77, '412721199805181830', '888888', '陈名阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(78, '412721199712231894', '888888', '胡春辉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(79, '412721199705154210', '888888', '常海坤', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(80, '412722199805282532', '888888', '刘霄杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(81, '41272119970103510X', '888888', '宋文君', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(82, '412721199801015026', '888888', '刘丹阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(83, '412721199703120332', '888888', '李宏阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(84, '412721199806063043', '888888', '史湘威', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(85, '412721199804054231', '888888', '孙俊超', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(86, '412721199710284212', '888888', '赵春雷', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(87, '412721199705101856', '888888', '尹献坡', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(88, '412721199912022675', '888888', '葛嘉宝', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(89, '412721199702033380', '888888', '冯旭冉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(90, '412721199703093864', '888888', '王婧颐', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(91, '412724199701144428', '888888', '张文文', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(92, '412721199710253029', '888888', '张梦鸽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(93, '412721199903205025', '888888', '任发启', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(94, '412721199901300318', '888888', '邹志翔', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(95, '41272119970924423X', '888888', '杜海放', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(96, '412721199711104244', '888888', '常庆悦', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(97, '412721199704164652', '888888', '娄元帅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(98, '411082199510234839', '888888', '王静宇', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(99, '412724199603224037', '888888', '李留原', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(100, '412721199806078831', '888888', '潘铭赫', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(101, '412721199706044232', '888888', '董澎飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(102, '412721199804251411', '888888', '张小兵', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(103, '412721199804064210', '888888', '侯肖磊', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(104, '411621199510283040', '888888', '聂梦雅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(105, '412721199610205417', '888888', '安慧民', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(106, '412721199703031022', '888888', '郭晶', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(107, '412721199712153814', '888888', '张亚杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(108, '41162119971202467X', '888888', '王凯迪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(109, '412721199609302615', '888888', '牛帅豪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(110, '412721199701202633', '888888', '尹腾耀', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(111, '412724199705042533', '888888', '刘华楠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(112, '412721199804275448', '888888', '吕萌萌', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(113, '412721199810041883', '888888', '杜苗苗', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(114, '412721199804195448', '888888', '马方方', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(115, '412721199710261811', '888888', '张松伟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(116, '41162119981027033X', '888888', '李帅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(117, '411621199806022616', '888888', '张东豪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(118, '412724199807142957', '888888', '郭一凡', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(119, '412721199711045010', '888888', '刘登科', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(120, '41272119981127067X', '888888', '李凯旋', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(121, '412721199910015017', '888888', '郝江潮', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(122, '412721199806163458', '888888', '马彦轲', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(123, '412721199710181918', '888888', '何培坤', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(124, '412721199503012636', '888888', '王腾飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(125, '412721199712305013', '888888', '刘相阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(126, '412721199710161415', '888888', '杨云召', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(127, '412721199805203833', '888888', '卢锦涛', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(128, '412721199805070639', '888888', '穆晓枫', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(129, '412721199705262633', '888888', '尹玉迪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(130, '412721199604101013', '888888', '石贝', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(131, '412721199707154214', '888888', '何汪洋', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(132, '412721199701124217', '888888', '李博文', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(133, '412721199610094657', '888888', '王九龙', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(134, '412724199811272922', '888888', '王心秀', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(135, '412721199805163835', '888888', '陈函志', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(136, '412721199803161828', '888888', '杜香香', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(137, '412721199610085048', '888888', '李晨茜', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(138, '412721199706275014', '888888', '李华康', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(139, '412721199809074733', '888888', '孙天文', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(140, '412721199711153847', '888888', '王云', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(141, '412721199812011856', '888888', '李俊广', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(142, '412721199809282671', '888888', '施家康', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(143, '412721199808282610', '888888', '翟文凯', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(144, '412721199803233414', '888888', '宁登科', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(145, '412721199709223834', '888888', '边金龙', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(146, '412725199611077416', '888888', '薛雷', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(147, '410222199807303018', '888888', '张振源', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(148, '412721199807280015', '888888', '田壹名', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(149, '412721199709100025', '888888', '曹丹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(150, '412721199603082623', '888888', '崔冰杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(151, '412721199802230425', '888888', '黄梦珠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(152, '412721199901265049', '888888', '郁闯', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(153, '412721199608161427', '888888', '宋贝贝', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(154, '412722199803194925', '888888', '苏钰杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(155, '411621199709270044', '888888', '张莉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(156, '412721199702042651', '888888', '路跃力', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(157, '412721199707095824', '888888', '侯文雅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(158, '412721199604233825', '888888', '杨二苹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(159, '412721199805065071', '888888', '刘慧斌', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(160, '412721199510202331', '888888', '谢波涛', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(161, '412721199804141810', '888888', '王向杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(162, '412721199602273452', '888888', '宁东辉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(163, '412721199711053838', '888888', '孙乐', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(164, '412721199612243476', '888888', '顾博伦', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(165, '412721199501182631', '888888', '邢兵', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(166, '412721199906175480', '888888', '齐静静', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(167, '412721199704064213', '888888', '王雷振', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(168, '412721199612251433', '888888', '张一凡', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(169, '412721199807124215', '888888', '高速贺', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(170, '412721199811295017', '888888', '董飞虎', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(171, '41272419980529253X', '888888', '赵晨阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(172, '412721199708113035', '888888', '谢林阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(173, '411621199705013040', '888888', '史笑丽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(174, '412721199801093067', '888888', '王孟雪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(175, '412721199806265446', '888888', '栾园园', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(176, '41272119990806264X', '888888', '杨姝', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(177, '412721199709060617', '888888', '李腾飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(178, '412721199703063454', '888888', '樊丰毅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(179, '412721199810091864', '888888', '王亚娟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(180, '412721199601232616', '888888', '任文康', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(181, '412721199903211829', '888888', '周娟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(182, '412721199709024237', '888888', '赵梦阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(183, '412721199709202611', '888888', '李磊', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(184, '412721199907125012', '888888', '郭家辉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(185, '412721199705284218', '888888', '井美绅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(186, '412721199706181878', '888888', '李意威', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(187, '412721199812101835', '888888', '周兴超', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(188, '412721199708195835', '888888', '梁孟君', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(189, '41272119980826181X', '888888', '金刘阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(190, '411621199701173063', '888888', '高俊芳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(191, '41272419980514254X', '888888', '陈晨', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(192, '412724199911082544', '888888', '孔格格', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(193, '412721199901282615', '888888', '刘静纯', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(194, '412721199711161871', '888888', '郭程龙', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(195, '412721199701115417', '888888', '付学言', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(196, '412721199304132635', '888888', '李文基', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(197, '412721199505052236', '888888', '陈云功', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(198, '412721199912081513', '888888', '赫志强', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(199, '412721199904204719', '888888', '刘禹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(200, '412724199604262924', '888888', '陈银慧', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(201, '412721199707172236', '888888', '姜琳琅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(202, '412721199504022617', '888888', '梅海洋', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(203, '412721199801016213', '888888', '万笑虎', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(204, '41272119970628501X', '888888', '李家乐', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(205, '412721199701134618', '888888', '王鹏飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(206, '412721199703013414', '888888', '张威威', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(207, '412721199801234616', '888888', '韩宇', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(208, '411621199707183035', '888888', '何争港', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(209, '41272119980507543X', '888888', '王光耀', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(210, '412721199801205444', '888888', '杜倩杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(211, '412721199811275040', '888888', '王四琼', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(212, '412721199901081020', '888888', '祝赢娟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(213, '412721199902245074', '888888', '郁海洲', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(214, '41272119951112462X', '888888', '苏瑞姣', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(215, '412721199803203864', '888888', '杜俊娅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(216, '412721199810280016', '888888', '赵宇飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(217, '412721199510203436', '888888', '李亚军', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(218, '412721199802185430', '888888', '何汶举', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(219, '412721199705190019', '888888', '李嘉龙', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(220, '412721199704265410', '888888', '朱会杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(221, '41272119931120262X', '888888', '张婧霞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(222, '412721199602171827', '888888', '何梦贝', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(223, '412721199903182650', '888888', '陈飞宇', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(224, '412721199612164305', '888888', '刘雪雅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(225, '41272119990806184X', '888888', '丁之琳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(226, '411621199801143048', '888888', '吴明珠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(227, '412721199510025021', '888888', '郝紫琦', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(228, '412721199707192632', '888888', '朱振方', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(229, '412721199804150610', '888888', '严奇光', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(230, '41272119990109143X', '888888', '杜向阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(231, '412721199702173811', '888888', '张龙飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(232, '411621199705163030', '888888', '朱金松', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(233, '412721199805123825', '888888', '常乐园', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(234, '412721199807301429', '888888', '张华丽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(235, '412721199805171878', '888888', '何景山', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(236, '412721199712222621', '888888', '张亚萌', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(237, '41272219960612256X', '888888', '张琳霞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(238, '412721199802190387', '888888', '侯时雨', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(239, '412721199801061022', '888888', '郭思雨', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(240, '412721199708184765', '888888', '王皎', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(241, '412721199503161834', '888888', '严江北', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(242, '411621199605161062', '888888', '李豆', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(243, '41272119960317224X', '888888', '时秋珂', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(244, '412721199811111863', '888888', '李静', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(245, '412721199809293063', '888888', '吴银玲', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(246, '412721199406202649', '888888', '李静银', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(247, '412721199811023441', '888888', '王璞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(248, '412721199706114288', '888888', '张文雅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(249, '412721199612294767', '888888', '李亚田', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(250, '412721199706210616', '888888', '刘骁健', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(251, '412724199811172534', '888888', '王加辉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(252, '411621199710103067', '888888', '张梦姣', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(253, '411627199912302126', '888888', '王银慧', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(254, '412721199608281023', '888888', '张燕杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(255, '41272119980117182X', '888888', '杜倩', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(256, '412721199704062648', '888888', '路璐', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(257, '41162119960515308X', '888888', '赵梦娟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(258, '412721199805264222', '888888', '祁肖茜', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(259, '411621199805204645', '888888', '薄彩云', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(260, '412721199509234221', '888888', '孙浩', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(261, '412721199611203448', '888888', '韩瑞苹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(262, '412721199809094224', '888888', '井美华', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(263, '41272119951210265X', '888888', '朱鹏飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(264, '412721199701014624', '888888', '王艳伟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(265, '412721199804031822', '888888', '刘畅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(266, '412721199710032226', '888888', '邵玉琪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(267, '412721199509075435', '888888', '何向阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(268, '412721199809035056', '888888', '郝帅威', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(269, '412721199708073467', '888888', '张卉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(270, '412721199812074224', '888888', '张丽娜', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(271, '412721199707273811', '888888', '王奇', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(272, '412721199810091020', '888888', '魏若冰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(273, '412721199611102284', '888888', '闫亚如', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(274, '412721199803113025', '888888', '张文雅', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(275, '412721199810180023', '888888', '刘梦瑶', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(276, '41272119960519061X', '888888', '赵豪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(277, '412721199810264614', '888888', '刘晨明', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(278, '41272119960201424X', '888888', '刘晓静', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(279, '412721199701202238', '888888', '范云飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(280, '411621199701243036', '888888', '闫慧林', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(281, '412721199812083044', '888888', '赵宇鸽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(282, '412721199707202247', '888888', '宋留画', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(283, '412721199808200312', '888888', '何琤翔', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(284, '412721199711272256', '888888', '李鑫龙', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(285, '412721199805201440', '888888', '张雅琼', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(286, '412721199810095435', '888888', '齐指辉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(287, '412721199712133418', '888888', '鲁培垒', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(288, '412721199709063412', '888888', '齐光耀', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(289, '412721199807143846', '888888', '张梦琳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(290, '412721199703283422', '888888', '赵静文', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(291, '412721199910023842', '888888', '杜玉珠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(292, '412721199801140301', '888888', '吴瑞晗', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(293, '412721199709103410', '888888', '侯彦康', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(294, '412721199711251033', '888888', '张富申', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(295, '412721199508254634', '888888', '刘萌科', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(296, '41272119980916267X', '888888', '施晨凯', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(297, '41272119980513302X', '888888', '朱开琴', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(298, '412721199806110321', '888888', '刘晓易', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(299, '412724199611042938', '888888', '王可飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(300, '412721199701084227', '888888', '陈利莎', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(301, '412721199711084220', '888888', '常梦娟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(302, '412721199709143842', '888888', '张家瑞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(303, '412721199808070028', '888888', '曹涵冲', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(304, '412721199801123828', '888888', '刘丹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(305, '411621199804233831', '888888', '杜东孝', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(306, '412721199808093836', '888888', '王亚涛', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(307, '412721199509231821', '888888', '郭莉杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(308, '41272119961220264X', '888888', '何思佳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(309, '411621199403121847', '888888', '陈星星', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(310, '412721199710302265', '888888', '谢梦月', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(311, '412721199805181857', '888888', '丁子昂', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(312, '41272119961230032X', '888888', '梁玉婷', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(313, '410526199803051169', '888888', '牛霄雯', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(314, '412721199711161839', '888888', '严豪强', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(315, '412721199810121031', '888888', '刘庆磊', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(316, '412721199504294217', '888888', '常天其', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(317, '412721199908061428', '888888', '何静思', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(318, '412721199703023022', '888888', '李莹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(319, '412721199606212622', '888888', '唐京婷', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(320, '412721199812120323', '888888', '杜涵', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(321, '412721199712085014', '888888', '宋亚涛', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(322, '412721200005205086', '888888', '张梦园', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(323, '412721199411093424', '888888', '张梦爽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(324, '41272119980120103X', '888888', '张亚飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(325, '412721199712263829', '888888', '张柯', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(326, '41272119981013342X', '888888', '李文艺', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(327, '412721199812063824', '888888', '孙盈盈', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(328, '412721199812065037', '888888', '长进才', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(329, '412721199810193043', '888888', '张文丹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(330, '412721199611224767', '888888', '陈瑞杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(331, '412721199706165448', '888888', '张慧如', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(332, '41272119970608463X', '888888', '王博奇', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(333, '41272119970506424X', '888888', '赵乐珊', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(334, '412721199709240626', '888888', '江苗苗', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(335, '412721199707202626', '888888', '任云玲', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(336, '412721199804161467', '888888', '曹肖楠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(337, '412721199811173829', '888888', '陈婷婷', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(338, '412721199610281866', '888888', '彭梦楠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(339, '412721199702044729', '888888', '薄玲玲', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(340, '41272119980113421X', '888888', '魏书豪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(341, '412721199805181194', '888888', '马驰涵', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(342, '41272119990511503X', '888888', '刘颖飞', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(343, '412721199801294221', '888888', '李蓉蓉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(344, '412721199601172625', '888888', '庞璐爽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(345, '412721199802064305', '888888', '刘梦晴', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(346, '412721199706114237', '888888', '孙云龙', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(347, '412721199612254220', '888888', '郭文婷', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(348, '412721199604133445', '888888', '骆莹莹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(349, '412721199802191048', '888888', '刘梦莹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(350, '412721199702272617', '888888', '梅中旺', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(351, '412721199701212620', '888888', '张晶晶', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(352, '412721199609131035', '888888', '张晓龙', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(353, '412721199812024614', '888888', '刘飞凡', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(354, '412721199806221419', '888888', '焦立博', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(355, '412721199710175462', '888888', '刘朵', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(356, '411621199802203102', '888888', '吴雨珠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(357, '412721199803031417', '888888', '李志丹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(358, '411621199510133026', '888888', '史玲莉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(359, '412721199702233044', '888888', '李广姣', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(360, '412721199706143433', '888888', '张闯', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(361, '412721199411204657', '888888', '贺义冲', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(362, '412721199612013427', '888888', '高嘉琳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(363, '412721199703063446', '888888', '顾晓婉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(364, '412721199710071831', '888888', '薄保罗', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(365, '412721199701133053', '888888', '吴楠楠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(366, '41272119960829502X', '888888', '李梦华', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(367, '412721199810263849', '888888', '豆惠迪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(368, '412721199712034225', '888888', '赵婷婷', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(369, '412721199704045063', '888888', '刘亚芳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(370, '412721199706074220', '888888', '卢士旗', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(371, '412721199709094681', '888888', '王欣欣', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(372, '412721199707015011', '888888', '宋俊龙', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(373, '412721199607262613', '888888', '王豪威', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(374, '412721199801084224', '888888', '解春鸣', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(375, '412721199704012624', '888888', '杨立原', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(376, '412721199609173825', '888888', '白亚丹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(377, '412721199710164659', '888888', '牛梦祥', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(378, '412721199711293428', '888888', '施亚楠', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(379, '412721199709112245', '888888', '任梦兵', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(380, '412721199806060336', '888888', '张锦泽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(381, '412721199609021426', '888888', '聂梦鸽', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(382, '41272119970920502X', '888888', '李慧娜', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(383, '412721199804120331', '888888', '班昊阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(384, '412721199709062620', '888888', '乔芳芳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(385, '412721199704163449', '888888', '顾静怡', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(386, '412721199511231441', '888888', '张晨佳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(387, '412721199802192219', '888888', '张东方', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(388, '412721199505100613', '888888', '田友', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(389, '412721199804243446', '888888', '李玉莹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(390, '412721199702061844', '888888', '代萌', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(391, 'xushisong', '888888', '邢荣', '男', 20, '123QQ.com', 0, '汉', NULL, '555', 1),
	(392, '412721199807241454', '888888', '唐壮壮', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(393, '412721199707020312', '888888', '齐凌霄', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(394, '412721199903125031', '888888', '郎佳成', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(395, '412721199810065017', '888888', '潘万波', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(396, '412721199801013063', '888888', '赵阡莉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(397, '412721199807052235', '888888', '包靖阳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(398, '412721199502245462', '888888', '刘怡萌', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(399, '411621199808280045', '888888', '周俞含', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(400, '41272120001117222', '888888', '肖丽娟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(401, '412721199601083841', '888888', '庄婷茹', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(402, '412721199704033839', '888888', '孙彦伟', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(403, '412721199708113836', '888888', '卢瑞成', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(404, '412721199711042311', '888888', '王世兴', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(405, '412721199802233041', '888888', '霍施汝', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(406, '412721199807052223', '888888', '吕博佳', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(407, '412721199805213476', '888888', '张笑磊', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(408, '412721199611165824', '888888', '刘贝贝', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(409, '412721199806102612', '888888', '柴明辉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(410, '412721199806211413', '888888', '李亚行', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(411, '412721199612225411', '888888', '卢晨辉', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(412, '412721199705095150', '888888', '刘森', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(413, '412721199509032662', '888888', '路紫琦', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(414, '412721199507192646', '888888', '左曼迪', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(415, '412721199706233463', '888888', '韩敏杰', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(416, '412721199801071810', '888888', '张金鹏', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(417, '41272119980527501X', '888888', '关万亮', '男', 20, '123QQ.com', 0, '汉', NULL, '', 1),
	(420, '001', '888888', 'aaa', '男', 11, '11@111.com', 0, '汉', '2014-06-25', '阿道夫', 1),
	(421, '002', '888888', 'bbb', '男', 11, 'ccc@163.com', 0, '汉', '2014-06-27', '阿道夫', 1),
	(422, '004', '888888', 'zhangyu', '男', 19, 'aa@qq.com', 0, 'han', '2014-06-01', '', 1),
	(423, '003', '888888', 'zhang', '男', 18, '123@qq.com', 0, 'han', '2014-06-01', '', 1),
	(427, '005', '888888', 'zhang', '男', 16, '123@qq.com', 0, '汉', '2014-06-01', 'uu', 1),
	(428, '006', '888888', 's', '男', 16, '123@qq.com', 0, '汉', '2014-07-06', '', 1),
	(429, '007', '888888', 'w', '男', 16, '123@qq.com', 0, '汉', '2014-07-06', '', 1),
	(430, '008', '888888', 's', '男', 16, '123@qq.com', 0, '汉', '2014-07-02', '', 1),
	(431, '009', '888888', 'q', '男', 16, '123@qq.com', 0, '汉', '2014-07-01', '', 1),
	(432, '010', '888888', 'a', '男', 16, '123@qq.com', 0, '汉', '2014-07-07', '', 1),
	(433, 'bjcs1', '888888', '北京测试一', '男', 16, '123@qq.com', 0, '汉', NULL, '北京海淀区', 2),
	(434, 'bjcs2', '888888', '北京测试二', '男', 16, '123@qq.com', 0, '汉', NULL, '', 1),
	(435, 'bj1', '888888', 'bj1', '男', 16, '123@qq.com', 0, '汉', NULL, '', 5);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


-- 导出  表 smlearning_new.user_test_paper 结构
CREATE TABLE IF NOT EXISTS `user_test_paper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `time` varchar(100) DEFAULT NULL COMMENT '时间',
  `score` varchar(10) DEFAULT NULL COMMENT '分数',
  `is_correct` varchar(10) DEFAULT NULL,
  `test_paper_id` bigint(20) DEFAULT NULL,
  `no_select_answer` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.user_test_paper 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `user_test_paper` DISABLE KEYS */;
REPLACE INTO `user_test_paper` (`id`, `user_id`, `question_id`, `time`, `score`, `is_correct`, `test_paper_id`, `no_select_answer`) VALUES
	(215, 428, 182, NULL, '0.0', 'W', 25, NULL),
	(216, 428, 186, '/homework/25_2.jpg', '0.0', 'W', 25, NULL),
	(217, 429, 182, NULL, '0.0', 'W', 25, NULL),
	(218, 429, 186, '/homework/25_2.jpg', '0.0', 'W', 25, NULL);
/*!40000 ALTER TABLE `user_test_paper` ENABLE KEYS */;


-- 导出  表 smlearning_new.user_test_result 结构
CREATE TABLE IF NOT EXISTS `user_test_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `no_select_answer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smlearning_new.user_test_result 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_test_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_test_result` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
