package me.zbl.fullstack.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.zbl.fullstack.entity.Article;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 博客列表视图
 *
 * @author James
 */
@Getter
@Setter
@AllArgsConstructor
public class PostView {

  private Integer id;
  private String title;
  private String description;
  private String dateTime;
  private String htmlMaterial;
  private String image;
  private String mdMaterial;
  private Integer isDelete;
  private Integer isComment;

  private Integer CommentCount;
  private Integer likeCount;

  private Boolean isFollow;



  /**
   * 拷贝构造方法
   *
   * @param article 文章
   */
  public PostView(Article article) {
    id = article.getId();
    title = article.getTitle();
    description = article.getIntroduction();
    dateTime = DateFormatUtils.format(article.getGmtCreate(), "yyyy-MM-dd HH:mm");
    htmlMaterial = article.getHtmlMaterial();
    image = article.getImage();
    mdMaterial = article.getMdMaterial();
    isDelete = article.getIsDelete();
    isComment = article.getIsComment();
  }

  public PostView(){}





}
