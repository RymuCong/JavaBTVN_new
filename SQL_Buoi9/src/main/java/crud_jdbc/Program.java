package crud_jdbc;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n\t1. Thêm dữ liệu");
            System.out.println("\t2. Sửa dữ liệu");
            System.out.println("\t3. Xóa dữ liệu");
            System.out.println("\t4. Xem dữ liệu");
            System.out.println("\t5. Exit");

            System.out.print("\nNhập lựa chọn: ");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            jdbc_mysql jdbc = new jdbc_mysql();

            switch (option) {
                case 1:
                    jdbc.addData();
                    break;
                case 2:
                    jdbc.editData();
                    break;
                case 3:
                    System.out.print("Nhập id muốn xóa : ");
                    int id = sc.nextInt();
                    jdbc.deleteData(id);
                    break;
                case 4:
                    jdbc.showData();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Hãy nhập giá trị hợp lệ!");
            }
        }
    }
}
