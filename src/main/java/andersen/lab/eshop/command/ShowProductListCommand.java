package andersen.lab.eshop.command;

import andersen.lab.eshop.domain.product.Product;
import andersen.lab.eshop.service.ProductService;

import java.util.List;

public class ShowProductListCommand implements ICommand {

    private ProductService productService;

    public ShowProductListCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        List<Product> products = productService.getProductList();
        for(Product product : products) {
            System.out.println(product.getId() + ") " + product.getName());
        }
    }

}
