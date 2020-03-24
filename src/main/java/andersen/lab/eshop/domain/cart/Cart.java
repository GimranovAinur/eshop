package andersen.lab.eshop.domain.cart;

import andersen.lab.eshop.domain.Customer;
import andersen.lab.eshop.model.Currency;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Заказ.
 */
@Entity
@Table(name = "carts")
@Data
public class Cart implements Serializable {

    private static final long serialVersionUID = 7571207642462498070L;

    @Id
    @GeneratedValue
    private Long id;

    /** Покупатель */
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** Полная стоймость */
    @Column(name = "total_price")
    private Double totalPrice;

    /** Валюта */
    @Enumerated(EnumType.STRING)
    private Currency currency;

    /** Позиции заказа */
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

}