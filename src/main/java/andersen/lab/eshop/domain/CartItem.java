package andersen.lab.eshop.domain;

import andersen.lab.eshop.domain.product.Product;
import lombok.Data;

import javax.persistence.*;

/**
 * Позиция заказа.
 */
@Entity
@Table(name = "cart_items")
@Data
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    /** ID продукта */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /** Заказанное кол-во */
    @Column(columnDefinition = "integer default '1'")
    private int amount;

}
