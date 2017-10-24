
DROP DATABASE IF EXISTS mldn;
CREATE DATABASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE member(
   mid                  varchar(50) not null,
   name                 varchar(30),
   password             varchar(32),
   locked               int,
   CONSTRAINT pk_mid PRIMARY KEY (mid)
) engine='innodb';
INSERT INTO member(mid,name,password,locked) VALUES ('admin','管理员','hello',0) ;
INSERT INTO member(mid,name,password,locked) VALUES ('mldn','普通人','java',0) ;
INSERT INTO member(mid,name,password,locked) VALUES ('mermaid','美人鱼','hello',1) ;