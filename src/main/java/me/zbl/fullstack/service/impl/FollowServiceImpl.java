package me.zbl.fullstack.service.impl;

import me.zbl.fullstack.entity.Follow;
import me.zbl.fullstack.mapper.FollowMapper;
import me.zbl.fullstack.service.api.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FollowServiceImpl implements IFollowService {

    @Autowired
    FollowMapper FollowMapper;

    @Override
    public int insert(Follow Follow) {
        return FollowMapper.insert(Follow);
    }

    @Override
    public int delete(int id) {
        return FollowMapper.delete(id);
    }

    @Override
    public int update(Follow Follow) {
        return FollowMapper.update(Follow);
    }

    @Override
    public Follow load(int id) {
        return FollowMapper.load(id);
    }

    @Override
    public List<Follow> pageList(int offset, int pagesize) {
        return FollowMapper.pageList(offset,pagesize);
    }

    @Override
    public int pageListCount(int offset, int pagesize) {
        return FollowMapper.pageListCount(offset,pagesize);
    }

    @Override
    public List<Follow> findFollows(Integer blogId) {
        return FollowMapper.findFollows(blogId);
    }

    @Override
    public Boolean isFollow(Integer blogId, Integer userId) {
        return FollowMapper.isFollow(blogId,userId);
    }
}
