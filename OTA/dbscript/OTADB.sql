drop table if exists notice;
drop table if exists auction_has_category_auction;
drop table if exists bidding;
drop table if exists category_auction;
drop table if exists auction;
drop table if exists user;

create table user(
	user_id varchar(20),
	user_password varchar(20),
	user_name varchar(20),
	user_email varchar(20),
	user_phone varchar(20),
	user_postcode varchar(20),
	user_address varchar(20),
	user_detail_address varchar(20),
	user_join_date datetime,
    primary key (user_id)
);

insert into user value ('admin','1234','관리자','admin@admin.com','010-1234-5678','우편번호','주소','상세주소',sysdate());

create table notice (
	notice_id int AUTO_INCREMENT,
    notice_title varchar(200) not null,
	notice_content text,
    notice_post_date timestamp,
    notice_visit_count int not null,
    notice_importance_type varchar(10) not null,
    notice_user_id varchar(20),
    constraint NOTICE_PK primary key (notice_id),
    foreign key ( notice_user_id) references user(user_id) on delete cascade
);

insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('adafbb', 'fabfabfb', '2024-09-25', 0,'하', 'admin');   
 
insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('adafbb', 'fabfabfb', now(), 0,'하', 'admin');   

insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('adafbb', 'fabfabfb', now(), 0,'중', 'admin');       

insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('adb', 'fabfb', now(), 0,'상', 'admin'); 
    
insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('adafbb', 'fabfabfb', now(), 0,'중', 'admin');
 
insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('adafbb', 'fabfabfb', now(), 0,'하', 'admin');    

insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('adafbb', 'fabfabfb', now(), 0,'중', 'admin');

insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('adafbb', 'fabfabfb', now(), 0,'하', 'admin');
    
insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
	notice_importance_type,notice_user_id) value ('a%dafbb', 'fabfabfb', now(), 0,'하', 'admin');    

drop procedure if exists notice_insert; 

DELIMITER $$
CREATE PROCEDURE notice_insert()
BEGIN
	DECLARE i INT DEFAULT 1; 
    	WHILE (i <= 350) 
        DO 
		insert into notice (notice_title, notice_content, notice_post_date, notice_visit_count,
		notice_importance_type,notice_user_id) value (concat('adafbb',i), concat('adabafbb',i), now(), 0,'하', 'admin'); 
        SET i = i + 1;
	END WHILE; 
END $$
DELIMITER ;
call notice_insert();

CREATE TABLE `auction` (
  `auction_id` int NOT NULL,
  `auction_title` varchar(20) NOT NULL,
  `auction_content` varchar(300) NOT NULL,
  `auction_img` varchar(100) NOT NULL,
  `auction_postdate` datetime NOT NULL,
  `auction_startingbid` int NOT NULL,
  `auction_startingdate` datetime NOT NULL,
  `auction_enddate` datetime NOT NULL,
  `auction_view` int unsigned NOT NULL,
  `auction_winner` varchar(6) DEFAULT NULL,
  `user_user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`auction_id`),
  KEY `fk_auction_user_idx` (`user_user_id`),
  CONSTRAINT `fk_auction_user` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `category_auction` (
  `category_auction_id` int NOT NULL,
  `category_auction_name` varchar(10) NOT NULL,
  PRIMARY KEY (`category_auction_id`),
  UNIQUE KEY `category_auction_name_UNIQUE` (`category_auction_name`)
);

CREATE TABLE `bidding` (
  `bidding_id` int NOT NULL,
  `bidding_bid` int NOT NULL,
  `bidding_biddate` datetime NOT NULL,
  `user_user_id` varchar(20) NOT NULL,
  `auction_auction_id` int NOT NULL,
  PRIMARY KEY (`bidding_id`),
  KEY `fk_bidding_user1_idx` (`user_user_id`),
  KEY `fk_bidding_auction1_idx` (`auction_auction_id`),
  CONSTRAINT `fk_bidding_auction1` FOREIGN KEY (`auction_auction_id`) REFERENCES `auction` (`auction_id`),
  CONSTRAINT `fk_bidding_user1` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `auction_has_category_auction` (
  `auction_auction_id` int NOT NULL,
  `category_auction_category_auction_id` int NOT NULL,
  PRIMARY KEY (`auction_auction_id`,`category_auction_category_auction_id`),
  KEY `fk_auction_has_category_auction_category_auction1_idx` (`category_auction_category_auction_id`),
  KEY `fk_auction_has_category_auction_auction1_idx` (`auction_auction_id`),
  CONSTRAINT `fk_auction_has_category_auction_auction1` FOREIGN KEY (`auction_auction_id`) REFERENCES `auction` (`auction_id`),
  CONSTRAINT `fk_auction_has_category_auction_category_auction1` FOREIGN KEY (`category_auction_category_auction_id`) REFERENCES `category_auction` (`category_auction_id`)
);



