package com.marondal.marondalgram.comment.service;

import com.marondal.marondalgram.comment.domain.Comment;
import com.marondal.marondalgram.comment.repository.CommentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public boolean createComment(long postId, long userId, String contents) {

        Comment comment = Comment.builder()
                .postId(postId)
                .userId(userId)
                .contents(contents)
                .build();

        try{
            commentRepository.save(comment);
        } catch(DataAccessException e) {
            return false;
        }

        return true;

    }

    public List<Comment> getCommentListByPostId(long postId) {
        return commentRepository.findByPostId(postId);
    }
}
