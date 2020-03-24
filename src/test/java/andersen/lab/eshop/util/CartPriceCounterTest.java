package andersen.lab.eshop.util;

import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.model.Currency;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CartPriceCounterTest {

    @Test
    public void testRecountCartPrice() {
        Cart cart = new Cart();
        cart.setTotalPrice(2000.0);
        cart.setCurrency(Currency.USD);

        Product product = new Product();
        product.setPrice(1000.0);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setAmount(2);

        assertThat(CartPriceCounter.recountPrice(cart, cartItem), equalTo(6000.0));
    }

}
