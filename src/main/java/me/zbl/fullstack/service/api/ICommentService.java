package me.zbl.fullstack.service.api;

import me.zbl.fullstack.entity.Comment;

import java.util.List;
import java.util.Map;

public interface ICommentService {
    /**
     * 新增
     */
    public int insert(Comment comment);

    /**
     * 删除
     */
    public int delete(int id);

    /**
     * 更新
     */
    public int update(Comment comment);

    /**
     * 根据主键 id 查询
     */
    public Comment load(int id);

    /**
     * 分页查询
     */
    public List<Comment> pageList(int offset, int pagesize);

    List<Comment> pageList(int blogId,int offset, int pagesize);

    /**
     * 根据博客ID查找评论
     * @param blogId
     * @return
     */
    public List<Comment> findComments(int blogId);
}
