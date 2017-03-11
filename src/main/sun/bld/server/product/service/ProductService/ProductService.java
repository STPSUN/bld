package main.sun.bld.server.product.service.ProductService;

import main.sun.bld.server.product.model.Product;
import main.sun.bld.server.product.model.ProductCategorys;

import java.util.List;

/**
 * Created by SUN on 2017/2/18.
 */
public interface ProductService {
    public void addProduct(Product product);

    public List<Product> getAllProduct();

    public List<Product> getAllProductByState(String productState);

    public List<Product> getAllProductByCategorys(String categorys);

    public List<ProductCategorys> getAllProductCategorys();

    public Product getProductByProductID(int productID);

    public Product getProductByProductName(String productName);
}
