package andersen.lab.eshop.repository;

import andersen.lab.eshop.domain.Cart;
import andersen.lab.eshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByCustomer(Customer customer);

}
