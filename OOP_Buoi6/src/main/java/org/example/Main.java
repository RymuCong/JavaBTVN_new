package org.example;

//        Tạo lớp Person chứa các thông tin:
//        - Tên
//        - Giới tính
//        - Ngày sinh
//        - Địa chỉ
//        1. Lớp Person bao gồm đầy đủ các phương thức getter, setter, constructor không tham số, constructor đầy đủ tham số
//        2. Viết phương thức inputInfo() để nhập thông tin Person từ bàn phím
//        3. Viết phương thức showInfo() để hiển thị tất cả thông tin Person

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.inputInfo();
        p1.showInfo();
    }
}