package andersen.lab.eshop.domain;

import andersen.lab.eshop.domain.cart.Cart;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Клиент магазина.
 */
@Entity
@Table(name = "customers")
@Data
public class Customer implements Serializable {

    private static final long serialVersionUID = -1931664796491144385L;

    @Id
    @GeneratedValue
    private Long id;

    /** Электронный адрес */
    @Column
    private String email;

    /** Пароль */
    @Column
    private String password;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

}
