package main.sun.bld.server.comment.dao;

import main.sun.bld.server.comment.model.Comment;

import java.util.List;

/**
 * Created by SUN on 2017/3/16.
 */
public interface CommentDao {
    public void addComment(Comment comment);

    public List<Comment> getAllCommentByProductID(int productID);
}
