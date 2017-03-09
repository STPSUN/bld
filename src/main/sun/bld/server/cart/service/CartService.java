package main.sun.bld.server.cart.service;

import main.sun.bld.server.cart.model.Cart;

import java.util.List;

/**
 * Created by SUN on 2017/3/2.
 */
public interface CartService {
    public void addCart(Cart cart);

    public List<Cart> getAllCartByUserName(String userName);

    public void deleteCartByUserNameAndProductID(String userName, int productID);

    public Cart findCartByUserNameAndProductID(String userName, int productID);

    public void modifyCart(Cart cart);
}
