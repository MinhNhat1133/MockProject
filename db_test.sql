-- Drop the database if it already exists
DROP DATABASE IF EXISTS mockup;
-- Create database
CREATE DATABASE IF NOT EXISTS mockup;
USE mockup;

-- Plans
DROP TABLE IF EXISTS plans;
CREATE TABLE IF NOT EXISTS plans (
	PRIMARY KEY (id), 	
	id 				TINYINT UNSIGNED AUTO_INCREMENT,
    plan_name		VARCHAR(250) NOT NULL,
    price			INT UNSIGNED NOT NULL 
);

-- Create table user
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users ( 	
	PRIMARY KEY (id), 	
	id 				INT UNSIGNED AUTO_INCREMENT,
	`email` 		CHAR(250) NOT NULL CHECK (LENGTH(`email`) >= 6 ),
    phone			VARCHAR(250) NOT NULL,
	`password` 		VARCHAR(500) NOT NULL,
    `full_name` 	VARCHAR(250) NOT NULL,
    `role` 			ENUM('ADMIN','CUSTOMER','STAFF') DEFAULT 'CUSTOMER',
	`status`		TINYINT DEFAULT 0 -- 0: Not Active, 1: Active
);

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders ( 	
	PRIMARY KEY (id), 	
	id 				INT UNSIGNED AUTO_INCREMENT,
    current_city	VARCHAR(255) NOT NULL,
    new_city		VARCHAR(255) NOT NULL,
    moving_date		DATE NOT NULL,
    plan_id			TINYINT UNSIGNED NOT NULL,
    customer_id		INT UNSIGNED NOT NULL,
    is_has_apartment_already TINYINT DEFAULT 0, -- 0 : khach hang CHUA chon duoc nha
    distance		SMALLINT UNSIGNED NOT NULL , -- Don vi tinh la km
    payment_status	ENUM('UNPAID','PAID')  DEFAULT 'UNPAID',
    payment_details	VARCHAR(255) ,  -- Phuong thuc thanh toan cua khach hang
    payment_date	DATE ,			
	`status`		TINYINT UNSIGNED DEFAULT 0 	, -- trang thai cua don hang tinh theo % 
    created_date	DATE DEFAULT (CURRENT_DATE),
	FOREIGN KEY (plan_id) REFERENCES plans (id),
    FOREIGN KEY (customer_id)	REFERENCES users (id)
);

DROP TABLE IF EXISTS services;
CREATE TABLE IF NOT EXISTS services ( 	
	PRIMARY KEY (id), 
	id 				TINYINT UNSIGNED AUTO_INCREMENT,
    service_content	VARCHAR(250) NOT NULL,
    service_weight	TINYINT UNSIGNED NOT NULL  , 
    required		TINYINT DEFAULT 0  -- 1 : cho nhung khach hang CHUA chon duoc nha, 
);

DROP TABLE IF EXISTS service_completion_progress;
CREATE TABLE IF NOT EXISTS service_completion_progress ( 	
	PRIMARY KEY (order_id,service_id), 	
	order_id 	INT UNSIGNED NOT NULL,
    service_id  TINYINT UNSIGNED NOT NULL,
	proposed_date   DATE,   -- Ngay de xuat 
    `status`  TINYINT DEFAULT 0, -- 0: chua hoan thanh , 1: da hoan thanh
	completion_date DATE ,	-- Ngay hoan thanh
	FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (service_id)	REFERENCES services (id)
);



DROP TABLE IF EXISTS plan_services;
CREATE TABLE IF NOT EXISTS plan_services (
	PRIMARY KEY(plan_id,service_id),
	plan_id 		TINYINT UNSIGNED NOT NULL,
    service_id		TINYINT UNSIGNED NOT NULL,
	FOREIGN KEY (plan_id) REFERENCES plans (id),
    FOREIGN KEY (service_id)	REFERENCES services (id)
);


-- Create table Registration_User_Token
DROP TABLE IF EXISTS 	`Registration_User_Token`;
CREATE TABLE IF NOT EXISTS `Registration_User_Token` ( 	
	id 				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`token`	 		CHAR(36) NOT NULL UNIQUE,
	`user_id` 		SMALLINT UNSIGNED NOT NULL,
	`expiryDate` 	DATETIME NOT NULL
);

-- Create table Reset_Password_Token
DROP TABLE IF EXISTS 	`Reset_Password_Token`;
CREATE TABLE IF NOT EXISTS `Reset_Password_Token` ( 	
	id 				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`token`	 		CHAR(36) NOT NULL UNIQUE,
	`user_id` 		SMALLINT UNSIGNED NOT NULL,
	`expiryDate` 	DATETIME NOT NULL
);

-- Insert in to plans:
insert into plans (plan_name, price) values ('GOI A', 1000);
insert into plans (plan_name, price) values ('GOI B', 2500);
insert into plans (plan_name, price) values ('GOI C', 100000);

