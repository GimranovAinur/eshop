package andersen.lab.eshop.service;

import andersen.lab.eshop.domain.Cart;
import andersen.lab.eshop.domain.CartItem;
import andersen.lab.eshop.domain.Customer;
import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.exception.EntityNotFoundException;
import andersen.lab.eshop.repository.CartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @Test
    public void testAddToCart() {
        Product product = new Product();
        Cart cart = cartRepository.findById(any(Long.class)).get();
        cartService.addToCart(cart, product);
        boolean containsProduct = cart.getCartItems()
                .stream()
                .anyMatch(cartItem -> cartItem.getProduct().equals(product));
        assertThat(containsProduct, equalTo(Boolean.TRUE));
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void testRemoveFromCartThrowsException() {
        Cart cart = cartRepository.findById(anyLong()).get();
        CartItem cartItem = cart.getCartItems().get(0);

        assertThrows(EntityNotFoundException.class, () -> cartService.removeFromCart(cart, cartItem));
        verify(cartRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetCartProductsThrowsException() {
        Cart cart = cartRepository.findById(anyLong()).get();
        assertThrows(EntityNotFoundException.class, () -> cartService.getCartProducts(cart.getId()));
        verify(cartRepository, times(1)).findById(anyLong());
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

        Cart cart = new Cart();
        cart.setCartItems(cartItems);
        cart.setCustomer(new Customer());
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));
    }

}
