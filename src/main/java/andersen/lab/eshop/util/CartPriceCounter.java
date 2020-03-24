package andersen.lab.eshop.util;

import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.domain.cart.CartItem;
import andersen.lab.eshop.domain.product.Product;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CartPriceCounter {

    public Double recountPrice(Cart cart, CartItem newCartItem) {
        if(newCartItem != null && cart != null){
            Product product = newCartItem.getProduct();
            if(product != null) {
                double cartItemPrice = product.getPrice() * newCartItem.getAmount();
                return cart.getTotalPrice() + cartItemPrice * cart.getCurrency().getCoefficient();
            }
        }
        throw new IllegalStateException();
    }

}
