# Bài 1
CREATE TABLE NHANVIEN (
    id INT,
    NAME VARCHAR(255),
    Ngay_Bat_Dau DATE,
    So_Chiec INT
);

INSERT INTO nhanvien (id, NAME, Ngay_Bat_Dau, So_Chiec) VALUES 
(1, 'Hoang', '2007-01-24', 250),
(2, 'Nam', '2007-05-27', 220),
(3, 'Viet', '2007-05-06', 170),
(3, 'Viet', '2007-04-06', 100),
(4, 'Huong', '2007-04-06', 220),
(5, 'Xuan', '2007-06-06', 300),
(5, 'Xuan', '2007-02-06', 350);

SELECT CONCAT(id,": ",NAME,", ", Ngay_Bat_Dau) AS thong_tin FROM nhanvien;

# Bài 2

CREATE TABLE employee (
	employee_id INT PRIMARY KEY,
	employee_name VARCHAR(255),
	salary INT
);


INSERT INTO employee (employee_id, employee_name, salary)
VALUES 
	(12,'Finch',15000),
	(22,'Peter',25000),
	(32,'Warner',5600),
	(42,'Watson',90000);
	
UPDATE employee 
SET salary = salary / 2;

SELECT employee_id, employee_name, if (salary < 50000, salary * 2, salary) FROM employee;

SELECT * FROM employee;

# Bài 3

CREATE TABLE person (
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birthday DATE
);

INSERT INTO person (first_name, last_name, birthday) VALUES 
('Le Thi', 'Thu', '1996-12-12'),
('Vu Thi', 'Nga', '1997-11-17'),
('Nguyen Van', 'C', '2000-12-17'),
('Pham Van', 'D', '1998-10-11'),
('Tran Dinh', 'Trong', '1997-04-25'),
('Bui', 'Tien Dung', '1997-02-28');

SELECT DATEDIFF(NOW(),birthday) AS so_ngay FROM person;