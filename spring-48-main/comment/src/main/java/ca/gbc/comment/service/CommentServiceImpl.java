package ca.gbc.comment.service;

import ca.gbc.comment.dto.CommentRequest;
import ca.gbc.comment.dto.CommentResponse;
import ca.gbc.comment.model.Comment;
import ca.gbc.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void createComment(CommentRequest commentRequest) {
        log.info("Creating a new comment: {}", commentRequest.getContent());

        Comment comment = Comment.builder()
                .postId(commentRequest.getPostId())
                .content(commentRequest.getContent())
                .author(commentRequest.getAuthor())
                .build();

        commentRepository.save(comment);
        log.info("Comment {} is saved", comment.getId());
    }

    @Override
    public Long updateComment(Long commentId, CommentRequest commentRequest) {
        log.info("Updating a comment with Id: {}", commentId);

        Comment query = Comment.builder().id(commentId).build();
        Optional<Comment> comment = commentRepository.findOne(Example.of(query));

        if (comment.isPresent()) {
            comment.get().setPostId(commentRequest.getPostId());
            comment.get().setContent(commentRequest.getContent());
            comment.get().setAuthor(commentRequest.getAuthor());

            log.info("Comment {} is updated", comment.get().getId());
            return commentRepository.save(comment.get()).getId();
        }
        return commentId;
    }

    @Override
    public void deleteComment(Long commentId) {
        log.info("Deleting comment with Id: {}", commentId);
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentResponse> getAllComments() {
        log.info("Returning a list of comments");
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(this::mapToCommentResponse).toList();
    }

    private CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .build();
    }
}
