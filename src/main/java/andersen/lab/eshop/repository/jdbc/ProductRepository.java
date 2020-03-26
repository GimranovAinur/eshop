package andersen.lab.eshop.repository.jdbc;

import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private static final String READ = "SELECT * FROM products WHERE id = ?";

    private static final String READ_ALL =
            "SELECT * FROM products";

    /**
     * Возвращает продукт по идентификатору.
     *
     * @param id идентификатор
     * @return продукт
     */
    public static Optional<Product> findById(Long id) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Long product_id = result.getLong(1);
                String name = result.getString(2);
                Double price = result.getDouble(3);
                Product product = new Product();
                product.setId(product_id);
                product.setName(name);
                product.setPrice(price);
                return Optional.of(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось найти продукт: " + e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Возвращает все продукты.
     *
     * @return список продуктов
     */
    public static List<Product> findAll() {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ_ALL);
            ResultSet result = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (result.next()) {
                Long product_id = result.getLong(1);
                String name = result.getString(2);
                Double price = result.getDouble(3);
                Product product = new Product();
                product.setId(product_id);
                product.setName(name);
                product.setPrice(price);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось найти продукт: " + e.getMessage());
        }
        return Collections.emptyList();
    }

}
