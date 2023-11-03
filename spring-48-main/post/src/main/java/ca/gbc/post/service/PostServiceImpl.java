package ca.gbc.post.service;

import ca.gbc.post.dto.PostRequest;
import ca.gbc.post.dto.PostResponse;
import ca.gbc.post.model.Post;
import ca.gbc.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MongoTemplate mongoTemplate;

    // Method to create a new post
    @Override
    public void createPost(PostRequest postRequest) {
        log.info("Creating a new post: {}", postRequest.getTitle());

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .author(postRequest.getAuthor())
                .build();

        postRepository.save(post);
        log.info("Post {} is saved", post.getId());
    }

    // Method to update a post
    @Override
    public String updatePost(String postId, PostRequest postRequest) {
        log.info("Updating a post with Id: {}", postId);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(postId));
        Post post = mongoTemplate.findOne(query, Post.class);

        if (post != null) {
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setAuthor(postRequest.getAuthor());

            log.info("Post {} is updated", post.getId());
            return String.valueOf(postRepository.save(post).getId());
        }
        return postId;
    }

    // Method to delete a post
    @Override
    public void deletePost(String postId) {
        log.info("Deleting post with Id: {}", postId);
        postRepository.deleteById(postId);
    }

    // Method to retrieve a list of all posts
    @Override
    public List<PostResponse> getAllPosts() {
        log.info("Returning a list of posts");
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToPostResponse).toList();
    }

    // Helper method to map Post entity to PostResponse DTO
    private PostResponse mapToPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .build();
    }
}
