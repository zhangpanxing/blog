package me.zbl.fullstack.mapper;

import me.zbl.fullstack.entity.User;
import me.zbl.fullstack.framework.mapper.IMyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author James
 */
public interface UserMapper extends IMyMapper<User> {


    User forOpenIdFindUser(@Param(value = "open_id")String openId);

    User byIdFindUser(@Param(value = "userId")Integer userId);

    /**
     * 查询微信小程序所有用户
     * @return
     */
    @Select({
            "SELECT",
             " u.id,u.avatar,u.gmt_create as gmtCreate ,u.nickname,u.`status`",
             "from user as u ",
             "WHERE u.open_id is not null"
    })
    List<User> getAllUser();
}