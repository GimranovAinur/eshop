package andersen.lab.eshop.domain.cart;

import andersen.lab.eshop.domain.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Позиция заказа.
 */
@Entity
@Table(name = "cart_items")
@Data
public class CartItem implements Serializable {

    private static final long serialVersionUID = 8919767316624674332L;

    @Id
    @GeneratedValue
    private Long id;

    /** Продукт */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /** Количество */
    @Column(columnDefinition = "integer default '1'")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
