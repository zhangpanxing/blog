package me.zbl.fullstack.service.api;

import me.zbl.fullstack.entity.Follow;

import java.util.List;

public interface IFollowService {

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

    List<Follow> findFollows(Integer blogId);

    Boolean isFollow(Integer blogId,Integer userId);
}
