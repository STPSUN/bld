package test;

import main.sun.bld.server.address.model.Address;
import main.sun.bld.server.address.service.impl.AddressServiceImpl;
import main.sun.bld.server.cart.model.Cart;
import main.sun.bld.server.cart.service.impl.CartServiceImpl;
import main.sun.bld.server.comment.model.Comment;
import main.sun.bld.server.comment.service.impl.CommentServiceImpl;
import main.sun.bld.server.order.model.*;
import main.sun.bld.server.order.service.impl.OrderServiceImpl;
import main.sun.bld.server.product.model.Product;
import main.sun.bld.server.product.model.ProductCategorys;
import main.sun.bld.server.product.service.ProductService.impl.ProductServiceImpl;
import main.sun.bld.server.user.model.User;
import main.sun.bld.server.user.service.impl.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by SUN on 2017/1/20.
 */
public class testMain {
    private ProductServiceImpl productService = new ProductServiceImpl();
    private CartServiceImpl cartService = new CartServiceImpl();
    private AddressServiceImpl addressService = new AddressServiceImpl();
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private CommentServiceImpl commentService = new CommentServiceImpl();
    public static void main(String[] args)
    {
        System.out.println("hello");
        testMain test = new testMain();
//        test.modifyCart();
//        test.findCart();
//        test.deleteCart();
//        test.getProductByProductID();
//        test.getAllCartByUserName();
//        test.addCart();
//        test.getAllProductByCategorys();
//        test.getAllCategorys();
//        test.getAllProductByState();
//        test.addProduct();
//        test.modifyUser();
//        test.addAddress();
//        test.getAllAddressByUserName();
//        test.deleteAddress();
//        test.getProductByProductName();
//        test.addOrder();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(new Date()));
//        test.getAllOrders();
//        test.getOrders();
//        test.getAddressByAddressID();
//        test.getAllOrderByOrderIDNotRepetition();
//        test.getAllOrders3();
//        test.getAllOrdersByState();
//        test.modifyOrderState();
//        test.whileTest();
//        test.addComment();
//        test.getAllComment();
//        test.getAllProductLike();
//        test.deleteProductID();
        test.modifyProduct();
    }

    public void modifyProduct()
    {
        Product product = productService.getProductByProductID(1);
        product.setCategorys("aaa");

        productService.modifyProduct(product);
    }

    public void deleteProductID()
    {
        int id = 1;
        productService.deleteProductByID(id);
    }

    public void getAllProductLike()
    {
        String name = "麻";
        List<Product> productList = productService.getAllProductByLikeName(name);
        for(Product product : productList)
        {
            System.out.println(product.getProductName());
        }
    }

    public void getAllComment()
    {
        List<Comment> commentList = commentService.getAllCommentByProductID(3);

        for (Comment comment : commentList)
        {
            System.out.println(comment.getProductID() + " " + comment.getCommentContent());
        }
    }

    public void addComment()
    {
        Comment comment = new Comment();
        comment.setProductID(3);
        comment.setUserName("sun");
        comment.setCommentContent("bbb");
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String commentTime = df.format(new Date());
        comment.setCommentTime(commentTime);

        commentService.addComment(comment);
    }

