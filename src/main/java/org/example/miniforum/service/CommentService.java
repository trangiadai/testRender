package org.example.miniforum.service;

import lombok.extern.slf4j.Slf4j;
import org.example.miniforum.dto.request.CommentRequest;
import org.example.miniforum.dto.response.CommentResponse;
import org.example.miniforum.model.Comment;
import org.example.miniforum.model.Post;
import org.example.miniforum.repository.CommentRepository;
import org.example.miniforum.repository.PostRepository;
import org.example.miniforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment createComment(CommentRequest commentRequest) {
        System.out.println("=========> userid: " + commentRequest.getUserId());
        Comment comment = new Comment();
        Post post = postRepository.findById(commentRequest.getPostId()).get();

        comment.setContent(commentRequest.getContent());
        comment.setUser(userRepository.findById(commentRequest.getUserId()).get());
        comment.setPost(post);

        post.setComment(comment);
        postRepository.save(post);

        System.out.println("comment of post: " + post.getComment());
        return commentRepository.save(comment);

    }
}
