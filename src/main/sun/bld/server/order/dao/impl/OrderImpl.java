package main.sun.bld.server.order.dao.impl;

import main.sun.bld.server.common.ConnectionJdbc;
import main.sun.bld.server.order.dao.OrderDao;
import main.sun.bld.server.order.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/3/9.
 */
public class OrderImpl implements OrderDao{
    public void addOrder(Order order)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "insert into orders(user_name,product_id,buy_number,order_time,order_state,address_id,order_id) values(?,?,?,?,?,?,?)";
//        String sql = "insert into order values(?,?,?,?,?,?,?,?,?,?,?)";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, order.getUserName());
            ps.setInt(2, order.getProductID());
            ps.setInt(3, order.getBuyNumber());
            ps.setString(4, order.getOrderTime());
            ps.setString(5, order.getOrderState());
            ps.setInt(6, order.getAddressID());
            ps.setString(7, order.getOrderID());

            ps.executeUpdate();
            ps.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrdersByUserName(String userName)
    {
        List<Order> orderList = new ArrayList<Order>();
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from orders where user_name=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Order order = new Order();
                order.setUserName(userName);
                order.setOrderID(rs.getString("order_id"));
                order.setProductID(rs.getInt("product_id"));
                order.setOrderTime(rs.getString("order_time"));
                order.setOrderState(rs.getString("order_state"));
                order.setBuyNumber(rs.getInt("buy_number"));
                order.setAddressID(rs.getInt("address_id"));

                orderList.add(order);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orderList;
    }

    public List<Order> getAllOrderByOrderID(String orderID)
    {
        List<Order> orderList = new ArrayList<Order>();
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from orders where order_id=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, orderID);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Order order = new Order();
                order.setOrderID(orderID);
                order.setProductID(rs.getInt("product_id"));
                order.setOrderTime(rs.getString("order_time"));
                order.setOrderState(rs.getString("order_state"));
                order.setBuyNumber(rs.getInt("buy_number"));
                order.setAddressID(rs.getInt("address_id"));
                order.setUserName(rs.getString("user_name"));

                orderList.add(order);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orderList;
    }



    public List<Order> getAllOrdersByOrderIDNotRepetition()
    {
        List<Order> orderList = new ArrayList<Order>();
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from orders group by order_id";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Order order = new Order();
                order.setAddressID(rs.getInt("address_id"));
                order.setProductID(rs.getInt("product_id"));
                order.setOrderState(rs.getString("order_state"));
                order.setOrderTime(rs.getString("order_time"));
                order.setUserName(rs.getString("user_name"));
                order.setBuyNumber(rs.getInt("buy_number"));
                order.setOrderID(rs.getString("order_id"));

                orderList.add(order);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orderList;
    }

    public Order getOrderByUserNameAndOrderID(String userName, String orderID)
    {
        Order order = null;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from orders where user_name=? and order_id=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, orderID);
            rs = ps.executeQuery();
            while (rs.next())
            {
                order = new Order();
                order.setUserName(userName);
                order.setOrderID(orderID);
                order.setProductID(rs.getInt("product_id"));
                order.setBuyNumber(rs.getInt("buy_number"));
                order.setOrderTime(rs.getString("order_time"));
                order.setOrderState(rs.getString("order_state"));
                order.setAddressID(rs.getInt("address_id"));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return order;
    }

    public void updateOrder(Order order)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "update orders set buy_number=?, order_state=?, address_id=? where order_id=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, order.getBuyNumber());
            ps.setString(2, order.getOrderState());
            ps.setInt(3, order.getAddressID());
            ps.setString(4, order.getOrderID());

            ps.executeUpdate();
            ps.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
