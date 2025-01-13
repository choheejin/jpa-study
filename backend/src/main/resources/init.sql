-- user 테이블 생성
CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY,
    department VARCHAR(50),
    name VARCHAR(50),
    password VARCHAR(255),
    position VARCHAR(50),
    user_id VARCHAR(50)
);

-- conference_category 테이블 생성
CREATE TABLE IF NOT EXISTS conference_category (
    id INT PRIMARY KEY,
    name VARCHAR(50)
);

-- user 테이블 초기 데이터 삽입
INSERT INTO user (id, department, name, password, position, user_id)
VALUES
(1, 'SSAFY', '홍길동', '$2a$10$0sNlKs6TUMs4hTLydDpC4.LWFpzb4dY20ZYNEegPKHkeEMqvyk85S', '교육생', 'test-1');

-- conference_category 테이블 초기 데이터 삽입
INSERT INTO conference_category (id, name)
VALUES
(1, '업무'),
(2, '교육'),
(3, '기타');
