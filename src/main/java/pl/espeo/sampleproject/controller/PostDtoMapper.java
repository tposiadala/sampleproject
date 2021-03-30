package pl.espeo.sampleproject.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.espeo.sampleproject.controller.dto.PostWithoutCommentsDto;
import pl.espeo.sampleproject.model.Post;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDtoMapper {

    public static List<PostWithoutCommentsDto> mapToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(PostDtoMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public static PostWithoutCommentsDto mapToPostDto(Post post) {
        return PostWithoutCommentsDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
