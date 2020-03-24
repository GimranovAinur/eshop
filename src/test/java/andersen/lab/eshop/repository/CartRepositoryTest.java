package andersen.lab.eshop.repository;

import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.Customer;
import andersen.lab.eshop.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartRepositoryTest {

    private Cart testData;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testCartFindById() {
        Cart cart = cartRepository.findById(testData.getId()).get();
        assertThat(cart, notNullValue());
    }

    @Test
    public void testCartItemSavedWhenSaveCart() {
        Cart cart = cartRepository.findById(testData.getId()).get();
        CartItem cartItem = cart.getCartItems().get(0);
        assertThat(cartItem, notNullValue());
    }

    @Test
    public void testCartRemove() {
        cartRepository.delete(testData);
        Optional<Cart> cart = cartRepository.findById(testData.getId());
        assertThat(cart.isPresent(), equalTo(Boolean.FALSE));
    }

    @BeforeEach
    public void setUp() {
        CartItem item1 = new CartItem();
        item1.setProduct(new Product());
        item1.setAmount(2);
        CartItem item2 = new CartItem();
        item2.setProduct(new Product());
        item2.setAmount(1);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(item1);
        cartItems.add(item2);

        testData = new Cart();
        testData.setCartItems(cartItems);
        testData.setCustomer(new Customer());
        cartRepository.save(testData);
    }

}
