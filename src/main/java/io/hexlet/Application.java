package io.hexlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Application {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            String sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (Statement statement = conn.createStatement()) {
                statement.execute(sql);
            }

            String sql2 = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, "hommy");
                preparedStatement.setString(2, "123456789");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "whoopy");
                preparedStatement.setString(2, "123456789");
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    String sql21 = "DELETE FROM users WHERE id = ?";
                    try (PreparedStatement preparedStatement2 = conn.prepareStatement(sql21)) {
                        preparedStatement2.setLong(1, id);
                        preparedStatement2.executeUpdate();
                    }
                } else {
                    throw new SQLException("DB have not returned an id after saving the entity");
                }

                preparedStatement.setString(1, "largo");
                preparedStatement.setString(2, "123456789");
                preparedStatement.executeUpdate();
            }
            String sql3 = "SELECT * FROM users";
            try (Statement statement3 = conn.createStatement()) {
                // Здесь вы видите указатель на набор данных в памяти СУБД
                ResultSet resultSet = statement3.executeQuery(sql3);
                // Набор данных — это итератор
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("phone"));
                }
            }
        }
    }
}