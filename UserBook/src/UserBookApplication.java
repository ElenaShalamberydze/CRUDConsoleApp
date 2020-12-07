import java.util.Scanner;

public class UserBookApplication {
    public static void main(String[] args) {
        String input;

        Scanner scanner = new Scanner(System.in);
        UserCommand command = UserCommand.getInstance();

        System.out.println("Welcome to UserBookApplication!");

        do {
            System.out.println();
            System.out.println("Chose action: create; show user; show all; update; delete; exit");
            input = scanner.nextLine();
            switch(input){
                case "create": {
                        command.createUser(scanner);
                        break;
                    }
                case "show user": {
                        command.user(scanner);
                        break;
                    }
                case "show all": {
                        command.allUsers();
                        break;
                    }
                case "update": {
                        command.updateUser(scanner);
                        break;
                    }
                case "delete": {
                        command.deleteUser(scanner);
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
