package crud_jdbc;

import java.sql.*;
import java.util.Scanner;

public class jdbc_mysql {
    public  void addData() {
        Connection con = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên trường: ");
        String school_name = sc.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();
        try {
            // Đảm bảo rằng driver JDBC đã được nạp
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối đến cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:4040/tb_k35", "root", "root");
            // Sử dụng PreparedStatement để tránh SQL Injection
            String query = "INSERT INTO tb_k35.school(school_name, address) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, school_name);
            pst.setString(2, address);
            // Thực thi câu lệnh
            pst.executeUpdate();
            System.out.println("Dữ liệu đã được chèn thành công.");
        } catch (Exception e) {
            System.out.println("Ngu!");
            e.printStackTrace();
        }
        finally {
            // Đóng kết nối
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void showData() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:4040/tb_k35", "root", "root");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tb_k35.school");
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Tên trường: " + rs.getString("school_name"));
                System.out.println("Địa chỉ: " + rs.getString("address"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            // Đóng kết nối
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteData(int id) {
        Connection con = null;
        try {
            // Đảm bảo rằng driver JDBC đã được nạp
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối đến cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:4040/tb_k35", "root", "root");
            // Sử dụng PreparedStatement để tránh SQL Injection
            String query = "DELETE FROM tb_k35.school WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            // Thiết lập giá trị cho tham số (id)
            pst.setInt(1, id);
            // Thực thi câu lệnh xóa
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được xóa thành công.");
            } else {
                System.out.println("Không tìm thấy dữ liệu để xóa.");
            }
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra!");
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void editData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id muốn sửa: ");
        int id = sc.nextInt();
        System.out.print("Nhập tên trường muốn sửa: ");
        String newSchoolName = sc.next();
        System.out.print("Nhập địa chỉ muốn sửa: ");
        String newAddress = sc.next();
        Connection con = null;
        try {
            // Đảm bảo rằng driver JDBC đã được nạp
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối đến cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:4040/tb_k35", "root", "root");
            // Sử dụng PreparedStatement để tránh SQL Injection
            String query = "UPDATE tb_k35.school SET school_name = ?, address = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            // Thiết lập giá trị cho các tham số
            pst.setString(1, newSchoolName);
            pst.setString(2, newAddress);
            pst.setInt(3, id);
            // Thực thi câu lệnh cập nhật
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được cập nhật thành công.");
            } else {
                System.out.println("Không tìm thấy dữ liệu để cập nhật.");
            }
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra!");
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
