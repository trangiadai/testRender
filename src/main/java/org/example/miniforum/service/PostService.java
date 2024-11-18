package org.example.miniforum.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.miniforum.dto.request.PostRequest;
import org.example.miniforum.dto.response.PostResponse;
import org.example.miniforum.mapper.CategoryMapper;
import org.example.miniforum.mapper.ImageMapper;
import org.example.miniforum.mapper.PostMapper;
import org.example.miniforum.model.Category;
import org.example.miniforum.model.Image;
import org.example.miniforum.model.Post;
import org.example.miniforum.model.User;
import org.example.miniforum.repository.CategoryRepository;
import org.example.miniforum.repository.ImageRepository;
import org.example.miniforum.repository.PostRepository;
import org.example.miniforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository CategoryRepository;;

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Post createPost(PostRequest postRequest) {
        log.info("After Post");
        Post post = post = postMapper.toPost(postRequest);
        log.info("Create Post");


        post.setComments(0L);
        post.setVotes(0L);

        List<Image> imageEntities = new ArrayList<>();
        //log.info("images: {} " + postRequest.getImages().size());
        //log.info("category: {} " + postRequest.getCategories().size());

        //handle categories
        if(postRequest.getCategories() != null) {
            Set<Category> categories = new HashSet<>();

            for(Category category : postRequest.getCategories()) {
                categoryRepository.findById(category.getId()).
                        ifPresent(categories::add);
                //System.out.println("categories: " + categories.size() + " cate: " + category.getId());
            }
            post.setCategories(categories);
        }

        if(postRequest.getImages() != null && !postRequest.getImages().isEmpty()) {
            for (MultipartFile imageFile : postRequest.getImages()) {
                try {
                    String imageUrl = cloudinaryService.uploadImage(imageFile);
                    Image image = new Image();
                    image.setImageUrl(imageUrl);
                    image.setPost(post);
                    imageEntities.add(image);
                } catch (IOException e) {
                    log.error("====> image service error: {}", e.getMessage());
                }

            }

            // image list auto save to image table
            post.setImages(imageEntities);
        }

        //set userId(author)
        userRepository.findById(postRequest.getUserId()).ifPresent(post::setUser);
        log.info("set user {}", postRequest.getUserId());


        return postRepository.save(post);
    }

    public List<PostResponse> getAllPosts() {

        List<Post> posts = postRepository.findAll();

        List<PostResponse> responses = new ArrayList<>();

        for (Post post : posts) {
            PostResponse postResponse = postMapper.toPostResponse(post);
            log.info("=======> {}",postResponse.toString());
            responses.add(postResponse);
        }

        return responses;
    }


    public PostResponse getPostById(int id) {
        PostResponse postResponse = postMapper.toPostResponse(postRepository.findById(id).get());
        log.info("=======> post data: {}",postResponse.toString());
        return postResponse;
    }

    public void likePost(int postId) {
        Post post = postRepository.findById(postId).get();
        post.addVote();
        System.out.println("like post " + postId + "votes: " + post.getVotes());
        postRepository.save(post);
    }

    public List<PostResponse> getPostsByUserId(int userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        List<PostResponse> responses = new ArrayList<>();
        for (Post post : posts) {
            PostResponse postResponse = postMapper.toPostResponse(post);
            responses.add(postResponse);
        }
        return responses;
    }
}