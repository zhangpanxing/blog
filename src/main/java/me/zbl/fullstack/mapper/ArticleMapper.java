package me.zbl.fullstack.mapper;

import me.zbl.fullstack.entity.Article;
import me.zbl.fullstack.entity.dto.form.ArticleSearchForm;
import me.zbl.fullstack.framework.mapper.IMyMapper;
import me.zbl.fullstack.mapper.provider.ArticleSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author James
 */
public interface ArticleMapper extends IMyMapper<Article> {

  String COLUMN_LIST = "article.id,title,introduction,article.gmt_create AS gmtCreate,article.gmt_modified AS gmtModified,image,article.is_delete AS isDelete,article.is_comment AS isComment";

  @Select({
                  "SELECT",
                  COLUMN_LIST,
                  "FROM",
                  "article",
                  "WHERE article.is_delete = 0 ",
                  "ORDER BY article.gmt_create DESC"
          })
  List<Article> getPostViewAllArticles();

  /**
   * 通过 tag id 查找文章
   *
   * @param id tag id
   *
   * @return 符合条件的文章
   */
  @Select({
                  "SELECT",
                  COLUMN_LIST,
                  "FROM article",
                  "INNER JOIN tag_article",
                  "ON tag_article.article_id = article.id",
                  "AND tag_article.tag_id=#{id}",
                  "AND article.is_delete = 0 ",
                  "ORDER BY article.gmt_create DESC ",
                  "LIMIT #{offset}, #{pageSize}"
          })
  List<Article> getArticleListByTagId(@Param(value = "id") Integer id,@Param(value = "offset") int offset, @Param(value = "pageSize")int pageSize);


  List<Article> getArticleListByTagIdName(@Param(value = "name") String name,@Param(value = "tagId") Integer tagId,@Param(value = "offset") int offset, @Param(value = "pageSize")int pageSize);


  @Select({
          "SELECT",
          COLUMN_LIST,
          "FROM article WHERE ",
          "article.is_delete = 0 ",
          "ORDER BY article.gmt_create DESC  LIMIT #{offset}, #{pageSize}"
  })
  List<Article> getArticleListPage(@Param(value = "offset") int offset, @Param(value = "pageSize")int pageSize);

  /**
   * 通过条件查找文章
   *
   * @param form 条件表单
   *
   * @return 符合条件的文章
   */
  @SelectProvider(type = ArticleSqlProvider.class, method = "getArticleByCondition")
  List<Article> getArticleListByCondition(ArticleSearchForm form);


  @Select({
          "SELECT",
          COLUMN_LIST,
          "FROM article WHERE ",
          "article.title LIKE CONCAT ('% #{name} %')",
          "article.is_delete = 0 ",
          "ORDER BY article.gmt_create DESC  LIMIT #{offset}, #{pageSize}"
  })
  List<Article> getPostListByArticleConditionByPage(@Param(value = "name") String name, @Param(value = "offset") int offset, @Param(value = "pageSize")int pageSize);


  /**
   * 通过Id来查找
   */
  @Select({
          "SELECT",
          COLUMN_LIST,
          ",html_material AS htmlMaterial,md_material AS mdMaterial",

          "FROM article",
          "WHERE article.is_delete = 0 ",
          "AND article.id = #{blogId}"
  })
  Article getArticle(Integer blogId);

  /**
   * 获取博客数量
   * @param name
   * @param tagId
   * @return
   */

  int getArticleCount(@Param(value = "name") String name,@Param(value = "tagId") Integer tagId);
}