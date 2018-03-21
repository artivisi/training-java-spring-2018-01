insert into s_role (id, name, description)
values ('staff', 'Staff', 'Staff');

insert into s_role (id, name, description)
values ('manager', 'Manager', 'Manager');

insert into s_role (id, name, description)
values ('superuser', 'Super User', 'Super User');

insert into s_permission (id, name, label)
values ('view-product', 'VIEW_PRODUCT', 'View Product Data');

insert into s_permission (id, name, label)
values ('edit-product', 'EDIT_PRODUCT', 'Edit Product Data');

insert into s_role_permission (id_role, id_permission)
values ('staff', 'view-product');

insert into s_role_permission (id_role, id_permission)
values ('manager', 'view-product');

insert into s_role_permission (id_role, id_permission)
values ('manager', 'edit-product');

insert into s_user (id, username, email, id_role, active)
values ('u001', 'user001', 'user001@contoh.com', 'staff', true);

insert into s_user (id, username, email, id_role, active)
values ('u002', 'user002', 'user002@contoh.com', 'manager', true);

insert into s_user (id, username, email, id_role, active)
values ('u003', 'user003', 'user003@contoh.com', 'superuser', false);

insert into s_user_password (id, id_user, password)
values ('up001', 'u001', 'test001');

insert into s_user_password (id, id_user, password)
values ('up002', 'u002', 'test002');

insert into s_user_password (id, id_user, password)
values ('up003', 'u003', 'test003');