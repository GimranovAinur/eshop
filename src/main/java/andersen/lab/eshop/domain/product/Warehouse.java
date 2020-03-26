package andersen.lab.eshop.domain.product;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Warehouse {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /** Наличие */
    @Column
    private int stock;

    /** Единица измерения */
    @Column
    private String measure;

}

