package andersen.lab.eshop.domain.order;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long id;

    private List<OrderItem> orderItems;

    private Double totalPrice;

    private Date date;

}
