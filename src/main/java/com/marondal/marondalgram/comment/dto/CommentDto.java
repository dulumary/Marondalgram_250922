package com.marondal.marondalgram.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDto {

    private long id;

    private long postId;
    private long userId;

    private String loginId;
    private String contents;

}
