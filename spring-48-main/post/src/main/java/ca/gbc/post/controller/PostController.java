package ca.gbc.post.controller;

import ca.gbc.post.dto.PostRequest;
import ca.gbc.post.dto.PostResponse;
import ca.gbc.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    // Endpoint for creating a new post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostRequest postRequest) {
        postService.createPost(postRequest);
    }

    // Endpoint to retrieve a list of all posts
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    // Endpoint to update a post
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable("postId") String postId, @RequestBody PostRequest postRequest) {
        String updatePostId = postService.updatePost(postId, postRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/post/" + updatePostId);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    // Endpoint to delete a post
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") String postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
