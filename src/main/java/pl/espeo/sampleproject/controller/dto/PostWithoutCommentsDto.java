package pl.espeo.sampleproject.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostWithoutCommentsDto {

    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
