package pl.espeo.sampleproject.controller.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class PostDto {

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Content is required")
    private String content;
}
