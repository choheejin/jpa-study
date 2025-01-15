USE ssafy_web_db;

-- Create table for conference_category
CREATE TABLE conference_category (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- Create table for user
CREATE TABLE user (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    department VARCHAR(255),
    position VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create table for conference
CREATE TABLE conference (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    owner_id INTEGER NOT NULL,
    conference_category INTEGER NOT NULL,
    call_start_time DATETIME,
    call_end_time DATETIME,
    thumbnail_url VARCHAR(255),
    title VARCHAR(255) NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (owner_id) REFERENCES user (id),
    FOREIGN KEY (conference_category) REFERENCES conference_category (id)
);

-- Create table for user_conference
CREATE TABLE user_conference (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    conference_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (conference_id) REFERENCES conference (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

-- Create table for conference_history
CREATE TABLE conference_history (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    conference_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    action SMALLINT CHECK (action IN (1, 2, 3)), -- 1: CREATE, 2: JOIN, 3: EXIT
    inserted_time DATETIME NOT NULL,
    FOREIGN KEY (conference_id) REFERENCES conference (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

-- user 테이블 초기 데이터 삽입
INSERT INTO user (department, name, password, position, user_id)
VALUES
('SSAFY', '홍길동', '$2a$10$0sNlKs6TUMs4hTLydDpC4.LWFpzb4dY20ZYNEegPKHkeEMqvyk85S', '교육생', 'test-1');

-- conference_category 테이블 초기 데이터 삽입
INSERT INTO conference_category (id, name)
VALUES
(1, '업무'),
(2, '교육'),
(3, '기타');
