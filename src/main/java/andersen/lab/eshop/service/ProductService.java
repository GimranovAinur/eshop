package andersen.lab.eshop.service;

import andersen.lab.eshop.domain.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductList();

    Product getProductById(Long id);

    String getProductInfo(Long id);

}
