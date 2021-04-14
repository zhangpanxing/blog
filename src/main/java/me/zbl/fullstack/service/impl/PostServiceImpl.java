package me.zbl.fullstack.service.impl;

import me.zbl.fullstack.entity.Article;
import me.zbl.fullstack.entity.vo.PostView;
import me.zbl.fullstack.entity.dto.form.ArticleSearchForm;
import me.zbl.fullstack.mapper.ArticleMapper;
import me.zbl.fullstack.service.api.IPostsService;
import me.zbl.fullstack.service.base.BaseViewTransableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 博客业务实现类
 *
 * @author James
 */
@Service
public class PostServiceImpl extends BaseViewTransableService<Article, PostView> implements IPostsService {

  @Autowired
  ArticleMapper mPostMapper;

  @Override
  public List<PostView> getPostList() {
    List<Article> articles = mPostMapper.getPostViewAllArticles();
    List<PostView> postViewList = transEntityToView(articles);
    return postViewList;
  }

  @Override
  @Deprecated
  public List<PostView> getPostListByDate(Date start, Date end) {
    return null;
  }

  @Override
  public List<PostView> getPostListByTagId(Integer tagId,Integer offset, Integer pageSize) {
    if(offset == null || pageSize == null){
      offset = 0;
      pageSize = 30;
    }
    List<Article> articlelist = mPostMapper.getArticleListByTagId(tagId,offset,pageSize);
    List<PostView> postViewList = transEntityToView(articlelist);
    return postViewList;
  }

  @Override
  public List<PostView> getPostListByTagIdAndByName(String name, Integer tagId, Integer offset, Integer pageSize) {
    if(offset == null || pageSize == null){
      offset = 0;
      pageSize = 30;
    }
    List<Article> articlelist = mPostMapper.getArticleListByTagIdName(name,tagId,offset,pageSize);
    List<PostView> postViewList = transEntityToView(articlelist);
    return postViewList;
  }


  @Override
  public List<PostView> getPostListByArticleConditionByPage(ArticleSearchForm form,Integer offset, Integer pageSize) {
    Article article = new Article();
    article.setTitle(form.getName());

    List<Article> articleList = mPostMapper.getPostListByArticleConditionByPage(form.getName(),offset,pageSize);
    return transEntityToView(articleList);
  }

  @Override
  public List<PostView> getPostListByArticleCondition(ArticleSearchForm form) {
    Article article = new Article();
    article.setTitle(form.getName());

    List<Article> articleList = mPostMapper.getArticleListByCondition(form);
    return transEntityToView(articleList);
  }


  @Override
  public List<PostView> getPostListByPage(int offset, int pageSize) {
    List<Article> articleList = mPostMapper.getArticleListPage(offset,pageSize);
    return transEntityToView(articleList);
  }

  @Override
  public PostView getPostView(Integer blogId) {
    Article article = mPostMapper.getArticle(blogId);
    PostView postView = new PostView(article);
    return postView;
  }

  @Override
  public Integer getPostCount(String name, Integer tagId) {
    return mPostMapper.getArticleCount(name,tagId);
  }

  @Override
  protected List<PostView> transEntityToView(List<Article> entityList) {
    List<PostView> postViewsList = new ArrayList<>();
    Iterator it = entityList.iterator();
    while (it.hasNext()) {
      Article article = (Article) it.next();
      PostView postView = new PostView(article);
      postViewsList.add(postView);
    }
    return postViewsList;
  }
}