-- Insert in to services:
insert into services (service_content, service_weight, required) values ('Tìm nhà', 10, 0);
insert into services (service_content, service_weight, required) values ('Ký hợp đồng thuê nhà', 2, 0);
insert into services (service_content, service_weight, required) values ('Cắt hợp đồng nhà', 3, 1);
insert into services (service_content, service_weight, required) values ('Cắt hợp đồng điện, nước, ga, internet', 4, 1);
insert into services (service_content, service_weight, required) values ('Thông báo chuyển ra khỏi tỉnh/thành phố 
hiện tại ', 5, 1);
insert into services (service_content, service_weight, required) values ('Thủ tục yêu cầu chuyển đồ đến địa chỉ 
mới', 6, 1);
insert into services (service_content, service_weight, required) values ('Thủ tục thay đổi địa chỉ ', 7, 1);
insert into services (service_content, service_weight, required) values ('Thủ tục vứt những đồ không dùng nữa: ', 8, 1);
insert into services (service_content, service_weight, required) values ('Dọn dẹp nhà cửa, Chuyển đồ', 9, 1);


insert into plan_services (plan_id, service_id) values (1, 1);
insert into plan_services (plan_id, service_id) values (1, 2);
insert into plan_services (plan_id, service_id) values (1, 3);
insert into plan_services (plan_id, service_id) values (1, 4);
insert into plan_services (plan_id, service_id) values (1, 5);
insert into plan_services (plan_id, service_id) values (1, 6);
insert into plan_services (plan_id, service_id) values (1, 7);
insert into plan_services (plan_id, service_id) values (1, 8);
insert into plan_services (plan_id, service_id) values (1, 9);

insert into plan_services (plan_id, service_id) values (2, 1);
insert into plan_services (plan_id, service_id) values (2, 2);
insert into plan_services (plan_id, service_id) values (2, 3);
insert into plan_services (plan_id, service_id) values (2, 4);
insert into plan_services (plan_id, service_id) values (2, 5);
insert into plan_services (plan_id, service_id) values (2, 6);
insert into plan_services (plan_id, service_id) values (2, 7);
insert into plan_services (plan_id, service_id) values (2, 8);
insert into plan_services (plan_id, service_id) values (2, 9);

insert into plan_services (plan_id, service_id) values (3, 1);
insert into plan_services (plan_id, service_id) values (3, 2);
insert into plan_services (plan_id, service_id) values (3, 3);
insert into plan_services (plan_id, service_id) values (3, 4);
insert into plan_services (plan_id, service_id) values (3, 5);
insert into plan_services (plan_id, service_id) values (3, 6);
insert into plan_services (plan_id, service_id) values (3, 7);
insert into plan_services (plan_id, service_id) values (3, 8);
insert into plan_services (plan_id, service_id) values (3, 9);




-- password: 123456
-- INSERT INTO `User` 	(`username`,			`email`,						`password`,														`firstName`,		`lastName`, 	`status`, 	`role`,  		`avatarUrl`				)
-- VALUE				('hanh.havan@vti',		'hanhhanoi1999@gmail.com',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Hà'	,		'Văn Hanh',			0, 		'Manager' 	,		null				), 
-- 					('thanhhung12@vti',		'hung122112@gmail.com',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Thanh Hưng',		0, 		'Manager' 	,		null				), 
-- 					('can.tuananh@vti',		'cananh.tuan12@vti.com',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Cấn'	,		'Tuấn Anh',			0, 		'Manager' 	,		null				), 
-- 					('toananh123@vti',		'toananh123@vti.com',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Anh Toàn',			0, 		'Manager' 	,		null				), 
-- 					('manhhung123@vti',		'manhhung123@vti.com',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Mạnh Hùng',		0, 		'Manager' 	,		null				),
-- 					('maianhvti123',		'maianhng@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Mai Anh',			0, 		'Employee'	,		null				),
-- 					('tuanvti12344',		'tuan1234@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Văn Tuấn',			0, 		'Employee'	,		null				),
-- 					('ngthuy123',			'thuyhanoi@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Thị Thủy',			0, 		'Employee'	,		null				),
-- 					('quanganhvti',			'quanganh@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Quang Anh',		0, 		'Manager' 	,		null				),
-- 					('hoanghungvti',	    'hunghoang@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Vũ'	,		'Hoàng Hưng',		0, 		'Employee'	,		null				),
-- 					('quocanhvti',			'quocanh12@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Quốc Anh',			0, 		'Admin'	  	,		null				),
-- 					('vananhvti',			'vananhb1@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Vân Anh',			0, 		'Employee'	,		null				),
-- 					('mailanvti',			'mailan123@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Thị Trinh',		0, 		'Manager' 	,		null				),
-- 					('tuanhungvti',			'tuanhung@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Vũ'	,		'Tuấn Hưng',		0, 		'Employee'	,		null				),
-- 					('xuanmaivti',			'xuanmai12@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Xuân Mai',			0, 		'Employee'	,		null				),
--                     ('duynn03',				'duynn03@gmail.com', 			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',		'Nguyễn',		'Duy',				1, 		'Employee'	,		'1613362949329.png'	);
--                     
