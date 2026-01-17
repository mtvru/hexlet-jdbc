package io.hexlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Application {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test");

        String sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
        Statement statement = conn.createStatement();
        statement.execute(sql);
        statement.close();

        String sql2 = "INSERT INTO users (username, phone) VALUES ('hommy', '123456789')";
        Statement statement2 = conn.createStatement();
        statement2.executeUpdate(sql2);
        statement2.close();

        String sql3 = "SELECT * FROM users";
        Statement statement3 = conn.createStatement();

        // Здесь вы видите указатель на набор данных в памяти СУБД
        ResultSet resultSet = statement3.executeQuery(sql3);

        // Набор данных — это итератор
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("phone"));
        }
        statement3.close();

        conn.close();
    }
}