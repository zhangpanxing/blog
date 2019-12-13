package me.zbl.fullstack.mapper;

import me.zbl.fullstack.entity.Article;
import me.zbl.fullstack.entity.Tag;
import me.zbl.fullstack.entity.vo.TagView;
import me.zbl.fullstack.framework.mapper.IMyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author James
 */
public interface TagMapper extends IMyMapper<Tag> {

  String COLUMN_LIST = "article.id,article.title,article.introduction,article.gmt_create AS gmtCreate,article.gmt_modified AS gmtModified";

  /**
   * 根据 tag 的 id 获取文章
   *
   * @param id tag id
   *
   * @return 文章集合
   */
  @Select({
                  "SELECT",
                  COLUMN_LIST,
                  "FROM article,tag,tag_article",
                  "WHERE article.id = tag_article.article_id AND tag.id = tag_article.tag_id",
                  "AND tag.id = #{id}"
          })
  List<Article> selectArticleByTagId(Integer id);

  /**
   * 获取所有 TagView
   * TagView 包括 tag 的名字/id 以及该 tag 对应的文章总数
   *
   * @return 文章集合
   */
  @Select({
                  "SELECT tag.id AS tagId,",
                  "tag.name AS tagName,",
                  "COUNT(tag_article.article_id) AS articleCount",
                  "FROM tag_article",
                  "LEFT JOIN tag",
                  "ON tag.id = tag_article.tag_id",
                  "GROUP BY tag_article.tag_id"
          })
  List<TagView> selectAllTagView();

  @Select({
                  "SELECT id,",
                  "`name` FROM tag WHERE `name` = #{tagName}"
          })
  Tag selectTagByName(String tagName);
}