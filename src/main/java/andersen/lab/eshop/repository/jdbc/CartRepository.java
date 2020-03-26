package andersen.lab.eshop.repository.jdbc;

import andersen.lab.eshop.domain.User;
import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.exception.DatabaseException;
import andersen.lab.eshop.exception.EntityNotFoundException;
import andersen.lab.eshop.model.Currency;
import andersen.lab.eshop.util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CartRepository {

    private static final String CREATE =
            "INSERT INTO carts (currency, total_price, user_id) VALUES (?, ?, ?)";

    private static final String UPDATE_CART = "UPDATE carts SET currency = ?, total_price = ?";

    private static final String READ = "SELECT * FROM carts WHERE id = ?";

    private static final String READ_BY_USER = "SELECT * FROM carts WHERE user_id = ?";

    public static Cart save(Cart cart) throws DatabaseException {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cart.getCurrency().name());
            statement.setDouble(2, cart.getTotalPrice());
            statement.setLong(3, cart.getUser().getId());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            Long id = null;
            if (result.next()) {
                id = result.getLong(1);
            }
            cart.setId(id);
            for(CartItem cartItem : cart.getCartItems()) {
                CartItemRepository.saveCartItem(cartItem, cart.getId());
            }
            return cart;
        } catch (SQLException e) {
            throw new DatabaseException("Не удалось сохранить корзину");
        }
    }

    public static void updateCart(Cart cart) throws DatabaseException {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_CART, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cart.getCurrency().name());
            statement.setDouble(2, cart.getTotalPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Не удалось обновить корзину");
        }
    }

    public static Optional<Cart> findById(Long id) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Long cart_id = result.getLong(1);
                Double price = result.getDouble(3);

                Cart cart = new Cart();
                cart.setId(cart_id);
                cart.setCurrency(Currency.RUB);
                cart.setTotalPrice(price);
                cart.setUser(new User());
                cart.setCartItems(CartItemRepository.findCartItems(cart));

                return Optional.of(cart);
            }
        } catch (SQLException e) {
            System.err.println("Не удалось найти корзину: " + e.getMessage());
        }
        return Optional.empty();
    }

    public static Optional<Cart> findByUserId(Long userId) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ_BY_USER);
            statement.setLong(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Long cart_id = result.getLong(1);
                Double price = result.getDouble(3);

                Cart cart = new Cart();
                cart.setId(cart_id);
                cart.setCurrency(Currency.RUB);
                cart.setTotalPrice(price);
                cart.setCartItems(CartItemRepository.findCartItems(cart));

                return Optional.of(cart);
            }
        } catch (SQLException e) {
            System.err.println("Не удалось найти корзину: " + e.getMessage());
        }
        return Optional.empty();
    }



}
