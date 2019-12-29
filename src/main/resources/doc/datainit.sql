
create table m_user(

id char(32) primary key,
loginname varchar(100) not null,
name varchar(100) not null,
kind int not null,
password char(32) not null,
createtime varchar(20) not null,
createuserid char(32),
phone varchar(11),
updatetime varchar(20),
updateuserid char(32),
pic char(32)
);
create table m_item(

id char(32) primary key,
title varchar(100) not null,
enTitle varchar(100) not null,
description varchar(100) not null,
kind int not null,
createtime varchar(20) not null,
createuserid char(32),
updatetime varchar(20),
updateuserid char(32),
urladdr varchar(500),
parentId char(32),
enableflag int ,
ordernum int,
code varchar(10)
);

create table m_itemcontent(

id char(32) primary key,
title varchar(100) not null,
createdate varchar(20),
publishdate varchar(20),
createId char(32),
publishId char(32),
picId char(32),
orgId char(32),
topflag int,
topCreateTime varchar(20),
data text,
kind int,
itemId char(32),
pubFlag char(1)
);
create table m_org(

id char(32) primary key,
name varchar(100) not null,
code varchar(100),
kind int,
parentId char(32)
);
create table m_att(

id char(32) primary key,
servername varchar(100) not null,
realname varchar(100),
path varchar(100),
createtime char(20),
deleteFlag CHAR(1),
ownId char(32)
);
alter table m_item add data text;
insert into m_item (id,title,entitle,kind,enableflag,ordernum,description,createtime,code)
values('00000000000000000000000000000000','root','root',0,1,0,'description','2019-12-03 00:43:00',"00");

create table m_sysinfo(

id char(32) primary key,
name varchar(100) not null,
syscode varchar(100) not null,
sysvalue varchar(100) not null,
description varchar(500),
canDel varchar(100)
);

INSERT INTO `m_sysinfo` VALUES ('4fe0d7977b7442a7b1100f0af0133d61', '系统进入默认导航栏', 'mrdhl', '01', '系统进入默认导航栏', '1');
INSERT INTO `m_sysinfo` VALUES ('8bc961fb056e4c2897b37103ff4adf51', '首页中展示导航栏对应类型的消息2', 'sydhl2', '0302', '首页中展示导航栏对应类型的消息2', '1');
INSERT INTO `m_sysinfo` VALUES ('bce3a8b4af9e47bab057ed49b1d02177', '首页中展示导航栏对应类型的消息3', 'sydhl3', '04', '首页中展示导航栏对应类型的消息3', '1');
INSERT INTO `m_sysinfo` VALUES ('e96e3445727b43b8b8edf28ffb19c926', '首页中展示导航栏对应类型的消息1', 'sydhl1', '0301', '首页中展示导航栏对应类型的消息1', '1');