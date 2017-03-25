package main.sun.bld.server.servlet;

import main.sun.bld.server.api.model.ApiResponse;
import main.sun.bld.server.api.response.UserResponse;
import main.sun.bld.server.user.model.User;
import main.sun.bld.server.user.service.impl.UserServiceImpl;
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
 * Created by SUN on 2017/1/20.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    private UserResponse userResponse = new UserResponse();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = "toLogin";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }
        if("register".equals(action))
        {
            doRegister(request, response);
        }
        if("doUserList".equals(action))
        {
            doUserList(request, response);
        }
        if("toUserList".equals(action))
        {
            toUserList(request, response);
        }
        if("test".equals(action))
        {
            Test(request, response);
        }
        if("login".equals(action))
        {
            doLogin(request, response);
        }
        if("retrieve".equals(action))
        {
            doRetrieve(request, response);
        }
        if("modifyUser".equals(action))
        {
            doModifyUser(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    private void doModifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String nickname = request.getParameter("nickname");
        String sex = request.getParameter("sex");

        ApiResponse apiResponse = new ApiResponse();

        if(!userName.isEmpty())
        {
            User user = userService.findUserByUserName(userName);
            if(user != null)
            {
                user.setNickname(nickname);
                user.setSex(sex);
                userService.modifyUser(user);

                apiResponse.setCode("200");
                apiResponse.setMsg("success");

            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("用户不存在");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("modifyUser", json);
        request.getRequestDispatcher("/WEB-INF/pages/modifyUser.jsp").forward(request, response);
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setNickname("nickname");
        user.setSex("man");

        ApiResponse apiResponse = new ApiResponse();
        if(user.getUserName() != null && userService.findUserByUserName(userName) == null)
        {
            userService.addUser(user);
            apiResponse.setCode("200");
            apiResponse.setMsg("用户注册成功");
            apiResponse.setData(user);
        }
        else if(userService.findUserByUserName(userName) != null)
        {
            apiResponse.setCode("203");
            apiResponse.setMsg("注册失败,该用户已注册");
        }
        else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("注册失败");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("register", json);
        request.getRequestDispatcher("/WEB-INF/pages/userRegister.jsp").forward(request, response);
    }

    private void doUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<User> userList = new ArrayList<User>();

        userList = userService.getUser();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("200");
        apiResponse.setMsg("success");
        apiResponse.setData(userList);

        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/jsp/user.jsp").forward(request, response);

    }

    private void toUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/pages/user.jsp").forward(request, response);
    }

    private void Test(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/pages/user.jsp").forward(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        ApiResponse apiResponse = new ApiResponse();
        if(userService.findUserByUserName(userName) != null)
        {
            User user = userService.findUserByUserName(userName);
            if(user != null)
            {
                if(user.getPassword().equals(password))
                {
                    apiResponse.setCode("200");
                    apiResponse.setMsg("success");
                    apiResponse.setData(user);
                 }else
                  {
                      apiResponse.setCode("202");
                      apiResponse.setMsg("密码错误");
                  }
            }
        }else
        {
            apiResponse.setCode("202");
            apiResponse.setMsg("用户不存在");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);

//        System.out.println("userName:" + userName);
//        System.out.println("password:" + password);
//        System.out.println("data:" + json);
        if(userName.equals("admin"))
        {
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }else
        {
            request.setAttribute("login", json);
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }

    private void doRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String password = request.getParameter("newPassword");
        ApiResponse apiResponse = new ApiResponse();
        if(userService.findUserByUserName(userName) != null)
         {
             User user = userService.findUserByUserName(userName);
             user.setPassword(password);
             userService.modifyUser(user);
             apiResponse.setCode("200");
             apiResponse.setMsg("成功修改密码");

        }else
        {
            apiResponse.setCode("202");
            apiResponse.setMsg("用户不存在");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("retrieve", json);
        request.getRequestDispatcher("/WEB-INF/pages/retrieve.jsp").forward(request, response);
    }

}






















