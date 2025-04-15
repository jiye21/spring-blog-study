package com.estsoft.jdbc;


import java.sql.*;

/**
 * 1. DB 연결
 * 2. SQL 실행  -> INSERT INTO 쿼리
 * 3. 결과 출력  -> insert 갯수
 */
public class InsertJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement statement = conn.prepareStatement("INSERT INTO students (name, age, address) VALUES (?, ?, ?)")
        ) {
            statement.setString(1, "장이수");
            statement.setInt(2, 40);
            statement.setString(3, "서울");
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " updated.");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
