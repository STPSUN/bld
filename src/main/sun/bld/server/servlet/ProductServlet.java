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
import java.io.PrintWriter;
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
        if(action.equals("productListPC"))
        {
            doProductListPC(request, response);
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
        if(action.equals("productByProductName"))
        {
            doGetProdutByProductName(request, response);
        }
        if(action.equals("toAddProduct"))
        {
            toAddProduct(request, response);
        }
        if(action.equals("addProduct"))
        {
            doAddProduct(request, response);
        }
        if(action.equals("productByLikeName"))
        {
            doGetProductByLikeName(request, response);
        }
        if(action.equals("productByLikeNamePC"))
        {
            doGetProductByLikeNamePC(request, response);
        }
        if(action.equals("deleteProductByID"))
        {
            doDeleteProductByID(request, response);
        }
        if(action.equals("toModifyProductByID"))
        {
            doToModifyProductByID(request, response);
        }
        if(action.equals("doModifyProduct"))
        {
            doModifyProduct(request, response);
        }
        if(action.equals("test"))
        {
            doTest(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    private void doModifyProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String id = request.getParameter("productID");
            int productID = Integer.parseInt(id);
            String categorys = request.getParameter("categorys");
            String productName = request.getParameter("productName");
            String p = request.getParameter("price");
            double price = Double.parseDouble(p);
            String num = request.getParameter("productNumber");
            int productNumber = Integer.parseInt(num);
            String productDetail = request.getParameter("productDetail");
            String productImg = request.getParameter("productImg");
            String productState = request.getParameter("productState");
            String categorysImg = request.getParameter("categorysImg");

            Product product = productService.getProductByProductID(productID);
            product.setCategorys(categorys);
            product.setProductName(productName);
            product.setPrice(price);
            product.setProductNumber(productNumber);
            product.setProductDetail(productDetail);
            product.setProductImg(productImg);
            product.setProductState(productState);
            product.setCategorysImg(categorysImg);

            productService.modifyProduct(product);

            List<Product> productList = productService.getAllProduct();
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/jsp/productLIstPC.jsp").forward(request, response);

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private void doToModifyProductByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String id = request.getParameter("productID");
            int productID = Integer.parseInt(id);
            if(!id.isEmpty())
            {
                Product product = productService.getProductByProductID(productID);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/jsp/modifyProduct.jsp").forward(request, response);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String test = request.getParameter("test");
        PrintWriter out = response.getWriter();

        JSONObject json = JSONObject.fromObject(test);
        out.println(json);

    }

    private void doDeleteProductByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String id = request.getParameter("productID");
            int productID = Integer.parseInt(id);
            if(!id.isEmpty())
            {
                productService.deleteProductByID(productID);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        List<Product> productList = productService.getAllProduct();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/jsp/productLIstPC.jsp").forward(request, response);

    }

    private void doGetProductByLikeName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String productName = request.getParameter("likeName");
        ApiResponse apiResponse = new ApiResponse();

        if (!productName.isEmpty())
        {
            List<Product> productList = productService.getAllProductByLikeName(productName);
            if(productList != null)
            {
                apiResponse.setCode("200");
                apiResponse.setMsg("success");
                apiResponse.setData(productList);
            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("未找到该商品");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("likeName", json);
        request.getRequestDispatcher("/WEB-INF/pages/findProductByLikeName.jsp").forward(request, response);
    }

    private void toAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/jsp/addProduct.jsp").forward(request, response);
    }

    private void doAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Product product = new Product();
        try
        {
            String categorys = request.getParameter("categorys");
            String productName = request.getParameter("productName");
            String p = request.getParameter("price");
            double price = Double.valueOf(p);
            String number = request.getParameter("productNumber");
            int productNumber = Integer.parseInt(number);
            String productDetail = request.getParameter("productDetail");
            String path = "http://116.62.37.49:8080/bld/img/";
            String productImg2 = request.getParameter("productImg");
            String productImg = path + productImg2 + ".jpg";
            String productState = request.getParameter("productState");
            String categorysImg2 = request.getParameter("categorysImg");
            String categorysImg = path + categorysImg2 + ".jpg";

            product.setCategorys(categorys);
            product.setProductName(productName);
            product.setPrice(price);
            product.setProductNumber(productNumber);
            product.setProductDetail(productDetail);
            product.setProductImg(productImg);
            product.setProductState(productState);
            product.setCategorysImg(categorysImg);

            productService.addProduct(product);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        List<Product> productList = new ArrayList<Product>();
        productList = productService.getAllProduct();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/jsp/productLIstPC.jsp").forward(request, response);

    }

    private void doGetProductByLikeNamePC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String productName = request.getParameter("productName");
        List<Product> productList = productService.getAllProductByLikeName(productName);

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/jsp/productLIstPC.jsp").forward(request, response);
    }

    private void doGetProdutByProductName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        ApiResponse apiResponse = new ApiResponse();

        if (!productName.isEmpty())
        {
            Product product = productService.getProductByProductName(productName);
            if(product != null)
            {
                apiResponse.setCode("200");
                apiResponse.setMsg("success");
                apiResponse.setData(product);
            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("未找到该商品");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("findProductByProductName", json);
        request.getRequestDispatcher("/WEB-INF/pages/findProductByProductName.jsp").forward(request, response);
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

    private void doProductListPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Product> productList = new ArrayList<Product>();

        productList = productService.getAllProduct();


        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/jsp/productLIstPC.jsp").forward(request, response);
    }

}
