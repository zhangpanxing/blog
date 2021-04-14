package me.zbl.fullstack.api.controller;

import me.zbl.fullstack.entity.*;
import me.zbl.fullstack.entity.dto.api.CommentWxData;
import me.zbl.fullstack.entity.dto.api.PostDto;
import me.zbl.fullstack.entity.dto.form.ArticleSearchForm;
import me.zbl.fullstack.entity.vo.PostView;
import me.zbl.fullstack.entity.vo.TagView;
import me.zbl.fullstack.service.api.IPostsService;
import me.zbl.fullstack.service.api.IResumeService;
import me.zbl.fullstack.service.api.ITagService;
import me.zbl.fullstack.service.impl.CommentServiceImpl;
import me.zbl.fullstack.service.impl.FollowServiceImpl;
import me.zbl.fullstack.service.impl.TagServiceImpl;
import me.zbl.fullstack.service.impl.UserServiceImpl;
import me.zbl.fullstack.utils.Message;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/wx_open")
public class WxOpenController  {

    @Autowired
    private IResumeService mResumeService;
    @Autowired
    private ITagService iTagService;
    @Autowired
    private IPostsService iPostsService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private FollowServiceImpl followService;

    /**
     *  小程序获取简历
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Resume test(){
        Resume resume = mResumeService.getResume();
        return resume;
    }

    /**
     * 小程序获取标签列表
     */
    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.GET)
    public List<TagView> tag(){
        List<TagView> tagViewList = iTagService.getAllTagView();
        return tagViewList;
    }

    /**
     * 小程序获取博客列表
     * @param tagId 标签ID
     * @param openId 用户OpenId
     * @param offset 第几页
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/blog",method = RequestMethod.POST)
    public PostDto blog(Integer tagId,String openId,Integer offset,String name){
        List<PostView> postViewList = null;
        Integer pageSize;
        if(offset == null){
            offset = 0;
        }
        pageSize = (offset+1)*3;

        User user = userService.forOpenIdFindUser(openId);
        Integer postCount;
        if(tagId == null){
            tagId=0;
        }

            postViewList = iPostsService.getPostListByTagIdAndByName(name,tagId,0,pageSize);
            postCount = iPostsService.getPostCount(name,tagId);


        for(PostView postView : postViewList){
            int commentSize  = commentService.findComments(postView.getId()).size();
            int likeSize = followService.findFollows(postView.getId()).size();
            if(user != null){
                boolean isFollow = followService.isFollow(postView.getId(),user.getId());
                postView.setIsFollow(isFollow);
            }
            postView.setCommentCount(commentSize);
            postView.setLikeCount(likeSize);
        }


        PostDto postDto = new PostDto(tagId,postCount,postViewList);
        return postDto;
    }

    /**
     * 根据输入框检索
     */
    @ResponseBody
    @RequestMapping(value = "/search_blog",method = RequestMethod.POST)
    public PostDto searchBlog(String name,String openId,Integer offset){
        List <PostView> postViewList;
        Integer pageSize;
        if(offset == null){
            offset = 0;
        }
        pageSize = (offset+1)*3;
        User user = userService.forOpenIdFindUser(openId);
        if(!StringUtils.isEmpty(name)){
            ArticleSearchForm form = new ArticleSearchForm(name);
            postViewList = iPostsService.getPostListByArticleCondition(form);

        }else{
            postViewList = iPostsService.getPostListByPage(0,pageSize);
        }
        for(PostView postView : postViewList){
            int commentSize  = commentService.findComments(postView.getId()).size();
            int likeSize = followService.findFollows(postView.getId()).size();
            postView.setCommentCount(commentSize);
            if(!openId.isEmpty()){
                boolean isFollow = followService.isFollow(postView.getId(),user.getId());
                postView.setIsFollow(isFollow);
            }
            postView.setLikeCount(likeSize);
        }
        PostDto postDto = new PostDto();
        postDto.setPostViews(postViewList);
        return postDto;
    }
    /**
     * 获取博客内容
     */
    @ResponseBody
    @RequestMapping(value = "/blog_content",method = RequestMethod.POST)
    public PostView blogContent(Integer blogId,String openId){
        User user = userService.forOpenIdFindUser(openId);
        if(blogId!=null){
            PostView postView = iPostsService.getPostView(blogId);
            int commentSize  = commentService.findComments(postView.getId()).size();
            int likeSize = followService.findFollows(postView.getId()).size();
            if(user != null){
                boolean isFollow = followService.isFollow(blogId,user.getId());
                postView.setIsFollow(isFollow);
            }
            postView.setCommentCount(commentSize);
            postView.setLikeCount(likeSize);
            return postView;
        }
        return null;
    }

    /**
     *  用户评论
     * @param commentText
     * @param openId
     * @param blogId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public List<CommentWxData> comment(String commentText, String openId,Integer blogId,Integer offset){
        User user = userService.forOpenIdFindUser(openId);
        Comment comment = new Comment();
        comment.setUserId(user.getId());
        comment.setContent(commentText);
        comment.setArticleId(blogId);
        int cont = commentService.insert(comment);
        if(cont == 0){
            return null;
        }
        Integer pageSize;
        if(offset == null){
            offset = 0;
        }
        pageSize = (offset+1)*5;
        List<Comment> commentList = commentService.pageList(blogId,0,pageSize);
        List<CommentWxData> commentWxDataList = new ArrayList<>();
        for(Comment comment1 :commentList){
            CommentWxData commentWxData = new CommentWxData();
            commentWxData.setArticleId(comment1.getArticleId());
            commentWxData.setUserId(comment1.getUserId());
            commentWxData.setContent(comment1.getContent());
            commentWxData.setGmtCreate(DateFormatUtils.format(comment1.getGmtCreate(), "yyyy-MM-dd HH:mm"));
            commentWxData.setType(comment1.getType());
            User user1 = userService.byIdFindUser(comment1.getUserId());
            if(user1 == null){
                continue;
            }
            commentWxData.setNickName(user1.getNickname());
            commentWxData.setAvatarUrl(user1.getAvatar());
            commentWxDataList.add(commentWxData);
        }
        return commentWxDataList;
    }

    @ResponseBody
    @RequestMapping(value = "/find_comments",method = RequestMethod.GET)
    public List<CommentWxData> findComments(Integer blogId,Integer offset){
        if(blogId == null){
            return null;
        }

        Integer pageSize;
        if(offset == null){
            offset = 0;
        }
        pageSize = (offset+1)*5;

        List<Comment> commentList = commentService.pageList(blogId,0,pageSize);
        List<CommentWxData> commentWxDataList = new ArrayList<>();
        for(Comment comment :commentList){
            CommentWxData commentWxData = new CommentWxData();
            commentWxData.setId(comment.getId());
            commentWxData.setArticleId(comment.getArticleId());
            commentWxData.setUserId(comment.getUserId());
            commentWxData.setContent(comment.getContent());
            commentWxData.setGmtCreate(DateFormatUtils.format(comment.getGmtCreate(), "yyyy-MM-dd HH:mm"));
            commentWxData.setType(comment.getType());
            User user = userService.byIdFindUser(comment.getUserId());
            if(user == null){
                continue;
            }
            commentWxData.setNickName(user.getNickname());
            commentWxData.setAvatarUrl(user.getAvatar());
            commentWxDataList.add(commentWxData);
        }



        return commentWxDataList;
    }
    @ResponseBody
    @RequestMapping(value = "/click_follow",method = RequestMethod.POST)
    public Message clickFollow(Integer blogId,String openId){
        User user = userService.forOpenIdFindUser(openId);
        Follow follow = new Follow();
        if(user != null){
            follow.setArticleId(blogId);
            follow.setUserId(user.getId());
            int Cont = followService.insert(follow);
            if(Cont > 0 ){
                return Message.success("点赞成功");
            }
        }
        return null;
    }




}
