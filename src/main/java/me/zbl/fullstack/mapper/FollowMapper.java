package me.zbl.fullstack.mapper;


import me.zbl.fullstack.entity.Comment;
import me.zbl.fullstack.entity.Follow;
import me.zbl.fullstack.framework.mapper.IMyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Follow
 * @author 张
 * @date 2020/02/22
 */

public interface FollowMapper  {

    /**
     * [新增]
     * @author 张
     * @date 2020/02/22
     **/
    int insert(Follow Follow);

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
    int update(Follow Follow);

    /**
     * [查询] 根据主键 id 查询
     * @author 张
     * @date 2020/02/22
     **/
    Follow load(int id);

    /**
     * [查询] 分页查询
     * @author 张
     * @date 2020/02/22
     **/
    List<Follow> pageList(int offset, int pagesize);

    /**
     * [查询] 分页查询 count
     * @author 张
     * @date 2020/02/22
     **/
    int pageListCount(int offset,int pagesize);

    List<Follow> findFollows(@Param(value = "blogId") int blogId);


    Boolean isFollow(@Param(value = "blogId") int blogId,@Param(value = "userId")int userId);


}
