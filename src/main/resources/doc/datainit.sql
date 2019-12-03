--用户
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
--菜单
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
ordernum int
);

--菜单内容
create table m_itemcontent(

id char(32) primary key,
title varchar(100) not null,
createdate varchar(20),
publishdate varchar(20),
createId char(32),
publishId char(32),
picId char(32),
orgId char(32),
topflag int
)

--组织
create table m_org(

id char(32) primary key,
name varchar(100) not null,
code varchar(100),
kind int,
parentId char(32)
)
--页面信息配置
create sysinfo(

  id char(32) primary key,
  name char(100) not null,
  stdcode char(100) not null,
  stdvalue  char(100) not null
)
drop table m_att;
create table m_att(

id char(32) primary key,
servername varchar(100) not null,
realname varchar(100),
path varchar(100),
createtime char(20),
deleteFlag CHAR(1)
)

insert into m_item (id,title,entitle,kind,parentid,enableflag,ordernum,description,createtime)
values('00000000000000000000000000000000','root','root',0,'00000000000000000000000000000000',1,0,'description','2019-12-03 00:43:00')