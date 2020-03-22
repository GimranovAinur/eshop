package andersen.lab.eshop.command;

import java.util.Scanner;

public class ShowCartConsoleCommand implements ICommand {

    private final Scanner scanner;

    public ShowCartConsoleCommand(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        int commandNumber = getCommandNumber();
    }

    private int getCommandNumber() {
        String commands = "[1]. Добавить товар\n" +
                "[2]. Удалить товар\n" +
                "[3]. Показать все товары\n" +
                "[0]. Выйти";
        System.out.println("Что вы хотите сделать?(Введите номер команды)\n" + commands);
        while (true) {
            int commandNumber = Integer.parseInt(scanner.nextLine());
            if (commandNumber == 1 || commandNumber == 2 || commandNumber == 3 || commandNumber == 0) {
                return commandNumber;
            } else {
                System.out.println(
                        "Такой команды не существует\n" + commands);
            }
        }
    }

}
