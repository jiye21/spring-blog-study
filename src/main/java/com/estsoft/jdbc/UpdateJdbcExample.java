package com.estsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateJdbcExample {
    private final static String SQL = "UPDATE students SET name=?, age=?, address=? WHERE id=?";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    private final static String username = "root";
    private final static String password = "0000";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(DB_URL, username, password);
                PreparedStatement ps = conn.prepareStatement(SQL)) {

            // 2. conn.statement  (SQL)
            ps.setString(1, "name");
            ps.setInt(2, 41);
            ps.setString(3, "제주도");
            ps.setInt(4, 2);

            int rowNum = ps.executeUpdate();
            // 3. 실행 결과 출력
            System.out.println("rowNum = " + rowNum);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
