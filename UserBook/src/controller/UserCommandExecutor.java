package controller;

import entity.PhoneField;
import entity.RoleField;
import entity.User;
import service.UserService;
import service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserCommandExecutor {

    public static UserService userService = UserServiceImpl.getInstance();

    private static final UserCommandExecutor instance = new UserCommandExecutor();

    public static UserCommandExecutor getInstance(){
        return instance;
    }

    private UserCommandExecutor(){

    }

    public void createUser(Scanner scanner){
        String email;
        List<RoleField> roles;
        List<PhoneField> phones;
        User user = new User();

        System.out.println("Enter user name.");
        user.setName(scanner.nextLine().trim());

        System.out.println("Enter user surname.");
        user.setSurname(scanner.nextLine().trim());

        do {
            System.out.println("Enter user email.");
            email = scanner.nextLine().trim();
        } while (!UserValidator.isEmailValid(email));
        user.setEmail(email);

        do {
            System.out.println("Enter user roles (1-3 are available). Split them with ','");
            String[] roleTitle = scanner.nextLine().trim().split(",");
            roles = new ArrayList<>(roleTitle.length);
            for (String title : roleTitle) {
                roles.add(new RoleField(title.trim()));
            }
        } while (!UserValidator.isRoleValid(roles));
        user.setRoles(roles);

        do {
            System.out.println("Enter user phones (1-3 are available) in '375** *******' format. Split them with ','");
            String[] phoneTitle = scanner.nextLine().trim().split(",");
            phones = new ArrayList<>(phoneTitle.length);
            for(String title : phoneTitle){
                phones.add(new PhoneField(title.trim()));
            }
        } while (!UserValidator.isPhoneValid(phones));
        user.setPhones(phones);

        userService.createUser(user);
    }


    public void selectAllUsers(){
        List<User> users = userService.selectAllUsers();
        if(users != null) {
            for (User user : users) {
                System.out.println(user.toString());
            }
        } else{
            System.out.println("No users in the list.");
        }
    }


    public void selectUser(Scanner scanner){
        System.out.println("Enter email of user you want to find:");

        User user = userService.selectUser(scanner.nextLine().trim());

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
        List<RoleField> roles;
        List<PhoneField> phones;

        System.out.println("Enter email of user you want to update:");
        User oldUser = userService.selectUser(scanner.nextLine().trim());
        if (oldUser == null) {
            System.out.println("User not found.");
            return;
        }
        User user = new User();

        System.out.println("Enter new user name. Current name: " + oldUser.getName() +
                " | Remain this field empty if you dont want to change it.");
        name = scanner.nextLine().trim();
        if(!"".equals(name)) {
            user.setName(name);
        }else{
            user.setName(oldUser.getName());
        }

        System.out.println("Enter new user surname. Current surname: " + oldUser.getSurname() +
                " | Remain this field empty if you dont want to change it.");
        surname = scanner.nextLine().trim();
        if(!"".equals(surname)) {
            user.setSurname(surname);
        }else{
            user.setSurname(oldUser.getSurname());
        }

        do {
            System.out.println("Enter new user email. Current email: " + oldUser.getEmail() +
                    " | Remain this field empty if you dont want to change it.");
            email = scanner.nextLine().trim();
            if(!"".equals(email)){
                user.setEmail(email);
            }else{
                user.setEmail(oldUser.getEmail());
            }
        } while (!UserValidator.isEmailValid(user.getEmail()));

        do {
            System.out.println("Enter new user roles (1-3 are available). Split them with ',' . " +
                    "Current roles: " + oldUser.listToString(oldUser.getRoles()) +
                    " | Remain this field empty if you dont want to change it.");
            String[] roleTitle = scanner.nextLine().trim().split(",");
            roles = new ArrayList<>(roleTitle.length);
            for (String title : roleTitle) {
                roles.add(new RoleField(title.trim()));
            }
            if(!roles.get(0).getTitle().equals("")){
                user.setRoles(roles);
            }else{
                user.setRoles(oldUser.getRoles());
            }
        } while (!UserValidator.isRoleValid(user.getRoles()));

        do {
            System.out.println("Enter new user phones (1-3 are available) in '375** *******' format. " +
                    "Split them with ',' . Current phones: " + oldUser.listToString(oldUser.getPhones()) +
                    " | Remain this field empty if you dont want to change it.");
            String[] phoneTitle = scanner.nextLine().trim().split(",");
            phones = new ArrayList<>(phoneTitle.length);
            for(String title : phoneTitle){
                phones.add(new PhoneField(title.trim()));
            }
            if(!phones.get(0).getTitle().equals("")){
                user.setPhones(phones);
            }else{
                user.setPhones(oldUser.getPhones());
            }
        } while (!UserValidator.isPhoneValid(user.getPhones()));

        userService.updateUser(user, oldUser.getEmail());
    }


    public void deleteUser(Scanner scanner){
        System.out.println("Enter email of user you want to delete:");
        String surname = scanner.nextLine().trim();
        userService.deleteUser(surname);
    }

}

