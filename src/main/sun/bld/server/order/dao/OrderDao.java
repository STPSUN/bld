package main.sun.bld.server.order.dao;

import main.sun.bld.server.order.model.Order;

import java.util.List;

/**
 * Created by SUN on 2017/3/9.
 */
public interface OrderDao {
    public void addOrder(Order order);

    public List<Order> getAllOrdersByUserName(String userName);

    public List<Order> getAllOrders();

    public List<Order> getAllOrderByOrderID(String orderID);

    public Order getOrderByUserNameAndOrderID(String userName, String orderID);

    public List<Order> getAllOrdersByOrderIDNotRepetition();

    public void updateOrder(Order order);
}
