-- 创建用户表
CREATE TABLE if not exists users (
                       id INT AUTO_INCREMENT PRIMARY KEY,  -- 用户ID，自增长，主键
                       username VARCHAR(50) NOT NULL Unique ,   -- 用户名，最大长度50字符，不为空且唯一
                       password VARCHAR(255) NOT NULL          -- 密码，最大长度255字符，不为空
#                        email VARCHAR(100) NOT NULL             -- 电子邮件，最大长度100字符，不为空
);

# -- 创建帖子表
# CREATE TABLE posts (
#                        post_id INT AUTO_INCREMENT PRIMARY KEY,  -- 帖子ID，自增长，主键
#                        user_id INT NOT NULL,                   -- 发帖用户ID，不为空
#                        post_title VARCHAR(255) NOT NULL,       -- 帖子标题，最大长度255字符，不为空
#                        post_content TEXT NOT NULL,             -- 帖子内容，文本类型，不为空
#                        post_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 发帖时间，默认为当前时间戳
#                        FOREIGN KEY (user_id) REFERENCES users(user_id)  -- 外键约束，关联到用户表的user_id字段
# );

# -- 创建回帖表
# CREATE TABLE comments (
#                           comment_id INT AUTO_INCREMENT PRIMARY KEY,  -- 回帖ID，自增长，主键
#                           post_id INT NOT NULL,                     -- 所属帖子ID，不为空
#                           user_id INT NOT NULL,                     -- 回帖用户ID，不为空
#                           comment_text TEXT NOT NULL,               -- 回帖内容，文本类型，不为空
#                           comment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 回帖时间，默认为当前时间戳
#                           FOREIGN KEY (post_id) REFERENCES posts(post_id),  -- 外键约束，关联到帖子表的post_id字段
#                           FOREIGN KEY (user_id) REFERENCES users(user_id)   -- 外键约束，关联到用户表的user_id字段
# );

-- 类别表
CREATE TABLE if not exists emoji_categories (
                                                category_id INT AUTO_INCREMENT PRIMARY KEY,  -- 分类ID，自增长，主键
                                                category_name VARCHAR(100) NOT NULL UNIQUE  -- 分类名称，最大长度100字符，不为空且唯一
);

-- 创建表情包表
CREATE TABLE if not exists emojis (
                        emoji_id INT AUTO_INCREMENT PRIMARY KEY,  -- 表情包ID，自增长，主键
                        emoji_name VARCHAR(100) NOT NULL,        -- 表情包名称，最大长度100字符，不为空
                        emoji_url VARCHAR(255) NOT NULL,         -- 表情包URL，最大长度255字符，不为空
                        emoji_category_id INT NOT NULL ,        -- 表情包类别
                        upload_user_id INT NOT NULL,             -- 上传用户ID，不为空
                        upload_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 上传时间，默认为当前时间戳
                        FOREIGN KEY (emoji_category_id) REFERENCES emoji_categories(category_id),  -- 外键约束，关联到类别表的category_id字段
                        FOREIGN KEY (upload_user_id) REFERENCES users(id)   -- 外键约束，关联到用户表的user_id字段
);



-- 创建点赞表
# CREATE TABLE emoji_likes (
#                              emoji_id INT,                            -- 表情包ID
#                              user_id INT,                             -- 用户ID
#                              like_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 点赞时间，默认为当前时间戳
#                              PRIMARY KEY (emoji_id, user_id),         -- 复合主键，确保每个用户对每个表情包只能点赞一次
#                              FOREIGN KEY (emoji_id) REFERENCES emojis(emoji_id),  -- 外键约束，关联到表情包表的emoji_id字段
#                              FOREIGN KEY (user_id) REFERENCES users(user_id)       -- 外键约束，关联到用户表的user_id字段
# );

# -- 创建表情包评论表
# CREATE TABLE emoji_comments (
#                                 comment_id INT AUTO_INCREMENT PRIMARY KEY,  -- 评论ID，自增长，主键
#                                 emoji_id INT NOT NULL,                     -- 表情包ID，不为空
#                                 user_id INT NOT NULL,                      -- 用户ID，不为空
#                                 comment_text TEXT NOT NULL,                -- 评论内容，文本类型，不为空
#                                 comment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 评论时间，默认当前时间戳
#                                 FOREIGN KEY (emoji_id) REFERENCES emojis(emoji_id),  -- 外键约束，关联到表情包表的emoji_id字段
#                                 FOREIGN KEY (user_id) REFERENCES users(user_id)      -- 外键约束，关联到用户表的user_id字段
# );

# -- 创建管理员表
# CREATE TABLE admins (
#                         admin_id INT AUTO_INCREMENT PRIMARY KEY,  -- 管理员ID，自增长，主键
#                         username VARCHAR(50) NOT NULL UNIQUE,    -- 管理员用户名，最大长度50字符，不为空且唯一
#                         password VARCHAR(255) NOT NULL,          -- 管理员密码，最大长度255字符，不为空
#                         email VARCHAR(100) NOT NULL UNIQUE       -- 管理员邮箱，最大长度100字符，不为空且唯一
# );
#
# -- 创建管理员管理权限表
# CREATE TABLE admin_permissions (
#                                    admin_id INT NOT NULL,                    -- 管理员ID，不为空
#                                    can_manage_users BOOL DEFAULT FALSE,       -- 是否具有管理用户权限，默认为false
#                                    can_manage_emojis BOOL DEFAULT FALSE,      -- 是否具有管理表情包权限，默认为false
#                                    can_manage_comments BOOL DEFAULT FALSE,    -- 是否具有管理回帖权限，默认为false
#                                    can_manage_posts BOOL DEFAULT FALSE,       -- 是否具有管理帖子权限，默认为false
#                                    can_manage_emoji_comments BOOL DEFAULT FALSE,       -- 是否具有管理表情包评论权限，默认为false
#                                    PRIMARY KEY (admin_id),                   -- 主键，确保每个管理员只有一行记录
#                                    FOREIGN KEY (admin_id) REFERENCES admins(admin_id)  -- 外键约束，关联到管理员表的admin_id字段
# );