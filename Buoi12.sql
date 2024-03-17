CREATE TABLE products (
    id INT PRIMARY KEY,
    productName VARCHAR(255),
    price DECIMAL(10, 2),
    amount INT,
    active BOOL,
    taxPercent DECIMAL(5, 2),
    createdDate DATE,
    updatedDate DATE
);

INSERT INTO products (id, productName, price, amount, active, taxPercent, createdDate, updatedDate)
VALUES 
    (1, 'Rượu', 16, 4, 0, 46, '2021-12-18', '2022-09-12'),
    (2, 'Muối', 36, 5, 0, 13, '2021-11-13', '2022-03-05'),
    (3, 'Trứng', 32, 16, 0, 5, '2022-08-05', '2022-04-22'),
    (4, 'Thịt Bò', 44, 13, 1, 56, '2022-08-23', '2022-04-09'),
    (5, 'Bánh Mì', 41, 17, 0, 64, '2019-07-18', '2021-12-23'),
    (6, 'Hạt Tiêu', 24, 13, 0, 86, '2022-02-19', '2022-08-25'),
    (7, 'Bia', 17, 18, 0, 6, '2022-03-14', '2022-10-15'),
    (8, 'Khoai Tây', 31, 9, 1, 39, '2021-12-04', '2022-01-03'),
    (9, 'Hoa', 25, 9, 0, 65, '2019-05-21', '2022-01-17'),
    (10, 'Bắp cải', 48, 7, 1, 27, '2021-11-04', '2022-03-13'),
    (11, 'Cua', 24, 14, 0, 48, '2022-01-09', '2022-02-11'),
    (12, 'Đào khô', 37, 17, 1, 89, '2022-07-31', '2021-12-06'),
    (13, 'Mù tạt', 46, 2, 1, 28, '2022-04-15', '2022-10-08'),
    (14, 'Kiwi', 30, 14, 0, 81, '2022-06-21', '2022-04-24');

# 1. Tìm tên sản phẩm có chữ 'a'
SELECT productName AS sp_chua_a FROM products WHERE productName LIKE "%a%";

# 2. Tìm tên sản phẩm bắt đầu bằng chữ 'b'
SELECT productName AS sp_bat_dau_b FROM products WHERE productName LIKE "b%";

# 3. Tìm tên sản phẩm có ký tự gần cuối là chữ 'a'
SELECT productName AS sp_kyTu_ganCuoi_a FROM products WHERE productName LIKE "%a_";

# 4. Tìm ra những sp có giá tiền trên 30 và mức thuế dưới 15 phần trăm
SELECT * FROM products WHERE price > 30 AND taxPercent < 15;

# 5. Tìm ra những sản phẩm mà có ngày tạo trong năm 2021
SELECT * FROM products WHERE YEAR(createdDate) = 2021;