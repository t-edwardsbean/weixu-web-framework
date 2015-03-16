drop database example_db; 
create database example_db character set utf8 default character set utf8 collate utf8_general_ci default collate utf8_general_ci;

use example_db;

create table t_report
(
    reportId   bigint   comment "报表ID",
    reportName   varchar(255)   comment "报表名称",
    xAxis   varchar(255)   comment "x轴说明",
    yAxis   varchar(255)   comment "y轴说明",
    conditions   varchar(255)  null comment "条件分组, 只用于列表报表, 多个用;隔开",
    columnTags   varchar(255)   comment "列标签说明, 只用于多值报表, 多个用;隔开",
    type   tinyint   comment "报表类型:1-单值, 2-数值, 3-列表, 4-单行表格, 5-多行表格, 6-分组表格",
    countType   tinyint   comment "统计类型:1-日统计, 2-月统计",
    createTime   bigint   comment "创建时间",
    creater   bigint   comment "创建人",
    constraint t_report_pk primary key (reportId)
) engine=myisam comment "报表实体";

create table t_execute
(
    executeId   bigint   comment "报表ID" auto_increment,
    reportId   bigint   comment "报表名称",
    executeTime   bigint   comment "执行时间",
    result   longtext   comment "执行结果json",
    constraint t_execute_pk primary key (executeId)
) engine=myisam comment "执行实体";

create table t_user
(
    userId   bigint   comment "用户ID" auto_increment,
    username   varchar(255)   comment "帐号",
    password   varchar(255)   comment "密码",
    name   varchar(255)   comment "姓名",
    role   tinyint   comment "角色, 1-管理员, 2-普通用户",
    encryptKey   varchar(255)   comment "用户加密key",
    constraint t_user_pk primary key (userId)
) engine=myisam comment "用户实体";








insert into t_report values(1, '单值报表-日统计', '日期', '下载次数', null, null, 1, 1, 20141101000000, 1);
insert into t_execute values(1, 1, 20141105000000, '10');
insert into t_execute values(2, 1, 20141106000000, '13');
insert into t_execute values(3, 1, 20141107000000, '14');
insert into t_execute values(4, 1, 20141108000000, '15');
insert into t_execute values(5, 1, 20141109000000, '16');
insert into t_execute values(6, 1, 20141110000000, '17');
insert into t_execute values(7, 1, 20141111000000, '10');
insert into t_execute values(8, 1, 20141112000000, '9');
insert into t_execute values(9, 1, 20141113000000, '10');

insert into t_report values(2, '数值报表-日统计', '平台', '下载次数', null, null, 2, 1, 20141101000000, 1);
insert into t_execute values(11, 2, 20141105000000, '[{"k":"1", "v":"10"}, {"k":"4", "v":"5"}, {"k":"9", "v":"1"}]');
insert into t_execute values(12, 2, 20141106000000, '[{"k":"1", "v":"11"}, {"k":"4", "v":"9"}, {"k":"9", "v":"2"}]');
insert into t_execute values(13, 2, 20141107000000, '[{"k":"1", "v":"12"}, {"k":"4", "v":"15"}, {"k":"9", "v":"3"}]');
insert into t_execute values(14, 2, 20141108000000, '[{"k":"1", "v":"13"}, {"k":"4", "v":"25"}, {"k":"9", "v":"4"}]');
insert into t_execute values(15, 2, 20141109000000, '[{"k":"1", "v":"14"}, {"k":"4", "v":"15"}, {"k":"9", "v":"5"}]');
insert into t_execute values(16, 2, 20141110000000, '[{"k":"1", "v":"15"}, {"k":"4", "v":"9"}, {"k":"9", "v":"8"}]');
insert into t_execute values(17, 2, 20141111000000, '[{"k":"1", "v":"16"}, {"k":"4", "v":"5"}, {"k":"9", "v":"9"}]');
insert into t_execute values(18, 2, 20141112000000, '[{"k":"1", "v":"13"}, {"k":"4", "v":"8"}, {"k":"9", "v":"4"}]');
insert into t_execute values(19, 2, 20141113000000, '[{"k":"1", "v":"10"}, {"k":"4", "v":"5"}, {"k":"9", "v":"8"}]');

