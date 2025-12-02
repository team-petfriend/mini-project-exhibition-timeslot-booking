# DROP DATABASE IF EXISTS `mini-exhibition-db`;
CREATE DATABASE IF NOT EXISTS `mini-exhibition-db`
  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `mini-exhibition-db`;

DROP TABLE IF EXISTS review_files;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS timeslots;
DROP TABLE IF EXISTS exhibitions;
DROP TABLE IF EXISTS venues;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS refresh_tokens;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS file_infos;
DROP TABLE IF EXISTS exhibition_file_infos;

SELECT * FROM venues;
SELECT * FROM exhibitions;

SELECT * FROM users;


CREATE TABLE file_infos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    
    original_name VARCHAR(255) NOT NULL,
    stored_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(255),
    file_size BIGINT,
    file_path VARCHAR(255) NOT NULL,
    
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
    
) ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 공통(유저/권한)
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  login_id VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  
  profile_file_id BIGINT NULL,
  
  provider varchar(20) not null,
  provider_id varchar(100),
  email_verified boolean not null,
  
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  UNIQUE KEY uk_login (login_id),
  -- UNIQUE KEY uk_email (email),
  CONSTRAINT `uk_users_provider_provider_id` UNIQUE(provider, provider_id),
  CONSTRAINT `chk_users_provider` CHECK(provider IN ('LOCAL', 'GOOGLE', 'KAKAO', 'NAVER')),
  CONSTRAINT `fk_users_profile_file` FOREIGN KEY (profile_file_id) REFERENCES file_infos(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE roles (
  role_name VARCHAR(30) PRIMARY KEY,
  CONSTRAINT `chk_roles_role_name` CHECK(role_name IN ('USER', 'ADMIN', 'STAFF'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE user_roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  role_name VARCHAR(30) NOT NULL,
  UNIQUE KEY uk_user_roles_user_id_role_name (user_id, role_name),
  INDEX idx_user_roles_user_id (user_id),
  INDEX idx_user_roles_role_name (role_name),
  CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_user_role_role_name FOREIGN KEY (role_name) REFERENCES roles(role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE refresh_tokens (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    token VARCHAR(350) NOT NULL,
    expiry DATETIME(6) NOT NULL,
    
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    
    INDEX `idx_refresh_token_user_id` (user_id),
    CONSTRAINT `fk_refresh_token_user` FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 전시장/전시
CREATE TABLE venues(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  file_id BIGINT NULL,
  
  name VARCHAR(120) NOT NULL,
  address VARCHAR(255) NULL,
  latitude DECIMAL(11,8) NULL,
  longitude DECIMAL(12,8) NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT `fk_venues_file` FOREIGN KEY (file_id) REFERENCES file_infos(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE exhibitions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  venue_id BIGINT NOT NULL,
  title VARCHAR(200) NOT NULL,
  description TEXT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED', -- SCHEDULED/OPEN/CLOSED/CANCELED
  capacity_policy VARCHAR(20) NOT NULL DEFAULT 'PER_SLOT', -- PER_DAY, PER_SLOT
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT fk_exhibition_venue FOREIGN KEY (venue_id) REFERENCES venues(id),
  CHECK (status IN ('SCHEDULED','OPEN','CLOSED','CANCELED')),
  CHECK (capacity_policy IN ('PER_DAY','PER_SLOT')),
  INDEX idx_exhibitions_venue (venue_id, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE exhibition_file_infos (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	exhibition_id BIGINT NOT NULL,
	file_id BIGINT NOT NULL,
	display_order INT DEFAULT 0,
	CONSTRAINT fk_exhibition_files_exhibition FOREIGN KEY (exhibition_id) REFERENCES exhibitions(id) ON DELETE CASCADE,
	CONSTRAINT fk_exhibition_files_file_info FOREIGN KEY (file_id) REFERENCES file_infos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 타임슬롯
CREATE TABLE timeslots (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  exhibition_id BIGINT NOT NULL,
  start_time DATETIME(6) NOT NULL,
  end_time DATETIME(6) NOT NULL,
  capacity INT NOT NULL,
  reserved INT NOT NULL DEFAULT 0,
  status VARCHAR(20) NOT NULL DEFAULT 'OPEN', -- OPEN/CLOSED/CANCELED
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT fk_timeslot_exhibition FOREIGN KEY (exhibition_id) REFERENCES exhibitions(id),
  CHECK (status IN ('OPEN','CLOSED','CANCELED')),
  CHECK (reserved >= 0 AND reserved <= capacity),
  UNIQUE KEY uk_slot_unique (exhibition_id, start_time, end_time),
  INDEX idx_slot_time (start_time, end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 예매/티켓/결제
CREATE TABLE bookings (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  timeslot_id BIGINT NOT NULL,
  qty INT NOT NULL CHECK (qty > 0),
  amount INT NOT NULL CHECK (amount>0),
  status VARCHAR(20) NOT NULL DEFAULT 'PENDING', -- PENDING/CONFIRMED/CANCELED/REFUNDED
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT fk_booking_user_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_booking_timeslot_id FOREIGN KEY (timeslot_id) REFERENCES timeslots(id),
  CHECK (status IN ('PENDING','CONFIRMED','CANCELED','REFUNDED')),
  INDEX idx_booking_user (user_id, status),
  INDEX idx_booking_timeslot (timeslot_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE tickets (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  booking_id BIGINT NOT NULL,
  code VARCHAR(40) NOT NULL,                 -- QR/바코드 값
  status VARCHAR(20) NOT NULL DEFAULT 'ISSUED', -- ISSUED/USED/VOID
  issued_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  used_at DATETIME(6) NULL,
  CONSTRAINT fk_ticket_booking_id FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE,
  UNIQUE KEY uk_ticket_code (code),
  INDEX idx_ticket_booking (booking_id, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE payments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  booking_id BIGINT NOT NULL,
  amount INT NOT NULL CHECK (amount>0),
  currency CHAR(3) NOT NULL DEFAULT 'KRW',
  method VARCHAR(20) NOT NULL,               -- CARD/TRANSFER 등
  status VARCHAR(20) NOT NULL DEFAULT 'PAID', -- PAID/PENDING/FAILED/REFUNDED
  paid_at DATETIME(6) NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT fk_payment_booking_id FOREIGN KEY (booking_id) REFERENCES bookings(id),
  CHECK (status IN ('PAID','PENDING','FAILED','REFUNDED')),
  INDEX idx_payment_booking (booking_id, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 리뷰(선택)
CREATE TABLE reviews (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  exhibition_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
  content TEXT NULL,
  review_file_id BIGINT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  UNIQUE KEY uk_review_once (exhibition_id, user_id),
  CONSTRAINT fk_review_exhibition_id FOREIGN KEY (exhibition_id) REFERENCES exhibitions(id),
  CONSTRAINT fk_review_user_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_review_review_file_id FOREIGN KEY (review_file_id) REFERENCES file_infos(id) ON DELETE SET NULL,
  INDEX idx_review_exhibition (exhibition_id, rating)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE review_files (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    
    review_id BIGINT NOT NULL,
    file_id BIGINT NOT NULL,
    display_order INT DEFAULT 0,
    
    CONSTRAINT fk_review_files_reviews FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE,
    CONSTRAINT fk_review_files_file_info FOREIGN KEY (file_id) REFERENCES file_infos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;


INSERT INTO roles (role_name) VALUES 
('USER'),
('ADMIN'),
('STAFF');

INSERT INTO user_roles (user_id, role_name)
VALUES (2, 'ADMIN');


INSERT INTO users
(created_at, updated_at, email, email_verified, login_id, name, password, provider, provider_id)
VALUES
(NOW(), NOW(), 'test@example.com', true, 'testuser', '테스트유저', '암호화된패스워드', 'LOCAL', 'dummy_provider_id');