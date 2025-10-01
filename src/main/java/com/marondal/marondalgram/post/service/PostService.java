package com.marondal.marondalgram.post.service;

import com.marondal.marondalgram.post.domain.Post;
import com.marondal.marondalgram.post.dto.PostDto;
import com.marondal.marondalgram.post.repository.PostRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public boolean createPost(long userId, String contents) {

        Post post = Post.builder()
                .userId(userId)
                .contents(contents)
                .build();

        try {
            postRepository.save(post);
        } catch(DataAccessException e) {
            return false;
        }

        return true;

    }

    public List<PostDto> getPostList() {

        List<Post> postList = postRepository.findAll(Sort.by("id").descending());

        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post:postList) {

            User user = userService.getUserById(post.getUserId());

            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .userId(post.getUserId())
                    .contents(post.getContents())
                    .loginId(user.getLoginId())
                    .build();

            postDtoList.add(postDto);
        }

        return postDtoList;


    }

}
