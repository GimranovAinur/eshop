package andersen.lab.eshop.domain.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Категория товара.
 */
@Entity
@Table(name = "categories")
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 6448699468062622145L;

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    /** Продукты из категории */
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}
