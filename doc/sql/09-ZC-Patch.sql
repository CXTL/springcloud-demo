-- 热更脚本

-- ---------------------------------更新人: cxt  更新时间: 2020.06.12 ---------------------------------------------------
-- user 表 新增 create_user_id  update_user_id deleted 字段
ALTER TABLE user ADD COLUMN create_user_id bigint(20) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE user ADD COLUMN update_user_id bigint(20) DEFAULT NULL COMMENT '修改人id';
ALTER TABLE user ADD COLUMN deleted bit(1) DEFAULT 0 COMMENT '删除标记 0正常，1已删除';

-- role 表 新增 create_user_id  update_user_id deleted 字段
ALTER TABLE role ADD COLUMN create_user_id bigint(20) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE role ADD COLUMN update_user_id bigint(20) DEFAULT NULL COMMENT '修改人id';
ALTER TABLE role ADD COLUMN deleted bit(1) DEFAULT 0 COMMENT '删除标记 0正常，1已删除';

-- menu 表 新增 create_user_id  update_user_id deleted 字段
ALTER TABLE menu ADD COLUMN create_user_id bigint(20) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE menu ADD COLUMN update_user_id bigint(20) DEFAULT NULL COMMENT '修改人id';
ALTER TABLE menu ADD COLUMN deleted bit(1) DEFAULT 0 COMMENT '删除标记 0正常，1已删除';

-- user_avatar 表 新增 create_user_id  update_user_id deleted 字段
ALTER TABLE user_avatar ADD COLUMN create_user_id bigint(20) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE user_avatar ADD COLUMN update_user_id bigint(20) DEFAULT NULL COMMENT '修改人id';
ALTER TABLE user_avatar ADD COLUMN deleted bit(1) DEFAULT 0 COMMENT '删除标记 0正常，1已删除';


