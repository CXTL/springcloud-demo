-- 天枢 ddl 脚本

create table if not exists data_dataset
(
    id                   bigint auto_increment
        primary key,
    name                 varchar(255)                           not null,
    remark               varchar(255)                           null,
    type                 varchar(255) default '0'               not null comment '类型 0: private 私有数据,  1:team  团队数据  2:public 公开数据',
    team_id              bigint                                 null,
    uri                  varchar(255) default ''                null comment '数据集存储位置',
    create_user_id       bigint                                 null,
    create_time          datetime     default CURRENT_TIMESTAMP not null,
    update_user_id       bigint                                 null,
    update_time          datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    deleted              bit          default b'0'              not null,
    data_type            tinyint      default 0                 not null comment '数据类型:0图片，1视频',
    annotate_type        tinyint      default 0                 not null comment '标注类型：0分类,1目标检测',
    status               tinyint      default 0                 not null comment '0:未标注，1:手动标注中，2:自动标注中，3:自动标注完成，4:标注完成，5:未采样，6:目标跟踪完成，7:采样中',
    current_version_name varchar(16)                            null comment '当前版本号',
    constraint idx_name_unique
        unique (name) comment '数据集名称唯一'
)
    comment '数据集管理' charset = utf8;

create table if not exists data_dataset_label
(
    id         bigint unsigned auto_increment
        primary key,
    dataset_id bigint unsigned not null,
    label_id   bigint unsigned not null,
    constraint dataset_id
        unique (dataset_id, label_id)
)
    charset = utf8;

create table if not exists data_dataset_version
(
    id             bigint(19) auto_increment comment '主键'
        primary key,
    dataset_id     bigint(19)       null comment '数据集ID',
    team_id        bigint(19)       null comment '团队ID',
    create_user_id bigint(19)       null comment '创建人',
    create_time    datetime         not null comment '创建时间',
    update_user_id bigint(19)       null comment '修改人',
    update_time    datetime         null comment '修改时间',
    deleted        bit default b'0' not null comment '数据集版本删除标记0正常，1已删除',
    version_name   varchar(8)       not null comment '版本号',
    version_note   varchar(50)      not null comment '版本说明',
    version_source varchar(32)      null comment '来源版本号',
    version_url    varchar(255)     null comment '版本信息存储url',
    constraint unique_version
        unique (dataset_id, version_name) comment '数据集版本号唯一'
)
    comment '数据集版本表 
创建人: 王伟 
创建时间: 2020-05-19' charset = utf8mb4;

create table if not exists data_dataset_version_file
(
    id                bigint auto_increment comment '主键'
        primary key,
    dataset_id        bigint                     null comment '数据集ID',
    version_name      varchar(8)                 null comment '数据集版本',
    file_id           bigint                     null comment '文件ID',
    status            tinyint(1)       default 2 not null comment '状态 0: 新增 1:删除 2:正常',
    annotation_status tinyint unsigned default 0 not null comment '状态:0-未标注，1-标注中，2-自动标注完成，3-已标注完成,4-目标追踪完成'
)
    comment '数据集版本文件关系表 
创建人: 王伟 
创建时间: 2020-05-19' charset = utf8mb4;

create table if not exists data_file
(
    id             bigint unsigned zerofill auto_increment comment 'ID'
        primary key,
    name           varchar(255)     default ''                not null comment '文件名',
    status         tinyint unsigned default 0                 not null comment '状态:0-未标注，1-标注中，2-自动标注完成，3-已标注完成,4-目标追踪完成',
    dataset_id     bigint                                     null comment '数据集id',
    url            varchar(255)     default ''                not null comment '资源访问路径',
    location       varchar(255)     default ''                not null comment '文件存储路径',
    create_user_id bigint                                     null comment '创建用户ID',
    create_time    datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    update_user_id bigint                                     null comment '更新用户ID',
    update_time    datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted        bit              default b'0'              not null comment '0正常，1已删除',
    md5            varchar(255)     default ''                not null comment '文件md5',
    file_type      tinyint          default 0                 null comment '文件类型  0-图片,1-视频',
    pid            bigint           default 0                 null comment '父文件id',
    frame_interval int              default 0                 not null comment '帧间隔',
    constraint name_uniq
        unique (name, dataset_id, deleted)
)
    comment '文件信息' charset = utf8;

