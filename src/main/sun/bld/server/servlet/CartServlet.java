package main.sun.bld.server.servlet;

import main.sun.bld.server.api.model.ApiResponse;
import main.sun.bld.server.cart.model.Cart;
import main.sun.bld.server.cart.service.impl.CartServiceImpl;
import main.sun.bld.server.product.model.Product;
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
 * Created by SUN on 2017/3/3.
 */
@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    private CartServiceImpl cartService = new CartServiceImpl();
    private ProductServiceImpl productService = new ProductServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = "null";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }

        if(action.equals("cartList"))
        {
            doCartLIst(request, response);
        }
        if(action.equals("addProductToCart"))
        {
            doAddProductToCart(request, response);
        }
        if(action.equals("deleteCart"))
        {
            doDeleteCart(request, response);
        }
        if(action.equals("modifyCart"))
        {
            doModifyCart(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doModifyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String id = request.getParameter("productID");
        int productID = Integer.parseInt(id);
        String num = request.getParameter("buyNumber");
        int buyNumber = Integer.parseInt(num);

        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty() && !id.isEmpty() && !num.isEmpty())
        {
            Cart cart = cartService.findCartByUserNameAndProductID(userName, productID);
            if(cart != null)
            {
                cart.setBuyNumber(buyNumber);
                cartService.modifyCart(cart);
                apiResponse.setCode("200");
                apiResponse.setMsg("success");
            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("未找到该物品");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("modifyCart", json);
        request.getRequestDispatcher("/WEB-INF/pages/modifyCart.jsp").forward(request, response);
    }

    private void doDeleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String id = request.getParameter("productID");
        int productID = Integer.parseInt(id);

        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty() && !id.isEmpty())
        {
            cartService.deleteCartByUserNameAndProductID(userName, productID);
            apiResponse.setCode("200");
            apiResponse.setMsg("success");

        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("deleteCart", json);
        request.getRequestDispatcher("/WEB-INF/pages/deleteCart.jsp").forward(request, response);
    }

    private void doAddProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String id = request.getParameter("productID");
        int productID = Integer.parseInt(id);
        String buyNumber = request.getParameter("buyNumber");
        int num = Integer.parseInt(buyNumber);

        ApiResponse apiResponse = new ApiResponse();

        if(!id.isEmpty())
        {
            Product product = productService.getProductByProductID(productID);
            String categorys = product.getCategorys();
            String productName = product.getProductName();
            double price = product.getPrice();
            int productNumber = product.getProductNumber();
            String productDetail = product.getProductDetail();
            String productImg = product.getProductImg();

            Cart cart = new Cart();
            cart.setProductID(productID);
            cart.setCategorys(categorys);
            cart.setPrice(price);
            cart.setProductNumber(productNumber);
            cart.setProductDetail(productDetail);
            cart.setBuyNumber(num);
            cart.setProductName(productName);
            cart.setProductImg(productImg);
            cart.setUserName(userName);

            cartService.addCart(cart);
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("false");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("addProductToCart", json);
        request.getRequestDispatcher("/WEB-INF/pages/addProductToCart.jsp").forward(request, response);
    }

    private void doCartLIst(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        List<Cart> cartList = new ArrayList<Cart>();

        ApiResponse apiResponse = new ApiResponse();
        if(!userName.isEmpty())
        {
            cartList = cartService.getAllCartByUserName(userName);
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
            apiResponse.setData(cartList);
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);

        request.setAttribute("cartList", json);
        request.getRequestDispatcher("/WEB-INF/pages/cartList.jsp").forward(request, response);
    }

}
