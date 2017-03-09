package main.sun.bld.server.servlet;

import main.sun.bld.server.api.model.ApiResponse;
import main.sun.bld.server.product.model.Product;
import main.sun.bld.server.product.model.ProductCategorys;
import main.sun.bld.server.product.service.ProductService.impl.ProductServiceImpl;
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
 * Created by SUN on 2017/2/18.
 */
@WebServlet(name = "ProductServlet")
public class ProductServlet extends HttpServlet {

    private ProductServiceImpl productService = new ProductServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = "null";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }

        if(action.equals("productList"))
        {
            doProductList(request, response);
        }
        if(action.equals("productHot"))
        {
            doProductHot(request, response);
        }
        if(action.equals("productCategorys"))
        {
            doProductCategorys(request, response);
        }
        if(action.equals("productByCategorys"))
        {
            doProductByCategorys(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    private void doProductByCategorys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String categorys = request.getParameter("categorys");
        List<Product> productList = new ArrayList<Product>();
        System.out.println("categorys:" + categorys);

        if(!categorys.isEmpty())
        {
            productList = productService.getAllProductByCategorys(categorys);

            System.out.println("productList:");
            for (int i = 0; i < productList.size(); i++)
            {
                System.out.println(productList.get(i).getCategorys());
            }

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
            apiResponse.setData(productList);

            JSONObject json = JSONObject.fromObject(apiResponse);
            System.out.println("data:");
            System.out.println(json);

            request.setAttribute("productByCategorys", json);
            request.getRequestDispatcher("/WEB-INF/pages/productByCategorys.jsp").forward(request, response);
        }
    }

    private void doProductCategorys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<ProductCategorys> productCategorysList = new ArrayList<ProductCategorys>();

        productCategorysList = productService.getAllProductCategorys();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("200");
        apiResponse.setMsg("success");
        apiResponse.setData(productCategorysList);

        JSONObject json = JSONObject.fromObject(apiResponse);

        request.setAttribute("productCategorys", json);
        request.getRequestDispatcher("/WEB-INF/pages/productCategorys.jsp").forward(request, response);
    }

    private void doProductHot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Product> productList = new ArrayList<Product>();

        productList = productService.getAllProductByState("1");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("200");
        apiResponse.setMsg("success");
        apiResponse.setData(productList);

        JSONObject json = JSONObject.fromObject(apiResponse);

        request.setAttribute("productHot", json);
        request.getRequestDispatcher("/WEB-INF/pages/productHot.jsp").forward(request, response);
    }

    private void doProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Product> productList = new ArrayList<Product>();

        productList = productService.getAllProduct();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("200");
        apiResponse.setMsg("success");
        apiResponse.setData(productList);

        JSONObject json= JSONObject.fromObject(apiResponse);

        request.setAttribute("productList", json);
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
