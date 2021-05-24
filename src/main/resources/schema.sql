-- DROP TABLE IF EXISTS PARENT_CHILD_COMMENT;
-- DROP TABLE IF EXISTS COMMENTS;
-- DROP TABLE IF EXISTS COMMENT;
-- DROP TABLE IF EXISTS BLOG;
-- DROP TABLE IF EXISTS USERS;

CREATE TABLE IF NOT EXISTS USERS(
    email VARCHAR(50) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    full_name VARCHAR(250) NOT NULL
);  

CREATE TABLE IF NOT EXISTS BLOG(
    post_id VARCHAR(10) PRIMARY KEY,
    content LONGTEXT NOT NULL,
    author VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    last_modified TIMESTAMP NOT NULL,
    FOREIGN KEY (author) REFERENCES USERS(email) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS COMMENTS(
    comment_id VARCHAR(10),
    post_id VARCHAR(10) NOT NULL,
    parent_id VARCHAR(10) NOT NULL,
    content LONGTEXT NOT NULL,
    author VARCHAR(50) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    last_modified TIMESTAMP NOT NULL,
    
    FOREIGN KEY(author) REFERENCES USERS(email) ON DELETE CASCADE,
    FOREIGN KEY(post_id) REFERENCES BLOG(post_id) ON DELETE CASCADE,
    PRIMARY KEY(comment_id,parent_id,post_id)
);