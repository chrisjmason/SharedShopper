package data.repos.login;

import utility.pojo.User;

public interface LoginRepositoryInterface {
    void registerUser(User user);
    void loginUser(User user);
}
