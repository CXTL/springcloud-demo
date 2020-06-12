-- 天枢 dml 脚本

-- truncate table dict;
-- truncate table dict_detail;
-- truncate table menu;
-- truncate table `role`;
-- truncate table `roles_menus`;
-- truncate table user;
-- truncate table `users_roles`;

-- 初始化默认用户
INSERT INTO user (id, email, enabled, last_password_reset_time, nick_name, password, phone, sex, username, remark, avatar_id) VALUES (1, 'admin@tom.com', true, '2019-05-18 17:34:21', 'admin', '$2a$10$VhAWNoUtpJKr000UYmfMee4SONBXJuRWGus64bmomyFKEo4kiwHve', '18888888888', '男', 'admin', '',  null);

-- 初始化默认角色
INSERT INTO `role`(`id`, `name`, `permission`) VALUES (1, '超级管理员', 'admin');

-- 初始化 admin 角色
INSERT INTO `users_roles`(`user_id`, `role_id`) VALUES (1, 1);

-- 初始化 一级菜单 1~20
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1, b'0', 'dashboard/dashboard', 'Home', b'1', b'0', 'dashboard', '概览', 'home', NULL, 0, 1, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (2, b'0', NULL, NULL, b'1', b'0', 'shujuguanli', '数据管理', 'preparation', NULL, 0, 2, 0);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (3, b'0', NULL, NULL, b'0', b'0', 'xunlianzhunbei', '模型开发', 'development', NULL, 0, 3, 0);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (4, b'0', NULL, NULL, b'0', b'0', 'xunlianguocheng', '训练管理', 'training', NULL, 0, 4, 0);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (5, b'0', 'model/index', 'Model', b'0', b'0', 'moxingguanli', '模型管理', 'model/model', NULL, 0, 5, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (6, b'0', 'service/index', 'Service', b'1', b'0', 'fuwuguanli', '服务管理', 'service/list', NULL, 0, 6, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (9, b'0', NULL, NULL, b'0', b'0', 'kongzhitaixitongguanliyuankejian', '控制台', 'system', NULL, 0, 9, 0);

-- 二级菜单 数据管理 21~29
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (21, b'0', 'dataset/dataset', 'Datasets', b'1', b'0', 'database', '数据集管理', 'data/datasets', 'dataset:list', 2, 1, 1);

-- 二级菜单 模型开发 31~39
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (31, b'0', 'development/index', 'Notebook', b'0', b'0', 'kaifahuanjing', 'Notebook', 'index', 'dev:list', 3, 1, 1);

-- 二级菜单 训练管理 41~49
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (41, b'0', 'training.algorithm/index', 'Algorithm', b'0', b'0', 'mobanguanli', '算法管理', 'algorithm', 'training:algorithm', 4, 1, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (42, b'0', 'training.job/detail', 'JobDetail', b'1', b'0', NULL, '任务详情', 'jobDetail', 'training:job', 4, 2, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (43, b'0', 'training.job/add', 'jobAdd', b'1', b'0', NULL, '添加任务', 'jobAdd', 'training:job', 4, 3, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (44, b'0', 'training.job/index', 'ModelJob', b'0', b'0', 'renwuguanli', '训练任务', 'job', 'training:job', 4, 4, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (45, b'0', 'training.param/index', 'ModelParam', b'0', b'0', 'mobanguanli', '运行参数管理', 'param', 'training:params', 4, 5, 1);


-- 二级菜单 模型管理 51~59

INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (51, b'0', 'model.optimize/optimize', 'ModelOptimize', b'0', b'0', 'caidanguanli', '模型优化', 'optimize', 'opt:list', '5', '2', '1');
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (52, b'0', 'model/index', 'ModelModel', b'0', b'0', 'zongshili', '模型列表', 'model', null, '5', '1', '1');
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (53, b'0', 'model/version', 'ModelVersion', '', b'0', null, '模型版本管理', 'version', null, '5', '999', '1');
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (54, b'0', 'model.optimize/record', 'ModelOptRecord', '', b'0', null, '模型优化执行记录', 'optimize/record', null, '5', '999', '1');
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (55, b'0', 'dataset/version', '版本管理', '', b'0', null, '数据集版本管理', '/preparation/data/datasets/:datasetId/version', null, '2', '999', '1');


-- 二级菜单 控制台 100 ~ 109
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (91, b'0', 'system/user/index', 'SystemUser', b'0', b'0', 'yonghuguanli', '用户管理', 'user', 'user:list', 9, 1, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (92, b'0', 'system/role/index', 'SystemRole', b'0', b'0', 'jiaoseguanli', '角色管理', 'role', 'role:list', 9, 2, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (93, b'0', 'system/menu/index', 'SystemMenu', b'0', b'0', 'caidanguanli', '菜单管理', 'menu', 'menu:list', 9, 3, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (94, b'0', 'system/dict/index', 'SystemDict', b'0', b'0', 'dictionary', '字典管理', 'dict', 'dict:list', 9, 4, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (95, b'0', 'system/node/index', 'SystemNode', b'0', b'0', 'jiqunguanli', '集群状态', 'node', 'node:list', 9, 5, 1);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (96, b'0', 'preparation.image/index', 'SystemImage', b'0', b'0', 'fuwuguanli', '镜像管理', 'image', 'image:list', 9, 6, 1);

-- 按钮不需要固定id，预留0~999配置菜单，从1000开始，
ALTER TABLE `menu` AUTO_INCREMENT = 1000;
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1001, b'0', NULL, NULL, b'0', b'0', NULL, '用户新增', '', 'user:add', 91, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1002, b'0', NULL, NULL, b'0', b'0', NULL, '用户编辑', '', 'user:edit', 91, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1003, b'0', NULL, NULL, b'0', b'0', NULL, '用户删除', '', 'user:del', 91, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1004, b'0', NULL, NULL, b'0', b'0', NULL, '角色创建', '', 'role:add', 92, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1005, b'0', NULL, NULL, b'0', b'0', NULL, '角色修改', '', 'role:edit', 92, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1006, b'0', NULL, NULL, b'0', b'0', NULL, '角色删除', '', 'role:del', 92, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1007, b'0', NULL, NULL, b'0', b'0', NULL, '菜单新增', '', 'menu:add', 93, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1008, b'0', NULL, NULL, b'0', b'0', NULL, '菜单编辑', '', 'menu:edit', 93, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1009, b'0', NULL, NULL, b'0', b'0', NULL, '菜单删除', '', 'menu:del', 93, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1010, b'0', NULL, NULL, b'0', b'0', NULL, '字典新增', '', 'dict:add', 94, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1011, b'0', NULL, NULL, b'0', b'0', NULL, '字典编辑', '', 'dict:edit', 94, 0, 2);
INSERT INTO `menu`(`id`, `cache`, `component`, `component_name`, `hidden`, `i_frame`, `icon`, `name`, `path`, `permission`, `pid`, `sort`, `type`) VALUES (1012, b'0', NULL, NULL, b'0', b'0', NULL, '字典删除', '', 'dict:del', 94, 0, 2);


-- 初始化 admin 菜单
INSERT INTO `roles_menus` (`role_id`, `menu_id`) SELECT 1, `id` FROM `menu`;


-- 初始化 字典
INSERT INTO dict (id , name ,remark ) VALUES (1,'user_status','用户状态' );
INSERT INTO dict (id , name ,remark ) VALUES (2,'team_status','团队状态');
INSERT INTO dict (id , name ,remark ) VALUES (10,'frame_type','框架名称');
INSERT INTO dict (id , name ,remark ) VALUES (11,'model_type','模型格式');
INSERT INTO dict (id , name ,remark ) VALUES (12,'model_class','模型分类');
INSERT INTO dict (id , name ,remark ) VALUES (14,'moedl_source','模型来源');
INSERT INTO dict (id , name ,remark ) VALUES (15,'opt_device_type','模型优化设备类型');
INSERT INTO dict (id , name ,remark ) VALUES (16,'task_status','模型优化任务状态');
INSERT INTO dict (id , name ,remark ) VALUES (17,'opt_model_type','模型优化教师模型分类');
INSERT INTO dict (id , name ,remark ) VALUES (19,'opt_result','模型优化结果');
INSERT INTO dict (id , name ,remark ) VALUES (25,'job_status','训练任务状态');

-- 初始化 字典详情

INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('激活','1','true','1');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('锁定','2','false','1');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('激活','1','true','2');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('锁定','2','false','2');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('OneFlow','1','1','10');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('TensorFlow','2','2','10');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('Pytroch','3','3','10');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('Keras','4','4','10');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('Caffe','5','5','10');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('Blade','6','6','10');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('SavedModel','1','1','11');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('ONNX','5','5','11');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('BladeModel','6','6','11');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('PMML','7','7','11');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('目标检测','1','1','12');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('目标分类','2','2','12');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('行为分析','3','3','12');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('异常检测','4','4','12');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('用户上传','1','0','14');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('训练生成','2','1','14');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('FrozenPb','2','2','11');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('KerasH5','3','3','11');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('CaffePrototxt','4','4','11');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('CPU','1','1','15');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('GPU','2','2','15');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('等待中','1','-1','16');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('进行中','2','0','16');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('已完成','3','1','16');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('已取消','4','2','16');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('执行失败','5','3','16');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('BERT','1','1','17');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('12层BERT','2','2','17');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('LSTM','3','3','17');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('ResNet','4','4','17');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('VggNet','5','5','17');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('accuracy','1','%','19');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('reasoningTime','2','s','19');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('modelSize','3','M','19');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('ARM','3','3','15');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('待处理','1','0','25');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('运行中','2','1','25');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('运行完成','3','2','25');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('失败','4','3','25');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('停止','5','4','25');
INSERT INTO `dict_detail`(label,sort,value,dict_id)VALUES('未知','6','5','25');

INSERT INTO `pt_train_job_specs`(`id`, `specs_name`, `specs_info`, `specs_type`) VALUES (1, '1核32GB', '{\"cpuNum\": 1, \"memNum\": 1000, \"workspaceRequest\": \"100Mi\"}', 0);
INSERT INTO `pt_train_job_specs`(`id`, `specs_name`, `specs_info`, `specs_type`) VALUES (2, '英伟达1核32GB', '{\"gpuNum\": 1, \"memNum\": 1000, \"workspaceRequest\": \"100Mi\"}', 1);


-- DML
INSERT INTO `harbor_project` (`id`, `project_name`, `create_resource`,  `create_user_id`,  `update_user_id`, `deleted`) VALUES (1, 'notebook', 0, NULL,  NULL, b'0');

-- model_opt
INSERT INTO `model_opt_algorithm` VALUES (1, 'NLP', 'DistilLSTM', 1);
INSERT INTO `model_opt_algorithm` VALUES (2, 'CV', 'TinyBert', 1);
INSERT INTO `model_opt_algorithm` VALUES (3, 'CV', 'Qbert', 1);

INSERT INTO `model_opt_built_in` VALUES (1, '12层BERT', NULL, '/model/bert', '2020-05-26 19:15:23', 0, '2020-05-26 19:15:30', 0, b'0');

INSERT INTO `data_label` (`id`, `name`, `color`)
VALUES ('1', '行人', '#ffbb96'),
       ('2', '自行车', '#fcffe6'),
       ('3', '汽车', '#f4ffb8'),
       ('4', '摩托车', '#254000'),
       ('5', '飞机', '#e6f7ff'),
       ('6', '巴士', '#bae7ff'),
       ('7', '火车', '#003a8c'),
       ('8', '货车', '#002766'),
       ('9', '轮船', '#d6e4ff'),
       ('10', '交通灯', '#d3adf7'),
       ('11', '消防栓', '#b37feb'),
       ('12', '停止标志', '#9254de'),
       ('13', '停车收费表', '#722ed1'),
       ('14', '长凳', '#531dab'),
       ('15', '鸟', '#f6ffed'),
       ('16', '猫', '#d9f7be'),
       ('17', '狗', '#b7eb8f'),
       ('18', '马', '#95de64'),
       ('19', '羊', '#73d13d'),
       ('20', '牛', '#52c41a'),
       ('21', '大象', '#389e0d'),
       ('22', '熊', '#237804'),
       ('23', '斑马', '#135200'),
       ('24', '长颈鹿', '#092b00'),
       ('25', '背包', '#ffadd2'),
       ('26', '雨伞', '#ff85c0'),
       ('27', '手提包', '#f759ab'),
       ('28', '领带', '#eb2f96'),
       ('29', '手提箱', '#c41d7f'),
       ('30', '飞盘', '#e6fffb'),
       ('31', '雪板', '#b5f5ec'),
       ('32', '滑雪板', '#87e8de'),
       ('33', '球', '#5cdbd3'),
       ('34', '风筝', '#36cfc9'),
       ('35', '棒球棒', '#13c2c2'),
       ('36', '棒球手套', '#08979c'),
       ('37', '滑板', '#006d75'),
       ('38', '冲浪板', '#00474f'),
       ('39', '网球拍', '#002329'),
       ('40', '瓶子', '#fffb8f'),
       ('41', '红酒杯', '#fff566'),
       ('42', '杯子', '#ffec3d'),
       ('43', '叉子', '#fadb14'),
       ('44', '刀', '#d4b106'),
       ('45', '勺子', '#ad8b00'),
       ('46', '碗', '#876800'),
       ('47', '香蕉', '#fff7e6'),
       ('48', '苹果', '#ffe7ba'),
       ('49', '三明治', '#ffd591'),
       ('50', '橙子', '#ffc069'),
       ('51', '西兰花', '#ffa940'),
       ('52', '胡萝卜', '#fa8c16'),
       ('53', '热狗', '#d46b08'),
       ('54', '披萨', '#ad4e00'),
       ('55', '甜甜圈', '#873800'),
       ('56', '蛋糕', '#612500'),
       ('57', '椅子', '#ffe58f'),
       ('58', '长椅', '#ffd666'),
       ('59', '盆栽', '#ffc53d'),
       ('60', '床', '#faad14'),
       ('61', '餐桌', '#d48806'),
       ('62', '厕所', '#ad6800'),
       ('63', '电视', '#91d5ff'),
       ('64', '笔记本电脑', '#69c0ff'),
       ('65', '鼠标', '#40a9ff'),
       ('66', '路由器', '#1890ff'),
       ('67', '键盘', '#096dd9'),
       ('68', '手机', '#0050b3'),
       ('69', '微波炉', '#adc6ff'),
       ('70', '烤箱', '#85a5ff'),
       ('71', '烤面包机', '#597ef7'),
       ('72', '水槽', '#2f54eb'),
       ('73', '冰箱', '#1d39c4'),
       ('74', '书籍', '#eaff8f'),
       ('75', '钟表', '#d3f261'),
       ('76', '花瓶', '#bae637'),
       ('77', '剪刀', '#a0d911'),
       ('78', '泰迪熊', '#7cb305'),
       ('79', '吹风机', '#5b8c00'),
       ('80', '牙刷', '#3f6600');
