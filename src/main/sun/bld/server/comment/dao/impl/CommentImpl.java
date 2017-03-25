package main.sun.bld.server.comment.dao.impl;

import main.sun.bld.server.comment.dao.CommentDao;
import main.sun.bld.server.comment.model.Comment;
import main.sun.bld.server.common.ConnectionJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/3/16.
 */
public class CommentImpl implements CommentDao{
    public void addComment(Comment comment)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "insert into comment(product_id,user_name,comment_time,comment_content) values(?,?,?,?)";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, comment.getProductID());
            ps.setString(2, comment.getUserName());
            ps.setString(3, comment.getCommentTime());
            ps.setString(4, comment.getCommentContent());

            ps.executeUpdate();
            ps.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public List<Comment> getAllCommentByProductID(int productID)
    {
        List<Comment> commentList = new ArrayList<Comment>();
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from comment where product_id=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Comment comment = new Comment();
                comment.setProductID(productID);
                comment.setUserName(rs.getString("user_name"));
                comment.setCommentTime(rs.getString("comment_time"));
                comment.setCommentContent(rs.getString("comment_content"));

                commentList.add(comment);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return commentList;
    }
}
