package andersen.lab.eshop.service.impl;

import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.exception.EntityNotFoundException;
import andersen.lab.eshop.repository.jdbc.ProductRepository;
import andersen.lab.eshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProductList() {
        return ProductRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return ProductRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public String getProductInfo(Long id) {
        StringBuilder sb = new StringBuilder();
        Product product = ProductRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        sb.append("id: ").append(product.getId()).append("\n")
                .append("name: ").append(product.getName()).append("\n")
                .append("category: ").append(product.getCategory()).append("\n")
                .append("price: ").append(product.getPrice()).append("\n");
        return sb.toString();
    }
}
