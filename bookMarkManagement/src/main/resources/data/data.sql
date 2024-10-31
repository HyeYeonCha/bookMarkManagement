TRUNCATE users;

INSERT INTO users(id, email, user_name, password, created_datetime, updated_datetime)
VALUES (1,'test@test.com','testUserName', 'test1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

TRUNCATE bookmark_groups;

INSERT INTO bookmark_groups(id, user_id, group_name, created_datetime, updated_datetime)
VALUES (1, 1, 'testGroupName', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
