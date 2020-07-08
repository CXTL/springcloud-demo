-- 天枢 dml 脚本


INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (1, '测试权限', '1', 0, 1, '1', 'path', 0, 'test:list', 0, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '测试权限');
INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (2, '用户管理', '1', 0, 2, '2', 'user', 0, 'userMenage', 0, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '用户管理');
INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (3, '角色管理', '1', 0, 3, '2', 'user', 0, 'roleMenage', 0, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '角色管理');
INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (4, '菜单管理', '1', 0, 4, '2', 'user', 0, 'menuMenage', 0, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '菜单管理');
INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (5, '日志管理', '1', 0, 5, '2', 'user', 0, 'logMenage', 0, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '日志管理');
INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (6, '用户列表', '1', 2, 3, '2', 'user', 0, 'userMenage:list', 1, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '用户列表');
INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (7, '角色列表', '1', 3, 3, '2', 'user', 0, 'roleMenage:list', 1, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '角色列表');
INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (8, '菜单列表', '1', 4, 3, '2', 'user', 0, 'menuMenage:list', 1, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '菜单列表');
INSERT INTO sys_menu (id, name, title, pid, sort, icon, path, hidden, permission, type, create_id, create_time, update_id, update_time, is_deleted, remark) VALUES (9, '日志列表', '1', 5, 3, '2', 'user', 0, 'logMenage:list', 1, 1, '2020-02-03 16:50:37', 1, '2020-02-03 16:50:37', '0', '日志列表');



INSERT INTO sys_role (id, name, level, remark, create_id, create_time, update_id, update_time, is_deleted) VALUES (1, 'ROLE_ADMIN', 1, '1', 1, null, 1, null, '0');


INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (1, 1, 1, 1, '2020-02-03 16:50:37', '0');
INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (2, 1, 2, 1, '2020-02-03 16:50:37', '0');
INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (3, 1, 3, 1, '2020-02-03 16:50:37', '0');
INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (4, 1, 4, 1, '2020-02-03 16:50:37', '0');
INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (5, 1, 5, 1, '2020-02-03 16:50:37', '0');
INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (6, 1, 6, 1, '2020-02-03 16:50:37', '0');
INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (7, 1, 7, 1, '2020-02-03 16:50:37', '0');
INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (8, 1, 8, 1, '2020-02-03 16:50:37', '0');
INSERT INTO sys_role_menu (id, role_id, menu_id, update_id, update_time, is_deleted) VALUES (9, 1, 9, 1, '2020-02-03 16:50:37', '0');


INSERT INTO sys_user (id, nick_name, username, password, status, sex, phone, create_id, create_time, update_id, update_time, is_deleted, email) VALUES (1, 'admin', 'admin', 'wg2p+RW6HARvP1vPoklko7AcWGCCDABOwG9Z1p8w0VRKhWVlkCNdSVb8Kj9xHUp4XWZUt8BW1zIX3gYm8Ox3eg==', 1, 1, '13823379117', 1, null, 1, null, 0, 'hb_cxtl@163.com');


INSERT INTO sys_user_role (id, user_id, role_id, update_id, update_time, is_deleted) VALUES (1, 1, 1, 1, null, '0');