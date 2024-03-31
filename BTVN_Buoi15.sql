CREATE TABLE Khoa (
	id INT PRIMARY KEY AUTO_INCREMENT,
	makhoa VARCHAR(10) UNIQUE,
	tenkhoa VARCHAR(30),
	dienthoai VARCHAR(10)
);

CREATE TABLE SinhVien (
	id INT PRIMARY KEY AUTO_INCREMENT,
	masv INT,
	hotensv VARCHAR(30),
	makhoa VARCHAR(10),
	namsinh INT,
	quequan VARCHAR(30),
	FOREIGN KEY (makhoa) REFERENCES khoa (makhoa)
);

INSERT INTO Khoa (makhoa, tenkhoa, dienthoai) VALUES
('K01', 'Toan', '0123456789'),
('K02', 'Ly', '0123456788'),
('K03', 'Hoa', '0123456787'),
('K04', 'Sinh', '0123456786'),
('K05', 'Van', '0123456785'),
('K06', 'Su', '0123456784'),
('K07', 'Dia', '0123456783'),
('K08', 'Anh', '0123456782'),
('K09', 'Phap', '0123456781'),
('K10', 'Duc', '0123456780');

INSERT INTO SinhVien (masv, hotensv, makhoa, namsinh, quequan) VALUES
(1, 'Nguyen Van A', 'K02', 2006, 'Ha Noi'),
(2, 'Le Thi B', 'K01', 2001, 'Vinh Phuc'),
(3, 'Tran Van C', 'K03', 2003, 'Thanh Hoa'),
(4, 'Pham Thi D', 'K01', 2001, 'Hai Phong'),
(5, 'Vu Van E', 'K06', 2009, 'Nam Dinh'),
(6, 'Bui Thi F', 'K05', 2001, 'HCM'),
(7, 'Hoang Van G', 'K08', 1999, 'Ha Noi'),
(8, 'Dang Thi H', 'K09', 2004, 'Viet Nam'),
(9, 'Do Van I', 'K03', 2005, 'Canada');

INSERT INTO SinhVien (masv, hotensv, namsinh, quequan) VALUES
(10, 'Nguyen Thi J', 2001, 'Ha Noi'),
(11, 'Tran Van K', 2004, 'Hai Phong'),
(12, 'Le Thi L', 2003, '');

# Hãy đưa ra thông tin gồm mã số, họ tên và tên khoa của tất cả các sinh viên
SELECT masv, hotensv, tenkhoa
FROM sinhvien LEFT JOIN khoa 
ON sinhvien.makhoa = khoa.makhoa;

# Sử dụng lệnh SQL để xuất ra thông tin về những sinh viên chưa được gán vào 1 khoa xác định
SELECT *
FROM sinhvien
WHERE sinhvien.makhoa IS NULL;

# Sử dụng câu lệnh SQL xuất ra danh sách gồm Mã số, Họ tên và Tuổi của các sinh viên khoa ‘TOAN’?
SELECT masv, hotensv, (YEAR(NOW()) - namsinh) AS Tuoi
FROM sinhvien JOIN khoa
ON sinhvien.makhoa = khoa.makhoa
WHERE khoa.tenkhoa = 'Toan';