package io.hexlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class Application {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            String sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (Statement statement = conn.createStatement()) {
                statement.execute(sql);
            }

            UserDAO dao = new UserDAO(conn);
            User user1 = new User("hommy", "123456789");
            User user2 = new User("whoopy", "123456789");
            User user3 = new User("largo", "123456789");
            dao.save(user1);
            dao.save(user2);
            dao.save(user3);
            dao.delete(user2.getId());
            Optional<User> user11 = dao.find(user1.getId());
            user11
                .map(u -> u.getName() + " " + u.getPhone())
                .ifPresent(System.out::println);
            Optional<User> user22 = dao.find(user2.getId());
            user22
                .map(u -> u.getName() + " " + u.getPhone())
                .ifPresent(System.out::println);
            Optional<User> user33 = dao.find(user3.getId());
            user33
                .map(u -> u.getName() + " " + u.getPhone())
                .ifPresent(System.out::println);
        }
    }
}