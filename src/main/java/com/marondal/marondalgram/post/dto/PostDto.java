package com.marondal.marondalgram.post.dto;


import com.marondal.marondalgram.comment.domain.Comment;
import com.marondal.marondalgram.comment.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PostDto {

    private long id;
    private long userId;

    private String loginId;
    private String contents;
    private String imagePath;

    private int likeCount;
    private boolean isLike;

    private List<CommentDto> commentList;
}
