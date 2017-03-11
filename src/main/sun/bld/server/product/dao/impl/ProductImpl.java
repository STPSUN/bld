package main.sun.bld.server.product.dao.impl;

import main.sun.bld.server.common.ConnectionJdbc;
import main.sun.bld.server.product.dao.ProductDao;
import main.sun.bld.server.product.model.Product;
import main.sun.bld.server.product.model.ProductCategorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/2/18.
 */
public class ProductImpl implements ProductDao{
    public List<Product> getAllProduct()
    {
        List<Product> productList= new ArrayList<Product>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from product";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Product product = new Product();
                product.setProductID(rs.getInt("product_id"));
                product.setCategorys(rs.getString("categorys"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setProductNumber(rs.getInt("product_number"));
                product.setProductDetail(rs.getString("product_detail"));
                product.setProductImg(rs.getString("product_img"));
                product.setProductState(rs.getString("product_state"));
                product.setCategorysImg(rs.getString("categorys_img"));

                productList.add(product);

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return productList;
    }

    public List<ProductCategorys> getAllProductCategorys()
    {
        List<ProductCategorys> productCategoryses = new ArrayList<ProductCategorys>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select categorys,categorys_img from product group by categorys";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                ProductCategorys productCategorys = new ProductCategorys();
                productCategorys.setCategorys(rs.getString("categorys"));
                productCategorys.setGetCategorysImg(rs.getString("categorys_img"));

                productCategoryses.add(productCategorys);

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return productCategoryses;
    }

    public List<Product> getAllProductByCategorys(String categorys)
    {
        List<Product> productList= new ArrayList<Product>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from product where categorys= ?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, categorys);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Product product = new Product();
                product.setCategorys(categorys);
                product.setProductState(rs.getString("product_state"));
                product.setProductID(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setProductNumber(rs.getInt("product_number"));
                product.setProductDetail(rs.getString("product_detail"));
                product.setProductImg(rs.getString("product_img"));
                product.setCategorysImg(rs.getString("categorys_img"));

                productList.add(product);

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return productList;
    }

    public boolean addProduct(Product product)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "insert into product(categorys, product_name, price, product_number, " +
                "product_detail, product_img, product_state, categorys_img) values(?,?,?,?,?,?,?,?)";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, product.getCategorys());
            ps.setString(2, product.getProductName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getProductNumber());
            ps.setString(5, product.getProductDetail());
            ps.setString(6, product.getProductImg());
            ps.setString(7, product.getProductState());
            ps.setString(8, product.getCategorysImg());

            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public List<Product> getAllProductByState(String productState)
    {
        List<Product> productList= new ArrayList<Product>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from product where product_state = ?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, productState);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Product product = new Product();
                product.setProductState(productState);
                product.setProductID(rs.getInt("product_id"));
                product.setCategorys(rs.getString("categorys"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setProductNumber(rs.getInt("product_number"));
                product.setProductDetail(rs.getString("product_detail"));
                product.setProductImg(rs.getString("product_img"));
                product.setCategorysImg(rs.getString("categorys_img"));

                productList.add(product);

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return productList;
    }

    public Product getProductByProductID(int productID)
    {
        Product product = null;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from product where product_id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next())
            {
                product = new Product();
                product.setProductID(productID);
                product.setCategorys(rs.getString("categorys"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setProductNumber(rs.getInt("product_number"));
                product.setProductDetail(rs.getString("product_detail"));
                product.setProductImg(rs.getString("product_img"));
                product.setProductState(rs.getString("product_state"));
                product.setCategorysImg(rs.getString("categorys_img"));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return product;
    }

    public Product getProductByProductName(String productName)
    {
        Product product = null;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from product where product_name= ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, productName);
            rs = ps.executeQuery();
            while (rs.next())
            {
                product = new Product();
                product.setProductName(productName);
                product.setProductID(rs.getInt("product_id"));
                product.setCategorys(rs.getString("categorys"));
//                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setProductNumber(rs.getInt("product_number"));
                product.setProductDetail(rs.getString("product_detail"));
                product.setProductImg(rs.getString("product_img"));
                product.setProductState(rs.getString("product_state"));
                product.setCategorysImg(rs.getString("categorys_img"));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return product;
    }

}
































