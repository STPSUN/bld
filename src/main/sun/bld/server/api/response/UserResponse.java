package main.sun.bld.server.api.response;

import main.sun.bld.server.api.model.ApiResponse;
import main.sun.bld.server.user.model.User;
import main.sun.bld.server.user.service.UserService;
import main.sun.bld.server.user.service.impl.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by SUN on 2017/1/20.
 */
public class UserResponse {
    public JSONObject getUserResponse()
    {
        UserServiceImpl userService = new UserServiceImpl();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("200");
        apiResponse.setMsg("success");
        apiResponse.setData(userService.getUser());

//        JSONArray json = JSONArray.fromObject(apiResponse);
        JSONObject json = JSONObject.fromObject(apiResponse);

        return json;
    }

    public JSONObject userRegister()
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("200");
        apiResponse.setMsg("用户注册成功");

        JSONObject json = JSONObject.fromObject(apiResponse);

        return json;
    }
}
