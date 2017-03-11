package main.sun.bld.server.product.service.ProductService.impl;

import main.sun.bld.server.product.dao.impl.ProductImpl;
import main.sun.bld.server.product.model.Product;
import main.sun.bld.server.product.model.ProductCategorys;
import main.sun.bld.server.product.service.ProductService.ProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/2/18.
 */
public class ProductServiceImpl implements ProductService {
    private ProductImpl productImpl = new ProductImpl();

    public void addProduct(Product product)
    {
        productImpl.addProduct(product);
    }

    public List<Product> getAllProduct()
    {
        List<Product> productList = new ArrayList<Product>();
        productList = productImpl.getAllProduct();

        return productList;
    }

    public List<ProductCategorys> getAllProductCategorys()
    {

        List<ProductCategorys> productCategorysList = new ArrayList<ProductCategorys>();
        productCategorysList = productImpl.getAllProductCategorys();

        return productCategorysList;
    }

    public List<Product> getAllProductByState(String productState)
    {
        List<Product> productList = new ArrayList<Product>();
        productList = productImpl.getAllProductByState(productState);

        return productList;
    }

    public List<Product> getAllProductByCategorys(String categorys)
    {
        List<Product> productList = new ArrayList<Product>();
        productList = productImpl.getAllProductByCategorys(categorys);

        return productList;
    }

    public Product getProductByProductID(int productID)
    {
        Product product = new Product();
        product = productImpl.getProductByProductID(productID);

        return product;
    }

    public Product getProductByProductName(String productName)
    {
        Product product = productImpl.getProductByProductName(productName);

        return product;
    }
}
