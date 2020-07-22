-- 热更脚本

-- ---------------------------------更新人: cxt  更新时间: 2020.06.12 ---------------------------------------------------
-- sys_user 表 新增 head_url 字段
alter table sys_user add column head_url varchar(100) comment '用户头像';
