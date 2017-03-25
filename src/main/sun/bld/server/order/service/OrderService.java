package main.sun.bld.server.order.service;

import main.sun.bld.server.order.model.AddOrder;
import main.sun.bld.server.order.model.AllOrders;
import main.sun.bld.server.order.model.Order;

import java.util.List;

/**
 * Created by SUN on 2017/3/9.
 */
public interface OrderService {
    public void addOrder(AddOrder addOrder);

    public List<Order> getAllOrdersByUserName(String userName);

    public List<Order> getAllOrderByOrderID(String orderID);

    public Order getOrderByUserNameAndOrderID(String userName, String orderID);

    public List<Order> getAllOrderByOrderIDNotRepetition();

    public List<AllOrders> getAllOrders(String userName);

    public List<AllOrders> getAllOrdersByUserNameAndState(String userName, String orderState);

    public void modifyOrderStateByOrderID(String orderID, String orderState);

}
