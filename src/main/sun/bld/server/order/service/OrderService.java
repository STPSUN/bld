package main.sun.bld.server.order.service;

import main.sun.bld.server.order.model.AddOrder;
import main.sun.bld.server.order.model.Order;

import java.util.List;

/**
 * Created by SUN on 2017/3/9.
 */
public interface OrderService {
    public void addOrder(AddOrder addOrder);

    public List<Order> getAllOrdersByUserName(String userName);

    public Order getOrderByUserNameAndOrderID(String userName, String orderID);

}
