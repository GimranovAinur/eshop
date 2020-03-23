package andersen.lab.eshop;

import andersen.lab.eshop.command.ICommand;
import andersen.lab.eshop.command.ShowProductInfoCommand;
import andersen.lab.eshop.command.ShowProductListCommand;
import andersen.lab.eshop.command.cart.AddProductCommand;
import andersen.lab.eshop.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EShopApplication implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ICommand command = getCommand(scanner);
        command.execute();
    }

    private ICommand getCommand(Scanner scanner) {
        String commands = "[1]. Показать все продукты\n" +
                "[2]. Показать информацию о продукте\n" +
                "[3]. Управление корзиной\n" +
                "[0]. Выйти";
        System.out.println("Что вы хотите сделать?(Введите номер команды)\n" + commands);
        while (true) {
            String command = scanner.nextLine();
            if(StringUtils.isNumeric(command)) {
                int commandNumber = Integer.parseInt(command);
                switch (commandNumber) {
                    case 1:
                        return new ShowProductListCommand(productService);
                    case 2:
                        return new ShowProductInfoCommand(productService, scanner);
                    case 3:
                        return openCartConsole(scanner);
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Такой команды не существует\n" + commands);
                }
            }
            System.out.println("Такой команды не существует\n" + commands);
        }
    }

    private ICommand openCartConsole(Scanner scanner) {
        String commands = "[1]. Добавить товар\n" +
                "[2]. Удалить товар\n" +
                "[3]. Показать все товары\n" +
                "[0]. Выйти";
        System.out.println("Что вы хотите сделать?(Введите номер команды)\n" + commands);
        while (true) {
            String command = scanner.nextLine();
            if(StringUtils.isNumeric(command)) {
                int commandNumber = Integer.parseInt(scanner.nextLine());
                switch (commandNumber) {
                    case 1:
                        return new AddProductCommand();
                }
            }

        }
    }

}
