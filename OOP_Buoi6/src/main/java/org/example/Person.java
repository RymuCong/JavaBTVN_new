package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Person {
    private String name;
    private boolean gender;
    private LocalDate dateOfBirth;
    private String address;
    public Person() {
    }

    public Person(String name, boolean gender, LocalDate dateOfBirth, String address) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void inputInfo (){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập họ tên: ");
        this.name = sc.nextLine();
        System.out.print("Nhập giới tính (true: nam/ false: nữ): ");
        this.gender = sc.nextBoolean();
        System.out.print("Nhập ngày tháng năm sinh (ví dụ: 30/04/1997): ");
        var dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(sc.next(),dft);
        sc.nextLine();
        System.out.print("Nhập địa chỉ: ");
        this.address = sc.nextLine();
        sc.close();
    }

    public void showInfo () {
        System.out.println("Thông tin đối tượng là: ");
        System.out.println("Họ và tên: "+this.name);
        System.out.println("Giới tính: "+this.gender);
        System.out.println("Ngày tháng năm sinh: "+DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.dateOfBirth));
        System.out.println("Địa chỉ: "+this.address);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                '}';
    }
}
