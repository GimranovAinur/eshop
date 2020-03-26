package andersen.lab.eshop.domain.order;

import andersen.lab.eshop.domain.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long id;

    private User user;

    private List<OrderItem> orderItems;

    private Double totalPrice;

    private Date date;

}
