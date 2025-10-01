package com.marondal.marondalgram.post.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDto {

    private long id;
    private long userId;

    private String loginId;
    private String contents;
}
