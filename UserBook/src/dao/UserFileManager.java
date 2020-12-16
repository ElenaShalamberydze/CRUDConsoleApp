package dao;

import entity.PhoneField;
import entity.RoleField;
import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFileManager {

    private static final File file = new File("D:" + File.separator + "userBook.csv");

    private static final UserFileManager instance = new UserFileManager();

    public static UserFileManager getInstance() {
        return instance;
    }

    private UserFileManager() {
        if (!file.exists()) {
            try {
                file.createNewFile();

                User user1 = new User("Ivan", "Ivanov", "ivanov@mail.by",
                                        new ArrayList<>(Arrays.asList(new RoleField("admin"))),
                                        new ArrayList<>(Arrays.asList(new PhoneField("37529 1112233")))
                );
                User user2 = new User("Petr", "Sidorov", "sidorov@gmail.ru",
                                        new ArrayList<>(Arrays.asList(new RoleField("admin"),
                                                                        new RoleField("user"))),
                                        new ArrayList<>(Arrays.asList(new PhoneField("37544 8886655")))
                );
                User user3 = new User("Ivan", "Petrov", "petrov@c.com",
                                        new ArrayList<>(Arrays.asList(new RoleField("owner"),
                                                                        new RoleField("admin"),
                                                                        new RoleField("user"))),
                                        new ArrayList<>(Arrays.asList(new PhoneField("37533 1234567"),
                                                                        new PhoneField("37525 9876655")))
                );

                List<User> predefinedUsers = new ArrayList<>();
                predefinedUsers.add(user1);
                predefinedUsers.add(user2);
                predefinedUsers.add(user3);
                writeUsersToFile(predefinedUsers);

            } catch (IOException e) {
                System.out.println("Problems creating userBook file!");
                e.printStackTrace();
            }
        }
    }

    public void createUser(User user) {
        appendUserToFile(user);
        System.out.println("user created!");
    }

    public User selectUser(String email) {
        User foundUser = null;
        List<User> allUsers = readUsersFromFile();

        for (User user : allUsers) {
            if (email.equals(user.getEmail())) {
                foundUser = user;
            }
        }
        return foundUser;
    }

    public List<User> selectAllUsers() {
        return readUsersFromFile();
    }

    public void updateUser(User newUser, String email) {
        List<User> allUsers = readUsersFromFile();
        int index = -1;

        for (User user : allUsers) {
            if (email.equals(user.getEmail())) {
                index = allUsers.indexOf(user);
            }
        }
        allUsers.set(index, newUser);
        writeUsersToFile(allUsers);
        System.out.println("User data successfully updated!");
    }

    public void deleteUser(String email) {
        List<User> allUsers = readUsersFromFile();
        User userToDelete = null;
        for (User user : allUsers) {
            if (email.equals(user.getEmail())) {
                userToDelete = user;
            }
        }
        if (userToDelete == null) {
            System.out.println("No such user in userBook file.");
            return;
        }
        allUsers.remove(userToDelete);
        writeUsersToFile(allUsers);
        System.out.println("User successfully deleted!");
    }


    private void appendUserToFile(User user) {

        try(FileWriter writer = new FileWriter(file, true)) {

            writer.write(user.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Problems writing new user to userBook file!");
            e.printStackTrace();
        }
    }


    private List<User> readUsersFromFile() {
        List<User> allUsers = new ArrayList<>();

        try(FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader)) {

            String line = reader.readLine();
            while ((line != null) && (!"".equals(line))) {
                String[] fields = line.trim().split(", ");
                User user = new User();
                user.setName(fields[0].trim());
                user.setSurname(fields[1].trim());
                user.setEmail(fields[2].trim());

                String[] roleTitle = fields[3].split(" : ");
                List<RoleField> roles = new ArrayList<>(roleTitle.length);
                for (String title : roleTitle) {
                    roles.add(new RoleField(title));
                }
                user.setRoles(roles);

                String[] phoneTitle = fields[4].split(" : ");
                List<PhoneField> phones = new ArrayList<>(phoneTitle.length);
                for(String title : phoneTitle){
                    phones.add(new PhoneField(title));
                }
                user.setPhones(phones);

                allUsers.add(user);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Problems reading data from userBook file!");
            e.printStackTrace();
        }
        return allUsers;
    }


    private void writeUsersToFile(List<User> users) {
        try(FileWriter writer = new FileWriter(file)) {
            for (User user : users) {
                writer.write(user.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Problems updating userBook file!");
            e.printStackTrace();
        }
    }

}