create index dataset_upt_time
    on data_file (dataset_id, update_time);

create index deleted
    on data_file (deleted);

create index status
    on data_file (dataset_id, status, deleted);

create index uuid
    on data_file (url, deleted);

create table if not exists data_label
(
    id             bigint auto_increment
        primary key,
    name           varchar(255) default ''                not null,
    color          varchar(7)   default '#000000'         not null,
    create_user_id bigint                                 null,
    create_time    datetime     default CURRENT_TIMESTAMP not null,
    update_user_id bigint                                 null,
    update_time    datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    deleted        bit          default b'0'              not null
)
    comment '数据集标签' charset = utf8;

create index dataset
    on data_label (name, deleted);

create table if not exists data_task
(
    id             bigint unsigned zerofill auto_increment comment 'ID'
        primary key,
    total          int          default 0                 not null comment '任务需要处理的文件总数',
    status         tinyint      default 1                 not null comment '任务状态，创建即为进行中。1进行中，2已完成',
    finished       int          default 0                 not null comment '已完成的文件数',
    files          varchar(255) default ''                null comment '文件id数组',
    datasets       varchar(255) default ''                null comment '数据集id数组',
    create_user_id bigint                                 null comment '创建用户ID',
    create_time    datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted        bit          default b'0'              not null comment '0正常，1已删除',
    annotate_type  tinyint      default 0                 not null comment '标注类型：0分类,1目标检测',
    data_type      tinyint      default 0                 not null comment '数据类型:0图片，1视频',
    labels         varchar(255)                           not null comment '该自动标注任务使用的标签数组，json串形式'
)
    comment '标注任务信息' charset = utf8;

create index deleted
    on data_task (deleted);

create index ds_status
    on data_task (datasets, status);

