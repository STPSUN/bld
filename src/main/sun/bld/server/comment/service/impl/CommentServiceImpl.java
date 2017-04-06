package main.sun.bld.server.comment.service.impl;

import main.sun.bld.server.comment.dao.impl.CommentImpl;
import main.sun.bld.server.comment.model.Comment;
import main.sun.bld.server.comment.service.CommentService;

import java.util.List;

/**
 * Created by SUN on 2017/3/16.
 */
public class CommentServiceImpl implements CommentService{
    private CommentImpl commentImpl = new CommentImpl();
    public void addComment(Comment comment)
    {
        commentImpl.addComment(comment);
    }

    public List<Comment> getAllCommentByProductID(int productID)
    {
        List<Comment> commentList = commentImpl.getAllCommentByProductID(productID);

        return commentList;
    }

    public List<Comment> getAllComment()
    {
        List<Comment> commentList = commentImpl.getAllComment();

        return commentList;
    }
}
