import controller.UserCommandExecutor;

import java.util.Scanner;

public class UserBookApplication {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);

        UserCommandExecutor commandExecutor = UserCommandExecutor.getInstance();

        System.out.println("Welcome to UserBookApplication!");

        do {
            System.out.println();
            System.out.println("Chose action: create; show user; show all; update; delete; exit");
            input = scanner.nextLine();
            switch (input) {
                case "create": {
                    commandExecutor.createUser(scanner);
                    break;
                }
                case "show user": {
                    commandExecutor.selectUser(scanner);
                    break;
                }
                case "show all": {
                    commandExecutor.selectAllUsers();
                    break;
                }
                case "update": {
                    commandExecutor.updateUser(scanner);
                    break;
                }
                case "delete": {
                    commandExecutor.deleteUser(scanner);
                    break;
                }

                case "exit": {
                    System.out.println("See you next time!");
                    break;
                }
                default: {
                    System.out.println("Unknown command! Please, try again.");
                }
            }

        } while (!"exit".equals(input));

    }
}

