package ca.gbc.post.service;

import ca.gbc.post.dto.PostRequest;
import ca.gbc.post.dto.PostResponse;

import java.util.List;

public interface PostService {
    void createPost(PostRequest postRequest);

    String updatePost(String postId, PostRequest postRequest);

    void deletePost(String postId);

    List<PostResponse> getAllPosts();
}
