package pl.espeo.sampleproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.espeo.sampleproject.controller.dto.PostDto;
import pl.espeo.sampleproject.exception.EntityNotFoundException;
import pl.espeo.sampleproject.model.Comment;
import pl.espeo.sampleproject.model.Post;
import pl.espeo.sampleproject.repository.CommentRepository;
import pl.espeo.sampleproject.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Cacheable(cacheNames = "Posts")
    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "PostsWithComments")
    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> postIds = allPosts.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(postIds);
        allPosts.forEach(post -> post.setComments(extractCommentsForPost(comments, post.getId())));

        return allPosts;
    }

    @Cacheable(cacheNames = "SinglePost", key = "#id")
    public Post getSinglePost(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @CachePut(cacheNames = "SinglePost", key = "#result.id")
    public Post addPost(PostDto postDto) {
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setContent(postDto.getContent());
        newPost.setCreated(LocalDateTime.now());
        return postRepository.save(newPost);
    }

    @Transactional
    @CachePut(cacheNames = "SinglePost", key = "#result.id")
    public Post editPost(PostDto postDto, long id) {
        return postRepository.findById(id)
                .map(element -> {
                    element.setTitle(postDto.getTitle());
                    element.setContent(postDto.getContent());
                    return element; // hibernate dirty checking
                })
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Transactional
    @CacheEvict(cacheNames = "SinglePost", key = "#id")
    public void deletePost(long id) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        postRepository.delete(existingPost);
    }

    @Transactional
    @CachePut(cacheNames = "SinglePost", key = "#result.id")
    public Post editOrCreatePost(PostDto postDto, long id) {
        List<Integer> list = new ArrayList<>();
        Integer sum = list.stream().reduce(Integer::sum).orElseGet(() -> {
            return 1;
        });
    return postRepository.findById(id)
            .map(element -> {
                element.setTitle(postDto.getTitle());
                element.setContent(postDto.getContent());
                return element; // hibernate dirty checking
            })
            .orElseGet(() -> {
                Post newPost = new Post();
                newPost.setTitle(postDto.getTitle());
                newPost.setContent(postDto.getContent());
                newPost.setCreated(LocalDateTime.now());
                return postRepository.save(newPost);
            });
    }

    @CacheEvict(cacheNames = "PostsWithComments")
    public void clearPostsWithComments() {

    }

    private List<Comment> extractCommentsForPost(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }
}
