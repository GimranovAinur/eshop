package andersen.lab.eshop.repository;

import andersen.lab.eshop.domain.product.Category;
import andersen.lab.eshop.domain.product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    private static Product testData;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        Category category = new Category();
        category.setName("Тестовая категория");
        testData = new Product();
        testData.setName("Тестовый продукт");
        testData.setCategory(category);
        testData.setPrice(1000.0);
        testData.setStock(5);
        testData.setMeasure("кг");
        productRepository.save(testData);
    }

    @Test
    public void testProductSave() {
        Product product = new Product();
        product.setName("Тестовый продукт");
        product.setPrice(1000.0);
        product.setStock(5);
        product.setMeasure("кг");
        assertThat(product, notNullValue());
        productRepository.delete(product);
    }

    @Test
    public void testProductFindById() {
        Product product = productRepository.findById(testData.getId()).get();
        assertThat(product, notNullValue());
    }

    @Test
    public void testProductFindByName() {
        Product product = productRepository.findByName(testData.getName()).get();
        assertThat(testData.getName(), equalTo(product.getName()));
    }

    @Test
    public void testProductRemove() {
        productRepository.delete(testData);
        Optional<Product> product = productRepository.findById(testData.getId());
        assertThat(product.isPresent(), equalTo(Boolean.FALSE));
    }

    @Test
    public void testFindByCategory() {
        List<Product> productList = productRepository.findAllByCategory_Name("Тестовая категория");
        assertThat(productList.contains(testData), equalTo(Boolean.TRUE));
    }

    @Test
    public void testFindAll() {
        Product product = new Product();
        productRepository.save(product);
        List<Product> products = productRepository.findAll();

        assertThat(products.size(), equalTo(4));
    }

}
