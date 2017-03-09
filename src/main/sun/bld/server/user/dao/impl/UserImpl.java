package main.sun.bld.server.user.dao.impl;

import main.sun.bld.server.common.ConnectionJdbc;
import main.sun.bld.server.user.dao.UserDao;
import main.sun.bld.server.user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/1/20.
 */
public class UserImpl implements UserDao{
    public List<User> getAllUser()
    {
        List<User> userList = new ArrayList<User>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from user";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                User user = new User();
                user.setUserName(rs.getString("user_name"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getString("sex"));
                userList.add(user);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return userList;
    }

    public boolean addUser(User user)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "INSERT INTO user(user_name, password, nickname, sex) VALUES(?, ?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getSex());
            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public User findUserByUserName(String userName)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        String sql = "select * from user where user_name = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next())
            {
                user = new User();
                user.setUserName(userName);
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getString("sex"));
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }

    public void updateUser(User user)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "update user set nickname=?, password=?, sex=? where user_name=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getUserName());

            ps.executeUpdate();
            ps.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}



















