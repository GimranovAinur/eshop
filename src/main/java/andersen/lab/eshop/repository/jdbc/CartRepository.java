package andersen.lab.eshop.repository.jdbc;

import andersen.lab.eshop.domain.Customer;
import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.product.Product;
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
            "INSERT INTO carts (currency, total_price, customer_id) VALUES (?, ?, ?)";

    private static final String CREATE_CART_ITEM =
            "INSERT INTO cart_items (amount, cart_id, product_id) VALUES (?, ?, ?)";

    private static final String READ = "SELECT * FROM carts WHERE id = ?";

    private static final String READ_CART_ITEMS = "SELECT * FROM cart_items WHERE cart_id = ?";

    public static Cart save(Cart cart) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cart.getCurrency().name());
            statement.setDouble(2, cart.getTotalPrice());
            statement.setLong(3, cart.getCustomer().getId());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            Long id = null;
            if (result.next()) {
                id = result.getLong(1);
            }
            cart.setId(id);
            for(CartItem cartItem : cart.getCartItems()) {
                saveCartItem(cartItem);
            }
            return cart;
        } catch (SQLException e) {
            System.err.println("Ошибка сохранения литературы: " + e.getMessage());
        }
        return null;
    }

    public static Long saveCartItem(CartItem cartItem) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_CART_ITEM, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cartItem.getAmount());
            statement.setLong(2, cartItem.getCart().getId());
            statement.setLong(3, cartItem.getProduct().getId());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            Long id = null;
            if (result.next()) {
                id = result.getLong(1);
            }
            cartItem.setId(id);
            return id;
        } catch (SQLException e) {
            System.err.println("Ошибка сохранения литературы: " + e.getMessage());
        }
        return null;
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
                cart.setCustomer(new Customer());
                cart.setCartItems(findCartItems(cart));

                return Optional.of(cart);
            }
        } catch (SQLException e) {
            System.err.println("Не удалось найти автора: " + e.getMessage());
        }
        return Optional.empty();
    }

    public static List<CartItem> findCartItems(Cart cart) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ_CART_ITEMS);
            statement.setLong(1, cart.getId());
            ResultSet result = statement.executeQuery();
            List<CartItem> cartItems = new ArrayList<>();
            while (result.next()) {
                Long id = result.getLong(1);
                Integer amount = result.getInt(2);
                Long productId = result.getLong(4);
                Product product = ProductRepository.findById(productId).orElseThrow(EntityNotFoundException::new);

                CartItem cartItem = new CartItem();
                cartItem.setId(id);
                cartItem.setAmount(amount);
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItems.add(cartItem);
            }
            return cartItems;
        } catch (SQLException e) {
            System.err.println("Не удалось найти автора: " + e.getMessage());
        }
        return Collections.emptyList();
    }

}
