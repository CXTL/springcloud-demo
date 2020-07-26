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





create table if not exists sys_user_account
(
    id          bigint auto_increment comment 'id' primary key,
    user_id     bigint     not null comment '用户ID',
    account_code     bigint     not null comment '帐套编号'
)
    comment '用户帐套关联表 ';



create table if not exists fin_account
(
    id          bigint auto_increment comment 'id'
        primary key,
    account_code        varchar(32)  not null comment '帐套信息编码',
    account_name        varchar(32)  not null comment '帐套名称',
    company_name          varchar(50)       not null comment '公司名称',
    tax_number        varchar(50)          not null comment '纳税识别号',
    address        varchar(100) not null comment '地址',
    phone        varchar(13) not null comment '电话',
    bank_account        varchar(50) not null comment '开户银行',
    bank_card_number      varchar(50)          not null comment '银行卡号',
    create_id   bigint       null comment '创建人',
    create_time bigint    null comment '创建时间',
    update_id   bigint       null comment '更新人',
    update_time bigint    null comment '更新时间',
    is_deleted  int   null comment '是否删除 0:未删除1:已删除',
    remark      varchar(128) null comment '备注'
)
    comment '财务-帐套信息表';

-- 资产=负载+所有权益  收入=费用+利润

create table if not exists fin_subject
(
    id          bigint auto_increment comment 'id'
        primary key,
    subject_code        varchar(32)  not null comment '科目编号',
    subject_name         varchar(32)       not null comment '科目名称',
    parent_code        varchar(32)          not null comment '父科目编号',
    subject_type        int not null comment '科目类型 1 资产 2负载 3权益 4成本 5其他',
    borrow_flag        int not null comment '借贷方向 0:借 1:贷',
    create_id   bigint       null comment '创建人',
    create_time bigint    null comment '创建时间',
    update_id   bigint       null comment '更新人',
    update_time bigint    null comment '更新时间',
    is_deleted  int   null comment '是否删除 0:未删除1:已删除',
    remark      varchar(128) null comment '备注'
)
    comment '财务-会计科目信息表';


create table if not exists fin_invest
(
    id          bigint auto_increment comment 'id'
        primary key,
    account_code        varchar(32)  not null comment '帐套信息编码',
    invest_name        varchar(32)  not null comment '投资人名称',
    invest_fund         decimal(11,2)       not null comment '投资款',
    invest_amount        decimal(11,2)          not null comment '投资总额',
    invest_ratio        int          not null comment '投资比例 %',
    create_id   bigint       null comment '创建人',
    create_time bigint    null comment '创建时间',
    update_id   bigint       null comment '更新人',
    update_time bigint    null comment '更新时间',
    is_deleted  int   null comment '是否删除 0:未删除1:已删除',
    remark      varchar(128) null comment '备注'
)
    comment '财务-投资信息表';


create table if not exists fin_balance
(
    id          bigint auto_increment comment 'id'
        primary key,
    account_code        varchar(32)  not null comment '帐套信息编码',
    total_balance        decimal(11,2)  not null comment '总余额',
    freeze_balance         decimal(11,2)       not null comment '冻结余额',
    available_balance        decimal(11,2)          not null comment '可用余额',
    status        int         not null comment '状态',
    create_id   bigint       null comment '创建人',
    create_time bigint    null comment '创建时间',
    update_id   bigint       null comment '更新人',
    update_time bigint    null comment '更新时间',
    is_deleted  int   null comment '是否删除 0:未删除1:已删除',
    remark      varchar(128) null comment '备注'
)
    comment '财务-余额信息表';



create table if not exists fin_balance_record
(
    id          bigint auto_increment comment 'id'
        primary key,
    account_code        varchar(32)  not null comment '帐套信息编码',
    subject_code        varchar(32)  not null comment '科目编码',
    receive_amount         decimal(11,2)       not null comment '应收金额',
    pay_amount         decimal(11,2)       not null comment '应付金额',
    real_receive_amount         decimal(11,2)       not null comment '实收金额',
    real_pay_amount         decimal(11,2)       not null comment '实付金额',
    amount         decimal(11,2)       not null comment '金额',
    balance_before         decimal(11,2)       not null comment '变动前余额',
    balance_after         decimal(11,2)      not null comment '变动后余额',
    type        int          not null comment '类型 1 收入 2 支出',
    create_id   bigint       null comment '创建人',
    create_time bigint    null comment '创建时间',
    update_id   bigint       null comment '更新人',
    update_time bigint    null comment '更新时间',
    is_deleted  int   null comment '是否删除 0:未删除1:已删除',
    remark      varchar(128) null comment '备注'
)
    comment '财务-余额记录信息表';
