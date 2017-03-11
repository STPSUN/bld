package main.sun.bld.server.order.service.impl;

import main.sun.bld.server.common.UUIDHexGenerator;
import main.sun.bld.server.order.dao.impl.OrderImpl;
import main.sun.bld.server.order.model.AddOrder;
import main.sun.bld.server.order.model.AddOrderData;
import main.sun.bld.server.order.model.Order;
import main.sun.bld.server.order.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SUN on 2017/3/9.
 */
public class OrderServiceImpl implements OrderService{
    private OrderImpl orderImpl = new OrderImpl();
    public void addOrder(AddOrder addOrder)
    {
        UUIDHexGenerator uuid= UUIDHexGenerator.getInstance();
        String orderID = uuid.generate();
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String orderTime = df.format(new Date());
        String orderState = "未发货";
        String userName = addOrder.getUserName();
        int adderssID = addOrder.getAddressID();

        List<AddOrderData> addOrderDataList = addOrder.getAddOrderData();
        for (int i = 0; i < addOrderDataList.size(); i++)
        {
            Order order = new Order();
            order.setOrderID(orderID);
            order.setUserName(userName);
            order.setOrderTime(orderTime);
            order.setOrderState(orderState);
            order.setAddressID(adderssID);
            order.setProductID(addOrderDataList.get(i).getProductID());
            order.setBuyNumber(addOrderDataList.get(i).getBuyNumber());

            orderImpl.addOrder(order);
        }

    }

    public List<Order> getAllOrdersByUserName(String userName)
    {
        List<Order> orderList = orderImpl.getAllOrdersByUserName(userName);

        return orderList;
    }

    public Order getOrderByUserNameAndOrderID(String userName, String orderID)
    {
        return orderImpl.getOrderByUserNameAndOrderID(userName, orderID);
    }
}
