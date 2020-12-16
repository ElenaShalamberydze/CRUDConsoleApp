package service;

import entity.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User selectUser(String email);

    List<User> selectAllUsers();

    void updateUser(User user, String email);

    void deleteUser(String email);
}
