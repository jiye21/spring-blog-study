package com.estsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteJdbcExample {
    private final static String SQL = "DELETE FROM students WHERE id = ?";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    private final static String username = "root";
    private final static String password = "0000";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, username, password);
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            int id = 3;
            ps.setInt(1, id);

            int rowNum = ps.executeUpdate();
            // 3. 실행 결과 출력
            System.out.println("rowNum = " + rowNum);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
