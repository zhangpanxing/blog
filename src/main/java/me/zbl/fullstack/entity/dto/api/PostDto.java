package me.zbl.fullstack.entity.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zbl.fullstack.entity.vo.PostView;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer tagId;
    private Integer postCount;
    private List<PostView> postViews;

}
