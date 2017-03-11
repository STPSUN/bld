package main.sun.bld.server.servlet;

import main.sun.bld.server.address.model.Address;
import main.sun.bld.server.address.service.impl.AddressServiceImpl;
import main.sun.bld.server.api.model.ApiResponse;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by SUN on 2017/3/8.
 */
@WebServlet(name = "AddressServlet")
public class AddressServlet extends HttpServlet {
    private AddressServiceImpl addressService = new AddressServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "null";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }

        if("addAddress".equals(action))
        {
            doAddAddress(request, response);
        }
        if("getAllAddress".equals(action))
        {
            doGetAllAddress(request, response);
        }
        if("deleteAddress".equals(action))
        {
            doDeleteAddress(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doDeleteAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String id = request.getParameter("addressID");
        int addressID = Integer.parseInt(id);

        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty() && !id.isEmpty())
        {
            addressService.deleteAddressByUserNameAndAddressID(userName, addressID);
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("false");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("deleteAddress", json);
        request.getRequestDispatcher("/WEB-INF/pages/deleteAddress.jsp").forward(request, response);
    }

    private void doGetAllAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty())
        {
            List<Address> addressList = addressService.getAllAddressByUserName(userName);
            if(addressList != null)
            {
                apiResponse.setCode("200");
                apiResponse.setData(addressList);
                apiResponse.setMsg("success");
            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("该用户暂无数据");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("getAllAddress", json);
        request.getRequestDispatcher("/WEB-INF/pages/getAllAddress.jsp").forward(request, response);
    }

    private void doAddAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        String recipients = request.getParameter("recipients");

        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty() && !address.isEmpty() && !postcode.isEmpty() && !recipients.isEmpty())
        {
            Address address1 = new Address();
            address1.setUserName(userName);
            address1.setAddress(address);
            address1.setPostcode(postcode);
            address1.setRecipients(recipients);

            addressService.addAddress(address1);

            apiResponse.setCode("200");
            apiResponse.setMsg("success");



        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("addAddress", json);
        request.getRequestDispatcher("/WEB-INF/pages/addAddress.jsp").forward(request, response);
    }
}
