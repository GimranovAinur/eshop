package andersen.lab.eshop.service;

import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.product.Product;

import java.util.List;

public interface CartService {

    void addToCart(Cart cart, CartItem cartItem);

    Cart removeFromCart(Cart cart, CartItem cartItem);

    List<Product> getCartProducts(Long cartId);

}
