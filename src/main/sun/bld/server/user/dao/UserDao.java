package main.sun.bld.server.user.dao;

import main.sun.bld.server.user.model.User;

import java.util.List;

/**
 * Created by SUN on 2017/1/20.
 */
public interface UserDao {
    public List<User> getAllUser();

    public boolean addUser(User user);

    public User findUserByUserName(String userName);

    public void updateUser(User user);
}