insert into t_report values(3, '列表报表-日统计', '日期', '下载次数', '项目;平台', null, 3, 1, 20141101000000, 1);
insert into t_execute values(21, 3, 20141105000000, '[{"v":"10", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"8", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"1", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"11", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(22, 3, 20141106000000, '[{"v":"11", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"9", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"2", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"12", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(23, 3, 20141107000000, '[{"v":"12", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"4", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"3", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"13", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(24, 3, 20141108000000, '[{"v":"13", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"6", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"4", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"14", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(25, 3, 20141109000000, '[{"v":"14", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"8", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"5", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"15", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(26, 3, 20141110000000, '[{"v":"15", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"8", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"6", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"16", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(27, 3, 20141111000000, '[{"v":"16", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"9", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"7", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"17", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(28, 3, 20141112000000, '[{"v":"17", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"7", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"8", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"18", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(29, 3, 20141113000000, '[{"v":"18", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"8", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"9", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"19", "c":[{"c":"2000"}, {"c":"4"}]}]');







insert into t_report values(4, '单值报表-月统计', '日期', '下载次数', null, null, 1, 2, 20141101000000, 1);
insert into t_execute values(31, 4, 20140101000000, '10');
insert into t_execute values(32, 4, 20140201000000, '13');
insert into t_execute values(33, 4, 20140301000000, '14');
insert into t_execute values(34, 4, 20140401000000, '15');
insert into t_execute values(35, 4, 20140501000000, '16');
insert into t_execute values(36, 4, 20140601000000, '17');
insert into t_execute values(37, 4, 20140701000000, '10');
insert into t_execute values(38, 4, 20140801000000, '9');
insert into t_execute values(39, 4, 20140901000000, '10');

insert into t_report values(5, '数值报表-月统计', '平台', '下载次数', null, null, 2, 2, 20141101000000, 1);
insert into t_execute values(41, 5, 20140101000000, '[{"k":"1", "v":"10"}, {"k":"4", "v":"5"}, {"k":"9", "v":"1"}]');
insert into t_execute values(42, 5, 20140201000000, '[{"k":"1", "v":"11"}, {"k":"4", "v":"9"}, {"k":"9", "v":"2"}]');
insert into t_execute values(43, 5, 20140301000000, '[{"k":"1", "v":"12"}, {"k":"4", "v":"15"}, {"k":"9", "v":"3"}]');
insert into t_execute values(44, 5, 20140401000000, '[{"k":"1", "v":"13"}, {"k":"4", "v":"25"}, {"k":"9", "v":"4"}]');
insert into t_execute values(45, 5, 20140501000000, '[{"k":"1", "v":"14"}, {"k":"4", "v":"15"}, {"k":"9", "v":"5"}]');
insert into t_execute values(46, 5, 20140601000000, '[{"k":"1", "v":"15"}, {"k":"4", "v":"9"}, {"k":"9", "v":"8"}]');
insert into t_execute values(47, 5, 20140701000000, '[{"k":"1", "v":"16"}, {"k":"4", "v":"5"}, {"k":"9", "v":"9"}]');
insert into t_execute values(48, 5, 20140801000000, '[{"k":"1", "v":"13"}, {"k":"4", "v":"8"}, {"k":"9", "v":"4"}]');
insert into t_execute values(49, 5, 20140901000000, '[{"k":"1", "v":"10"}, {"k":"4", "v":"5"}, {"k":"9", "v":"8"}]');

insert into t_report values(6, '列表报表-月统计', '日期', '下载次数', '项目;平台', null, 3, 2, 20141101000000, 1);
insert into t_execute values(51, 6, 20140101000000, '[{"v":"10", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"8", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"1", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"11", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(52, 6, 20140201000000, '[{"v":"11", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"9", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"2", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"12", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(53, 6, 20140301000000, '[{"v":"12", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"4", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"3", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"13", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(54, 6, 20140401000000, '[{"v":"13", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"6", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"4", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"14", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(55, 6, 20140501000000, '[{"v":"14", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"8", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"5", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"15", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(56, 6, 20140601000000, '[{"v":"15", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"8", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"6", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"16", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(57, 6, 20140701000000, '[{"v":"16", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"9", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"7", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"17", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(58, 6, 20140801000000, '[{"v":"17", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"7", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"8", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"18", "c":[{"c":"2000"}, {"c":"4"}]}]');
insert into t_execute values(59, 6, 20140901000000, '[{"v":"18", "c":[{"c":"1800"}, {"c":"1"}]}, {"v":"8", "c":[{"c":"1800"}, {"c":"4"}]}, {"v":"9", "c":[{"c":"2000"}, {"c":"1"}]}, {"v":"19", "c":[{"c":"2000"}, {"c":"4"}]}]');

insert into t_report values(7, '单行表格报表-日统计', '', '', null, '91助手下载量;market下载量;苹果助手下载量', 4, 1, 20141101000000, 1);
insert into t_execute values(61, 7, 20141105000000, '[10,21,11]');
insert into t_execute values(62, 7, 20141106000000, '[11,22,12]');
insert into t_execute values(63, 7, 20141107000000, '[12,23,13]');
insert into t_execute values(64, 7, 20141108000000, '[13,24,14]');
insert into t_execute values(65, 7, 20141109000000, '[14,25,15]');
insert into t_execute values(66, 7, 20141110000000, '[15,26,16]');
insert into t_execute values(67, 7, 20141111000000, '[16,27,17]');
insert into t_execute values(68, 7, 20141112000000, '[17,28,18]');
insert into t_execute values(69, 7, 20141113000000, '[18,29,19]');

insert into t_report values(8, '单行表格报表-月统计', '', '', null, '91助手下载量;market下载量;苹果助手下载量', 4, 2, 20141101000000, 1);
insert into t_execute values(71, 8, 20140101000000, '[10,21,11]');
insert into t_execute values(72, 8, 20140201000000, '[11,22,12]');
insert into t_execute values(73, 8, 20140301000000, '[12,23,13]');
insert into t_execute values(74, 8, 20140401000000, '[13,24,14]');
insert into t_execute values(75, 8, 20140501000000, '[14,25,15]');
insert into t_execute values(76, 8, 20140601000000, '[15,26,16]');
insert into t_execute values(77, 8, 20140701000000, '[16,27,17]');
insert into t_execute values(78, 8, 20140801000000, '[17,28,18]');
insert into t_execute values(79, 8, 20140901000000, '[18,29,19]');

insert into t_report values(9, '多行表格报表-日统计', '', '', null, '下载量;搜索量;转化率', 5, 1, 20141101000000, 1);
insert into t_execute values(91, 9, 20140101000000, '[[11,10,21],[22,20,21],[33,30,31]]');
insert into t_execute values(92, 9, 20140201000000, '[[11,10,21],[22,20,21],[33,30,31]]');
insert into t_execute values(93, 9, 20140301000000, '[[11,10,21],[22,20,21],[33,30,31]]');
insert into t_execute values(94, 9, 20140401000000, '[[11,10,21],[22,20,21],[33,30,31]]');
insert into t_execute values(95, 9, 20140501000000, '[[11,10,21],[22,20,21],[33,30,31]]');
insert into t_execute values(96, 9, 20140601000000, '[[11,10,21],[22,20,21],[33,30,31]]');
insert into t_execute values(97, 9, 20140701000000, '[[11,10,21],[22,20,21],[33,30,31]]');
insert into t_execute values(98, 9, 20140801000000, '[[11,10,21],[22,20,21],[33,30,31]]');
insert into t_execute values(99, 9, 20140901000000, '[[11,10,21],[22,20,21],[33,30,31]]');

insert into t_report values(10, '分组表格报表-日统计', '', '', null, '搜索词;搜索量;转化率', 6, 1, 20141101000000, 1);
insert into t_execute values(101, 10, 20141101000000, '[["QQ","10","21"],["MM","20","21"],["Taobao",30,31]]');
insert into t_execute values(102, 10, 20141102000000, '[["QQ","10","21"],["MM","20","21"],["Taobao",30,31]]');
insert into t_execute values(103, 10, 20141103000000, '[["QQ","10","21"],["MM","20","21"],["Taobao",30,31]]');
insert into t_execute values(104, 10, 20141104000000, '[["QQ","10","21"],["默默","20","21"],["Taobao",30,31]]');
insert into t_execute values(105, 10, 20141105000000, '[["QQ","10","21"],["MM","20","21"],["Taobao",30,31]]');
insert into t_execute values(106, 10, 20141106000000, '[["QQ","10","21"],["默默","20","21"],["Taobao",30,31]]');
insert into t_execute values(107, 10, 20141107000000, '[["QQ","10","21"],["MM","20","21"],["默默",30,31]]');
insert into t_execute values(108, 10, 20141108000000, '[["QQ","10","21"],["MM","20","21"],["Taobao",30,31]]');
insert into t_execute values(109, 10, 20141109000000, '[["QQ","10","21"],["MM","20","21"],["Taobao",30,31]]');

insert into t_user values(1, '546806', '123456', '张伟旭', 1, 'bigdata-sean');
insert into t_user values(2, '130519', '123456', '张海', 2, 'bigdata-sea');