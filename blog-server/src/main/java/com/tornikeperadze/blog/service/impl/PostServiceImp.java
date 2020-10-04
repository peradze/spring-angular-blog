package com.tornikeperadze.blog.service.impl;

import com.tornikeperadze.blog.dto.CommentDto;
import com.tornikeperadze.blog.dto.request.PostRequest;
import com.tornikeperadze.blog.dto.response.PostDetailResponse;
import com.tornikeperadze.blog.dto.response.PostListResponse;
import com.tornikeperadze.blog.mapper.PostMapper;
import com.tornikeperadze.blog.model.Comment;
import com.tornikeperadze.blog.model.Post;
import com.tornikeperadze.blog.model.User;
import com.tornikeperadze.blog.repository.CommentRepository;
import com.tornikeperadze.blog.repository.PostRepository;
import com.tornikeperadze.blog.repository.UserRepository;
import com.tornikeperadze.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImp.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public PostRequest save(PostRequest post) {
        try {
            Post p = PostMapper.INSTANCE.postDtoToPost(post);
            User user = userRepository.findByEmail(post.getUserEmail()).get();
            p.setUser(user);
            p.setCreatedAt(LocalDateTime.now());
            postRepository.save(p);
        } catch (Exception e) {
            logger.error("Cannot save to database. {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return post;
    }

    @Override
    public List<PostListResponse> getAll() {
        List<PostListResponse> postListResponses = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            String author = "";
            if (post.getUser().getFirstName() != null) {
                author = post.getUser().getFirstName() + " " + post.getUser().getLastName();
            }
            String contentPreview = "...";
            int startIndex = post.getContent().indexOf("<p");
            int endIndex = post.getContent().indexOf("</p");
            if (startIndex != -1 && endIndex != -1) {
                contentPreview = post.getContent().substring(startIndex, endIndex);
            }
            postListResponses.add(new PostListResponse(
                    post.getId(),
                    post.getTitle(),
                    post.getCategory().getName(),
                    author,
                    contentPreview,
                    post.getCreatedAt()
            ));
        }
        return postListResponses;
    }

    @Override
    public PostDetailResponse getPostDetail(Long id) {
        Post post = postRepository.getOne(id);
        List<Comment> postComments = commentRepository.findByPost_Id(post.getId());
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : postComments) {
            commentDtos.add(new CommentDto(
                    comment.getComment(),
                    comment.getUser().getFirstName() + " " + comment.getUser().getLastName(),
                    comment.getCreatedAt()
            ));
        }
        PostDetailResponse postDetailResponse = new PostDetailResponse();
        String author = "";
        if (post.getUser().getFirstName() != null) {
            author = post.getUser().getFirstName() + " " + post.getUser().getLastName();
        }
        postDetailResponse.setId(post.getId());
        postDetailResponse.setAuthor(author);
        postDetailResponse.setCategory(post.getCategory().getName());
        postDetailResponse.setContent(post.getContent());
        postDetailResponse.setCreatedAt(post.getCreatedAt());
        postDetailResponse.setTitle(post.getTitle());
        postDetailResponse.setComments(commentDtos);
        return postDetailResponse;
    }
}
