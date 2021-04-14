package me.zbl.fullstack.entity.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zbl.fullstack.entity.Comment;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentWxData {

    private Integer id;


    private String gmtCreate;


    private Integer articleId;


    private Integer userId;

    private Integer type;

    private String content;

    private String nickName;

    private String avatarUrl;


}
