package andersen.lab.eshop.service;

import andersen.lab.eshop.domain.User;
import andersen.lab.eshop.exception.DatabaseException;

public interface UserService {

    User createUser(User user) throws DatabaseException;

}
