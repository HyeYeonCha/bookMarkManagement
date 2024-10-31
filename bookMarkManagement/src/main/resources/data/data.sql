TRUNCATE users;

INSERT INTO users(id, email, user_name, password, created_datetime, updated_datetime)
VALUES (1,'test@test.com','testUserName', 'test1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

TRUNCATE bookmark_groups;

INSERT INTO bookmark_groups(id, user_id, group_name, created_datetime, updated_datetime)
VALUES (1, 1, 'testGroupName', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products(name, thumbnail, price, created_datetime, updated_datetime)
VALUES ('product_0', 'https://image.com/products/thumbnail/product_0.jpeg', 140500, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_1', 'https://image.com/products/thumbnail/product_1.jpeg', 18000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_2', 'https://image.com/products/thumbnail/product_2.jpeg', 68700, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_3', 'https://image.com/products/thumbnail/product_3.jpeg', 46000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_4', 'https://image.com/products/thumbnail/product_4.jpeg', 106600, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_5', 'https://image.com/products/thumbnail/product_5.jpeg', 29800, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_6', 'https://image.com/products/thumbnail/product_6.jpeg', 76900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_7', 'https://image.com/products/thumbnail/product_7.jpeg', 44500, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_8', 'https://image.com/products/thumbnail/product_8.jpeg', 103700, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('product_9', 'https://image.com/products/thumbnail/product_9.jpeg',92900,  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

TRUNCATE TABLE bookmarks;
INSERT INTO bookmarks(id, user_id, bookmark_group_id, product_id, created_datetime, updated_datetime)
VALUES (1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
