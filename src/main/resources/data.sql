INSERT INTO employee (name,address,email) VALUES ('Jack','London','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 1','Paris','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 2','weee','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 3','qwe','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 4','ert','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 5','qwas','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 6','dsazxc','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 7','xxxx','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 8','zzz','test@example.com');
INSERT INTO employee (name,address,email) VALUES ('Jack 9','cccc','test@example.com');

INSERT INTO task (title,description,employee_id) VALUES ('Do the dishes','weee',1);
INSERT INTO task (title,description,employee_id) VALUES ('Do 2','weee',1);
INSERT INTO task (title,description,employee_id) VALUES ('Do 3','weee',1);
INSERT INTO task (title,description,employee_id) VALUES ('Do 4','weee',1);
INSERT INTO task (title,description,employee_id) VALUES ('Do 5','weee',1);

INSERT INTO task (title,description,employee_id) VALUES ('Do 6','weee',2);
INSERT INTO task (title,description,employee_id) VALUES ('Do 7','weee',3);
INSERT INTO task (title,description,employee_id) VALUES ('Do 8','weee',4);
INSERT INTO task (title,description,employee_id) VALUES ('Do 9','weee',5);
INSERT INTO task (title,description,employee_id) VALUES ('Do 10','weee',6);

INSERT INTO employee_assists_in_task VALUES (1,6);
INSERT INTO employee_assists_in_task VALUES (1,7);
INSERT INTO employee_assists_in_task VALUES (1,8);

INSERT INTO employee_assists_in_task VALUES (2,1);
INSERT INTO employee_assists_in_task VALUES (3,1);
INSERT INTO employee_assists_in_task VALUES (4,1);