package main.sun.bld.server.user.service;

import main.sun.bld.server.user.model.User;

import java.util.List;

/**
 * Created by SUN on 2017/1/20.
 */
public interface UserService {
    public List<User> getUser();

    public void addUser(User user);

    public User findUserByUserName(String userName);

    public void modifyUser(User user);
}