    public void whileTest()
    {
        boolean result = true;
        while (true)
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("in:");
            String str = sc.next();
            if(str.equals("q"))
            {
                break;
            }

        }
    }

    public void modifyOrderState()
    {
        String orderID = "000000005ac85d1c015ac85d1c440000";
        String state = "1";
        orderService.modifyOrderStateByOrderID(orderID, state);
    }

    public void getAllOrdersByState()
    {
        String userName = "18459159891";
        String state = "2";
        List<AllOrders> allOrdersList = orderService.getAllOrdersByUserNameAndState(userName, state);
        JSONArray jsonArray = JSONArray.fromObject(allOrdersList);
        System.out.println(jsonArray);

    }

    public void getAllOrders3()
    {
        String userName = "18459159891";
        List<AllOrders> allOrdersList = orderService.getAllOrders(userName);
        JSONArray jsonArray = JSONArray.fromObject(allOrdersList);
        System.out.println(jsonArray);
    }

    public void getAllOrderByOrderID()
    {
    }

    public void getAllOrderByOrderIDNotRepetition()
    {
        List<Order> orderList = new ArrayList<Order>();
        orderList = orderService.getAllOrderByOrderIDNotRepetition();

        List<AllOrders> allOrdersList = new ArrayList<AllOrders>();
        for(Order order : orderList)
        {
            List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
            System.out.println("******************************************************");
//            System.out.println(order.getOrderID());
            List<Order> orderList2 = orderService.getAllOrderByOrderID(order.getOrderID());
            Address address = addressService.getAddressByAddressID(order.getAddressID());
            for(Order order2 : orderList2)
            {
//                System.out.println(order2.getProductID());
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
                JSONArray jsonArray2 = JSONArray.fromObject(orderProductList);
                System.out.println("json2" + jsonArray2);
            }
            AllOrders orders = new AllOrders();
            orders.setOrderTime(order.getOrderTime());
            orders.setOrderID(order.getOrderID());
            orders.setRecipients(address.getRecipients());
            orders.setAddress(address.getAddress());
            orders.setOrderState(order.getOrderState());
            orders.setOrderProductList(orderProductList);

            allOrdersList.add(orders);

//            System.out.println("******************************************************");
        }

//        JSONObject json = JSONObject.fromObject(allOrdersList);
        JSONArray jsonArray = JSONArray.fromObject(allOrdersList);
        System.out.println(jsonArray);

    }

    public void getAddressByAddressID()
    {
        Address address = addressService.getAddressByAddressID(1);
        System.out.println(address.getAddress() + " " + address.getUserName());
    }

    public void getOrders()
    {
        String userName = "18459159891";
        String orderID = "000000005abde16d015abdef466b0003";
        Order order = orderService.getOrderByUserNameAndOrderID(userName, orderID);
        System.out.println(order.getOrderID() + " " + order.getOrderState());
    }

    public void getAllOrders2()
    {

    }

    public void getAllOrders()
    {
        List<Order> orderList = orderService.getAllOrdersByUserName("18459159891");
        List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
        List<AllOrders> allOrdersList = new ArrayList<AllOrders>();
        String orderIDTest = "";
        String orderID = "";
        String userName = "";
        int productID = 0;
        int buyNumber = 0;
        String address = "";
        int addressID = 0;
        for(int i = 0; i < orderList.size(); i++)
        {
            orderID = orderList.get(i).getOrderID();
            userName = orderList.get(i).getUserName();
            productID = orderList.get(i).getProductID();
            buyNumber = orderList.get(i).getBuyNumber();
            addressID = orderList.get(i).getAddressID();
            String orderState = orderList.get(i).getOrderState();
            String orderTime = orderList.get(i).getOrderTime();
            if(orderID.equals(orderIDTest))
            {


            }else
            {
                Product product = productService.getProductByProductID(productID);
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setBuyNumber(buyNumber);
                orderProduct.setPrice(product.getPrice());
                orderProduct.setProductDetail(product.getProductDetail());
                orderProduct.setProductID(product.getProductID());
                orderProduct.setProductImg(product.getProductImg());
                orderProduct.setProductName(product.getProductName());

                orderProductList.add(orderProduct);

                Address address1 = addressService.getAddressByAddressID(addressID);
                address = address1.getAddress();
                String recipients = address1.getRecipients();

                AllOrders allOrders = new AllOrders();
                allOrders.setAddress(address);
                allOrders.setRecipients(recipients);
                allOrders.setOrderID(orderID);
                allOrders.setOrderState(orderState);
                allOrders.setOrderTime(orderTime);
                allOrders.setOrderProductList(orderProductList);

                allOrdersList.add(allOrders);
            }
        }
    }

    public void addOrder()
    {
        AddOrder addOrder = new AddOrder();
        List<AddOrderData> addOrderDataList = new ArrayList<AddOrderData>();
        for(int i = 0; i < 2; i++)
        {
            AddOrderData addOrderData = new AddOrderData();
            addOrderData.setBuyNumber(i);
            addOrderData.setProductID(i);
            addOrderDataList.add(addOrderData);
        }
        addOrder.setUserName("18850705207");
        addOrder.setAddressID(13);
        addOrder.setAddOrderData(addOrderDataList);

//        JSONArray array = JSONArray.fromObject(addOrderDataList);
//        for (int i = 0; i < array.size(); i++)
//        {
//            AddOrderData addOrderData = new AddOrderData();
//        }
//        addOrder.setAddOrderData(array);

        JSONObject json = JSONObject.fromObject(addOrder);
        System.out.println(json);
        String userName = json.getString("userName");
        int addressID = json.getInt("addressID");
        JSONArray data = json.getJSONArray("addOrderData");
        String test = data.toString();
        JSONArray data2 = JSONArray.fromObject(test);
//        System.out.println(data2);
//        for(int i = 0; i < data2.size(); i++)
//        {
//            JSONObject jsonItem = data2.getJSONObject(i);
//            System.out.println(jsonItem.getInt("productID"));
//        }

//        System.out.println(array);
//        AddOrder addOrder2 = new AddOrder();
//        List<AddOrderData> addOrderDataList2 = new ArrayList<AddOrderData>();
//        try
//        {
//            JSONArray data = json.getJSONArray("addOrderData");
//            for(int i = 0; i < data.size(); i++)
//            {
//                JSONObject jsonItem = data.getJSONObject(i);
//
//                AddOrderData addOrderData = new AddOrderData();
//                addOrderData.setProductID(jsonItem.getInt("productID"));
//                addOrderData.setBuyNumber(jsonItem.getInt("buyNumber"));
//                addOrderDataList2.add(addOrderData);
//            }
//        }catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
//
//        addOrder2.setUserName(userName);
//        addOrder2.setAddressID(addressID);
//        addOrder2.setAddOrderData(addOrderDataList2);
//
//        orderService.addOrder(addOrder2);

    }

    public void getProductByProductName()
    {
        Product product = productService.getProductByProductName("中华");
        System.out.println(product.getProductID() + " " + product.getProductName());
    }


    public void deleteAddress()
    {
        addressService.deleteAddressByUserNameAndAddressID("1", 2);
    }

    public void getAllAddressByUserName()
    {
        List<Address> addressList = addressService.getAllAddressByUserName("332");
        for(int i = 0; i < addressList.size(); i++)
        {
            System.out.println(addressList.get(i).getAddress() + " " + addressList.get(i).getAddressID());
        }
    }

    public void addAddress()
    {
        Address address = new Address();
        address.setUserName("332");
        address.setAddress("ada");
        address.setPostcode("832");
        address.setRecipients("sun");

        addressService.addAddress(address);
    }

    public void modifyCart()
    {
        Cart cart = cartService.findCartByUserNameAndProductID("18459159891", 12);
        cart.setBuyNumber(5);
        cartService.modifyCart(cart);

    }

    public void findCart()
    {
        Cart cart = cartService.findCartByUserNameAndProductID("18459159891", 12);
        System.out.println(cart.getProductID() + " " + cart.getBuyNumber());
    }

    public void deleteCart()
    {
//        Integer id = 10;
        cartService.deleteCartByUserNameAndProductID("18459159891", 10);
    }

    public void getProductByProductID()
    {
        Product product = new Product();
        product = productService.getProductByProductID(2);

        System.out.println(product.getProductID() + " " + product.getCategorys());
    }

    public void getAllCartByUserName()
    {
        String userName = "18850705207";
        List<Cart> cartList = new ArrayList<Cart>();
        cartList = cartService.getAllCartByUserName(userName);
        for(int i = 0; i < cartList.size(); i++)
        {
            System.out.println(cartList.get(i).getProductNumber() + " " + cartList.get(i).getCategorys() + " " +cartList.get(i).getProductID());
        }
    }


    public void addCart()
    {
        Cart cart = new Cart();
        cart.setCategorys("a");
        cart.setBuyNumber(3);
        cart.setPrice(20.0);
        cart.setUserName("18850705207");

        CartServiceImpl cartService = new CartServiceImpl();
        cartService.addCart(cart);
    }

    public void getAllProductByCategorys()
    {
        List<Product> productList = new ArrayList<Product>();
        String categorys = "烟";
        productList = productService.getAllProductByCategorys(categorys);

        for(int i = 0; i < productList.size(); i++)
        {
            System.out.println(productList.get(i).getProductID() + " " + productList.get(i).getCategorys() + " " + productList.get(i).getProductName());
        }
    }

    public void getAllCategorys()
    {
        List<ProductCategorys> productCategorysList = new ArrayList<ProductCategorys>();
        productCategorysList = productService.getAllProductCategorys();

        for(int i=0; i < productCategorysList.size(); i++)
        {
            System.out.println(productCategorysList.get(i).getCategorys() + "  " + productCategorysList.get(i).getGetCategorysImg());
        }
    }

    public void getAllProductByState()
    {
        List<Product> productList = new ArrayList<Product>();
        productList = productService.getAllProductByState("1");
        for(int i = 0; i < productList.size(); i++)
        {
            System.out.println(productList.get(i).getProductID());
        }
    }

    public void getAllProduct()
    {
        List<Product> productList = new ArrayList<Product>();
        productList = productService.getAllProduct();
        for(int i = 0; i < productList.size(); i++)
        {
            System.out.println(productList.get(i).getProductID());
        }
    }

    public void addProduct()
    {
        Product product = new Product();
        product.setPrice(20.0);
        product.setProductNumber(2);
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.addProduct(product);
    }

    public void modifyUser()
    {
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();
        user = userService.findUserByUserName("4");
//        user.setUserName("4");
        user.setNickname("ss");
        user.setSex("a");
        user.setPassword("00");

        System.out.println(user.getUserName() + user.getPassword() + user.getNickname() + user.getSex());
        userService.modifyUser(user);
    }

}
