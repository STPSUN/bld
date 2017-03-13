package main.sun.bld.server.order.service.impl;

import main.sun.bld.server.address.model.Address;
import main.sun.bld.server.address.service.impl.AddressServiceImpl;
import main.sun.bld.server.common.UUIDHexGenerator;
import main.sun.bld.server.order.dao.impl.OrderImpl;
import main.sun.bld.server.order.model.*;
import main.sun.bld.server.order.service.OrderService;
import main.sun.bld.server.product.model.Product;
import main.sun.bld.server.product.service.ProductService.impl.ProductServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public List<Order> getAllOrderByOrderID(String orderID)
    {
        List<Order> orderList = orderImpl.getAllOrderByOrderID(orderID);

        return orderList;
    }

    public List<Order> getAllOrderByOrderIDNotRepetition()
    {
        List<Order> orderList = orderImpl.getAllOrdersByOrderIDNotRepetition();

        return orderList;
    }

    public Order getOrderByUserNameAndOrderID(String userName, String orderID)
    {
        return orderImpl.getOrderByUserNameAndOrderID(userName, orderID);
    }

    public List<AllOrders> getAllOrders(String userName)
    {
        List<Order> orderList = new ArrayList<Order>();
//        orderList = orderService.getAllOrderByOrderIDNotRepetition();
        //获取不同的订单
        orderList = getAllOrderByOrderIDNotRepetition();

        AddressServiceImpl addressService = new AddressServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl();

        List<AllOrders> allOrdersList = new ArrayList<AllOrders>();
        //遍历不同的订单
        for(Order order : orderList)
        {
            if(order.getUserName().equals(userName))
            {
                List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
//            System.out.println("******************************************************");
//            System.out.println(order.getOrderID());
//            List<Order> orderList2 = orderService.getAllOrderByOrderID(order.getOrderID());
                //根据订单号，遍历出所有该订单号的订单
                List<Order> orderList2 = getAllOrderByOrderID(order.getOrderID());
                Address address = addressService.getAddressByAddressID(order.getAddressID());
                //遍历某一订单号的所有订单
                for(Order order2 : orderList2)
                {
                    //判断该订单是否是某一用户
//                    if(order2.getUserName().equals(userName))
//                    {
                    Product product = productService.getProductByProductID(order2.getProductID());
//                System.out.println(product.getProductID());
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setBuyNumber(order2.getBuyNumber());
                    orderProduct.setProductID(product.getProductID());
                    orderProduct.setProductName(product.getProductName());
                    orderProduct.setProductImg(product.getProductImg());
                    orderProduct.setPrice(product.getPrice());
                    orderProduct.setProductDetail(product.getProductDetail());

                    orderProductList.add(orderProduct);
//                JSONArray jsonArray2 = JSONArray.fromObject(orderProductList);
//                System.out.println("json2" + jsonArray2);


                }

                AllOrders orders = new AllOrders();
                orders.setOrderTime(order.getOrderTime());
                orders.setOrderID(order.getOrderID());
                orders.setRecipients(address.getRecipients());
                orders.setAddress(address.getAddress());
                orders.setOrderState(order.getOrderState());
                orders.setOrderProductList(orderProductList);

                allOrdersList.add(orders);
            }

//                    AllOrders orders = new AllOrders();
//                    orders.setOrderTime(order.getOrderTime());
//                    orders.setOrderID(order.getOrderID());
//                    orders.setRecipients(address.getRecipients());
//                    orders.setAddress(address.getAddress());
//                    orders.setOrderState(order.getOrderState());
//                    orders.setOrderProductList(orderProductList);
//
//                    allOrdersList.add(orders);
//                    }
//                System.out.println(order2.getProductID());

            }

        return allOrdersList;
    }

}
