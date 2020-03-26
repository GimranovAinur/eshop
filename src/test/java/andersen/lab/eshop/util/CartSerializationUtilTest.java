package andersen.lab.eshop.util;

import andersen.lab.eshop.domain.Customer;
import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import org.junit.jupiter.api.Test;

public class CartSerializationUtilTest {

    @Test
    public void testCartSerialization() {
        Customer customer = new Customer();
        customer.setEmail("Test email");
        Cart cart = new Cart();
        cart.setCustomer(customer);

        CartSerializationUtil.writeToFile(cart);
    }

}
