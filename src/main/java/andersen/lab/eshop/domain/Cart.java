package andersen.lab.eshop.domain;

import lombok.Data;
import lombok.Singular;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Заказ.
 */
@Entity
@Table(name = "carts")
@Data
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    /** Покупатель */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** Полная стоймость */
    @Column(name = "total_price")
    private Double totalPrice;

    /** Позиции заказа */
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

}
