package test;

import main.sun.bld.server.address.model.Address;
import main.sun.bld.server.address.service.impl.AddressServiceImpl;
import main.sun.bld.server.cart.model.Cart;
import main.sun.bld.server.cart.service.impl.CartServiceImpl;
import main.sun.bld.server.product.model.Product;
import main.sun.bld.server.product.model.ProductCategorys;
import main.sun.bld.server.product.service.ProductService.impl.ProductServiceImpl;
import main.sun.bld.server.user.model.User;
import main.sun.bld.server.user.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/1/20.
 */
public class testMain {
    private ProductServiceImpl productService = new ProductServiceImpl();
    private CartServiceImpl cartService = new CartServiceImpl();
    private AddressServiceImpl addressService = new AddressServiceImpl();
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
        test.getAllAddressByUserName();
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
