package andersen.lab.eshop.repository.jdbc;

import andersen.lab.eshop.domain.User;
import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.exception.DatabaseException;
import andersen.lab.eshop.util.DatabaseConnector;

import java.sql.*;
import java.util.Optional;

public class UserRepository {

    private static final String CREATE =
            "INSERT INTO users (email, password) VALUES (?, ?)";

    private static final String READ_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static final String READ_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    public static User saveUser(User user) throws DatabaseException {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                user.setId(result.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public static Optional<User> findById(Long id) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Long userId = result.getLong(1);
                String email = result.getString(2);
                String password = result.getString(3);

                User user = new User();
                user.setId(userId);
                user.setEmail(email);
                user.setPassword(password);
                user.setCart(CartRepository.findByUserId(userId).orElse(new Cart()));

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось найти продукт: " + e.getMessage());
        }
        return Optional.empty();
    }

    public static Optional<User> findByEmail(String email) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ_BY_EMAIL);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Long userId = result.getLong(1);
                String password = result.getString(3);

                User user = new User();
                user.setId(userId);
                user.setEmail(email);
                user.setPassword(password);
                user.setCart(CartRepository.findByUserId(userId).orElse(new Cart()));

                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.err.println("Не удалось найти продукт: " + e.getMessage());
        }
        return Optional.empty();
    }

}
