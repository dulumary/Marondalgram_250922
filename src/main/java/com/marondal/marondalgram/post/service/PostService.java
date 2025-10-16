package com.marondal.marondalgram.post.service;

import com.marondal.marondalgram.comment.domain.Comment;
import com.marondal.marondalgram.comment.dto.CommentDto;
import com.marondal.marondalgram.comment.service.CommentService;
import com.marondal.marondalgram.common.FileManager;
import com.marondal.marondalgram.like.service.LikeService;
import com.marondal.marondalgram.post.domain.Post;
import com.marondal.marondalgram.post.dto.PostDto;
import com.marondal.marondalgram.post.repository.PostRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 필수 멤버변수에 대한 초기화 생성자
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeService likeService;
    private final CommentService commentService;

//    public PostService(PostRepository postRepository, UserService userService, LikeService likeService, CommentService commentService) {
//        this.postRepository = postRepository;
//        this.userService = userService;
//        this.likeService = likeService;
//        this.commentService = commentService;
//    }

    public boolean createPost(long userId, String contents, MultipartFile file) {

        String imagePath = FileManager.saveFile(userId, file);

        Post post = Post.builder()
                .userId(userId)
                .contents(contents)
                .imagePath(imagePath)
                .build();

        try {
            postRepository.save(post);
        } catch(DataAccessException e) {
            return false;
        }

        return true;

    }

    public List<PostDto> getPostList(long userId) {

        List<Post> postList = postRepository.findAll(Sort.by("id").descending());

        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post:postList) {

            User user = userService.getUserById(post.getUserId());

            int likeCount = likeService.getLikeCountByPostId(post.getId());
            boolean isLike = likeService.isLikeByPostIdAndUserId(post.getId(), userId);

            List<CommentDto> commentList = commentService.getCommentListByPostId(post.getId());

            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .userId(post.getUserId())
                    .contents(post.getContents())
                    .loginId(user.getLoginId())
                    .imagePath(post.getImagePath())
                    .likeCount(likeCount)
                    .commentList(commentList)
                    .isLike(isLike)
                    .build();

            postDtoList.add(postDto);
        }

        return postDtoList;


    }

    @Transactional
    public boolean deletePost(long id, long userId) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isPresent()) {

            Post post = optionalPost.get();

            if(post.getUserId() != userId) {
                return false;
            }

            FileManager.removeFile(post.getImagePath());
            likeService.deleteByPostId(post.getId());
            commentService.deleteByPostId(post.getId());
            postRepository.delete(post);
        } else {
            return false;
        }

        return true;

    }

}
