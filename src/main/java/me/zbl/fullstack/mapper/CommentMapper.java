package me.zbl.fullstack.mapper;

import me.zbl.fullstack.entity.Comment;
import me.zbl.fullstack.framework.mapper.IMyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CommentMapper  {

    /**
     * [新增]
     * @author 张
     * @date 2020/02/22
     **/
    int insertComment(Comment comment);

    /**
     * [刪除]
     * @author 张
     * @date 2020/02/22
     **/
    int delete(int id);

    /**
     * [更新]
     * @author 张
     * @date 2020/02/22
     **/
    int update(Comment comment);

    /**
     * [查询] 根据主键 id 查询
     * @author 张
     * @date 2020/02/22
     **/
    Comment load(int id);

    /**
     * [查询] 分页查询
     * @author 张
     * @date 2020/02/22
     **/
    List<Comment> pageList(@Param(value = "offset") int offset, @Param(value = "pageSize")int pageSize);

    /**
     * [查询] 分页查询 count
     * @author 张
     * @date 2020/02/22
     **/
    int pageListCount(int offset,int pagesize);


    List<Comment> findComments(@Param(value = "blogId") int blogId);


    List<Comment> byBlogPageList(@Param(value = "blogId")int blogId,  @Param(value = "offset")int offset, @Param(value = "pageSize")int pageSize);

}

