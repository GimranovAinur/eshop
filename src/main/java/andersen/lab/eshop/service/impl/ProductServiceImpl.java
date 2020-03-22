package andersen.lab.eshop.service.impl;

import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.exception.EntityNotFoundException;
import andersen.lab.eshop.repository.ProductRepository;
import andersen.lab.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public String getProductInfo(Long id) {
        StringBuilder sb = new StringBuilder();
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        sb.append("id: ").append(product.getId()).append("\n")
                .append("name: ").append(product.getName()).append("\n")
                .append("category: ").append(product.getCategory()).append("\n")
                .append("price: ").append(product.getPrice()).append("\n")
                .append("stock: ").append(product.getStock()).append("\n")
                .append("measure: ").append(product.getMeasure());
        return sb.toString();
    }
}
