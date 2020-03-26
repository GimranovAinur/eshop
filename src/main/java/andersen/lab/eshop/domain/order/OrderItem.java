package andersen.lab.eshop.domain.order;

import andersen.lab.eshop.domain.product.Product;
import lombok.Data;

@Data
public class OrderItem {

    private Long id;

    private Product product;

    private int amount;

}
