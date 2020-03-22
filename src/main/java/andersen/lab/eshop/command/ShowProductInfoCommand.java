package andersen.lab.eshop.command;

import andersen.lab.eshop.service.ProductService;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class ShowProductInfoCommand implements ICommand{

    private final ProductService productService;

    private final Scanner scanner;

    public ShowProductInfoCommand(ProductService productService, Scanner scanner) {
        this.scanner = scanner;
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.println("Введите ID продукта");
        while (true) {
            String id = scanner.nextLine();
            if(StringUtils.isNumeric(id)) {
                System.out.println(productService.getProductInfo(Long.parseLong(id)));
            }else{
                System.out.println("Неверный формат ID, должен быть числовым");
            }
        }
    }

}
