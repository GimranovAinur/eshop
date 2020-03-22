package andersen.lab.eshop.service;

import andersen.lab.eshop.domain.product.Category;
import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @BeforeEach
    public void setUp() {
        Category category = new Category();
        category.setName("Тестовая категория");
        Product product = new Product();
        product.setName("Тестовый продукт");
        product.setCategory(category);
        product.setPrice(1000.0);
        product.setStock(5);
        product.setMeasure("кг");
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
    }

    @Test
    public void testGetById() {
        Product product = productService.getProductById(any(Long.class));
        assertThat(product, instanceOf(Product.class));
        verify(productRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void testGetProductList() {
        productService.getProductList();
        verify(productRepository, times(1)).findAll();
    }

}
