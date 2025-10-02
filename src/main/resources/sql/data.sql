-- users 테이블 데이터 삽입
INSERT INTO `users` (`no`, `user_id`, `password`, `email`) VALUES
(1, 'user01', 'passUser01', NULL),
(2, 'user02', 'passUser02', NULL),
(3, 'user03', 'passUser03', NULL),
(4, 'user04', 'passUser04', NULL),
(5, 'user05', 'passUser05', NULL),
(6, 'user06', 'passUser06', NULL),
(7, 'user07', 'passUser07', NULL),
(8, 'user08', 'passUser08', NULL),
(9, 'user09', 'passUser09', NULL),
(10, 'user10', 'passUser10', NULL),
(11, 'poweruser', 'passPower11', NULL),
(12, 'dietking', 'passDiet12', 'dietking@example.com'),
(13, 'admin01', 'passAdmin13', 'admin01@example.com'),
(14, 'runner14', 'passRunner14', 'runner14@example.com'),
(15, 'tester15', 'passTester15', 'tester15@example.com'),
(16, 'health_fan', 'passHealth16', 'health_fan@example.com'),
(17, 'newbie17', 'passNewbie17', 'newbie17@example.com'),
(18, 'pro18', 'passPro18', 'pro18@example.com'),
(19, 'lazy_guy', 'passLazy19', 'lazy_guy@example.com'),
(20, 'active_gal', 'passActive20', NULL);

-- video 테이블 데이터 삽입
INSERT INTO `video` (`no`, `title`, `part`, `url`) VALUES
(1, '전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]', '전신', 'https://www.youtube.com/embed/gMaB-fG4u4g'),
(2, '하루 15분! 전신 칼로리 불태우는 다이어트 운동', '전신', 'https://www.youtube.com/embed/swRNeYw1JkY'),
(3, '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]', '상체', 'https://www.youtube.com/embed/54tTYO-vU2E'),
(4, '상체비만 다이어트 최고의 운동 [상체 핵매운맛]', '상체', 'https://www.youtube.com/embed/QqqZH3j_vH0'),
(5, '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]', '하체', 'https://www.youtube.com/embed/tzN6ypk6Sps'),
(6, '저는 하체 식주의자 입니다', '하체', 'https://www.youtube.com/embed/u5OgcZdNbMo'),
(7, '11자복근 복부 최고의 운동 [복근 핵매운맛]', '복부', 'https://www.youtube.com/embed/PjGcOP-TQPE'),
(8, '(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)', '복부', 'https://www.youtube.com/embed/7TLk7pscICk');

-- saved_video 테이블 데이터 삽입 (user_no와 video_no의 매핑)
INSERT INTO `saved_video` (`user_no`, `video_no`) VALUES
-- user01 (no: 1) -> video: 1, 3, 7
(1, 1), (1, 3), (1, 7),
-- user02 (no: 2) -> video: 2, 4, 6, 8
(2, 2), (2, 4), (2, 6), (2, 8),
-- user03 (no: 3) -> video: 5, 6
(3, 5), (3, 6),
-- user04 (no: 4) -> video: 1, 2, 3, 4, 5, 6, 7, 8
(4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7), (4, 8),
-- user05 (no: 5) -> video: 7, 8
(5, 7), (5, 8),
-- user06 (no: 6) -> video: 1
(6, 1),
-- user07 (no: 7) -> video: 3, 4
(7, 3), (7, 4),
-- user09 (no: 9) -> video: 2, 5, 7
(9, 2), (9, 5), (9, 7),
-- user10 (no: 10) -> video: 6, 8, 3
(10, 6), (10, 8), (10, 3),
-- poweruser (no: 11) -> video: 1, 5, 7
(11, 1), (11, 5), (11, 7),
-- dietking (no: 12) -> video: 2, 4
(12, 2), (12, 4),
-- admin01 (no: 13) -> video: 7, 8
(13, 7), (13, 8),
-- runner14 (no: 14) -> video: 5, 6
(14, 5), (14, 6),
-- health_fan (no: 16) -> video: 1, 2, 3, 4
(16, 1), (16, 2), (16, 3), (16, 4),
-- newbie17 (no: 17) -> video: 7
(17, 7),
-- pro18 (no: 18) -> video: 5, 7, 8
(18, 5), (18, 7), (18, 8),
-- lazy_guy (no: 19) -> video: 6
(19, 6),
-- active_gal (no: 20) -> video: 1, 2, 3, 4, 5, 6, 7, 8
(20, 1), (20, 2), (20, 3), (20, 4), (20, 5), (20, 6), (20, 7), (20, 8);