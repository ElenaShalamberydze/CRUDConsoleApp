package service;

import dao.UserFileManager;
import entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserFileManager fileManager = UserFileManager.getInstance();

    private static final UserService instance = new UserServiceImpl();

    public static UserService getInstance(){
        return instance;
    }

    private UserServiceImpl(){
    }


    @Override
    public void createUser(User user) {
        fileManager.createUser(user);
    }

    @Override
    public User selectUser(String email) {
        return fileManager.selectUser(email);
    }

    @Override
    public List<User> selectAllUsers() {
        return fileManager.selectAllUsers();
    }

    @Override
    public void updateUser(User user, String email) {
        fileManager.updateUser(user, email);
    }

    @Override
    public void deleteUser(String email) {
        fileManager.deleteUser(email);
    }

}

