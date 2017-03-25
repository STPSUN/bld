package main.sun.bld.server.comment.service;

import main.sun.bld.server.comment.model.Comment;

import java.util.List;

/**
 * Created by SUN on 2017/3/16.
 */
public interface CommentService {
    public void addComment(Comment comment);

    public List<Comment> getAllCommentByProductID(int productID);
}
