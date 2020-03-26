package andersen.lab.eshop.service.impl;

import andersen.lab.eshop.domain.User;
import andersen.lab.eshop.domain.cart.Cart;
import andersen.lab.eshop.exception.DatabaseException;
import andersen.lab.eshop.model.Currency;
import andersen.lab.eshop.repository.jdbc.CartRepository;
import andersen.lab.eshop.repository.jdbc.UserRepository;
import andersen.lab.eshop.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(User user) throws DatabaseException {
        Cart cart = new Cart();
        cart.setCurrency(Currency.RUB);
        User savedUser = UserRepository.saveUser(user);
        cart.setUser(savedUser);
        CartRepository.save(cart);
        return savedUser;
    }
}
