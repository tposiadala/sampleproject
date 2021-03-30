package pl.espeo.sampleproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.espeo.sampleproject.model.Post;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void shouldGetSinglePost() {
        //given
        //when
        Post singlePost = postService.getSinglePost(1L);
        //then
        assertThat(singlePost).isNotNull();
        assertThat(singlePost.getTitle()).isEqualTo("Test post 1");
        assertThat(singlePost.getContent()).isEqualTo("Content 1");
        //assertThat(singlePost.getComment()).hasSize(9); //nie pobiera postow, bo sa one pobierane dopiero, kiedy leci getter na encji np. przy uzyciu objectMappera (LAZY FETCH)
    }
}