create table if not exists dict
(
    id          bigint auto_increment
        primary key,
    name        varchar(255)                       not null,
    remark      varchar(255)                       null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    charset = utf8;

create table if not exists dict_detail
(
    id          bigint auto_increment
        primary key,
    label       varchar(255)                       not null,
    sort        varchar(255)                       null,
    value       varchar(255)                       not null,
    dict_id     bigint                             null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    charset = utf8;

create table if not exists harbor_project
(
    id              bigint unsigned auto_increment comment '主键ID'
        primary key,
    project_name    varchar(100)                        not null comment 'project名称',
    create_resource tinyint   default 0                 not null comment '0 - NOTEBOOK模型管理  1- ALGORITHM算法管理',
    create_time     timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id  bigint                              null comment '创建用户ID',
    update_time     timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    update_user_id  bigint                              null comment '更新用户ID',
    deleted         bit       default b'0'              null comment '0正常，1已删除'
)
    comment 'harbor project表 
Create by: Yang Qiwei 2020-05-26' charset = utf8;

create table if not exists log
(
    id               bigint auto_increment
        primary key,
    browser          varchar(255) null,
    create_time      datetime     null,
    description      varchar(255) null,
    exception_detail text         null,
    log_type         varchar(255) null,
    method           varchar(255) null,
    params           text         null,
    request_ip       varchar(255) null,
    time             bigint       null,
    username         varchar(255) null
)
    charset = utf8;

create table if not exists menu
(
    id             bigint auto_increment
        primary key,
    cache          bit      default b'0'              null,
    component      varchar(255)                       null,
    component_name varchar(255)                       null,
    hidden         bit      default b'0'              null,
    i_frame        bit                                null,
    icon           varchar(255)                       null,
    name           varchar(255)                       null,
    path           varchar(255)                       null,
    permission     varchar(255)                       null,
    pid            bigint                             not null,
    sort           bigint                             null,
    type           int                                null,
    create_time    datetime default CURRENT_TIMESTAMP null,
    update_time    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    charset = utf8;

create table if not exists model_opt_algorithm
(
    id        bigint auto_increment comment '主键'
        primary key,
    type      varchar(255)  not null comment '算法类别',
    algorithm varchar(255)  not null comment '算法名称',
    status    int default 0 not null comment '算法状态：0-未激活，1-激活'
)
    comment '模型优化任务算法配置关系表' charset = utf8;

create index status
    on model_opt_algorithm (status);

create index type
    on model_opt_algorithm (type);

create table if not exists model_opt_built_in
(
    id             bigint auto_increment comment '内置模型id'
        primary key,
    name           varchar(127)                        not null comment '模型名称',
    description    varchar(255)                        null comment '模型描述',
    input_dir      varchar(255)                        not null comment '存储目录',
    create_time    timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id bigint                              null comment '创建人',
    update_time    timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    update_user_id bigint                              null comment '修改人',
    deleted        bit       default b'0'              not null comment '删除标识：0-未删除，1-删除'
)
    charset = utf8;

create index create_time
    on model_opt_built_in (create_time);

create table if not exists model_opt_task
(
    id              bigint auto_increment comment '任务ID主键'
        primary key,
    name            varchar(100) collate utf8_bin       not null comment '任务名称',
    description     varchar(1024)                       null comment '任务描述',
    device_type     varchar(8)                          not null comment '设备类型',
    model_type      varchar(8)                          not null comment '模型类型',
    input_dir       varchar(300)                        not null comment '输入目录',
    output_dir      varchar(300)                        null comment '输出目录',
    algorithm_type  varchar(10)                         not null comment '优化算法分类',
    algorithm_value varchar(200)                        null comment '优化算法',
    create_time     timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id  bigint                              null comment '创建人',
    update_time     timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    update_user_id  bigint                              null comment '修改人',
    deleted         bit       default b'0'              not null comment '删除标志',
    model_name      varchar(255)                        null comment '模型名称',
    model_source    varchar(8)                          null comment '模型来源：1-内置，2-我的模型',
    dataset_name    varchar(255)                        null comment '数据集名称',
    dataset_path    varchar(255)                        null comment '数据集路径'
)
    comment '模型优化任务表' charset = utf8;

create index algorithm_type
    on model_opt_task (algorithm_type);

create index create_time
    on model_opt_task (create_time);

create index device_type
    on model_opt_task (device_type);

create index model_type
    on model_opt_task (model_type);

create index name
    on model_opt_task (name);

create table if not exists model_opt_task_instance
(
    id                bigint auto_increment comment '主键'
        primary key,
    task_id           bigint                               not null comment '任务Id',
    name              varchar(127)                         null comment '任务名称',
    device_type       varchar(8)                           not null comment '设备类型',
    model_type        varchar(8)                           not null comment '模型类型',
    input_dir         varchar(255)                         not null comment '输入目录',
    output_dir        varchar(255)                         null comment '输出目录',
    algorithm_type    varchar(8)                           not null comment '优化算法分类',
    algorithm_value   varchar(255)                         null comment '优化算法',
    start_time        datetime                             null comment '任务实例开始时间',
    end_time          datetime                             null comment '任务实例结束时间',
    task_process      varchar(8) default '0%'              null comment '进度',
    log_path          varchar(255)                         null comment '日志地址',
    status            varchar(8) default '-1'              not null comment '-1-等待中,\n0-进行中,\n1-已完成\n,2-已取消',
    create_time       timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id    bigint                               null comment '创建人',
    update_time       timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    update_user_id    bigint                               null comment '修改人',
    deleted           bit        default b'0'              not null comment '删除标识：0-未删除，1-删除',
    opt_result_before mediumtext                           null comment '模型优化前性能参数',
    opt_result_after  mediumtext                           null comment '模型优化后性能参数',
    dataset_path      varchar(255)                         null comment '数据集路径'
)
    comment '模型优化任务实例记录表' charset = utf8;

create index algorithm_type
    on model_opt_task_instance (algorithm_type);

create index device_type
    on model_opt_task_instance (device_type);

create index end_time
    on model_opt_task_instance (end_time);

create index model_type
    on model_opt_task_instance (model_type);

create index name
    on model_opt_task_instance (name);

create index start_time
    on model_opt_task_instance (start_time);

create index status
    on model_opt_task_instance (status);

create index task_id
    on model_opt_task_instance (task_id);

create table if not exists notebook
(
    id                     bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id                bigint                                 not null comment '所属用户ID',
    name                   varchar(100)                           not null comment 'notebook名称',
    description            varchar(255)                           null comment '描述',
    url                    varchar(255)                           null comment '访问 notebook 在 Jupyter 里所需的url',
    total_run_min          int          default 0                 not null comment '运行总时间(分钟)',
    cpu_num                tinyint      default 0                 not null comment 'CPU数量',
    gpu_num                tinyint      default 0                 not null comment 'GPU数量',
    mem_num                int          default 0                 not null comment '内存大小（G）',
    disk_mem_num           int          default 0                 not null comment '硬盘内存大小（G）',
    create_resource        tinyint      default 0                 not null comment '0 - notebook 创建 1- 其它系统创建',
    status                 tinyint      default 1                 not null comment '0运行，1停止, 2删除, 3启动中，4停止中，5删除中，6运行异常（暂未启用）',
    last_start_time        timestamp                              null comment '上次启动执行时间',
    last_operation_timeout bigint                                 null comment '上次操作对应超时时间点（20200603121212）',
    k8s_status_code        varchar(100)                           null comment 'k8s响应状态码',
    k8s_status_info        varchar(255)                           null comment 'k8s响应状态信息',
    k8s_namespace          varchar(255)                           not null comment 'k8s中namespace',
    k8s_resource_name      varchar(255)                           not null comment 'k8s中资源名称',
    k8s_image_name         varchar(255)                           not null comment 'k8s中jupyter的镜像名称',
    k8s_pvc_path           varchar(255)                           not null comment 'k8s中pvc存储路径',
    k8s_mount_path         varchar(255) default '/notebook'       not null comment 'k8s中容器路径',
    create_time            timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id         bigint                                 null comment '创建用户ID',
    update_time            timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    update_user_id         bigint                                 null comment '更新用户ID',
    deleted                bit          default b'0'              null comment '0正常，1已删除'
)
    comment 'notebook数据表 
Create by: Yang Qiwei 2020-04-24' charset = utf8;

create index k8s_namespace
    on notebook (k8s_namespace);

create index k8s_resource_name
    on notebook (k8s_resource_name);

create index last_operation_timeout
    on notebook (last_operation_timeout);

create index name
    on notebook (name);

create index status
    on notebook (status);

create index user_id
    on notebook (user_id);

create table if not exists notebook_model
(
    id           int auto_increment comment '主键ID'
        primary key,
    model_type   varchar(50)   not null comment '模板类型 CPU GPU',
    cpu_num      int           not null comment 'CPU数量',
    gpu_num      int           not null comment 'GPU数量',
    mem_num      int           not null comment '内存大小',
    spec         varchar(50)   null comment 'GPU规格',
    deleted      int default 0 null comment '0正常，1已删除',
    disk_mem_num int           null comment '硬盘内存大小'
)
    comment 'notebook模板
Create by: 杨屹  2020.06.01';

create table if not exists pt_dev_envs
(
    id             bigint auto_increment comment '主键'
        primary key,
    name           varchar(255)     not null comment '名称',
    remark         varchar(255)     null comment '描述',
    type           varchar(255)     not null comment '类型 ',
    cpu_num        int              not null comment 'CPU数量',
    gpu_num        int              not null comment 'GPU数量',
    mem_num        int              not null comment '内存大小单位M',
    pod_num        int              not null comment 'POD数量',
    status         varchar(255)     not null comment '状态 对应k8s的状态',
    dataset_id     bigint           null comment '数据集ID',
    image_id       bigint           null comment '镜像ID',
    storage_id     bigint           null comment '存储ID',
    duration       int              null comment '时长',
    start_time     datetime         null comment '开始时间',
    close_time     datetime         null comment '释放时间',
    create_time    datetime         null comment '创建时间',
    update_time    datetime         null comment '修改时间',
    create_user_id bigint           null comment '创建人ID',
    update_user_id bigint           null comment '修改人ID',
    team_id        bigint           null comment '团队ID',
    deleted        bit default b'0' null comment '0正常，1已删除'
)
    charset = utf8;

create table if not exists pt_image
(
    id             int(8) auto_increment comment '主键'
        primary key,
    image_name     varchar(64)          not null comment '镜像名称',
    image_url      varchar(255)         not null comment '镜像地址',
    image_tag      varchar(32)          not null comment '镜像版本',
    remark         varchar(255)         not null comment '镜像描述',
    create_user_id bigint               null comment '创建人',
    create_time    timestamp            null comment '创建时间',
    update_user_id bigint               null comment '更新人',
    update_time    timestamp            null comment '更新时间',
    deleted        tinyint(1) default 0 null comment '删除(0正常，1已删除)'
)
    comment '镜像表' charset = utf8mb4;

create table if not exists pt_job_param
(
    id             bigint auto_increment comment '主键id'
        primary key,
    train_job_id   bigint                  not null comment '训练作业jobId',
    algorithm_id   bigint                  not null comment '算法来源id',
    run_params     json                    null comment '运行参数(算法来源为我的算法时为调优参数，算法来源为预置算法时为运行参数)',
    param_f1       varchar(255) default '' null comment 'F1值',
    param_callback varchar(255) default '' null comment '召回率',
    param_precise  varchar(255) default '' null comment '精确率',
    param_accuracy varchar(255) default '' null comment '准确率',
    create_user_id bigint                  null comment '创建人',
    deleted        tinyint(1)   default 0  null comment '删除(0正常，1已删除)',
    create_time    timestamp               null comment '创建时间'
)
    comment 'job运行参数及结果表' charset = utf8mb4;

create table if not exists pt_model_branch
(
    id               bigint auto_increment comment '主键'
        primary key,
    parent_id        bigint                             null comment '父ID',
    version          int                                not null comment '版本号',
    url              varchar(255)                       not null comment '模型地址',
    model_path       varchar(255)                       not null comment '模型存储地址',
    model_source     tinyint(3)                         not null comment '模型来源（用户上传、平台生成、优化后导入）',
    create_user_id   bigint                             null comment '用户ID',
    team_id          bigint                             null comment '团队ID',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    deleted          bit      default b'0'              not null comment '0 正常，1 已删除',
    algorithm_id     bigint                             null comment '算法ID',
    algorithm_name   varchar(255)                       null comment '算法名称',
    algorithm_source tinyint(1)                         null comment '算法来源(1为我的算法，2为预置算法)'
)
    comment '分支管理' charset = utf8;

create table if not exists pt_model_info
(
    id                bigint auto_increment comment '主键'
        primary key,
    name              varchar(255)                       not null comment '模型名称',
    frame_type        tinyint                            not null comment '框架类型',
    model_format      tinyint                            not null comment '模型文件的格式（后缀名）',
    model_description varchar(255)                       not null comment '模型描述',
    model_type        tinyint                            not null comment '模型分类',
    url               varchar(255)                       null comment '模型地址',
    model_vesrion     int                                null comment '模型版本',
    create_user_id    bigint                             null comment '用户ID',
    team_id           bigint                             null comment '组ID',
    deleted           bit      default b'0'              not null comment '0 正常，1 已删除',
    create_time       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '模型管理' charset = utf8;

create table if not exists pt_project_template
(
    id             bigint auto_increment comment '主键'
        primary key,
    name           varchar(255)     not null comment '名称',
    remark         varchar(255)     null comment '描述',
    type           varchar(255)     not null comment '类型 ',
    dataset_id     bigint           null comment '数据集ID',
    image_id       bigint           null comment '镜像ID',
    code_url       varchar(255)     null comment '代码地址',
    cmd            varchar(255)     not null comment '命令行',
    create_time    datetime         not null comment '创建时间',
    update_time    datetime         null comment '修改时间',
    create_user_id bigint           null comment '创建人ID',
    update_user_id bigint           null comment '修改人ID',
    team_id        bigint           null comment '团队ID',
    deleted        bit default b'0' null comment '0正常，1已删除'
)
    charset = utf8;

create table if not exists pt_storage
(
    id             bigint auto_increment comment '主键'
        primary key,
    name           varchar(255)     not null comment '名称',
    type           varchar(255)     not null comment '类型 ',
    size           int              not null comment '存储大小，单位M',
    storage_class  varchar(255)     null comment '对应k8s pvc的 storageClass',
    create_time    datetime         not null comment '创建时间',
    create_user_id bigint           null comment '创建人ID',
    update_user_id bigint           null comment '修改人ID',
    update_time    datetime         null comment '修改时间',
    team_id        bigint           null comment '团队ID',
    deleted        bit default b'0' null comment '0正常，1已删除'
)
    charset = utf8;

create table if not exists pt_train
(
    id             bigint auto_increment comment '主键id'
        primary key,
    train_name     varchar(64)          not null comment '训练作业名',
    version_num    int(8)     default 1 not null comment '训练作业job有效版本数量',
    total_num      int(8)     default 1 not null comment '训练作业总版本数',
    deleted        tinyint(1) default 0 not null comment '删除(0正常，1已删除)',
    create_user_id bigint               null comment '创建人',
    create_time    timestamp            null comment '创建时间',
    train_key      varchar(32)          null
)
    comment '训练作业主表' charset = utf8mb4;

create index idx_user_id
    on pt_train (create_user_id);

create table if not exists pt_train_algorithm
(
    id                 bigint auto_increment comment '主键'
        primary key,
    algorithm_name     varchar(255)            not null comment '算法名称',
    description        varchar(255) default '' null comment '算法描述',
    algorithm_source   tinyint(1)              not null comment '算法来源(1为我的算法，2为预置算法)',
    code_dir           varchar(255) default '' null comment '代码目录',
    run_command        varchar(255) default '' null comment '运行命令',
    run_params         json                    null comment '运行参数',
    algorithm_usage    varchar(255) default '' null comment '算法用途',
    accuracy           varchar(255) default '' null comment '算法精度',
    p4_inference_speed int                     null comment 'P4推理速度（ms）',
    create_user_id     bigint                  null comment '创建人',
    create_time        timestamp               null comment '创建时间',
    update_user_id     bigint                  null comment '更新人',
    update_time        timestamp               null comment '更新时间',
    deleted            tinyint(1)   default 0  not null comment '删除(0正常，1已删除)',
    image_name         varchar(127)            null,
    is_train_out       tinyint(1)   default 1  null comment '是否输出训练结果:1是，0否',
    is_train_log       tinyint(1)   default 1  null comment '是否输出训练日志:1是，0否',
    is_visualized_log  tinyint(1)   default 0  null comment '是否输出可视化日志:1是，0否'
)
    comment '训练算法表' charset = utf8mb4;

create table if not exists pt_train_job
(
    id                   bigint auto_increment comment '主键id'
        primary key,
    train_id             bigint                                 not null comment '训练作业id',
    train_version        varchar(32)                            not null comment 'job版本',
    parent_train_version varchar(32)                            null comment 'job父版本',
    job_name             varchar(64)                            not null comment '任务名称',
    description          varchar(255) default ''                null comment '描述',
    runtime              varchar(32)  default ''                null comment '运行时长',
    out_path             varchar(128) default ''                null comment '训练输出位置',
    log_path             varchar(128) default ''                null comment '作业日志路径',
    resources_pool_type  tinyint(1)   default 0                 not null comment '类型(0为CPU，1为GPU)',
    resources_pool_specs varchar(128)                           null comment '规格',
    resources_pool_node  int(8)       default 1                 not null comment '节点个数',
    train_status         tinyint(1)   default 0                 not null comment '训练作业job状态, 0为待处理，1为运行中，2为运行完成，3为失败，4为停止，5为未知)',
    deleted              tinyint(1)   default 0                 null comment '删除(0正常，1已删除)',
    create_user_id       bigint                                 null comment '创建人',
    create_time          timestamp    default CURRENT_TIMESTAMP null comment '创建时间',
    visualized_log_path  varchar(128) default ''                null comment '可视化日志路径',
    data_source_name     varchar(127)                           null comment '数据集名称',
    data_source_path     varchar(127)                           null comment '数据集路径',
    constraint inx_tran_id_version
        unique (train_id, train_version)
)
    comment '训练作业job表' charset = utf8mb4;

create index inx_create_user_id
    on pt_train_job (create_user_id);

create table if not exists pt_train_job_specs
(
    id          int(6) auto_increment comment '主键id'
        primary key,
    specs_name  varchar(128) default ''                not null comment '规格名称',
    specs_info  json                                   not null comment '规格信息',
    specs_type  tinyint(1)   default 0                 not null comment '规格类型(0为CPU, 1为GPU)',
    create_time timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_time timestamp                              null comment '修改时间'
)
    comment '规格表' charset = utf8mb4;

create table if not exists pt_train_param
(
    id                   bigint auto_increment comment '主键id'
        primary key,
    param_name           varchar(128)            not null comment '任务参数名称',
    description          varchar(256) default '' null comment '描述',
    algorithm_id         bigint                  not null comment '算法id',
    out_path             varchar(128) default '' null comment '输出路径',
    run_params           json                    null comment '运行参数(算法来源为我的算法时为调优参数，算法来源为预置算法时为运行参数)',
    algorithm_source     tinyint(1)   default 1  not null comment '算法来源(1为我的算法，2为预置算法)',
    log_path             varchar(128) default '' null comment '日志输出路径',
    resources_pool_type  tinyint(1)   default 0  not null comment '类型(0为CPU，1为GPU)',
    resources_pool_specs varchar(128)            null comment '规格',
    resources_pool_node  int(8)       default 1  not null comment '节点个数',
    deleted              tinyint(1)   default 0  null comment '删除(0正常，1已删除)',
    create_user_id       bigint                  null comment '创建人',
    create_time          timestamp               null comment '创建时间',
    update_user_id       bigint                  null comment '更新人',
    update_time          timestamp               null comment '更新时间',
    data_source_name     varchar(127)            null comment '数据集名称',
    data_source_path     varchar(127)            null comment '数据集路径'
)
    comment '任务参数表' charset = utf8mb4;

create table if not exists role
(
    id          bigint auto_increment
        primary key,
    name        varchar(255)                       not null,
    permission  varchar(255)                       null,
    remark      varchar(255)                       null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    charset = utf8;

create table if not exists roles_menus
(
    role_id bigint not null,
    menu_id bigint not null,
    primary key (role_id, menu_id)
)
    charset = utf8;

create table if not exists service
(
    id             int auto_increment comment '主键'
        primary key,
    model_id       int          null comment '模型id',
    model_version  int          null comment '模板版本号',
    status         int          null comment '状态',
    config         text         null comment '配置信息',
    yaml_path      varchar(255) null comment 'yaml配置信息',
    create_user_id int          null comment '创建人',
    create_time    datetime     null comment '创建时间',
    update_user_id datetime     null comment '更新人',
    update_time    datetime     null comment '更新时间'
)
    comment '服务管理' charset = utf8;

create table if not exists service_monitor
(
    id          int auto_increment comment '主键'
        primary key,
    service_id  int      null comment '服务id',
    system_info text     null comment '占用系统信息',
    api_info    text     null comment '接口信息',
    create_time datetime null comment '创建时间'
)
    comment '服务监控信息' charset = utf8;

create table if not exists team
(
    id          bigint auto_increment
        primary key,
    create_time datetime     null,
    enabled     bit          not null,
    name        varchar(255) not null
)
    charset = utf8;

create table if not exists teams_users_roles
(
    id      bigint auto_increment
        primary key,
    role_id bigint null,
    team_id bigint null,
    user_id bigint null
)
    charset = utf8;

create table if not exists user
(
    id                       bigint auto_increment
        primary key,
    email                    varchar(255)                       null,
    enabled                  bit                                not null,
    last_password_reset_time datetime                           null,
    nick_name                varchar(255)                       null,
    password                 varchar(255)                       null,
    phone                    varchar(255)                       null,
    sex                      varchar(255)                       null,
    username                 varchar(255)                       null,
    remark                   varchar(255)                       null,
    create_time              datetime default CURRENT_TIMESTAMP null,
    update_time              datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    avatar_id                bigint                             null
)
    charset = utf8;

create table if not exists user_avatar
(
    id          bigint auto_increment
        primary key,
    path        varchar(255)                       null,
    real_name   varchar(255)                       null,
    size        varchar(255)                       null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    charset = utf8;

create table if not exists users_roles
(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    primary key (user_id, role_id)
)
    comment '用户角色关联' charset = utf8;

