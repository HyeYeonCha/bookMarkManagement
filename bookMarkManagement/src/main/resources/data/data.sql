TRUNCATE users;

INSERT INTO users(id, email, password, created_datetime, updated_datetime)
VALUES (1,'test@test.com','test1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
