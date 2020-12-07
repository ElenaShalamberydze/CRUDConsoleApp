import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserCommand {

    private static UserFileManager fileManager = UserFileManager.getInstance();

    private static final UserCommand instance = new UserCommand();

    public static UserCommand getInstance(){
        return instance;
    }

    private UserCommand(){

    }


    public void createUser(Scanner scanner){
        String email;
        String[] roles;
        String[] phones;
        User user = new User();

        System.out.println("Enter user name.");
        user.setName(scanner.nextLine());

        System.out.println("Enter user surname.");
        user.setSurname(scanner.nextLine());

        do {
            System.out.println("Enter user email.");
            email = scanner.nextLine();
        } while (!UserValidator.isEmailValid(email));
        user.setEmail(email);

        do {
            System.out.println("Enter user roles (1-3 are available). Split them with ','");
            roles = scanner.nextLine().split(",");
        } while (!UserValidator.isRoleValid(roles));
        user.setRoles(roles);

        do {
            System.out.println("Enter user phones (1-3 are available) in '375** *******' format. Split them with ','");
            phones = scanner.nextLine().split(",");
        } while (!UserValidator.isPhoneValid(phones));
        user.setPhones(phones);

        fileManager.createUser(user);
    }


    public void allUsers(){
        List<User> users = fileManager.allUsers();
        if(users != null) {
            for (User user : users) {
                System.out.println(user.toString());
            }
        } else{
            System.out.println("No users in the list.");
        }
    }


    public void user(Scanner scanner){
        System.out.println("Enter surname of user you want to find:");
        User user = fileManager.user(scanner.nextLine());

        if(user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println(user.toString());
    }


    public void updateUser(Scanner scanner){
        String name;
        String surname;
        String email;
        String[] roles;
        String[] phones;

        System.out.println("Enter surname of user you want to update:");
        User oldUser = fileManager.user(scanner.nextLine());
        if (oldUser == null) {
            System.out.println("User not found.");
            return;
        }
        User newUser = new User();

        System.out.println("Enter new user name. Current name: " + oldUser.getName() +
                            " | Remain this field empty if you dont want to change it.");
        name = scanner.nextLine();
        if(!"".equals(name)) {
            newUser.setName(name);
        }else{
            newUser.setName(oldUser.getName());
        }

        System.out.println("Enter new user surname. Current surname: " + oldUser.getSurname() +
                            " | Remain this field empty if you dont want to change it.");
        surname = scanner.nextLine();
        if(!"".equals(surname)) {
            newUser.setSurname(surname);
        }else{
            newUser.setSurname(oldUser.getSurname());
        }

        do {
            System.out.println("Enter new user email. Current email: " + oldUser.getEmail() +
                                " | Remain this field empty if you dont want to change it.");
            email = scanner.nextLine();
            if(!"".equals(email)){
                newUser.setEmail(email);
            }else{
                newUser.setEmail(oldUser.getEmail());
            }
        } while (!UserValidator.isEmailValid(newUser.getEmail()));

        do {
            System.out.println("Enter new user roles (1-3 are available). Split them with ',' . " +
                                "Current roles: " + Arrays.toString(oldUser.getRoles()) +
                                " | Remain this field empty if you dont want to change it.");
            roles = scanner.nextLine().split(",");
            if(!roles[0].equals("")){
                newUser.setRoles(roles);
            }else{
                newUser.setRoles(oldUser.getRoles());
            }
        } while (!UserValidator.isRoleValid(newUser.getRoles()));

        do {
            System.out.println("Enter new user phones (1-3 are available) in '375** *******' format. " +
                                "Split them with ',' . Current phones: " + Arrays.toString(oldUser.getPhones()) +
                                " | Remain this field empty if you dont want to change it.");
            phones = scanner.nextLine().split(",");
            if(!phones[0].equals("")){
                newUser.setPhones(phones);
            }else{
                newUser.setPhones(oldUser.getPhones());
            }
        } while (!UserValidator.isPhoneValid(newUser.getPhones()));

        fileManager.updateUser(oldUser, newUser);
    }


    public void deleteUser(Scanner scanner){
        System.out.println("Enter surname of user you want to delete:");
        String surname = scanner.nextLine();
        fileManager.deleteUser(surname);
    }

}
