package andersen.lab.eshop.domain.product;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Категория товара.
 */
@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    /** Продукты из категории */
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
