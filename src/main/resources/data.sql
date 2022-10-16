insert into user_details(id, birth_date, name) values(1001, current_date(), 'Anuj');
insert into user_details(id, birth_date, name) values(1002, current_date(), 'Amish');
insert into user_details(id, birth_date, name) values(1003, current_date(), 'Kaushik');

insert into post(id, description, user_id) values(2001, 'I want to learn aws', 1001);
insert into post(id, description, user_id) values(2004, 'I want to learn devops', 1001);
insert into post(id, description, user_id) values(2002, 'I want to learn devops', 1002);
insert into post(id, description, user_id) values(2003, 'I want to learn node', 1003);
insert into post(id, description, user_id) values(2005, 'I want to learn aws', 1003);
