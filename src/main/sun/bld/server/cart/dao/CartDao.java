package main.sun.bld.server.cart.dao;

import main.sun.bld.server.cart.model.Cart;

import java.util.List;

/**
 * Created by SUN on 2017/3/2.
 */
public interface CartDao {
    public boolean addCart(Cart cart);

    public List<Cart> getAllCartByUserName(String userName);

    public boolean deleteCartByUserNameAndProductID(String userName, int productID);

    public void updateCart(Cart cart);

    public Cart findCartByUserNameAndProductID(String userName, int productID);

}
