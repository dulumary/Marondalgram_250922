package com.marondal.marondalgram.post;

import com.marondal.marondalgram.post.domain.Post;
import com.marondal.marondalgram.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/post")
@RestController
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/write-process")
    public Map<String, String> writePost(
            @RequestParam String contents
            , HttpSession session) {

        long userId = (Long)session.getAttribute("userId");

        Map<String, String> resultMap = new HashMap<>();

        if(postService.createPost(userId, contents)) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;

    }

}
