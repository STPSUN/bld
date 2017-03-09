package main.sun.bld.server.user.service.impl;

import main.sun.bld.server.user.dao.impl.UserImpl;
import main.sun.bld.server.user.model.User;
import main.sun.bld.server.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/1/20.
 */
public class UserServiceImpl implements UserService{
    public List<User> getUser()
    {
        List<User> userList = new ArrayList<User>();
        UserImpl userImpl = new UserImpl();
        userList = userImpl.getAllUser();

        return userList;
    }

    public void addUser(User user)
    {
        UserImpl userImpl = new UserImpl();
        userImpl.addUser(user);
    }

    public User findUserByUserName(String userName)
    {
        UserImpl userImpl = new UserImpl();

        return userImpl.findUserByUserName(userName);
    }

    public void modifyUser(User user)
    {
        UserImpl user1 = new UserImpl();
        user1.updateUser(user);
    }
}
