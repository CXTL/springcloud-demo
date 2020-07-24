create table if not exists sys_menu
(
    id          bigint auto_increment comment 'id'
        primary key,
    name        varchar(32)  not null comment '名称',
    pid         bigint       not null comment '父ID',
    sort        int          not null comment '排序',
    icon        varchar(128) not null comment '图标',
    path        varchar(128) not null comment '路径',
    title        varchar(128) not null comment '标题',
    hidden      int          not null comment '是否隐藏 0:未隐藏1:已隐藏',
    permission   varchar(32)  not null comment '权限',
    type        int          not null comment '类型 0:菜单权限1:资源权限',
    create_id   bigint       null comment '创建人',
    create_time bigint    null comment '创建时间',
    update_id   bigint       null comment '更新人',
    update_time bigint    null comment '更新时间',
    is_deleted  varchar(1)   null comment '是否删除 0:未删除1:已删除',
    remark      varchar(128) null comment '备注'
)
    comment '菜单表 ';

create table if not exists sys_role
(
    id          bigint auto_increment comment 'id'
        primary key,
    name        varchar(32) not null comment '名称',
    is_enable       int         not null comment '是否启用 0:否 1:是',
    remark       varchar(32) null comment '备注',
    create_id   bigint      null comment '创建人',
    create_time bigint   null comment '创建时间',
    update_id   bigint      null comment '更新人',
    update_time bigint   null comment '更新时间',
    is_deleted  varchar(1)  null comment '是否删除 0:未删除1:已删除'
)
    comment '角色表';

create table if not exists sys_role_menu
(
    id          bigint auto_increment comment 'id'
        primary key,
    role_id     bigint     not null comment '角色ID',
    menu_id     bigint     not null comment '菜单ID'
)
    comment '角色菜单表 ';

create table if not exists sys_user
(
    id          bigint auto_increment comment 'id'
        primary key,
    nick_name   varchar(32) null comment '昵称',
    username    varchar(32) null comment '账号',
    password    varchar(128) null comment '密码',
    status      int         not null comment '状态 0:未激活1:已激活2:已锁定3:已注销4::账号异常',
    sex         int         null comment '性别',
    phone       varchar(32) null comment '手机号',
    head_url       varchar(100) null comment '用户头像',
    remark       varchar(200) null comment '备注',
    create_id   bigint      null comment '创建人',
    create_time bigint   null comment '创建时间',
    update_id   bigint      null comment '更新人',
    update_time bigint   null comment '更新时间',
    is_deleted  int         null comment '是否删除 0:未删除1:已删除',
    email       varchar(32) null
)
    comment '用户表';

create table if not exists sys_user_role
(
    id          bigint auto_increment comment 'id'
        primary key,
    user_id     bigint     not null comment '用户ID',
    role_id     bigint     not null comment '角色ID'
)
    comment '用户角色关联表 ';

