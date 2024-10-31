-- 권한 부여
-- GRANT SELECT, INSERT, UPDATE, DELETE ON dev.* TO 'dev_admin'@'%';

-- users table (회원 정보)
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT PRIMARY KEY,
    email VARCHAR(256) NOT NULL,
    user_name VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    created_datetime DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_datetime DATETIME DEFAULT CURRENT_TIMESTAMP
    ) ENGINE=innodb;


CREATE TABLE IF NOT EXISTS bookmark_groups (
                                 id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    group_name VARCHAR(255) NOT NULL,
    created_datetime DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_datetime DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=innodb;


CREATE TABLE IF NOT EXISTS bookmarks (
                                 id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    bookmark_group_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    created_datetime DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_datetime DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=innodb;


CREATE TABLE IF NOT EXISTS products
(
    id        BIGINT PRIMARY KEY,
    name      VARCHAR(255) null,
    thumbnail VARCHAR(255) null,
    price     BIGINT null,
    created_datetime datetime null default CURRENT_TIMESTAMP,
    updated_datetime datetime null default CURRENT_TIMESTAMP
) ENGINE=innodb;
