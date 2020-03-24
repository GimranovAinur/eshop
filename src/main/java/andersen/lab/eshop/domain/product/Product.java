package andersen.lab.eshop.domain.product;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = -783352129159400073L;

    @Id
    @GeneratedValue
    private Long id;

    /** Наименование */
    @Column
    private String name;

    /** Стоимость */
    @Column
    private Double price;

    /** Категория товара */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Category category;

}
