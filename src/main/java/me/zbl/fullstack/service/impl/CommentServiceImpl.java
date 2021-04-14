package me.zbl.fullstack.service.impl;

import me.zbl.fullstack.entity.Comment;
import me.zbl.fullstack.mapper.CommentMapper;
import me.zbl.fullstack.service.api.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public int insert(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public int delete(int id) {
        return commentMapper.delete(id);
    }

    @Override
    public int update(Comment comment) {
        return commentMapper.update(comment);
    }

    @Override
    public Comment load(int id) {
        return commentMapper.load(id);
    }

    @Override
    public List<Comment> pageList(int offset, int pageSize) {
        return commentMapper.pageList(offset,pageSize);
    }

    @Override
    public List<Comment> pageList(int blogId,  int offset, int pageSize) {
        return commentMapper.byBlogPageList(blogId,offset,pageSize);
    }

    @Override
    public List<Comment> findComments(int blogId) {
        return commentMapper.findComments(blogId);
    }
}
