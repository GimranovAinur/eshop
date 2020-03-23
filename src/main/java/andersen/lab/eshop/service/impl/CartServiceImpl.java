package andersen.lab.eshop.service.impl;

import andersen.lab.eshop.domain.Cart;
import andersen.lab.eshop.domain.CartItem;
import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.exception.EntityNotFoundException;
import andersen.lab.eshop.repository.CartRepository;
import andersen.lab.eshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart addToCart(Cart cart, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.getCartItems().add(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeFromCart(Cart cart, CartItem cartItem) {
        Cart updatedCart = cartRepository
                .findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        List<CartItem> cartItems = updatedCart.getCartItems();
        if(!cartItems.remove(cartItem)) {
            throw new EntityNotFoundException();
        }
        updatedCart.setCartItems(cartItems);
        return updatedCart;
    }

    @Override
    public List<Product> getCartProducts(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        return cart.getCartItems().stream().map(cartItem -> cartItem.getProduct()).collect(Collectors.toList());
    }
}
