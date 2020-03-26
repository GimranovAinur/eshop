package andersen.lab.eshop.service.impl;

import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.exception.DatabaseException;
import andersen.lab.eshop.exception.EntityNotFoundException;
import andersen.lab.eshop.repository.jdbc.CartItemRepository;
import andersen.lab.eshop.repository.jdbc.CartRepository;
import andersen.lab.eshop.service.CartService;
import andersen.lab.eshop.util.CartPriceCounter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public void addToCart(Cart cart, CartItem cartItem) {
        List<CartItem> cartItems = cart.getCartItems();
        if(cartItems != null) {
            cartItems.add(cartItem);
            cart.setTotalPrice(CartPriceCounter.recountPrice(cart, cartItem));
            try {
                CartRepository.updateCart(cart);
                CartItemRepository.saveCartItem(cartItem, cart.getId());
            } catch (DatabaseException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public Cart removeFromCart(Cart cart, CartItem cartItem) {
        Cart updatedCart = CartRepository
                .findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        List<CartItem> cartItems = updatedCart.getCartItems();
        if((cartItems != null) && !cartItems.remove(cartItem)) {
            throw new EntityNotFoundException();
        }
        updatedCart.setCartItems(cartItems);
        return updatedCart;
    }

    @Override
    public List<Product> getCartProducts(Long cartId) {
        Cart cart = CartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        List<CartItem> cartItems = cart.getCartItems();
        if(cartItems != null) {
            return cartItems.stream().map(CartItem::getProduct).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
