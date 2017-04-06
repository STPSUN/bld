package main.sun.bld.server.servlet;

import main.sun.bld.server.api.model.ApiResponse;
import main.sun.bld.server.order.model.AddOrder;
import main.sun.bld.server.order.model.AddOrderData;
import main.sun.bld.server.order.model.AllOrders;
import main.sun.bld.server.order.model.Order;
import main.sun.bld.server.order.service.impl.OrderServiceImpl;
import main.sun.bld.server.product.service.ProductService.impl.ProductServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/3/9.
 */
@WebServlet(name = "OrdersServlet")
public class OrdersServlet extends HttpServlet {
    private ProductServiceImpl productService = new ProductServiceImpl();
    private OrderServiceImpl orderService = new OrderServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = "null";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }

        if(action.equals("addOrders"))
        {
            doAddOrders(request, response);
        }
        if(action.equals("getAllOrders"))
        {
            doGetAllOrders(request, response);
        }
        if(action.equals("ordersListPC"))
        {
            doOrdersListPC(request, response);
        }
        if(action.equals("getAllOrdersByState"))
        {
            doGetAllOrdersByState(request, response);
        }
        if(action.equals("getOrdersByID"))
        {
            doGetOrdersByID(request, response);
        }
        if(action.equals("modifyOrderState"))
        {
            doModifyOrderState(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doModifyOrderState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String orderID = request.getParameter("orderID");
        String orderState = request.getParameter("orderState");
        ApiResponse apiResponse = new ApiResponse();

        if(!orderID.isEmpty() && !orderState.isEmpty())
        {
            List<Order> orderList = orderService.getAllOrderByOrderID(orderID);
            if(orderList != null)
            {
                orderService.modifyOrderStateByOrderID(orderID, orderState);
                apiResponse.setCode("200");
                apiResponse.setMsg("success");
            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("该订单号不存在");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("modifyOrderState", json);
        request.getRequestDispatcher("/WEB-INF/pages/modifyOrderState.jsp").forward(request, response);
    }

    private void doGetAllOrdersByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String orderState = request.getParameter("orderState");
        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty() && !orderState.isEmpty())
        {
            List<AllOrders> allOrdersList = orderService.getAllOrdersByUserNameAndState(userName, orderState);
            if(allOrdersList != null)
            {
                apiResponse.setCode("200");
                apiResponse.setMsg("success");
                apiResponse.setData(allOrdersList);
            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("该用户或订单不存在");
            }

        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("getAllOrderState", json);
        request.getRequestDispatcher("/WEB-INF/pages/getAllOrderState.jsp").forward(request, response);
    }

    private void doGetOrdersByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ordersID = request.getParameter("ordersID");
        List<AllOrders> allOrdersList = orderService.getAllOrdersPC();
        if(!ordersID.isEmpty())
        {
            List<AllOrders> allOrdersList2 = new ArrayList<AllOrders>();
            try
            {
                allOrdersList2 = orderService.getAllOrdersByID(ordersID);
                JSONArray jsonArray = JSONArray.fromObject(allOrdersList2);
                System.out.println(jsonArray);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            if(allOrdersList2 != null)
            {
                request.setAttribute("ordersList", allOrdersList2);
                request.getRequestDispatcher("/jsp/ordersListPC.jsp").forward(request, response);
            }else
            {
                request.setAttribute("ordersList", allOrdersList);
                request.getRequestDispatcher("/jsp/ordersListPC.jsp").forward(request, response);
            }
        }


    }

    private void doOrdersListPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<AllOrders> allOrdersList = orderService.getAllOrdersPC();
        if(allOrdersList != null)
        {
//            JSONArray json = JSONArray.fromObject(allOrdersList);
//            System.out.println(json);
            request.setAttribute("ordersList", allOrdersList);
            request.getRequestDispatcher("/jsp/ordersListPC.jsp").forward(request, response);
        }

    }

    private void doGetAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty())
        {
            List<AllOrders> allOrdersList = orderService.getAllOrders(userName);
//            List<Order> orderList = orderService.getAllOrdersByUserName(userName);
            if(allOrdersList != null)
            {
                apiResponse.setCode("200");
                apiResponse.setMsg("success");
                apiResponse.setData(allOrdersList);
            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("该用户不存在");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("getAllOrders", json);
        request.getRequestDispatcher("/WEB-INF/pages/getAllOrders.jsp").forward(request, response);
    }

    private void doAddOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        System.out.println("into doAddOrders");
//        String userName = request.getParameter("userName");
//        System.out.println("userName:" + userName);
//        String id = request.getParameter("addressID");
//        System.out.println("id:" + id);
//        int addressID = Integer.parseInt(id);
//        String data1 = request.getParameter("data");
//        System.out.println("data:" + data1);
//        JSONArray data = JSONArray.fromObject(data1);
        String jsonStr = request.getParameter("jsonString");
        System.out.println("jsonString" + jsonStr);
        System.out.println("*************************************************");
        JSONObject json2 = JSONObject.fromObject(jsonStr);
        System.out.println("json" + json2);
        System.out.println("*************************************************");
        String userName = json2.getString("userName");
        System.out.println("name:" + userName);
        int addressID= json2.getInt("addressID");
        String data1 = json2.getString("data");
        JSONArray data = JSONArray.fromObject(data1);
//        System.out.println("************************************************************");
//        System.out.println("jsonString:" + jsonStr);
//        System.out.println("************************************************************");
//        System.out.println("json2:" + json2);
//        System.out.println("userName:" + userName);
//        System.out.println("addressID:" + addressID);
//        System.out.println("data:" + data);
//        System.out.println("************************************************************");

        List<AddOrderData> addOrderDataList = new ArrayList<AddOrderData>();
        AddOrder addOrder = new AddOrder();
        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty() && !data1.isEmpty())
        {
//            System.out.println("into if");
            for (int i = 0; i < data.size(); i++)
            {
//                System.out.println("into for");
                JSONObject jsonItem = data.getJSONObject(i);

                AddOrderData addOrderData = new AddOrderData();
                addOrderData.setProductID(jsonItem.getInt("productID"));
                addOrderData.setBuyNumber(jsonItem.getInt("buyNumber"));

//                System.out.println("productID:" + jsonItem.getInt("productID"));
//                System.out.println("buyNumber:" + jsonItem.getInt("buyNumber"));
//                System.out.println("buyNumber String:" + jsonItem.getString("buyNumber"));

                addOrderDataList.add(addOrderData);
            }

            System.out.println("addOrderDataList:" + addOrderDataList);

            addOrder.setUserName(userName);
            addOrder.setAddressID(addressID);
            addOrder.setAddOrderData(addOrderDataList);

            orderService.addOrder(addOrder);

            apiResponse.setCode("200");
            apiResponse.setMsg("success");
            apiResponse.setData(addOrder);
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("addOrders", json);
        request.getRequestDispatcher("/WEB-INF/pages/addOrders.jsp").forward(request, response);
    }
}
