package main.sun.bld.server.cart.service.impl;

import main.sun.bld.server.cart.dao.impl.CartImpl;
import main.sun.bld.server.cart.model.Cart;
import main.sun.bld.server.cart.service.CartService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/3/2.
 */
public class CartServiceImpl implements CartService{
    private CartImpl cartImpl = new CartImpl();
    public void addCart(Cart cart)
    {
        cartImpl.addCart(cart);
    }

    public List<Cart> getAllCartByUserName(String userName)
    {
        List<Cart> cartList = new ArrayList<Cart>();
        cartList = cartImpl.getAllCartByUserName(userName);

        return cartList;
    }

    public void deleteCartByUserNameAndProductID(String userName, int productID)
    {
        cartImpl.deleteCartByUserNameAndProductID(userName, productID);
    }

    public Cart findCartByUserNameAndProductID(String userName, int productID)
    {
        return cartImpl.findCartByUserNameAndProductID(userName, productID);
    }

    public void modifyCart(Cart cart)
    {
        cartImpl.updateCart(cart);
    }

}
