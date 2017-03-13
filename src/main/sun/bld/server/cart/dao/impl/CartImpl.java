package main.sun.bld.server.cart.dao.impl;

import main.sun.bld.server.cart.dao.CartDao;
import main.sun.bld.server.cart.model.Cart;
import main.sun.bld.server.common.ConnectionJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/3/2.
 */
public class CartImpl implements CartDao{
    public boolean addCart(Cart cart)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "insert into cart(categorys, price, repertory, product_detail, buy_number," +
                "product_name, product_img, user_name,product_id) values(?,?,?,?,?,?,?,?,?)";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cart.getCategorys());
            ps.setDouble(2, cart.getPrice());
            ps.setInt(3, cart.getProductNumber());
            ps.setString(4, cart.getProductDetail());
            ps.setInt(5, cart.getBuyNumber());
            ps.setString(6, cart.getProductName());
            ps.setString(7, cart.getProductImg());
            ps.setString(8, cart.getUserName());
            ps.setInt(9, cart.getProductID());

            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public List<Cart> getAllCartByUserName(String userName)
    {
        List<Cart> cartList = new ArrayList<Cart>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from cart where user_name = ?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Cart cart = new Cart();
//                cart.setUserName(userName);
                cart.setProductID(rs.getInt("product_id"));
                cart.setCategorys(rs.getString("categorys"));
                cart.setPrice(rs.getDouble("price"));
                cart.setProductNumber(rs.getInt("repertory"));
                cart.setProductDetail(rs.getString("product_detail"));
                cart.setBuyNumber(rs.getInt("buy_number"));
                cart.setProductName(rs.getString("product_name"));
                cart.setProductImg(rs.getString("product_img"));

                cartList.add(cart);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return cartList;
    }

    public boolean deleteCartByUserNameAndProductID(String userName, int productID)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "delete from cart where user_name = ? and product_id = ?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setInt(2, productID);
//            ps.executeUpdate();
//            ps.close();
            result = ps.executeUpdate() == 1 ? true : false;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public Cart findCartByUserNameAndProductID(String userName, int productID)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cart cart = null;
        String sql = "select * from cart where user_name=? and product_id=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setInt(2, productID);
            rs = ps.executeQuery();
            while (rs.next())
            {
                cart = new Cart();
                cart.setUserName(userName);
                cart.setProductID(productID);
                cart.setCategorys(rs.getString("categorys"));
                cart.setPrice(rs.getDouble("price"));
                cart.setProductNumber(rs.getInt("repertory"));
                cart.setProductDetail(rs.getString("product_detail"));
                cart.setBuyNumber(rs.getInt("buy_number"));
                cart.setProductName(rs.getString("product_name"));
                cart.setProductImg(rs.getString("product_img"));

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return cart;
    }

    public void updateCart(Cart cart)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "update cart set categorys=?, price=?, repertory=?, product_detail=?, buy_number=?," +
                "product_name=?, product_img=? where user_name=? and product_id=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cart.getCategorys());
            ps.setDouble(2, cart.getPrice());
            ps.setInt(3, cart.getProductNumber());
            ps.setString(4, cart.getProductDetail());
            ps.setInt(5, cart.getBuyNumber());
            ps.setString(6, cart.getProductName());
            ps.setString(7, cart.getProductImg());
            ps.setString(8, cart.getUserName());
            ps.setInt(9, cart.getProductID());

            ps.executeUpdate();
            ps.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
