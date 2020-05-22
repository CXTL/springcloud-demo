create table persistent_logins
(
    username  varchar(64)                         not null,
    series    varchar(64)                         not null
        primary key,
    token     varchar(64)                         not null,
    last_used timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    charset = utf8;

create table sys_role
(
    id   int          not null
        primary key,
    name varchar(255) not null
)
    charset = utf8;

create table sys_user
(
    id       int auto_increment
        primary key,
    name     varchar(255) not null,
    password varchar(255) not null
)
    charset = utf8;

create table sys_user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint fk_role_id
        foreign key (role_id) references sys_role (id)
            on update cascade on delete cascade,
    constraint fk_user_id
        foreign key (user_id) references sys_user (id)
            on update cascade on delete cascade
)
    charset = utf8;

create table t_menu
(
    id          bigint auto_increment
        primary key,
    name        varchar(50)      null comment '菜单名称',
    path        varchar(100)     null comment '链接地址',
    pid         bigint           not null comment '上级菜单ID',
    sort        bigint           not null comment '排序',
    hidden      bit default b'0' null comment '是否隐藏',
    is_deleted  int              null comment '是否删除 0：否 1：是',
    permission  varchar(255)     null comment '权限',
    type        int              null comment '类型',
    create_time bigint           null comment '创建时间',
    update_time bigint           null comment '修改时间'
)
    comment '菜单表' collate = utf8mb4_general_ci;

create table t_order
(
    id             bigint auto_increment
        primary key,
    order_no       varchar(50)   null comment '订单编号',
    name           varchar(50)   null comment '名称',
    create_time    bigint        null comment '创建时间',
    update_time    bigint        null comment '修改时间',
    status         int           null comment '订单状态',
    amount         decimal(9, 2) null comment '金额',
    commodity_code varchar(30)   null comment '商品编号'
)
    collate = utf8mb4_general_ci;

create table t_points
(
    id          bigint auto_increment comment '主键'
        primary key,
    user_id     bigint        null comment '用户id',
    order_no    varchar(32)   null comment '订单编号',
    points      decimal(9, 2) null comment '积分',
    remarks     varchar(128)  null comment '备注',
    create_time bigint        null comment '创建时间',
    update_time bigint        null comment '修改时间'
);

create table t_role
(
    id          bigint auto_increment
        primary key,
    name        varchar(50) null comment '角色名称',
    is_deleted  int         null comment '是否删除 0：否 1：是',
    create_time bigint      null comment '创建时间',
    update_time bigint      null comment '修改时间'
)
    comment '角色表' collate = utf8mb4_general_ci;

create table t_role_menu
(
    menu_id bigint not null comment '菜单ID',
    role_id bigint not null comment '角色ID'
)
    comment '角色菜单表' collate = utf8mb4_general_ci;

create table t_transaction_log
(
    id          varchar(32) not null comment '事务ID'
        primary key,
    business    varchar(32) not null comment '业务标识',
    foreign_key varchar(32) not null comment '对应业务表中的主键',
    create_time bigint      null comment '创建时间',
    update_time bigint      null comment '修改时间'
);

create table t_user
(
    id          bigint auto_increment
        primary key,
    username    varchar(50) null comment '账户',
    password    varchar(50) null comment '密码',
    nick_name   varchar(50) null comment '昵称',
    phone       varchar(50) null comment '手机号码',
    email       varchar(50) null comment '邮箱',
    is_deleted  int         null comment '是否删除 0：否 1：是',
    sex         int         null comment '性别 0：男 1：女',
    enabled     int         null comment '状态：1启用、0禁用',
    create_time bigint      null comment '创建时间',
    update_time bigint      null comment '修改时间'
)
    comment '用户表' collate = utf8mb4_general_ci;

create table t_user_role
(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID'
)
    comment '用户角色表' collate = utf8mb4_general_ci;


CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_roleId` (`role_id`),
  CONSTRAINT `fk_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;