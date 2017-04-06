package main.sun.bld.server.servlet;

import main.sun.bld.server.api.model.ApiResponse;
import main.sun.bld.server.comment.model.Comment;
import main.sun.bld.server.comment.service.impl.CommentServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SUN on 2017/3/16.
 */
@WebServlet(name = "CommentServlet")
public class CommentServlet extends HttpServlet {
    private CommentServiceImpl commentService = new CommentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "null";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }
        if(action.equals("addComment"))
        {
            doAddComment(response, request);
        }
        if(action.equals("getAllComment"))
        {
            doGetAllComment(request, response);
        }
        if(action.equals("commentListPC"))
        {
            doCommentListPC(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doCommentListPC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            List<Comment> commentList = commentService.getAllComment();
            if(commentList != null)
            {
                request.setAttribute("commentList", commentList);
                request.getRequestDispatcher("/jsp/commentListPC.jsp").forward(request, response);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void doGetAllComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("productID");
        int productID = Integer.parseInt(id);
        ApiResponse apiResponse = new ApiResponse();

        if(!id.isEmpty())
        {
            List<Comment> commentList = commentService.getAllCommentByProductID(productID);
            if(commentList != null)
            {
                apiResponse.setCode("200");
                apiResponse.setMsg("success");
                apiResponse.setData(commentList);

            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("该商品暂无评论");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("getAllComment", json);
        request.getRequestDispatcher("/WEB-INF/pages/getAllComment.jsp").forward(request, response);
    }

    private void doAddComment(HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException
    {
        String id = request.getParameter("productID");
        int productID = Integer.parseInt(id);
        String userName = request.getParameter("userName");
        String commentContent = request.getParameter("commentContent");

        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        String commentTime = time;

        ApiResponse apiResponse = new ApiResponse();

        if(!id.isEmpty() && !userName.isEmpty() && !time.isEmpty() && !commentContent.isEmpty())
        {
            Comment comment = new Comment();
            comment.setProductID(productID);
            comment.setUserName(userName);
            comment.setCommentTime(commentTime);
            comment.setCommentContent(commentContent);

            commentService.addComment(comment);

            apiResponse.setCode("200");
            apiResponse.setMsg("success");
//            apiResponse.setData(comment);
        }else
        {
            apiResponse.setCode("200");
            apiResponse.setMsg("请求体参数不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        request.setAttribute("addComment", json);
        request.getRequestDispatcher("/WEB-INF/pages/addComment.jsp").forward(request, response);

    }

}
