-- Bài 1: Tìm các giáo viên không tham gia đề tài
SELECT *
FROM giao_vien
WHERE ma_gv NOT IN (
	SELECT ma_gv
	FROM tham_gia_de_tai
);

-- Bài 2: Tìm tất cả giáo viên là trưởng bộ môn dùng 2 cách là join và cách truy vấn lồng

CREATE TABLE giao_vien (
	id INT PRIMARY KEY AUTO_INCREMENT,
	ho_ten VARCHAR(50),
	luong FLOAT,
	gioi_tinh VARCHAR(50)
);

INSERT INTO giao_vien (ho_ten, luong, gioi_tinh)
VALUES
	("Nguyễn Văn Ngọc", 10 , "Nam"),
	("Phạm Văn Nam", 12 , "Nam"),
	("Trần Văn Hải", 15 , "Nu"),
	("Phạm Thành Công", 20,"Nam"),
	("Nguyễn Thị Mai", 16,"Nữ");

CREATE TABLE bo_mon (
	id INT PRIMARY KEY AUTO_INCREMENT,
	ten_bo_mon VARCHAR(50),
	dien_thoai VARCHAR(50),
	truong_bo_mon INT,
	
	FOREIGN KEY (truong_bo_mon) REFERENCES giao_vien(id)
);

INSERT INTO bo_mon (ten_bo_mon,dien_thoai,truong_bo_mon) VALUES
	("Toán",0123456789,1),
	("Văn",0987654321,2),
	("Tiếng Anh", 0876779903,4);
	
-- Cách 1: Sử dụng JOIN
SELECT gv.*, bm.ten_bo_mon
FROM giao_vien gv
JOIN bo_mon bm ON gv.id = bm.truong_bo_mon;

-- Cách 2: Sử dụng truy vấn lồng
SELECT *
FROM giao_vien
WHERE id IN (
	SELECT truong_bo_mon
	FROM bo_mon
	);