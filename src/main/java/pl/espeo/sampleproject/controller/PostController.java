package pl.espeo.sampleproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.espeo.sampleproject.controller.dto.PostDto;
import pl.espeo.sampleproject.controller.dto.PostWithoutCommentsDto;
import pl.espeo.sampleproject.model.Post;
import pl.espeo.sampleproject.service.PostService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostWithoutCommentsDto>> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort,
                                                                 @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return new ResponseEntity<>(PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber, sortDirection)), HttpStatus.OK);
    }

    @GetMapping("/posts/comments")
    public ResponseEntity<List<Post>> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return new ResponseEntity<>(postService.getPostsWithComments(pageNumber, sortDirection), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id) {
        return new ResponseEntity<>(postService.getSinglePost(id), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@Valid @RequestBody PostDto post) {
        Post newPost = postService.addPost(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();

//        return ResponseEntity.created(location).build();
        return new ResponseEntity<>(postService.addPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> editPost(@Valid @RequestBody PostDto post, @PathVariable long id) {
        return new ResponseEntity<>(postService.editPost(post, id), HttpStatus.OK);
    }

    @PostMapping("/posts/{id}")
    public ResponseEntity<Post> editOrCreatePost(@Valid @RequestBody PostDto post, @PathVariable long id) {
        return new ResponseEntity<>(postService.editOrCreatePost(post, id), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
